import java.lang.Math; // Bakal pake pangkat
// import java.util.*; // Scanner

public class Bicubic {
    // Interpolasi Bicubic
    // 1. Matriks bicubicnyaa
    double[][] matriksBicubic() {
        // Melakukan loop sepanjang elemen
        int i, j, m, n, k, l;
        // Variables assignment
        i = -1;
        j = -1;
        m = 0;
        n = 0;
        // Inisiasi matriks
        double[][] A = new double[16][16];
        // Ngisi elemenn :((((
        for (k = 0; k < 16; k++) {
            if (k % 4 == 0 && k != 0) {
                i = -1;
                j += 1;
            }
            for (l = 0; l < 16; l++) {
                if (l % 4 == 0 && l != 0) {
                    m = 0;
                    n += 1;
                }
                A[k][l] = Math.pow(i, m) * Math.pow(j, n);
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
        return A;
    }

    // 2. Perkalian kedua matriks
    double[][] matriksKali(double[][] A, double[][] B, int m, int n, int r) {
        // m = baris matriks 1
        // n = kolom matriks 2
        // r = kolom matriks 1 = baris matriks 2
        // Melakukan loop sepanjang elemen
        int x, y, z;
        // Inisiasi matriks
        double[][] kali = new double[m][n];
        // Mengalikan kedua matriks
        for (x = 0; x < m; x++) {
            for (y = 0; y < n; y++) {
                double total = 0;
                for (z = 0; z < r; z++) {
                    total += A[x][z] * B[z][y];
                }
                kali[x][y] += total;
            }
        }
        return kali;
    }

    // 3. Transpose matriks, khusus 4x4
    double[][] matriksTranspose(double[][] A, int baris, int kolom) {
        // Melakukan loop sepanjang elemen
        int i, j;
        // Inisiasi matriks
        double[][] transpose = new double[kolom][baris];
        // Transpose matriks
        for (i = 0; i < baris; i += 1) {
            for (j = 0; j < kolom; j += 1) {
                // Assign elemen hasil sesuai sama definisi kofaktor
                transpose[i][j] = A[j][i];
            }
        }
        return transpose;
    }

    // 4. Convert matrix size
    double[][] matriksConvert(double[][] mat, int baris1, int kolom1, int baris2, int kolom2) {
        // Inisiasi indeks untuk dianalisis
        int idx = 0;

        // Inisiasi matriks baru, hasil konversi
        double[][] res = new double[baris2][kolom2];

        if (baris1 * kolom1 != baris2 * kolom2) {
            return res;
        }

        // Traversal sepanjang list, untuk mindah
        for (int i = 0; i < baris1; i++) {
            for (int j = 0; j < kolom1; j++) {
                res[idx / kolom2][idx % kolom2] = mat[i][j];
                // jangan lupa naikin idx
                idx++;
            }
        }
        return res;
    }
}
