package tictactoe;

import java.util.*;

public class Main {
    private static char[] gameGrid;
    private static char currentPlayer = 'X';
    private static final String cellOccupiedMsg = "This cell is occupied! Choose another one!",
                                wrongCoordinatesMsg = "Coordinates should be from 1 to 3!",
                                wrongInputMsg = "You should enter numbers!";

    private static char[] setGameGrid() {
        char[] gameGrid = new char[9];

        Arrays.fill(gameGrid, ' ');

        return gameGrid;
    }

    private static StringBuilder getGameGridOutput(char[] gameGrid) {
        StringBuilder gameGridOutput = new StringBuilder();

        gameGridOutput.append("-".repeat(9)).append(String.format("%n"));

        for (int i = 0; i < gameGrid.length; i++) {
            if (i % 3 == 0) {
                gameGridOutput.append(String.format("| %c ", gameGrid[i]));
            } else if (i % 3 == 2) {
                gameGridOutput.append(String.format("%c |%n", gameGrid[i]));
            } else {
                gameGridOutput.append(String.format("%c ", gameGrid[i]));
            }
        }

        gameGridOutput.append("-".repeat(9));

        return gameGridOutput;
    }

    private static boolean isCellOccupied(int row, int column) {
        return gameGrid[row * 3 + column] != ' ';
    }

    private static void setCell(int row, int column, char currentPlayer) {
        gameGrid[row * 3 + column] = currentPlayer;
    }

    private static boolean isCorrectCoordinate(int number) {
        return number >= 0 && number < 3;
    }

    private static void switchPlayer() {
        currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
    }

    private static boolean checkWinner() {
        /*
        gameGrid
            0 1 2
            3 4 5
            6 7 8
        */

        // check rows
        for (int i = 0; i < 3; i++) {
            if (currentPlayer == gameGrid[i * 3] && currentPlayer == gameGrid[i * 3 + 1] && currentPlayer == gameGrid[i * 3 + 2]) {
                return true;
            }
        }

        // check columns
        for (int i = 0; i < 3; i++) {
            if (currentPlayer == gameGrid[i] && currentPlayer == gameGrid[i + 3] && currentPlayer == gameGrid[i + 6]) {
                return true;
            }
        }

        // check diagonals
        if (currentPlayer == gameGrid[0] && currentPlayer == gameGrid[4] && currentPlayer == gameGrid[8]) {
            return true;
        }

        if (currentPlayer == gameGrid[2] && currentPlayer == gameGrid[4] && currentPlayer == gameGrid[6]) {
            return true;
        }

        return false;
    }

    private static boolean isDraw() {
        for (char cell : gameGrid) {
            if (cell == ' ') {
                return false;
            }
        }

        return true;
    }

    static void game() {
        Scanner scanner = new Scanner(System.in);

        boolean isGameEnded = false;
        int row, column;

        gameGrid = setGameGrid();
        System.out.println(getGameGridOutput(gameGrid));

        while (!isGameEnded) {
            try {
                row = scanner.nextInt() - 1;
                column = scanner.nextInt() - 1;
                if (isCorrectCoordinate(row) && isCorrectCoordinate(column)) {
                    if (!isCellOccupied(row, column)) {
                        setCell(row, column, currentPlayer);
                        System.out.println(getGameGridOutput(gameGrid));

                        if (checkWinner()) {
                            System.out.println(currentPlayer + " wins");
                            isGameEnded = true;
                        } else if (isDraw()) {
                            System.out.println("Draw");
                            isGameEnded = true;
                        }

                        switchPlayer();
                    } else {
                        System.out.println(cellOccupiedMsg);
                        scanner.nextLine();
                    }
                } else {
                    System.out.println(wrongCoordinatesMsg);
                    scanner.nextLine();
                }
            } catch (InputMismatchException e) {
                System.out.println(wrongInputMsg);
                scanner.nextLine();
            }
        }
    }

    public static void main(String[] args) {
        game();
    }
}
