package JavaBoardGames.Game2048;

import java.util.Scanner;

/***
 * @author estebanacosta
 */
public class Game2048
{

    public boolean slideUp(Board board)
    {
        Tile[][] tileBoard = board.getBoard();

        int firstRow = 0;

        // this determines if there has been a change to the tileboard
        boolean noChange = true;

        for (int row = 0; row < tileBoard.length; row++)
        {
            for (int col = 0; col < tileBoard[row].length; col++)
            {

                if (tileBoard[row][col].isOccupied() && row != firstRow)
                {

                    // start on the first row
                    int currentRow = firstRow;

                    // continue looping until either the row isn't greater than the row this square is on
                    // or until there is an unoccupied square
                    while (tileBoard[currentRow][col].isOccupied() && currentRow < row)
                    {

                        currentRow++;
                    }

                    if (currentRow != row)
                    {
                        // set this unoccupied square to the value that's on the current square
                        tileBoard[currentRow][col].setOccupant(tileBoard[row][col].getOccupant());

                        // set it to 0 in order to officially unoccupy the square
                        tileBoard[row][col].setOccupant(0);

                        // since the tiles have been moved upwards then we set no change to false
                        noChange = false;
                    }

                }

            }
        }

        return noChange;

    }

    public boolean slideDown(Board board)
    {
        Tile[][] tileBoard = board.getBoard();

        boolean noChange = true;

        int lastRow = tileBoard.length - 1;

        for (int row = lastRow; row >= 0; row--)
        {
            for (int col = 0; col < tileBoard[row].length; col++)
            {

                if (tileBoard[row][col].isOccupied() && row != lastRow)
                {
                    boolean hasMerged = false;

                    // start on the last row
                    int currentRow = lastRow;

                    // continue looping until either the row isn't greater than the row this square is on
                    // or until there is an unoccupied square
                    while (tileBoard[currentRow][col].isOccupied() && currentRow > row)
                    {

                        currentRow--;
                    }

                    if (currentRow != row && hasMerged == false)
                    {
                        // set this unoccupied square to the value that's on the current square
                        tileBoard[currentRow][col].setOccupant(tileBoard[row][col].getOccupant());

                        // set it to 0 in order to officially unoccupy the square
                        tileBoard[row][col].setOccupant(0);

                        noChange = false;
                    }

                }

            }
        }

        return noChange;

    }

    public boolean slideRight(Board board)
    {
        Tile[][] tileBoard = board.getBoard();

        boolean noChange = true;

        int firstCol = tileBoard[0].length - 1;

        for (int row = 0; row < tileBoard.length; row++)
        {
            for (int col = firstCol; col >= 0; col--)
            {

                if (tileBoard[row][col].isOccupied() && col != firstCol)
                {
                    // start on the last row
                    int currentCol = firstCol;

                    // continue looping until either the row isn't greater than the row this square is on
                    // or until there is an unoccupied square
                    while (tileBoard[row][currentCol].isOccupied() && currentCol > col)
                    {
                        currentCol--;
                    }

                    if (currentCol != col)
                    {
                        // set this unoccupied square to the value that's on the current square
                        tileBoard[row][currentCol].setOccupant(tileBoard[row][col].getOccupant());

                        // set it to 0 in order to officially unoccupy the square
                        tileBoard[row][col].setOccupant(0);

                        noChange = false;
                    }

                }

            }
        }

        return noChange;

    }

    public boolean slideLeft(Board board)
    {
        Tile[][] tileBoard = board.getBoard();

        boolean noChange = true;

        int lastCol = 0;

        for (int row = 0; row < tileBoard.length; row++)
        {
            for (int col = 0; col < tileBoard[row].length; col++)
            {

                if (tileBoard[row][col].isOccupied() && col != lastCol)
                {
                    // start on the last row
                    int currentCol = lastCol;

                    // continue looping until either the row isn't greater than the row this square is on
                    // or until there is an unoccupied square
                    while (tileBoard[row][currentCol].isOccupied() && currentCol < col)
                    {
                        currentCol++;
                    }

                    if (currentCol != col)
                    {
                        // set this unoccupied square to the value that's on the current square
                        tileBoard[row][currentCol].setOccupant(tileBoard[row][col].getOccupant());

                        // set it to 0 in order to officially unoccupy the square
                        tileBoard[row][col].setOccupant(0);

                        noChange = false;
                    }

                }

            }
        }
        return noChange;

    }

