package org.example;

public class Board {
    private Player[][] board;

    public Board(){
        board = new Player[3][3];
    }

    public Player[][] getBoard() {
        return board;
    }

    public void setBoard(Player[][] board) {
        this.board = board;
    }
    public Player getSquare(int[] location){
        return this.board[location[0]][location[1]];
    }

    public void displayBoard() {
        System.out.println();
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    line.append(" ");
                } else {
                    line.append(board[i][j]);
                }
                if(j < 2){
                    line.append(" | ");
                }
            }
            System.out.println(line);
            line.setLength(0);
            if(i < 2){
                System.out.println("--|---|---");
            }
        }
    }


    public boolean takeSquare(int row, int col, Player player) {
        // Check if move is valid
        if (row < 0 || row > 2 || col < 0 || col > 2) {
            return false;
        }
        //Check if space is occupied
        if (board[row][col] != null) {
            return false;
        }
        //Claim the space for the player
        board[row][col] = player;
        return true;
    }


    public boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin(Player player) {
        // Check rows
        for (int i = 0; i < 3; i++) {
            if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
                return true;
            }
        }

        // Check columns
        for (int j = 0; j < 3; j++) {
            if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
                return true;
            }
        }

        // Check diagonals
        if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            return true;
        }
        if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            return true;
        }
        return false;
    }
}
