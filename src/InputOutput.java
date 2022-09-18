import java.util.Scanner;

class InputOutput {
    // Matriks dasar ((sengaja dibuat berlebihan))
    double [][] Mat = new double[100][100];

    // Baca dari keyboard
    void readByKeyboard (int M, int N) {
        int i,j;

        Scanner scan = new Scanner(System.in);
        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                Mat[i][j] = scan.nextDouble();
            }
        }
        scan.close();
    }

    // Print ke layar
    void printToScreen (int M, int N) {
        int i,j;

        for (i = 0; i < M; i++) {
            for (j = 0; j < N; j++) {
                System.out.printf("%.2f ", Mat[i][j]);
            }
            System.out.println();
        }
    }
}
