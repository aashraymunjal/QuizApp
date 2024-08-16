package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;

@ExtendWith(MockitoExtension.class)
class QuizServiceTest {

	@InjectMocks
	QuizService service;

	@Mock
	QuizDao dao;

	@Mock
	QuestionDao qdao;

	@Test
	@DisplayName("CreateQuiz test success")
	void createQuiz() {

		String cat = "java";
		int num = 2;
		String title = "title";

		List<Question> list = new ArrayList<>();
		Question q1 = new Question();

		q1.setId(10001);
		q1.setCategory(cat);
		q1.setDifficulty_level("medium");
		q1.setQuestion_title("qt1");
		q1.setOption1("op1");
		q1.setOption2("op2");
		q1.setOption3("op3");
		q1.setOption4("op4");
		q1.setRight_answer("op1");

		list.add(q1);

		Question q2 = new Question();

		q2.setId(1000);
		q2.setCategory(cat);
		q2.setDifficulty_level("medium");
		q2.setQuestion_title("qt2");
		q2.setOption1("op1");
		q2.setOption2("op2");
		q2.setOption3("op3");
		q2.setOption4("op4");
		q2.setRight_answer("op2");

		list.add(q2);

		Quiz quiz = new Quiz();
		quiz.setQuestions(list);
		quiz.setTitle(title);

		Mockito.when(qdao.findRandomQuestionsByCategory(num, cat)).thenReturn(list);
		Mockito.when(dao.save(ArgumentMatchers.any(Quiz.class))).thenReturn(quiz);
		Quiz res = service.createQuiz(cat, num, title);
		Assertions.assertEquals(res.getTitle(), title);
		Assertions.assertEquals(res.getQuestions().get(0).getCategory(), list.get(0).getCategory());
		Assertions.assertNotNull(res);

	}

	@Test
	@DisplayName("Test get questions by id")
	void testGetQuestion() {
		List<Question> list = new ArrayList<>();
		Question q1 = new Question();

		q1.setId(10001);
		q1.setCategory("cat");
		q1.setDifficulty_level("medium");
		q1.setQuestion_title("qt1");
		q1.setOption1("op1");
		q1.setOption2("op2");
		q1.setOption3("op3");
		q1.setOption4("op4");
		q1.setRight_answer("op1");

		list.add(q1);

		Question q2 = new Question();

		q2.setId(1000);
		q2.setCategory("cat");
		q2.setDifficulty_level("medium");
		q2.setQuestion_title("qt2");
		q2.setOption1("op1");
		q2.setOption2("op2");
		q2.setOption3("op3");
		q2.setOption4("op4");
		q2.setRight_answer("op2");

		list.add(q2);

		int id = 1;
		Quiz quiz = new Quiz();
		quiz.setId(id);
		quiz.setTitle("first");
		quiz.setQuestions(list);

		List<QuestionWrapper> questionsForUsers = new ArrayList();
		for (Question q : list) {
			QuestionWrapper qw = new QuestionWrapper();
			qw.setId(q.getId());
			qw.setQuestion_title(q.getQuestion_title());
			qw.setOption1(q.getOption1());
			qw.setOption2(q.getOption2());
			qw.setOption3(q.getOption3());
			qw.setOption4(q.getOption4());
			questionsForUsers.add(qw);
		}

		Mockito.when(dao.findById(id)).thenReturn(Optional.of(quiz));
		List<QuestionWrapper> res = service.getQuizQuestions(id);
		Assertions.assertNotNull(res);
		Assertions.assertEquals(res.get(0).getQuestion_title(), list.get(0).getQuestion_title());
	}

	@Test
	@DisplayName("Test result calculation")
	void testResultCalc() {

		List<Question> list = new ArrayList<>();
		Question q1 = new Question();

		q1.setId(10001);
		q1.setCategory("cat");
		q1.setDifficulty_level("medium");
		q1.setQuestion_title("qt1");
		q1.setOption1("op1");
		q1.setOption2("op2");
		q1.setOption3("op3");
		q1.setOption4("op4");
		q1.setRight_answer("op1");

		list.add(q1);

		Question q2 = new Question();

		q2.setId(1000);
		q2.setCategory("cat");
		q2.setDifficulty_level("medium");
		q2.setQuestion_title("qt2");
		q2.setOption1("op1");
		q2.setOption2("op2");
		q2.setOption3("op3");
		q2.setOption4("op4");
		q2.setRight_answer("op2");

		list.add(q2);

		int id = 1;
		Quiz quiz = new Quiz();
		quiz.setId(id);
		quiz.setTitle("first");
		quiz.setQuestions(list);

		List<Response> responses = new ArrayList<>();

		Response res1 = new Response();
		res1.setId(10001);
		res1.setResponse("op1");
		Response res2 = new Response();
		res2.setId(10000);
		res2.setResponse("op2");

		responses.add(res1);
		responses.add(res2);
		int rightAnsCount = 0;
		int i = 0;
		for (Response response : responses) {
			if (response.getResponse().equals(list.get(i).getRight_answer())) {
				rightAnsCount++;
			}
			i++;
		}
		Mockito.when(dao.findById(id)).thenReturn(Optional.of(quiz));
		rightAnsCount = service.calculateResult(id, responses);
		Assertions.assertEquals(rightAnsCount, 2);
		Assertions.assertNotNull(rightAnsCount);

	}

}
