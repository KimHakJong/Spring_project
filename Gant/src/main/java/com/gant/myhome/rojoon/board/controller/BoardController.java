package com.gant.myhome.rojoon.board.controller;


import java.io.File;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.gant.myhome.rojoon.board.domain.Board;
import com.gant.myhome.rojoon.board.domain.BoardSaveFolder;
import com.gant.myhome.rojoon.board.service.BoardService;
import com.gant.myhome.rojoon.board.service.CommentService;




@Controller
@RequestMapping(value = "/board")//http://localhost:8088/myhome4/board/ 로 시작하는 주소 맴핑
public class BoardController {
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	private BoardSaveFolder  boardsavefolder;
	private BoardService  boardService;
	private CommentService commentService;
	
		
	@Autowired
	public BoardController(BoardService boardService ,
			CommentService  commentService,
			BoardSaveFolder  boardsavefolder
			) {
	this.boardService = boardService;
	this.commentService = commentService;
	this.boardsavefolder = boardsavefolder;
	}
	
	
	
	@RequestMapping(value = "/main" , method = RequestMethod.GET)
	public ModelAndView boardlist(
			@RequestParam(value = "page", defaultValue = "1", required = false) int page,
			@RequestParam(value = "search_name", required = false) String search_name,
			ModelAndView mv) {
		List<Board> boardlist = new ArrayList<Board>();
			
		int limit = 10; //  한 화면에 출력할 로우 갯수
		
		//개시물 갯수
		int listcount = 0 ; 
		
		//공지게시글 수
		int noticeCount = 0;
		
		//검색어가 있는경우
        if(search_name == null || search_name.equals("")) {	
			//검색어에 포함되어있는 게시글 수 
			listcount = boardService.getSearchListCount(search_name);
			
			//리스트를 받아옵니다.
			//검색어에 포함되어있는 게시글 리스트
			boardlist = boardService.getSearchBoardList(page,limit,search_name);
					
		}else {// 검색어가없는경우
			
			//총 리스트 수를 받아옵니다.
			listcount = boardService.getListCount();
			
			//리스트를 받아옵니다.
			//일반게시물 리스트
			boardlist = boardService.getBoardList(page,limit);
			
			//공지게시글 수
		    noticeCount = boardService.getNoticeCount();
		}
		
        
		//총 페이지 수
		int maxpage = (listcount + limit - 1) / limit;
		
		//현재 페이지에 보여줄 시작 페이지 수 (1, 11 , 21 등....)
		int startpage = ((page - 1) / 10) * 10 + 1 ;
		
		// 현재 페이지에 보여줄 마지막 페이지 수 (10 , 20 , 30 등....)
		int endpage = startpage + 10 - 1 ;
		
		if(endpage > maxpage) {
			endpage = maxpage;
		}
		

		mv.setViewName("board/boardList.jsp");
		mv.addObject("page", page);
		mv.addObject("maxpage" , maxpage);
		mv.addObject("startpage" , startpage);
		mv.addObject("endpage" , endpage);
		mv.addObject("listcount" , listcount);
		mv.addObject("boardlist" , boardlist);
		mv.addObject("limit" , limit);
		mv.addObject("noticeCount" , noticeCount);
		
		return mv;
	}
	
	//글쓰기
	@GetMapping(value = "/write") 
	//@RequestMapping(value = "/write" , method = RequestMethod.GET)
	public ModelAndView write(Principal principal,
                              ModelAndView mv ) {			
    String id = principal.getName();
    String admin = boardService.getadmindate(id);
    
    mv.setViewName("board/boardWrite");
	mv.addObject("admin", admin);
	mv.addObject("id", id);
	 return mv;
	}
	
