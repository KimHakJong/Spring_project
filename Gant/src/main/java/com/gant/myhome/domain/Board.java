package com.gant.myhome.domain;

import org.springframework.web.multipart.MultipartFile;

public class Board {
	private int    BOARD_NUM; //글번호
	private String BOARD_NAME; // 작성자
	private String BOARD_PASS; // 비밀번호
	private String BOARD_SUBJECT; // 제목
	private String BOARD_CONTENT; // 내용
	private String BOARD_FILE; // 실제 저장된 파일의 이름
	private int    BOARD_RE_REF; //답변글 작성시 참조되는 글의 번호
	private int    BOARD_RE_LEV; // 답변 글의 깊이 (원문글:0,답변:1,답변의 답변2)
	private int    BOARD_RE_SEQ; // 답변 글의 순서 (원문글 기준으로 보여주느 순서)
	private int    BOARD_READCOUNT; // 글의 조회수
    
	//board_write.jsp에서 name 속성 확인하세요
    // <input type="file" id="upfile" name="uploadfile" > 확인
	private MultipartFile uploadfile;

	private String BOARD_ORIGINAL; // 첨부될 파일의 이름
	private String BOARD_DATE; //글의 작성날짜
	private int CNT; // 답글 갯수
	
	
	public int getBOARD_NUM() {
		return BOARD_NUM;
	}
	public void setBOARD_NUM(int bOARD_NUM) {
		BOARD_NUM = bOARD_NUM;
	}
	public String getBOARD_NAME() {
		return BOARD_NAME;
	}
	public void setBOARD_NAME(String bOARD_NAME) {
		BOARD_NAME = bOARD_NAME;
	}
	public String getBOARD_PASS() {
		return BOARD_PASS;
	}
	public void setBOARD_PASS(String bOARD_PASS) {
		BOARD_PASS = bOARD_PASS;
	}
	public String getBOARD_SUBJECT() {
		return BOARD_SUBJECT;
	}
	public void setBOARD_SUBJECT(String bOARD_SUBJECT) {
		BOARD_SUBJECT = bOARD_SUBJECT;
	}
	public String getBOARD_CONTENT() {
		return BOARD_CONTENT;
	}
	public void setBOARD_CONTENT(String bOARD_CONTENT) {
		BOARD_CONTENT = bOARD_CONTENT;
	}
	public String getBOARD_FILE() {
		return BOARD_FILE;
	}
	public void setBOARD_FILE(String bOARD_FILE) {
		BOARD_FILE = bOARD_FILE;
	}
	public int getBOARD_RE_REF() {
		return BOARD_RE_REF;
	}
	public void setBOARD_RE_REF(int bOARD_RE_REF) {
		BOARD_RE_REF = bOARD_RE_REF;
	}
	public int getBOARD_RE_LEV() {
		return BOARD_RE_LEV;
	}
	public void setBOARD_RE_LEV(int bOARD_RE_LEV) {
		BOARD_RE_LEV = bOARD_RE_LEV;
	}
	public int getBOARD_RE_SEQ() {
		return BOARD_RE_SEQ;
	}
	public void setBOARD_RE_SEQ(int bOARD_RE_SEQ) {
		BOARD_RE_SEQ = bOARD_RE_SEQ;
	}
	public int getBOARD_READCOUNT() {
		return BOARD_READCOUNT;
	}
	public void setBOARD_READCOUNT(int bOARD_READCOUNT) {
		BOARD_READCOUNT = bOARD_READCOUNT;
	}
	public MultipartFile getUploadfile() {
		return uploadfile;
	}
	public void setUploadfile(MultipartFile uploadfile) {
		this.uploadfile = uploadfile;
	}
	public String getBOARD_ORIGINAL() {
		return BOARD_ORIGINAL;
	}
	public void setBOARD_ORIGINAL(String bOARD_ORIGINAL) {
		BOARD_ORIGINAL = bOARD_ORIGINAL;
	}
	public String getBOARD_DATE() {
		return BOARD_DATE;
	}
	public void setBOARD_DATE(String bOARD_DATE) {
		BOARD_DATE = bOARD_DATE.substring(0,10); //년-월-일 시:분:초 => 년-월-일;
	}
	
	public int getCNT() {
		return CNT;
	}
	public void setCNT(int cNT) {
		CNT = cNT;
	}
	
	
	@Override
	public String toString() {
		return "Board [BOARD_NUM=" + BOARD_NUM + ", BOARD_NAME=" + BOARD_NAME + ", BOARD_PASS=" + BOARD_PASS
				+ ", BOARD_SUBJECT=" + BOARD_SUBJECT + ", BOARD_CONTENT=" + BOARD_CONTENT + ", BOARD_FILE=" + BOARD_FILE
				+ ", BOARD_RE_REF=" + BOARD_RE_REF + ", BOARD_RE_LEV=" + BOARD_RE_LEV + ", BOARD_RE_SEQ=" + BOARD_RE_SEQ
				+ ", BOARD_READCOUNT=" + BOARD_READCOUNT + ", uploadfile=" + uploadfile + ", BOARD_ORIGINAL="
				+ BOARD_ORIGINAL + ", BOARD_DATE=" + BOARD_DATE + "]";
	}
	
}
