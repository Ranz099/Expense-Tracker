package com.ranjan.myApp.UserRepository;

import com.ranjan.myApp.model.Expense;
import com.ranjan.myApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Integer> {
    List<Expense> findByUser(User user);
}