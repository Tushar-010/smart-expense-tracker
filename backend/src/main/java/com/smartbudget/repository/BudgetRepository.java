package com.smartbudget.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.smartbudget.entity.Budget;
import com.smartbudget.entity.User;
import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByUserAndMonth(User user, String month);
}