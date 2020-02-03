package homework2;

import homework2.my_exceptions.MyArrayDataException;
import homework2.my_exceptions.MyArraySizeException;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        String[][] correctArray = new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13", "14", "15", "16"}
        };

        String[][] wrongRowsArray = new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"}
        };

        String[][] wrongSizeArray = new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"9", "10", "11", "12"},
                {"13"}
        };

        String[][] wrongElementArray = new String[][]{
                {"1", "2", "3", "4"},
                {"5", "6", "7", "8"},
                {"here is error", "9", "10", "12"},
                {"13", "14", "15", "16"}
        };

        String[][][] arrays = new String[][][] {
                correctArray, wrongRowsArray, wrongSizeArray, wrongElementArray
        };

        for (String[][] array : arrays) {
            for (String[] strings : array) {
                System.out.println(Arrays.toString(strings));
            }
            tryArray(array);
            System.out.println("");
        }
    }

    private static void tryArray(String[][] strings) {
        try {
            System.out.println("Sum of integers in the strings: " + getSum(strings));
            System.out.println("Success!");
        } catch (MyArraySizeException e) {
            System.out.println("Your array has wrong dimensions!");
            if (e.isWrongRowsNumber()) {
                System.out.println("Your array has wrong rows number!");
            } else {
                System.out.println("Your array has wrong columns number in row " + e.getIllegalRow());
            }
        } catch (MyArrayDataException e) {
            System.out.println("Your array has correct dimensions...");
            System.out.println("Number format error in row " +
                    (e.getErrorRow() + 1) + " column " + (e.getErrorColumn() + 1) +
                    ", wrong value: " + strings[e.getErrorRow()][e.getErrorColumn()]);
        }
    }

    private static void checkArraySize(String[][] strings) throws MyArraySizeException {
        if (strings.length == 4) {
            for (int i = 0; i < strings.length; i++) {
                if (strings[i].length != 4) {
                    throw new MyArraySizeException("Wrong size array", i, false);
                }
            }
        } else {
            throw new MyArraySizeException("Wrong size array", 0, true);
        }
    }

    private static int getSum(String[][] strings) throws MyArraySizeException {
        checkArraySize(strings);

        int result = 0;

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                try {
                    result += Integer.parseInt(strings[i][j]);
                } catch (NumberFormatException e) {
                    throw new MyArrayDataException("Wrong number format", i, j);
                }
            }
        }

        return result;
    }
}
