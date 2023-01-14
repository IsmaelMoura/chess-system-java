package boardgame;

public abstract class Piece {
    protected Position position;
    private final Board board;

    public Piece(Board board) {
        this.board = board;
        position = null;
    }

    protected Board getBoard() {
        return board;
    }

    public abstract boolean[][] possibleMoves();

    public boolean possibleMove(Position position) {
        return possibleMoves()[position.getRow()][position.getColumn()];
    }

    public boolean isThereAnyPossibleMove() {
        boolean[][] possibleMoves = possibleMoves();
        for (boolean[] row : possibleMoves) {
            for (int column = 0; column < possibleMoves.length; column++) {
                if (row[column]) {
                    return true;
                }
            }
        }
        return false;
    }
}
