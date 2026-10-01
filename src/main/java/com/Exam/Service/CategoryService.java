package com.Exam.Service;


import java.util.Set;

import com.Exam.entity.exam.Category;
import com.Exam.helper.UserNotFoundException;

public interface CategoryService {
    public Category addCategory(Category category);

    public Category updateCategory(Category category) throws UserNotFoundException;

    public Set<Category> getCategories();

    public Category getCategory(Long categoryId) throws UserNotFoundException;

    public void deleteCategory(Long categoryId) throws UserNotFoundException;
}
