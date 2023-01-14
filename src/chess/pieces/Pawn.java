package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessMatch;
import chess.ChessPiece;
import chess.Color;

public class Pawn extends ChessPiece {

    private final ChessMatch chessMatch;

    public Pawn(Board board, Color color, ChessMatch chessMatch) {
        super(board, color);
        this.chessMatch = chessMatch;
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] possibleBoardSquares = new boolean[getBoard().getRows()][getBoard().getColumns()];

        Position auxPosition1 = new Position(0, 0);
        if (getColor() == Color.WHITE) {
            auxPosition1.setValues(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(auxPosition1) && !getBoard().thereIsAPiece(auxPosition1)) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            auxPosition1.setValues(position.getRow() - 2, position.getColumn());
            Position auxPosition2 = new Position(position.getRow() - 1, position.getColumn());
            if (getBoard().positionExists(auxPosition1)
                    && !getBoard().thereIsAPiece(auxPosition1)
                    && getBoard().positionExists(auxPosition2)
                    && !getBoard().thereIsAPiece(auxPosition2)
                    && getMoveCount() == 0
            ) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            auxPosition1.setValues(position.getRow() - 1, position.getColumn() - 1);
            if (getBoard().positionExists(auxPosition1) && isThereOpponentPiece(auxPosition1)) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            auxPosition1.setValues(position.getRow() - 1, position.getColumn() + 1);
            if (getBoard().positionExists(auxPosition1) && isThereOpponentPiece(auxPosition1)) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            // special move: en passant - WHITE pieces
            if (position.getRow() == 3) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left)
                        && isThereOpponentPiece(left)
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()
                ) {
                    possibleBoardSquares[left.getRow() - 1][left.getColumn()] = true;
                }
                
                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right)
                        && isThereOpponentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()
                ) {
                    possibleBoardSquares[right.getRow() - 1][right.getColumn()] = true;
                }
            }

        } else {
            auxPosition1.setValues(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(auxPosition1) && !getBoard().thereIsAPiece(auxPosition1)) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            auxPosition1.setValues(position.getRow() + 2, position.getColumn());
            Position auxPosition2 = new Position(position.getRow() + 1, position.getColumn());
            if (getBoard().positionExists(auxPosition1)
                    && !getBoard().thereIsAPiece(auxPosition1)
                    && getBoard().positionExists(auxPosition2)
                    && !getBoard().thereIsAPiece(auxPosition2)
                    && getMoveCount() == 0
            ) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            auxPosition1.setValues(position.getRow() + 1, position.getColumn() - 1);
            if (getBoard().positionExists(auxPosition1) && isThereOpponentPiece(auxPosition1)) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            auxPosition1.setValues(position.getRow() + 1, position.getColumn() + 1);
            if (getBoard().positionExists(auxPosition1) && isThereOpponentPiece(auxPosition1)) {
                possibleBoardSquares[auxPosition1.getRow()][auxPosition1.getColumn()] = true;
            }

            // special move: en passant - BLACK pieces
            if (position.getRow() == 4) {
                Position left = new Position(position.getRow(), position.getColumn() - 1);
                if (getBoard().positionExists(left)
                        && isThereOpponentPiece(left)
                        && getBoard().piece(left) == chessMatch.getEnPassantVulnerable()
                ) {
                    possibleBoardSquares[left.getRow() + 1][left.getColumn()] = true;
                }

                Position right = new Position(position.getRow(), position.getColumn() + 1);
                if (getBoard().positionExists(right)
                        && isThereOpponentPiece(right)
                        && getBoard().piece(right) == chessMatch.getEnPassantVulnerable()
                ) {
                    possibleBoardSquares[right.getRow() + 1][right.getColumn()] = true;
                }
            }
        }

        return possibleBoardSquares;
    }

    @Override
    public String toString() {
        return "P";
    }
}
