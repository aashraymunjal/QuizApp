package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.exceptions.base.MockitoException;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;

@ExtendWith(MockitoExtension.class)
class QuestionControllerTest {

	@InjectMocks
	QuestionController controller;

	@Mock
	QuestionService service;

	@DisplayName("Test get all questions")
	@Test
	void testGetAllQuestionsNotNull() {
		Question q1 = new Question();
		q1.setCategory("Java");
		List<Question> list = new ArrayList<>();
		list.add(q1);

		Mockito.when(service.getAllQuestions()).thenReturn(list);
		ResponseEntity<List<Question>> res = controller.getAllQuestions();
		Assertions.assertNotNull(res);
		Assertions.assertEquals(res.getBody().get(0).getCategory(), q1.getCategory());
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());
	}

	@DisplayName("Test get all questions NULL")
	@Test
	void testGetAllQuestionsNull() {
		Question q1 = new Question();
		q1.setCategory("Java");
		List<Question> list = new ArrayList<>();

		Mockito.when(service.getAllQuestions()).thenReturn(list);
		ResponseEntity<List<Question>> res = controller.getAllQuestions();
		Assertions.assertNull(res.getBody());
		Assertions.assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

	}

	@DisplayName("Saving question success")
	@Test
	void savedQuestion() {
		Question q1 = new Question();
		q1.setCategory("java");
		Mockito.when(service.adQuestion(q1)).thenReturn(q1);
		ResponseEntity<Question> res = controller.addQuestion(q1);
		Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());
		Assertions.assertNotNull(res.getBody());

	}

	@DisplayName("Saving question Fails")
	@Test
	void savedQuestionFails() {
		Question q1 = null;
		ResponseEntity<Question> res = controller.addQuestion(q1);
		Assertions.assertNull(res.getBody());

	}

	@DisplayName("Test fetch list by category")
	@Test
	void testfetchbyCat() {
		Question q1 = new Question();
		q1.setCategory("java");
		Question q2 = new Question();
		q2.setCategory("java");

		List<Question> list = new ArrayList<>();
		list.add(q2);
		list.add(q1);
		String category = "java";
		Mockito.when(service.getQuestionByCat(category)).thenReturn(list);
		ResponseEntity<List<Question>> res = controller.getQuestionByCat(category);
		Assertions.assertEquals(res.getBody().get(0).getCategory(), category);
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@DisplayName("Test fetch list by category")
	@Test
	void testfetchbyCatEmpty() {
		Question q1 = new Question();
		q1.setCategory("java");
		Question q2 = new Question();
		q2.setCategory("java");

		List<Question> list = new ArrayList<>();

		String category = "java";
		Mockito.when(service.getQuestionByCat(category)).thenReturn(list);
		ResponseEntity<List<Question>> res = controller.getQuestionByCat(category);
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@DisplayName("Test Deletion by ID success")
	@Test
	void testDeleteSuccess() {
		Question q1 = new Question();
		q1.setId(10);
		int id = 10;
		Mockito.when(service.deletebyid(id)).thenReturn("Deleted");
		ResponseEntity<String> res = controller.deleteById(id);
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@DisplayName("Test Deletion by ID fail")
	@Test
	void testDeleteFail() {
		Question q1 = new Question();
		q1.setId(10);
		int id = 10;
		Mockito.when(service.deletebyid(id)).thenReturn("Not found");
		ResponseEntity<String> res = controller.deleteById(id);
		Assertions.assertEquals(HttpStatus.NOT_FOUND, res.getStatusCode());

	}

	@DisplayName("Test updation by ID and question")
	@Test
	void testUpdationByIdQuestion() {
		Question q1 = new Question();
		q1.setId(10);
		q1.setCategory("Java");

		Question q2 = new Question();
		q2.setId(10);
		q2.setCategory("C++");
		int id = 10;
		Mockito.when(service.upadteQuestion(id, q2)).thenReturn(q2);
		ResponseEntity<Question> res = controller.updateRecord(id, q2);
		Assertions.assertEquals(HttpStatus.CREATED, res.getStatusCode());

	}
	@DisplayName("Test updation by ID and question Fails" )
	@Test
	void testUpdationByIdQuestionFails() {
		Question q1 = new Question();
		q1.setId(10);
		q1.setCategory("Java");

		Question q2 = new Question();
		q2.setId(10);
		q2.setCategory("C++");
		int id = 10;
		Mockito.when(service.upadteQuestion(id, q2)).thenReturn(null);
		ResponseEntity<Question> res = controller.updateRecord(id, q2);
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());

	}

}
