/**
 * Created by xavi on 12/10/15.
 */
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
class TestingUnicode{
    public static void main (String[ ] args) throws
            UnsupportedEncodingException {
        String unicodeMessage = "\u2654"
                + // white king
                "\u2655"
                + // white queen
                "\u2656"
                + // white rook
                "\u2657"
                + // white bishop
                "\u2658"
                + // white knight
                "\u2659"
                + // white pawn
                "\n"
                +
                "\u265A"
                + // black king
                "\u265B"
                + // black queen
                "\u265C"
                + // black rook
                "\u265D"
                + // black bishop
                "\u265E"
                + // black knight
                "\u265F"
                + // black pawn
                "\n" ;
        PrintStream out = new PrintStream(System.out, true, "UTF-8");
        out.println(unicodeMessage);
    }

}