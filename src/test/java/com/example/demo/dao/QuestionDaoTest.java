package com.example.demo.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.demo.model.Question;

@DataJpaTest
class QuestionDaoTest {

	@Autowired
	private QuestionDao questionDao;

	@Test
	void testFindByCategory() {
		// Arrange
		Question question = new Question();
		question.setCategory("java");
		question.setQuestion_title("What is Java?");
		question.setOption1("Programming Language");
		question.setOption2("Coffee");
		question.setOption3("Island");
		question.setOption4("All of the above");
		question.setRight_answer("Programming Language");
		question.setDifficulty_level("easy");

		questionDao.save(question);
		List<Question> javaQuestions = questionDao.findByCategory("java");

		assertNotNull(javaQuestions);
		assertEquals(1, javaQuestions.size());
		assertEquals("What is Java?", javaQuestions.get(0).getQuestion_title());
	}
	
    @Test
    void testFindRandomQuestionsByCategory() {
        // Arrange: Insert multiple questions into the database
        Question question1 = new Question();
        question1.setCategory("java");
        question1.setQuestion_title("What is Java?");
        question1.setOption1("Programming Language");
        question1.setOption2("Coffee");
        question1.setOption3("Island");
        question1.setOption4("All of the above");
        question1.setRight_answer("Programming Language");
        question1.setDifficulty_level("easy");

        Question question2 = new Question();
        question2.setCategory("java");
        question2.setQuestion_title("What is JVM?");
        question2.setOption1("Java Virtual Machine");
        question2.setOption2("Coffee Maker");
        question2.setOption3("Operating System");
        question2.setOption4("All of the above");
        question2.setRight_answer("Java Virtual Machine");
        question2.setDifficulty_level("medium");

        questionDao.save(question1);
        questionDao.save(question2);

        // Act: Fetch a random question by category
        List<Question> randomQuestions = questionDao.findRandomQuestionsByCategory(1, "java");

        // Assert: Validate the results
        assertNotNull(randomQuestions);
        assertEquals(1, randomQuestions.size());
        assertEquals("java", randomQuestions.get(0).getCategory());
    }

}
