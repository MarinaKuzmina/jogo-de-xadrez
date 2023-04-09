package chess;

import boardgame.Board;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {
    private Board board;//partida de xadrez tem o tabuleiro

    public ChessMatch() {
        board = new Board(8, 8); //essa classe vai recever o tabuleiro 8x8
        inicialSetup();
    }

    /*
    esse método vai ter que retornar uma matriz de peças de chadrez correspondente
    a essa [ChessMatch] partida
     */
    public ChessPiece[][] getPieces() {
        ChessPiece[][] mat = new ChessPiece[board.getRows()][board.getColumns()]; //quantos linhas? A qtd de linhas de tabuleiro [board.gerRows()]
        /*
        vou percorrer a matriz de peças do tabuleiro e pra cada peça do meu tabuleiro, vou
        fazer downcasting pra ChessPiece
         */
        for(int i=0; i<board.getRows(); i++){
            for(int j=0; j<board.getColumns(); j++){
                mat[i][j] = (ChessPiece) board.piece(i, j);
            }
        }
        return mat;
    }
    //esse método vai receber as coordenadas do xadrez
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    /*
    esse metodo é responsavel por inicial a partidaq de xadrez,
    colocando peças no tabuleiro
     */
    private void inicialSetup() {
        //board.placePiece(new Rook(board, Color.WHITE), new Position(2, 1));
        //board.placePiece(new King(board, Color.BLACK), new Position(0, 4));
        //board.placePiece(new King(board, Color.WHITE), new Position(7, 4));

        placeNewPiece('c', 1, new Rook(board, Color.WHITE));
        placeNewPiece('c', 2, new King(board, Color.WHITE));
        placeNewPiece('d', 2, new King(board, Color.WHITE));
        placeNewPiece('e', 2, new Rook(board, Color.WHITE));
        placeNewPiece('e', 1, new King(board, Color.WHITE));
        placeNewPiece('d', 1, new King(board, Color.WHITE));

        placeNewPiece('c', 7, new Rook(board, Color.BLACK));
        placeNewPiece('c', 8, new King(board, Color.BLACK));
        placeNewPiece('d', 7, new King(board, Color.BLACK));
        placeNewPiece('e', 7, new Rook(board, Color.BLACK));
        placeNewPiece('e', 8, new King(board, Color.BLACK));
        placeNewPiece('d', 8, new King(board, Color.BLACK));
    }
    }
