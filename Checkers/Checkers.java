package JavaBoardGames.Checkers;

import java.util.ArrayList;
import java.util.Scanner;

/***
 * @author estebanacosta
 */
public class Checkers
{

    private Tile[][] Board = new Tile[8][8];

    public Player red;

    public AlphaBetaPlayer black;

    public static void main(String[] args)
    {
        Checkers c = new Checkers();
        c.setUp();
    }

    /***
     * Initializes the game and creates the board,
     * an 8 by 8 array of Tiles.
     */
    public void setUp()
    {

        for (int row = 0; row < Board.length; row++)
        {
            for (int col = 0; col < Board[row].length; col++)
            {
                Board[row][col] = new Tile(row, col);
            }
        }

        red = new Player(PlayerType.RED);

        for (int row = 0; row < 3; row++)
        {
            if (row == 1)
            {
                for (int col = 1; col < 8; col += 2)
                {
                    red.addPiece(Board[row][col]);
                }
            }
            else
            {
                for (int col = 0; col < 8; col += 2)
                {
                    red.addPiece(Board[row][col]);
                }
            }
        }

        black = new AlphaBetaPlayer(PlayerType.BLACK);

        for (int row = 5; row < 8; row++)
        {
            if (row == 6)
            {
                for (int col = 0; col < 8; col += 2)
                {
                    black.addPiece(Board[row][col]);
                }
            }
            else
            {
                for (int col = 1; col < 8; col += 2)
                {
                    black.addPiece(Board[row][col]);
                }
            }
        }

        // store the checker pieces that red has
        ArrayList<Piece> redPieces = red.getPieces();

        // loop through red pieces
        for (Piece redP : redPieces)
        {

            // make sure that the pieces on the board are occupying the tile
            Board[redP.getCurrentTile().getRow()][redP.getCurrentTile().getCol()].setOccupant(redP);

            if (redP.isKinged())
            {
                setLegalKingMoves(Board, Board[redP.getCurrentTile().getRow()][redP.getCurrentTile().getCol()].getOccupant());
            }

            else
            {
                // make sure that the pieces on the board have been given legal moves
                setLegalPieceMoves(Board, Board[redP.getCurrentTile().getRow()][redP.getCurrentTile().getCol()].getOccupant());
            }
        }

        // store the checker pieces that black has
        ArrayList<Piece> blackPieces = black.getPieces();

        // loop through black pieces
        for (Piece blackP : blackPieces)
        {

            // make sure that the pieces on the board are occupying the tile
            Board[blackP.getCurrentTile().getRow()][blackP.getCurrentTile().getCol()].setOccupant(blackP);

            if (blackP.isKinged())
            {
                setLegalKingMoves(Board, Board[blackP.getCurrentTile().getRow()][blackP.getCurrentTile().getCol()].getOccupant());
            }

            else
            {
                // make sure that the pieces on the board have been given legal moves
                setLegalPieceMoves(Board, Board[blackP.getCurrentTile().getRow()][blackP.getCurrentTile().getCol()].getOccupant());
            }
        }

        play();

    }

    /***
     * @return true if either Player has no more Pieces or either player can't make a legal move
     */
    public boolean endGame()
    {

        // if black or red doesn't have any pieces left, then the game is over
        // Display message of who's won the game
        if (black.getNumPieces() == 0)
        {
            System.out.println(red.getColor() + " HAS WON!!!!");

            displayBoard(Board);

            return true;
        }

        else if (red.getNumPieces() == 0)
        {
            System.out.println(black.getColor() + " HAS WON!!!!");
            displayBoard(Board);
            return true;
        }

        // get the pieces black has
        ArrayList<Piece> blackP = black.getPieces();

        // get the pieces red has
        ArrayList<Piece> redP = red.getPieces();

        // count the # of total legal moves that the pieces each player has in total
        int blackTotalLegalMoves = 0, redTotalLegalMoves = 0;

        // loop through black pieces
        for (Piece bp : blackP)
        {
            // calculate how many total legal moves black has
            blackTotalLegalMoves += bp.getLegalMoves().size();
        }

        // loop through red pieces
        for (Piece rp : redP)
        {
            // calculate how many total legal moves red has
            redTotalLegalMoves += rp.getLegalMoves().size();
        }

        // if either player can't make a legal move, then the game is over
        if (blackTotalLegalMoves == 0)
        {
            System.out.println(red.getColor() + " HAS WON!!!!");
            displayBoard(Board);
            return true;
        }

        else if (redTotalLegalMoves == 0)
        {
            System.out.println(black.getColor() + " HAS WON!!!!");
            displayBoard(Board);
            return true;
        }

        return false;
    }

    /**
     * Set the list of legal moves this player can take
     * @param pieces
     */
    public void setPlayersPiecesMoves(Tile[][] board, ArrayList<Piece> pieces)
    {

        for (Piece piece : pieces)
        {

            if (piece.isKinged())
            {
                setLegalKingMoves(board, piece);

            }

            else
            {
                // set the legal moves for black pieces
                setLegalPieceMoves(board, piece);
            }

        }

    }

