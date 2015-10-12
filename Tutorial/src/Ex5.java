import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Ex5 {
    public enum Chessmen {
        // define an enum of chess piece types, and pass in their starting positions
        WHITE_KING(7, 4, -8, -8, false, false, "\u2654", "w"),
        WHITE_QUEEN(7, 3, -8, -8, false, false, "\u2655", "w"),
        WHITE_ROOK(7, 0, 7, 7, true, false, "\u2656", "w"),
        WHITE_BISHOP(7, 2, 7, 5, true, false, "\u2657", "w"),
        WHITE_KNIGHT(7, 1, 7, 6, true, false, "\u2658", "w"),
        WHITE_PAWN(6, 0, 0, 0, false, true, "\u2659", "w"),
        BLACK_ROOK(0, 0, 0, 7, true, false, "\u265C", "b"),
        BLACK_BISHOP(0, 2, 0, 5, true, false, "\u265D", "b"),
        BLACK_KNIGHT(0, 1, 0, 6, true, false, "\u265E", "b"),
        BLACK_KING(0, 4, -8, -8, false, false, "\u265A", "b"),
        BLACK_QUEEN(0, 3, -8, -8, false, false, "\u265B", "b"),
        BLACK_PAWN(1, 0, 0, 0, false, true, "\u265F", "b"),
        EMPTY(-8, -8, -8, -8, true, false, " ", "n");

        // make a constructor method
        private Chessmen(int A, int B, int C, int D, boolean twice, boolean pawn, String name, String col) {
            this.twice = twice;
            this.pawn = pawn;
            this.I = A;
            this.J = B;
            this.K = C;
            this.L = D;
            this.name = name;
            this.col = col;
        }

        // create a getter for position
        public int[] getPosition() {
            int[] arr = new int[4];
            arr[0] = I;
            arr[1] = J;
            arr[2] = K;
            arr[3] = L;
            return arr;
        }

        // create a getter for name
        public String getName(){
            return name;
        }

        // create a getter for colour
        public String getCol() { return col; }

        private final int I;
        private final int J;
        private final int K;
        private final int L;
        private final boolean twice;
        private final boolean pawn;
        private final String name;
        private final String col;

    }

    public static void main(String[] args) {
        Chessmen[][] chessboard = new Chessmen[8][8];

        ArrayList<String> capturedByBlack = new ArrayList<String>();
        ArrayList<String> capturedByWhite = new ArrayList<String>();

        for (Chessmen piece : Chessmen.values()) {
            int[] position = piece.getPosition();

            // populate board with pieces that appear twice (rooks, knights, bishops)
            if (piece.twice == true && piece.K >= 0 && piece.L >= 0) {
                chessboard[position[0]][position[1]] = piece;
                chessboard[position[2]][position[3]] = piece;
            }

            // populate board with pieces that appear once (kings, queens)
            if (piece.twice == false && piece.K < 0 && piece.L < 0) {
                chessboard[position[0]][position[1]] = piece;
            }
            // populate board with pawns
            if(piece.twice == false && piece.K == 0 && piece.L ==0){
                for(int i=0; i<8; i++){
                    chessboard[position[0]][i] = piece;
                }
            }
            // populate the remaining pieces with empties
            for(int i=0; i<8; i++){
                for(int j=0; j<8; j++){
                    if(chessboard[i][j] == null){
                        chessboard[i][j] = Chessmen.EMPTY;
                    }
                }
            }
        }

        boolean running = true;
        boolean whiteMove = true;

        Utilities u = new Utilities();
        Scanner sc = new Scanner(System.in);
        //initialize board
        drawBoard(chessboard);

        int turns = 0;

        while(running){
            // determine whose move it is (white begins)
            if(whiteMove) {System.out.println("White's move.");}else{System.out.println("Black's move.");}
            System.out.println("Please enter a move in the form <letter><number> to <letter><number>. For example: a3 to c6");
            String mv = sc.nextLine();
            //add in extra commands
            if(mv.equals("exit")) {
                running = false;
                System.exit(0);
            }else if(mv.equals("turns")) {
                System.out.printf("turns taken: %d", turns);
            }else {
                // extract the numeric information about the proposed move from the user inputted string
                int[][] stEd = u.parseMove(mv);
                // decide whether it's a legal move or not
                boolean legal = isItLegal(stEd, chessboard, whiteMove, turns);
                if (legal == true) {
                    move(stEd, chessboard);
                    drawBoard(chessboard);
                    whiteMove = !whiteMove;
                    System.out.println("__________________________________");
                } else {
                    System.out.println("that wasn't a legal move");
                }
            }

        }

    }

    // define a method to draw the chessboard to screen
    public static void drawBoard(Chessmen[][] board){

        System.out.println("\ta\tb\tc\td\te\tf\tg\th");

        for(int i=0; i<8; i++){
            String[] row = new String[8];
            System.out.printf("%d.\t", 8-i);
            for(int j=0; j<8; j++){
                row[j] = board[i][j].getName();
            }
            System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\n",
                    row[0], row[1], row[2], row[3], row[4], row[5], row[6], row[7]);
            System.out.print("\n");
        }
    }

    // define a method to move pieces
    public static void move(int[][] startEnd, Chessmen[][] board) {
        // the "selected piece" resides at the coordinates specified in the first element of StartEnd[][]
        // N.B. the second element of startEnd[][] gives the coordinates of the selected piece's destination.
        Chessmen selectedPiece = board[startEnd[0][0]][startEnd[0][1]];
        // find out if anything is at the selectedPiece's destination coordinate
        Chessmen overWrittenPiece = board[startEnd[1][0]][startEnd[1][1]];
        if(overWrittenPiece == Chessmen.EMPTY){
            System.out.println("");
        }else{
            String taken = overWrittenPiece.getName();
            System.out.printf("You took the opponent's %s", taken);
        }
        // place a piece of the type of selectedPiece at the destination address
        board[startEnd[1][0]][startEnd[1][1]] = selectedPiece;
        // at selectedPiece's origin, replace it with an EMPTY
        board[startEnd[0][0]][startEnd[0][1]] = Chessmen.EMPTY;
    }

    // define a method to decide whether a move is legal or not
    public static boolean isItLegal(int[][] startEnd, Chessmen[][] board, boolean wMove, int turns){
        boolean legal = false;
        Chessmen selectedPiece = board[startEnd[0][0]][startEnd[0][1]];
        int[] start = startEnd[0];
        int[] end = startEnd[1];
        Chessmen destinationPiece = board[end[0]][end[1]];
        ArrayList<Chessmen> path = new ArrayList<Chessmen>();

        switch(selectedPiece) {
            case BLACK_PAWN:
                // opening
                if (end[0] < 3 && end[1] == start[1]) {
                    legal = true;
                }
                // regular play
                else if (end[0] == start[0] + 1 && end[1] == start[1]) {
                    legal = true;
                }
                // taking an opponent's piece
                else if (end[0] == start[0] + 1 && Math.abs(end[1] - start[1]) == 1
                        && destinationPiece.getCol().equals("w")) {
                    legal = true;
                }
                break;
            case WHITE_PAWN:
                // opening
                if (end[0] > 4 && end[1] == start[1]) {
                    legal = true;
                //regular play
                }else if (end[0] == start[0] - 1) {
                    legal = true;
                }
                // taking an opponent's piece
                else if (end[0] == start[0] - 1 && Math.abs(end[1] - start[1]) == 1
                        && destinationPiece.getCol().equals("b")) {
                    legal = true;
                }
                break;
            case WHITE_ROOK:
                if (end[0] == start[0] || end[1] == start[1]) {
                    legal = true;
                }
                break;
            case BLACK_ROOK:
                if (end[0] == start[0] || end[1] == start[1]) {
                    legal = true;
                }
                break;
            case WHITE_BISHOP:
                if (Math.abs(end[0] - start[0]) == Math.abs(end[1] - start[1])) {
                    legal = true;
                }
                break;
            case BLACK_BISHOP:
                if (Math.abs(end[0] - start[0]) == Math.abs(end[1] - start[1])) {
                    legal = true;
                }
                break;
            case WHITE_KNIGHT:
                if ((Math.abs(end[0] - start[0]) == 2 && Math.abs(end[1] - start[1]) == 1)
                        || (Math.abs(end[0] - start[0]) == 1 && Math.abs(end[1] - start[1]) == 2)) {
                    legal = true;
                }
                break;
            case BLACK_KNIGHT:
                if ((Math.abs(end[0] - start[0]) == 2 && Math.abs(end[1] - start[1]) == 1)
                        || (Math.abs(end[0] - start[0]) == 1 && Math.abs(end[1] - start[1]) == 2)) {
                    legal = true;
                }
                break;
            case WHITE_QUEEN:
                if (Math.abs(end[0] - start[0]) == Math.abs(end[1] - start[1])) {
                    legal = true;
                } else if (end[0] == start[0] || end[1] == start[1]) {
                    legal = true;
                }
                break;
            case BLACK_QUEEN:
                if (Math.abs(end[0] - start[0]) == Math.abs(end[1] - start[1])) {
                    legal = true;
                } else if (end[0] == start[0] || end[1] == start[1]) {
                    legal = true;
                }
                break;
            case WHITE_KING:
                if (Math.abs(end[0] - start[0]) <= 1 || Math.abs(end[1] - start[1]) <= 1) {
                    legal = true;
                }
                break;
            case BLACK_KING:
                if (Math.abs(end[0] - start[0]) <= 1 || Math.abs(end[1] - start[1]) <= 1) {
                    legal = true;
                }
                break;
            default:
                legal=false;
        }

        // disallow players from stealing each others' moves
        String col = selectedPiece.getCol();
        if(wMove && col.equals("b")){legal = false;}
        if(!wMove && col.equals("w")){legal = false;}

        return legal;
    }
}

