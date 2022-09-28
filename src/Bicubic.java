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

    // 5. Jalankannn!
    // void main(String[] args) {
    // // Panggil fungsi eksetranl
    // Invers inv = new Invers();

    // // Assign matr dengan matriks bicubic hasil mikir sejam kita
    // double[][] matr = matriksBicubic();

    // // Inverskan matriks tersebut
    // inv.inversGaussJordan(matr, 16);

    // // Define matriks dan nilai yang akan dianalisis
    // double[][] A = new double[4][4];
    // double[][] trans1 = new double[4][4];
    // double[][] pers = new double[16][1];
    // double[][] kali = new double[16][1];
    // double[][] xval = new double[1][4];
    // double[][] yval = new double[4][1];
    // double[][] hasil1 = new double[1][4];
    // double[][] hasil2 = new double[1][1];

    // // Nah sekarang minta matriks
    // System.out.println("Masukkan matriks yang akan di interpolasi bicubic");
    // int m, n;

    // Scanner scan = new Scanner(System.in);
    // for (m = 0; m < 4; m++) {
    // for (n = 0; n < 4; n++) {
    // A[m][n] = scan.nextDouble();
    // }
    // }

    // // Masukkan titik yang ingin dianalisis
    // System.out.println("Masukkan nilai x yang akan dianalisis");
    // double x = scan.nextDouble();
    // System.out.println("Masukkan nilai y yang akan dianalisis");
    // double y = scan.nextDouble();

    // // Transpose dulu matriks yang diinputkan
    // trans1 = matriksTranspose(A, 4, 4);
    // /*
    // * System.out.println("Hasil transpose A");
    // * for (int k = 0; k < 4; k++) {
    // * for (int l = 0; l < 4; l++) {
    // *
    // * System.out.printf("%.2f ", trans1[k][l]);
    // * }
    // * System.out.println();
    // * }
    // */

    // // Konversi dari bentuk 4x4 jadi 16x1
    // pers = matriksConvert(trans1, 4, 4, 16, 1);
    // /*
    // * System.out.println("Konversi ukuran matriks A");
    // * for (int k = 0; k < 16; k++) {
    // * for (int l = 0; l < 1; l++) {
    // *
    // * System.out.printf("%.2f ", pers[k][l]);
    // * }
    // * System.out.println();
    // * }
    // */

    // // Perkalian transpose dengan matriks kofaktor kitah, ketemu koef A
    // kali = matriksKali(matr, pers, 16, 1, 16);
    // /*
    // * System.out.println("Matriks hasil kali");
    // * for (int k = 0; k < 16; k++) {
    // * for (int l = 0; l < 1; l++) {
    // *
    // * System.out.printf("%.2f ", kali[k][l]);
    // * }
    // * System.out.println();
    // * }
    // */

    // // Konversi hasil koef A dari 16x1 ke 4x4
    // A = matriksConvert(kali, 16, 1, 4, 4);
    // /*
    // * System.out.println("Matriks hasil convert ke 4x4");
    // * for (int k = 0; k < 4; k++) {
    // * for (int l = 0; l < 4; l++) {
    // *
    // * System.out.printf("%.2f ", A[k][l]);
    // * }
    // * System.out.println();
    // * }
    // */

    // // Transpose koef
    // A = matriksTranspose(A, 4, 4);
    // /*
    // * System.out.println("Matriks hasil convert ke 4x4");
    // * for (int k = 0; k < 4; k++) {
    // * for (int l = 0; l < 4; l++) {
    // *
    // * System.out.printf("%.2f ", A[k][l]);
    // * }
    // * System.out.println();
    // * }
    // */

    // // Bikin list baru isinya [1,x,x^2,x^3] sama [1,y,y^2,y^3]
    // for (int i = 0; i < 4; i++) {
    // xval[0][i] = Math.pow(x, i);
    // yval[i][0] = Math.pow(y, i);
    // }

    // /*
    // * System.out.println("Hasil matriks X");
    // * for (int k = 0; k < 4; k++) {
    // * System.out.printf("%.2f ", xval[0][k]);
    // * }
    // *
    // * System.out.println("Hasil matriks Y");
    // * for (int k = 0; k < 4; k++) {
    // * System.out.printf("%.2f ", yval[k][0]);
    // * }
    // */

    // // Perkalian matriks
    // hasil1 = matriksKali(xval, A, 1, 4, 4);
    // /*
    // * System.out.println("Matriks hasil kali xval dengan A");
    // * for (int k = 0; k < 1; k++) {
    // * for (int l = 0; l < 4; l++) {
    // *
    // * System.out.printf("%.2f ", hasil1[k][l]);
    // * }
    // * System.out.println();
    // * }
    // */

    // hasil2 = matriksKali(hasil1, yval, 1, 1, 4);
    // /*
    // * System.out.println("HASILLL");
    // * for (int k = 0; k < 1; k++) {
    // * for (int l = 0; l < 1; l++) {
    // * System.out.printf("%.2f ", hasil2[k][l]);
    // * }
    // * System.out.println();
    // * }
    // */

    // // Keluar nilainya YEY
    // System.out.println("Nilai hasil interpolasi adalah");
    // System.out.printf("%.2f", hasil2[0][0]);

    // scan.close();
    // }
}
