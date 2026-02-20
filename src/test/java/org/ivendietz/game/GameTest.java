package org.ivendietz.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();

    Player a;
    Player b;

    @BeforeEach
    void beforeEach() {
        a = new Player("A");
        b = new Player("B");
    }

    @Test
    void isPlayingRoundThatAGiven() {
        Game game = new Game(a, b);
        int rounds = 10;

        game.play(rounds);

        int actuallyPlate = game.getDraws() + game.getWinsPlayerA() + game.getWinsPlayerB();

        assertEquals(rounds, actuallyPlate);
    }

    private static Stream<Arguments> winnerIsCheckKorrieInputs() {
        return Stream.of(
                Arguments.of(Move.SCHERE, Move.PAPIER, true),
                Arguments.of(Move.SCHERE, Move.SCHERE, false),
                Arguments.of(Move.SCHERE, Move.STEIN, false),

                Arguments.of(Move.PAPIER, Move.PAPIER, false),
                Arguments.of(Move.PAPIER, Move.STEIN, true),
                Arguments.of(Move.PAPIER, Move.SCHERE, false),

                Arguments.of(Move.STEIN, Move.PAPIER, false),
                Arguments.of(Move.STEIN, Move.SCHERE, true),
                Arguments.of(Move.STEIN, Move.STEIN, false)

        );
    }

    @ParameterizedTest()
    @MethodSource("winnerIsCheckKorrieInputs")
    void winnerIsCheckKorrekt(Move a, Move b, boolean ergebnis) {
        assertEquals(ergebnis, Game.isWinning(a, b));
    }

    @Test
    void firstPlayCountGetUpWhennPlayerIsWinnig() {
        a.setMove(Move.STEIN);
        b.setMove(Move.SCHERE);

        Game game = new Game(a, b);
        game.playRound();

        assertEquals(1, game.getWinsPlayerA());
        assertEquals(0, game.getWinsPlayerB());
        assertEquals(0, game.getDraws());
    }

    @Test
    void secoundPlayCountGetUpWhennPlayerIsWinnig() {
        a.setMove(Move.STEIN);
        b.setMove(Move.PAPIER);

        Game game = new Game(a, b);
        game.playRound();

        assertEquals(0, game.getWinsPlayerA());
        assertEquals(1, game.getWinsPlayerB());
        assertEquals(0, game.getDraws());
    }

    @Test
    void drawsCountGetUpWhennNoPlayerIsWinnig() {
        a.setMove(Move.PAPIER);
        b.setMove(Move.PAPIER);

        Game game = new Game(a, b);
        game.playRound();

        assertEquals(0, game.getWinsPlayerA());
        assertEquals(0, game.getWinsPlayerB());
        assertEquals(1, game.getDraws());
    }

    @Test
    void printResultAfterPlay() {
        System.setOut(new PrintStream(outContent));
        a.setMove(Move.PAPIER);
        b.setMove(Move.PAPIER);

        Game game = new Game(a, b);
        game.play(1);

        assertEquals(0, game.getWinsPlayerA());
        assertEquals(0, game.getWinsPlayerB());
        assertEquals(1, game.getDraws());

        assertEquals("Winnings Player A: 0Winnings Player B: 0Draws: 1", outContent.toString().replaceAll("[\n\r]", ""));
    }

}