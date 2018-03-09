package com.itfollowme.quiz.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.itfollowme.quiz.pojo.Quiz;

public interface QuizDao extends JpaRepository<Quiz, Long>{

}
