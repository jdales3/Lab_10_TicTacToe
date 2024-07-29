import java.util.Scanner;

public class ticTacToe
{

    // Declare constants for board size and board array
    private static final int ROW = 3;
    private static final int COL = 3;
    private static String[][] board = new String[ROW][COL];

    public static void main(String[] args)
    {
        // Initialize Scanner for user input
        Scanner scanner = new Scanner(System.in);
        boolean playAgain;

        // DO-WHILE loop for playing the game and checking if players want to play again
        do {
            // Call initializeBoard() to set up the game board
            initializeBoard();
            // Call playGame() to play the game
            playGame(scanner);

            // Ask if players want to play again
            playAgain = SafeInput.getYNConfirm(scanner, "Would you like to play again?");
        } while (playAgain);

        // Print thank you message and close Scanner
        System.out.println("That was fun! Thank you for playing!");
        scanner.close();
    }

    // Method to initialize the board
    private static void initializeBoard()
    {
        // Initialize the board with spaces
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                board[i][j] = " ";
            }
        }
    }

    // Method to play the game
    private static void playGame(Scanner scanner)
    {
        // Initialize game variables
        String presentPlayer = "X"; // X always starts
        boolean gameEnded = false;

        // WHILE loop for game play
        while (!gameEnded)
        {
            // Call displayBoard() to show the current state of the board
            displayBoard();
            System.out.println("Player " + presentPlayer + "'s turn.");

            // Get move coordinates from user
            int rowOption = SafeInput.getRangedInt(scanner, "Please enter row (1-3): ", 1, ROW) - 1;
            int colOption = SafeInput.getRangedInt(scanner, "Please enter column (1-3): ", 1, COL) - 1;

            // Check if the move is valid
            if (isValidMove(rowOption, colOption))
            {
                board[rowOption][colOption] = presentPlayer;

                // Check for win or tie
                if (isWin(presentPlayer))
                {
                    displayBoard();
                    System.out.println("Player " + presentPlayer + " wins!");
                    gameEnded = true;
                } else if (isTie())
                {
                    displayBoard();
                    System.out.println("This game is a tie!");
                    gameEnded = true;
                }
                else
                {
                    // Switch player
                    presentPlayer = (presentPlayer.equals("X")) ? "O" : "X";
                }
            }
            else
            {
                // Handle invalid move
                System.out.println("Sorry! This is an invalid move. Please try again.");
            }
        }
    }

    // Method to display the board
    private static void displayBoard()
    {
        System.out.println("   1 2 3");
        for (int i = 0; i < ROW; i++)
        {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < COL; j++)
            {
                System.out.print(board[i][j]);
                if (j < COL - 1)
                {
                    System.out.print(" | ");
                }
            }
            System.out.println();
            if (i < ROW - 1) {
                System.out.println("  ---------");
            }
        }
    }

    // Method to check if a move is valid
    private static boolean isValidMove(int row, int col)
    {
        return board[row][col].equals(" ");
    }

    // Method to check if a player has won
    private static boolean isWin(String player)
    {
        return isRowWin(player) || isColWin(player) || isDiagonalWin(player);
    }

    // Method to check for row win
    private static boolean isRowWin(String player)
    {
        for (int i = 0; i < ROW; i++)
        {
            if (board[i][0].equals(player) && board[i][1].equals(player) && board[i][2].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    // Method to check for column win
    private static boolean isColWin(String player)
    {
        for (int j = 0; j < COL; j++)
        {
            if (board[0][j].equals(player) && board[1][j].equals(player) && board[2][j].equals(player))
            {
                return true;
            }
        }
        return false;
    }

    // Method to check for diagonal win
    private static boolean isDiagonalWin(String player)
    {
        return (board[0][0].equals(player) && board[1][1].equals(player) && board[2][2].equals(player)) ||
                (board[0][2].equals(player) && board[1][1].equals(player) && board[2][0].equals(player));
    }

    // Method to check for tie
    private static boolean isTie()
    {
        for (int i = 0; i < ROW; i++)
        {
            for (int j = 0; j < COL; j++)
            {
                if (board[i][j].equals(" "))
                {
                    return false;
                }
            }
        }
        return true;
    }
}

