package homework2.my_exceptions;

public class MyArrayDataException extends NumberFormatException {
    private int errorRow;
    private int errorColumn;

    public MyArrayDataException(String s, int errorRow, int errorColumn) {
        super(s);
        this.errorRow = errorRow;
        this.errorColumn = errorColumn;
    }

    public int getErrorRow() {
        return errorRow;
    }

    public int getErrorColumn() {
        return errorColumn;
    }
}
