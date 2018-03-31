/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Tic-Tac-Toe: TWo-player console version.
 */
public class Game
{
    // The game board and the game status

    private String[] board =
    {
        "0", "1", "2", "3", "4", "5", "6", "7", "8"
    };

    private int spots[] = new int[9];//played spots
    private Player players[] = new Player[2];// Game players

    public Game(Player players[])
    {
        this.players = players;
    }

    /**
     * Method to validate if a given spot is playable
     */
    public boolean canPlayTheSpot(int spot)
    {
        return spots[spot] == 0;
    }

    /**
     * Method to log all played spots
     */
    public void saveSpot(int spot)
    {
        spots[spot] = 1;
    }

    public void getHumanSpot(int spot, int player)
    {
        board[spot] = players[player].getSymbol();  // update game-board content
        printBoard(); //Print Board
    }

    public void evalBoard(int playerIndex)
    {
        boolean foundSpot = false;
        do
        {
            if (board[4] == "4")
            {
                board[4] = players[playerIndex].getSymbol();
                saveSpot(4);
                foundSpot = true;
            }
            else
            {
                int spot = getBestMove();
                if (board[spot] != "X" && board[spot] != "O")
                {
                    foundSpot = true;
                    board[spot] = players[playerIndex].getSymbol();
                    saveSpot(spot);
                }
                else
                {
                    foundSpot = false;
                }
            }
        }
        while (!foundSpot);
        printBoard();
    }

    /**
     * Return true if the game was just won
     */
    public boolean gameIsOver()
    {
        return board[0] == board[1] && board[1] == board[2]
                || board[3] == board[4] && board[4] == board[5]
                || board[6] == board[7] && board[7] == board[8]
                || board[0] == board[3] && board[3] == board[6]
                || board[1] == board[4] && board[4] == board[7]
                || board[2] == board[5] && board[5] == board[8]
                || board[0] == board[4] && board[4] == board[8]
                || board[2] == board[4] && board[4] == board[6];
    }

    public int getBestMove()
    {
        ArrayList<String> availableSpaces = new ArrayList<String>();
        boolean foundBestMove = false;
        int spot = 100;
        for (String s : board)
        {
            //Regular expression to validate numbers
            if (s.matches("[0-8]"))
            {
                availableSpaces.add(s);
            }
        }
        for (String as : availableSpaces)
        {
            spot = Integer.parseInt(as);
            board[spot] = "O";
            if (gameIsOver())
            {
                foundBestMove = true;
                board[spot] = as;
                return spot;
            }
            else
            {
                board[spot] = "X";
                if (gameIsOver())
                {
                    foundBestMove = true;
                    board[spot] = as;
                    return spot;
                }
                else
                {
                    board[spot] = as;
                }
            }
        }
        if (foundBestMove)
        {
            return spot;
        }
        else
        {
            int n = ThreadLocalRandom.current().nextInt(0, availableSpaces.size());
            return Integer.parseInt(availableSpaces.get(n));
        }
    }

    /**
     * Return true if it is a draw (no more empty cell)
     */
    // TODO: maybe there is an easeir way to check this
    public boolean tie()
    {
        for (int i = 0; i < board.length; i++)
        {
            if (board[i] != i + "")
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Print the game board
     */
    public void printBoard()
    {
        System.out.println(" " + board[0] + " | " + board[1] + " | " + board[2] + "\n===+===+===\n" + " " + board[3] + " | " + board[4] + " | " + board[5] + "\n===+===+===\n" + " " + board[6] + " | " + board[7] + " | " + board[8] + "\n"); // print all the board cells
    }

}
