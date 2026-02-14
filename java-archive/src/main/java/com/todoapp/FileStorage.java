package com.todoapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles saving and loading todos to/from a CSV file.
 *
 * File format (one line per todo):
 *   id,completed,createdAt,title
 *
 * Example:
 *   1,false,2026-02-13T10:30:00,Buy groceries
 *   2,true,2026-02-13T10:31:00,Learn Java
 */
public class FileStorage {
    private String filePath;

    public FileStorage(String filePath) {
        this.filePath = filePath;
    }

    // Save a list of todos to the CSV file
    public void save(List<Todo> todos) {
        // try-with-resources: Java automatically closes the writer when done
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Todo todo : todos) {
                // Build one CSV line: id,completed,createdAt,title
                String line = todo.getId()
                    + "," + todo.isCompleted()
                    + "," + todo.getCreatedAt()
                    + "," + todo.getTitle();
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving todos: " + e.getMessage());
        }
    }

    // Load todos from the CSV file — returns an empty list if file doesn't exist
    public List<Todo> load() {
        List<Todo> todos = new ArrayList<>();
        File file = new File(filePath);

        // If there's no save file yet, just return an empty list
        if (!file.exists()) {
            return todos;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;

            // Read the file line by line
            while ((line = reader.readLine()) != null) {
                Todo todo = parseLine(line);
                if (todo != null) {
                    todos.add(todo);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading todos: " + e.getMessage());
        }

        return todos;
    }

    // Parse one CSV line back into a Todo object
    private Todo parseLine(String line) {
        // Split into at most 4 parts: id, completed, createdAt, title
        // Limit of 4 means commas inside the title won't break things
        String[] parts = line.split(",", 4);

        if (parts.length < 4) {
            return null; // skip malformed lines
        }

        try {
            int id = Integer.parseInt(parts[0]);
            boolean completed = Boolean.parseBoolean(parts[1]);
            LocalDateTime createdAt = LocalDateTime.parse(parts[2]);
            String title = parts[3];

            return new Todo(id, title, completed, createdAt);
        } catch (Exception e) {
            System.out.println("Skipping bad line: " + line);
            return null;
        }
    }
}
