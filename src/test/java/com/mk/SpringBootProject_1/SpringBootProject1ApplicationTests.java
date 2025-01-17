package com.mk.SpringBootProject_1;

import com.mk.SpringBootProject_1.Service.userService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootProject1ApplicationTests {


	@Autowired
	private userService service;


	//Asset means Dawa(promise)
	//Check methods of spring application
//@Disabled used to disable the test method

	@ParameterizedTest
	@ValueSource(strings = {
			"ram",
			"mkm13",
			"thala"
	})
	public void testFindByUserName(String name) {
		assertNotNull(service.findByUsername(name));
	}


	//Example for Parameterized test if more than 1 value/testCases needs to be tested using Junit Test
	@ParameterizedTest
	@CsvSource({
		"5,7,12",
			"77,5,82",
			"0,0,0",
			"9,-4,5",
			"-7,-2,-9"
	})
	void test(int a,int b,int expected){
		assertEquals(expected,a+b);
	}
}
