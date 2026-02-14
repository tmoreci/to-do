export interface Todo {
  id: number;
  title: string;
  completed: boolean;
  createdAt: string; // ISO-8601, e.g. "2026-02-13T10:30:00"
}

export interface CreateTodoRequest {
  title: string;
}
