import java.util.Scanner;

class DriverMatriks {
    public static void main(String[] args) {
        // Baca Input
        Scanner sc = new Scanner(System.in);
        System.out.println("Masukkan jumlah baris");
        int baris = sc.nextInt();
        System.out.println("Masukkan jumlah kolom");
        int kolom = sc.nextInt();

        // Define matriks
        double[][] A = new double[baris][kolom];

        // Manggil fungsi eksternal
        InputOutput M = new InputOutput();
        Gauss ge = new Gauss();
        GaussJordan gj = new GaussJordan();

        // Baca matriks
        System.out.println("Baca matriks");
        M.readByKeyboard(A, baris, kolom);

        // Eselon baris
        ge.gauss(A, baris, kolom);

        // Eselon baris tereduksi
        // gj.gaussJordan(A, baris, kolom);

        // ini ngitung determinan, GAUSA DIGANTI2 DULU Y
        /*
         * int kali = 1;
         * for (int i = 0; i < baris; i++) {
         * for (int j = 0; j < kolom; j++) {
         * if (i == j) {
         * kali *= A[i][j];
         * }
         * }
         * }
         */
        // System.out.println(kali);

        // Cetak matriks
        System.out.println("Cetak matriks");
        M.printToScreen(A, baris, kolom);

        sc.close();
    }
}
