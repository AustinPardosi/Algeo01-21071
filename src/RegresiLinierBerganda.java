import java.io.IOException;
import java.util.Scanner;



public class RegresiLinierBerganda {
    static InputOutput io = new InputOutput();
    // Regresi Linier
    // 1. Matriks Regresi Liniernya

    // Membaca input 
    public static void regresiLinierBerganda() throws IOException {
        double[][] matriks;
        double[] y;
        String path;
        int n, m; // n = jumlah peubah x, m = jumlah sampel
        
        System.out.println("PILIHAN INPUT");
        System.out.println("1. Keyboard");
        System.out.println("2. File");

        Scanner sc = new Scanner(System.in);
        int pilihan = sc.nextInt();
        if (pilihan == 1) {
            // masukan dari pengguna
            System.out.println("Masukkan jumlah peubah : ");
            n = sc.nextInt();
            System.out.println("Masukkan jumlah sampel : ");
            m = sc.nextInt();

            matriks = new double[m][n];
            y = new double[m];

           io.readByKeyboard(matriks, m, n);
        } else /* pilihan 2 */ {
            // membaca dari file
            System.out.println("Masukkan path : ");
            path = sc.next();
            double[][] tempMatrix = io.readByFile(path);
            matriks = new double[tempMatrix.length][tempMatrix[0].length - 1];
            y = new double[tempMatrix.length];
            for (int i = 0; i < matriks.length; i++) {
                for (int j = 0; j < matriks[i].length; j++) {
                    matriks[i][j] = tempMatrix[i][j];
                }
                y[i] = tempMatrix[i][tempMatrix[0].length - 1];
            }
        }

        // Hasil dari regresi linier
        double[] hasil = hasilRegresi(matriks, y);

        // Output persamaan regresi linier berganda
        System.out.printf("Persamaan hasil regresi linier berganda adalah y = %.2f",hasil[0]);
        for (int i = 1; i < hasil.length; i++) {
            if (hasil[i] > 0) {
                System.out.printf(" + %.2f x%d", hasil[i], i);
            } else {
                System.out.printf(" %.2f x%d", hasil[i], i);
            }
        }

        // Sisa taksiran
    }


    public static double[] hasilRegresi(double[][] matriks, double[] y) {
        // INI BELUM JADI YGY
        return y;

    }

        // sihiy progress austin wkwkwk
}
