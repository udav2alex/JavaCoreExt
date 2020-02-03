package homework2.my_exceptions;

public class MyArraySizeException extends IllegalArgumentException {
    private int illegalRow;
    private boolean wrongRowsNumber;

    public MyArraySizeException(String s, int illegalRow, boolean wrongRowsNumber) {
        super(s);
        this.illegalRow = illegalRow;
        this.wrongRowsNumber = wrongRowsNumber;
    }

    public int getIllegalRow() {
        return illegalRow;
    }

    public boolean isWrongRowsNumber() {
        return wrongRowsNumber;
    }
}
