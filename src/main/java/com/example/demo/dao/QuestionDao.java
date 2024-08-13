package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> {

	List<Question> findByCategory(String category);

	@Query(value = "SELECT * FROM QUESTION WHERE CATEGORY = :category AND NAME = :name", nativeQuery = true)
	public List<Question> findbycategoryandname(String category, String name);

}
