
package com.ranjan.myApp.controller;

import com.ranjan.myApp.UserRepository.ExpenseRepository;
import com.ranjan.myApp.UserRepository.UserRepository;
import com.ranjan.myApp.model.Expense;
import com.ranjan.myApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class JournalEntryController {

    @Autowired
    private ExpenseRepository journalEntryRepository;

    @Autowired
    private UserRepository userRepository;



    @PostMapping("/{username}")
    public String addExpense(@PathVariable String username, @RequestBody Expense entry) {
        System.out.println(username);
        User user = userRepository.findByUsername(username);
//        System.out.println(user.toString());
        if (user == null) {
            return "Username does not exist.";
        }
        entry.setUser(user); // Associate the expense with the user
        journalEntryRepository.save(entry);
        return "Expense added successfully!";
    }


    @GetMapping("/{username}")
    public List<Expense> getExpenses(@PathVariable String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return List.of();
        }
        return journalEntryRepository.findByUser(user);
    }


}




