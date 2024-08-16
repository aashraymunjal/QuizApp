package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.meanbean.test.BeanTester;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class QuizTest {

	@Test
	void testMyBean() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Quiz.class);
	}

}
