import java.util.Scanner;

class DriverMatriks {
    public static void main(String[] args) {
        // TESTING readByFile then output the matrix
        // InputOutput IO = new InputOutput();

        // double[][] matrix = IO.readByFile("readByFileTesting");
        // IO.writeFile("test/writeFileTesting.txt", matrix);

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
        // GaussJordan gj = new GaussJordan();
        SPL spl = new SPL();
        // Invers invers = new Invers();
        // Kofaktor k = new Kofaktor();

        // Baca matriks
        System.out.println("Baca matriks");
        M.readByKeyboard(A, baris, kolom);

        // Penyelesaian determinan
        // System.out.println("Determinannya adalah");
        // 1. Determinan dengan gauss
        // double det1 = ge.detGauss(A, baris, kolom);
        // System.out.printf("%.2f \n",det1);
        // 2. Determinan dengan kofaktor
        // double det2 = k.detKofak(A, baris, kolom);
        // System.out.printf("%.2f \n",det2);

        // Eselon baris
        // ge.gauss(A, baris, kolom);

        // Eselon baris tereduksi
        // gj.inversGaussJordan(A, baris);
        // invers.inversGaussJordan(A, baris);
        // System.out.println("Solusi SPL dengan metode Invers :");
        // spl.SPLInvers(A, baris, kolom);

        // Mencari solusi SPL dengan gauss
        System.out.println("Solusi SPL nya kak");
        // spl.SPLGauss(A, baris, kolom);
        SPL.SPLCramer(A, baris, kolom);

        // Penyelesaian inverse
        // System.out.println("Inversnya adalah");
        // 1. Inverse dengan adjoint
        // k.inverseAdjoint (A, baris, kolom);
        // 2. Inverse dengan gauss-jordan
        // [insert here]

        // Cetak matriks
        // System.out.println("Cetak matriks");
        // System.out.println("Bentuk eselon baris");
        // M.printToScreen(A, baris, kolom);

        sc.close();
    }
}
