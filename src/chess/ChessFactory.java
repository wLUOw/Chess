package chess;

public class ChessFactory {

    private ChessFactory() {}

    public static Chess create(String name, int player, int px) {
        if ("King".equals(name)) {
            return new King(player, px);
        } else if ("Queen".equals(name)) {
            return new Queen(player, px);
        } else if ("Bishop".equals(name)) {
            return new Bishop(player, px);
        } else if ("Knight".equals(name)) {
            return new Knight(player, px);
        } else if ("Rook".equals(name)) {
            return new Rook(player, px);
        } else if ("Pawn".equals(name)) {
            return new Pawn(player, px);
        }
        return null;
    }
}
