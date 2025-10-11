public class App {
    public static void main(String[] args) {
        int[][] board = new int[20][20];
        board[1][3] = 1;
        board[2][1] = 1;
        board[2][3] = 1;
        board[3][2] = 1;
        board[3][3] = 1;
        BoardGame bg = new BoardGame(board, 20);
        bg.printBoard();
    }
}
