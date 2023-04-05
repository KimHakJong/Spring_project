package com.gant.myhome.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.gant.myhome.domain.Todolist;
import com.gant.myhome.service.TodolistService;


@Controller
@RequestMapping(value="/todolist")
//http://localhost:8088/gant/todolist/ 로 시작하는 주소 

public class TodolistController {
   
   private static final Logger logger = LoggerFactory.getLogger(TodolistController.class);
   
   private TodolistService todolistservice;

      
   @Autowired
   public TodolistController(TodolistService todolistservice) {
	   this.todolistservice = todolistservice;

   }
   
	public static void init(HttpServletResponse response) {
        response.setContentType("text/html; charset=utf-8");
        response.setCharacterEncoding("utf-8");
    }
	
   
   @RequestMapping(value="/receive", method=RequestMethod.GET)
   public ModelAndView Todolist(ModelAndView mv, 
		   						@RequestParam("p_no") int p_no, 
		   						@RequestParam(value="p_mids", required = false) String p_id,
		   						@RequestParam(value="p_mnames", required = false) String p_name,
		   						@RequestParam(value="page", defaultValue="1", required=false) int page)  
   {
	   
	   String p_ids[], p_names[];
	   
		int limit = 10; 
		
		int listcount = todolistservice.getListCount();
		
		int maxpage = (listcount + limit - 1) / limit;
		
		int startpage = ((page-1) /10) *10 +1;
		int endpage = startpage +10 -1;
		
		if(endpage>maxpage)
			endpage=maxpage;
		
		List<Todolist> todolist = todolistservice.getTodolist(page, limit, p_no);
	   	   
	   p_id = get_id(p_no);
	   p_name = get_name(p_no);
	   
	   p_ids = p_id.split(",");
	   p_names = p_name.split(",");
	   
	   
	   mv.addObject("p_no", p_no);
	   mv.addObject("p_id", p_ids);
	   mv.addObject("p_name", p_names);
		mv.addObject("page",page);
		mv.addObject("maxpage",maxpage);
		mv.addObject("startpage",startpage);
		mv.addObject("endpage",endpage);
		mv.addObject("listcount",listcount);
		mv.addObject("todolist",todolist);
		mv.addObject("limit",limit);
	   
	   
	   System.out.println("mv 결과" + mv);
	   

	   mv.setViewName("/todolist/todolist");
	   	   
	   return mv;

	}
   
   
   
private String get_name(int p_no) {
	String name = todolistservice.get_name(p_no);
	
	return name;
}

private String get_id(int p_no) {
	String id = todolistservice.get_id(p_no);
	
	
	return id;
}
   