    /***
     * While an end-game state is not reached, asks the Player whose turn it is for a move.
     * If that move is not a legal move, keeps asking until a legal move is given. Once a valid move is supplied,
     * the move is executed, and the turn is switched, giving the other Player a chance to move.
     */
    public void play()
    {

        // black always goes first
        // so set black turn to true
        // and red turn, by default false
        boolean blackTurn = true;
        boolean redTurn = false;

        // until we reach the end game (where either player has no more pieces)
        while (endGame() == false)
        {
            Scanner kbd = new Scanner(System.in);

            System.out.println();

            System.out.println("ScoreBoard: ");
            System.out.println();

            System.out.println("Red pieces");

            System.out.println(red.getNumPieces());
            System.out.println();

            System.out.println("Black pieces");
            System.out.println(black.getNumPieces());

            ArrayList<Piece> myPieces = new ArrayList<Piece>();

            if (blackTurn)
            {

                myPieces = black.getPieces();

                ArrayList<Piece> theirPieces = red.getPieces();

                // update the legal moves for both red and black player

                setPlayersPiecesMoves(Board, myPieces);

                setPlayersPiecesMoves(Board, theirPieces);

                // checks to see if a mandatory capture needs to enforced (pass it the current player's pieces)
                enforceMandatoryCapture(myPieces);

                // prints the board to the screen
                displayBoard(Board);

                System.out.println("It's " + black.getColor() + "'s turn ");

            }

            else
            {
                myPieces = red.getPieces();
                ArrayList<Piece> theirPieces = black.getPieces();

                setPlayersPiecesMoves(Board, myPieces);

                setPlayersPiecesMoves(Board, theirPieces);

                // checks to see if a mandatory capture needs to enforced (pass it the current player's pieces)
                enforceMandatoryCapture(myPieces);

                // prints the board to the screen
                displayBoard(Board);

                System.out.println("It's " + red.getColor() + "'s turn ");
            }

            if (blackTurn)
            {

                Board currentBoard = new Board(Board, black, red, blackTurn, redTurn);

                Move preferredMove = black.whichMove(currentBoard);

                executeMove(Board, preferredMove.getPiece(), preferredMove);
            }

            else
            {
                System.out.println();

                System.out.println("Enter which of your pieces you want to move");

                System.out.println();

                // keeps track if the piece was found
                boolean pieceFound = false;

                // keeps track if the piece can be moved from its current position
                boolean movablePiece = false;

                // this will store the player's piece they want to move
                Piece playerChoice = null;

                // ask the player for the row# and column#
                // keep asking them for the values until they give a legal row # and a legal column #
                // make sure that the player enters a movable piece (a.k.a. a piece that can move from its current position)
                while (pieceFound == false && movablePiece == false)
                {

                    System.out.println("Enter a legal row # (0 - 7) for the piece you are moving");

                    // get the row number
                    int row = kbd.nextInt();

                    while (row < 0 || row > 7)
                    {
                        System.out.println("Please enter a row # that is between 0 and 7 ");
                        row = kbd.nextInt();
                    }

                    System.out.println("Enter a legal column # (0 - 7) for the piece you are moving");

                    // get the column number
                    int col = kbd.nextInt();

                    while (col < 0 || col > 7)
                    {
                        System.out.println("Please enter a column # that is between 0 and 7");
                        col = kbd.nextInt();
                    }

                    // loop through the player's pieces
                    for (Piece p : myPieces)
                    {

                        // if the row & col # that was entered is equal to the row & col# of one of the pieces
                        // and if the piece can move from its current position
                        if (row == p.getCurrentTile().getRow() && col == p.getCurrentTile().getCol() &&
                        p.getLegalMoves().size() > 0)
                        {

                            // set both boolean values to true
                            pieceFound = true;
                            movablePiece = true;

                            // store the piece
                            playerChoice = p;

                            break;
                        }
                    }
                }

                // store the list of legal moves this specific piece can take
                ArrayList<Move> playerChoices = playerChoice.getLegalMoves();

                ArrayList<ArrayList<Move>> allJumpSequences = new ArrayList<ArrayList<Move>>();

                // This will store the move that the player is going to take
                Move whichMove = null;

                System.out.println();

                System.out.println("List of playable options: ");

                for (Move eachMove : playerChoices)
                {
                    System.out.println(eachMove.toString());
                }

                // makes sure that the move is legal
                boolean legalMove = false;

                System.out.println("Now please enter where you want to move that piece to");

                while (legalMove == false)
                {
                    System.out.println("Enter a legal row # (0 - 7) for the tile where you want to move your piece");

                    // get row #
                    int row = kbd.nextInt();

                    while (row < 0 || row > 7)
                    {
                        System.out.println("Please enter a row # that is between 0 and 7 ");
                        row = kbd.nextInt();
                    }

                    System.out.println("Enter a legal column # (0 - 7) for the tile where you want to move your piece");

                    // get col #
                    int col = kbd.nextInt();

                    while (col < 0 || col > 7)
                    {
                        System.out.println("Please enter a column # that is between 0 and 7 ");
                        col = kbd.nextInt();
                    }

                    // loop through the list of legal tiles this piece can land on
                    for (Move availableMove : playerChoices)
                    {
                        // if the row and col #'s entered are equal to one of the piece's moves
                        if (availableMove.getNewHome().getRow() == row && availableMove.getNewHome().getCol() == col)
                        {

                            whichMove = availableMove;

                            // set legal move to true and break
                            legalMove = true;

                            break;
                        }
                    }
                }

                // if the move that the player has taken involves a capture
                if (!playerChoice.isKinged() && whichMove.canEat())
                {
                    // generate and print all the jump sequences that can be made from this one move
                    allJumpSequences = printJumpSequences(Board, whichMove, playerChoice);

                    System.out.println("Which of these multiple jump sequences do you wish to execute: ");

                    int whichSequence = kbd.nextInt();

                    // continue prompting the user until user gives the program one of the options
                    while (whichSequence <= 0 || whichSequence > allJumpSequences.size())
                    {
                        System.out.println("Please enter a choice that is between 1  and " + allJumpSequences.size());
                        whichSequence = kbd.nextInt();

                    }
                    // store the jump sequence that the user selected
                    ArrayList<Move> favoriteJumpSequence = allJumpSequences.get(whichSequence - 1);

                    // loop through each move in their sequence
                    for (Move m : favoriteJumpSequence)
                    {
                        System.out.println("IT'S STILL " + playerChoice.getPlayer().getColor() + " 'S TURN");

                        // and execute that move
                        executeMove(Board, playerChoice, m);

                        displayBoard(Board);
                    }

                }

                // if this move doesn't involve a capture
                else
                {
                    // make sure that the piece is now placed on the new tile
                    executeMove(Board, playerChoice, whichMove);
                }
            }

            // Change black's turn and red's turn
            blackTurn = !blackTurn;
            redTurn = !redTurn;

        }
    }

    /***
     * If at least one of the pieces can capture another piece then make sure
     * that only those pieces that have an available capture to them can move.
     * This makes sure that no other piece can move except those pieces that can capture
     * a piece.
     * @param pieces
     */
    public void enforceMandatoryCapture(ArrayList<Piece> pieces)
    {

        boolean mustCapture = false;

        // loop through the player's pieces
        for (Piece p : pieces)
        {

            // get that piece's moves
            ArrayList<Move> moves = p.getLegalMoves();

            // loop through its moves
            for (Move m : moves)
            {
                // if this piece can jump over another piece (aka if it can eat another piece)
                if (m.canEat())
                {
                    // set this boolean value to true
                    mustCapture = true;
                    break;
                }
            }

        }

        // if at least one piece has to make a capture
        if (mustCapture)
        {
            // loop through the pieces
            for (Piece p : pieces)
            {
                // get that piece's moves
                ArrayList<Move> moves = p.getLegalMoves();

                // create a new list that will store the piece's new moves
                ArrayList<Move> newMoves = new ArrayList<Move>(moves);

                // loop through its old moves
                for (int i = 0; i < moves.size(); i++)
                {
                    Move removed = null;

                    // if this piece can't jump over another piece
                    if (moves.get(i).canEat() == false)
                    {

                        // get that legal move and store it
                        removed = moves.get(i);

                        // get rid of that move from the list of new moves
                        newMoves.remove(removed);

                    }
                }

                // set the legal new moves for that piece
                p.setLegalMoves(newMoves);

            }
        }
    }

