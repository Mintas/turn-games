package ru.hse.java;

import ru.hse.java.engine.GameEngine;
import ru.hse.java.exceptions.InvalidGameStatusException;
import ru.hse.java.exceptions.InvalidMoveException;
import ru.hse.java.model.Move;
import ru.hse.java.model.Position;
import ru.hse.java.model.Status;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameEngine engine = initEngine(scanner);

        while (engine.getStatus() == Status.ACTIVE) {
            printState(engine);
            Move move = scanMove(scanner);
            makeMove(engine, move);
        }

        printState(engine);
        printResult(engine);
    }

    private static void makeMove(GameEngine engine, Move move) {
        try {
            engine.makeMove(move);
        } catch (InvalidGameStatusException e) {
            System.out.println(e.getMessage());
        } catch (InvalidMoveException e) {
            System.out.println(e.getReason());
        }
    }

    private static GameEngine initEngine(Scanner scanner) {
        //todo create one?
        //todo : implement choice of game
        return null;
    }

    private static Move scanMove(Scanner scanner) {
        System.out.println("Your move: ");
        return new Move(new Position(scanner.nextInt(), scanner.nextInt()), new Position(scanner.nextInt(), scanner.nextInt()));
    }

    private static void printState(GameEngine engine) {
        System.out.println();
        System.out.println(engine.getRepresentation());
    }

    private static void printResult(GameEngine engine) {
        if (engine.getStatus() == Status.DRAW) {
            System.out.println("Game finished with Draw!");
        } else {
            System.out.println("Player " + engine.getCurrentPlayer() + " has won!");
        }
    }
}
