package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;

@Service
public class QuizService {

	@Autowired
	QuizDao dao;

	@Autowired
	QuestionDao questionDao;

	public Quiz createQuiz(String category, int numQ, String title) {

		List<Question> questions = questionDao.findRandomQuestionsByCategory(numQ, category);

		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestions(questions);
		dao.save(quiz);
		return quiz;

	}

	public List<QuestionWrapper> getQuizQuestions(Integer id) {
		Optional<Quiz> quiz = dao.findById(id);
		List<Question> questionsfromDB = quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUsers = new ArrayList();
		for (Question q : questionsfromDB) {
			QuestionWrapper qw = new QuestionWrapper();
			qw.setId(q.getId());
			qw.setQuestion_title(q.getQuestion_title());
			qw.setOption1(q.getOption1());
			qw.setOption2(q.getOption2());
			qw.setOption3(q.getOption3());
			qw.setOption4(q.getOption4());
			questionsForUsers.add(qw);
		}

		return questionsForUsers;
	}

	public int calculateResult(Integer id, List<Response> responses) {
		Optional<Quiz> quiz = dao.findById(id);
		List<Question> questions = quiz.get().getQuestions();
		int rightAnsCount = 0;
		int i = 0;
		for (Response response : responses) {
			if (response.getResponse().equals(questions.get(i).getRight_answer())) {
				rightAnsCount++;
			}
			i++;
		}

		return rightAnsCount;
	}

}
