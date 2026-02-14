import { Todo } from "../types/todo";

interface Props {
  todo: Todo;
  onComplete: (id: number) => Promise<void>;
  onDelete: (id: number) => Promise<void>;
}

export default function TodoItem({ todo, onComplete, onDelete }: Props) {
  return (
    <li className="flex items-center justify-between py-3">
      <div className="flex items-center gap-3">
        <button
          onClick={() => !todo.completed && onComplete(todo.id)}
          className={`w-5 h-5 rounded border-2 flex items-center justify-center text-xs
            ${
              todo.completed
                ? "bg-green-500 border-green-500 text-white"
                : "border-gray-300 hover:border-green-400"
            }`}
        >
          {todo.completed && "\u2713"}
        </button>
        <span
          className={
            todo.completed ? "line-through text-gray-400" : "text-gray-800"
          }
        >
          {todo.title}
        </span>
      </div>
      <button
        onClick={() => onDelete(todo.id)}
        className="text-red-400 hover:text-red-600 text-sm"
      >
        Delete
      </button>
    </li>
  );
}
