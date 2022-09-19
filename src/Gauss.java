public class Gauss {

    void gauss(double[][] matr,int M, int N)
    {
        for (int i = 0; i < M; i++) {
            // Cari baris yang akan dijadikan pivot
            // yaitu baris dengan nilai yang lebih kecil
            int maks = i;
            for (int j = i + 1; j < N; j++) 
                if (Math.abs(matr[j][i]) > Math.abs(matr[maks][i])) // Nyari nilai yang absolutnya lebih besar
                    maks = j; // assign maksnya

            // Swapping, tuker baris yang nilainya kecil atas, gede bawah
            double[] temp = matr[i]; // Inisialisasi array temporarry buat mindah data
            // proses pindah
            matr[i] = matr[maks]; 
            matr[maks] = temp;   // Balikin lagi

            // Proses pengurangan row dengan nilai lebih tinggi dengan yang lebih rendah
            // Posisi row sudah urut jadi lebih mudah
            for (int k = i + 1; k < M; k++) {
                double rasio = matr[k][i] / matr[i][i]; // mengambil rasio baris
                for (int l = i; l < N; l++) {
                    matr[k][l] -= rasio * matr[i][l]; // Proses pengurangannya
                }
            }

            for (int m = 0; m < N; m++) {
                for (int n = m+1; n < N; n++) {
                    if (m == n) {
                        double pembagi = matr[i][n];
                    }
                    matr[i][m] /= pembagi;
                }
            }
        }
    }
}
