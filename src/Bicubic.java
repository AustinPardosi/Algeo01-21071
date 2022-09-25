import java.lang.Math;  // Bakal pake pangkat

public class Bicubic {
    // Interpolasi Bicubic
    // 1. Matriks bicubicnyaa
    public static void matriksBicubic(double[][] A) {
        // Panggil fungsi eksetranl
        InputOutput M = new InputOutput();

        // Melakukan loop sepanjang elemen
        int i, j, m, n, k, l;
        // Variables assignment
        i = -1; j = -1; m = 0; n = 0;
        // Ngisi elemenn :((((
        for (k = 0; k < 16; k ++) {
            if (k % 4 == 0 && k != 0) {
                i = -1;
                j += 1;
            }
            for (l = 0; l < 16; l ++) {
                if (l % 4 == 0 && l != 0) {
                    m = 0;
                    n += 1;
                }
                A[k][l] = Math.pow(i,m) * Math.pow(j,n);
                // -0 handling
                if (A[k][l] == -0) {
                    A[k][l] = 0;
                }
                m += 1;
            }
            i += 1;
            m = 0;
            n = 0;
        }
        
        // Cetak matriks
        M.printToScreen(A, 16, 16);
    }

    // 3. Jalankannn!
    public static void main(String[] args) {
        double[][] matr = new double[16][16];
        matriksBicubic(matr);
    }
}