class Utilities {

    // define a map of letter (horizontal) addresses to java.array indices
    public static Map map() {
        Map letterToNumber = new HashMap();
        letterToNumber.put("a", 0);
        letterToNumber.put("b", 1);
        letterToNumber.put("c", 2);
        letterToNumber.put("d", 3);
        letterToNumber.put("e", 4);
        letterToNumber.put("f", 5);
        letterToNumber.put("g", 6);
        letterToNumber.put("h", 7);
        return letterToNumber;
    }

    // define a method to turn string representations of square addresses into java array indices
    // e.g. a8 -> 00; b6 -> 21 etc.
    public static int[] addressToIndex(String address) {
        Map m = map();
        String[] pair = address.split("");
        // convert the letter part of the address into a column index
        int int1 = (int) m.get(pair[0]);
        // convert the number part of the address into a row index
        int int2 = Integer.parseInt(pair[1]);
        int2 = 8 - int2;
        int[] out = {int2, int1};
        return out;
    }

    // define a method to take user commands (strings) and convert them into a form that the move() method can use
    public static int[][] parseMove(String move) {
        String startPositionString;
        String endPositionString;
        String delims = "[ ]+";
        String[] moveBits = new String[3];
        moveBits = move.split(delims);
        startPositionString = moveBits[0];
        endPositionString = moveBits[2];
        int[] startPosition = addressToIndex(startPositionString);
        int[] endPosition = addressToIndex(endPositionString);
        int[][] startEnd = {startPosition, endPosition};
        return startEnd;
    }
}