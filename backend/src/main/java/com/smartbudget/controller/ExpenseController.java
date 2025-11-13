package com.smartbudget.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.smartbudget.entity.Expense;
import com.smartbudget.entity.User;
import com.smartbudget.service.ExpenseService;
import com.smartbudget.service.UserService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@CrossOrigin(origins = "*")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @Autowired
    private UserService userService;

    @PostMapping
    public Expense addExpense(@RequestBody Expense expense) {
        return expenseService.addExpense(expense);
    }

    @GetMapping("/user/{userId}")
    public List<Expense> getExpensesByUser(@PathVariable Long userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return expenseService.getExpensesByUser(user);
    }

    @GetMapping("/user/{userId}/range")
    public List<Expense> getExpensesByDateRange(
            @PathVariable Long userId,
            @RequestParam String start,
            @RequestParam String end
    ) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        LocalDate startDate = LocalDate.parse(start);
        LocalDate endDate = LocalDate.parse(end);
        return expenseService.getExpensesByUserAndDateRange(user, startDate, endDate);
    }

    @DeleteMapping("/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully!";
    }
}
