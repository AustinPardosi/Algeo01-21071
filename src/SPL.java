public class SPL {
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
        Invers inv = new Invers();
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
}
