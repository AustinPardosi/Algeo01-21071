import java.lang.Math;  // Bakal pake pangkat

public class Kofaktor {
    // Jadi idenya bakal programming modular ((lagi)) doain jadi [mulai 10.34 pm - 11.38 pm]
    // 1. Ngapus elemen di baris dan kolom itu, jelas lahya kan mau ngitung kofak
    double[][] hapusBarisKolom (double[][] matr, int baris, int kolom, int baris1, int kolom1) {
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

                // Pastiin supaya nilai k ga overflow atau underflow
                k = (k + 1) % (baris - 1);
                if (k == 0)
                    l += 1;
            }
        }
        return hasil;
    }

    // Ngitung determinan using kofak
    public double detKofak (double[][] matr, int baris, int kolom) {
        // Asumsi matriksnya pasti persegi yes, soalnya kalo ga gitu, gabisa
        Gauss ge = new Gauss(); // Import function gauss
        double det = 0; // Inisialisasi variabel ngitung determinan
        if (baris == 2) {
            det += (matr[0][0] * matr[1][1]) - (matr[0][1] * matr[1][0]);
        }
        else {
            for (int i = 0; i < kolom ; i += 1) {
                det += matr[0][i] * ge.detGauss(hapusBarisKolom (matr, baris, kolom, 0, i), baris - 1, kolom - 1) * Math.pow((-1), i);
            }
        }
        return det;
    }

    // Sekarang bikin matriks kofaktor
    double[][] matriksKofaktor (double[][] matr, int baris, int kolom) {
        // Inisalisasi matriks hasilnya dulu
        double [][] hasil = new double[baris][kolom];
        Gauss ge = new Gauss(); // Import function gauss

        // Ya biasa bikin double loop
        for (int i = 0; i < baris; i += 1) {
            for (int j = 0; j < kolom; j += 1) {
                // Assign elemen hasil sesuai sama definisi kofaktor
                hasil[i][j] = ge.detGauss(hapusBarisKolom (matr, baris, kolom, i, j), baris - 1, kolom - 1) * Math.pow((-1), (i+j));
            }
        }
        return hasil;
    }

    // 2. Matriks adjoint, yaitu transpose dari matriks kofaktor
    double[][] matriksAdjoint (double[][] matr, int baris, int kolom) {
        // Inisalisasi matriks adjoint dulu
        double [][] adjoint = new double[baris][kolom];

        // Ya biasa bikin double loop
        for (int i = 0; i < baris; i += 1) {
            for (int j = 0; j < kolom; j += 1) {
                // Assign elemen hasil sesuai sama definisi kofaktor
                adjoint[i][j] = (matriksKofaktor(matr,baris,kolom))[j][i];
            }
        }
        return adjoint;
    }

    // Last, inverse by adjoint
    void inverseAdjoint (double[][] matr, int baris, int kolom) {
        // Inisalisasi matriks adjoint dulu
        double [][] inverse = new double[baris][kolom];

        // Cek dulu, inget, kalo matriks determinannya 0, artinya gapunya invers
        double determinan = detKofak(matr,baris,kolom);
        if (determinan == 0) {
            System.out.println("Matriks tidak memiliki invers.");
        } else {
            // Ya biasa bikin double loop
            for (int i = 0; i < baris; i += 1) {
                for (int j = 0; j < kolom; j += 1) {
                    // Assign elemen hasil sesuai sama definisi kofaktor
                    inverse[i][j] = (matriksAdjoint(matr,baris,kolom))[i][j] * 1/determinan;
                }
            }
            // Yak, final bgt, cetak inversenya
            InputOutput M = new InputOutput();
            M.printToScreen(inverse, baris, kolom);
        }
    }
}
