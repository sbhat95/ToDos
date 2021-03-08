package com.in28minutes.rest.basic.auth;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class BasicAuthController {

	@GetMapping("/basicauth")
	public AuthenticationBean helloWorldBean() {
		//return new HelloWorldBean("Hello World Bean");
		return new AuthenticationBean("You are logged in");
	}
}
