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
        // Gauss ge = new Gauss();
        GaussJordan gj = new GaussJordan();

        // Baca matriks
        System.out.println("Baca matriks");
        M.readByKeyboard(A, baris, kolom);

        // Eselon baris
        // ge.gauss(A, baris, kolom);

        // Eselon baris tereduksi
        gj.inversGaussJordan(A, baris);

        // Penyelesaian determinan dengan gauss
        // System.out.println("Determinannya adalah");
        // double det = ge.detGauss(A, baris, kolom);
        // System.out.printf("%.2f \n",det);

        // Mencari solusi SPL dengan gauss
        System.out.println("Solusi SPL nya kak");
        // ge.SPLGauss(A, baris, kolom);

        // Cetak matriks
        System.out.println("Cetak matriks");
        System.out.println("Bentuk eselon baris");
        M.printToScreen(A, baris, kolom);

        sc.close();
    }
}
