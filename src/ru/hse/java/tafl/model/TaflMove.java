package ru.hse.java.tafl.model;

import ru.hse.java.model.Move;
import ru.hse.java.model.Position;

public record TaflMove(Position from, Position to) implements Move {
}
