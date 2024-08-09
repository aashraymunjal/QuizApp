package com.example.demo.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.meanbean.test.BeanTester;

@ExtendWith(MockitoExtension.class)
class QuestionTest {

	@Test
	void testMyBean() {
		BeanTester beanTester = new BeanTester();
		beanTester.testBean(Question.class);
	}

}
