public class Gauss {
    // Oke gais, jadi idenya bakal programming modular doain jadi plis plis plis keos [mulai 9.54 pm - 11.40 pm]
    // 1. Apakah semua elemen dalam 1 baris nol semua
    boolean barisNol(double[][] matr, int kolom, int i) {
        for (int j = 0; j < kolom; j += 1) {
            if (matr[i][j] != 0) {
                return false;
            }
        }
        return true;
    }

    // 2. Handling row aneh, ngerti ga, kek yang elemennya 0000x intinya depannya 0
    boolean barisAneh(double[][] matr, int kolom, int i) {
        // Cari dulu sampe N-1 elemen, ada yang bukan 0 ga
        for (int j = 0; j < kolom - 1; j += 1) {
            if (matr[i][j] != 0) {
                return false;  // Kalo gaada brarti dia normal
            }
        }
        // Terakhir ngecek di elemen terakhirnya, klo bukan 0, fix row aneh, klo 0 harusnya dia masuk
        // barisnol
        return !(matr[i][kolom - 1] == 0);
    }

    // 3. Swappp, ya benar, swap, tuker baris itulho
    void swapBaris(double[][] matr, int kolom, int baris1, int baris2) {
        // Cek dulu baris yang mau dituker sama ga, (kalo sama lucu dong)
        if (baris1 != baris2) {
            for (int j = 0; j < kolom; j += 1) {
                double temp = matr[baris1][j];  // Bikin temp, yaa idenya kaya yang sebelumnya, tempat mindah
                // proses pindah
                matr[baris1][j] = matr[baris2][j];
                matr[baris2][j] = temp; // Balikin lagi yang dipindah
            }
        }
        // Pindah pindahnya kelar yey
    }

    // 4. Pindah nol kebawah, y emg nol semua ganggu bgt
    void pindahBarisNol (double[][] matr, int baris, int kolom) {
        for(int i = 0; i < baris; i += 1) {
            if(barisNol(matr, kolom, i) ) { // Kalo matriks 0 semua
                swapBaris(matr, kolom, i, (baris - 1)); // Pindah ke paling bawah
            }
        }
    }

    // 5. Yak, kita masuk ke poin penting, nentuin yang bakal dijadiin pivot
    // Ketentuannya baca yah, agak ribet soalnya mikirnya
    int barisPivot(double[][] matr, int baris, int kolom, int baris1, int kolom1) {
        int indexb = baris1; // First assignment, baris pertama
        for (int k = baris1; k < baris; k += 1) { // Loop sepanjang matriks, di baris
            // Pembahasan conditional, kalo di baris indexb elemen pertamanya 0 atau di baris k elemen pertamanya
            // bukan nol dan elemen pertama baris k lebih besar dari baris indexb (tentu saja dengan note kalo
            // baris k bukan barisnol atau barisaneh)
            if ((matr[indexb][kolom1] == 0) || ((matr[k][kolom1] != 0) && (matr[k][kolom1] < matr[indexb][kolom1])) && (!barisNol(matr, kolom, k)) && (!barisAneh(matr, kolom, k))) {
                indexb = k;  // Baris dengan elemen besar nya di reassign dengan baris k
            }
            // Diulangi terus prosedurnya sampe nemu yang bener2 maks
        }
        return indexb;  // yak direturn
    }

    // 6. Pivot ketemu, sekarang assign titik pivotnya
    void titikPivot(double[][] matr, int baris, int kolom, int baris1, int kolom1) {
        // Ya basically swap antara 2 baris mana yang non-0 nya lebih banyak
        swapBaris(matr, kolom, baris1, barisPivot(matr, baris, kolom, baris1, kolom1));
    }

    // 7. Mengurangi 2 row, penting dong yaa
    void kurangi(double[][] matr, int kolom, double pembagi, int baris1, int baris2) {
        for (int j = 0; j < kolom; j += 1) {  // Loop data sepanjang kolom
            matr[baris2][j] -= pembagi * matr[baris1][j];  // Kurangi aja data baris ke 2 sama pengalinya
        }
    }

    // 8. Last but not least, bagi semua elemen di row dengan suatu konstanta
    // Harapannya ini bisa bikin jadi leading 1 nya T__T karena baris ini dibagi elemen non-0 pertama
    void bagiBaris(double[][] matr, int kolom, double pembagi, int baris) {
        for (int j = 0; j < kolom; j += 1) {   // Loop data sepanjang kolom
            matr[baris][j] /= pembagi;
        }
    }