    /***
     * This method checks to see if the piece that can make captures has other legal moves
     * If it does have other legal moves, this method will get rid of every other legal move
     * except for capture moves
     * @param legalMoves
     * @return
     */
    public ArrayList<Move> setUpdatedLegalMoves(ArrayList<Move> legalMoves)
    {

        // keeps track if this piece has to capture the opponent's piece
        boolean mustCapture = false;
        // loops through the list of legal moves
        for (Move m : legalMoves)
        {
            // set the value to true if there is one move that the piece can take that requires a capture
            if (m.canEat())
            {
                mustCapture = true;
            }
        }

        // if there is at least one move that the piece can take that requires a capture
        if (mustCapture == true)
        {

            ArrayList<Move> newLegalMoves = new ArrayList<Move>(legalMoves);

            // loop through its old moves
            for (int i = 0; i < legalMoves.size(); i++)
            {
                Move removed = null;

                // if this piece can't jump over another piece
                if (legalMoves.get(i).canEat() == false)
                {

                    // get that legal move and store it
                    removed = legalMoves.get(i);

                    // get rid of that move from the list of new moves
                    newLegalMoves.remove(removed);

                }
            }

            // make sure that the updated list of legal moves is stored in the original
            // legal moves array list
            legalMoves = newLegalMoves;

        }
        return legalMoves;
    }

