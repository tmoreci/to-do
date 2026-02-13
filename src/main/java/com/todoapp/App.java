package com.todoapp;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the Todo App!");
        System.out.println("========================\n");

        // Create some todos
        Todo todo1 = new Todo(1, "Learn Java basics");
        Todo todo2 = new Todo(2, "Build a Todo app");
        Todo todo3 = new Todo(3, "Show it to friends");

        // Mark the first one as done
        todo1.setCompleted(true);

        // Print them out — this calls each Todo's toString() method
        System.out.println(todo1);
        System.out.println(todo2);
        System.out.println(todo3);

        // Show that we can read individual fields with getters
        System.out.println("\nDetails for todo #2:");
        System.out.println("  Title:     " + todo2.getTitle());
        System.out.println("  Completed: " + todo2.isCompleted());
        System.out.println("  Created:   " + todo2.getCreatedAt());
    }
}
