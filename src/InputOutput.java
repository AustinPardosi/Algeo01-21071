import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

class InputOutput {
    // Baca dari keyboard
    void readByKeyboard(Scanner scan, double[][] matr, int M, int N) {
        int i, j;
        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                matr[i][j] = scan.nextDouble();
            }
        }
    }

    // Print ke layar
    void printToScreen(double[][] matr, int M, int N) {
        int i, j;

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                System.out.printf("%.2f ", matr[i][j]);
            }
            System.out.println();
        }
    }

    // Baca dari file
    double[][] readByFile(String path) {
        int[] rowCol = countRowCol(path);
        double[][] matrix = new double[rowCol[0]][rowCol[1]];
        int i = 0;
        int j = 0;
        try {
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitStr = data.split("\\s+");
                j = 0;
                for (String str : splitStr) {
                    matrix[i][j] = Double.parseDouble(str);
                    j += 1;
                }
                i += 1;
            }

            System.out.println("Berhasil membaca file pada: " + path);
            myReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return matrix;
    }

    // Hitung row dan col dari file
    int[] countRowCol(String path) {
        // mereturn array berisi [row, col];
        int[] rowCol = new int[2];
        try {
            int i = 0;
            int j = 0;
            File myObj = new File(path);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                String[] splitStr = data.split("\\s+");
                j = 0;
                while (j < splitStr.length) {
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

    // Write dari file
    // Belum di tes
    boolean writeFile(String path, double[][] matrix) {
        try {
            FileWriter myWriter = new FileWriter(path);
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix[i].length; j++) {
                    myWriter.write(Double.toString(matrix[i][j]) + " ");
                }
                myWriter.write("\n");
            }
            myWriter.write("\n");
            myWriter.close();
            System.out.println("Berhasil menuliskan file pada: " + path);
            return true;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            return false;
        }
    }
}
