public class SPL {
    // Mendatangkan fungsi eksternal [kumpulin sini]
    Gauss ge = new Gauss();
    Invers inv = new Invers();

    // [ procedure SPLInvers ]
    // Desc :
    // mencari solusi SPL dengan metode invers
    // inversnya pake gauss jordan
    // Parameter input :
    // - matr (matriksnya)
    // - row (jumlah baris)
    // - col (jumlah kolom, yaitu row + 1 karena ketambahan b)
    // prekondisi : masukannya matriks augmented [A | b] untuk persamaan Ax = b
    void SPLInvers(double[][] matr, int row, int col) {

        // jadi idenya kita mau pisah dulu matriks augmentednya jadi A sama b
        // matriks a disimpen di variabel invers (karena nantinya bakal diinvers)
        // matriks b disimpen di variabel b

        double[][] invers = new double[row][row]; // inisialisasi matriks invers
        double[][] temp = new double[row][row]; // copy an invers
        double[][] b = new double[row][1]; // inisialisasi matriks b
        double[][] hasil = new double[row][1]; // inisialisasi matriks hasil
        // copy elemen matr (yang bagian A saja) ke invers dan temp
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                invers[i][j] = matr[i][j];
                temp[i][j] = matr[i][j];
            }
        }
        // copy elemen matr bagian b ke variabel b
        for (int i = 0; i < row; i++) {
            b[i][0] = matr[i][col - 1];
        }
        // diinvers buat dapet invers dari matriks A
        inv.inversGaussJordan(invers, row);

        // cek apakah matriks singular
        // kalo iya -> ga punya balikan -> SPL ga bisa diselesaikan dengan invers
        int count = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < row; j++) {
                if (invers[i][j] == temp[i][j]) {
                    count += 1;
                }
            }
        }
        if (count == row * row) {
            System.out.println("SPL tidak bisa diselesaikan dengan metode invers");
        } else {
            // perkalian matriks A^(-1) dan b (lebih jelasnya liat ppt pak Rin), intinya
            // Ax = b -> x = A^(-1) * b (nanti bisa dapet solusi SPLnya dari perkalian ini)
            for (int i = 0; i < row; i++) {
                double sum = 0;
                for (int j = 0; j < row; j++) {
                    sum += invers[i][j] * b[j][0];
                }
                hasil[i][0] = sum;
            }

            // output hasil
            for (int i = 0; i < row; i++) {
                System.out.printf("x%d = %.2f \n", i + 1, hasil[i][0]);
            }
        }
    }

    // 2. SPL Gauss
    public void SPLGauss (double[][] matr, int baris, int kolom) {
        // Lagi2 pake algo yang mirip gauss, cuma kita ketambahan 1 kolom lagi
        int i = 0;
        ge.pindahBarisNol (matr, baris, kolom); 
        for (int k = 0; k < kolom - 1; k += 1) {
            ge.titikPivot (matr, baris, kolom, i, k);
            if (matr[i][k] != 0) {  
                for (int b = i + 1; b <= baris; b += 1) {
                    if(b != baris) { 
                        // Kalo b = baris, ini bisa error karena kalo kita inget lagi tadi, ada pindahbarisnol yang
                        // midah barisnol kebawah kan?
                        double pembagi = matr[b][k] / matr[i][k];  // Nentuin pembagi kedua row, sama kek gauss pada umumnya
                        ge.kurangi(matr, kolom, pembagi, i, b); // Makanya buat yang ini divide ditambah pengurangan
                    }
                    ge.bagiBaris (matr, kolom, matr[i][k], i);  // Yang ini divide aja, jadi misal nol semua, kita tau lah
                    // kalo 0 dibagi apapun akan tetep 0
                }
                i += 1;  // Ganti baris
            }
        }
        // Nilai -0 handler
        for (int m = 0; m < baris; m += 1) {
            for (int n = 0; n < kolom; n += 1) {
                if (matr[m][n] == -0) {
                    matr[m][n] = 0;
                }
            }
        }

        // Inisiasi, Nyari kolom terakhir
        double[] nilai = new double[baris];
        for (int m = 0; m < baris; m += 1) {
            nilai[m] = matr[m][kolom-1];
        }
        // List nilai keiisi sama elemen terakhir di sebuah baris

        // Nah ini yang susah, substitusi balik buat nentuin solusi
        if (ge.barisAneh(matr, kolom, baris-1)) { // Kalo baris terakhirnya baris aneh
            System.out.println("SPL Tidak memiliki solusi");
        }
        else if (ge.barisNol(matr, kolom, baris-1)) { // Kalo baris terakhirnya 0 semua
            System.out.println("SPL memiliki banyak solusi");
            // Harusnya pake parametrik tp lagi bingung jadi ntar dulu
        }
        else {
            // Inisiasi list hasil
            double[] hasil = new double[baris];
            for (int m = baris-1; m >= 0; m -= 1) {
                hasil[m] = nilai[m];
                for (int n = 1; n <= baris - m - 1; n += 1) {
                    hasil[m] = hasil[m] - matr [m][m+n] * hasil[m+n];
                }
            }

            for (int m = 0; m < baris; m += 1) {
                System.out.printf("x%d = %.2f \n", m+1, hasil[m]);
            }
        }
    }
}