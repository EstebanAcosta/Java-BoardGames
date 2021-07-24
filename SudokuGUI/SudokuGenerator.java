package SudokuGUI;

import javax.swing.JButton;

public class SudokuGenerator
{
    private int N; // number of columns/rows.

    private int SRN; // square root of N

    private int K; // No. Of missing digits

    private JButton[][] sudoku;

    public SudokuGenerator(int N, int K)
    {
        this.N = N;

        this.K = K;

        sudoku = new JButton[N][N];

        // Compute square root of N
        Double SRNd = Math.sqrt(N);

        SRN = SRNd.intValue();

        for (int r = 0; r < 9; r++)
        {
            for (int c = 0; c < 9; c++)
            {
                sudoku[r][c] = new JButton("0");
            }
        }
    }

    // // Print sudoku
    // public void printSudoku()
    // {
    // for (int i = 0; i<N; i++)
    // {
    // for (int j = 0; j<N; j++)
    // System.out.print(sudoku[i][j].getText());
    // System.out.println();
    // }
    // System.out.println();
    // }
    //

    public JButton[][] getFilledCells()
    {
        fillDiagonal();

        fillRemaining(0, SRN);

        removeKDigits();

        // printSudoku();

        return sudoku;

    }

    public void fillDiagonal()
    {
        for (int i = 0; i < N; i += SRN)
        {
            fillBox(i, i);
        }
    }

    public void fillBox(int row, int col)
    {
        int num;

        for (int i = 0; i < SRN; i++)
        {
            for (int j = 0; j < SRN; j++)
            {
                do
                {
                    num = randomGenerator(N);
                }

                while (!unUsedInBox(row, col, num));

                sudoku[row + i][col + j].setText(Integer.toString(num));
            }
        }
    }

    public int randomGenerator(int num)
    {
        return (int) Math.floor((Math.random() * num + 1));
    }

    // Returns false if given 3 x 3 block contains num.
    public boolean unUsedInBox(int rowStart, int colStart, int num)
    {
        for (int i = 0; i < SRN; i++)
        {
            for (int j = 0; j < SRN; j++)

            {
                int thatNum = Integer.parseInt(sudoku[rowStart + i][colStart + j].getText());

                if (thatNum == num)
                {
                    return false;

                }
            }
        }

        return true;

    }

    // check in the row for existence
    boolean unUsedInRow(int i, int num)
    {
        for (int j = 0; j < N; j++)
        {
            if (Integer.parseInt(sudoku[i][j].getText()) == num)
            {
                return false;

            }
        }

        return true;
    }

    // check in the col for existence
    boolean unUsedInCol(int j, int num)
    {
        for (int i = 0; i < N; i++)
        {
            if (Integer.parseInt(sudoku[i][j].getText()) == num)
            {
                return false;

            }
        }

        return true;
    }

    public boolean fillRemaining(int i, int j)
    {
        if (j >= N && i < N - 1)
        {
            i += 1;

            j = 0;
        }

        if (i >= N && j >= N)
            return true;

        if (i < SRN)
        {
            if (j < SRN)
                j = SRN;
        }
        else if (i < N - SRN)
        {
            if (j == (int) (i / SRN) * SRN)
                j = j + SRN;
        }
        else
        {
            if (j == N - SRN)
            {
                i = i + 1;
                j = 0;
                if (i >= N)
                    return true;
            }
        }

        for (int num = 1; num <= N; num++)
        {
            if (CheckIfSafe(i, j, num))
            {
                sudoku[i][j].setText(Integer.toString(num));

                if (fillRemaining(i, j + 1))
                {
                    return true;

                }

                sudoku[i][j].setText(Integer.toString(0));
            }
        }
        return false;

    }

    public boolean CheckIfSafe(int i, int j, int num)
    {
        return (unUsedInRow(i, num) &&
        unUsedInCol(j, num) &&
        unUsedInBox(i - i % SRN, j - j % SRN, num));
    }

    public void removeKDigits()
    {
        int count = K;
        while (count != 0)
        {
            int cellId = randomGenerator(N * N) - 1;

            // System.out.println(cellId);
            // extract coordinates i and j
            int i = (cellId / N);

            int j = cellId % 9;

            if (j != 0)
            {
                j = j - 1;

            }

            // System.out.println(i+" "+j);
            if (Integer.parseInt(sudoku[i][j].getText()) != 0)
            {
                count--;

                sudoku[i][j].setText("0");
            }
        }
    }

}
