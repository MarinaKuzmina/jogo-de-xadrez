package chess.pieces;

import boardgame.Board;
import chess.ChessPiece;
import chess.Color;

public class Rook extends ChessPiece {
    public Rook(Board board, Color color) {
        super(board, color);
    } //torre

    @Override
    public String toString() {
        return "R";
    }


    @Override
    public boolean[][] possibleMoves() {
        /*
        a matriz temporario criada vai ter a mesma qtd de linhas e colunas que o tabuleiro
         */
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];
        return mat;
    }
}