    /***
     * @param piece
     *            Set the list of legal moves that the king can take
     * @return
     */
    public ArrayList<Move> setLegalKingMoves(Tile[][] board, Piece p)
    {

        ArrayList<Move> legalMoves = new ArrayList<Move>();

        int upperLeftRow = 0, upperLeftCol = 0, upperRightRow = 0, upperRightCol = 0;
        int lowerRightRow = 0, lowerRightCol = 0, lowerLeftRow = 0, lowerLeftCol = 0;
        int newUpperRightRow = 0, newUpperRightCol = 0, newUpperLeftRow = 0, newUpperLeftCol = 0;
        int newLowerLeftRow = 0, newLowerLeftCol = 0, newLowerRightRow = 0, newLowerRightCol = 0;

        // if it's red's king
        if (p.getPlayer().getColor() == PlayerType.RED)
        {
            lowerRightRow = p.getCurrentTile().getRow() - 1;
            lowerRightCol = p.getCurrentTile().getCol() + 1;

            lowerLeftRow = lowerRightRow;
            lowerLeftCol = p.getCurrentTile().getCol() - 1;

            upperLeftRow = p.getCurrentTile().getRow() + 1;
            upperLeftCol = p.getCurrentTile().getCol() - 1;

            upperRightRow = upperLeftRow;
            upperRightCol = p.getCurrentTile().getCol() + 1;

            // calculates the new upper right tile positions
            newUpperRightRow = upperRightRow + 1;
            newUpperRightCol = upperRightCol + 1;

            // calculates the new upper left tile positions
            newUpperLeftRow = upperLeftRow + 1;
            newUpperLeftCol = upperLeftCol - 1;

            // calculates the new lower right tile positions
            newLowerRightRow = lowerRightRow - 1;
            newLowerRightCol = lowerRightCol + 1;

            // calculates the new lower left tile positions
            newLowerLeftRow = lowerLeftRow - 1;
            newLowerLeftCol = lowerLeftCol - 1;
        }

        // if it's black's king
        else if (p.getPlayer().getColor() == PlayerType.BLACK)
        {
            lowerRightRow = p.getCurrentTile().getRow() + 1;
            lowerRightCol = p.getCurrentTile().getCol() - 1;

            lowerLeftRow = lowerRightRow;
            lowerLeftCol = p.getCurrentTile().getCol() + 1;

            upperLeftRow = p.getCurrentTile().getRow() - 1;
            upperLeftCol = p.getCurrentTile().getCol() + 1;

            upperRightRow = upperLeftRow;
            upperRightCol = p.getCurrentTile().getCol() - 1;

            // calculates the new upper right tile positions
            newUpperRightRow = upperRightRow - 1;
            newUpperRightCol = upperRightCol - 1;

            // calculates the new upper left tile positions
            newUpperLeftRow = upperLeftRow - 1;
            newUpperLeftCol = upperLeftCol + 1;

            // calculates the new lower right tile positions
            newLowerRightRow = lowerRightRow + 1;
            newLowerRightCol = lowerRightCol - 1;

            // calculates the new lower left tile positions
            newLowerLeftRow = lowerLeftRow + 1;
            newLowerLeftCol = lowerLeftCol + 1;
        }

        // if the king can't move in the lower left, lower right or upper left direction,
        if ((upperLeftRow < 0 || upperLeftRow > 7 || upperLeftCol < 0 || upperLeftCol > 7)
        && (lowerLeftRow < 0 || lowerLeftRow > 7 || lowerLeftCol < 0 || lowerLeftCol > 7)
        && (lowerRightRow < 0 || lowerRightRow > 7 || lowerRightCol < 0 || lowerRightCol > 7))
        {

            // we can assume that the king can only move is in the upper right direction
            Tile UpperRight = board[upperRightRow][upperRightCol];

            // if the king can move in the upper right direction
            if (p.canMoveTo(UpperRight))
            {
                // add it to the king's set of legal moves
                legalMoves.add(new Move(p, UpperRight, null, false));
            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (UpperRight.isOccupied() && p.canCapture(UpperRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperRightRow >= 0 && newUpperRightRow < 8 && newUpperRightCol >= 0 && newUpperRightCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperRightRow][newUpperRightCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, UpperRight, true));
                    }
                }

            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the king can't legally move in the lower left, lower right or upper right direction,
        else if ((upperRightRow < 0 || upperRightRow > 7 || upperRightCol < 0 || upperRightCol > 7)
        && (lowerLeftRow < 0 || lowerLeftRow > 7 || lowerLeftCol < 0 || lowerLeftCol > 7)
        && (lowerRightRow < 0 || lowerRightRow > 7 || lowerRightCol < 0 || lowerRightCol > 7))
        {

            // we can assume that the king can only move in the upper left direction
            Tile UpperLeft = board[upperLeftRow][upperLeftCol];

            // if the king can move in the upper left direction
            if (p.canMoveTo(UpperLeft))
            {
                // add it to the king's set of legal moves
                legalMoves.add(new Move(p, UpperLeft, null, false));

            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (UpperLeft.isOccupied() && p.canCapture(UpperLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperLeftRow >= 0 && newUpperLeftRow < 8 && newUpperLeftCol >= 0 && newUpperLeftCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperLeftRow][newUpperLeftCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, UpperLeft, true));

                    }
                }
            }

        }
        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the king can't legally move in the upper left, upper right or lower right direction,
        else if ((upperRightRow < 0 || upperRightRow > 7 || upperRightCol < 0 || upperRightCol > 7)
        && (upperLeftRow < 0 || upperLeftRow > 7 || upperLeftCol < 0 || upperLeftCol > 7)
        && (lowerRightRow < 0 || lowerRightRow > 7 || lowerRightCol < 0 || lowerRightCol > 7))
        {

            // we can assume that the king can only move in the lower left direction
            Tile LowerLeft = board[lowerLeftRow][lowerLeftCol];

            // add it to the king's set of legal moves
            if (p.canMoveTo(LowerLeft))
            {
                legalMoves.add(new Move(p, LowerLeft, null, false));
            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (LowerLeft.isOccupied() && p.canCapture(LowerLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerLeftRow >= 0 && newLowerLeftRow < 8 && newLowerLeftCol >= 0 && newLowerLeftCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerLeftRow][newLowerLeftCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerLeft, true));

                    }
                }
            }

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the king can't legally move in the upper left, upper right or lower left direction,
        else if ((upperRightRow < 0 || upperRightRow > 7 || upperRightCol < 0 || upperRightCol > 7)
        && (upperLeftRow < 0 || upperLeftRow > 7 || upperLeftCol < 0 || upperLeftCol > 7)
        && (lowerLeftRow < 0 || lowerLeftRow > 7 || lowerLeftCol < 0 || lowerLeftCol > 7))
        {

            // we can assume that the king can only move in the lower right direction
            Tile LowerRight = board[lowerRightRow][lowerRightCol];

            // if the king can move in the lower right direction
            if (p.canMoveTo(LowerRight))
            {
                // add it to the king's set of legal moves
                legalMoves.add(new Move(p, LowerRight, null, false));
            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (LowerRight.isOccupied() && p.canCapture(LowerRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerRightRow >= 0 && newLowerRightRow < 8 && newLowerRightCol >= 0 && newLowerRightCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerRightRow][newLowerRightCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerRight, true));
                    }
                }
            }

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the upper right row # and the upper right column # aren't in the bounds of board game
        // and if the lower right row # and the lower right column # aren't in the bounds of board game
        else if ((lowerRightRow < 0 || lowerRightRow > 7 || lowerRightCol < 0 || lowerRightCol > 7) &&
        (upperRightRow < 0 || upperRightRow > 7 || upperRightCol < 0 || upperRightCol > 7))
        {

            // we can assume that the king can move in the upper left direction and the lower left direction
            Tile LowerLeft = board[lowerLeftRow][lowerLeftCol];
            Tile UpperLeft = board[upperLeftRow][upperLeftCol];

            // add it to the king's set of legal moves
            if (p.canMoveTo(LowerLeft))
            {
                legalMoves.add(new Move(p, LowerLeft, null, false));
            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (LowerLeft.isOccupied() && p.canCapture(LowerLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerLeftRow >= 0 && newLowerLeftRow < 8 && newLowerLeftCol >= 0 && newLowerLeftCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerLeftRow][newLowerLeftCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerLeft, true));

                    }
                }
            }

            // if the king can move in the upper right direction
            if (p.canMoveTo(UpperLeft))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperLeft, null, false));
            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (UpperLeft.isOccupied() && p.canCapture(UpperLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperLeftRow >= 0 && newUpperLeftRow < 8 && newUpperLeftCol >= 0 && newUpperLeftCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperLeftRow][newUpperLeftCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, UpperLeft, true));

                    }
                }
            }

            // make sure that the only legal move this piece can take
            // (assuming at least one of those moves is a capture)
            // is a capture
            legalMoves = setUpdatedLegalMoves(legalMoves);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the upper left row # and the upper left column # aren't in the bounds of board game
        // and if the lower left row # and the lower left column # aren't in the bounds of board game
        else if ((lowerLeftRow < 0 || lowerLeftRow > 7 || lowerLeftCol < 0 || lowerLeftCol > 7)
        && (upperLeftRow < 0 || upperLeftRow > 7 || upperLeftCol < 0 || upperLeftCol > 7))
        {

            // we can assume that the king can move in the upper right direction and the lower right direction
            Tile LowerRight = board[lowerRightRow][lowerRightCol];
            Tile UpperRight = board[upperRightRow][upperRightCol];

            // if the king can move in the lower right direction
            if (p.canMoveTo(LowerRight))
            {
                // add it to the king's set of legal moves
                legalMoves.add(new Move(p, LowerRight, null, false));
            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (LowerRight.isOccupied() && p.canCapture(LowerRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerRightRow >= 0 && newLowerRightRow < 8 && newLowerRightCol >= 0 && newLowerRightCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerRightRow][newLowerRightCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerRight, true));
                    }
                }
            }

            // if the king can move in the upper right direction
            if (p.canMoveTo(UpperRight))
            {
                // add it to the king's set of legal moves
                legalMoves.add(new Move(p, UpperRight, null, false));
            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (UpperRight.isOccupied() && p.canCapture(UpperRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperRightRow >= 0 && newUpperRightRow < 8 && newUpperRightCol >= 0 && newUpperRightCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperRightRow][newUpperRightCol];

                    // if the tile the king is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, UpperRight, true));
                    }
                }
            }

            // make sure that the only legal move this piece can take
            // (assuming at least one of those moves is a capture)
            // is a capture
            legalMoves = setUpdatedLegalMoves(legalMoves);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the lower right row # and the lower left column # aren't in the bounds of board game
        // and if the lower left row # and the lower left column # aren't in the bounds of board game
        else if ((lowerLeftRow < 0 || lowerLeftRow > 7 || lowerLeftCol < 0 || lowerLeftCol > 7)
        && (lowerRightRow < 0 || lowerRightRow > 7 || lowerRightCol < 0 || lowerRightCol > 7))
        {

            // we can assume that the king can move in the upper right direction and the upper left direction
            Tile UpperRight = board[upperRightRow][upperRightCol];
            Tile UpperLeft = board[upperLeftRow][upperLeftCol];

            // if the king can move in the upper left direction
            if (p.canMoveTo(UpperLeft))
            {
                // add it to the king's set of legal moves
                legalMoves.add(new Move(p, UpperLeft, null, false));

            }

            // if this tile is occupied and the king can capture the piece that is occupying this specific tile
            if (UpperLeft.isOccupied() && p.canCapture(UpperLeft.getOccupant()))
            {
                // if the new row and column # are in the bounds of the board game
                if (newUpperLeftRow >= 0 && newUpperLeftRow < 8 && newUpperLeftCol >= 0 && newUpperLeftCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperLeftRow][newUpperLeftCol];

                    if (jump.isOccupied() == false)
                    {

                        legalMoves.add(new Move(p, jump, UpperLeft, true));

                    }
                }
            }

            // if the king can move in the upper right direction
            if (p.canMoveTo(UpperRight))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperRight, null, false));
            }

            // if this king can capture the piece that is occupying this specific tile
            if (UpperRight.isOccupied() && p.canCapture(UpperRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperRightRow >= 0 && newUpperRightRow < 8 && newUpperRightCol >= 0 && newUpperRightCol < 8)
                {

                    // this will figure out where this king is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperRightRow][newUpperRightCol];

                    if (jump.isOccupied() == false)
                    {

                        legalMoves.add(new Move(p, jump, UpperRight, true));

                    }
                }
            }

            // make sure that the only legal move this piece can take
            // (assuming at least one of those moves is a capture)
            // is a capture
            legalMoves = setUpdatedLegalMoves(legalMoves);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the king can't move in the upper left and upper right direction
        else if ((upperLeftRow < 0 || upperLeftRow > 7 || upperLeftCol < 0 || upperLeftCol > 7)
        && (upperRightRow < 0 || upperRightRow > 7 || upperRightCol < 0 || upperRightCol > 7))
        {

            // then the king can only move in the lower right direction and lower left direction
            Tile LowerLeft = board[lowerLeftRow][lowerLeftCol];
            Tile LowerRight = board[lowerRightRow][upperRightCol];

            // add it to the piece's set of legal moves
            if (p.canMoveTo(LowerLeft))
            {
                legalMoves.add(new Move(p, LowerLeft, null, false));
            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (LowerLeft.isOccupied() && p.canCapture(LowerLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerLeftRow >= 0 && newLowerLeftRow < 8 && newLowerLeftCol >= 0 && newLowerLeftCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerLeftRow][newLowerLeftCol];

                    // if the tile the piece is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerLeft, true));

                    }
                }
            }

            // if the piece can move in the lower right direction
            if (p.canMoveTo(LowerRight))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, LowerRight, null, false));
            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (LowerRight.isOccupied() && p.canCapture(LowerRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerRightRow >= 0 && newLowerRightRow < 8 && newLowerRightCol >= 0 && newLowerRightCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerRightRow][newLowerRightCol];

                    // if the tile the piece is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerRight, true));
                    }
                }
            }

            // make sure that the only legal move this piece can take
            // (assuming at least one of those moves is a capture)
            // is a capture
            legalMoves = setUpdatedLegalMoves(legalMoves);

        }

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        // if the piece can move in the bounds of the game
        else
        {
            // Create two tiles that represent the upper left and the upper right tile that piece can move in
            Tile UpperRight = board[upperRightRow][upperRightCol];
            Tile UpperLeft = board[upperLeftRow][upperLeftCol];
            Tile LowerLeft = board[lowerLeftRow][lowerLeftCol];
            Tile LowerRight = board[lowerRightRow][upperRightCol];

            // if the piece can move in the upper left direction
            if (p.canMoveTo(UpperLeft))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperLeft, null, false));
            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (UpperLeft.isOccupied() && p.canCapture(UpperLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperLeftRow >= 0 && newUpperLeftRow < 8 && newUpperLeftCol >= 0 && newUpperLeftCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperLeftRow][newUpperLeftCol];

                    if (jump.isOccupied() == false)
                    {

                        legalMoves.add(new Move(p, jump, UpperLeft, true));

                    }
                }
            }

            // if the piece can move in the upper right direction
            if (p.canMoveTo(UpperRight))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperRight, null, false));
            }

            // if this piece can capture the piece that is occupying this specific tile
            if (UpperRight.isOccupied() && p.canCapture(UpperRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperRightRow >= 0 && newUpperRightRow < 8 && newUpperRightCol >= 0 && newUpperRightCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperRightRow][newUpperRightCol];

                    if (jump.isOccupied() == false)
                    {

                        legalMoves.add(new Move(p, jump, UpperRight, true));

                    }
                }
            }

            // add it to the piece's set of legal moves
            if (p.canMoveTo(LowerLeft))
            {
                legalMoves.add(new Move(p, LowerLeft, null, false));
            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (LowerLeft.isOccupied() && p.canCapture(LowerLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerLeftRow >= 0 && newLowerLeftRow < 8 && newLowerLeftCol >= 0 && newLowerLeftCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerLeftRow][newLowerLeftCol];

                    // if the tile the piece is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerLeft, true));

                    }
                }
            }

            // if the piece can move in the lower right direction
            if (p.canMoveTo(LowerRight))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, LowerRight, null, false));
            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (LowerRight.isOccupied() && p.canCapture(LowerRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newLowerRightRow >= 0 && newLowerRightRow < 8 && newLowerRightCol >= 0 && newLowerRightCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newLowerRightRow][newLowerRightCol];

                    // if the tile the piece is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, LowerRight, true));
                    }
                }
            }

            // make sure that the only legal move this piece can take
            // (assuming at least one of those moves is a capture)
            // is a capture

            legalMoves = setUpdatedLegalMoves(legalMoves);

        }

        // set the king's list of legal moves
        p.setLegalMoves(legalMoves);

        return legalMoves;
    }

    /***
     * @param piece
     *            Set the list of legal moves a regular piece can take
     * @return
     */
    public ArrayList<Move> setLegalPieceMoves(Tile[][] board, Piece p)
    {

        ArrayList<Move> legalMoves = new ArrayList<Move>();
        int upperLeftRow = 0, upperLeftCol = 0, upperRightRow = 0, upperRightCol = 0;
        int newUpperRightRow = 0, newUpperRightCol = 0, newUpperLeftRow = 0, newUpperLeftCol = 0;

        // if it's red's piece
        if (p.getPlayer().getColor() == PlayerType.RED)
        {

            upperLeftRow = p.getCurrentTile().getRow() + 1;
            upperLeftCol = p.getCurrentTile().getCol() - 1;

            upperRightRow = upperLeftRow;
            upperRightCol = p.getCurrentTile().getCol() + 1;

            // calculates the new upper right tile positions
            newUpperRightRow = upperRightRow + 1;
            newUpperRightCol = upperRightCol + 1;

            // calculates the new upper left tile positions
            newUpperLeftRow = upperLeftRow + 1;
            newUpperLeftCol = upperLeftCol - 1;

        }

        // if it's black's piece
        else if (p.getPlayer().getColor() == PlayerType.BLACK)
        {

            upperLeftRow = p.getCurrentTile().getRow() - 1;
            upperLeftCol = p.getCurrentTile().getCol() + 1;

            upperRightRow = upperLeftRow;
            upperRightCol = p.getCurrentTile().getCol() - 1;

            // calculates the new upper right tile positions
            newUpperRightRow = upperRightRow - 1;
            newUpperRightCol = upperRightCol - 1;

            // calculates the new upper left tile positions
            newUpperLeftRow = upperLeftRow - 1;
            newUpperLeftCol = upperLeftCol + 1;

        }

        // if the piece is on a tile where it can't move upper left or upper right, then the piece is at the other end of the board
        // if the piece is at the other end of the board, the piece will be upgraded to a king
        if (((upperLeftRow < 0 || upperLeftRow > 7) || upperLeftCol < 0 || upperLeftCol > 7) &&
        (upperRightRow < 0 || upperRightRow > 7 || upperRightCol < 0 || upperRightCol > 7))
        {

            // upgrade this piece to a king
            p.king();

            // get all the moves that this newly kinged piece can make
            setLegalKingMoves(board, p);

            // add these moves to the pieces' list of legal moves
            legalMoves.addAll(p.getLegalMoves());

        }

        // if the upper left row # and the upper left column # aren't in the bounds of board game
        else if (upperLeftRow < 0 || upperLeftRow > 7 || upperLeftCol < 0 || upperLeftCol > 7)
        {

            // we can assume that the piece can only move is in the upper right direction
            Tile UpperRight = board[upperRightRow][upperRightCol];

            // if the piece can move in the upper right direction
            if (p.canMoveTo(UpperRight))
            {

                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperRight, null, false));
            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (UpperRight.isOccupied() && p.canCapture(UpperRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperRightRow >= 0 && newUpperRightRow < 8 && newUpperRightCol >= 0 && newUpperRightCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperRightRow][newUpperRightCol];

                    // if the tile the piece is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, UpperRight, true));

                    }
                }
            }

        }

        // if the upper right row and upper right column number aren't in the bounds of board game
        else if (upperRightRow < 0 || upperRightRow > 7 || upperRightCol < 0 || upperRightCol > 7)
        {

            // we can assume that the piece can only move in the upper left direction
            Tile UpperLeft = board[upperLeftRow][upperLeftCol];

            // if the piece can move in the upper left direction
            if (p.canMoveTo(UpperLeft))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperLeft, null, false));

            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (UpperLeft.isOccupied() && p.canCapture(UpperLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperLeftRow >= 0 && newUpperLeftRow < 8 && newUpperLeftCol >= 0 && newUpperLeftCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperLeftRow][newUpperLeftCol];

                    // if the tile the piece is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, UpperLeft, true));

                    }
                }
            }

        }

        // if the piece can move in the bounds of the game
        else
        {

            // Create two tiles that represent the upper left and the upper right tile that piece can move in
            Tile UpperRight = board[upperRightRow][upperRightCol];
            Tile UpperLeft = board[upperLeftRow][upperLeftCol];

            // if the piece can move in the upper left direction
            if (p.canMoveTo(UpperLeft))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperLeft, null, false));
            }

            // if this tile is occupied and the piece can capture the piece that is occupying this specific tile
            if (UpperLeft.isOccupied() && p.canCapture(UpperLeft.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperLeftRow >= 0 && newUpperLeftRow < 8 && newUpperLeftCol >= 0 && newUpperLeftCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperLeftRow][newUpperLeftCol];

                    // if the tile the piece is going to land on after the jump isn't occupied
                    if (jump.isOccupied() == false)
                    {

                        // add this tile to the list of legal moves
                        legalMoves.add(new Move(p, jump, UpperLeft, true));

                    }
                }
            }

            // if the piece can move in the upper right direction
            if (p.canMoveTo(UpperRight))
            {
                // add it to the piece's set of legal moves
                legalMoves.add(new Move(p, UpperRight, null, false));
            }

            // if this piece can capture the piece that is occupying this specific tile
            if (UpperRight.isOccupied() && p.canCapture(UpperRight.getOccupant()))
            {

                // if the new row and column # are in the bounds of the board game
                if (newUpperRightRow >= 0 && newUpperRightRow < 8 && newUpperRightCol >= 0 && newUpperRightCol < 8)
                {

                    // this will figure out where this piece is going to be after it captures the opponent's piece
                    Tile jump = board[newUpperRightRow][newUpperRightCol];

                    if (jump.isOccupied() == false)
                    {

                        legalMoves.add(new Move(p, jump, UpperRight, true));

                    }
                }
            }

            // make sure that the only legal move this piece can take
            // (assuming at least one of those moves is a capture)
            // is a capture
            legalMoves = setUpdatedLegalMoves(legalMoves);

        }

        // set the piece's list of legal moves
        p.setLegalMoves(legalMoves);

        return legalMoves;
    }

    /**
     * Print all possible jump sequences
     * @param Board
     * @param startJump
     * @param playerChoice
     * @return
     */
    private ArrayList<ArrayList<Move>> printJumpSequences(Tile[][] Board, Move startJump, Piece playerChoice)
    {

        ArrayList<ArrayList<Move>> allJumpSequences = new ArrayList<ArrayList<Move>>();

        // create an empty move array list that will store every jump sequence for this piece
        ArrayList<Move> jumpSequences = new ArrayList<Move>();

        // Store the starting jump
        Move startingMove = startJump;

        System.out.println();
        System.out.println("These are all the jump sequences from the starting position:");

        // look through all possible jump sequences from the starting jump position
        getJumpSequences(Board, new Piece(playerChoice.getPlayer(), startJump.getNewHome()), startingMove, jumpSequences);

        int count = 1;

        // loop through the jump sequences
        for (Move lastJump : jumpSequences)
        {

            // create an array list that will store each jump sequence
            ArrayList<Move> jumpSeq = new ArrayList<Move>();

            // since the jump sequence doesn't contain the piece's current position
            // print that out first
            System.out.print(count + " : " + playerChoice.getCurrentTile().toString());

            // Print the starting jump position
            System.out.print(" -> " + startJump.toString());

            // add that to this specific jump sequence
            jumpSeq.add(startJump);

            // loop through each in between jump sequence
            for (Move jumps : lastJump.getJumps())
            {

                // and print the jumps in the sequence
                if (!jumps.equals(startJump))
                {
                    System.out.print(" -> " + jumps.toString());

                    // add each jump to this specific sequence
                    jumpSeq.add(jumps);
                }
            }

            // if the last jump happens to be the first jump (which means that there is one jump move in this sequence)
            if (!lastJump.equals(startJump))
            {
                // print the last move of the sequence
                System.out.println(" -> " + lastJump.toString());

                // add the last jump to the jump sequence
                jumpSeq.add(lastJump);

            }

            // add this jump sequence to the list of jump sequences
            allJumpSequences.add(jumpSeq);

            count++;

        }
        System.out.println();

        return allJumpSequences;
    }

    /***
     * Finds all possible jump sequences
     * @param jump
     * @param p
     */
    public void getJumpSequences(Tile[][] board, Piece p, Move nextJump, ArrayList<Move> moves)
    {

        // give this piece a set of new legal moves

        if (p.isKinged())
        {

            setLegalKingMoves(board, p);

        }

        else
        {

            setLegalPieceMoves(board, p);
        }

        // get the moves that this piece can take
        ArrayList<Move> legalMoves = p.getLegalMoves();

        // make a copy of this piece's list of moves
        ArrayList<Move> onlyMoves = new ArrayList<Move>(legalMoves);

        // loop through the original and determine if there are moves that have no captures
        for (Move m : legalMoves)
        {

            // if there are moves that don't involve a capture, remove them
            if (m.canEat() == false)
            {
                onlyMoves.remove(m);
            }
        }

        // set this piece's legal moves to the copy's legal moves (which should contain only captures)
        p.setLegalMoves(onlyMoves);

        // stores the piece's attack moves
        ArrayList<Move> currentJumps = p.getLegalMoves();

        // if there are no more available attack moves
        if (currentJumps.isEmpty())
        {

            // add the last jump move
            moves.add(nextJump);

            return;
        }

        // look through each possible jump that can be made from the current tile position
        for (Move possibleJump : currentJumps)
        {

            // set the new piece to be on the next possible jump tile
            Piece newPiece = new Piece(p.getPlayer(), possibleJump.getNewHome());

            // make a new jump move that stores the next possible jump that can be made from the current position
            Move newJump = new Move(newPiece, possibleJump.getNewHome(), possibleJump.getRemovedHome(), possibleJump.canEat());

            // Add the jumps that comes from the current tile position
            newJump.addJumps(nextJump);

            getJumpSequences(board, newPiece, newJump, moves);

        }
    }

    /***
     * Prints the checker board on the console and on the GUI
     */
    public void displayBoard(Tile[][] board)
    {
        // print the board to the screen

        System.out.println();
        System.out.print("    |");
        for (int i = 0; i < 8; i++)
        {
            System.out.print("   " + i + " |");
        }
        System.out.println();
        System.out.println("    -------------------------------------------------");

        for (int r = board.length - 1; r >= 0; r--)
        {
            System.out.print(" " + r + "  |");
            for (int c = 0; c < 8; c++)
            {
                if (board[r][c].isOccupied())
                {

                    if (board[r][c].getOccupant().getPlayer().getColor() == PlayerType.RED)
                    {
                        if (board[r][c].getOccupant().getCheckerType() == CheckerType.RED_REGULAR)
                        {
                            System.out.print("  R  |");
                        }
                        else
                        {
                            System.out.print("  RK |");
                        }
                    }

                    else if (board[r][c].getOccupant().getPlayer().getColor() == PlayerType.BLACK)
                    {

                        if (board[r][c].getOccupant().getCheckerType() == CheckerType.BLACK_REGULAR)
                        {
                            System.out.print("  B  |");
                        }
                        else
                        {
                            System.out.print("  BK |");
                        }
                    }
                }

                else
                {
                    System.out.print("     |");
                }
            }
            System.out.println();
            System.out.println("-----------------------------------------------------");
        }
    }

    /**
     * This function will calculate the rating of each board
     */
    public int evaluationFunction(Board currentBoard, boolean blackTurn, boolean redTurn)
    {

        int redScore = 0;
        int blackScore = 0;

        // loop through black's pieces
        for (Piece blackPieces : currentBoard.getBlackPlayer().getPieces())
        {
            // if the piece is a king
            if (blackPieces.isKinged())
            {
                // add two points
                blackScore += 2;
            }
            // if it's a regular piece
            else
            {
                // add one point
                blackScore += 1;
            }

            // if this movable, regular piece current position is closer to the other end of the board (closer to becoming a king)
            if (blackPieces.isKinged() == false && blackPieces.getCurrentTile().getRow() < 5 && blackPieces.getLegalMoves().size() > 0)
            {
                // add a point
                blackScore += blackPieces.getCurrentTile().getRow();
            }

            // if this movable, regular piece is within the edges of the board (meaning this piece isn't on the edges of the board)
            if (blackPieces.isKinged() == false && blackPieces.getCurrentTile().getCol() > 0 && blackPieces.getCurrentTile().getCol() < 7 && blackPieces.getLegalMoves().size() > 0)
            {
                // add a point
                blackScore += blackPieces.getCurrentTile().getCol();
            }

            // loop through this piece's moves
            for (Move move : blackPieces.getLegalMoves())
            {

                // if it's a jump
                if (move.canEat())
                {
                    // if it's a king piece
                    if (blackPieces.isKinged())
                    {
                        blackScore += 2;
                    }
                    // if it's a regular piece
                    else
                    {
                        // if the piece is going to land on a square that is away from the edges of the board and is closer to kinging
                        if (move.getNewHome().getRow() < 5 && move.getNewHome().getCol() > 0 && move.getNewHome().getCol() < 7)
                        {
                            // add two points
                            blackScore += 2;
                        }
                    }

                }

                // if it's a regular move
                else
                {
                    // if the piece is a king
                    if (blackPieces.isKinged())
                    {
                        // and the new tile it will move to is within the boundaries of the board and not on the outer edges of the board
                        if (move.getNewHome().getRow() > 1 && move.getNewHome().getCol() > 0 && move.getNewHome().getCol() < 7)
                        {
                            // add a point
                            blackScore += 1;
                        }
                    }
                    // if the piece is going to land on a square that is away from the edges of the board and is closer to kinging
                    else
                    {
                        if (move.getNewHome().getRow() < 5 && move.getNewHome().getCol() > 0 && move.getNewHome().getCol() < 7)
                        {
                            // add a point
                            blackScore += 1;
                        }
                    }
                }
            }

        }
        // loop through black's pieces
        for (Piece redPieces : currentBoard.getBlackPlayer().getPieces())
        {
            // if the piece is a king
            if (redPieces.isKinged())
            {
                // add two points
                redScore += 2;
            }
            // if it's a regular piece
            else
            {
                // add one point
                redScore += 1;
            }

            // if this movable, regular piece current position is closer to the other end of the board (closer to becoming a king)
            if (redPieces.isKinged() == false && redPieces.getCurrentTile().getRow() > 3 && redPieces.getLegalMoves().size() > 0)
            {
                // add a point
                redScore += redPieces.getCurrentTile().getRow();
            }

            // if this movable, regular piece is within the edges of the board (meaning this piece isn't on the edges of the board)
            if (redPieces.isKinged() == false && redPieces.getCurrentTile().getCol() > 0 && redPieces.getCurrentTile().getCol() < 7 && redPieces.getLegalMoves().size() > 0)
            {
                // add a point
                redScore += redPieces.getCurrentTile().getCol();
            }

            // loop through this piece's moves
            for (Move move : redPieces.getLegalMoves())
            {

                // if it's a jump
                if (move.canEat())
                {
                    // if it's a king
                    if (redPieces.isKinged())
                    {
                        redScore += 2;
                    }
                    // if it's a regular piece
                    else
                    {
                        // if the piece is going to land on a square that is away from the edges of the board and is closer to kinging
                        if (move.getNewHome().getRow() > 3 && move.getNewHome().getCol() > 0 && move.getNewHome().getCol() < 7)
                        {
                            redScore += 2;
                        }
                    }

                }

                // if it's a regular move
                else
                {
                    // if the piece is a king
                    if (redPieces.isKinged())
                    {
                        // and the new tile it will move to is within the boundaries of the board and not on the outer edges of the board
                        if (move.getNewHome().getRow() < 7 && move.getNewHome().getCol() > 0 && move.getNewHome().getCol() < 7)
                        {
                            // add a point
                            redScore += 1;
                        }
                    }
                    // if the piece is going to land on a square that is away from the edges of the board and is closer to kinging
                    else
                    {
                        if (move.getNewHome().getRow() > 3 && move.getNewHome().getCol() > 0 && move.getNewHome().getCol() < 7)
                        {
                            // add a point
                            redScore += 1;
                        }
                    }
                }
            }

        }

        return blackTurn ? blackScore - redScore : redScore - blackScore;

    }

    /***
     * Updates the Pieces on the Board to reflect the Move specified,
     * and informs both Players that the Move has been executed via their respective executeMove methods.
     * If the Move results in a capture, removes the captured Piece from the board and from the appropriate Player's list of Pieces.
     * If the Move results in a Piece occupying it's opponent Player's first row*, the Piece is kinged.
     * Finally, adds the specified Move to the history of Moves made thus far.
     * @param playerChoice,
     *            playerMove
     */
    public void executeMove(Tile[][] board, Piece playerChoice, Move playerMove)
    {

        ArrayList<Piece> pieces = playerChoice.getPlayer().getPieces();

        // get the row and column # of the tile the piece is currently on
        int originalRow = playerChoice.getCurrentTile().getRow();
        int originalCol = playerChoice.getCurrentTile().getCol();

        // get the row and column # of the tile that the piece is going to be on
        int newRow = playerMove.getNewHome().getRow();
        int newCol = playerMove.getNewHome().getCol();

        // loop through the player's pieces
        for (Piece p : pieces)
        {

            // if the piece the player is trying to move is in the list of pieces
            if (p.getCurrentTile().getRow() == originalRow && p.getCurrentTile().getCol() == originalCol)
            {

                // if this piece can jump over opponent's pieces
                if (playerMove.canEat())
                {

                    // get the row and column # of the tile that the captured piece is on
                    int removedRow = playerMove.getRemovedHome().getRow();
                    int removedCol = playerMove.getRemovedHome().getCol();

                    // Remove the opponent's piece from the player's list of pieces
                    board[removedRow][removedCol].getOccupant().getPlayer().removePiece(board[removedRow][removedCol].getOccupant());

                    // set the tile the old piece was on to null
                    board[removedRow][removedCol].setOccupant(null);

                    // print the result of the capture
                    System.out.println(playerChoice.getPlayer().getColor() + " has captured opponent's piece on ("
                    + board[removedRow][removedCol].toString() + ")");

                }

                // make sure that the new tile is occupied
                board[newRow][newCol].setOccupant(p);

                p.setCurrentTile(board[newRow][newCol]);

                // make sure that the tile the piece was last on is no longer occupied
                board[originalRow][originalCol].setOccupant(null);

                // print the result of their move
                System.out.println(playerChoice.getPlayer().getColor() + " has moved their piece from (" + board[originalRow][originalCol].toString() +
                ") to (" + board[newRow][newCol].toString() + ")");

                break;

            }
        }
    }
}
