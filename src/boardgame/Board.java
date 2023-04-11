package boardgame;

public class Board {
    private int rows;
    private int columns;
    private Piece[][] pieces;//matriz de peças

    public Board(int rows, int columns){
        if(rows < 1 || columns < 1){
            throw new BoardException("there must be at least 1 row and 1 column");
        }
        this.rows = rows;
        this.columns = columns;
        pieces = new Piece[rows][columns];
    }


    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    public Piece piece(int row, int column) {//retornando o objeto tipo "Piece" e o nome do metodo é "piece"
        if (!positionExists(row, column)) { //se essa posição não existe
            throw new BoardException("Position is not on the board");
        }
        return pieces[row][column];//o metodo vai me retornar a matriz
    }
    public Piece piece(Position position){//retornar a peça pela posição
        if (!positionExists(position)) {
            throw new BoardException("Position is not on the board");
        }
        return pieces[position.getRow()][position.getColumn()];
    }
    /*
    Colocando peças no tabuleiro
     */
    public void placePiece(Piece piece, Position position){
        //se já existe a peça nesta posição, eu não posso colocar outra
        if(thereIsAPiece(position)){
            throw new BoardException("There is a piece on position " + position);
        }
        //vai pro matriz de peças, percorre linhas e colunas e posiciona uma peça
        pieces[position.getRow()][position.getColumn()] = piece;
        piece.position = position; //posição não é mais nula
    }
    public Piece removePiece(Position position){
        //se essa posição não existe, eu vou lançar uma excessão
        if(!positionExists(position)){
            throw new BoardException("Position is not on the board");
        }
        /*
        Se a peça do tabuleiro nessa posição é igual a nulo,
        isso significa que não tem nenhuma peça nessa posição e vou retornar o valor null
         */
        if(piece(position) == null){
            return null;
        }
        /*
        Caso contrário, vou remover a peça do tabuleiro. auxis vai receber a peça que estiver
        no tabuleiro nessa posiçãonessa posiçâo
         */
        Piece aux = piece(position);
        aux.position = null; //posição aux vai ser null, ou seja, essa peça foi retirada do tabuleiro
        /*
        Vou acessar a patriz de peças e vou falar que essa matriz de peças na linha "position.getTow
        e na coluna position.getColumn vai receber nulo
         */
        pieces[position.getRow()][position.getColumn()] = null;
        return aux; //contém a peça que foi retirada
    }
    public boolean positionExists(int row, int column) {
        /*
        essa linha tem que ser maior ou igual a zero;
        essa linha tem que ser menor do que a altura ou qtd de linhas
         de tabuleiro
         */
        return (row >= 0 && row < rows) && (column >= 0 && column < columns);
    }
    //o método fala se esta posicao existe ou não
    public boolean positionExists(Position position){
        return positionExists(position.getRow(), position.getColumn());
    }
    /*
    testar se existe peça nessa posição
     */
    public boolean thereIsAPiece(Position position){
        if (!positionExists(position)) {
            throw new BoardException("Position is not on the board");
        }
        //testando se a peça nesta posicão é diferende de null
        return piece(position) != null;
    }
}
