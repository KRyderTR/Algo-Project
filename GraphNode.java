package test;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private Puzzle15Board board;
    private int moves; // Number of moves to reach this board configuration

    private List<GraphNode> neighbors;

    private static int nodeCount = 0; // Counter for the number of nodes created

    private int cost; // Total cost of the node (g + h)

    public GraphNode(Puzzle15Board board, int moves) {
        this.board = board;
        this.moves = moves;
        this.neighbors = new ArrayList<>();
    }


    public GraphNode(Puzzle15Board board, int moves, int cost) {
        this.board = board;
        this.moves = moves;
        this.cost = cost;
        this.neighbors = new ArrayList<>();
        nodeCount++; // Increment the node count when a new node is created
    }

    public int getTotalCost() {
        // Calculate and return the total cost (sum of moves and heuristic)
        return moves + cost;
    }

    // Getter methods...
    public static int getNodeCount() {
        return nodeCount;
    }

    public int getCost() {
        return cost;
    }


    public Puzzle15Board getBoard() {
        return board;
    }

    public List<GraphNode> getNeighbors() {
        return neighbors;
    }

    public int getMoves() {
        return moves;
    }

    public void addNeighbor(GraphNode neighbor) {
        neighbors.add(neighbor);
    }
}
