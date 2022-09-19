// NOTE :
// ini masih error kalau hasil akhir matriksnya ada leading one
// yang ga pas di diagonal atau ada baris yang 0 smua
// contoh yang error:
// 1 * * * *
// 0 1 * * *
// 0 0 0 1 *    leading one ga pas di diagonal
// 0 0 0 0 1
// atau
// 1 * * *
// * 1 * *      ada baris yang 0 smua
// 0 0 0 0

public class Gauss {

    void gauss(double[][] matr, int M, int N) {
        for (int i = 0; i < M; i++) {
            // Cari baris yang akan dijadikan pivot
            // yaitu baris dengan nilai yang lebih kecil
            int maks = i;
            for (int j = i + 1; j < N; j++)
                if (Math.abs(matr[j][i]) < Math.abs(matr[maks][i])) // Nyari nilai yang absolutnya lebih kecil
                    maks = j; // assign maksnya

            // Swapping, tuker baris yang nilainya kecil atas, gede bawah
            double[] temp = matr[i]; // Inisialisasi array temporarry buat mindah data
            // proses pindah
            matr[i] = matr[maks];
            matr[maks] = temp; // Balikin lagi

            int leadingOneCol = i;
            while (matr[i][leadingOneCol] == 0 && leadingOneCol < N) {
                leadingOneCol += 1;
                System.out.println(leadingOneCol);
            }

            if (matr[i][leadingOneCol] != 0) {
                // ngebagi baris tersebut supaya ada leading one
                for (int j = i; j < N; j++) {
                    matr[i][j] = matr[i][j] / matr[i][leadingOneCol];
                }

                // baris-baris yang lain dikurangin rasio supaya bawahnya leading one 0 smua
                for (int k = i + 1; k < M; k++) {
                    double rasio = matr[k][leadingOneCol] / matr[i][leadingOneCol]; // mengambil rasio baris
                    for (int l = i; l < N; l++) {
                        matr[k][l] -= rasio * matr[i][l]; // Proses pengurangannya
                    }
                }
            } else if (leadingOneCol == N) {
                // ini kondisi kalau satu baris 0 smua, ditukar ke paling bawah
                double[] temp2 = matr[i];
                matr[i] = matr[N - 1];
                matr[N - 1] = temp2;
            }
        }
    }
}