package ru.dstu.laba3.models;

import java.util.Random;
public class TicTacToeModel {
    private char[][] board;
    private char currentPlayer;
    private char winner;

    public TicTacToeModel() {
        board = new char[3][3];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
        currentPlayer = 'X';
        winner = ' ';
    }

    public char[][] getBoard() {
        return board;
    }

    public char getCurrentPlayer() {
        return currentPlayer;
    }

    public char getWinner() {
        return winner;
    }

    public void makeMove(int row, int col) {
        if (board[row][col] == ' ' && winner == ' ') {
            board[row][col] = currentPlayer;
            if (checkWin(row, col)) {
                winner = currentPlayer;
            } else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    public void makeComputerMove() {
        Random random = new Random();
        int row, col;
        do {
            row = random.nextInt(3);
            col = random.nextInt(3);
        } while (board[row][col] != ' ');

        makeMove(row, col);
    }

    private boolean checkWin(int row, int col) {
        // Проверка выигрыша по горизонтали, вертикали и диагоналям
        return (board[row][0] == currentPlayer && board[row][1] == currentPlayer && board[row][2] == currentPlayer)
                || (board[0][col] == currentPlayer && board[1][col] == currentPlayer && board[2][col] == currentPlayer)
                || (row == col && board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer)
                || (row + col == 2 && board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer);
    }
}

