package com.mk.SpringBootProject_1;

import org.springframework.web.bind.annotation.*;


@RestController
public class ApiCheck {
	@GetMapping("api")
	public static String hey() {
		return "hello springBoot user";
	}
	

}
