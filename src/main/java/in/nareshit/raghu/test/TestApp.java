package in.nareshit.raghu.test;

import java.net.MalformedURLException;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import in.nareshit.raghu.config.AppConfig;
import in.nareshit.raghu.util.MailUtil;

public class TestApp {

	public static void main(String[] args) {
		Resource file1 = new FileSystemResource("E:\\Images\\SB9AM09082021_1.png");
		Resource file2 = null;
		Resource file3 = null;
		try {
			file2 = new UrlResource("https://s3-ap-southeast-1.amazonaws.com/tv-prod/member/photo/745494-large.jpg");
			file3 = new UrlResource("https://upload.wikimedia.org/wikipedia/en/thumb/4/41/Flag_of_India.svg/1200px-Flag_of_India.svg.png");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		
		ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

		MailUtil util = ac.getBean(MailUtil.class);

		boolean sent = util.send(
				//"javaraghu2021@gmail.com", 
				"g.yeshvicky@gmail.com", 

				new String[] { //cc
						"ygowreddigari@gmail.com",
						"roys4629@gmail.com",
						"shubhamdeshmukh410@gmail.com"

				}, new String[] { //bcc
						"pawankrishna888@gmail.com",
						"sudhakar.bathini01@gmail.com",
				}, 

				"HELLO DEAR STUDENTS!!", 
				"<html><body><h1>WELCOME TO ALL FOR EMAIL PROGRAMMING</h1><b>Hello all</b> </body></html>", 
				new Resource[] {
						file1,file2,file3
				}
				);

		if(sent)
			System.out.println("CHECK EMAIL INBOX");
		else
			System.out.println("CHECK ERRORS");
	}
}
