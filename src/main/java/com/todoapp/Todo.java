package com.todoapp;

import java.time.LocalDateTime;

public class Todo {
    // Fields — the data each Todo holds
    private int id;
    private String title;
    private boolean completed;
    private LocalDateTime createdAt;

    // Constructor — how we create a new Todo
    public Todo(int id, String title) {
        this.id = id;
        this.title = title;
        this.completed = false;                  // new todos start incomplete
        this.createdAt = LocalDateTime.now();     // timestamp when created
    }

    // Constructor for restoring a todo from a saved file
    public Todo(int id, String title, boolean completed, LocalDateTime createdAt) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.createdAt = createdAt;
    }

    // Getters — let other code READ our fields
    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    // Setters — let other code CHANGE our fields
    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    // toString — controls what prints when you do System.out.println(todo)
    @Override
    public String toString() {
        String status = completed ? "[X]" : "[ ]";
        return status + " " + id + ". " + title;
    }
}