   /*
   	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	private MySaveFolder mysavefolder;
	private BoardService boardService;
	private CommentService commentService;
	
	@Autowired
	public BoardController(BoardService boardservice, CommentService commentService, MySaveFolder mysavefolder) {
		this.mysavefolder = mysavefolder;
		this.boardService = boardservice;
		this.commentService = commentService;
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public ModelAndView boardList(
									@RequestParam(value="page",defaultValue="1",required=false) int page,
									ModelAndView mv	) {
		int limit = 10; 
				
		int listcount = boardService.getListCount();
		
		int maxpage = (listcount + limit - 1) / limit;
		
		int startpage = ((page-1) /10) *10 +1;
		int endpage = startpage +10 -1;
		
		if(endpage>maxpage)
			endpage=maxpage;
		
		List<Board> boardlist = boardService.getBoardList(page,limit);
		
		mv.setViewName("board/board_list");
		mv.addObject("page",page);
		mv.addObject("maxpage",maxpage);
		mv.addObject("startpage",startpage);
		mv.addObject("endpage",endpage);
		mv.addObject("listcount",listcount);
		mv.addObject("boardlist",boardlist);
		mv.addObject("limit",limit);
		return mv;
	}
	
	
	@GetMapping(value="/write")
	//RequestMapping(value="/write",method=RequestMethod.GET)
	public String board_write() {
		return "board/board_write";
	}
	
	@PostMapping("/add")
	public String add(Board board, HttpServletRequest request) throws Exception{
		
		MultipartFile uploadfile = board.getUploadfile();
		
		if(!uploadfile.isEmpty()) 
		{
			String fileName = uploadfile.getOriginalFilename();
			board.setBOARD_ORIGINAL(fileName);
			
			//<input type="hidden" name="board_file value="${boarddata.board_file}">
			
			//String saveFolder = request.getSession().getServletContext().getRealPath("resources")+"/upload";
			String saveFolder = mysavefolder.getSavefolder();
			String fileDBName = fileDBName(fileName, saveFolder);
			logger.info("fileDBName : "+ fileDBName);
			
			
			uploadfile.transferTo(new File(saveFolder + fileDBName));
			logger.info("transferTo path = " + saveFolder + fileDBName);
				
			//�ٲ� ���ϸ����� ����
			board.setBOARD_FILE(fileDBName);
		}
		
		boardService.insertBoard(board);
		logger.info(board.toString());
		return"redirect:list";

	}
	
	private String fileDBName(String fileName, String saveFolder) {
		
		
		Calendar c = Calendar.getInstance();
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH)+1;
		int date = c.get(Calendar.DATE);
		
		String homedir = saveFolder + "/" + year + "-" + month + "-" + date;
		
		logger.info(homedir);
		File path1 = new File(homedir);
		
		if(!(path1.exists())) {
			path1.mkdir(); 
		}
		
		
		Random r = new Random();
		int random = r.nextInt(100000000);
		

		int index = fileName.lastIndexOf(".");
		
		
		logger.info("index = " + index);
		
		String fileExtension = fileName.substring(index+1);
		logger.info("fileExtension = " + fileExtension);
		

		String refileName = "bbs" + year + month + date + random + "." + fileExtension;
		
		logger.info("refileName = " + refileName);
		
		
		String fileDBName = File.separator + year + "-" + month + "-" + date + File.separator + refileName;
		logger.info("fileDBName = " + fileDBName);
		
		return fileDBName;
	}
	

	@ResponseBody
	@RequestMapping(value="/list_ajax")
	public Map<String, Object> boardListAjax(@RequestParam(value="page", defaultValue="1", required=false) int page, 
			@RequestParam(value="limit", defaultValue="10", required=false) int limit){
		
		int listcount = boardService.getListCount();
		
		int maxpage = (listcount + limit - 1) / limit;
		
		
		int startpage = ((page-1) /10) *10 +1;
		
		int endpage = startpage +10 -1;
			
		if(endpage > maxpage)
			endpage = maxpage;
		
		List<Board> boardlist = boardService.getBoardList(page, limit);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		
		map.put("page",page);
		map.put("maxpage",maxpage);
		map.put("startpage",startpage);
		map.put("endpage",endpage); 
		map.put("listcount",listcount);
		map.put("boardlist",boardlist);
		map.put("limit",limit);
		
		
		return map;
	}
	
	
	
	@GetMapping("/detail")
	
	   public ModelAndView Detail (
	            int num,ModelAndView mv, HttpServletRequest request,
	            @RequestHeader(value="referer", required=false) String beforeURL){

		
		
		logger.info("referer: "+ beforeURL);
		if(beforeURL!=null && beforeURL.endsWith("list")) {
			boardService.setReadCountUpdate(num);
		}
		
		Board board = boardService.getDetail(num);
		
		if(board==null) {
			logger.info("상세보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message","상세보기 실패입니다.");
		}
		else {
			logger.info("상세보기 성공");
			int count = commentService.getListCount(num);
			mv.setViewName("board/board_view");
			mv.addObject("count", count);
			mv.addObject("boarddata", board);
		}
		return mv;
	}
	
	
	@GetMapping("/modifyView")
	
	   public ModelAndView BoardModifyView (
	            int num,ModelAndView mv, HttpServletRequest request){

				
		Board boarddata = boardService.getDetail(num);
		
		if(boarddata == null) {
			logger.info("수정보기 실패");
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message","수정보기 실패입니다.");
			return mv;
		}
		logger.info("수정 상세보기 성공");
		
		mv.addObject("boarddata", boarddata);
		mv.setViewName("board/board_modify");
			
		
		return mv;
	}
	
	@PostMapping("/modifyAction")
	   public String BoardModifyAction (
	            Board boarddata, String check, Model mv, HttpServletRequest request, RedirectAttributes rattr)
	throws Exception{
		
		boolean usercheck = boardService.isBoardWriter(boarddata.getBOARD_NUM(), boarddata.getBOARD_PASS());
		
		String url="";
		
		if(usercheck == false) {
			rattr.addFlashAttribute("result","passFail");
			rattr.addAttribute("num", boarddata.getBOARD_NUM());
			return "redirect:modifyView";
			 
		}
		MultipartFile uploadfile = boarddata.getUploadfile();
		//String saveFolder = request.getSession().getServletContext().getRealPath("resources")+"/upload";
		
		if(check != null && !check.equals("")) {//����
			logger.info("기존 파일 그대로 사용합니다");
			boarddata.setBOARD_ORIGINAL(check);
		
		}else {

			
			if(uploadfile!=null && !uploadfile.isEmpty()) {
				logger.info("���� �߰�/���� �Ǿ����ϴ�");

				String fileName = uploadfile.getOriginalFilename();//�������ϸ�
				boarddata.setBOARD_ORIGINAL(fileName);
				
				String saveFolder = mysavefolder.getSavefolder();
				
				
				String fileDBName = fileDBName(fileName, saveFolder);
				logger.info("fileDBName = " + fileDBName);
				
				uploadfile.transferTo(new File(saveFolder + fileDBName));
				
				logger.info("transferTo path = " + saveFolder + fileDBName);
				boarddata.setBOARD_FILE(fileDBName);
				
			}else {
				logger.info("선택한 파일이 없습니다");
				
				
				boarddata.setBOARD_FILE("");
				boarddata.setBOARD_ORIGINAL("");
				
			}//else end
		}//else end
		

		int result = boardService.boardModify(boarddata);
		
		if(result ==0) {
			logger.info("게시판 수정 실패");
			
			mv.addAttribute("url", request.getRequestURL());
			mv.addAttribute("message","게시판 수정실패입니다.");
			url="error/error";
		} else {
			logger.info("게시판 수정 완료");
		
			url="redirect:detail";
			
			rattr.addAttribute("num", boarddata.getBOARD_NUM());
			
		}
		return url; 
				
		
	}
	
	@GetMapping("/replyView")
	
	   public ModelAndView BoardReplyView (
	            int num,ModelAndView mv, HttpServletRequest request){

				
		Board board = boardService.getDetail(num);
		
		
		
		if(board == null) {

			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message","게시판 답글 가져오기 실패입니다.");
			
		}else {
	
			mv.addObject("message","답글 가져오기 성공.");
			mv.addObject("boarddata", board);
			mv.setViewName("board/board_reply");
			
		}

			
		
		return mv;
	}
	
	@PostMapping("/replyAction")
	   public ModelAndView BoardReplyAction (
	            Board board, ModelAndView mv, HttpServletRequest request, RedirectAttributes rattr)
	{
		
	
		int result = boardService.boardReply(board);
		
		if(result ==0) {
			
			mv.setViewName("error/error");
			mv.addObject("url", request.getRequestURL());
			mv.addObject("message","게시판 답변 처리 실패");
		} else {
			
			//mv.setViewName("redirect:list");
			//mv.setViewName("redirect:detail>num="+board.getBOARD_NUM());
			mv.addObject("message","답글 생성 성공.");
			rattr.addAttribute("num", board.getBOARD_NUM());
			mv.setViewName("redirect:detail");

		}
		return mv; 

	}
	

	
	@PostMapping("/delete")
	   public String BoardDeleteAction (
	            String BOARD_PASS, int num, Model mv, HttpServletRequest request, RedirectAttributes rattr)
	{
		
		boolean usercheck = boardService.isBoardWriter(num, BOARD_PASS);
		
		String url="";
		

		
		if(usercheck == false) {
			rattr.addFlashAttribute("result","passFail");
			rattr.addAttribute("num", num);
			return "redirect:detail";
			
		}
		
		int result = boardService.boardDelete(num);
		
		if(result == 0) {
			logger.info("게시판 삭제 실패");
			mv.addAttribute("url", request.getRequestURL());
			mv.addAttribute("message","삭제 실패");
			return "error/error";
			
		}
		
		logger.info("게시판 삭제 성공");
		
		
		rattr.addFlashAttribute("result", "deleteSuccess");
		
		return "redirect:list";
	}
	
	@ResponseBody
	@PostMapping("/down")
	   public byte[] BoardFileDown(String filename, HttpServletRequest request, String original,
			   HttpServletResponse response)
	throws Exception{
		

		
		String saveFolder = mysavefolder.getSavefolder();
		
		String sFilePath = saveFolder + filename;
		
		File file = new File(sFilePath);
		
		byte[] bytes = FileCopyUtils.copyToByteArray(file);
		
		String sEncoding = new String(original.getBytes("utf-8"), "ISO-8859-1");
		
		
		
		response.setHeader("Content-Disposition", "attachment;filename=" + sEncoding);
		
		response.setContentLength(bytes.length);
		return bytes;
		
	}*/


   
   
   

   
}