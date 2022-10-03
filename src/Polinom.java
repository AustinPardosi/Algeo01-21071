public class Polinom {
    String[][] polinom(int n, double[][] points, double x) {
        String[][] res = new String[2][1];
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
        String[] hasilString = spl.SPLGauss(aug, n, n + 1);
        double[] hasil = new double[hasilString.length];

        for (int i = 0; i < hasilString.length; i++) {
            hasil[i] = Double.parseDouble(hasilString[i]);
        }

        res[0][0] = "f(x) = ";
        System.out.print("f(x) = ");
        for (int i = hasil.length - 1; i > -1; i--) {
            res[0][0] += Math.round(hasil[i] * 1000.0) / 1000.0;
            System.out.printf("%.03f", hasil[i]);
            if (i != 0) {
                if (i == 1) {
                    res[0][0] += "x";
                    System.out.print("x");
                } else {
                    res[0][0] += "x^" + i;
                    System.out.printf("x^%d", i);
                }
                res[0][0] += " + ";
                System.out.print(" + ");
            }
            calculate += Math.round(hasil[i] * 1000.0) / 1000.0 * Math.pow(x, i);
        }

        res[1][0] = "f(" + x + ") = " + Math.round(calculate * 1000.0) / 1000.0;
        System.out.println();
        System.out.printf("f(%.02f) = ", x);
        System.out.printf("%.03f\n", calculate);

        return res;
    }
}
