// Ini spek dari pdf, aku copas soalnya males bolak-balik buka docs

// Untuk persoalan interpolasi, masukannya jika dari keyboard adalah
// n, (x0, y0), (x1, y1), ..., (xn, yn), dan nilai x yang akan ditaksir nilai fungsinya.
// Jika masukannya dari file, maka titik-titik dinyatakan pada setiap baris tanpa koma
// dan tanda kurung. Misalnya jika titik-titik datanya adalah (8.0, 2.0794), (9.0,
// 2.1972), dan (9.5, 2.2513), maka di dalam file text ditulis sebagai berikut:
// 8.0 2.0794
// 9.0 2.1972
// 9.5 2.2513

// Untuk persoalan polinom interpolasi dan regresi, luarannya adalah persamaan
// polinom/regresi dan taksiran nilai fungsi pada x yang diberikan.
// Contoh luaran untuk interpolasi adalah
// 𝑓(𝑥)= -0.0064𝑥^2 + 0.2266x + 0.6762 , 𝑓(5) = … 

public class Polinom {
    static void polinom(int n, double[][] points, int x) {
        double[][] aug = new double[n][n + 1];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                aug[i][j] = Math.pow(points[i][0], j);
            }
        }

        for (int i = 0; i < n; i++) {
            aug[i][n] = points[i][1];
        }

        double calculate = 0;

        // Penyelesaian dengan metode eliminasi Gauss
        SPL spl = new SPL();
        double[] hasil = spl.SPLGauss(aug, n, n + 1);
        System.out.print("f(x) = ");
        for (int i = hasil.length - 1; i > -1; i--) {
            System.out.printf("%.03f", hasil[i]);
            if (i != 0) {
                if (i == 1) {
                    System.out.print("x");
                } else {
                    System.out.printf("x^%d", i);
                }
                System.out.print(" + ");
            }
            calculate += Math.round(hasil[i] * 1000.0) / 1000.0 * Math.pow(x, i);
        }

        System.out.println();
        System.out.printf("f(%d) = ", x);
        System.out.printf("%.03f\n", calculate);

    }

    // TESTING POLINOM
    // public static void main(String[] args) {
    // double[][] matrix = { { 8.0, 2.0794 }, { 9.0, 2.1972 }, { 9.5, 2.251 } };

    // polinom(3, matrix, 5);
    // }
}
