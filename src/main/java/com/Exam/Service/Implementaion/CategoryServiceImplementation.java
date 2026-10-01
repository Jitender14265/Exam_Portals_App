package com.Exam.Service.Implementaion;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Exam.Repository.CategoryRepository;
import com.Exam.Service.CategoryService;
import com.Exam.entity.exam.Category;
import com.Exam.helper.UserNotFoundException;

import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class CategoryServiceImplementation implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;
	
	private Category categories;

	@Override
	public Category addCategory(Category category) {
		return this.categoryRepository.save(category);
	}

	@Override
	public Category updateCategory(Category category) throws UserNotFoundException {
		categories=this.categoryRepository.save(category);
		if(categories==null) {
			throw new UserNotFoundException("This Category Not Found ");
		}
		return categories;
	}

	@Override
	public Set<Category> getCategories() {
		return new LinkedHashSet<>(this.categoryRepository.findAll());
	}

	@Override
	public Category getCategory(Long categoryId) throws UserNotFoundException {

//		Category category = this.categoryRepository.findById(categoryId).get();
		Optional<Category> category=this.categoryRepository.findById(categoryId);
		if (!category.isPresent()) {
			throw new UserNotFoundException("This Category Cannot Exist " + categoryId);

		}

		return category.get();
	}

	@Override
	public void deleteCategory(Long categoryId) throws UserNotFoundException {
		if (categoryRepository.existsById(categoryId)) {
			Category category = new Category();
			category.setCid(categoryId);
			this.categoryRepository.delete(category);
		} else {
			throw new UserNotFoundException("This Category Cannot Exist " + categoryId + " It Cannot be Deleted");
		}

	}

}
