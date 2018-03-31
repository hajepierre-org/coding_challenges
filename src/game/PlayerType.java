/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game;

/**
 *
 * @author jeanpierre
 */
public enum PlayerType
{
    HUMAN,
    COMPUTER;

    public static PlayerType getType(int value)
    {
        return value == 1 ? HUMAN : COMPUTER;
    }
}
