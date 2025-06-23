# 🧩 Puzzle 15/24 Solver

A Java-based solver for the classic **15 Puzzle** and extended **24 Puzzle**, implementing and comparing three pathfinding algorithms: **Breadth-First Search (BFS)**, **Dijkstra’s Algorithm**, and **A\* (A-star) Search**.


## 📌 Features

- Randomly generates solvable board states for both 15-puzzle (4×4) and 24-puzzle (5×5)
- Solves the puzzle using BFS, Dijkstra, and A*
- Compares each algorithm based on:
  - Execution time
  - Number of visited nodes
  - Solution path length
- Uses **Manhattan Distance** as a heuristic for A*
- Highlights the performance impact of scaling from 15-puzzle to 24-puzzle

## 🛠️ Technologies Used

- Java
- Object-Oriented Programming (OOP)
- Data Structures (Priority Queues, Graphs)
- Algorithmic Search (BFS, Dijkstra, A*)

## 🤖 Motivation

This project was developed as part of an academic research assignment focused on evaluating and comparing classic search algorithms for solving combinatorial puzzles.  
In addition to solving the standard 15 Puzzle, the solver was extended to handle the more complex 24 Puzzle to observe how each algorithm scales with increased problem size.  
This reinforced key concepts such as algorithmic complexity, heuristic design, and optimization of search time in large state spaces.
