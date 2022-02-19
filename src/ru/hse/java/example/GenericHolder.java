package ru.hse.java.example;

import ru.hse.java.model.Printable;

public class GenericHolder<T extends Printable & Comparable<T>> {
    private final T value;

    public GenericHolder(T value) {
        this.value = value;
    }

    public static void main(String[] args) {
        GenericHolder<MyString> something = new GenericHolder<>(new MyString("something"));
        Direction up = Direction.UP;
        Examplee examplee = new Examplee();
    }


    public static class MyString implements Printable, Comparable<MyString> {
        private final String value;

        public MyString(String value) {
            this.value = value;
        }

        @Override
        public int compareTo(MyString o) {
            return value.compareTo(o.value);
        }

        @Override
        public String getRepresentation() {
            return value;
        }
    }

    enum Direction {
        UP, LEFT
    }

    enum MyEnum2 {

    }

}
