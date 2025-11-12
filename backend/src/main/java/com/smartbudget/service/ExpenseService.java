package com.smartbudget.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.smartbudget.entity.Expense;
import com.smartbudget.entity.User;

public interface ExpenseService {

	public Expense addExpense(Expense expense);
	public List<Expense> getExpensesByUser(User user);
	public List<Expense> getExpensesByUserAndDateRange(User user, LocalDate start, LocalDate end);
	public Optional<Expense> getExpenseById(Long id);
	public void deleteExpense(Long id);
}
