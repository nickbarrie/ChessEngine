import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class Main extends Application {

    private static final int TILE_SIZE = 100;
    private static final int BOARD_SIZE = 8;

    // Instance variables
    private GridPane grid = new GridPane();
    private Board board = new Board();

    private PieceType selectedPiece = null;
    private boolean isWhiteTurn = true;

    private int selectedRow = -1, selectedCol = -1;

    // Method to add a piece to the board dynamically
    private void addPieceToBoard(PieceType pieceType, int row, int col) {
        try{
            Image image = new Image(getClass().getResource(pieceType.getImagePath()).toExternalForm());
        }catch(Exception e){
            System.out.println("Error: " + e);
        }
        ImageView piece = new ImageView(getClass().getResource(pieceType.getImagePath()).toExternalForm());
        piece.setFitWidth(TILE_SIZE);
        piece.setFitHeight(TILE_SIZE);
        piece.setMouseTransparent(true);
        grid.add(piece, col, row);
    }

    // Method to check if a pawn move is valid
    private boolean isValidPawnMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is one square forward
        if (Math.abs(fromCol - toCol) == 0 && Math.abs(fromRow - toRow) == 1) {
            if(isWhiteTurn && toRow < fromRow || !isWhiteTurn && toRow > fromRow){
                return false;
            }
            if(board.getPieceAt(toRow, toCol) != null){
                return false;
            }
            return true;
        }

        // Check if the move is two squares forward
        if (Math.abs(fromCol - toCol) == 0 && Math.abs(fromRow - toRow) == 2) {
            if(!isWhiteTurn && fromRow != 1 || isWhiteTurn && fromRow != 1){
                return false;
            }
            if(board.getPieceAt(toRow, toCol) != null){
                return false;
            }
            return true;
        }

        return false;
    }

    // Method to check if a knight move is valid
    private boolean isValidKnightMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is an L-shape
        return (Math.abs(fromRow - toRow) == 2 && Math.abs(fromCol - toCol) == 1) ||
                (Math.abs(fromRow - toRow) == 1 && Math.abs(fromCol - toCol) == 2);
    }

    // Method to check if a bishop move is valid
    private boolean isValidBishopMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is diagonal
        return Math.abs(fromRow - toRow) == Math.abs(fromCol - toCol);
    }

    // Method to check if a rook move is valid
    private boolean isValidRookMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is vertical or horizontal
        return fromRow == toRow || fromCol == toCol;
    }

    // Method to check if a queen move is valid
    private boolean isValidQueenMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is diagonal, vertical, or horizontal
        return isValidBishopMove(fromRow, fromCol, toRow, toCol) || isValidRookMove(fromRow, fromCol, toRow, toCol);
    }

    // Method to check if a king move is valid
    private boolean isValidKingMove(int fromRow, int fromCol, int toRow, int toCol) {
        // Check if the move is one square in any direction
        return Math.abs(fromRow - toRow) <= 1 && Math.abs(fromCol - toCol) <= 1;
    }

    private boolean isValidMove(PieceType piece, int fromRow, int fromCol, int toRow, int toCol) {
        // chech if the tile is empty
        if (board.getPieceAt(toRow, toCol) != null) {
            if(isWhiteTurn && board.getPieceAt(toRow, toCol).isWhite() || !isWhiteTurn && !board.getPieceAt(toRow, toCol).isWhite()){
                return false;
            }
        }
        // Check if the move is within the board
        if (toRow < 0 || toRow >= BOARD_SIZE || toCol < 0 || toCol >= BOARD_SIZE) {
            return false;
        }

        // Check if the move is valid for the piece
        switch (piece) {
            case WHITE_PAWN:
                return isValidPawnMove(fromRow, fromCol, toRow, toCol);
            case BLACK_PAWN:
                return isValidPawnMove(fromRow, fromCol, toRow, toCol);
            case WHITE_KNIGHT:
                return isValidKnightMove(fromRow, fromCol, toRow, toCol);
            case BLACK_KNIGHT:
                return isValidKnightMove(fromRow, fromCol, toRow, toCol);
            case WHITE_BISHOP:
                return isValidBishopMove(fromRow, fromCol, toRow, toCol);
            case BLACK_BISHOP:
                return isValidBishopMove(fromRow, fromCol, toRow, toCol);
            case WHITE_ROOK:
                return isValidRookMove(fromRow, fromCol, toRow, toCol);
            case BLACK_ROOK:
                return isValidRookMove(fromRow, fromCol, toRow, toCol);
            case WHITE_QUEEN:
                return isValidQueenMove(fromRow, fromCol, toRow, toCol);
            case BLACK_QUEEN:
                return isValidQueenMove(fromRow, fromCol, toRow, toCol);
            case WHITE_KING:
                return isValidKingMove(fromRow, fromCol, toRow, toCol);
            case BLACK_KING:
                return isValidKingMove(fromRow, fromCol, toRow, toCol);
            default:
                return false;
        }
    }

    // Method to highlight valid moves for a selected piece
    private void highlightValidMoves(PieceType piece, int row, int col) {
        // Clear all previous highlights
       

        // Check for all possible moves
        for (int i = 0; i < BOARD_SIZE; i++) {
            for (int j = 0; j < BOARD_SIZE; j++) {
                if (isValidMove(piece, row, col, i, j)) {
                    Rectangle highlight = new Rectangle(TILE_SIZE, TILE_SIZE);
                    highlight.setMouseTransparent(true);
                    highlight.setFill(Color.rgb(0, 255, 0, 0.5)); // Green highlight
                    grid.add(highlight, j, i);
                }
            }


        }
        }

     // Handle square click to select and move pieces
     private void handleSquareClick(int row, int col) {
        // If no piece is selected, select the piece at the clicked square
        if (selectedPiece == null) {
            
                selectedPiece = board.getPieceAt(row, col); // Retrieve the selected piece
                if(isWhiteTurn && !selectedPiece.isWhite() || !isWhiteTurn && selectedPiece.isWhite()) {
                    selectedPiece = null;
                }
                selectedRow = row;
                selectedCol = col;
                System.out.println("Piece selected: " + selectedPiece);
                highlightValidMoves(selectedPiece, selectedRow, selectedCol);
        } else {
            // If a piece is selected, attempt to move it to the clicked square
            if (isValidMove(selectedPiece, selectedRow, selectedCol, row, col)) {
                board.movePiece(selectedPiece, selectedRow, selectedCol, row, col);
                System.out.println("Moved piece to: (" + row + ", " + col + ")");
                selectedPiece = null; // Deselect piece
                grid.getChildren().removeIf(node -> node instanceof Rectangle && ((Rectangle) node).getFill().equals(Color.rgb(0, 255, 0, 0.5)));
                isWhiteTurn = !isWhiteTurn; // Switch turns

                updateBoard();
            }
        }
    }

    // Update the board by adding pieces to their respective positions
    public void updateBoard() {
        grid.getChildren().removeIf(node -> node instanceof ImageView);
        board.printBoard();
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                // Check for all piece types
                for (PieceType pieceType : PieceType.values()) {
                    if (board.getPieceAt(row, col) == pieceType) {
                        addPieceToBoard(pieceType, row, col);
                        break; // Break after adding the piece to avoid adding multiple pieces to the same square
                    }
                }
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {

        // Create the chessboard with alternating colored squares
        for (int row = 0; row < BOARD_SIZE; row++) {
            for (int col = 0; col < BOARD_SIZE; col++) {
                Rectangle square = new Rectangle(TILE_SIZE, TILE_SIZE);
                if ((row + col) % 2 == 0) {
                    square.setFill(Color.WHITE); // Light square
                } else {
                    square.setFill(Color.GREEN); // Dark square
                }

                final int r = row, c = col;

                square.setOnMouseClicked(event -> handleSquareClick(r, c));
                grid.add(square, col, row);
            }
        }

        // Call updateBoard to add all pieces to the board
        updateBoard();

        // Set up the scene and stage
        Scene scene = new Scene(grid, TILE_SIZE * BOARD_SIZE, TILE_SIZE * BOARD_SIZE);
        primaryStage.setTitle("Chess Board");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
