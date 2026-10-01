package com.Exam.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.Exam.Service.CategoryService;
import com.Exam.entity.exam.Category;
import com.Exam.helper.UserFoundException;
import com.Exam.helper.UserNotFoundException;

@RestController
@RequestMapping("/category")
@CrossOrigin("*")
public class CategoryController {

	@Autowired
	private CategoryService categoryService;

	// add category
	@PostMapping("/")
	public ResponseEntity<Category> addCategory(@RequestBody Category category) {
		Category category1 = this.categoryService.addCategory(category);
		return ResponseEntity.ok(category1);
	}

	// get category
	@GetMapping("/{categoryId}")
	public ResponseEntity getCategory(@PathVariable("categoryId") Long categoryId) {
		try {
			Category categoryy = this.categoryService.getCategory(categoryId);
			 return ResponseEntity.ok(categoryy);
		} catch (UserNotFoundException e) {
			return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}

	// get all categories
	@GetMapping("/")
	public ResponseEntity<?> getCategories() {
		return ResponseEntity.ok(this.categoryService.getCategories());
	}

	// update category
	@PutMapping("/")
	public ResponseEntity<?> updateCategory(@RequestBody Category category) {
		
		try {
			Category categoryy =categoryService.updateCategory(category);
			 return ResponseEntity.ok(categoryy);
		} catch (UserNotFoundException e) {
		return	ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
		
	}

	// delete category
	 
	
	


}
