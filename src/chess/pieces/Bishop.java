package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Bishop extends ChessPiece {
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleBoardSquares = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition = new Position(0, 0);

        // NW
        auxPosition.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn() - 1);
        }
        if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // NE
        auxPosition.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() - 1, auxPosition.getColumn() + 1);
        }
        if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // SE
        auxPosition.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() + 1);
        }
        if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        // SW
        auxPosition.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (getBoard().positionExists(auxPosition) && !getBoard().thereIsAPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
            auxPosition.setValues(auxPosition.getRow() + 1, auxPosition.getColumn() - 1);
        }
        if (getBoard().positionExists(auxPosition) && isThereOpponentPiece(auxPosition)) {
            possibleBoardSquares[auxPosition.getRow()][auxPosition.getColumn()] = true;
        }

        return possibleBoardSquares;
    }
}
