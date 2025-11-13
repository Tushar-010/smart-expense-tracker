package com.smartbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.smartbudget.entity.Budget;
import com.smartbudget.repository.BudgetRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
@CrossOrigin(origins = "http://localhost:5173")
public class BudgetController {

    @Autowired
    private BudgetRepository budgetRepository;

    // Get all budgets
    @GetMapping
    public List<Budget> getAllBudgets() {
        return budgetRepository.findAll();
    }

    // Get budget by ID
    @GetMapping("/{id}")
    public ResponseEntity<Budget> getBudgetById(@PathVariable Long id) {
        Optional<Budget> budget = budgetRepository.findById(id);
        return budget.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create new budget
    @PostMapping
    public Budget createBudget(@RequestBody Budget budget) {
        return budgetRepository.save(budget);
    }

    // Update budget
    @PutMapping("/{id}")
    public ResponseEntity<Budget> updateBudget(@PathVariable Long id, @RequestBody Budget updatedBudget) {
        return budgetRepository.findById(id)
                .map(existingBudget -> {
                    existingBudget.setMonth(updatedBudget.getMonth());
                    existingBudget.setAmount(updatedBudget.getAmount());
                    existingBudget.setCategory(updatedBudget.getCategory());
                    return ResponseEntity.ok(budgetRepository.save(existingBudget));
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Delete budget
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBudget(@PathVariable Long id) {
        if (budgetRepository.existsById(id)) {
            budgetRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
