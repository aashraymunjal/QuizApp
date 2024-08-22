package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.model.Question;
import com.example.demo.service.QuestionService;

@RestController
public class QuestionController {

	@Autowired
	QuestionService service;

	@GetMapping("/allquestions")
	public ResponseEntity<List<Question>> getAllQuestions() {
		List<Question> list = service.getAllQuestions();
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@GetMapping("/category/{category}")
	public ResponseEntity<List<Question>> getQuestionByCat(@PathVariable("category") String category) {
		List<Question> list = service.getQuestionByCat(category);
		if (list.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(null);
		} else {
			return new ResponseEntity<>(list, HttpStatus.OK);
		}
	}

	@PostMapping("/add")
	public ResponseEntity<Question> addQuestion(@RequestBody Question question) {

		if (question != null) {
			Question saved = service.adQuestion(question);
			return new ResponseEntity<>(saved, HttpStatus.CREATED);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteById(@PathVariable("id") int id) {
		String res = service.deletebyid(id);

		if (res.equals("Deleted")) {
			return new ResponseEntity<>("Deletion Success", HttpStatus.OK);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("ID cannot be found");
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Question> updateRecord(@PathVariable("id") int id, @RequestBody Question question) {

		Question q1 = service.upadteQuestion(id, question);
		if (q1 == null) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		} else {
			return new ResponseEntity<>(q1, HttpStatus.CREATED);
		}
		//to be added more APIs here

	}

}
