package com.hyong.util;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMessage.RecipientType;

@Service
public class MailService {
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private RedisUtil redisUtil;
	
	private MimeMessage createMessage(String code, String email) throws Exception {
		MimeMessage message = javaMailSender.createMimeMessage();

        message.addRecipients(RecipientType.TO, email);
        message.setSubject("인증 번호입니다.");
        message.setText("이메일 인증코드: "+code);

        message.setFrom("hyong0701@gmail.com"); //보내는사람.

        return  message;
	}
	
	public void sendMail(String code, String email) throws Exception{
        try{
            MimeMessage mimeMessage = createMessage(code, email);
            javaMailSender.send(mimeMessage);
        }catch (MailException mailException){
            mailException.printStackTrace();
            throw new IllegalAccessException();
        }
    }
	
	public String sendCertificationMail(String email)  throws Exception {

      try{
          String code = UUID.randomUUID().toString().substring(0, 6); //랜덤 인증번호 uuid를 이용!
          sendMail(code,email);

//          redisUtil.setDataExpire(code,email,60*5L); // {key,value} 5분동안 저장.

          return  code;
      }catch (Exception exception){
          exception.printStackTrace();
          throw new Exception();
      }
  }

}
