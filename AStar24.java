package test;

import java.util.*;

public class AStar24 {
    public static int[] aStar(Puzzle24Board startBoard, int heuristicType) {
        Set<Puzzle24Board> visited = new HashSet<>();
        PriorityQueue<GraphNode24> openList = new PriorityQueue<>(Comparator.comparingInt(GraphNode24::getCost));
        Map<Puzzle24Board, Integer> costMap = new HashMap<>();
        GraphNode24 startNode = new GraphNode24(startBoard, 0, calculateHeuristic(startBoard, heuristicType)); // Start with 0 moves
        openList.offer(startNode);
        visited.add(startBoard);
        costMap.put(startBoard, 0);

        int nodeCount = 1; // Count the start node

        while (!openList.isEmpty()) {
            GraphNode24 currentNode = openList.poll();
            Puzzle24Board currentBoard = currentNode.getBoard();

            // Check if the current board is the solution
            if (currentBoard.isSolved()) {
                return new int[]{nodeCount, currentNode.getMoves()};
            }

            // Generate neighbors dynamically
            List<Puzzle24Board> neighbors = currentBoard.generateNeighbors();
            for (Puzzle24Board neighborBoard : neighbors) {
                int newMoves = currentNode.getMoves() + 1;
                int newCost = newMoves + calculateHeuristic(neighborBoard, heuristicType);
                if (!visited.contains(neighborBoard) || newCost < costMap.getOrDefault(neighborBoard, Integer.MAX_VALUE)) {
                    GraphNode24 neighborNode = new GraphNode24(neighborBoard, newMoves, newCost);
                    openList.offer(neighborNode);
                    visited.add(neighborBoard);
                    costMap.put(neighborBoard, newCost);
                    nodeCount++;
                }
            }
        }
        // If the solution is not found, return an array with -1 values
        return new int[]{-1, -1};
    }

    private static int calculateHeuristic(Puzzle24Board board, int heuristicType) {
        // Dijkstra's Algorithm (heuristic = 0)
        if (heuristicType == 0) {
            return 0;
        }
        // Manhattan Distance Heuristic
        else if (heuristicType == 1) {
            int heuristic = 0;
            for (int i = 0; i < Puzzle24Board.SIZE; i++) {
                for (int j = 0; j < Puzzle24Board.SIZE; j++) {
                    int value = board.getValue(i, j);
                    if (value != 0) { // Ignore empty space
                        int goalRow = (value - 1) / Puzzle24Board.SIZE;
                        int goalCol = (value - 1) % Puzzle24Board.SIZE;
                        heuristic += Math.abs(i - goalRow) + Math.abs(j - goalCol);
                    }
                }
            }
            return heuristic;
        }
        // Non-admissible heuristic (random number)
        else if (heuristicType == 2) {
            Random random = new Random();
            return random.nextInt(99);
        } else {
            throw new IllegalArgumentException("Invalid heuristic type: " + heuristicType);
        }
    }
}
