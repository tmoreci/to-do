import { Todo } from "../types/todo";
import TodoItem from "./TodoItem";

interface Props {
  todos: Todo[];
  onComplete: (id: number) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
}

export default function TodoList({ todos, onComplete, onDelete }: Props) {
  if (todos.length === 0) {
    return (
      <p className="text-gray-500 text-center py-8">
        No todos yet! Add one to get started.
      </p>
    );
  }

  return (
    <ul className="divide-y divide-gray-200">
      {todos.map((todo) => (
        <TodoItem
          key={todo.id}
          todo={todo}
          onComplete={onComplete}
          onDelete={onDelete}
        />
      ))}
    </ul>
  );
}
