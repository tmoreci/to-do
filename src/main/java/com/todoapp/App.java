package com.todoapp;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // Scanner reads input from the keyboard (System.in)
        Scanner scanner = new Scanner(System.in);
        TodoManager manager = new TodoManager();

        System.out.println("Welcome to the Todo App!");
        System.out.println("========================");

        // Main loop — keeps running until the user types "quit"
        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1":
                    addTodo(scanner, manager);
                    break;
                case "2":
                    listTodos(manager);
                    break;
                case "3":
                    completeTodo(scanner, manager);
                    break;
                case "4":
                    deleteTodo(scanner, manager);
                    break;
                case "5":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1-5.");
                    break;
            }
        }

        scanner.close();
        System.out.println("\nGoodbye! Stay organized!");
    }

    private static void printMenu() {
        System.out.println("\nWhat would you like to do?");
        System.out.println("  1) Add a todo");
        System.out.println("  2) List all todos");
        System.out.println("  3) Complete a todo");
        System.out.println("  4) Delete a todo");
        System.out.println("  5) Quit");
    }

    private static void addTodo(Scanner scanner, TodoManager manager) {
        System.out.print("Enter todo title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {
            System.out.println("Title cannot be empty!");
            return;
        }

        Todo todo = manager.addTodo(title);
        System.out.println("Added: " + todo);
    }

    private static void listTodos(TodoManager manager) {
        System.out.println("\n--- My Todos ---");
        manager.printAll();
    }

    private static void completeTodo(Scanner scanner, TodoManager manager) {
        if (manager.getAllTodos().isEmpty()) {
            System.out.println("No todos to complete!");
            return;
        }

        manager.printAll();
        System.out.print("Enter todo ID to complete: ");
        int id = readInt(scanner);

        if (id == -1) return;

        if (manager.completeTodo(id)) {
            System.out.println("Todo #" + id + " marked as complete!");
        } else {
            System.out.println("No todo found with ID " + id + ".");
        }
    }

    private static void deleteTodo(Scanner scanner, TodoManager manager) {
        if (manager.getAllTodos().isEmpty()) {
            System.out.println("No todos to delete!");
            return;
        }

        manager.printAll();
        System.out.print("Enter todo ID to delete: ");
        int id = readInt(scanner);

        if (id == -1) return;

        if (manager.deleteTodo(id)) {
            System.out.println("Todo #" + id + " deleted.");
        } else {
            System.out.println("No todo found with ID " + id + ".");
        }
    }

    // Helper to safely read an integer from the user
    private static int readInt(Scanner scanner) {
        String input = scanner.nextLine().trim();
        try {
            return Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return -1;
        }
    }
}
