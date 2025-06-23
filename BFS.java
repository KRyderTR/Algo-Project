package test;

import java.util.*;

public class BFS {
    public static int[] bfs(Puzzle15Board startBoard) {
        Set<Puzzle15Board> visited = new HashSet<>();
        Queue<GraphNode> queue = new ArrayDeque<>();
        GraphNode startNode = new GraphNode(startBoard, 0); // Start with 0 moves
        queue.offer(startNode);
        visited.add(startBoard);

        int nodeCount = 1; // Count the start node

        while (!queue.isEmpty()) {
            GraphNode currentNode = queue.poll();
            Puzzle15Board currentBoard = currentNode.getBoard();

            // Check if the current board is the solution
            if (currentBoard.isSolved()) {
                return new int[] {nodeCount, currentNode.getMoves()};
            }

            // Generate neighbors dynamically
            List<Puzzle15Board> neighbors = currentBoard.generateNeighbors();
            for (Puzzle15Board neighborBoard : neighbors) {
                if (!visited.contains(neighborBoard)) {
                    GraphNode neighborNode = new GraphNode(neighborBoard, currentNode.getMoves() + 1); // Increment moves
                    currentNode.addNeighbor(neighborNode);
                    queue.offer(neighborNode);
                    visited.add(neighborBoard);
                    nodeCount++;
                }
            }
        }

        // If the solution is not found, return -1
        return new int[] {-1, -1};
    }
}

