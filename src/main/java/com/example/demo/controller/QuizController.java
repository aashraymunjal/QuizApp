package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Quiz;
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

}