    public boolean mergeUp(Board board)
    {
        Tile[][] tileBoard = board.getBoard();

        boolean merged = false;
        // merging process

        // loop through each column of the grid
        for (int col = 0; col < tileBoard[0].length; col++)
        {
            int merged12 = 0, merged23 = 0, merged34 = 0;

            if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant()
            && tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant() && tileBoard[3][col].getOccupant() != 0)
            {
                // add them together and store it in a variable that specifically stores the merged values of row 1 and 2
                merged12 = tileBoard[0][col].getOccupant() + tileBoard[1][col].getOccupant();

                tileBoard[0][col].setOccupant(merged12);

                merged34 = tileBoard[2][col].getOccupant() + tileBoard[3][col].getOccupant();

                tileBoard[1][col].setOccupant(merged34);

                tileBoard[2][col].setOccupant(0);

                tileBoard[3][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant()
            && tileBoard[0][col].getOccupant() != 0 && tileBoard[2][col].getOccupant() != 0)
            {
                merged12 = tileBoard[0][col].getOccupant() + tileBoard[1][col].getOccupant();

                tileBoard[0][col].setOccupant(merged12);

                merged34 = tileBoard[2][col].getOccupant() + tileBoard[3][col].getOccupant();

                tileBoard[1][col].setOccupant(merged34);

                tileBoard[2][col].setOccupant(0);

                tileBoard[3][col].setOccupant(0);

                merged = true;
            }

            // if the second row and the third row have the same number
            else if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant() && tileBoard[2][col].getOccupant() != 0)
            {

                merged12 = tileBoard[0][col].getOccupant() + tileBoard[1][col].getOccupant();

                tileBoard[0][col].setOccupant(merged12);

                tileBoard[1][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant() && tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant() && tileBoard[3][col].getOccupant() != 0)
            {

                merged23 = tileBoard[1][col].getOccupant() + tileBoard[2][col].getOccupant();

                tileBoard[1][col].setOccupant(merged23);

                tileBoard[2][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[0][col].getOccupant() != 0)
            {

                merged12 = tileBoard[0][col].getOccupant() + tileBoard[1][col].getOccupant();

                tileBoard[0][col].setOccupant(merged12);

                tileBoard[1][col].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant() && tileBoard[1][col].getOccupant() != 0)
            {

                merged23 = tileBoard[1][col].getOccupant() + tileBoard[2][col].getOccupant();

                tileBoard[1][col].setOccupant(merged23);

                tileBoard[2][col].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant() && tileBoard[2][col].getOccupant() != 0)
            {

                merged34 = tileBoard[2][col].getOccupant() + tileBoard[3][col].getOccupant();

                tileBoard[2][col].setOccupant(merged34);

                tileBoard[3][col].setOccupant(0);
                merged = true;
            }

        }
        return merged;

    }

    public boolean mergeDown(Board board)
    {
        Tile[][] tileBoard = board.getBoard();
        // merging process

        boolean merged = false;

        // loop through each column of the grid
        for (int col = 0; col < tileBoard[0].length; col++)
        {
            int merged12 = 0, merged23 = 0, merged34 = 0;

            // if the first row and the second row have the same number
            if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant()
            && tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant() && tileBoard[0][col].getOccupant() != 0)
            {
                merged34 = tileBoard[2][col].getOccupant() + tileBoard[3][col].getOccupant();

                tileBoard[3][col].setOccupant(merged34);

                merged12 = tileBoard[0][col].getOccupant() + tileBoard[1][col].getOccupant();

                tileBoard[2][col].setOccupant(merged12);

                tileBoard[0][col].setOccupant(0);

                tileBoard[1][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant()
            && tileBoard[0][col].getOccupant() != 0 && tileBoard[2][col].getOccupant() != 0)
            {
                merged34 = tileBoard[2][col].getOccupant() + tileBoard[3][col].getOccupant();

                tileBoard[3][col].setOccupant(merged34);

                merged12 = tileBoard[0][col].getOccupant() + tileBoard[1][col].getOccupant();

                tileBoard[2][col].setOccupant(merged12);

                tileBoard[0][col].setOccupant(0);

                tileBoard[1][col].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant() && tileBoard[1][col].getOccupant() != 0)
            {

                merged12 = tileBoard[1][col].getOccupant() + tileBoard[2][col].getOccupant();

                tileBoard[2][col].setOccupant(merged12);

                tileBoard[1][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant() && tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant() && tileBoard[2][col].getOccupant() != 0)
            {
                merged23 = tileBoard[2][col].getOccupant() + tileBoard[3][col].getOccupant();

                tileBoard[3][col].setOccupant(merged23);

                tileBoard[2][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[0][col].getOccupant() == tileBoard[1][col].getOccupant() && tileBoard[0][col].getOccupant() != 0)
            {

                merged12 = tileBoard[0][col].getOccupant() + tileBoard[1][col].getOccupant();

                tileBoard[1][col].setOccupant(merged12);

                tileBoard[0][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[1][col].getOccupant() == tileBoard[2][col].getOccupant() && tileBoard[1][col].getOccupant() != 0)
            {

                merged23 = tileBoard[1][col].getOccupant() + tileBoard[2][col].getOccupant();

                tileBoard[2][col].setOccupant(merged23);

                tileBoard[1][col].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[2][col].getOccupant() == tileBoard[3][col].getOccupant() && tileBoard[2][col].getOccupant() != 0)
            {

                merged34 = tileBoard[2][col].getOccupant() + tileBoard[3][col].getOccupant();

                tileBoard[3][col].setOccupant(merged34);

                tileBoard[2][col].setOccupant(0);

                merged = true;

            }

        }
        return merged;

    }

    public boolean mergeLeft(Board board)
    {
        Tile[][] tileBoard = board.getBoard();
        // merging process
        boolean merged = false;
        // loop through each row of the grid
        for (int row = 0; row < tileBoard.length; row++)
        {
            int merged12 = 0, merged23 = 0, merged34 = 0;

            // if the first row and the second row have the same number
            if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant()
            && tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant() && tileBoard[row][3].getOccupant() != 0)
            {
                merged12 = tileBoard[row][0].getOccupant() + tileBoard[row][1].getOccupant();

                tileBoard[row][0].setOccupant(merged12);

                merged34 = tileBoard[row][2].getOccupant() + tileBoard[row][3].getOccupant();

                tileBoard[row][1].setOccupant(merged34);

                tileBoard[row][2].setOccupant(0);

                tileBoard[row][3].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant()
            && tileBoard[row][0].getOccupant() != 0 && tileBoard[row][2].getOccupant() != 0)
            {
                merged12 = tileBoard[row][0].getOccupant() + tileBoard[row][1].getOccupant();

                tileBoard[row][0].setOccupant(merged12);

                merged34 = tileBoard[row][2].getOccupant() + tileBoard[row][3].getOccupant();

                tileBoard[row][1].setOccupant(merged34);

                tileBoard[row][2].setOccupant(0);

                tileBoard[row][3].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant() && tileBoard[row][2].getOccupant() != 0)
            {
                merged12 = tileBoard[row][0].getOccupant() + tileBoard[row][1].getOccupant();

                tileBoard[row][0].setOccupant(merged12);

                tileBoard[row][1].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant() && tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant() && tileBoard[row][2].getOccupant() != 0)
            {
                merged12 = tileBoard[row][1].getOccupant() + tileBoard[row][2].getOccupant();

                tileBoard[row][1].setOccupant(merged12);

                tileBoard[row][2].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][0].getOccupant() != 0)
            {

                merged12 = tileBoard[row][0].getOccupant() + tileBoard[row][1].getOccupant();

                tileBoard[row][0].setOccupant(merged12);

                tileBoard[row][1].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant() && tileBoard[row][1].getOccupant() != 0)
            {

                merged23 = tileBoard[row][1].getOccupant() + tileBoard[row][2].getOccupant();

                tileBoard[row][1].setOccupant(merged23);

                tileBoard[row][2].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant() && tileBoard[row][2].getOccupant() != 0)
            {

                merged34 = tileBoard[row][2].getOccupant() + tileBoard[row][3].getOccupant();

                tileBoard[row][2].setOccupant(merged34);

                tileBoard[row][3].setOccupant(0);

                merged = true;
            }

        }
        return merged;

    }

    public boolean mergeRight(Board board)
    {
        Tile[][] tileBoard = board.getBoard();
        // merging process
        boolean merged = false;
        // loop through each row of the grid
        for (int row = 0; row < tileBoard.length; row++)
        {
            int merged12 = 0, merged23 = 0, merged34 = 0;

            // if the first row and the second row have the same number
            if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant()
            && tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant() && tileBoard[row][3].getOccupant() != 0)
            {

                merged34 = tileBoard[row][2].getOccupant() + tileBoard[row][3].getOccupant();

                tileBoard[row][3].setOccupant(merged34);

                merged12 = tileBoard[row][0].getOccupant() + tileBoard[row][1].getOccupant();

                tileBoard[row][2].setOccupant(merged12);

                tileBoard[row][0].setOccupant(0);

                tileBoard[row][1].setOccupant(0);

                merged = true;

            }

            else if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant()
            && tileBoard[row][0].getOccupant() != 0 && tileBoard[row][2].getOccupant() != 0)
            {

                merged34 = tileBoard[row][2].getOccupant() + tileBoard[row][3].getOccupant();

                tileBoard[row][3].setOccupant(merged34);

                merged12 = tileBoard[row][0].getOccupant() + tileBoard[row][1].getOccupant();

                tileBoard[row][2].setOccupant(merged12);

                tileBoard[row][0].setOccupant(0);

                tileBoard[row][1].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant() && tileBoard[row][2].getOccupant() != 0)
            {
                merged23 = tileBoard[row][1].getOccupant() + tileBoard[row][2].getOccupant();

                tileBoard[row][2].setOccupant(merged23);

                tileBoard[row][1].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant() && tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant() && tileBoard[row][2].getOccupant() != 0)
            {
                merged34 = tileBoard[row][2].getOccupant() + tileBoard[row][3].getOccupant();

                tileBoard[row][3].setOccupant(merged34);

                tileBoard[row][2].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][0].getOccupant() == tileBoard[row][1].getOccupant() && tileBoard[row][0].getOccupant() != 0)
            {

                merged12 = tileBoard[row][0].getOccupant() + tileBoard[row][1].getOccupant();

                tileBoard[row][1].setOccupant(merged12);

                tileBoard[row][0].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][1].getOccupant() == tileBoard[row][2].getOccupant() && tileBoard[row][1].getOccupant() != 0)
            {

                merged23 = tileBoard[row][1].getOccupant() + tileBoard[row][2].getOccupant();

                tileBoard[row][2].setOccupant(merged23);

                tileBoard[row][1].setOccupant(0);

                merged = true;
            }

