package com.Exam.Service;

import java.util.List;
import java.util.Set;

import com.Exam.entity.exam.Category;
import com.Exam.entity.exam.Quiz;
import com.Exam.helper.UserNotFoundException;

public interface QuizService {

    public Quiz addQuiz(Quiz quiz);

    public Quiz updateQuiz(Quiz quiz);

    public Set<Quiz> getQuizzes();

    public Quiz getQuiz(Long quizId) throws UserNotFoundException;

    public void deleteQuiz(Long quizId) throws UserNotFoundException;


    public List<Quiz> getQuizzesOfCategory(Category category) throws UserNotFoundException;

    public List<Quiz> getActiveQuizzes();

    public List<Quiz> getActiveQuizzesOfCategory(Category c);
}

