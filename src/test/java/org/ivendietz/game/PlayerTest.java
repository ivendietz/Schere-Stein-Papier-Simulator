package org.ivendietz.game;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @RepeatedTest(100)
    void shoutReturnOnlySetMove() {
        Player player = new Player("A");
        player.setMove(Move.STEIN);

        assertEquals(Move.STEIN, player.getMove());
    }

    @Test
    void shoutReturnRandomMove() {
        Player player = new Player("A");

        Set<Move> seen = new HashSet<>();
        for (int i = 0; i < 500; i++) {
            seen.add(player.getMove());
        }

        assertTrue(seen.size() > 1);
    }
}