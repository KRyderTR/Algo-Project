package test;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        // Board 15
        Scanner scanner = new Scanner(System.in);
        Puzzle15Board puzzle15 = new Puzzle15Board();

        System.out.println(" ");
        System.out.println("How would you like to initialize the Puzzle15 board?");
        System.out.println(" 1. Manually enter the starting board");
        System.out.println(" 2. Enter the number of moves from the solution board to shuffle");

        int initChoice = scanner.nextInt();
        System.out.println(" ");

        if (initChoice == 1) {
            puzzle15.initializeManually();
        } else if (initChoice == 2) {
            System.out.println("Please enter the number of moves from the solution board to shuffle:");
            int moves = scanner.nextInt();
            puzzle15.shuffle(moves);
        } else {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        System.out.println(" ");
        puzzle15.printBoard();
        System.out.println(" ");

        // Generate and print the table
        System.out.println("Comparison table between the algorithms for solving the board:");
        printTable1(puzzle15);
        System.out.println(" ");

        // Board 24
        Scanner scanner1 = new Scanner(System.in);
        Puzzle24Board puzzle24 = new Puzzle24Board();

        System.out.println(" ");
        System.out.println("How would you like to initialize the Puzzle 24 board?");
        System.out.println(" 1. Manually enter the starting board");
        System.out.println(" 2. Enter the number of moves from the solution board to shuffle");

        int initChoice1 = scanner1.nextInt();
        System.out.println(" ");

        if (initChoice1 == 1) {
            puzzle24.initializeManually();
        } else if (initChoice1 == 2) {
            System.out.println("Enter the number of moves from the solution board to shuffle:");
            int moves1 = scanner1.nextInt();
            puzzle24.shuffle(moves1);
        } else {
            System.out.println("Invalid choice. Please try again.");
            return;
        }
        System.out.println(" ");
        puzzle24.printBoard();
        System.out.println(" ");

        // Generate and print the table
        System.out.println("Comparison table between the algorithms for solving the board:");
        printTable3(puzzle24);
        System.out.println(" ");


        //Print a table for 5 puzzle 15 boards shuffled 10 moves from solution
        for (int i = 0; i < 5; i++) {
            System.out.println("Comparison table for Puzzle 15 board number " + (i+1));
            int n=10;
            Puzzle15Board board = new Puzzle15Board();
            board.shuffle(n);
            printTable1(board);
        }

        //Print a table for 5 puzzle 24 boards shuffled 10 moves from solution
        for (int i = 0; i < 5; i++) {
            System.out.println("Comparison table for Puzzle 24 board number " + (i+1));
            int n=10;
            Puzzle15Board board = new Puzzle15Board();
            board.shuffle(n);
            printTable1(board);
        }

        // print the avg tables for Puzzle 15 and 24 games
        System.out.println(" ");
        System.out.println("Average stats table for Puzzle15 - 50 boards shuffled 10 steps from solution");
        printTable2();
        System.out.println("Average stats table for Puzzle24 - 50 boards shuffled 10 steps from solution");
        printTable4();
    }


    // Method to print table 1 (Puzzle 15)
    private static void printTable1(Puzzle15Board puzzle15) {

        // Define the table size
        int rows = 4;
        int cols = 5;
        // Get the maximum word length to determine column width
        int maxWordLength = 0;
        String[][] table = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word;
                if (i == 0) {
                    // Assign header labels for the first row
                    if (j == 0) {
                        word = "";
                    } else if (j == 1) {
                        word = "BFS";
                    } else if (j == 2) {
                        word = "Dijkstra";
                    } else if (j == 3) {
                        word = "A*MAN";
                    } else {
                        word = "A*NON-AD";
                    }
                } else {
                    // Assign header labels for the left column
                    if (j == 0) {
                        if (i == 1) {
                            word = "Running time (ms)";
                        } else if (i == 2) {
                            word = "Nodes created";
                        } else {
                            word = "Moves made";
                        }
                    } else {
                        word = ""; // Empty cell initially
                    }
                }
                table[i][j] = word;
                maxWordLength = Math.max(maxWordLength, word.length());
            }
        }

        // Simulate data for each algorithm
        for (int j = 1; j < cols; j++) {
            switch (table[0][j]) {
                case "BFS" -> {
                    // Run BFS and print the number of nodes, moves, and running time
                    long bfsStartTime = System.currentTimeMillis();
                    int[] bfsResult = BFS.bfs(puzzle15);
                    long bfsEndTime = System.currentTimeMillis();
                    int bfsNodeCount = bfsResult[0];
                    int bfsMovesToSolution = bfsResult[1];
                    long bfsElapsedTime = bfsEndTime - bfsStartTime;
                    table[1][j] = Long.toString(bfsElapsedTime); // Running time for BFS
                    table[2][j] = Integer.toString(bfsNodeCount); // Nodes created for BFS
                    table[3][j] = Integer.toString(bfsMovesToSolution); // Moves made for BFS
                }
                case "Dijkstra" -> {
                    // Run Dijkstra algorithm and print the number of nodes, moves, and running time
                    long dijkstraStartTime = System.currentTimeMillis();
                    int[] dijkstraResult = AStar.aStar(puzzle15, 0);
                    long dijkstraEndTime = System.currentTimeMillis();
                    int dijkstraNodeCount = dijkstraResult[0];
                    int dijkstraMovesToSolution = dijkstraResult[1];
                    long dijkstraElapsedTime = dijkstraEndTime - dijkstraStartTime;
                    table[1][j] = Long.toString(dijkstraElapsedTime); // Running time for Dijkstra
                    table[2][j] = Integer.toString(dijkstraNodeCount); // Nodes created for Dijkstra
                    table[3][j] = Integer.toString(dijkstraMovesToSolution); // Moves made for Dijkstra
                }
                case "A*MAN" -> {
                    // Run A* algorithm and print the number of nodes, moves, and running time
                    long aStarStartTime = System.currentTimeMillis();
                    int[] aStarResult = AStar.aStar(puzzle15, 1);
                    long aStarEndTime = System.currentTimeMillis();
                    int aStarNodeCount = aStarResult[0];
                    int aStarMovesToSolution = aStarResult[1];
                    long aStarElapsedTime = aStarEndTime - aStarStartTime;
                    table[1][j] = Long.toString(aStarElapsedTime); // Running time for A*MAN
                    table[2][j] = Integer.toString(aStarNodeCount); // Nodes created for A*MAN
                    table[3][j] = Integer.toString(aStarMovesToSolution); // Moves made for A*MAN
                }
                case "A*NON-AD" -> {
                    // Run A* algorithm with non-admissible heuristic and print the number of nodes, moves, and running time
                    long nonAdStartTime = System.currentTimeMillis();
                    int[] nonAdResult = AStar.aStar(puzzle15, 2);
                    long nonAdEndTime = System.currentTimeMillis();
                    int nonAdNodeCount = nonAdResult[0];
                    int nonAdMovesToSolution = nonAdResult[1];
                    long nonAdElapsedTime = nonAdEndTime - nonAdStartTime;
                    table[1][j] = Long.toString(nonAdElapsedTime); // Running time for A*NON-AD
                    table[2][j] = Integer.toString(nonAdNodeCount); // Nodes created for A*NON-AD
                    table[3][j] = Integer.toString(nonAdMovesToSolution); // Moves made for A*NON-AD
                }
            }
        }
        // Print the table with aligned columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word = table[i][j];
                if (i == 0 || j == 0) {
                    System.out.print("\u001B[33m" + word + "\u001B[0m"); // Yellow color code for header labels
                } else {
                    System.out.print(word);
                }
                // Add spaces to align columns
                for (int k = 0; k < maxWordLength - word.length() + 4; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println(); // Move to the next row
        }
        System.out.println(" ");
    }

    // Method to print table 2 (Puzzle 15 avg)
    private static void printTable2() {

        // Define the table size
        int rows = 4;
        int cols = 5;

        int avg = 50; // number of boards
        int n = 10; // n steps from solution

        // Get the maximum word length to determine column width
        int maxWordLength = 0;
        String[][] table = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word;
                if (i == 0) {
                    // Assign header labels for the first row
                    if (j == 0) {
                        word = "";
                    } else if (j == 1) {
                        word = "BFS";
                    } else if (j == 2) {
                        word = "Dijkstra";
                    } else if (j == 3) {
                        word = "A*MAN";
                    } else {
                        word = "A*NON-AD";
                    }
                } else {
                    // Assign header labels for the left column
                    if (j == 0) {
                        if (i == 1) {
                            word = "avg Running time (ms)";
                        } else if (i == 2) {
                            word = "avg Nodes created";
                        } else {
                            word = "avg Moves made";
                        }
                    } else {
                        word = ""; // Empty cell initially
                    }
                }
                table[i][j] = word;
                maxWordLength = Math.max(maxWordLength, word.length());
            }
        }

        int AVGbfsNodeCount = 0;
        int AVGbfsMovesToSolution = 0;
        long AVGbfsElapsedTime = 0;

        int AVGdijkstraNodeCount = 0;
        int AVGdijkstraMovesToSolution = 0;
        long AVGdijkstraElapsedTime = 0;

        int AVGaStarNodeCount = 0;
        int AVGaStarMovesToSolution = 0;
        long AVGaStarElapsedTime = 0;

        int AVGnonAdNodeCount = 0;
        int AVGnonAdMovesToSolution = 0;
        long AVGnonAdElapsedTime = 0;


        for (int k = 0; k < avg; k++) {
            Puzzle15Board puzzle15 = new Puzzle15Board();
            puzzle15.shuffle(n);
            // Simulate data for each algorithm
            for (int j = 1; j < cols; j++) {
                switch (table[0][j]) {
                    case "BFS" -> {
                        // Run BFS and increase the number of nodes, moves, and running time
                        long bfsStartTime = System.nanoTime() / 1000;
                        int[] bfsResult = BFS.bfs(puzzle15);
                        long bfsEndTime = System.nanoTime() / 1000;
                        AVGbfsNodeCount += bfsResult[0];
                        AVGbfsMovesToSolution += bfsResult[1];
                        AVGbfsElapsedTime += bfsEndTime - bfsStartTime;
                    }
                    case "Dijkstra" -> {
                        // Run Dijkstra algorithm and increase the number of nodes, moves, and running time
                        long dijkstraStartTime = System.nanoTime() / 1000;
                        int[] dijkstraResult = AStar.aStar(puzzle15, 0);
                        long dijkstraEndTime = System.nanoTime() / 1000;
                        AVGdijkstraNodeCount += dijkstraResult[0];
                        AVGdijkstraMovesToSolution += dijkstraResult[1];
                        AVGdijkstraElapsedTime += dijkstraEndTime - dijkstraStartTime;
                    }
                    case "A*MAN" -> {
                        // Run A* algorithm and increase the number of nodes, moves, and running time
                        long aStarStartTime = System.nanoTime() / 1000;
                        int[] aStarResult = AStar.aStar(puzzle15, 1);
                        long aStarEndTime = System.nanoTime() / 1000;
                        AVGaStarNodeCount += aStarResult[0];
                        AVGaStarMovesToSolution += aStarResult[1];
                        AVGaStarElapsedTime += aStarEndTime - aStarStartTime;
                    }
                    case "A*NON-AD" -> {
                        // Run A* algorithm with non-admissible heuristic and increase the number of nodes, moves, and running time
                        long nonAdStartTime = System.nanoTime() / 1000;
                        int[] nonAdResult = AStar.aStar(puzzle15, 2);
                        long nonAdEndTime = System.nanoTime() / 1000;
                        AVGnonAdNodeCount += nonAdResult[0];
                        AVGnonAdMovesToSolution += nonAdResult[1];
                        AVGnonAdElapsedTime += nonAdEndTime - nonAdStartTime;
                    }
                }
            }
        }

        // Simulate data for each algorithm
        for (int j = 1; j < cols; j++) {
            switch (table[0][j]) {
                case "BFS" -> {
                    // Dividing BFS data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGbfsElapsedTime / avg); // AVG Running time for BFS
                    table[2][j] = Integer.toString(AVGbfsNodeCount / avg); // AVG Nodes created for BFS
                    table[3][j] = Integer.toString(AVGbfsMovesToSolution / avg); // AVG Moves made for BFS
                }
                case "Dijkstra" -> {
                    // Dividing Dijkstra data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGdijkstraElapsedTime / avg); // AVG Running time for Dijkstra
                    table[2][j] = Integer.toString(AVGdijkstraNodeCount / avg); // AVG Nodes created for Dijkstra
                    table[3][j] = Integer.toString(AVGdijkstraMovesToSolution / avg); // AVG Moves made for Dijkstra
                }
                case "A*MAN" -> {
                    // Dividing A* data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGaStarElapsedTime / avg); // AVG Running time for A*MAN
                    table[2][j] = Integer.toString(AVGaStarNodeCount / avg); // AVG Nodes created for A*MAN
                    table[3][j] = Integer.toString(AVGaStarMovesToSolution / avg); // AVG Moves made for A*MAN
                }
                case "A*NON-AD" -> {
                    // Dividing A* with non-admissible heuristic data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGnonAdElapsedTime / avg); // AVG Running time for A*NON-AD
                    table[2][j] = Integer.toString(AVGnonAdNodeCount / avg); // AVG Nodes created for A*NON-AD
                    table[3][j] = Integer.toString(AVGnonAdMovesToSolution / avg); // AVG Moves made for A*NON-AD
                }
            }
        }

        // Print the table with aligned columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word = table[i][j];
                if (i == 0 || j == 0) {
                    System.out.print("\u001B[34;1m" + word + "\u001B[0m"); // Bright blue color code for header labels

                } else {
                    System.out.print(word);
                }
                // Add spaces to align columns
                for (int k = 0; k < maxWordLength - word.length() + 4; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println(); // Move to the next row
        }
        System.out.println(" ");
    }

    // Method to print the table 3 (Puzzle 24)
    private static void printTable3(Puzzle24Board puzzle24) {

        // Define the table size
        int rows = 4;
        int cols = 5;
        // Get the maximum word length to determine column width
        int maxWordLength = 0;
        String[][] table = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word;
                if (i == 0) {
                    // Assign header labels for the first row
                    if (j == 0) {
                        word = "";
                    } else if (j == 1) {
                        word = "BFS";
                    } else if (j == 2) {
                        word = "Dijkstra";
                    } else if (j == 3) {
                        word = "AStarMan";
                    } else {
                        word = "AStarNonAd";
                    }
                } else {
                    // Assign header labels for the left column
                    if (j == 0) {
                        if (i == 1) {
                            word = "Running time (ms)";
                        } else if (i == 2) {
                            word = "Nodes created";
                        } else {
                            word = "Moves made";
                        }
                    } else {
                        word = ""; // Empty cell initially
                    }
                }
                table[i][j] = word;
                maxWordLength = Math.max(maxWordLength, word.length());
            }
        }

        // Simulate data for each algorithm
        for (int j = 1; j < cols; j++) {
            switch (table[0][j]) {
                case "BFS" -> {

                    // Run BFS24 and print the number of nodes, moves, and running time
                    long bfsStartTime = System.currentTimeMillis();
                    int[] bfsResult = BFS24.bfs(puzzle24);
                    long bfsEndTime = System.currentTimeMillis();
                    int bfsNodeCount = bfsResult[0];
                    int bfsMovesToSolution = bfsResult[1];
                    long bfsElapsedTime = bfsEndTime - bfsStartTime;
                    table[1][j] = Long.toString(bfsElapsedTime); // Running time for BFS24
                    table[2][j] = Integer.toString(bfsNodeCount); // Nodes created for BFS24
                    table[3][j] = Integer.toString(bfsMovesToSolution); // Moves made for BFS24
                }
                case "Dijkstra" -> {

                    // Run Dijkstra24 algorithm and print the number of nodes, moves, and running time
                    long dijkstraStartTime = System.currentTimeMillis();
                    int[] dijkstraResult = AStar24.aStar(puzzle24, 0);
                    long dijkstraEndTime = System.currentTimeMillis();
                    int dijkstraNodeCount = dijkstraResult[0];
                    int dijkstraMovesToSolution = dijkstraResult[1];
                    long dijkstraElapsedTime = dijkstraEndTime - dijkstraStartTime;
                    table[1][j] = Long.toString(dijkstraElapsedTime); // Running time for Dijkstra24
                    table[2][j] = Integer.toString(dijkstraNodeCount); // Nodes created for Dijkstra24
                    table[3][j] = Integer.toString(dijkstraMovesToSolution); // Moves made for Dijkstra24
                }
                case "AStarMan" -> {

                    // Run AStar24 algorithm and print the number of nodes, moves, and running time
                    long aStarStartTime = System.currentTimeMillis();
                    int[] aStarResult = AStar24.aStar(puzzle24, 1);
                    long aStarEndTime = System.currentTimeMillis();
                    int aStarNodeCount = aStarResult[0];
                    int aStarMovesToSolution = aStarResult[1];
                    long aStarElapsedTime = aStarEndTime - aStarStartTime;
                    table[1][j] = Long.toString(aStarElapsedTime); // Running time for AStarMan24
                    table[2][j] = Integer.toString(aStarNodeCount); // Nodes created for AStarMan24
                    table[3][j] = Integer.toString(aStarMovesToSolution); // Moves made for AStarMan24
                }
                case "AStarNonAd" -> {

                    // Run AStar24 algorithm with non-admissible heuristic and print the number of nodes, moves, and running time
                    long nonAdStartTime = System.currentTimeMillis();
                    int[] nonAdResult = AStar24.aStar(puzzle24, 2);
                    long nonAdEndTime = System.currentTimeMillis();
                    int nonAdNodeCount = nonAdResult[0];
                    int nonAdMovesToSolution = nonAdResult[1];
                    long nonAdElapsedTime = nonAdEndTime - nonAdStartTime;
                    table[1][j] = Long.toString(nonAdElapsedTime); // Running time for AStarNonAd24
                    table[2][j] = Integer.toString(nonAdNodeCount); // Nodes created for AStarNonAd24
                    table[3][j] = Integer.toString(nonAdMovesToSolution); // Moves made for AStarNonAd24
                }
            }
        }
        // Print the table with aligned columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word = table[i][j];
                if (i == 0 || j == 0) {
                    System.out.print("\u001B[33m" + word + "\u001B[0m"); // Yellow color code for header labels
                } else {
                    System.out.print(word);
                }
                // Add spaces to align columns
                for (int k = 0; k < maxWordLength - word.length() + 4; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println(); // Move to the next row
        }
        System.out.println(" ");
    }

    // Method to print table 4 (Puzzle 24 avg)
    private static void printTable4() {

        // Define the table size
        int rows = 4;
        int cols = 5;

        int avg = 50; // number of boards
        int n = 10; // n steps from solution


        // Get the maximum word length to determine column width
        int maxWordLength = 0;
        String[][] table = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word;
                if (i == 0) {
                    // Assign header labels for the first row
                    if (j == 0) {
                        word = "";
                    } else if (j == 1) {
                        word = "BFS";
                    } else if (j == 2) {
                        word = "Dijkstra";
                    } else if (j == 3) {
                        word = "A*MAN";
                    } else {
                        word = "A*NON-AD";
                    }
                } else {
                    // Assign header labels for the left column
                    if (j == 0) {
                        if (i == 1) {
                            word = "avg Running time (ms)";
                        } else if (i == 2) {
                            word = "avg Nodes created";
                        } else {
                            word = "avg Moves made";
                        }
                    } else {
                        word = ""; // Empty cell initially
                    }
                }
                table[i][j] = word;
                maxWordLength = Math.max(maxWordLength, word.length());
            }
        }

        int AVGbfsNodeCount = 0;
        int AVGbfsMovesToSolution = 0;
        long AVGbfsElapsedTime = 0;

        int AVGdijkstraNodeCount = 0;
        int AVGdijkstraMovesToSolution = 0;
        long AVGdijkstraElapsedTime = 0;

        int AVGaStarNodeCount = 0;
        int AVGaStarMovesToSolution = 0;
        long AVGaStarElapsedTime = 0;

        int AVGnonAdNodeCount = 0;
        int AVGnonAdMovesToSolution = 0;
        long AVGnonAdElapsedTime = 0;


        for (int k = 0; k < avg; k++) {
            Puzzle24Board puzzle24 = new Puzzle24Board();
            puzzle24.shuffle(n);
            // Simulate data for each algorithm
            for (int j = 1; j < cols; j++) {
                switch (table[0][j]) {
                    case "BFS" -> {
                        // Run BFS and increase the number of nodes, moves, and running time
                        long bfsStartTime = System.nanoTime() / 1000;
                        int[] bfsResult = BFS24.bfs(puzzle24);
                        long bfsEndTime = System.nanoTime() / 1000;
                        AVGbfsNodeCount += bfsResult[0];
                        AVGbfsMovesToSolution += bfsResult[1];
                        AVGbfsElapsedTime += bfsEndTime - bfsStartTime;
                    }
                    case "Dijkstra" -> {
                        // Run Dijkstra algorithm and increase the number of nodes, moves, and running time
                        long dijkstraStartTime = System.nanoTime() / 1000;
                        int[] dijkstraResult = AStar24.aStar(puzzle24, 0);
                        long dijkstraEndTime = System.nanoTime() / 1000;
                        AVGdijkstraNodeCount += dijkstraResult[0];
                        AVGdijkstraMovesToSolution += dijkstraResult[1];
                        AVGdijkstraElapsedTime += dijkstraEndTime - dijkstraStartTime;
                    }
                    case "A*MAN" -> {
                        // Run A* algorithm and increase the number of nodes, moves, and running time
                        long aStarStartTime = System.nanoTime() / 1000;
                        int[] aStarResult = AStar24.aStar(puzzle24, 1);
                        long aStarEndTime = System.nanoTime() / 1000;
                        AVGaStarNodeCount += aStarResult[0];
                        AVGaStarMovesToSolution += aStarResult[1];
                        AVGaStarElapsedTime += aStarEndTime - aStarStartTime;
                    }
                    case "A*NON-AD" -> {
                        // Run A* algorithm with non-admissible heuristic and increase the number of nodes, moves, and running time
                        long nonAdStartTime = System.nanoTime() / 1000;
                        int[] nonAdResult = AStar24.aStar(puzzle24, 2);
                        long nonAdEndTime = System.nanoTime() / 1000;
                        AVGnonAdNodeCount += nonAdResult[0];
                        AVGnonAdMovesToSolution += nonAdResult[1];
                        AVGnonAdElapsedTime += nonAdEndTime - nonAdStartTime;
                    }
                }
            }
        }

        // Simulate data for each algorithm
        for (int j = 1; j < cols; j++) {
            switch (table[0][j]) {
                case "BFS" -> {
                    // Dividing BFS data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGbfsElapsedTime / avg); // AVG Running time for BFS
                    table[2][j] = Integer.toString(AVGbfsNodeCount / avg); // AVG Nodes created for BFS
                    table[3][j] = Integer.toString(AVGbfsMovesToSolution / avg); // AVG Moves made for BFS
                }
                case "Dijkstra" -> {
                    // Dividing Dijkstra data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGdijkstraElapsedTime / avg); // AVG Running time for Dijkstra
                    table[2][j] = Integer.toString(AVGdijkstraNodeCount / avg); // AVG Nodes created for Dijkstra
                    table[3][j] = Integer.toString(AVGdijkstraMovesToSolution / avg); // AVG Moves made for Dijkstra
                }
                case "A*MAN" -> {
                    // Dividing A* data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGaStarElapsedTime / avg); // AVG Running time for A*MAN
                    table[2][j] = Integer.toString(AVGaStarNodeCount / avg); // AVG Nodes created for A*MAN
                    table[3][j] = Integer.toString(AVGaStarMovesToSolution / avg); // AVG Moves made for A*MAN
                }
                case "A*NON-AD" -> {
                    // Dividing A* with non-admissible heuristic data by 50 and print the number of nodes, moves, and running time
                    table[1][j] = Long.toString(AVGnonAdElapsedTime / avg); // AVG Running time for A*NON-AD
                    table[2][j] = Integer.toString(AVGnonAdNodeCount / avg); // AVG Nodes created for A*NON-AD
                    table[3][j] = Integer.toString(AVGnonAdMovesToSolution / avg); // AVG Moves made for A*NON-AD
                }
            }
        }

        // Print the table with aligned columns
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                String word = table[i][j];
                if (i == 0 || j == 0) {
                    System.out.print("\u001B[34;1m" + word + "\u001B[0m"); // Bright blue color code for header labels

                } else {
                    System.out.print(word);
                }
                // Add spaces to align columns
                for (int k = 0; k < maxWordLength - word.length() + 4; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println(); // Move to the next row
        }
        System.out.println(" ");
    }
}










