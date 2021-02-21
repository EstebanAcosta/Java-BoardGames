package JavaBoardGames.Checkers;
import java.util.ArrayList;
/***
 * 
 * @author estebanacosta
 *
 */
public class AlphaBetaPlayer extends Player
{

    private static final int SEARCH_DEPTH = 7;

    private Checkers checkersGame = new Checkers();

    private Move bestMove;

    public AlphaBetaPlayer(PlayerType pt)
    {
        super(pt);
    }


    /**
     * Decides which move the AI should take
     * @param currentTileBoard
     * @return bestMove
     */
    public Move whichMove(Board currentBoard){

        System.out.println("α-β IS THINKING...");
        maxValue(currentBoard,Integer.MIN_VALUE ,Integer.MAX_VALUE,SEARCH_DEPTH); 

        return bestMove;
    }


    private int maxValue(Board currentBoard, int alpha, int beta, int depth) {

        if(depth == 0 || hasWon(currentBoard)) {

            return checkersGame.evaluationFunction(currentBoard, currentBoard.isBlackTurn(), currentBoard.isRedTurn());

        }

        //get all the legal moves this player can make
        ArrayList<Move> legalMoves = getAllLegalMoves(currentBoard);

        //loop through each move they can make
        for(Move move: legalMoves) {

            //execute that move on the tile board and get the resulting tile board back
            Board nextBoard = createNewBoard(currentBoard);

            makeMove(nextBoard, move,move.getPiece());

            switchSides(nextBoard);

            computeLegalMoves(nextBoard);

            //Make sure to make the current board's child be the new board that was just generated
            currentBoard.setNextBoard(nextBoard);


            //Make sure that the new board's original board is set to be the current board
            nextBoard.setLastBoard(currentBoard);

            int value = 0;

            value = minValue(nextBoard,alpha,beta,depth - 1);  

            //get the parent board of the new board (essentially undoing the move)
            nextBoard = undoMove(nextBoard);


            if(value > alpha) {

                alpha = value;

                if(depth == SEARCH_DEPTH) {
                    bestMove = move;
                }

            }

            if(alpha >= beta) {

                return alpha;

            }

        }

        return alpha;
    }


    private int minValue(Board currentBoard, int alpha, int beta, int depth) {

        if(depth == 0 || hasWon(currentBoard)) {

            return checkersGame.evaluationFunction(currentBoard, currentBoard.isBlackTurn() , currentBoard.isRedTurn());

        }

        //get all the legal moves this player can make
        ArrayList<Move> legalMoves = getAllLegalMoves(currentBoard);

        //loop through each move they can make
        for(Move move: legalMoves) {

            //execute that move on the tile board and get the resulting tile board back
            Board nextBoard = createNewBoard(currentBoard);

            makeMove(nextBoard, move,move.getPiece());

            switchSides(nextBoard);

            computeLegalMoves(nextBoard);

            //Make sure to make the current board's child be the new board that was just generated
            currentBoard.setNextBoard(nextBoard);


            //Make sure that the new board's original board is set to be the current board
            nextBoard.setLastBoard(currentBoard);

            int value = 0;


            value = maxValue(nextBoard,alpha,beta,depth - 1);  


            //get the parent board of the new board (essentially undoing the move)
            nextBoard = undoMove(nextBoard);


            if(value <=beta) {

                beta = value;

            }

            if(alpha >= beta) {

                return beta;

            }
        }

        return beta;

    }
    
    /**
     * Takes the currentBoard, creates a new Board from the current Board, executes move on the new board
     * @param currentBoard
     * @param move
     * @param piece
     * @return new board
     */
    private void makeMove(Board currentBoard, Move move, Piece piece) {

        Piece nextPiece = null ;

        if(currentBoard.isBlackTurn()) {
            for(Piece blackP : currentBoard.getBlackPlayer().getPieces()) {
                if(blackP.getCurrentTile().getRow() == piece.getCurrentTile().getRow() &&  
                blackP.getCurrentTile().getCol()== piece.getCurrentTile().getCol()) {

                    //and store the piece we are trying to find
                    nextPiece = blackP;

                }
            }
        }

        else {
            for(Piece redP : currentBoard.getRedPlayer().getPieces()) {
                if(redP.getCurrentTile().getRow() == piece.getCurrentTile().getRow() &&  
                redP.getCurrentTile().getCol()== piece.getCurrentTile().getCol()) {

                    //and store the piece we are trying to find
                    nextPiece = redP;

                }
            }
        }

        //execute the move on the new tile board
        executeMove(currentBoard, nextPiece, move);

    }

    private Board undoMove(Board currentBoard) {

        return currentBoard.getLastBoard();


    }

