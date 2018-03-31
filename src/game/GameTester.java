/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author jeanpierre
 */
public class GameTester
{

    private static Scanner input = new Scanner(System.in);
    private static Game game;

    public static void main(String args[])
    {
        Random random = new Random();
        System.out.println("WELCOME TO TIC TOE GAME");
        System.out.println("=======================");

        int gameType = chooseGameType();

        int playerToGoFirst = 0;
        int firstPlayerSymbol = 0;

        Player players[] = initPlayers(gameType);

        if (gameType % 2 > 0)
        {
            playerToGoFirst = choosePlayeToGoFirst(players);
            firstPlayerSymbol = selectGameSymbol(players[playerToGoFirst - 1].getName());
        }
        else
        {
            firstPlayerSymbol = (random.nextInt() % 2);
        }

        players[getIndex(playerToGoFirst)].setSymbol(getSymbol(getIndex(firstPlayerSymbol)));

        players[getIndex(playerToGoFirst + 1)].setSymbol(getSymbol(getIndex(firstPlayerSymbol + 1)));

        game = new Game(players);

        boolean isGameOver = false;
        int currentPLayerIndex = getIndex(playerToGoFirst);

        System.out.println("INITIAL GAME BOARD: ");
        game.printBoard();
        System.out.println("******************: ");

        while (!isGameOver)
        {
            System.out.println("Current Player: " + players[currentPLayerIndex]);
            Player currentPlayer = players[currentPLayerIndex];
            int attempts = 3;
            if (currentPlayer.getType().equals(PlayerType.HUMAN))
            {
                int spot = gamePrompt();

                if (game.canPlayTheSpot(spot))
                {
                    attempts = 0;
                    game.saveSpot(spot);
                    game.getHumanSpot(spot, currentPLayerIndex);
                    attempts = 0;
                }
                else
                {
                    attempts--;
                    if (attempts > 0)
                    {
                        System.out.println("You cannot play spot " + spot + " it has been played already");
                        System.out.println("Try again. (" + attempts + "attempts left)");
                    }

                }
            }
            else
            {
                attempts = 0;
                if (gameType == 2)
                {
                    Thread thread = new Thread(() ->
                    {
                        try
                        {
                            Thread.sleep(1000);
                        }
                        catch (Exception e)
                        {
                        }

                    });
                    thread.start();
                }
                game.evalBoard(currentPLayerIndex);
            }

            if (attempts == 0)
            {
                isGameOver = game.gameIsOver();
                if (!isGameOver)
                {
                    currentPLayerIndex = flipPlayer(currentPLayerIndex);
                }
            }

        }

        System.out.println("Game won by " + players[currentPLayerIndex].getName() + ". Bravo!!!");
    }

    /**
     * Method to choose game type
     */
    private static int chooseGameType()
    {
        String message = "ENTER THE NUMBER OF TYPE OF GAME YOU WANT TO PLAY\n"
                + "1. HUMAN VS HUMAN\n"
                + "2. COMPUTER VS COMPUTER\n"
                + "3. HUMAR VS COMPUTER\n";

        int gameType = 0;

        while (gameType < 1 || gameType > 3)
        {
            System.out.println(message);
            try
            {
                gameType = input.nextInt();
            }
            catch (Exception e)
            {
                input = new Scanner(System.in);
                System.out.println("PLEASE ENTER ENTER VALUES BETWEEN 1 AND 3");
            }
        }
        return gameType;
    }

    /**
     * Method to select the player to go first
     */
    private static int choosePlayeToGoFirst(Player[] players)
    {
        int choice = 0;
        String message = "ENTER THE NUMBER OF THE PLAYER YOU WANT TO START\n"
                + "1. " + players[0].getName() + "\n"
                + "2. " + players[1].getName() + "\n";

        while (choice < 1 || choice > 2)
        {
            System.out.println(message);
            try
            {
                choice = input.nextInt();
            }
            catch (Exception e)
            {
                input = new Scanner(System.in);
                System.out.println("Please enter a number between 1 and 2");
            }
        }
        return choice;
    }

    /**
     * Method to select the game symbol of the user to play first
     */
    private static int selectGameSymbol(String name)
    {
        int choice = 0;
        String message = "ENTER THE NUMBER FOR THE PLAY SYMBOL FOR PLAYER " + name + "\n"
                + "1. O\n"
                + "2. X\n";

        while (choice < 1 || choice > 2)
        {
            System.out.println(message);
            try
            {
                choice = input.nextInt();
            }
            catch (Exception e)
            {
                input = new Scanner(System.in);
                System.out.println("Please enter a number between 1 and 2");
            }
        }
        return choice;
    }

    /**
     * Method to initiate players
     */
    private static Player[] initPlayers(int gameType)
    {
        Player players[] = new Player[2];
        Player player1;
        Player player2;

        if (gameType == 1)
        {
            player1 = initPlayer(1, 1, false);
            player2 = initPlayer(2, 1, false);
        }
        else
        {
            if (gameType == 2)
            {
                player1 = initPlayer(1, 2, true);
                player2 = initPlayer(2, 2, true);
            }
            else
            {
                player1 = initPlayer(1, 1, false);
                player2 = initPlayer(2, 2, false);
            }
        }
        players[0] = player1;
        players[1] = player2;
        return players;
    }

    /**
     * Method to help initiating players one by one
     */
    private static Player initPlayer(int id, int type, boolean addIndex)
    {
        Player player = new Player();
        player.setId(id);
        player.setType(PlayerType.getType(type));

        player.setName("Computer" + (addIndex ? "- Player " + id : ""));

        if (type == 1)
        {
            String name = "";
            input = new Scanner(System.in);
            while (name.isEmpty())
            {
                System.out.println("Enter the name of player number " + id + " :");
                name = input.nextLine();
            }
            player.setName(name);
        }
        return player;
    }

    /**
     * Method to get Game symbols
     */
    private static String getSymbol(int index)
    {
        return "O X".split(" ")[index];
    }

    /**
     * Method to get index. For odd number 1 will be returned, for even number 0
     * will be returned.
     */
    private static int getIndex(int index)
    {
        return (index + 1) % 2;
    }

    /**
     * Method to prompt user to play
     */
    private static int gamePrompt()
    {
        int spot = -1;

        while (spot < 0 || spot > 9)
        {
            System.out.println("Enter number between 0 and 8 [0-8]");
            try
            {
                spot = input.nextInt();
            }
            catch (Exception e)
            {
                input = new Scanner(System.in);
                System.out.println("Please enter a number between 0 and 8");
            }
        }
        return spot;
    }

    /**
     * Method to flip user, to get the next user to play
     */
    private static int flipPlayer(int index)
    {
        return index == 0 ? 1 : (index == 1 ? 0 : 1);
    }

}
