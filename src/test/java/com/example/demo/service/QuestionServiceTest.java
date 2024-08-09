package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.example.demo.dao.QuestionDao;
import com.example.demo.model.Question;

@ExtendWith(MockitoExtension.class)
class QuestionServiceTest {

	@InjectMocks
	QuestionService service;

	@Mock
	QuestionDao dao;

	@DisplayName("Testing getAllQuestions success")
	@Test
	public void testGetAllQuestions() {

		Question q1 = new Question();
		q1.setCategory("Java");
		List<Question> prepared = new ArrayList<>();
		prepared.add(q1);
		Mockito.when(dao.findAll()).thenReturn(prepared);
		List<Question> res = service.getAllQuestions();
		Assertions.assertNotNull(res);

	}

	@DisplayName("Testing getAllQuestions success")
	@Test
	public void testGetQuestionsBtCat() {

		Question q1 = new Question();
		q1.setCategory("Java");
		List<Question> prepared = new ArrayList<>();
		prepared.add(q1);
		String cat = "Java";
		Mockito.when(dao.findByCategory(cat)).thenReturn(prepared);
		List<Question> res = service.getQuestionByCat(cat);
		Assertions.assertNotNull(res);
		Assertions.assertEquals(res.get(0).getCategory(), cat);

	}

	@DisplayName("Testing getAllQuestions success")
	@Test
	public void testaddquestions() {
		Question q1 = new Question();
		q1.setCategory("Java");
		Mockito.when(dao.save(q1)).thenReturn(q1);
		Question res = service.adQuestion(q1);
		Assertions.assertNotNull(res);

	}

	@DisplayName("Testing DeletebyID success")
	@Test
	public void DeletByID() {
		Question q1 = new Question();
		q1.setCategory("java");
		q1.setId(10);
		int id = 10;
		Mockito.when(dao.findById(id)).thenReturn(Optional.of(q1));
		String res = service.deletebyid(id);
		Assertions.assertEquals("Deleted", res);

	}

	@DisplayName("Testing DeletebyID Not found")
	@Test
	public void DeletByIDFails() {
		Question q1 = new Question();
		q1.setCategory("java");
		q1.setId(10);
		int id = 10;
		Mockito.when(dao.findById(id)).thenReturn(Optional.empty());
		String res = service.deletebyid(id);
		Assertions.assertEquals("Failed to find id", res);

	}

	@DisplayName("Testing Updation success")
	@Test
	public void TestUpdateSuccess() {
		Question q2 = new Question();
		q2.setId(10);
		q2.setCategory("C++");
		int id = 10;
		Mockito.when(dao.findById(Mockito.any())).thenReturn(Optional.of(q2));
		Question res = service.upadteQuestion(id, q2);
		Assertions.assertNotNull(res);

	}

	@DisplayName("Testing Updation Fail")
	@Test
	public void TestUpdateFail() {
		Question q2 = new Question();
		q2.setId(10);
		q2.setCategory("C++");
		int id = 10;
		Question res = service.upadteQuestion(id, q2);
		Assertions.assertNull(res);

	}

}
