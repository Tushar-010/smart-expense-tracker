package com.smartbudget.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "budgets")
public class Budget {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // For grouping or filtering budgets (e.g. "November 2025")
    private String month;

    // Total amount allocated
    private Double amount;

    // When the budget was created
    private LocalDateTime createdAt = LocalDateTime.now();

    // Relationships -----------------------------------------

    // Many budgets can belong to one user
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Each budget can optionally be associated with a category (e.g., Food, Travel)
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

}

