package boardgame;

public abstract class Piece {
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


public abstract boolean[][] possibleMoves();

/*
Testar se a peça pode mover para a determinada posi
 */
    public boolean possibleMove(Position position){
    //retornar possiveis movimentos na linha e na coluna
    return possibleMoves()[position.getRow()][position.getColumn()];
    }

    /*
    metodo para descobrir se a peça pode ter movomento; se ela não está travada
     */
    public boolean isThereAnyPossibleMove(){
        /*
        Vai ter que novamente chamar o metodo abstrato, e vai me retornar a matriz buleana,
        vai percorrer a matriz pra ver se existe uma posição verdadeira. Caso não existir,
        a peça não pode ser movimentada
    */
        boolean[][] mat = possibleMoves();
        for(int i=0; i<mat.length; i++){
            for(int j=0; j<mat.length; j++){
                if(mat[i][j]){//se a minha matriz na linha i e na coluna j for verdadeira, a posição existe
                    return true;
                }
            }
        }
        return false;
    }
}