    // Now we're ready for GAUSS!
    public void gauss(double[][] matr, int baris, int kolom) {
        // Hal yang jadi concern kita pada revisi kali ini adalah barisnol dan barisaneh, jadi
        // Kita pake algoritma kemaren yang dimodif dikit
        int i = 0; // Handling per baris
        pindahBarisNol (matr, baris, kolom); // Pindahin barisnol kebawah biar ga aneh2
        for (int k = 0; k < kolom; k += 1) {
            titikPivot (matr, baris, kolom, i, k); // Titik pivotnyaa, simply baris udah keurut
            if (matr[i][k] != 0) {  // Kalo di baris itu, elemen pertamanya bukan 0, masuk loop
                for (int b = i + 1; b <= baris; b += 1) {
                    if(b != baris) { 
                        // Kalo b = baris, ini bisa error karena kalo kita inget lagi tadi, ada pindahbarisnol yang
                        // midah barisnol kebawah kan?
                        double pembagi = matr[b][k] / matr[i][k];  // Nentuin pembagi kedua row, sama kek gauss pada umumnya
                        kurangi(matr, kolom, pembagi, i, b); // Makanya buat yang ini divide ditambah pengurangan
                    }
                    bagiBaris (matr, kolom, matr[i][k], i);  // Yang ini divide aja, jadi misal nol semua, kita tau lah
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
    }

    // Lesgo to determinan gauss
    public double detGauss (double[][] matr, int baris, int kolom) {
        // Kita bakal pake algo yang mirip sama gauss, cuma kita ga bikin dia leading one
        // Sebelumnya, determinan cuma bisa diitung kalo matriks nya persegi yes
        // Handling kasus terkecilnya
        double det = 1; // inisiasi variabel
        if (baris == 2) {
            det *= ((matr[0][0] * matr[1][1]) - (matr[0][1] * matr[1][0]));
        }
        else {
            int i = 0; 
            pindahBarisNol (matr, baris, kolom); 
            for (int k = 0; k < kolom; k += 1) {
                titikPivot (matr, baris, kolom, i, k);
                if (matr[i][k] != 0) {  
                    for (int b = i + 1; b <= baris; b += 1) {
                        if(b != baris) { 
                            // Kalo b = baris, ini bisa error karena kalo kita inget lagi tadi, ada pindahbarisnol yang
                            // midah barisnol kebawah kan?
                            double pembagi = matr[b][k] / matr[i][k];  // Nentuin pembagi kedua row, sama kek gauss pada umumnya
                            kurangi(matr, kolom, pembagi, i, b); // Makanya buat yang ini divide ditambah pengurangan
                        }
                        // ya divide nya dihapus supaya ga jadi leading one dan bisa diitung det nya
                    }
                    i += 1;  // Ganti baris
                }
            }
            // Keluar dari loop, udah jadi eselon baris bukan leading one, simply tinggal kaliin diagonal
            // utamanya
            for (int j = 0; j < baris; j += 1) {
                det = det * matr[j][j]; // ya kaliin aja sama diagonal utama
            }
        }

        return det;
    }

    // SPL Gausssss
    public void SPLGauss (double[][] matr, int baris, int kolom) {
        // Lagi2 pake algo yang mirip gauss, cuma kita ketambahan 1 kolom lagi
        int i = 0;
        pindahBarisNol (matr, baris, kolom); 
        for (int k = 0; k < kolom - 1; k += 1) {
            titikPivot (matr, baris, kolom, i, k);
            if (matr[i][k] != 0) {  
                for (int b = i + 1; b <= baris; b += 1) {
                    if(b != baris) { 
                        // Kalo b = baris, ini bisa error karena kalo kita inget lagi tadi, ada pindahbarisnol yang
                        // midah barisnol kebawah kan?
                        double pembagi = matr[b][k] / matr[i][k];  // Nentuin pembagi kedua row, sama kek gauss pada umumnya
                        kurangi(matr, kolom, pembagi, i, b); // Makanya buat yang ini divide ditambah pengurangan
                    }
                    bagiBaris (matr, kolom, matr[i][k], i);  // Yang ini divide aja, jadi misal nol semua, kita tau lah
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
        if (barisAneh(matr, kolom, baris-1)) { // Kalo baris terakhirnya baris aneh
            System.out.println("SPL Tidak memiliki solusi");
        }
        else if (barisNol(matr, kolom, baris-1)) { // Kalo baris terakhirnya 0 semua
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