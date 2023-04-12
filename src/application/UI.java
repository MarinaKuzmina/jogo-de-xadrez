package application;

import chess.ChessPiece;
import chess.ChessPosition;
import chess.Color;

import java.util.InputMismatchException;
import java.util.Scanner;

public class UI {

    // https://stackoverflow.com/questions/5762491/how-to-print-color-in-console-using-system-out-println

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public static void clearScreen(){
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scan) {//ler posição do chadrez
        try {
            String s = scan.nextLine();
            char column = s.charAt(0); //posição da coluna que comeca com zero
            int row = Integer.parseInt(s.substring(1));//recortar String apartir da posição 1
            return new ChessPosition(column, row);
        } catch (RuntimeException e) {
            throw new InputMismatchException("Invalid position (a1 até h8)");
        }
    }


    //imprimir o tabuleiro inteiro
    public static void printBoard(ChessPiece[][] pieces) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], false);//nenhuma peça é pra ter o fundo colorido
            }
            //dar uma quebra de linha
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + " ");
            for (int j = 0; j < pieces.length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            //dar uma quebra de linha
            System.out.println();
        }
        System.out.println("  a b c d e f g h");
    }
    //metodo auxiliar para imprimir uma peça

    /*
    private static void printPiece (ChessPiece piece){

        if(piece == null){ //se não tiver peça
            System.out.print("-");
        }else{
            System.out.print(piece);
        }
        System.out.print(" ");
    }
    */

    private static void printPiece(ChessPiece piece, boolean background) {//mudar cor do fundo dos movimentos possiveis
        if(background){
            System.out.print(ANSI_BLUE_BACKGROUND);
        }
        if (piece == null) { //se não tiver peça
            System.out.print("-" + ANSI_RESET);
        }
        else {
            if (piece.getColor() == Color.WHITE) {
                System.out.print(ANSI_WHITE + piece + ANSI_RESET);
            }
            else {
                System.out.print(ANSI_YELLOW + piece + ANSI_RESET);
            }
        }
        System.out.print(" ");
    }
}
