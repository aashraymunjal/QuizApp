package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.model.Question;
import com.example.demo.model.Quiz;

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

}
