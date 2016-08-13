package com.heinrichs.email.services;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.heinrichs.email.domain.User;
import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
@Service
@SuppressWarnings("deprecation")
public class EmailService {
	@Autowired
	private JavaMailSender mailsender;
	
	@Autowired
	private VelocityEngine ve;
	
	
	@Value("${email.example}")
	private String email;
	
	public void sendConfirmationEmail(User user) {
        MimeMessage mail = mailsender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo("jordan.heinrichs86248@gmail.com");
            helper.setFrom("test@gmail.com");
            helper.setSubject("Lorem ipsum");
			Map<String,Object> model = new HashMap<String,Object>();
			model.put("user", user);
			String html = VelocityEngineUtils.mergeTemplateIntoString(ve,
					"test.vm", "UTF-8", model);
			createPdfFromHtml(html);
            helper.setText(VelocityEngineUtils.mergeTemplateIntoString(ve,
					"test.vm", "UTF-8", model), true);
        } catch (MessagingException e) {
            e.printStackTrace();
        } finally {}
        //mailsender.send(mail);

	}
	
	public void createPdfFromHtml(String html){
		try {
		    String k = "<html><body> This is my Project </body></html>";
		    OutputStream file = new FileOutputStream(new File("/Users/jordanheinrichs/Desktop/test.pdf"));
		    Document document = new Document();
		    PdfWriter writer = PdfWriter.getInstance(document, file);
		    document.open();
		    InputStream is = new ByteArrayInputStream(html.getBytes());
		    XMLWorkerHelper.getInstance().parseXHtml(writer, document, is);
		    document.close();
		    file.close();
		} catch (Exception e) {
		    e.printStackTrace();
		}
	}
}