      //글등록
		/*
		 * 스프링 컨테이너는 매개변수 Board객체를 생성하고
		 * Board객체의 setter 메서드들을 호출하여 사용자 입력값을 설정합니다.
		 * 매개변수의 이름과 setter의 property가 일치하면 됩니다.
		 */
		@PostMapping(value = "/add") 
		//@RequestMapping(value = "/add" , method = RequestMethod.POST)
		public String add(Board board ,
				          HttpServletRequest request,
				          @RequestParam(value = "noticebox",defaultValue = "false" ,required = false) String noticebox,
				          @RequestParam(value = "board_pass",defaultValue = "1" ,required = false) String board_pass // 비밀글 설정을 안했다면 1을 넣어준다.   
				         )
		                   throws Exception{
		    /*
		     * MultipartFile 클래스는 웹 애플리케이션에서 파일 업로드를 처리하는 방법을 제공하는 Spring Framework 웹 모듈의 일부입니다.
		     *  HTTP 요청의 일부로 서버에 전송된 업로드된 파일을 나타냅니다.
		     */
		    MultipartFile uploadfile = board.getUploadfile();	
			
		    if(!uploadfile.isEmpty()) {
		    	String fileName = uploadfile.getOriginalFilename();//원래 파일명
		    	board.setBoard_original(fileName); //원래 파일명 저장
                
		    	//업로드 파일 저장 경로
		    	String saveFolder = boardsavefolder.getSavefolder();
		    	
		    	String fileDBName = fileDBName(fileName , saveFolder);
		    	
		    	// transferTo(Flie path) : 업로드한 파일을 매개변수의 경로에 저장합니다.
		    	//import java.io.File;
		    	uploadfile.transferTo(new File(saveFolder + fileDBName));
		    	logger.info("transferTo path = " + saveFolder + fileDBName);
		    	//바뀐 파일명으로 저장
		    	board.setBoard_file(fileDBName);
		    }
		    
		    // 공지게시글 체크 여부는 admin 계정만 할 수 있다. 만약 noticebox값이 null이라면 "false"(일반글) 로 값을 넣어준다.
		    board.setBoard_notice(noticebox);
		    // 비밀글 설정을 안했다면 1
		    board.setBoard_pass(board_pass);
		        
			boardService.insertBoard(board); // 정의한 메서드 호출
			logger.info(board.toString()); // selectKey로 정의한 BOARD_NUM 값 확인해 봅니다.
			return "redirect:main";
		}
	
		
		private String fileDBName(String fileName, String saveFolder) {
			// 새로운 폴터 이름 : 오늘 년+월+일
			Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR); //오늘 년도 구합니다.
			int month = c.get(Calendar.MONTH);//오늘 월도 구합니다.
			int date = c.get(Calendar.DATE);//오늘 일 구합니다.
			
			String homedir = saveFolder + "/" + year + "-" + month + "-" + date;

			File path1 = new File(homedir);
			if(!(path1.exists())) { // 폴더의 유무 확인
				path1.mkdir(); // 새로운 폴더를 생성
			}
			
			//난수를 구합니다.
			Random r = new Random();
			int random = r.nextInt(100000000);
			
			/*** 확장자 구하기 시작  ***/
			int index = fileName.lastIndexOf(".");
			/* 문자열에서 특정 문자열의 위치 값(index)를 반환합니다.
			 * indexof가 처음 발견되는 문자열에 대한 index를 반환하는 반면,
			 * lastIndexOf는 마지막으로 발견되는 문자열의 index를 반환합니다.
			 * (파일명에 점에 여려개 있을경우 맨 마지막에 발견되는 문자열의 위치를 리턴합니다. 
			 */
			
			String fileExtension = fileName.substring(index+1);
			/*** 확장자 구하기 끝  ***/
			
			//새로운 파일명
			String refileName = "bbs" + year + month + date + random + "." + fileExtension;
			
			//오라클 디비에 저장될 파일 명
			//String fileDBName = "/" + year + "-" + month + "-" + date + "/" + refileName; 
			//File.separator 는 파일 시스템별 디렉터리 구분 문자를 나타내는 공용 정적 필드입니다.  즉 "/"을 나타낸다.
			String fileDBName = File.separator + year + "-" + month + "-" + date + File.separator + refileName; 
			logger.info("fileDBName = "+ fileDBName);
			return fileDBName;
		}
	
	
	
	


}
