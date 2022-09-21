import java.lang.Math;  // Bakal pake pangkat

public class Kofaktor {
    // Jadi idenya bakal programming modular ((lagi)) doain jadi [mulai 10.34 pm - 11.38 pm]
    // 1. Ngapus elemen di baris dan kolom itu, jelas lahya kan mau ngitung kofak
    double[][] hapusbariskolom (double[][] matr, int baris, int kolom, int baris1, int kolom1) {
        // Inisiasi matriks hasil ngapus
        double [][] hasil = new double[baris - 1][kolom - 1];

        int k = 0, l = 0;
        for (int i = 0; i < baris; i += 1) { // Loop sepanjang baris
            if (i == baris1)  // Kalo barisnya sama kek baris1
                continue;  // skip
            for (int j = 0; j < kolom; j += 1) {  // Loop sepanjang kolom juga
                if (j == kolom1)  // Kalo kolomnya sama kek kolom1
                    continue;  // skip lagi h3h3

                // Assignment ke matriks hasil
                hasil[l][k] = matr[i][j];

                k = (k + 1) % (baris - 1);
                if (k == 0)
                    l++;
            }
        }
        return hasil;
    }

    // Ngitung determinan using kofak
    public double detkofak (double[][] matr, int baris, int kolom) {
        // Asumsi matriksnya pasti persegi yes, soalnya kalo ga gitu, gabisa
        Gauss ge = new Gauss(); // Import function gauss
        double det = 0; // Inisialisasi variabel ngitung determinan
        for (int i = 0; i < kolom ; i += 1) {
            det += matr[0][i] * ge.detGauss(hapusbariskolom (matr, baris, kolom, 0, i), baris - 1, kolom - 1) * Math.pow((-1), i);
        }
        return det;
    }
}
