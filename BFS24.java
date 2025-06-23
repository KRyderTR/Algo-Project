package test;

import java.util.*;

public class BFS24 {
    public static int[] bfs(Puzzle24Board startBoard) {
        Set<Puzzle24Board> visited = new HashSet<>();
        Queue<GraphNode24> queue = new ArrayDeque<>();
        GraphNode24 startNode = new GraphNode24(startBoard, 0); // Start with 0 moves
        queue.offer(startNode);
        visited.add(startBoard);

        int nodeCount = 1; // Count the start node

        while (!queue.isEmpty()) {
            GraphNode24 currentNode = queue.poll();
            Puzzle24Board currentBoard = currentNode.getBoard();

            // Check if the current board is the solution
            if (currentBoard.isSolved()) {
                return new int[] {nodeCount, currentNode.getMoves()};
            }

            // Generate neighbors dynamically
            List<Puzzle24Board> neighbors = currentBoard.generateNeighbors();
            for (Puzzle24Board neighborBoard : neighbors) {
                if (!visited.contains(neighborBoard)) {
                    GraphNode24 neighborNode = new GraphNode24(neighborBoard, currentNode.getMoves() + 1); // Increment moves
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

