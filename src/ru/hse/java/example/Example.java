package ru.hse.java.example;

public class Example {
    enum MyLovelyClasses {
        DOUBLE(Double.class), STRING(String.class);

        private final Class<?> clazzzz;

        MyLovelyClasses(Class<?> aClass) {
            this.clazzzz = aClass;
        }

        public Class<?> getClazzzz() {
            return clazzzz;
        }
    }

//    public static class GenericException<T> extends Exception {
//        private final T value;
//
//        public GenericException(T value) {
//            this.value = value;
//        }
//
//        public T getValue() {
//            return value;
//        }
//    }


    public static class GenericException extends Exception {
        private final Object value;

        public GenericException(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }
    }

    private static void doSmth() {

    }
}
