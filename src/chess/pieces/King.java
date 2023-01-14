package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class King extends ChessPiece {

    private final ChessMatch chessMatch;

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
        ChessPiece piece = (ChessPiece) getBoard().piece(position);
        return piece == null || piece.getColor() != getColor();
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleBoardSquares = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition = new Position(0, 0);

        // above
        auxPosition.setValues(position.getRow() - 1, position.getColumn());
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // below
        auxPosition.setValues(position.getRow() + 1, position.getColumn());
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // left
        auxPosition.setValues(position.getRow(), position.getColumn() - 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // right
        auxPosition.setValues(position.getRow(), position.getColumn() + 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // NW
        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // NE
        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // SW
        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // SE
        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
        if (getBoard().positionExists(auxPosition) && canMove(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // #special move castling
        if (getMoveCount() == 0 && !chessMatch.getCheck()) {
            // special move castling kingside rook
            Position rightRook = new Position(position.getRow(), position.getColumn() + 3);
            if (testRookCastling(rightRook)) {
                Position oneSquareToTheRight = new Position(position.getRow(), position.getColumn() + 1);
                Position twoSquaresToTheRight = new Position(position.getRow(), position.getColumn() + 2);
                if (getBoard().piece(oneSquareToTheRight) == null && getBoard().piece(twoSquaresToTheRight) == null) {
                    possibleBoardSquares[position.getRow()][position.getColumn() + 2] = true;
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
                    possibleBoardSquares[position.getRow()][position.getColumn() - 2] = true;
                }
            }
        }

        return possibleBoardSquares;
    }
}
