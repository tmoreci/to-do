import { Router, Request, Response } from "express";
import * as manager from "../services/todoManager";

export const todoRoutes = Router();

// GET /api/todos — list all todos
todoRoutes.get("/", (_req: Request, res: Response) => {
  const todos = manager.getAllTodos();
  const pendingCount = manager.getPendingCount();
  res.json({ todos, pendingCount });
});

// POST /api/todos — create a new todo
todoRoutes.post("/", (req: Request, res: Response) => {
  const { title } = req.body;
  if (!title || typeof title !== "string" || !title.trim()) {
    res.status(400).json({ error: "Title is required" });
    return;
  }
  const todo = manager.addTodo(title.trim());
  res.status(201).json({ todo });
});

// PATCH /api/todos/:id/complete — mark a todo as completed
todoRoutes.patch("/:id/complete", (req: Request, res: Response) => {
  const idParam = req.params.id;
  const id = parseInt(Array.isArray(idParam) ? idParam[0] : idParam, 10);
  if (isNaN(id)) {
    res.status(400).json({ error: "Invalid ID" });
    return;
  }
  const todo = manager.completeTodo(id);
  if (!todo) {
    res.status(404).json({ error: "Todo not found" });
    return;
  }
  res.json({ todo });
});

// DELETE /api/todos/:id — delete a todo
todoRoutes.delete("/:id", (req: Request, res: Response) => {
  const idParam = req.params.id;
  const id = parseInt(Array.isArray(idParam) ? idParam[0] : idParam, 10);
  if (isNaN(id)) {
    res.status(400).json({ error: "Invalid ID" });
    return;
  }
  const deleted = manager.deleteTodo(id);
  if (!deleted) {
    res.status(404).json({ error: "Todo not found" });
    return;
  }
  res.json({ message: `Todo #${id} deleted` });
});
