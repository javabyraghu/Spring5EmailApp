package in.nareshit.raghu.test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import in.nareshit.raghu.config.AppConfig;
import in.nareshit.raghu.util.MailUtil;

public class TestApp {

	public static void main(String[] args) {
		Resource file = new FileSystemResource("E:\\Images\\SB9AM09082021_1.png");

		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MailUtil util = ac.getBean(MailUtil.class);

		boolean sent = util.send(
				"javaraghu2021@gmail.com", 

				new String[] { //cc
						"ygowreddigari@gmail.com",
						"roys4629@gmail.com",
						"shubhamdeshmukh410@gmail.com"

				}, new String[] { //bcc
						"pawankrishna888@gmail.com",
						"sudhakar.bathini01@gmail.com",
				}, 

				"HELLO DEAR STUDENTS!!", 
				"WELCOME TO ALL FOR EMAIL PROGRAMMING", 
				file
				);

		if(sent)
			System.out.println("CHECK EMAIL INBOX");
		else
			System.out.println("CHECK ERRORS");
	}
}
