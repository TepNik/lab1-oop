import matrix.Matrix;
import java.util.Random;


public class Main {
    public static int[][] random2dArray(int rows, int cols, int max) {
        Random rand = new Random();
        int[][] result = new int[rows][cols];
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                result[i][j] = rand.nextInt(max + 1);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        Matrix m1 = new Matrix(random2dArray(4, 3, 20));
        Matrix m2 = new Matrix(random2dArray(4, 3, 20));
        Matrix m3 = new Matrix(random2dArray(3, 3, 20));

        System.out.println("m1 = " + m1);
        System.out.println("m1 has " + m1.getRows() + " rows and " + m1.getColumns() + " columns");
        System.out.println("Changing m1[0][0] value to -1...");
        m1.set(0,0, -1);
        System.out.println("Now m1[0][0] value is " + m1.get(0, 0));
        System.out.println("m1 = " + m1);
        System.out.println("m2 = " + m2);
        System.out.println("m3 = " + m3);


        System.out.println("m1 + m2 = " + (m1.plus(m2)));
        System.out.println("m1 - m2 = " + (m1.minus(m2)));
        System.out.println("m2 * m3 = " + (m2.times(m3)));
        System.out.println("m2 * 5 = " + (m2.times(5)));
        System.out.println("det(m3) = " + m3.getDet());
        System.out.println("m1 == m2 -> " + m1.equals(m2));
    }
}
