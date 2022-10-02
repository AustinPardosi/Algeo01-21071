// import java.io.IOException;
// import java.util.Scanner;

public class RegresiLinierBerganda {
    static InputOutput io = new InputOutput();

    // Regresi Linier
    // 1. Matriks Regresi Liniernya
    // Membaca input
    // void regresiLinierBerganda() throws IOException {
    // double[][] matriks;
    // double[] y;
    // String path;
    // int n, m; // n = jumlah peubah x, m = jumlah sampel

    // System.out.println("PILIHAN INPUT");
    // System.out.println("1. Keyboard");
    // System.out.println("2. File");

    // Scanner sc = new Scanner(System.in);
    // int pilihan = sc.nextInt();
    // if (pilihan == 1) {
    // // masukan dari pengguna
    // System.out.println("Masukkan jumlah peubah : ");
    // n = sc.nextInt();
    // System.out.println("Masukkan jumlah sampel : ");
    // m = sc.nextInt();
    // System.out.println("Masukkan matriks persamaan : ");
    // matriks = new double[m][n];
    // y = new double[m];

    // for (int i = 0; i < m; i++) {
    // for (int j = 0; j < n; j++) {
    // matriks[i][j] = sc.nextDouble();
    // }
    // y[i] = sc.nextDouble();
    // }
    // // InputOutput io = new InputOutput();
    // // io.printToScreen(matriks, m, n);
    // } else /* pilihan 2 */ {
    // // membaca dari file
    // System.out.println("Masukkan path : ");
    // path = sc.next();
    // double[][] tempMatrix = io.readByFile(path);
    // matriks = new double[tempMatrix.length][tempMatrix[0].length - 1];
    // y = new double[tempMatrix.length];
    // for (int i = 0; i < matriks.length; i++) {
    // for (int j = 0; j < matriks[i].length; j++) {
    // matriks[i][j] = tempMatrix[i][j];
    // }
    // y[i] = tempMatrix[i][tempMatrix[0].length - 1];
    // }
    // }

    // // Hasil dari regresi linier
    // double[] hasil = hasilRegresi(matriks, y);

    // // Output persamaan regresi linier berganda
    // System.out.printf("Persamaan hasil regresi linier berganda adalah y = %.2f",
    // hasil[0]);
    // for (int i = 1; i < hasil.length; i++) {
    // if (hasil[i] > 0) {
    // System.out.printf(" + %.2f x%d", hasil[i], i);
    // } else {
    // System.out.printf(" %.2f x%d", hasil[i], i);
    // }
    // }

    // // Sisa taksiran
    // System.out.println();
    // System.out.println("\nTaksiran nilai fungsi");
    // System.out.printf("Masukkan %d peubah yang ingin ditaksir : \n", hasil.length
    // - 1);
    // double[] taksir = new double[hasil.length];
    // for (int i = 0; i < hasil.length - 1; i++) {
    // taksir[i] = sc.nextDouble();
    // }
    // double result = hasil[0];
    // for (int i = 0; i < hasil.length - 1; i++) {
    // result += hasil[i + 1] * taksir[i];
    // }
    // System.out.printf("Nilai taksirannya adalah %.03f\n", result);

    // }

    double[] hasilRegresi(double[][] matriks, double[] y) {
        // Matrix augmented
        int baris = matriks.length;
        int kolom = matriks[0].length;
        double[][] test = new double[baris][kolom + 2];
        // Membuat kolom pertama berisi angka 1 semua (b0)
        for (int i = 0; i < baris; i++) {
            test[i][0] = 1;
        }

        // Salin Matrix
        for (int i = 0; i < baris; i++) {
            for (int j = 1; j <= kolom; j++) {
                test[i][j] = matriks[i][j - 1];

            }
        }

        // Hasil Augmented
        for (int i = 0; i < baris; i++) {
            test[i][kolom + 1] = y[i];
        }

        // Normal Estimation Equation
        double[][] SPL = new double[kolom + 1][kolom + 2];
        for (int i = 0; i <= kolom; i++) {
            for (int j = 0; j <= kolom + 1; j++) {
                SPL[i][j] = 0;
                for (int k = 0; k < baris; k++) {
                    SPL[i][j] += test[k][i] * test[k][j];
                }
            }
        }
        InputOutput io = new InputOutput();
        System.out.println("\nDengan Normal Estimation Equation, diperoleh matrix SPL sebagai berikut");
        io.printToScreen(SPL, kolom + 1, kolom + 2);
        System.out.println("");

        Gauss ga = new Gauss();
        ga.gauss(SPL, kolom + 1, kolom + 2);

        // // Nilai X'X saja
        // double[][] xX = new double[kolom + 1][kolom + 1];
        // for (int i = 0; i <= kolom; i++) {
        //     for (int j = 0; j <= kolom; j++) {
        //         xX[i][j] = SPL[i][j];
        //     }
        // }
        // System.out.println("\nMatriks dari X'x : ");
        // io.printToScreen(xX, kolom+1, kolom+1);

        // Nilai Y saja
        double[] x = new double[kolom + 1];
        for (int i = 0; i <= kolom; i++) {
                x[i] = SPL[i][kolom + 1];

        }

        // // Nilai invers X'X
        // Invers iv = new Invers();
        // double[][] hasilInvers = new double[kolom + 1][kolom + 1];
        // System.out.println("\n(X'x)^-1 : ");
        // hasilInvers = iv.inverseAdjoint(xX, kolom + 1, kolom + 1);
        // System.out.println();

        // double[] temp = new double[kolom + 1];
        // // Kalikan matrix X'X dan Y
        // for (int i = 0; i < kolom + 1; i++) {
        //     temp[i] = 0;
        //     for (int j = 0; j < kolom + 1; j++) {
        //         temp[i] += hasilInvers[i][j] * x[j][i];
        //     }
        // }

        // return temp;
        return x;

    }
    // Akhirnya selesai
    // yuhuuuu

}