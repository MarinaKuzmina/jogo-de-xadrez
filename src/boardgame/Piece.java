package boardgame;

public class Piece {
    protected Position position;
    private Board board;

    //para o construtor passamos apenas o tabuleiro pq a peça será como nula
    //indicando que essa peça não foi colocada no tabuleiro ainda
    public Piece(Board board){
        this.board = board;
        position = null;//mas não é necessaria esta linha, ela vem como padrão já
    }

    protected Board getBoard() {
        return board;
    }
    //não vamos permitir que o tabuleiro seja alterado
}