    /**
     * Take the current board and set the legal moves for both sides of the board 
     * @param currentBoard
     * @return the board
     */
    private void computeLegalMoves(Board currentBoard) {


        Tile [][] thisBoard = currentBoard.getTileBoard();

        checkersGame.setPlayersPiecesMoves(thisBoard, currentBoard.getBlackPlayer().getPieces());

        checkersGame.setPlayersPiecesMoves(thisBoard, currentBoard.getRedPlayer().getPieces());

        if(currentBoard.isBlackTurn()) {
            checkersGame.enforceMandatoryCapture(currentBoard.getBlackPlayer().getPieces());
        }

        else {
            checkersGame.enforceMandatoryCapture(currentBoard.getRedPlayer().getPieces());   
        }

    }

    /**
     * Retrieves all the legal moves that player can make and puts it into one big arraylist
     * @param currentBoard
     * @return all legal moves for that player
     */
    private  ArrayList<Move> getAllLegalMoves(Board currentBoard){

        //Create a new array list
        ArrayList<Move> allLegalMoves = new ArrayList<Move>();

        //if it's black's turn
        if(currentBoard.isBlackTurn()) {

            //get all the legal moves that black can take and put it into one big array list
            for(Piece blackPiece : currentBoard.getBlackPlayer().getPieces()) {
                allLegalMoves.addAll(blackPiece.getLegalMoves());
            }

        }

        //if it's red's turn
        else if(currentBoard.isRedTurn()){

            //get all the legal moves that redcan take and put it into one big array list
            for(Piece redPiece : currentBoard.getRedPlayer().getPieces()) {
                allLegalMoves.addAll(redPiece.getLegalMoves());
            }

        }


        return allLegalMoves;
    }


    /**
     * Checks to see if any of the two players in this board has won the game
     * @param currentBoard
     * @return
     */
    private boolean hasWon(Board currentBoard) {

        if(currentBoard.getBlackPlayer().getNumPieces() == 0) {

            return true;

        }
        else if(currentBoard.getRedPlayer().getNumPieces() == 0) {

            return true;
        }

        //count the # of total legal moves that the pieces each player has in total
        int blackTotalLegalMoves = 0, redTotalLegalMoves = 0;

        //loop through black pieces
        for(Piece bp : currentBoard.getBlackPlayer().getPieces() ) {
            //calculate how many total legal moves black has
            blackTotalLegalMoves += bp.getLegalMoves().size();
        }

        //loop through red pieces
        for(Piece rp : currentBoard.getRedPlayer().getPieces()) {
            //calculate how many total legal moves red has
            redTotalLegalMoves += rp.getLegalMoves().size();
        }

        //if either player can't make a legal move, then the game is over
        if(blackTotalLegalMoves == 0) {

            return true;
        }

        else if(redTotalLegalMoves == 0) {

            return true;
        }

        return false;
    }


    /**
     * Create a new board every time a move is going to be made
     * @param currentBoard
     * @return
     */
    private Board createNewBoard(Board currentBoard) {
        //create a new tile board
        Tile [][] newTileBoard = new Tile[8][8];

        Player newBlack =  new Player(PlayerType.BLACK);

        Player newRed = new Player(PlayerType.RED);

        boolean redTurn = currentBoard.isRedTurn();

        boolean blackTurn = currentBoard.isBlackTurn();


        //we are trying to make a deep copy of current board before we manipulate any of the pieces on the new board
        /*
         * First we take the current board's tile board and copy all the pieces that are on it onto the new tile board
         */

        //loop through the old board's tile board
        for(int row = 0; row < currentBoard.getTileBoard().length; row++) {
            for(int col = 0; col < currentBoard.getTileBoard()[row].length; col++) {
                //create a new tile object in every square of new tile board
                newTileBoard[row][col] = new Tile(row,col) ;
                //And copy everything from the old tile board onto the new tile board

                //if that tile from the old tile board is empty, set it to empty
                if(currentBoard.getTileBoard()[row][col].getOccupant() == null) {
                    newTileBoard[row][col].setOccupant(null);
                }
                //if that tile from the old tile board is occupied by a piece
                else {

                    Piece newPiece = null;

                    //Identify whose piece it is
                    if(currentBoard.getTileBoard()[row][col].getOccupant().getPlayer().getColor() == PlayerType.BLACK) {

                        //create a new tile with the current row and column
                        Tile newTile = new Tile(row,col);

                        //add that piece to the new player's list
                        newBlack.addPiece(newTile);

                        //if the piece belongs to black, create a new player black, and create a new tile with the current row and column #
                        newPiece = new Piece(newBlack, newTile);


                    }

                    else {

                        //create a new tile with the current row and column
                        Tile newTile = new Tile(row,col);

                        //add that piece to the new player's list                        
                        newRed.addPiece(newTile);

                        //if the piece belongs to red, create a new player red, and create a new tile with the current row and column#
                        newPiece = new Piece(newRed, newTile);
                    }

                    //set the occupant of the new tile board to the new piece
                    //this seemingly tedious process is to ensure that we are making a deep copy of the original board
                    //so that no change affects any of the other tile boards we are going to create
                    newTileBoard[row][col].setOccupant(newPiece); 
                }

            }
        }



        //create a new board with the new tile board, and the two new players
        return new Board(newTileBoard,newBlack, newRed,blackTurn,redTurn);

    }

