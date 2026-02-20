package org.ivendietz.game;

import java.util.Random;

public class Player {

    private final String Name;
    private Move move;
    private static final Random Random = new Random();
    private static final int moveSize = Move.values().length;

    public Player(String name) {
        this.Name = name;
    }

    public String getName() {
        return Name;
    }

    public Move getMove() {
        if (move == null) {
            return generateMove();
        }
        return move;
    }

    public void setMove(Move move) {
        this.move = move;
    }

    private Move generateMove() {
        return Move.values()[Random.nextInt(moveSize)];
    }

}
