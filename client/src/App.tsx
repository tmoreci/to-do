import { useEffect, useState } from "react";
import { Todo } from "./types/todo";
import * as todoApi from "./api/todoApi";
import AddTodoForm from "./components/AddTodoForm";
import TodoStats from "./components/TodoStats";
import TodoList from "./components/TodoList";

export default function App() {
  const [todos, setTodos] = useState<Todo[]>([]);
  const [pendingCount, setPendingCount] = useState(0);
  const [loading, setLoading] = useState(true);

  const fetchTodos = async () => {
    const data = await todoApi.getAll();
    setTodos(data.todos);
    setPendingCount(data.pendingCount);
    setLoading(false);
  };

  useEffect(() => {
    fetchTodos();
  }, []);

  const handleAdd = async (title: string) => {
    await todoApi.create(title);
    await fetchTodos();
  };

  const handleComplete = async (id: number) => {
    await todoApi.complete(id);
    await fetchTodos();
  };

  const handleDelete = async (id: number) => {
    await todoApi.remove(id);
    await fetchTodos();
  };

  if (loading) {
    return (
      <div className="min-h-screen bg-gray-100 flex items-center justify-center">
        <p className="text-gray-500">Loading...</p>
      </div>
    );
  }

  return (
    <div className="min-h-screen bg-gray-100 py-8">
      <div className="max-w-lg mx-auto bg-white rounded-lg shadow-md p-6">
        <h1 className="text-2xl font-bold text-gray-800 mb-6">Todo App</h1>
        <AddTodoForm onAdd={handleAdd} />
        <TodoStats pendingCount={pendingCount} total={todos.length} />
        <TodoList
          todos={todos}
          onComplete={handleComplete}
          onDelete={handleDelete}
        />
      </div>
    </div>
  );
}
