package com.mk.SpringBootProject_1;

import com.mk.SpringBootProject_1.Service.userService;
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

//	@BeforeAll
//	static void setUpBeforeClass() throws Exception {
//		This will get invoked before all test cases/methods written
//	}

//	@Test -> used to annotate for normal tests
//	@BeforeTestMethod @TestConstructor() @TestComponent @TestBean @JdbcTest

//	@BeforeEach
//	void setUp() {
//		This will get invoked before each Test Methods used to initialize something
//	}

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

//	@AfterTransaction -> will invoke after a Transactional method gets tested

//			@AfterEach
//	void afterEach() {
//		will get invoked after each method tested using Junit testing
//			}

//			@BeforeTestMethod @AfterTestMethod All these annotations are used as per their names
}