    /**
     * Determines whose turn is it
     * @param currentBoard
     */
    private void switchSides(Board currentBoard) {

        boolean redTurn = currentBoard.isRedTurn();

        boolean blackTurn = currentBoard.isBlackTurn();

        blackTurn = !blackTurn;
        redTurn = !redTurn;

        currentBoard.setBlackTurn(blackTurn);
        currentBoard.setRedTurn(redTurn);

    }


    /**
     * Does the move on the imaginary board 
     * @param currentBoard
     * @param playerChoice
     * @param playerMove
     */
    private void executeMove(Board currentBoard, Piece playerChoice, Move playerMove)
    {
        Tile [][] board = currentBoard.getTileBoard();

        ArrayList<Piece> pieces = new ArrayList<Piece>();

        if(currentBoard.isBlackTurn()) {
            pieces = currentBoard.getBlackPlayer().getPieces();
        }

        else {
            pieces = currentBoard.getRedPlayer().getPieces();
        }

        //get the row and column # of the tile the piece is currently on
        int originalRow = playerChoice.getCurrentTile().getRow();
        int originalCol = playerChoice.getCurrentTile().getCol();

        //get the row and column # of the tile that the piece is going to be on
        int newRow = playerMove.getNewHome().getRow();
        int newCol = playerMove.getNewHome().getCol();


        //loop through the player's pieces
        for(Piece p : pieces) {

            //if the piece the player is trying to move is in the list of pieces
            if(p.getCurrentTile().getRow() == originalRow && p.getCurrentTile().getCol() == originalCol ) {

                //if this piece can jump over opponent's pieces
                if(playerMove.canEat()) {

                    //get the row and column # of the tile that the captured piece is on
                    int removedRow = playerMove.getRemovedHome().getRow();
                    int removedCol = playerMove.getRemovedHome().getCol();

                    Piece pieceToRemove = null;

                    //if the player whose piece is being removed is black
                    if(board[removedRow][removedCol].getOccupant().getPlayer().getColor() == PlayerType.BLACK) {
                        //loop through their pieces
                        for(Piece blackP : currentBoard.getBlackPlayer().getPieces()) {
                            //check to see if the soon to be removed piece exists in their list of pieces
                            if(blackP.getCurrentTile().getRow() == removedRow && blackP.getCurrentTile().getCol() == removedCol) {
                                //if it does exist, store it
                                pieceToRemove = blackP;

                            }

                        }

                        //now remove that piece from the player's list of pieces
                        currentBoard.getBlackPlayer().removePiece(pieceToRemove);
                    }

                    //if the player whose piece is being removed is red
                    else {

                        //loop through their pieces
                        for(Piece redP : currentBoard.getRedPlayer().getPieces()) {
                            //check to see if the soon to be removed piece exists in their list of pieces
                            if(redP.getCurrentTile().getRow() == removedRow && redP.getCurrentTile().getCol() == removedCol) {
                                //if it does exist, store it
                                pieceToRemove = redP;
                            }

                        }
                        //now remove that piece from the player's list of pieces
                        currentBoard.getRedPlayer().removePiece(pieceToRemove);

                    }


                    //set the tile the old piece was on to null
                    board[removedRow][removedCol].setOccupant(null);

                    //                    //print the result of the capture
                    //                    System.out.println(playerChoice.getPlayer().getColor() + " has captured opponent's piece on (" 
                    //                    + board[removedRow][removedCol].toString()  + ")");

                }

                //make sure that the new tile is occupied
                board[newRow][newCol].setOccupant(p);

                p.setCurrentTile(board[newRow][newCol]);

                //make sure that the tile the piece was last on is no longer occupied
                board[originalRow][originalCol].setOccupant(null);

                //print the result of their move
                //                System.out.println(playerChoice.getPlayer().getColor() + " has moved their piece from (" + board[originalRow][originalCol].toString()  +
                //                ") to (" + board[newRow][newCol].toString() + ")");

                break;

            }
        }

        currentBoard.setTileBoard(board);

    }
}
