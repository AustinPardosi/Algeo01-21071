public class Parametric {

    GaussJordan gj = new GaussJordan();

    String[] parametrik(double[][] matr, boolean gaussJordan) {
        // Hasil bentuk parametrik
        String[] hasil = new String[matr[0].length - 1]; // array hasilnya nanti (output)
        String cek = "";
        int total = 0;
        int[] indeksVar = new int[(matr[0].length) - 1];

        if (gaussJordan) { // kl gauss di gauss jordan kan dulu
            gj.gaussJordan(matr, matr.length, matr[0].length);
        }

        for (int i = 0; i < (matr[0].length) - 1; i++) { // i = baris
            if (gj.leadingOneRow(matr, matr.length, i) == -999) { // Mengembalikan nilai indeks baris kalo ada leading
                                                                  // one, -999 kalo gaada
                indeksVar[total] = i; // bisa diisi x1,x2 // diambil dari gauss jordan
                total++;
            }
        }

        for (int i = 0; i < (matr[0].length) - 1; i++) { // i = kolom
            int barisEselon = gj.leadingOneRow(matr, matr.length, i);
            if (barisEselon != (-1)) {
                cek = cek + ("x" + (i + 1) + " = " + matr[barisEselon][(matr[0].length) - 1]);
                for (int j = (i + 1); j < (matr[0].length) - 1; j++) {
                    double xVal = matr[barisEselon][j];
                    if (xVal != 0) {
                        cek = cek + (" + (" + (-xVal) + ")" + "x" + (j + 1));
                    }
                }
            } else {
                cek = "x" + (i + 1) + " = " + "x" + (i + 1);
            }
            hasil[i] = cek;
            cek = "";
        }

        for (int i = 0; i < matr[0].length - 1; i++) {
            System.out.println(hasil[i]);
        }

        return hasil;
    }
}