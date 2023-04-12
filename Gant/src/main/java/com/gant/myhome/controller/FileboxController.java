package com.gant.myhome.controller;


import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.zeroturnaround.zip.ZipUtil;

import com.gant.myhome.domain.FFileinfo;
import com.gant.myhome.domain.FFolder;
import com.gant.myhome.domain.Filebox;
import com.gant.myhome.domain.FileboxSaveFolder;
import com.gant.myhome.domain.Members;
import com.gant.myhome.domain.Project;
import com.gant.myhome.service.FFileinfoService;
import com.gant.myhome.service.FFolderService;
import com.gant.myhome.service.FileboxService;
import com.gant.myhome.service.MemberService;
import com.gant.myhome.service.ProjectService;

@Controller
@RequestMapping(value="/filebox")
public class FileboxController {
	
private static final Logger logger = LoggerFactory.getLogger(MembersController.class);
	
	private MemberService  memberservice;
	private ProjectService projectservice;
	private FileboxService fileboxservice;
	private FFolderService ffolderservice;
	private FFileinfoService ffileinfoservice;
	
	private FileboxSaveFolder fileboxsavefolder;
	
	@Autowired
	public FileboxController(MemberService memberservice, ProjectService projectservice, FileboxService fileboxservice,
						FFolderService ffolderservice,FFileinfoService ffileinfoservice,
						FileboxSaveFolder fileboxsavefolder) {
		this.memberservice = memberservice;
		this.projectservice = projectservice;
		this.fileboxservice = fileboxservice;
		this.ffolderservice = ffolderservice;
		this.ffileinfoservice = ffileinfoservice;
		this.fileboxsavefolder = fileboxsavefolder;
	}
	
	@RequestMapping(value="/home")
	public ModelAndView home(int p_no, ModelAndView mv) {
		//해당 프로젝트번호에 대한 파일보관함 이용이 처음이면(폴더조회결과가없으면) 첫화면의 폴더를 생성한다.
		int select_result = ffolderservice.selectCount(p_no);
		int insert_result = 0;
		FFolder ffolder = new FFolder();
		ffolder.setP_no(p_no);
		if(select_result==0) { //조회 결과없으면 첫 폴더 생성
			insert_result = ffolderservice.insert(ffolder); //폴더 생성 ( 폴더 번호 1번)
			File path1 = new File(fileboxsavefolder.getSavefolder() + p_no + File.separator);
			logger.info("무슨값?"+fileboxsavefolder.getSavefolder()+ p_no + File.separator);
			if (!(path1.exists())) {
				path1.mkdirs(); //새로운 폴더를 생성
			}
			if(insert_result==0) {
				mv.addObject("message","파일보관함 이용 불가");
				mv.setViewName("error/error");
				return mv;
			}
		}
		//첫화면엔 폴더번호 1번인 경로의 리스트가 보이도록 경로값을 보낸다.
		ffolder = ffolderservice.selectFirst(p_no);
		mv.addObject("p_no",p_no);
		mv.addObject("ffolder",ffolder);
		mv.setViewName("filebox/home");
		return mv;
	}
	
