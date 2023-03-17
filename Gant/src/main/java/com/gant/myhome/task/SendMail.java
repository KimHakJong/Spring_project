package com.gant.myhome.task;

import java.io.File;
import javax.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Component;

import com.gant.myhome.domain.MailVO;
import com.gant.myhome.domain.MySaveFolder;



@Component
public class SendMail {

	private static final Logger logger = LoggerFactory.getLogger(SendMail.class);
    
	//삭제
	//@Value("${sandfile}")
    //private String sandfile;
	
	@Autowired
	private JavaMailSenderImpl mailSender;
	
	//추가
	@Autowired
	private MySaveFolder mySaveFolder;
	

	public void sendMail(MailVO vo) {
		//추가
		String sandfile = mySaveFolder.getSendfile();
		
		
		//MimeMessagePreparator은 인터페이스이다.
		//다른 클래스에 implements하여 사용하지않고 익명클래스를 이용해서 사용할 것이다.
		MimeMessagePreparator mp = new MimeMessagePreparator() {
			
			//추상 메서드를 구현해야한다.
			@Override
			public void prepare(MimeMessage mimeMessage) throws Exception {
			
				/*
				 MimeMessage : 이 클래스는 MIME 스타일 이메일 메세지를 나타냅니다.
				 MIME (영어 : Multipurpose Internet Mail Extensions)는
				 전자 우편을 위한 인터넷 표준 포맷입니다.				 
				 */
				
				/*
				  MimeMessageHelper를 이용하면 첨부 파일이나 특수 문자 인코딩으로 작업할 때 전달된 MimeMessage를 채우는  데
				  편리합니다.
				 */
				//두번째 인자 ture는 멀티 파트 메세지를 사용하겠다는 의미 입니다.
				MimeMessageHelper helper = new MimeMessageHelper(mimeMessage,true,"UTF-8"); 
				helper.setFrom(vo.getFrom());
				helper.setTo(vo.getTo());
				helper.setSubject(vo.getSubject());
				
				//1. 문자로만 전송하는 경우
				//두 번째 인자는 html을 사용하겠다는 뜻입니다.
				//helper.setText(vo.getContent(),true);
				
				//2. 이미지를 내장해서 보내는 경우
                //cid(content id)
				String content ="<img src='cid:Home'>" + vo.getContent();
				helper.setText(content,true);
				
				FileSystemResource file = new FileSystemResource(new File(sandfile));
				//addInline메서드의 첫번째 메서드는 cid(content id)를 지정합니다.
				helper.addInline("Home",file);
				
				//3.파일을 첨부해서 보내는 경우
				//첫번째 인자 : 첨부될 파일의 이름입니다.
				//두번째 인자 : 첨부파일
				helper.addAttachment("코알라.jpg",file);
				
			} //prepare end
			
		}; // new MimeMessagePreparator() end
		
		  mailSender.send(mp); //메일 전송합니다.
		  logger.info("메일 전송했습니다.");
	  
	}// sendMail(MailVO vo) end


}// class SendMail end
