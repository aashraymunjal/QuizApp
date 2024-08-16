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
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import com.example.demo.service.QuizService;

@ExtendWith(MockitoExtension.class)
class QuizControllerTest {

	@InjectMocks
	QuizController controller;

	@Mock
	QuizService service;

	@Test
	@DisplayName("Test createQuiz success")
	void createQuizSuccess() {

		String cat = "c1";
		int num = 10;
		String title = "t1";
		List<Question> listq = new ArrayList<>();
		Question qq = new Question();
		qq.setCategory(cat);
		listq.add(qq);
		Quiz q1 = new Quiz();
		q1.setId(1);
		q1.setTitle(title);
		q1.setQuestions(listq);

		Mockito.when(service.createQuiz(cat, num, title)).thenReturn(q1);
		ResponseEntity<Quiz> res = controller.createQuiz(cat, num, title);
		Assertions.assertEquals(res.getBody().getQuestions().get(0).getCategory(), cat);
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@Test
	@DisplayName("Test createQuiz fails")
	void createQuizFail() {

		String cat = "c1";
		int num = 10;
		String title = "t1";
		List<Question> listq = new ArrayList<>();
		Quiz q1 = new Quiz();
		q1.setId(1);
		q1.setTitle(title);
		q1.setQuestions(listq);

		Mockito.when(service.createQuiz(cat, num, title)).thenReturn(q1);
		ResponseEntity<Quiz> res = controller.createQuiz(cat, num, title);
		Assertions.assertNull(res.getBody());
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());

	}

	@Test
	@DisplayName("Test getQuizQuestionsById success")
	void getQuizQuestionsById() {
		List<QuestionWrapper> listWrapped = new ArrayList<>();
		QuestionWrapper q1 = new QuestionWrapper();
		q1.setId(10);
		q1.setOption1("o1");
		q1.setOption2("o2");
		q1.setOption3("o3");
		q1.setOption4("o4");
		q1.setQuestion_title("t1");
		listWrapped.add(q1);

		QuestionWrapper q2 = new QuestionWrapper();
		q2.setId(20);
		q2.setOption1("o1");
		q2.setOption2("o2");
		q2.setOption3("o3");
		q2.setOption4("o4");
		q2.setQuestion_title("t2");
		listWrapped.add(q2);

		int id = 1;
		Mockito.when(service.getQuizQuestions(id)).thenReturn(listWrapped);
		ResponseEntity<List<QuestionWrapper>> res = controller.getQuizQuestions(id);
		Assertions.assertNotNull(res.getBody());
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

	}

	@Test
	@DisplayName("Test getQuizQuestionsById fails")
	void getQuizQuestionsByIdFails() {
		List<QuestionWrapper> listWrapped = new ArrayList<>();
		int id = 1;
		Mockito.when(service.getQuizQuestions(id)).thenReturn(listWrapped);
		ResponseEntity<List<QuestionWrapper>> res = controller.getQuizQuestions(id);
		Assertions.assertNull(res.getBody());
		Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, res.getStatusCode());

	}

	@Test
	@DisplayName("Test Submit Quiz success")
	void testSubmitQuiz() {
		List<Response> list = new ArrayList<>();
		Response r1 = new Response();
		r1.setId(1);
		r1.setResponse("ABC");

		Response r2 = new Response();
		r2.setId(2);
		r2.setResponse("ABCD");

		list.add(r1);
		list.add(r2);
		int id = 1;
		int score = 2;
		Mockito.when(service.calculateResult(id, list)).thenReturn(score);
		ResponseEntity<Integer> res = controller.submitQuiz(id, list);
		Assertions.assertNotNull(res);
		Assertions.assertEquals(HttpStatus.OK, res.getStatusCode());

	}

}