	@ResponseBody
	@PostMapping(value="/fileUpload")
	public int fileUpload(@RequestPart("file") MultipartFile file,
	        			  @RequestPart("value_store") FFileinfo ffileinfo, 
	        			  @RequestPart("value_store") Filebox filebox,
	        			  @RequestPart("value_store") FFolder ffolder,
	        			  ModelAndView mv,
	        			  HttpServletRequest request) throws Exception {
		
		MultipartFile uploadfile = file;
		logger.info(filebox.getId()+filebox.getP_no());
		logger.info(ffileinfo.getFile_name()+ffileinfo.getExtension());
		if (!uploadfile.isEmpty()) {
			String fileName = uploadfile.getOriginalFilename();//원래 파일명
			logger.info("원래 파일명:"+fileName);
			logger.info("뭐가나올까?:"+filebox.getP_no()+ffolder.getFolder_path());
			//업로드 경로: getsavefolder + 프로젝트 번호 + 폴더 경로 
			String fileDBName = fileDBName(fileName, fileboxsavefolder.getSavefolder(), ffolder.getFolder_path());
			logger.info("파일 저장될 경로와 이름 = " + fileDBName);
			
			//transferTo(File path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
			uploadfile.transferTo(new File(fileboxsavefolder.getSavefolder() +fileDBName));
			logger.info("transferTo path = " + fileboxsavefolder.getSavefolder() + fileDBName);
			//바뀐 파일명으로 저장
			ffileinfo.setFile_save_path(fileDBName);
		}
		//프로젝트 번호 , 아이디가지고 파일보관함작성테이블 insert! 
		int result = fileboxservice.insert(filebox);
		int result2 = 0;
		logger.info("새로 생긴 파일번호:"+filebox.getFile_num());
		//만든 파일번호,파일명,확장자,폴더번호,저장경로가지고 파일명저장
		if(result ==1) {
			ffileinfo.setFile_num(filebox.getFile_num());
			result2 = ffileinfoservice.insert(ffileinfo);
			logger.info("파일업로드 후 DB저장성공");
		}
		//DB에 저장 성공하면 값1
		logger.info("저장값은?:"+result2);
		return result2;
	}
	
	private String fileDBName(String fileName, String saveFolder, String nextPath) {
		
		//새로운 폴더 이름 : 오늘 년+월+일
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR); //오늘 년도 구합니다.
		int month = c.get(Calendar.MONTH) + 1; //오늘 월 구합니다.
		int date = c.get(Calendar.DATE); //오늘 일 구합니다.
		
		String homedir = saveFolder+nextPath;
		File path1 = new File(homedir);
		if (!(path1.exists())) {
			path1.mkdirs(); //새로운 폴더를 생성
		}
		
		// 난수를 구합니다.
		Random r = new Random();
		int random = r.nextInt(10000000);
		
		/**** 확장자 구하기 시작 ****/
		int index = fileName.lastIndexOf(".");
		// 문자열에서 특정 문자열의 위치 값(index)를 반환합니다.
		// indexOf가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
		// lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
		// (파일명에 점이 여러개 있을 경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다.)
		logger.info("index = " + index);
		
		String fileExtension = fileName.substring(index+1);
		logger.info("fileExtension = " + fileExtension);
		/**** 확장자 구하기 끝 ****/
		
		//새로운 파일명
		String refileName = "filebox" + year + month + date + random + "." + fileExtension;
		logger.info("refileName = " + refileName);
		
