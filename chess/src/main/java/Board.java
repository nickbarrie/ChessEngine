

public class Board {
    long whitePawns;
    long whiteKnights;
    long whiteBishops;
    long whiteRooks;
    long whiteQueens;
    long whiteKing;
    long blackPawns;
    long blackKnights;
    long blackBishops;
    long blackRooks;
    long blackQueens;
    long blackKing;

    public Board() {
        whitePawns = 0x000000000000FF00L;
        whiteKnights = 0x0000000000000042L;
        whiteBishops = 0x0000000000000024L;
        whiteRooks = 0x0000000000000081L;
        whiteQueens = 0x0000000000000010L;
        whiteKing = 0x0000000000000008L;
        blackPawns = 0x00FF000000000000L;
        blackKnights = 0x4200000000000000L;
        blackBishops = 0x2400000000000000L;
        blackRooks = 0x8100000000000000L;
        blackQueens = 0x1000000000000000L;
        blackKing = 0x0800000000000000L;
        

       
    }

    public PieceType getPieceAt(int row, int col){
        if((whitePawns & (1L << (8 * row + col))) != 0){
            return PieceType.WHITE_PAWN;
        } else if((whiteKnights & (1L << (8 * row + col))) != 0){
            return PieceType.WHITE_KNIGHT;
        } else if((whiteBishops & (1L << (8 * row + col))) != 0){
            return PieceType.WHITE_BISHOP;
        } else if((whiteRooks & (1L << (8 * row + col))) != 0){
            return PieceType.WHITE_ROOK;
        } else if((whiteQueens & (1L << (8 * row + col))) != 0){
            return PieceType.WHITE_QUEEN;
        } else if((whiteKing & (1L << (8 * row + col))) != 0){
            return PieceType.WHITE_KING;
        } else if((blackPawns & (1L << (8 * row + col))) != 0){
            return PieceType.BLACK_PAWN;
        } else if((blackKnights & (1L << (8 * row + col))) != 0){
            return PieceType.BLACK_KNIGHT;
        } else if((blackBishops & (1L << (8 * row + col))) != 0){
            return PieceType.BLACK_BISHOP;
        } else if((blackRooks & (1L << (8 * row + col))) != 0){
            return PieceType.BLACK_ROOK;
        } else if((blackQueens & (1L << (8 * row + col))) != 0){
            return PieceType.BLACK_QUEEN;
        } else if((blackKing & (1L << (8 * row + col))) != 0){
            return PieceType.BLACK_KING;
        } else {
            return null;
        }
       
    }

    public void movePiece(PieceType piece, int startRow, int startCol, int endRow, int endCol){
        long startBit = 1L << (8 * startRow + startCol);
        long endBit = 1L << (8 * endRow + endCol);
        switch(piece){
            case WHITE_PAWN:
                whitePawns &= ~startBit;
                whitePawns |= endBit;
                break;
            case WHITE_KNIGHT:
                whiteKnights &= ~startBit;
                whiteKnights |= endBit;
                break;
            case WHITE_BISHOP:
                whiteBishops &= ~startBit;
                whiteBishops |= endBit;
                break;
            case WHITE_ROOK:
                whiteRooks &= ~startBit;
                whiteRooks |= endBit;
                break;
            case WHITE_QUEEN:
                whiteQueens &= ~startBit;
                whiteQueens |= endBit;
                break;
            case WHITE_KING:
                whiteKing &= ~startBit;
                whiteKing |= endBit;
                break;
            case BLACK_PAWN:
                blackPawns &= ~startBit;
                blackPawns |= endBit;
                break;
            case BLACK_KNIGHT:
                blackKnights &= ~startBit;
                blackKnights |= endBit;
                break;
            case BLACK_BISHOP:
                blackBishops &= ~startBit;
                blackBishops |= endBit;
                break;
            case BLACK_ROOK:
                blackRooks &= ~startBit;
                blackRooks |= endBit;
                break;
            case BLACK_QUEEN:
                blackQueens &= ~startBit;
                blackQueens |= endBit;
                break;
            case BLACK_KING:
                blackKing &= ~startBit;
                blackKing |= endBit;
                break;
        }
    }

    public boolean isPieceAt(int row, int col, long piece) {
        return (piece & (1L << (8 * row + col))) != 0;
    }


    public long getWhitePawns() {
        return whitePawns;
    }

    public long getBlackPawns() {
        return blackPawns;
    }

    public long getWhiteKnights() {
        return whiteKnights;
    }

    public long getBlackKnights() {
        return blackKnights;
    }

    public long getWhiteBishops() {
        return whiteBishops;
    }

    public long getBlackBishops() {
        return blackBishops;
    }

    public long getWhiteRooks() {
        return whiteRooks;
    }

    public long getBlackRooks() {
        return blackRooks;
    }

    public long getWhiteQueens() {
        return whiteQueens;
    }

    public long getBlackQueens() {
        return blackQueens;
    }

    public long getWhiteKing() {
        return whiteKing;
    }

    public long getBlackKing() {
        return blackKing;
    }


    

    public void printBoard() {
        for (int i = 0; i < 64; i++) {
            if ((whitePawns & (1L << i)) != 0) {
                System.out.print("P");
            } else if ((whiteKnights & (1L << i)) != 0) {
                System.out.print("N");
            } else if ((whiteBishops & (1L << i)) != 0) {
                System.out.print("B");
            } else if ((whiteRooks & (1L << i)) != 0) {
                System.out.print("R");
            } else if ((whiteQueens & (1L << i)) != 0) {
                System.out.print("Q");
            } else if ((whiteKing & (1L << i)) != 0) {
                System.out.print("K");
            } else if ((blackPawns & (1L << i)) != 0) {
                System.out.print("p");
            } else if ((blackKnights & (1L << i)) != 0) {
                System.out.print("n");
            } else if ((blackBishops & (1L << i)) != 0) {
                System.out.print("b");
            } else if ((blackRooks & (1L << i)) != 0) {
                System.out.print("r");
            } else if ((blackQueens & (1L << i)) != 0) {
                System.out.print("q");
            } else if ((blackKing & (1L << i)) != 0) {
                System.out.print("k");
            } else {
                System.out.print(".");
            }
            if ((i + 1) % 8 == 0) {
                System.out.println();
            }
        }

    }
}