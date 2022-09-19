import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

class InputOutput {
    // Baca dari keyboard
    void readByKeyboard (double[][] matr, int M, int N) {
        int i,j;

        Scanner scan = new Scanner(System.in);
        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                matr[i][j] = scan.nextDouble();
            }
        }
        scan.close();
    }

    // Print ke layar
    void printToScreen (double[][] matr, int M, int N) {
        int i,j;

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                System.out.printf("%.2f ", matr[i][j]);
            }
            System.out.println();
        }
    }

    // Baca dari file
    public static float[][] readByFile(String filename) {
        int[] rowCol = countRowCol("filename");
        float[][] matrix = new float[rowCol[0]][rowCol[1]];
        int i = 0;
        int j = 0;
        try {
            File myObj = new File("test/" + filename + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitStr = data.split("\\s+");
                j = 0;
                for (String str : splitStr) {
                    matrix[i][j] = Float.parseFloat(str);
                    j += 1;
                }
                i += 1;
            }
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return matrix;
    }

    // Hitung row dan col dari file
    public static int[] countRowCol(String filename) {
        // mereturn array berisi [row, col];
        int[] rowCol = new int[2];
        try {
            int i = 0;
            int j = 0;
            File myObj = new File("test/" + filename + ".txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitStr = data.split("\\s+");
                j = 0;
                for (String str : splitStr) {
                    j += 1;
                }
                i += 1;
            }
            rowCol[0] = i;
            rowCol[1] = j;
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return rowCol;
    }

}
