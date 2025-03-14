public class BoardGame {

    private int[][] board;
    private int[][] neighbours;
    int size;

    public void printBoard() {
        for (int[] i : board) {
            for (int j : i) {
                if (j == 0) {
                    System.out.print("\u2588 ");
                } else {
                    System.out.print("\u2591 ");
                }
            }
            System.out.println();
            System.out.println();
        }
    }

    private int countNeighbours(int x, int y) {
        int neighbours = 0;
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                if (board[x + i][y + j] == 1) {
                    neighbours++;
                }
            }
        }

        if (board[x][y] == 1) {
            neighbours--;
        }

        return neighbours;
    }

    private void getNumberOfNeighbours() {
        for (int i = 1; i < board.length; i++) {
            for (int j = 1; j < board[i].length; j++) {
                neighbours[i][j] = countNeighbours(i, j);
            }
        }
    }

    private void changeNeighbours(int x, int y, int adder, int[][] neighbours) {
        for (int i = -1; i < 2; i++) {
            for (int j = -1; j < 2; j++) {
                neighbours[x + i][y + j]+=adder;
            }
        }

        neighbours[x][y] -=adder;
    }

    public BoardGame nextIteration() {
        int[][] bd = new int[size][size];
        int[][] newNeighbours = neighbours.clone();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (neighbours[i][j] == 2 || neighbours[i][j] == 3) {
                    if (board[i][j] == 0) {
                        bd[i][j] = 1;
                        changeNeighbours(i, j, 1, newNeighbours);
                    }
                } else if (board[i][j] == 1) {
                    bd[i][j] = 0;
                    changeNeighbours(i, j, -1, newNeighbours);
                }
            }
        }
        return new BoardGame(bd, size, newNeighbours);
    }

    public BoardGame(int[][] board, int size) {
        this.board = board.clone();
        this.size = size;
        neighbours = new int[size][size];
        getNumberOfNeighbours();
    }

    public BoardGame(int[][] board, int size, int[][] neighbours) {
        this.board = board.clone();
        this.size = size;
        this.neighbours = neighbours;
    }

}
