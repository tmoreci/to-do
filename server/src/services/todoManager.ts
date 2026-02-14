import { Todo } from "../models/todo";
import { loadTodos, saveTodos } from "../storage/fileStorage";

let todos: Todo[] = [];
let nextId: number = 1;

// Load todos from CSV (called once at startup)
export function init(): void {
  todos = loadTodos();
  nextId = 1;
  for (const todo of todos) {
    if (todo.id >= nextId) {
      nextId = todo.id + 1;
    }
  }
}

export function getAllTodos(): Todo[] {
  return todos;
}

export function findById(id: number): Todo | undefined {
  return todos.find((t) => t.id === id);
}

export function addTodo(title: string): Todo {
  const todo: Todo = {
    id: nextId,
    title,
    completed: false,
    createdAt: new Date().toISOString().slice(0, 19),
  };
  todos.push(todo);
  nextId++;
  saveTodos(todos);
  return todo;
}

export function completeTodo(id: number): Todo | null {
  const todo = findById(id);
  if (!todo) return null;
  todo.completed = true;
  saveTodos(todos);
  return todo;
}

export function deleteTodo(id: number): boolean {
  const index = todos.findIndex((t) => t.id === id);
  if (index === -1) return false;
  todos.splice(index, 1);
  saveTodos(todos);
  return true;
}

export function getPendingCount(): number {
  return todos.filter((t) => !t.completed).length;
}
