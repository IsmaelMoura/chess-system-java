package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private ChessMatch chessMatch;

    public King(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    private boolean testRookCastling(Position position) {
        ChessPiece chessPiece = (ChessPiece) getBoard().piece(position);
        return chessPiece instanceof Rook
                && chessPiece.getColor() == getColor()
                && chessPiece.getMoveCount() == 0;
    }

    @Override
    public String toString() {
        return "K";
    }

    private boolean canMove(Position position) {
        ChessPiece p = (ChessPiece) getBoard().piece(position);
        return p == null || p.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position p = new Position(0, 0);

        // above
        p.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // below
        p.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // left
        p.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // right
        p.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // NW
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // NE
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SW
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // SE
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(p) && canMove(p)) {
            mat[p.getRow()][p.getColumn()] = true;
        }

        // #special move castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // special move castling kingside rook
            Position rightRook = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(rightRook)) {
                Position oneSquareToTheRight = new Position(position.getRow(), position.getColumn() + 1);
                Position twoSquaresToTheRight = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(oneSquareToTheRight) == null && getBoard().piece(twoSquaresToTheRight) == null) {
                    mat[position.getRow()][position.getColumn() + 2] = true;
                }
            }
            // special move castling queen side rook
            Position leftRook = new Position(position.getRow(), position.getColumn() - 4);
            if (testRookCastling(leftRook)) {
                Position oneSquareToTheLeft = new Position(position.getRow(), position.getColumn() - 1);
                Position twoSquaresToTheLeft = new Position(position.getRow(), position.getColumn() - 2);
                Position threeSquaresToTheLeft = new Position(position.getRow(), position.getColumn() - 3);
                if (getBoard().piece(oneSquareToTheLeft) == null
                                && getBoard().piece(twoSquaresToTheLeft) == null
                                && getBoard().piece(threeSquaresToTheLeft) == null
                ) {
                    mat[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return mat;
    }
}
