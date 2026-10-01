package com.Exam.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Exam.entity.exam.Question;
import com.Exam.entity.exam.Quiz;

import java.util.Set;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Set<Question> findByQuiz(Quiz quiz);
}

