public class GaussJordan {
    // Mendatangkan fungsi eksternal [kumpulin sini]
    Gauss ge = new Gauss();

    // [ function leadingOneRow ]
    // Desc :
    // Mencari indeks baris yang mengandung leading one di kolom tersebut
    // Mengembalikan nilai indeks baris kalo ada leading one, -999 kalo gaada
    // Parameter input :
    // - matr (matriksnya)
    // - row (jumlah baris)
    // - j (indeks kolom yang mau dicari leading one nya)
    int leadingOneRow(double[][] matr, int row, int j) {
        int i; // inisiasi variabel i buat looping baris

        // looping dari baris terakhir (indeks ke row-1) sampai awal (indeks 0)
        // kenapa looping dari belakang?
        // supaya angka 1 yang ketemu itu pasti leading one
        // kalo dari baris pertama, bisa jadi angka 1 nya itu bukan leading one
        // contoh :
        // 1 2 4 1 5 -> angka 1 di sebelah angka 4 bukan leading one
        // 0 0 1 7 0
        // 0 0 0 1 2
        for (i = row - 1; i >= 0; i--) {
            // kalo ketemu elemen yang bernilai 1 (artinya udh ketemu leading one nya), maka
            // keluar dari loop
            if (matr[i][j] == 1) {
                break;
            }
        }

        // setelah keluar looping
        if (i != row) {
            // kalo i != row, artinya ada elemen matr[i][j] yang bernilai 1
            // (berarti punya leading one di kolom itu)
            // return indeks barisnya
            return i;
        } else {
            // kalo gaada leading one di kolom itu, berarti nge-return -999 (kayak MARK-nya)
            return -999;
        }

    }

    // [ procedure gaussJordan ]
    // Desc :
    // ngelakuin prosedur gauss jordan sih intinya hingga terbentuk
    // matriks eselon tereduksi
    // Parameter input :
    // - matr (matriksnya)
    // - row (jumlah baris)
    // - col (jumlah kolom)
    void gaussJordan(double[][] matr, int row, int col) {

        Gauss ge = new Gauss();
        // manggil gauss dulu sebelum dilanjutin gauss jordan sampe eselon tereduksi
        ge.gauss(matr, row, col);

        // looping kolom
        for (int j = 0; j < col; j++) {
            // cari indeks baris leading one di kolom j
            int leadingOneRow = leadingOneRow(matr, row, j);

            // looping dari baris pertama (alias indeks 0) sampai sebelum baris si leading
            // one
            for (int i = 0; i < leadingOneRow; i++) {
                // ini ngecek dulu apakah elemen dari matr[i][j] itu 0 apa engga
                // kalo 0, brarti ga perlu diapa-apain (karna tujuan kita mau bikin semua elemen
                // di atas leading one jadi 0)
                // kalo bukan 0, berarti perlu dikurangin supaya jadi 0
                if (matr[i][j] != 0) {
                    // nah ini pengali diisi dengan nilai dari matr[i][j] soalnya dia yang bisa
                    // bikin elemen itu jadi 0 kalo dikurangin (agak bingung jelasinnya gimana, kalo
                    // ga ngerti tanya aku aja lah - oliv)
                    double pengali = matr[i][j];
                    // ini looping buat kolom dari indeks ke-j sampe akhir
                    for (int k = j; k < col; k++) {
                        // tinggal dikurangin aja
                        // elemen matr[i][k] dengan pengali * matr[leadingOneRow][k]
                        matr[i][k] -= pengali * matr[leadingOneRow][k];
                        if (matr[i][k] == -0) {
                            matr[i][k] = 0;
                        }
                    }
                } else if (matr[i][j] == -0) {
                    matr[i][j] = 0;
                }
            }
        }
    }

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
        double[][] aug = new double[row][2 * row]; // matriks augmented [ A | I ]
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

        for (int i = 0; i < row; i++) {
            // cek apakah determinan 0 (yaitu ada elemen di diagonal yang bernilai 0)
            if (aug[i][i] == 0) {
                System.out.println("Matriks tidak memiliki balikan");
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
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                matr[i][j] = aug[i][j + row]; // lagi-lagi, kolomnya ditambah row supaya dapet nilai si inversnya
                if (matr[i][j] == -0) {
                    matr[i][j] = 0;
                }
            }
        }
    }
}
// sekali lagi, kalo ga ngerti tanya aku aja ya
// rada bingung kalo jelasin pake ketikan gini
// aku jelasin kalo ketemu aja wkwkw
// - oliv
