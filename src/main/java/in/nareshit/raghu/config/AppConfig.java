package in.nareshit.raghu.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
@ComponentScan("in.nareshit.raghu")
@PropertySource("classpath:mail.properties")
public class AppConfig {

	@Autowired
	private Environment env;
	
	@Bean
	public JavaMailSender mailSender() {
		JavaMailSenderImpl jm = new JavaMailSenderImpl();
		
		jm.setHost(env.getProperty("my.mail.host"));
		jm.setPort(env.getProperty("my.mail.port",Integer.class));
		jm.setUsername(env.getProperty("my.mail.un"));
		jm.setPassword(env.getProperty("my.mail.pwd"));
		jm.setJavaMailProperties(props());
		return jm;
	}

	public Properties props() {
		Properties p = new Properties();
		//TLS- Trsport Layer Security
		p.put("mail.smtp.starttls.enable", "true");
		return p;
	}
}
