package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "question")
public class Question {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	/*
	 * Auto - sab apne aap hoga postgres use sequence so in our case it is sequence
	 * when we write auto.
	 * 
	 */
	private Integer id;
	@Column(name = "question_title")
	private String question_title;
	@Column(name = "option1")
	private String option1;
	@Column(name = "option2")
	private String option2;
	@Column(name = "option3")
	private String option3;
	@Column(name = "option4")
	private String option4;
	@Column(name = "right_answer")
	private String right_answer;
	@Column(name = "difficulty_level")
	private String difficulty_level;
	@Column(name = "category")
	private String category;

	public Integer getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion_title() {
		return question_title;
	}

	public void setQuestion_title(String question_title) {
		this.question_title = question_title;
	}

	public String getOption1() {
		return option1;
	}

	public void setOption1(String option1) {
		this.option1 = option1;
	}

	public String getOption2() {
		return option2;
	}

	public void setOption2(String option2) {
		this.option2 = option2;
	}

	public String getOption3() {
		return option3;
	}

	public void setOption3(String option3) {
		this.option3 = option3;
	}

	public String getOption4() {
		return option4;
	}

	public void setOption4(String option4) {
		this.option4 = option4;
	}

	public String getRight_answer() {
		return right_answer;
	}

	public void setRight_answer(String right_answer) {
		this.right_answer = right_answer;
	}

	public String getDifficulty_level() {
		return difficulty_level;
	}

	public void setDifficulty_level(String difficulty_level) {
		this.difficulty_level = difficulty_level;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "Question [id=" + id + ", question_title=" + question_title + ", option1=" + option1 + ", option2="
				+ option2 + ", option3=" + option3 + ", option4=" + option4 + ", right_answer=" + right_answer
				+ ", difficulty_level=" + difficulty_level + ", category=" + category + "]";
	}

}
