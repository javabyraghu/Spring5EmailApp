package in.nareshit.raghu.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender sender;

	public boolean send(
			String to,
			String[] cc,
			String[] bcc,
			String subject,
			String text,
			Resource file
			) 
	{
		boolean flag = false;

		try {
			//1. create one message Message
			MimeMessage message = sender.createMimeMessage();

			//2. use Helper class and fill details
			MimeMessageHelper helper = new MimeMessageHelper(message, file!=null);
			helper.setTo(to);
			
			if(cc!=null && cc.length>0)
				helper.setCc(cc);
			
			if(bcc!=null && bcc.length>0)
				helper.setBcc(bcc);
			
			helper.setSubject(subject);
			helper.setText(text);

			if(file!=null) {
				//filename , file object
				helper.addAttachment(file.getFilename(), file);
			}
			
			//3. send email
			sender.send(message);
			flag = true;

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}


		return flag;
	}
}
