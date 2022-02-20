package ru.hse.java;

import ru.hse.java.exception.InvalidGameStatusException;
import ru.hse.java.exception.InvalidMoveException;
import ru.hse.java.engine.GameEngine;
import ru.hse.java.model.Move;
import ru.hse.java.model.Position;
import ru.hse.java.model.Status;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameEngine engine = initGame(scanner);

        while (engine.getStatus() == Status.ACTIVE) {
            printEngine(engine);
            Move move = scanInput(scanner);
            doMove(engine, move);
        }

        printEngine(engine);
        printResults(engine);
    }

    private static void doMove(GameEngine engine, Move move) {
        try {
            engine.makeMove(move);
        } catch (InvalidGameStatusException e) {
            System.out.println(e.getStatusMessage());
        } catch (InvalidMoveException e) {
            System.out.println(e.getReason());
        }
    }

    private static void printResults(GameEngine engine) {
        if (engine.getStatus() == Status.DRAW) {
            System.out.println("Game finished with Draw!");
        } else {
            System.out.println("Player " + engine.getCurrentPlayer() + " has won!");
        }
    }

    private static Move scanInput(Scanner scanner) {
        return new Move(new Position(scanner.nextInt(), scanner.nextInt()),
                new Position(scanner.nextInt(), scanner.nextInt()));
    }

    private static void printEngine(GameEngine engine) {
        System.out.println();
        System.out.println(engine.getRepresentation());
    }

    private static GameEngine initGame(Scanner scanner) {
        //todo
        return null;
    }
}
