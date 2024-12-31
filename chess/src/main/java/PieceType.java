public enum PieceType {
    WHITE_PAWN("white_pawn.png"),
    BLACK_PAWN("black_pawn.png"),
    WHITE_KING("white_king.png"),
    BLACK_KING("black_king.png"),
    WHITE_QUEEN("white_queen.png"),
    BLACK_QUEEN("black_queen.png"),
    WHITE_ROOK("white_rook.png"),
    BLACK_ROOK("black_rook.png"),
    WHITE_BISHOP("white_bishop.png"),
    BLACK_BISHOP("black_bishop.png"),
    WHITE_KNIGHT("white_knight.png"),
    BLACK_KNIGHT("black_knight.png")
    ;

    private final String imagePath;

    PieceType(String imagePath) {
        this.imagePath = imagePath;
    }

    public boolean isWhite() {
        return this.name().startsWith("WHITE");
    }

    public String getImagePath() {
        return imagePath;
    }
}