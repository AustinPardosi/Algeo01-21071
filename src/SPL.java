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
    double[][] SPLInvers(double[][] matr, int row, int col) {

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
        return hasil;
    }

    // 2. SPL Gauss
    public double[] SPLGauss(double[][] matr, int baris, int kolom) {
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
                System.out.printf("x%d = %.2f \n", m + 1, hasil[m]);
            }
        }
        return hasil;
    }

    // 3. SPL Gauss Jordan
    public double[] SPLGaussJordan(double[][] matr, int baris, int kolom) {

        // double[][] m = new double[baris + 1][kolom];
        // for (int i = 0; i < baris; i++) {
        //     for (int j = 0; j < kolom; j++) {
        //         m[i][j] = matr[i][j];
        //     }
        // }
        // for (int j = 0; j < kolom; j++) {
        //     m[baris][j] = 0;
        // }

        // Mengembalikan array of string berisi hasil kalkulasi SPL dengan metode Gauss
        // Jordan
        gj.gaussJordan(matr, baris, kolom);

        // for (int i = 0; i < baris; i++) {
        //     if (!ge.barisNol(m, kolom, i)) {
        //         for (int j = 0; j < kolom; j++) {
        //             matr[i][j] = m[i][j];
        //         }
        //     } else {
        //         baris = i;
        //         break;
        //     }
        // }

        // Cek apakah ada baris yang matrixnya nol dan hasilnya tidak nol
        double[] Hasil = new double[baris];
        if (gj.isNoSolution(matr, baris, baris)) {
            System.out.println("SPL Tidak memiliki solusi");
        } else {
            double[][] afterCut = gj.createMatEff(matr);
            baris = afterCut.length;
            try {
                kolom = afterCut[0].length;
            } catch (ArrayIndexOutOfBoundsException err) {
                kolom = 0;
            }
            io.printToScreen(afterCut, baris, kolom);
            if (baris == (kolom - 1)) {
                // CASE 1 : baris == kolom -1
                return cariHasil(afterCut);
            } else if (baris < (kolom - 1)) {
                // CASE 2 : baris < kolom - 1
                System.out.println("SPL memiliki banyak solusi");
            } else {
                // CASE 3 : baris > kolom - 1
                System.out.println("SPL Tidak memiliki solusi");
            }
        }
        return Hasil;
    }

    public double[] cariHasil(double[][] matr) {
        int rows = matr.length;
        int cols = matr[0].length;
        double[] hasil = new double[rows];
        for (int i = 0; i < rows; i++) {
            hasil[i] = matr[i][cols - 1];
            System.out.printf("x%d = %.2f \n", i + 1, hasil[i]);
        }

        return hasil;
    }

    // 4. SPL Cramer
    public double[] SPLCramer(double[][] matr, int baris, int kolom) {
        // idenya buat Ax = b; dimana A = cramer, B= jawaban
        double[][] cramer = new double[baris][baris];
        double[][] jawaban = new double[baris][1];
        double[] hasil = new double[baris];

        // copy elemen matr (yang bagian A saja) ke cramer
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < baris; j++) {
                cramer[i][j] = matr[i][j];
            }
        }

        // copy elemen matr (kolom terakhir) ke jawaban
        for (int i = 0; i < baris; i++) {
            jawaban[i][0] = matr[i][kolom - 1];
        }

        // membuat matriks test untuk setiap kolom dan menghitung nilai masing2 variabel
        // x
        for (int k = 0; k < baris; k++) {
            double[][] test = new double[baris][kolom - 1];
            for (int i = 0; i < baris; i++) {
                for (int j = 0; j < baris; j++) {
                    if (k == j) {
                        test[i][j] = jawaban[i][0];
                    } else {
                        test[i][j] = cramer[i][j];
                    }
                }
            }
            // cari nilai x
            // hitung nilai x pada masing2 matriks test
            double x = det.detKofak(test, baris, kolom - 1) / det.detKofak(cramer, baris, kolom - 1);
            hasil[k] = x;
            if (hasil[k] == -0) {
                hasil[k] = 0;
            }
            System.out.printf("x%d = %.2f \n", k + 1, hasil[k]);

        }
        return hasil;
    }
}
