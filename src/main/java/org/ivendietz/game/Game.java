package org.ivendietz.game;

public class Game {

    private final Player playerA;
    private final Player playerB;

    private int winsPlayerA;

    private int winsPlayerB;
    private int draws;
    public Game(Player playerA, Player playerB) {
        this.playerA = playerA;
        this.playerB = playerB;
    }

    public void play(int rounds) {
        for (int i = 0; i < rounds; i++) {
            playRound();
        }
        printResult();
    }

    void playRound() {
        Move movePlayerA = playerA.getMove();
        Move movePlayerB = playerB.getMove();

        if (isWinning(movePlayerA, movePlayerB)) {
            winsPlayerA++;
        } else if (isWinning(movePlayerB, movePlayerA)) {
            winsPlayerB++;
        }else {
            draws++;
        }

    }

     void printResult() {
        System.out.println("Winnings Player " + playerA.getName() + ": " + winsPlayerA);
        System.out.println("Winnings Player " + playerB.getName() + ": " + winsPlayerB);
        System.out.println("Draws: " + draws);
    }

    static boolean isWinning(Move moveA, Move moveB) {
        return moveA.equals(Move.SCHERE) && moveB.equals(Move.PAPIER) ||
                moveA.equals(Move.PAPIER) && moveB.equals(Move.STEIN) ||
                moveA.equals(Move.STEIN) && moveB.equals(Move.SCHERE);
    }

    public int getDraws() {
        return draws;
    }

    public int getWinsPlayerB() {
        return winsPlayerB;
    }

    public int getWinsPlayerA() {
        return winsPlayerA;
    }

}
