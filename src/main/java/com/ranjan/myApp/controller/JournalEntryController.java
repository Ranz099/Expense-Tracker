
package com.ranjan.myApp.controller;

import com.ranjan.myApp.UserRepository.ExpenseRepository;
import com.ranjan.myApp.UserRepository.UserRepository;
import com.ranjan.myApp.model.Expense;
import com.ranjan.myApp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    @PutMapping("/{username}")
    public String updateExpense(@PathVariable String username, @RequestBody Expense updatedExpense) {
        // Retrieve the user from the database
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "Username does not exist.";
        }

        // Retrieve the existing expense entry for modification
        Expense existingExpense = journalEntryRepository.findByUserAndId(user, updatedExpense.getId());
        if (existingExpense == null) {
            return "Expense record not found.";
        }

        // Update the existing expense with new details
        existingExpense.setAmount(updatedExpense.getAmount());
        existingExpense.setTitle(updatedExpense.getTitle());
        existingExpense.setCategory(updatedExpense.getCategory());
        journalEntryRepository.save(existingExpense);  // Save the updated expense

        return "Expense updated successfully!";  // Return a success message
    }
    @DeleteMapping("/{username}/{id}")
    public String deleteExpense(@PathVariable String username, @PathVariable int id ){
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "Username does not exist.";
        }
        Expense existingExpense = journalEntryRepository.findByUserAndId(user, id);
        if (existingExpense == null) {
            return "Expense record not found.";
        }
        journalEntryRepository.deleteById(id);
        return "deleted successfully";
    }

}



