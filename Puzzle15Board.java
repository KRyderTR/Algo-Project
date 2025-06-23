package test;

import java.util.*;

public class Puzzle15Board {
    public static final int SIZE = 4; // Define SIZE as a public static constant
    private final int[][] board;
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};


    // Constructor to initialize the board
    public Puzzle15Board() {
        board = new int[SIZE][SIZE];
    }

    // Constructor to create a deep copy of another Puzzle15Board object
    public Puzzle15Board(Puzzle15Board other) {
        this.board = new int[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                this.board[i][j] = other.board[i][j];
            }
        }
    }

    public int getValue(int row, int col) {
        return board[row][col];
    }

    // Method to initialize the board manually by user input
    public void initializeManually() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the initial board (use 0 to represent the empty space):");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = scanner.nextInt();
            }
        }
    }

    // Method to shuffle the board by making a specified number of moves from the solution board
    public void shuffle(int moves) {
        // Start with the solved board
        resetBoard();

        // Perform 'moves' number of random moves
        Random random = new Random();
        int prevDirection = -1; // Initialize to an invalid direction
        for (int i = 0; i < moves; i++) {
            int[] emptyPosition = findEmptyPosition();
            int emptyRow = emptyPosition[0];
            int emptyCol = emptyPosition[1];

            // Generate a random direction that doesn't undo the previous move
            int direction;
            do {
                direction = random.nextInt(4); // 0: up, 1: down, 2: left, 3: right
            } while (!isValidMove(emptyRow, emptyCol, direction) || (direction + prevDirection) % 4 == 1);

            // Move the empty space in the chosen direction
            switch (direction) {
                case 0:
                    swap(emptyRow, emptyCol, emptyRow - 1, emptyCol);
                    break;
                case 1:
                    swap(emptyRow, emptyCol, emptyRow + 1, emptyCol);
                    break;
                case 2:
                    swap(emptyRow, emptyCol, emptyRow, emptyCol - 1);
                    break;
                case 3:
                    swap(emptyRow, emptyCol, emptyRow, emptyCol + 1);
                    break;
            }
            prevDirection = direction;
        }
    }

    // Method to reset the board to the solved state
    public void resetBoard() {
        int count = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (count == SIZE * SIZE) {
                    board[i][j] = 0; // Set the last cell as empty
                } else {
                    board[i][j] = count;
                    count++;
                }
            }
        }
    }

    // Method to find the position of the empty space on the board
    private int[] findEmptyPosition() {
        int[] position = new int[2];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == 0) {
                    position[0] = i;
                    position[1] = j;
                    return position;
                }
            }
        }
        return null;
    }

    // Method to check if a move in a specified direction is valid
    private boolean isValidMove(int row, int col, int direction) {
        return switch (direction) {
            case 0 -> row > 0;
            case 1 -> row < SIZE - 1;
            case 2 -> col > 0;
            case 3 -> col < SIZE - 1;
            default -> false;
        };
    }

    // Method to swap two cells on the board
    private void swap(int row1, int col1, int row2, int col2) {
        int temp = board[row1][col1];
        board[row1][col1] = board[row2][col2];
        board[row2][col2] = temp;
    }


    // Method to print the current state of the board
    public void printBoard() {
        System.out.println("Current Puzzle 15 board:");
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
                String word = Integer.toString(board[i][j]);
                // Add spaces to align columns
                for (int k = 0; k < 2 - word.length() + 2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }

    public List<Puzzle15Board> generateNeighbors() {
        List<Puzzle15Board> neighbors = new ArrayList<>();
        int[] emptyPosition = findEmptyPosition();
        int emptyRow = emptyPosition[0];
        int emptyCol = emptyPosition[1];

        // Try moving the empty space in all four directions
        for (int[] direction : DIRECTIONS) {
            int newRow = emptyRow + direction[0];
            int newCol = emptyCol + direction[1];
            if (isValidPosition(newRow, newCol)) {
                Puzzle15Board neighborBoard = new Puzzle15Board(this);
                neighborBoard.swap(emptyRow, emptyCol, newRow, newCol);
                neighbors.add(neighborBoard);
            }
        }
        return neighbors;
    }

    public boolean isSolved() {
        int count = 1;
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != count % (SIZE * SIZE)) {
                    return false;
                }
                count++;
            }
        }
        return true;
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE;
    }

}

