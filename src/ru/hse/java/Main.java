package ru.hse.java;

import ru.hse.java.engine.GameEngine;
import ru.hse.java.exception.InvalidGameStatusException;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.model.Move;
import ru.hse.java.model.Player;
import ru.hse.java.model.Position;
import ru.hse.java.model.Status;
import ru.hse.java.tafl.BrandubhDeskFactory;
import ru.hse.java.tafl.TaflGameEngine;
import ru.hse.java.tafl.model.TaflMove;
import ru.hse.java.tictactoe.model.TicTacToeMove;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameType gameType = scanGameType(scanner);
        GameEngine<?> engine = initGame(gameType);

        while (engine.getStatus() == Status.ACTIVE) {
            printEngine(engine);
            doMove(engine, scanInput(scanner, gameType));
        }

        printEngine(engine);
        printResults(engine);
    }

    private static GameType scanGameType(Scanner scanner) {
        return scanner.nextInt() == 0 ? GameType.BRANDRUBH_TAFL : GameType.TIC_TAC_TOE;
    }

    enum GameType{
        BRANDRUBH_TAFL, TIC_TAC_TOE
    }

    private static <M extends Move> void doMove(GameEngine<M> engine, M move) {
        try {
            engine.makeMove(move);
        } catch (InvalidGameStatusException e) {
            System.out.println(e.getStatusMessage());
        } catch (InvalidMoveException e) {
            System.out.println(e.getReason());
        }
    }

    private static void printResults(GameEngine<?> engine) {
        if (engine.getStatus() == Status.DRAW) {
            System.out.println("Game finished with Draw!");
        } else {
            System.out.println("Player " + engine.getCurrentPlayer() + " has won!");
        }
    }

    @SuppressWarnings("unchecked")
    private static <M extends Move> M scanInput(Scanner scanner, GameType gameType) {
        if (gameType == GameType.BRANDRUBH_TAFL) {
            return (M) new TaflMove(new Position(scanner.nextInt(), scanner.nextInt()),
                    new Position(scanner.nextInt(), scanner.nextInt()));
        } else {
            return (M) new TicTacToeMove(new Position(scanner.nextInt(), scanner.nextInt()));
        }
    }

    private static void printEngine(GameEngine<?> engine) {
        System.out.println();
        System.out.println(engine.getRepresentation());
    }

    private static GameEngine<?> initGame(GameType type) {
        if (type == GameType.BRANDRUBH_TAFL) {
            return new TaflGameEngine(Player.BLACK, new BrandubhDeskFactory());
        }
        //todo for TicTacToe
        return null;
    }
}
