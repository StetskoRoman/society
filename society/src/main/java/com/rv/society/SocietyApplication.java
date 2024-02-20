package com.rv.society;

import com.rv.society.domain.Role;
import com.rv.society.domain.User;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SocietyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocietyApplication.class, args);

//		User user = new User();
//		System.out.println(user.getRoles());
	}

}
