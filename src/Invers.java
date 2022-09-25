import java.lang.Math;  // Bakal pake pangkat

public class Invers {
    // Mendatangkan fungsi eksternal [kumpulin sini]
    Determinan d = new Determinan();
    InputOutput M = new InputOutput();
    Kofaktor k = new Kofaktor();

    // [ procedure inversGaussJordan ]
    // Desc :
    // mencari invers dari matriks dengan cara gauss jordan
    // matriks eselon tereduksi
    // Parameter input :
    // - matr (matriksnya)
    // - row (jumlah baris yang juga adalah jumlah kolom)
    // prekondisi : row dan col bernilai sama, jadi nilai row dapat
    // berperan di iterasi kolom
    void inversGaussJordan(double[][] matr, int row) {
        double[][] aug = new double[row][2 * row]; // inisialisasi matriks augmented [ A | I ]
        // copy elemen matr ke aug
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                aug[i][j] = matr[i][j];
            }
        }

        // tambahin di sebelah kanannya dengan matriks identitas
        // supaya jadi bentuk [ A | I ]
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (i != j) {
                    aug[i][j + row] = 0; // kolomnya ditambah row supaya jadi di sebelah kanannya
                } else {
                    aug[i][j + row] = 1; // kolomnya ditambah row supaya jadi di sebelah kanannya
                }
            }
        }

        boolean singular = false;
        for (int i = 0; i < row; i++) {
            // cek apakah determinan 0 (yaitu ada elemen di diagonal yang bernilai 0)
            if (aug[i][i] == 0) {
                System.out.println("Matriks tidak memiliki balikan");
                singular = true;
                break; // keluar dari loop
            }

            // ini intinya ngelakuin gauss jordan ke matriks aug nya
            for (int j = 0; j < row; j++) {
                if (i != j) {
                    // ambil rasionya, yaitu elmt aug[j][i] dibagi elmt aug[i][i] atau diagonalnya
                    double ratio = aug[j][i] / aug[i][i];
                    // apply gauss jordan ke matriks aug nya (termasuk si matriks identitas,
                    // makanya looping sampe 2*row)
                    for (int k = 0; k < 2 * row; k++) {
                        aug[j][k] -= ratio * aug[i][k]; // dikurangin kayak biasa la ya
                    }
                }
            }
        }

        // bikin nilai diagonal matriks awal jadi 1 semua
        // otomatis matriks identitasnya juga harus ikut dibagi untuk membuat
        // nilai diagonal matriks awal jadi 1 semua
        for (int i = 0; i < row; i++) {
            for (int j = row; j < 2 * row; j++) {
                aug[i][j] = aug[i][j] / aug[i][i];
            }
        }

        // assign balik nilai invers ke matriks awal (yaitu matr)
        if (!singular) {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < row; j++) {
                    matr[i][j] = aug[i][j + row]; // lagi-lagi, kolomnya ditambah row supaya dapet nilai si inversnya
                }
            }
        }
    }

    // 2. Matriks Kofaktor
    double[][] matriksKofaktor (double[][] matr, int baris, int kolom) {
        // Inisalisasi matriks hasilnya dulu
        double [][] hasil = new double[baris][kolom];

        // Ya biasa bikin double loop
        for (int i = 0; i < baris; i += 1) {
            for (int j = 0; j < kolom; j += 1) {
                // Assign elemen hasil sesuai sama definisi kofaktor
                hasil[i][j] = d.detGauss(k.hapusBarisKolom (matr, baris, kolom, i, j), baris - 1, kolom - 1) * Math.pow((-1), (i+j));
            }
        }
        return hasil;
    }

    // 3. Matriks adjoint, yaitu transpose dari matriks kofaktor
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

    // 2. Inverse by adjoint
    void inverseAdjoint (double[][] matr, int baris, int kolom) {
        // Inisalisasi matriks adjoint dulu
        double [][] inverse = new double[baris][kolom];

        // Cek dulu, inget, kalo matriks determinannya 0, artinya gapunya invers
        double determinan = d.detKofak(matr,baris,kolom);
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
            M.printToScreen(inverse, baris, kolom);
        }
    }
}
