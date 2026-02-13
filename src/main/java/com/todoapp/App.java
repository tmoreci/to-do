package com.todoapp;

public class App {
    public static void main(String[] args) {
        System.out.println("Welcome to the Todo App!");
        System.out.println("========================\n");

        // Create a manager — it handles all the todos for us
        TodoManager manager = new TodoManager();

        // Add some todos (no more manual IDs — the manager handles that!)
        manager.addTodo("Learn Java basics");
        manager.addTodo("Build a Todo app");
        manager.addTodo("Show it to friends");

        // Print the list
        System.out.println("--- My Todos ---");
        manager.printAll();

        // Complete a todo by ID
        System.out.println("\n* Completing 'Learn Java basics'...\n");
        manager.completeTodo(1);

        // Delete a todo by ID
        System.out.println("* Deleting 'Show it to friends'...\n");
        manager.deleteTodo(3);

        // Print the updated list
        System.out.println("--- Updated Todos ---");
        manager.printAll();
    }
}
