
//Imports
import java.util.Scanner;
import java.util.Random;

//Minh Doan, 2/25/2025
//Assignment 4 'A Game'
//Prof. John Fiore
class Knoxhult {

    public static void PrintRules() {
        System.out.println("\nThe Rules:");
        System.out.println("    Revskar beats Utlangen, Kloven");
        System.out.println("    Utlangen beats Altappen, Begripa");
        System.out.println("    Altappen beats Revskar, Kloven");
        System.out.println("    Kloven Beats Begripa, Utlangen");
        System.out.println("    Begripa beats Revskar, Altappen");
    }

    public static boolean PlayAgain(Scanner scan) {

        String userInput;
        scan.nextLine(); // Consumes Line. In the first iteration the user has to press enter to get to
                         // the print statement.
        System.out.println("Do you want to play a round? (y/n)");
        userInput = scan.nextLine();
        // System.out.println("Inputted: " + userInput);

        if (userInput.isEmpty()) {
            System.out.println("You Entered Nothing, assuming you no longer want to play");
            return false;
        }

        if (userInput.toLowerCase().charAt(0) == 'y') {
            // System.out.println("Playing Again!");
            return true;
        }

        return false;
    }

    public static void displayMoves() { // Helper Function. not to be called in game loop
        // Kloven, Altappen, Utlangen, Begripa, or Revskar.
        System.out.println("1. Kloven");
        System.out.println("2. Altappen");
        System.out.println("3. Utlangen");
        System.out.println("4. Begripa");
        System.out.println("5. Revskar");
    }

    public static String getUserMove(Scanner scan) {
        // get user moves via numeric input!
        Boolean validMove = false;

        while (!validMove) {
            validMove = true;
            final int userInput;
            final String userMove;

            System.out.println("Pick a move! 1-5");
            displayMoves();
            System.out.println("Your Choice: ");

            userInput = scan.nextInt();

            switch (userInput) { // Convert Numeric Value to Move
                case 1:
                    userMove = "Kloven";
                    return userMove;

                case 2:
                    userMove = "Altappen";
                    return userMove;

                case 3:
                    userMove = "Utlangen";
                    return userMove;

                case 4:
                    userMove = "Begripa";
                    return userMove;

                case 5:
                    userMove = "Revskar";
                    return userMove;

                default: // user did not enter valid move
                    System.out.println("Not a valid input! Please try again!");
                    validMove = false;
            }
        }

        return "Get User Move Error!"; // Hopefully this is not possible to get
    }

    public static String getComputerMove() { // Helper function for 'decide winner'
        Random random = new Random();

        int computerInput = random.nextInt(5); // generates a number between 0-4
        computerInput += 1; // adds one to computer move to make it more stream lined
        final String computerMove;

        switch (computerInput) { // Convert numeric value to Move
            case 1:
                computerMove = "Kloven";
                break;

            case 2:
                computerMove = "Altappen";
                break;

            case 3:
                computerMove = "Utlangen";
                break;

            case 4:
                computerMove = "Begripa";
                break;

            case 5:
                computerMove = "Revskar";
                break;

            default:
                System.out.println("Something went wrong with getComputerMove()!");
                computerMove = "Kloven";
        }

        return computerMove;
    }

    public static String decideWinner(String userMove) {
        // rules
        // Revskar beats Utlangen, Kloven D
        // Utlangen beats Altappen, Begripa H
        // Altappen beats Revskar, Kloven N
        // Kloven Beats Begripa, Utlangen I
        // Begripa beats Revskar, Altappen M
        final String computerMove = getComputerMove();
        System.out.println("The Computer Picked: " + computerMove);

        // Check if user has won
        if (userMove == "Revskar" && computerMove == "Utlangen") {
            return "User";
        } else if (userMove == "Utlangen" && computerMove == "Altappen") {
            return "User";
        } else if (userMove == "Altappen" && computerMove == "Revskar") {
            return "User";
        } else if (userMove == "Kloven" && computerMove == "Begripa") {
            return "User";
        } else if (userMove == "Begripa" && computerMove == "Revskar") {
            return "User";
        }
        // Computer has won. Ties also count towards computer
        return "Computer";
    }

    public static void StartGame() {
        Scanner scan = new Scanner(System.in);
        boolean isPlaying = true;
        int gamesPlayed = 0;
        int gamesUserWon = 0;
        int gamesComputerWon = 0;

        while (isPlaying) {
            PrintRules();
            if (PlayAgain(scan) == true) { // Playing Again
                gamesPlayed += 1;

                String userMove = getUserMove(scan);
                System.out.println("You Picked: " + userMove);

                String winner = decideWinner(userMove);

                if (winner == "User") {
                    // User won
                    System.out.println("    YOU WIN!");
                    gamesUserWon += 1;
                } else {
                    // Computer Won
                    System.out.println("    YOU LOSE!!!");
                    gamesComputerWon += 1;

                }

            } else { // Quit Program
                isPlaying = false;
                System.out.println("Games Played: " + gamesPlayed);
                System.out.println("Games You Won: " + gamesUserWon);
                System.out.println("Games Computer Won: " + gamesComputerWon);
            }
        }

        scan.close(); // close scanner
    }

    public static void main(String args[]) {
        StartGame();
    }
}