public class Determinan {
    // Mendatangkan fungsi eksternal [kumpulin sini]
    Gauss ge = new Gauss();
    Kofaktor k = new Kofaktor();

    // 1. Determinan Gauss
    public String detGauss(double[][] matr, int baris, int kolom) {
        // Kita bakal pake algo yang mirip sama gauss, cuma kita ga bikin dia leading
        // one
        // Sebelumnya, determinan cuma bisa diitung kalo matriks nya persegi yes
        // Handling kasus terkecilnya
        double det = 1; // inisiasi variabel
        if (baris == 2) {
            det *= ((matr[0][0] * matr[1][1]) - (matr[0][1] * matr[1][0]));
        } else {
            int i = 0;
            ge.pindahBarisNol(matr, baris, kolom);
            for (int k = 0; k < kolom; k += 1) {
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
                        // ya divide nya dihapus supaya ga jadi leading one dan bisa diitung det nya
                    }
                    i += 1; // Ganti baris
                }
            }
            // Keluar dari loop, udah jadi eselon baris bukan leading one, simply tinggal
            // kaliin diagonal
            // utamanya
            for (int j = 0; j < baris; j += 1) {
                det = det * matr[j][j]; // ya kaliin aja sama diagonal utama
            }
        }

        return Double.toString(det);
    }

    // 2. Determinan kofaktor
    public String detKofak(double[][] matr, int baris, int kolom) {
        // Asumsi matriksnya pasti persegi yes, soalnya kalo ga gitu, gabisa
        double det = 0; // Inisialisasi variabel ngitung determinan
        if (baris == 2) {
            det += (matr[0][0] * matr[1][1]) - (matr[0][1] * matr[1][0]);
        } else {
            for (int i = 0; i < kolom; i += 1) {
                det += (matr[0][i]
                        * Double.parseDouble(
                                detGauss(k.hapusBarisKolom(matr, baris, kolom, 0, i), baris - 1, kolom - 1))
                        * Math.pow((-1), i));
            }
        }
        return Double.toString(det);
    }
}
