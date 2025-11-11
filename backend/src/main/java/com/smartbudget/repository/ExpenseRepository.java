package com.smartbudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudget.entity.Expense;
import com.smartbudget.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
    List<Expense> findByUserAndDateBetween(User user, LocalDate start, LocalDate end);
}