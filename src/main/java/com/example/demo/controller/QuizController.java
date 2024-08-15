package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Question;
import com.example.demo.model.QuestionWrapper;
import com.example.demo.model.Quiz;
import com.example.demo.model.Response;
import com.example.demo.service.QuizService;

@RequestMapping("quiz")
@RestController
public class QuizController {

	@Autowired
	QuizService service;

	@PostMapping("create")
	public ResponseEntity<Quiz> createQuiz(@RequestParam String category, @RequestParam int numQ,
			@RequestParam String title) {

		Quiz q1 = service.createQuiz(category, numQ, title);

		if (q1.getQuestions().isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

		} else {
			return new ResponseEntity<>(q1, HttpStatus.OK);
		}

	}

	@GetMapping("get/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuizQuestions(@PathVariable Integer id) {

		List<QuestionWrapper> list = service.getQuizQuestions(id);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}

	}

	@PostMapping("submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<Response> responses) {
		int ans = service.calculateResult(id, responses);
		return new ResponseEntity<>(ans, HttpStatus.OK);
	}

}
