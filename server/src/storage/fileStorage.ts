import fs from "fs";
import path from "path";
import { Todo } from "../models/todo";

const CSV_PATH = path.join(__dirname, "../../todos.csv");

export function loadTodos(): Todo[] {
  if (!fs.existsSync(CSV_PATH)) {
    return [];
  }

  const content = fs.readFileSync(CSV_PATH, "utf-8");
  const lines = content.split("\n").filter((line) => line.trim() !== "");

  return lines
    .map(parseLine)
    .filter((todo): todo is Todo => todo !== null);
}

export function saveTodos(todos: Todo[]): void {
  const lines = todos.map(
    (t) => `${t.id},${t.completed},${t.createdAt},${t.title}`
  );
  fs.writeFileSync(CSV_PATH, lines.join("\n") + "\n", "utf-8");
}

// Parse one CSV line: id,completed,createdAt,title
// Title is last so it can contain commas (mirrors Java's split(",", 4))
function parseLine(line: string): Todo | null {
  const firstComma = line.indexOf(",");
  const secondComma = line.indexOf(",", firstComma + 1);
  const thirdComma = line.indexOf(",", secondComma + 1);

  if (thirdComma === -1) return null;

  const id = parseInt(line.substring(0, firstComma), 10);
  const completed = line.substring(firstComma + 1, secondComma) === "true";
  const createdAt = line.substring(secondComma + 1, thirdComma);
  const title = line.substring(thirdComma + 1);

  if (isNaN(id) || !title) return null;

  return { id, completed, createdAt, title };
}