		//오라클 디비에 저장될 파일 명
		//String fileDBName =  + "/" + refileName;
		String fileDBName = nextPath + refileName;
		logger.info("fileDBName = " + fileDBName);
		return fileDBName;
	}
	
	@ResponseBody
	@PostMapping(value="/addFolder")
	public int addFolder(FFolder ffolder) {
		int result = ffolderservice.insert(ffolder);
		File path1 = new File(fileboxsavefolder.getSavefolder()+ffolder.getFolder_path());
		if (!(path1.exists())) {
			path1.mkdirs(); //새로운 폴더를 생성
		}
		logger.info("폴더추가성공이면 1 : "+result);
		return result;
	}
	
	@ResponseBody
	@PostMapping(value="/loadAll")
	public Map<String,Object> loadAll(FFolder ffolder, int included_folder_num) {
		List<FFolder> folder_list = ffolderservice.selectSubFolder(ffolder); //한 단계 하위 폴더리스트 조회
		
		Map<String,Integer> param_map = new HashMap<String,Integer>();
		param_map.put("included_folder_num", included_folder_num);
		param_map.put("p_no", ffolder.getP_no());
		List<Map<String,Object>> file_list = fileboxservice.selectFile(param_map); //파일정보테이블과 조인하여 폴더번호에 해당하는 파일들 조회
		logger.info("폴더리스트 불러오는 개수:"+folder_list.size());
		logger.info("파일리스트 불러오는 개수:"+file_list.size());
		
		Map<String,Object> send_map = new HashMap<String,Object>(); //폴더와 파일 리스트들을 반환
		send_map.put("file", file_list);
		send_map.put("folder", folder_list);
		return send_map;
	}
	
	@ResponseBody
	@PostMapping(value="/editName")
	public int editName(int num, String type, String name) {
		
		int result = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("num", num);
		map.put("name", name);
		if(type.equals("파일")) {
			result = ffileinfoservice.update(map);
		}else if(type.equals("폴더")) {
			result = ffolderservice.update(map);
		}
		
		return result;
	}
	
	@ResponseBody
	@PostMapping(value="/delete")
	public int delete(String id, int num, String type, int p_no, String folder_path) {
		
		int result = 0;
		Members m = memberservice.getMemberInfo(id); //관리자인지확인
		String admin = m.getAdmin();
		Project p = projectservice.getProjectOne(p_no); //프로젝트생성자인지 확인
		String host_id = p.getP_hostid();
		logger.info("삭제클릭한사람:"+id);
		logger.info("관리자인가?"+admin);
		logger.info("프로젝트생성자?"+host_id);
		if(type.equals("파일")) {
			String uploader = fileboxservice.selectUploader(num); //파일생성자인지 확인
			logger.info("업로더?:"+uploader);
			if(admin.equals("true") || id.equals(host_id) || id.equals(uploader)) { //삭제클릭한 ID가 관리자,프로젝트생성자,파일생성자이면 삭제O
				result = fileboxservice.delete(num);
			}
		}else if(type.equals("폴더")) {
			
			if(admin.equals("true") || id.equals(host_id)) { //삭제클릭한 ID가 관리자,프로젝트생성자이면 삭제O
				
				FFolder ffolder = new FFolder();
				ffolder.setP_no(p_no);
				ffolder.setFolder_path(folder_path);
				
				result  = fileboxservice.deleteFileinFolder(ffolder); //해당폴더 하위 경로에 있는 파일을 삭제
				result += ffolderservice.delete(ffolder); //하위폴더도 삭제
			}
		}
		
		return result;
	}
	
	@ResponseBody
	@PostMapping(value="/fileDown")
	public byte[] fileDownload(FFileinfo ffileinfo, HttpServletResponse response) throws Exception {
		
		String sFilePath = fileboxsavefolder.getSavefolder() + ffileinfo.getFile_save_path();
		
		File file = new File(sFilePath);
		
		byte[] bytes = FileCopyUtils.copyToByteArray(file);
		
		String sEncoding = new String(ffileinfo.getFile_name().getBytes("utf-8"), "ISO-8859-1");
		
		//Content-Disposition: attachment: 브라우저는 해당 Content를 처리하지 않고, 다운로드하게 됩니다.
		response.setHeader("Content-Disposition", "attachment;filename="+sEncoding);
		
		response.setContentLength(bytes.length);
		return bytes;
	}
	
	@ResponseBody
	@PostMapping(value="/folderDown")
	public byte[] folderDownload(FFolder ffolder, HttpServletResponse response) throws Exception {

		
		List<FFileinfo> list = ffileinfoservice.selectAllFileInFolder(ffolder);//폴더 하위에있는 모든 파일의 이름,확장자,파일경로를 가져오는 동작 수행
			
			//카피한 파일을 모아둘 임시폴더 (마지막에 파일삭제할 예정)
			String zipdir = fileboxsavefolder.getSavefolder() + "fordownzip";
			File path1 = new File(zipdir);
			if (!(path1.exists())) {
				path1.mkdir(); //새로운 폴더를 생성
			}
			//List<String> file_names = new ArrayList<String>();
			
			for(FFileinfo ffileinfo : list) {
				//다운로드 선택한 폴더끝 부분부터의 경로
				String new_folder_path = zipdir + File.separator + 
								ffileinfo.getFile_save_path().substring(ffolder.getFolder_path().lastIndexOf("/")+1 , ffileinfo.getFile_save_path().lastIndexOf("/")+1);
				rename(ffileinfo, new_folder_path); //압축할 임시폴더로 업로드경로와 동일한 폴더경로 생성과 파일명 재지정
			}
			
			//다운로드할 폴더 압축 (임시폴더 -> zip 파일)
			File zipfile = new File(fileboxsavefolder.getSavefolder()+ffolder.getFolder_name()+".zip");
	        ZipUtil.pack(new File(zipdir+File.separator), zipfile);
	        
	        //압축한 zip파일 다운로드
			byte[] bytes = FileCopyUtils.copyToByteArray(zipfile);
			response.setContentType("application/zip");
			String sEncoding = new String((ffolder.getFolder_name()+".zip").getBytes("utf-8"), "ISO-8859-1");
	        response.setHeader("Content-Disposition", "attachment;filename="+ sEncoding);
	        response.setStatus(HttpServletResponse.SC_OK);
			response.setContentLength(bytes.length);
			
	        //임시폴더와 zip파일 삭제
	        FileUtils.cleanDirectory(path1); //임시폴더 내부 파일과 폴더
	        zipfile.delete(); //임시폴더 압축한 zip파일
			return bytes;

	}
	
	//다운받을 기존 폴더명과 난수파일명 -> 원래파일명 바꿔서 임시폴더에 카피하는 메소드
	public void rename(FFileinfo ffileinfo, String new_folder_path) {
		
		File origin_path = new File(fileboxsavefolder.getSavefolder()+ffileinfo.getFile_save_path());
		
		
		String new_path = new_folder_path + ffileinfo.getFile_name()+"."+ffileinfo.getExtension();
		File path1 = new File(new_folder_path);
		if (!(path1.exists())) {
			path1.mkdir(); //새로운 폴더를 생성
		}
		File rename_path = new File(new_path);
	  try {
	    FileUtils.copyFile(origin_path, rename_path);
	  } catch (IOException e) {
	    e.printStackTrace();
	  }
	}
	
	@ResponseBody
	@PostMapping(value="/loadFolderForMove")
	public List<FFolder> loadFolderForMove(FFolder ffolder){
		List<FFolder> list = ffolderservice.selectSubFolder(ffolder); //한 단계 하위경로의 폴더들 가져옴
		
		return list; 
	}
	
	@ResponseBody
	@PostMapping(value="/updateForMove")
	public int updateForMove(String folder_path, int p_no,
			@RequestParam(value="folder_to_move", defaultValue="", required=false) String folder_to_move,
			@RequestParam(value="file_to_move", defaultValue="0", required=false) int file_to_move) {
			int result = 0;
			Map<String,Object> map = new HashMap<String,Object>();
			if(!folder_to_move.equals("")) { //폴더를 이동(file_to_move값은 비어있음)
				map.put("p_no", p_no);
				map.put("destination_folder_path", folder_path); //변경될 위치의 폴더 경로
				map.put("folder_to_move", folder_to_move); //옮길 폴더 경로
				result = ffolderservice.updateLocation(map); //옮길 폴더와 하위폴더도 경로 변경
				if(result>0) result=2;
					
			}else { //파일을 이동(file_to_move값이 들어있음)
				map.put("p_no", p_no); //프로젝트 번호
				map.put("file_num", file_to_move); //이동할 파일의 파일번호
				map.put("folder_path", folder_path); //변경될 위치의 폴더경로
				result = ffileinfoservice.updateLocation(map); //파일이 포함된 폴더경로 변경
				if(result>0) result=1;
			}
			return result;
	}
}
