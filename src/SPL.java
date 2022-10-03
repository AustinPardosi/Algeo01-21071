public class SPL {
    // Mendatangkan fungsi eksternal [kumpulin sini]
    Gauss ge = new Gauss();
    Invers inv = new Invers();
    GaussJordan gj = new GaussJordan();
    static Determinan det = new Determinan();
    InputOutput io = new InputOutput();

    // [ function SPLInvers ]
    // Desc :
    // mencari solusi SPL dengan metode invers
    // inversnya pake gauss jordan
    // Parameter input :
    // - matr (matriksnya)
    // - row (jumlah baris)
    // - col (jumlah kolom, yaitu row + 1 karena ketambahan b)
    // prekondisi : masukannya matriks augmented [A | b] untuk persamaan Ax = b
    String[][] SPLInvers(double[][] matr, int row, int col) {

        // jadi idenya kita mau pisah dulu matriks augmentednya jadi A sama b
        // matriks a disimpen di variabel invers (karena nantinya bakal diinvers)
        // matriks b disimpen di variabel b

        double[][] invers = new double[row][row]; // inisialisasi matriks invers
        double[][] temp = new double[row][row]; // copy an invers
        double[][] b = new double[row][1]; // inisialisasi matriks b
        double[][] hasil = new double[row][1]; // inisialisasi matriks hasil
        String[][] hasilString = new String[row][1]; // inisialisasi matriks hasil

        if (row != col - 1) {
            for (int i = 0; i < row; i++) {
                hasil[i][0] = 0;
            }
            System.out.println("SPL tidak bisa diselesaikan dengan metode invers");
            return hasilString;
        }

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
                System.out.printf("x%d = %.3f \n", i + 1, hasil[i][0]);
            }
        }
        for (int i = 0; i < row; i++) {
            hasil[i][0] = Math.round(hasil[i][0] * 1000.0) / 1000.0;
        }

        for (int j = 0; j < row; j++) {
            hasilString[j][0] = Double.toString(hasil[j][0]);
        }

        return hasilString;
    }

    // 2. SPL Gauss
    public String[] SPLGauss(double[][] matr, int baris, int kolom) {
        // Lagi2 pake algo yang mirip gauss, cuma kita ketambahan 1 kolom lagi
        int i = 0;
        ge.pindahBarisNol(matr, baris, kolom);
        for (int k = 0; k < kolom - 1; k += 1) {
            ge.titikPivot(matr, baris, kolom, i, k);
            if (matr[i][k] != 0) {
                for (int b = i + 1; b <= baris; b += 1) {
                    if (b != baris) {
                        // Kalo b = baris, ini bisa error karena kalo kita inget lagi tadi, ada
                        // pindahbarisnol yang
                        // midah barisnol kebawah kan?
                        double pembagi = matr[b][k] / matr[i][k]; // Nentuin pembagi kedua row, sama kek gauss pada
                                                                  // umumnya
                        ge.kurangi(matr, kolom, pembagi, i, b); // Makanya buat yang ini divide ditambah pengurangan
                    }
                    ge.bagiBaris(matr, kolom, matr[i][k], i); // Yang ini divide aja, jadi misal nol semua, kita tau lah
                    // kalo 0 dibagi apapun akan tetep 0
                }
                i += 1; // Ganti baris
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
            nilai[m] = matr[m][kolom - 1];
        }
        // List nilai keiisi sama elemen terakhir di sebuah baris

        // Nah ini yang susah, substitusi balik buat nentuin solusi
        double[] hasil = new double[baris]; // Inisiasi list hasil
        String[] hasilString = new String[baris]; // Inisiasi list hasil
        if (ge.barisAneh(matr, kolom, baris - 1)) { // Kalo baris terakhirnya baris aneh
            System.out.println("SPL Tidak memiliki solusi");
        } else if (ge.barisNol(matr, kolom, baris - 1)) { // Kalo baris terakhirnya 0 semua
            System.out.println("SPL memiliki banyak solusi");
            // Harusnya pake parametrik tp lagi bingung jadi ntar dulu
            /*
             * Belom jadi, skip aj
             * // Kamus Lokal
             * boolean[] stat = new boolean[baris + 1];
             * String[] ans = new String[baris + 1];
             * // Algoritma
             * 
             * for (int k = 0; k < baris; k++) {
             * stat[k] = false;
             * }
             * 
             * int count = 0;
             * for (char cha = 'a'; cha <= 'z'; cha++) {
             * if (count < baris) {
             * ans[count] = Character.toString(cha);
             * count += 1;
             * }
             * }
             * 
             * // Proses looping dimulai dari baris paling bawah
             * int ketemu = 0;
             * for (int r = baris; r >= 0; r -= 1) {
             * for (int j = 0; j < kolom - 1; j += 1) {
             * if (matr[r][j] == 0 && matr[r][j+1] != 0) {
             * ketemu = j; // Kalo gaada brarti dia normal
             * }
             * }
             * }
             */
        } else {
            for (int m = baris - 1; m >= 0; m -= 1) {
                hasil[m] = nilai[m];
                for (int n = 1; n <= baris - m - 1; n += 1) {
                    hasil[m] = hasil[m] - matr[m][m + n] * hasil[m + n];
                }
            }

            for (int m = 0; m < baris; m += 1) {
                System.out.printf("x%d = %.3f \n", m + 1, hasil[m]);
            }
        }

        for (int j = 0; j < baris; j++) {
            hasilString[j] = Double.toString(hasil[j]);
        }

        return hasilString;
    }

    // 3. SPL Gauss Jordan
    public String[] SPLGaussJordan(double[][] matr, int baris, int kolom) {
        Gauss ge = new Gauss();
        GaussJordan gj = new GaussJordan();
        // manggil gauss dulu sebelum dilanjutin gauss jordan sampe eselon tereduksi
        // Prosedur GAUSS
        // Lagi2 pake algo yang mirip gauss, cuma kita ketambahan 1 kolom lagi
        int i = 0;
        ge.pindahBarisNol(matr, baris, kolom);
        for (int k = 0; k < kolom - 1; k += 1) {
            ge.titikPivot(matr, baris, kolom, i, k);
            if (matr[i][k] != 0) {
                for (int b = i + 1; b <= baris; b += 1) {
                    if (b != baris) {
                        // Kalo b = baris, ini bisa error karena kalo kita inget lagi tadi, ada
                        // pindahbarisnol yang
                        // midah barisnol kebawah kan?
                        double pembagi = matr[b][k] / matr[i][k]; // Nentuin pembagi kedua row, sama kek gauss pada
                                                                  // umumnya
                        ge.kurangi(matr, kolom, pembagi, i, b); // Makanya buat yang ini divide ditambah pengurangan
                    }
                    ge.bagiBaris(matr, kolom, matr[i][k], i); // Yang ini divide aja, jadi misal nol semua, kita tau lah
                    // kalo 0 dibagi apapun akan tetep 0
                }
                i += 1; // Ganti baris
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

        // looping kolom
        for (int j = 0; j < kolom; j++) {
            // cari indeks baris leading one di kolom j
            int leadingOneRow = gj.leadingOneRow(matr, baris, j);

            for (int p = 0; p < leadingOneRow; p++) {
                if (matr[p][j] != 0) {
                    double pengali = matr[p][j];
                    for (int k = j; k < kolom; k++) {
                        matr[p][k] -= pengali * matr[leadingOneRow][k];
                        if (matr[p][k] == -0) {
                            matr[p][k] = 0;
                        }
                    }
                } else if (matr[p][j] == -0) {
                    matr[p][j] = 0;
                }
            }
        }

        // Inisiasi, Nyari kolom terakhir
        double[] nilai = new double[baris];
        for (int m = 0; m < baris; m += 1) {
            nilai[m] = matr[m][kolom - 1];
        }
        // List nilai keiisi sama elemen terakhir di sebuah baris

        // Nah ini yang susah, substitusi balik buat nentuin solusi
        double[] hasil = new double[baris]; // Inisiasi list hasil
        String[] hasilString = new String[baris]; // Inisiasi list hasil
        if (ge.barisAneh(matr, kolom, baris - 1)) { // Kalo baris terakhirnya baris aneh
            System.out.println("SPL Tidak memiliki solusi");
        } else if (ge.barisNol(matr, kolom, baris - 1)) { // Kalo baris terakhirnya 0 semua
            System.out.println("SPL memiliki banyak solusi");
            // Harusnya pake parametrik tp lagi bingung jadi ntar dulu
        } else {
            for (int m = baris - 1; m >= 0; m -= 1) {
                hasil[m] = nilai[m];
                for (int n = 1; n <= baris - m - 1; n += 1) {
                    hasil[m] = hasil[m] - matr[m][m + n] * hasil[m + n];
                }
            }

            for (int m = 0; m < baris; m += 1) {
                System.out.printf("x%d = %.3f \n", m + 1, hasil[m]);
            }
        }

        for (int j = 0; j < baris; j++) {
            hasilString[j] = Double.toString(hasil[j]);
        }

        return hasilString;
    }

    // 4. SPL Cramer
    public String[] SPLCramer(double[][] matr, int baris, int kolom) {
        double[] hasil = new double[baris];
        String[] hasilString = new String[baris];
        if (baris != kolom - 1) {
            for (int i = 0; i < baris; i++) {
                hasil[i] = 0;
            }
            System.out.println("Matriks tidak persegi");
        } else {
            // idenya buat Ax = b; dimana A = cramer, B= jawaban
            double[][] matriksPersegi = MatriksPersegi(matr); // bagian A
            double[][] jawaban = new double[baris][1]; // bagian b

            // copy elemen matr (kolom terakhir) ke jawaban
            for (int i = 0; i < baris; i++) {
                jawaban[i][0] = matr[i][kolom - 1];
            }

            // membuat matriks test untuk setiap kolom dan menghitung nilai masing2 variabel
            // x
            for (int k = 0; k < (kolom - 1); k++) {
                double[][] test = new double[baris][kolom - 1];
                for (int i = 0; i < baris; i++) {
                    for (int j = 0; j <= (kolom - 2); j++) {
                        if (k == j) {
                            test[i][j] = jawaban[i][0];
                        } else {
                            test[i][j] = matr[i][j];
                        }
                    }
                }

                // cari nilai x
                // hitung nilai x pada masing2 matriks test
                if (Double
                        .parseDouble(det.detKofak(matriksPersegi, matriksPersegi.length, matriksPersegi.length)) != 0) {
                    double x = Double.parseDouble(det.detKofak(test, baris, kolom - 1))
                            / Double.parseDouble(
                                    det.detKofak(matriksPersegi, matriksPersegi.length, matriksPersegi.length));
                    hasil[k] = x;
                } else {
                    hasil[k] = -999;
                }

                if (hasil[k] == -0) {
                    hasil[k] = 0;
                }
            }

            if (ge.barisAneh(matriksPersegi, matriksPersegi.length, matriksPersegi.length - 1)) { // Kalo baris
                                                                                                  // terakhirnya baris
                                                                                                  // aneh
                System.out.println("SPL Tidak memiliki solusi");
            } else if (Double
                    .parseDouble(det.detKofak(matriksPersegi, matriksPersegi.length, matriksPersegi.length)) == 0) {
                System.out.println("Determinan = 0 sehingga matriks tidak memiliki solusi unik");
            } else {
                for (int k = 0; k < (kolom - 1); k++) {
                    System.out.printf("x%d = %.3f \n", k + 1, hasil[k]);
                }
            }
        }

        for (int j = 0; j < baris; j++) {
            hasilString[j] = Double.toString(hasil[j]);
        }

        return hasilString;
    }

    // Membuat matriks persegi dari suatu matriks augmented
    protected static double[][] MatriksPersegi(double[][] matr) {
        double[][] matriksBaru = new double[matr.length][matr.length];

        for (int i = 0; i < matr.length; i++) {
            for (int j = 0; j < matr.length; j++) {
                matriksBaru[i][j] = matr[i][j];
            }
        }
        return matriksBaru;
    }
}
