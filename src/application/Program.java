package application;

import chess.ChessMatch;
import chess.ChessPiece;
import chess.ChessPosition;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        ChessMatch chessMatch = new ChessMatch();

        while (true) {//ele vai repetir infinitamente
            UI.printBoard(chessMatch.getPieces());
            System.out.println();
            System.out.print("Source: ");
            ChessPosition source = UI.readChessPosition(scan);

            System.out.print("Target: ");
            ChessPosition target = UI.readChessPosition(scan);

            ChessPiece capturedPiece = chessMatch.performChessMove(source, target);//movendo de origem para o destino
        }
    }
}
