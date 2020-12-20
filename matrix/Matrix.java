package matrix;

import java.util.Arrays;

public final class Matrix {
    private final int[][] matrix;
    private final int rows;
    private final int columns;

    public Matrix(int[][] matrix) {
        rows = matrix.length;
        columns = matrix[0].length;
        for(int i = 1; i < matrix.length; ++i) {
            if (columns != matrix[i].length) {
                throw new IllegalArgumentException("The matrix must be rectangular or square");
            }
        }
        this.matrix = new int[rows][columns];
        System.arraycopy(matrix, 0, this.matrix, 0, this.matrix.length);
    }

    public int getRows() {
        return rows;
    }

    public int getColumns() {
        return columns;
    }

    private Matrix cof(int p, int q) {
        Matrix result = new Matrix(new int[rows - 1][columns - 1]);
        int i = 0;
        int j = 0;

        for(int row = 0; row < rows; ++row) {
            for(int col = 0; col < columns; ++col) {
                if (row != p && col != q) {
                    result.set(i, j++, get(row, col));
                    if (j == columns - 1) {
                        j = 0;
                        ++i;
                    }
                }
            }
        }
        return result;
    }

    public int getDet() {
        if (rows != columns) {
            throw new IllegalArgumentException("The determinant is only defined for square matrix");
        } else if (rows == 1) {
            return matrix[0][0];
        } else {
            int det = 0;
            int sign = 1;

            for(int i = 0; i < columns; ++i) {
                Matrix cofactor = cof(0, i);
                det += sign * matrix[0][i] * cofactor.getDet();
                sign = -sign;
            }
            return det;
        }
    }

    public void set(int i, int j, int value) {
        matrix[i][j] = value;
    }

    public int get(int i, int j) {
        return matrix[i][j];
    }

    public Matrix plus(Matrix other) {
        if (rows == other.rows && columns == other.columns) {
            Matrix result = new Matrix(new int[rows][columns]);
            for(int i = 0; i < rows; ++i) {
                for(int j = 0; j < columns; ++j) {
                    result.set(i, j, get(i, j) + other.get(i, j));
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("The sum is defined only for matrices of the same size");
        }
    }

    public Matrix minus(Matrix other) {
        if (rows == other.rows && columns == other.columns) {
            Matrix result = new Matrix(new int[rows][columns]);
            for(int i = 0; i < rows; ++i) {
                for(int j = 0; j < columns; ++j) {
                    result.set(i, j, get(i, j) - other.get(i, j));
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("The difference is defined only for matrices of the same size");
        }
    }

    public Matrix times(Matrix other) {
        if (columns == other.rows) {
            Matrix result = new Matrix(new int[rows][other.columns]);
            for(int i = 0; i < rows; ++i) {
                for(int j = 0; j < other.columns; ++j) {
                    for(int k = 0; k < columns; ++k) {
                        result.set(i, j, result.get(i, j) + get(i, k) * other.get(k, j));
                    }
                }
            }
            return result;
        } else {
            throw new IllegalArgumentException("The product is defined only if left matrix columns same as right matrix rows");
        }
    }

    public Matrix times(int number) {
        Matrix result = new Matrix(new int[rows][columns]);
        for(int i = 0; i < rows; ++i) {
            for(int j = 0; j < columns; ++j) {
                result.set(i, j, get(i, j) * number);
            }
        }
        return result;
    }

    @Override
    public String toString() {
        return Arrays.deepToString(matrix);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        return other instanceof Matrix && Arrays.deepEquals(matrix, ((Matrix)other).matrix);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(matrix);
    }
}