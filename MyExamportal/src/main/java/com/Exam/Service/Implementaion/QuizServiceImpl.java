package com.Exam.Service.Implementaion;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Exam.Repository.CategoryRepository;
import com.Exam.Repository.QuizRepository;
import com.Exam.Service.QuizService;
import com.Exam.entity.exam.Category;
import com.Exam.entity.exam.Quiz;
import com.Exam.helper.UserNotFoundException;

import jakarta.transaction.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class QuizServiceImpl implements QuizService {
    @Autowired
    private QuizRepository quizRepository;
    
    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Quiz addQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Quiz updateQuiz(Quiz quiz) {
        return this.quizRepository.save(quiz);
    }

    @Override
    public Set<Quiz> getQuizzes() {
        return new HashSet<>(this.quizRepository.findAll());
    }

    @Override
    public Quiz getQuiz(Long quizId) throws UserNotFoundException {
    	Optional<Quiz> quiz=this.quizRepository.findById(quizId);
		if (!quiz.isPresent()) {
			throw new UserNotFoundException("This Quiz is Cannot Exist " + quizId);

		}

        return quiz.get();
    }

    @Override
    public void deleteQuiz(Long quizId) throws UserNotFoundException {
        
        if (quizRepository.existsById(quizId)) {
        	this.quizRepository.deleteById(quizId);
		} else {
			throw new UserNotFoundException("This Quiz Cannot Exist " + quizId + " It Cannot be Deleted");
		}
    }

    @Override
    public List<Quiz> getQuizzesOfCategory(Category category) throws UserNotFoundException {
       
        if (categoryRepository.existsById(category.getCid())) {
        	 return this.quizRepository.findBycategory(category);
             
		} else {
			throw new UserNotFoundException("This Category Cannot Exist ");
		}
        
    }


    //get active quizzes

    @Override
    public List<Quiz> getActiveQuizzes() {
        return this.quizRepository.findByActive(true);
    }

    @Override
    public List<Quiz> getActiveQuizzesOfCategory(Category c) {
        return this.quizRepository.findByCategoryAndActive(c, true);
    }

}
