import express from "express";
import cors from "cors";
import { todoRoutes } from "./routes/todos";
import { init } from "./services/todoManager";

const app = express();
const PORT = 3001;

app.use(cors());
app.use(express.json());
app.use("/api/todos", todoRoutes);

init();

app.listen(PORT, () => {
  console.log(`Server running at http://localhost:${PORT}`);
});
