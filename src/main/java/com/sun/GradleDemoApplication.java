package com.sun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class GradleDemoApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(GradleDemoApplication.class, args);
		//HashMap<String, String> mapsunsun = new HashMap<>();
		/*for(;;){
			mapsunsun.put("keysunsun"+ new Random(100),"value"+Integer.MAX_VALUE);
			System.out.println(mapsunsun.size());
		}*/


	}
}
