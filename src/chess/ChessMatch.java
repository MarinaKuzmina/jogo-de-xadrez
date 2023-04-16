package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChessMatch {
    private int turn;
    private Color currentPlayer;
    private Board board;//partida de xadrez tem o tabuleiro
    private boolean check;
    private List<Piece> piecesOnTheBoard = new ArrayList<>();;
    private List<Piece> capturedPieces = new ArrayList<>();

    public ChessMatch() {
        board = new Board(8, 8); //essa classe vai recever o tabuleiro 8x8
        turn = 1;
        currentPlayer = Color.WHITE;
        inicialSetup();
    }
    public int getTurn(){
        return turn;
    }
    public Color getCurrentPlayer(){
        return currentPlayer;
    }
    public boolean getCheck() {
        return check;
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
    public boolean[][] possibleMoves(ChessPosition sourcePosition){
        /*
        Convertemos essa posição de xadrez para uma posição de matriz normal
         */
        Position position = sourcePosition.toPosition();
        /*
        validamos posição de origem
         */
        validateSourcePosition(position);
        /*
        retornamos os movimentos possíveis dessa posição
         */
        return board.piece(position).possibleMoves();
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

        if(testCheck(currentPlayer)){
            undoMove(source, target, capturedPiece);
            throw new ChessException("You can not put yourself in check");
        }
        check = (testCheck(opponent(currentPlayer))) ? true : false;

        nextTurn();
        return (ChessPiece) capturedPiece;
    }
    private Piece makeMove(Position source, Position target){
        Piece p = board.removePiece(source);
        Piece capturedPiece = board.removePiece(target);
        board.placePiece(p, target);//colocando peça de origem para a posição do destino
        if(capturedPiece != null){
            piecesOnTheBoard.remove(capturedPiece);
            capturedPieces.add(capturedPiece);
        }
        return capturedPiece;
    }
    //metodo para desfazer o movimento
    private void undoMove(Position source, Position target, Piece capturedPiece){
        Piece p = board.removePiece(target);//remover a peça que foi movida para o destino
        board.placePiece(p, source);//devolver a peça para a posição de origem
        /*
        Se a peça foi capturada, tem que voltar à posição do destino
         */
        if(capturedPiece != null){
            board.placePiece(capturedPiece, target);
            /*
        Tirar a peça da lista de capturadas e colocá-la novamente na lista das peças no tabuleiro
         */
            capturedPieces.remove(capturedPieces);
            piecesOnTheBoard.add(capturedPiece);
        }
    }
    private Color opponent(Color color){
        return (color == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    //localizar rei de uma determinada cor, varrendo as peças do jogo
    private ChessPiece King(Color color){
        //procurar na lista de peças em jogo qual que é o rei dessa cor
        List<Piece> list = piecesOnTheBoard.stream().filter(x-> ((ChessPiece)x).getColor() == color).collect(Collectors.toList());
        for(Piece p : list){
            //se essa peça é uma instancia de Rei, significa que encontrei o Rei
            if(p instanceof King){
                return (ChessPiece)p;
            }
        }
        //se não encontrar nenhum Rei, vou lançar uma excessão
        throw new IllegalStateException("There is no " + color + " King on the Board");
    }
    private boolean testCheck(Color color){
        Position KingPosition = King(color).getChessPosition().toPosition();
        List<Piece> opponentPieces = piecesOnTheBoard.stream().filter(x-> ((ChessPiece)x).getColor() == opponent(color)).collect(Collectors.toList());
        //testar os movimentos possíveis do rei
        for(Piece p : opponentPieces){
            boolean[][] mat = p.possibleMoves();
            if(mat[KingPosition.getRow()][KingPosition.getColumn()]){
                return true;
            }
        }
        return false;
    }
    //esse método vai receber as coordenadas do xadrez
    private void placeNewPiece(char column, int row, ChessPiece piece){
        board.placePiece(piece, new ChessPosition(column, row).toPosition());
        piecesOnTheBoard.add(piece);
    }
    private void validateSourcePosition(Position position){
        if(!board.thereIsAPiece(position)){
            throw new ChessException("There is no piece on source position ");
        }
        if(currentPlayer != ((ChessPiece)board.piece(position)).getColor()){
            throw new ChessException("The chosen piece is not yours");
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
    private void nextTurn(){
        turn++;
        //mudar jogador atual (se agora ele é branco, vai ser preto; caso contrario, branco)
        currentPlayer = (currentPlayer == Color.WHITE) ? Color.BLACK : Color.WHITE;
    }
    /*
    esse metodo é responsavel por inicial a partida de xadrez,
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
