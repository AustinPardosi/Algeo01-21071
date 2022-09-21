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
        // GaussJordan gj = new GaussJordan();

        // Baca matriks
        System.out.println("Baca matriks");
        M.readByKeyboard(A, baris, kolom);

        // Eselon baris
        double determinan = ge.detGauss(A, baris, kolom);
        System.out.println("Determinannya adalah");
        System.out.printf("%.2f\n",determinan);
        ge.gauss(A, baris, kolom);

        // Eselon baris tereduksi
        // gj.gaussJordan(A, baris, kolom);

        // Cetak matriks
        System.out.println("Cetak matriks eselon baris");
        M.printToScreen(A, baris, kolom);

        sc.close();
    }
}
