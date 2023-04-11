package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;

public abstract class ChessPiece extends Piece {

    private Color color;

    public ChessPiece(Board board, Color color) {
        super(board);
        this.color = color;
    }

    public Color getColor() {
        return color;
    }
    protected boolean isThereOpponentPiece(Position position){
        ChessPiece p = (ChessPiece)getBoard().piece(position);
        /*
        Para concluir que "p" é uma peça adversária, terei que testar se o "p" é
        diferente de nulo e diferente da cor da peça onde eu estou
         */
        return p != null && p.getColor() != color;
    }

}