            else if (tileBoard[row][2].getOccupant() == tileBoard[row][3].getOccupant() && tileBoard[row][2].getOccupant() != 0)
            {

                merged34 = tileBoard[row][2].getOccupant() + tileBoard[row][3].getOccupant();

                tileBoard[row][3].setOccupant(merged34);

                tileBoard[row][2].setOccupant(0);

                merged = true;
            }

        }

        return merged;

    }

    public void startGame()
    {
        Board board = new Board();

        System.out.println("Welcome to 2048");

        Scanner kbd = new Scanner(System.in);

        boolean shouldRandomlyAssign = true;

        board.randomlyAssignToBoard();

        while (gameOver(board) != true)
        {

            if (shouldRandomlyAssign == true)
            {
                board.randomlyAssignToBoard();
            }

            board.displayBoard();

            System.out.println("\nEnter d for down, u for up, l for left and r for right");

            String direction = kbd.nextLine();

            while (!direction.equalsIgnoreCase("d") && !direction.equalsIgnoreCase("u") && !direction.equalsIgnoreCase("l") && !direction.equalsIgnoreCase("r"))
            {
                System.out.println("\nPlease enter d for down, u for up, l for left and r for right");

                direction = kbd.nextLine();
            }

            switch (direction)
            {

                case "d":

                    // if there is a change in the tile board whether it be just through a slide or a merge
                    boolean slideDown = slideDown(board) ? false : true;

                    boolean mergeDown = mergeDown(board) ? true : false;

                    if (slideDown == true && mergeDown == true || slideDown == true && mergeDown == false || slideDown == false && mergeDown == true)
                    {
                        // the board will be assigned a randomly placed 2 or 4
                        shouldRandomlyAssign = true;

                    }

                    // otherwise no randomly assigned number should be placed on the board
                    else
                    {
                        System.out.println("Please choose a different direction to move");

                        shouldRandomlyAssign = false;
                    }

                    slideDown(board);
                    break;

                case "u":

                    // if there is a change in the tile board whether it be just through a slide or a merge

                    boolean slideUp = slideUp(board) ? false : true;

                    boolean mergeUp = mergeUp(board) ? true : false;

                    if (slideUp == true && mergeUp == true || slideUp == true && mergeUp == false || slideUp == false && mergeUp == true)
                    {
                        // the board will be assigned a randomly placed 2 or 4
                        shouldRandomlyAssign = true;

                    }
                    // otherwise no randomly assigned number should be placed on the board

                    else
                    {
                        System.out.println("Please choose a different direction to move");

                        shouldRandomlyAssign = false;
                    }

                    slideUp(board);
                    break;

                case "l":

                    boolean slideLeft = slideLeft(board) ? false : true;

                    boolean mergeLeft = mergeLeft(board) ? true : false;

                    // if there is a change in the tile board whether it be just through a slide or a merge

                    if (slideLeft == true && mergeLeft == true || slideLeft == true && mergeLeft == false || slideLeft == false && mergeLeft == true)
                    {
                        // the board will be assigned a randomly placed 2 or 4
                        shouldRandomlyAssign = true;
                    }

                    // otherwise no randomly assigned number should be placed on the board

                    else
                    {

                        System.out.println("Please choose a different direction to move");

                        shouldRandomlyAssign = false;
                    }

                    slideLeft(board);
                    break;

                case "r":

                    boolean slideRight = slideRight(board) ? false : true;

                    boolean mergeRight = mergeRight(board) ? true : false;

                    // if there is a change in the tile board whether it be just through a slide or a merge

                    if (slideRight == true && mergeRight == true || slideRight == false && mergeRight == true || slideRight == true && mergeRight == false)
                    {
                        // the board will be assigned a randomly placed 2 or 4
                        shouldRandomlyAssign = true;
                    }

                    // otherwise no randomly assigned number should be placed on the board

                    else
                    {
                        System.out.println("Please choose a different direction to move");

                        shouldRandomlyAssign = false;
                    }
                    slideRight(board);
                    break;

            }
        }
    }

    public boolean gameOver(Board board)
    {

        if (board.reachedWinningNumber())
        {
            System.out.println("Congrats you have won");
            return true;
        }

        else if (board.allOccupied())
        {
            System.out.println("GAME OVER");
            System.out.println("YOU HAVE LOST");
            return true;
        }

        return false;
    }

    public static void main(String[] args)
    {

        Game2048 game = new Game2048();

        game.startGame();
    }
}
