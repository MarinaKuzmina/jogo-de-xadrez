package chess.pieces;

import boardgame.Board;
import boardgame.Position;
import chess.ChessPiece;
import chess.Color;

public class Queen extends ChessPiece {

    public Queen(Board board, Color color) {
        super(board, color);
    } //torre

    @Override
    public String toString() {
        return "Q";
    }


    @Override
    public boolean[][] possibleMoves() {
        /*
        a matriz temporario criada vai ter a mesma qtd de linhas e colunas que o tabuleiro
         */
        boolean[][] mat = new boolean[getBoard().getRows()][getBoard().getColumns()];//inicialmente, todoas as posissoes estão falsas
        Position p = new Position(0, 0);//posição auxiliar
        //acima da peça (na mesma linha, mas na coluna menos um)
        p.setValues(position.getRow()-1, position.getColumn());//posiçao da mesma peça menos um na linha dela
        /*
        enquanto a posição p existir e não tiver uma peça lá (enquanto a posição estiver vaga),
        marcar esta posição como verdadeira
         */
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            //mais pra cima ainda
            p.setRow(p.getRow()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // pra esquerda
        p.setValues(position.getRow(), position.getColumn()-1);//posiçao da mesma peça menos um na linha dela
        /*
        enquanto a posição p existir e não tiver uma peça lá (enquanto a posição estiver vaga),
        marcar esta posição como verdadeira
         */
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            //mais pra cima ainda
            p.setColumn(p.getColumn()-1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        // pra direita
        p.setValues(position.getRow(), position.getColumn()+1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            //mais pra direita ainda
            p.setColumn(p.getColumn()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        // pra baixo
        p.setValues(position.getRow()+1, position.getColumn());
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            //mais pra baixo ainda
            p.setRow(p.getRow()+1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }





        //noroeste
        p.setValues(position.getRow()-1, position.getColumn() - 1);//posiçao da mesma peça menos um na linha dela
        /*
        enquanto a posição p existir e não tiver uma peça lá (enquanto a posição estiver vaga),
        marcar esta posição como verdadeira
         */
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            //mais pra cima ainda
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }

        // nordeste
        p.setValues(position.getRow() - 1, position.getColumn() + 1);//posiçao da mesma peça menos um na linha dela
        /*
        enquanto a posição p existir e não tiver uma peça lá (enquanto a posição estiver vaga),
        marcar esta posição como verdadeira
         */
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            //mais pra cima ainda
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        // sudeste
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;

            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        // sudoeste
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        while(getBoard().positionExists(p) && !getBoard().thereIsAPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
            //mais pra baixo ainda
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        if(getBoard().positionExists(p) && isThereOpponentPiece(p)){
            mat[p.getRow()][p.getColumn()] = true;
        }
        return mat;
    }

}
