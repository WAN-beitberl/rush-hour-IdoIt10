class Car {
    private int row;
    private int col;
    private int length;
    private boolean isHorizontal;
    private boolean isRed;

    public Car(int row, int col, int length, boolean isHorizontal, boolean isRed) {
        this.row = row;
        this.col = col;
        this.length = length;
        this.isHorizontal = isHorizontal;
        this.isRed = isRed;
    }
  
    public void move(boolean isForward) {
        if (isHorizontal) {
            if (isForward) {
                col++;
            } else {
                col--;
            }
        } else {
            if (isForward) {
                row++;
            } else {
                row--;
            }
        }
    }
  
    public boolean isValid(int boardSize) {
        return row >= 0 && row < boardSize && col >= 0 && col < boardSize && (isHorizontal ? col + length - 1 < boardSize : row + length - 1 < boardSize);
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getLength() {
        return length;
    }

    public boolean isHorizontal() {
        return isHorizontal;
    }

    public boolean isRed() {
        return isRed;
    }
}