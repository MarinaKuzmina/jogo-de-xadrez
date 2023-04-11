package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
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
    public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition){ //sourcePosition - posição inicial, targetPosition - posição do destino
        //converter as duas posições nas posições da matriz
        Position source = sourcePosition.toPosition();
        Position target = targetPosition.toPosition();
        /*
        Antes de fazer o movimento, precisamos verificar se nessa posição de origem há uma peça;
        se ela não exixtir, o programa vai lançar uma excessão
         */
        validateSourcePosition(source);
        validateTargetPosition(source, target);
        Piece capturedPiece = makeMove(source, target);
        return (ChessPiece) capturedPiece;
    }
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);//colocando peça de origem para a posição do destino
        return capturedPiece;
    }
    //esse método vai receber as coordenadas do xadrez
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
    }
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position ");
        }
        if(!board.piece(position).isThereAnyPossibleMove()){
            throw new ChessException("There is no possible noves for the chosen piece ");
        }
    }
    private void validateTargetPosition(Position source, Position target){
        //se na posição de origigem não há um movimento possivel, não posso mover pra la
        if(!board.piece(source).possibleMove(target)){
            throw new ChessException("A peça escolhida não pode se mover para posição de destino");
        }
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

        placeNewPiece('c', 7, new Rook(board, Color.RED));
        placeNewPiece('c', 8, new King(board, Color.RED));
        placeNewPiece('d', 7, new King(board, Color.RED));
        placeNewPiece('e', 7, new Rook(board, Color.RED));
        placeNewPiece('e', 8, new King(board, Color.RED));
        placeNewPiece('d', 8, new King(board, Color.RED));
    }
    }
