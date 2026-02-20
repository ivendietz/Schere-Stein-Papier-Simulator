package org.ivendietz;


import org.ivendietz.game.Game;
import org.ivendietz.game.Move;
import org.ivendietz.game.Player;

public class Main {
    public static void main(String[] args) {
       Player a = new Player("A");
       Player b = new Player("B");

       a.setMove(Move.PAPIER);

       Game game = new Game(a, b);
       game.play(100);
    }
}