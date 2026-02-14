import { Todo } from "../types/todo";

const BASE_URL = "http://localhost:3001/api/todos";

interface GetAllResponse {
  todos: Todo[];
  pendingCount: number;
}

export async function getAll(): Promise<GetAllResponse> {
  const res = await fetch(BASE_URL);
  if (!res.ok) throw new Error("Failed to fetch todos");
  return res.json();
}

export async function create(title: string): Promise<{ todo: Todo }> {
  const res = await fetch(BASE_URL, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title }),
  });
  if (!res.ok) throw new Error("Failed to create todo");
  return res.json();
}

export async function complete(id: number): Promise<{ todo: Todo }> {
  const res = await fetch(`${BASE_URL}/${id}/complete`, {
    method: "PATCH",
  });
  if (!res.ok) throw new Error("Failed to complete todo");
  return res.json();
}

export async function remove(id: number): Promise<void> {
  const res = await fetch(`${BASE_URL}/${id}`, {
    method: "DELETE",
  });
  if (!res.ok) throw new Error("Failed to delete todo");
}
