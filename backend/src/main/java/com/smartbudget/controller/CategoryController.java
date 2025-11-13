package com.smartbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartbudget.entity.Category;
import com.smartbudget.repository.CategoryRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
@CrossOrigin(origins = "http://localhost:5173") // frontend URL
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // Get all categories
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    // Get category by id
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        return category.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new category
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryRepository.save(category);
    }

    // Update existing category
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(existingCategory -> {
                    existingCategory.setName(updatedCategory.getName());
                    existingCategory.setDescription(updatedCategory.getDescription());
                    return ResponseEntity.ok(categoryRepository.save(existingCategory));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete category
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}