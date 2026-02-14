# Todo App

A full-stack to-do application built with TypeScript, Express, React, and Tailwind CSS.

## Setup

### Backend

```bash
cd server
npm install
npm run dev
```

The API server runs at http://localhost:3001.

### Frontend

```bash
cd client
npm install
npm run dev
```

The app opens at http://localhost:5173.

## API Endpoints

| Method | Path | Description |
|--------|------|-------------|
| GET | `/api/todos` | List all todos |
| POST | `/api/todos` | Create a todo (`{ "title": "..." }`) |
| PATCH | `/api/todos/:id/complete` | Mark a todo as completed |
| DELETE | `/api/todos/:id` | Delete a todo |
