public class RegresiLinierBerganda {
    static InputOutput io = new InputOutput();

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
        double[][] solusi = new double[kolom + 1][kolom + 2];
        for (int i = 0; i <= kolom; i++) {
            for (int j = 0; j <= kolom + 1; j++) {
                solusi[i][j] = 0;
                for (int k = 0; k < baris; k++) {
                    solusi[i][j] += test[k][i] * test[k][j];
                }
            }
        }

        InputOutput io = new InputOutput();
        System.out.println("\nDengan Normal Estimation Equation, diperoleh matrix solusi sebagai berikut");
        io.printToScreen(solusi, kolom + 1, kolom + 2);
        System.out.println("");

        SPL ga = new SPL();
        System.out.println("Hasilnya (var beta) adalah : ");
        double[] result = ga.SPLGauss(solusi, kolom + 1, kolom + 2);
        System.out.println();

        // Nilai Y saja
        double[] x = new double[kolom + 1];
        for (int i = 0; i < (kolom + 1); i++) {
            x[i] = result[i];
        }

        return x;

    }
}