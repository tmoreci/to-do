package com.todoapp;

import java.util.ArrayList;
import java.util.List;

public class TodoManager {
    // An ArrayList that holds all our Todo objects
    private List<Todo> todos;
    private int nextId;
    private FileStorage storage;

    public TodoManager(FileStorage storage) {
        this.storage = storage;
        this.todos = storage.load(); // load saved todos from file

        // Set nextId to one past the highest existing ID
        this.nextId = 1;
        for (Todo todo : todos) {
            if (todo.getId() >= nextId) {
                nextId = todo.getId() + 1;
            }
        }

        if (!todos.isEmpty()) {
            System.out.println("Loaded " + todos.size() + " todo(s) from disk.");
        }
    }

    // Save current todos to file
    public void save() {
        storage.save(todos);
    }

    // Add a new todo and return it
    public Todo addTodo(String title) {
        Todo todo = new Todo(nextId, title);
        todos.add(todo);
        nextId++;
        return todo;
    }

    // Get all todos as a list
    public List<Todo> getAllTodos() {
        return todos;
    }

    // Find a todo by its ID, or return null if not found
    public Todo findById(int id) {
        for (Todo todo : todos) {
            if (todo.getId() == id) {
                return todo;
            }
        }
        return null;
    }

    // Mark a todo as completed — returns true if found, false if not
    public boolean completeTodo(int id) {
        Todo todo = findById(id);
        if (todo != null) {
            todo.setCompleted(true);
            return true;
        }
        return false;
    }

    // Delete a todo by ID — returns true if found and removed
    public boolean deleteTodo(int id) {
        Todo todo = findById(id);
        if (todo != null) {
            todos.remove(todo);
            return true;
        }
        return false;
    }

    // Count how many todos are not yet completed
    public int getPendingCount() {
        int count = 0;
        for (Todo todo : todos) {
            if (!todo.isCompleted()) {
                count++;
            }
        }
        return count;
    }

    // Print all todos to the console
    public void printAll() {
        if (todos.isEmpty()) {
            System.out.println("No todos yet! Add one to get started.");
            return;
        }
        for (Todo todo : todos) {
            System.out.println(todo);
        }
        System.out.println("\n" + getPendingCount() + " task(s) remaining.");
    }
}
