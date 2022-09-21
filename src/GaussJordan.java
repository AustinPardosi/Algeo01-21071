public class GaussJordan {
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
    // NOTE :
    // udah berhasil kalo masukan awalnya udah eselon baris
    // kalo manggil fungsi gauss dulu masih aneh hasilnya
    void gaussJordan(double[][] matr, int row, int col) {

        // // KALO MAU NYOBA MANGGIL GAUSS DULU, UN-COMMENT BAGIAN INI
        // Gauss ge = new Gauss();
        // // manggil gauss dulu sebelum dilanjutin gauss jordan sampe eselon tereduksi
        // ge.gauss(matr, row, col);

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
                    }
                }
            }
        }
    }
}
// sekali lagi, kalo ga ngerti tanya aku aja ya
// rada bingung kalo jelasin pake ketikan gini
// aku jelasin kalo ketemu aja wkwkw
// - oliv