import java.util.Scanner; // for I/O purposes

public class Main {

    // Membuat semua instance kelas
    // I/O
    static InputOutput IO = new InputOutput();

    // File dasar
    static Gauss gauss = new Gauss();
    static GaussJordan gaussjordan = new GaussJordan();
    static Kofaktor kofaktor = new Kofaktor();
    // File lanjutan
    static SPL SPL = new SPL();
    static Determinan determinan = new Determinan();
    static Invers invers = new Invers();
    // File pengembangan
    static Polinom polinom = new Polinom();
    static Bicubic bicubic = new Bicubic();
    static Regresi regresi = new Regresi();

    public static void main(String[] args) {
        String listMenu = """
                1. Sistem Persamaaan Linier
                    a. Metode eliminasi Gauss
                    b. Metode eliminasi Gauss-Jordan
                    c. Metode matriks balikan
                    d. Kaidah Cramer
                2. Determinan
                    a. Metode eliminasi Gauss
                    b. Kofaktor
                3. Matriks Balikan
                    a. Metode eliminasi Gauss-Jordan
                    b. Matriks adjoint
                4. Interpolasi Polinom
                5. Interpolasi Bicubic
                6. Regresi linier berganda
                7. Keluar
                    """;

        System.out.println("SELAMAT DATANG DI KALKULATOR MATRIKS KELOMPOK 2B1");
        System.out.println();

        boolean isContinue = true;

        System.out.println("PILIHAN MENU");
        System.out.println(listMenu);

        // while (isContinue) {
        Scanner sc = new Scanner(System.in); // untuk input keyboard
        System.out.println("Masukkan angka menu yang ingin dijalankan (Contoh: 1, 2, 3) :");
        int menu = sc.nextInt();
        System.out.println();

        System.out.println("Masukkan huruf sub-menu yang ingin dijalankan (Contoh: a, b, c) :");
        char subMenu = sc.next().charAt(0);
        System.out.println();

        System.out.println("PILIHAN INPUT");
        System.out.println("1. Keyboard");
        System.out.println("2. File");

        System.out.println("Masukkan pilihan input yang diinginkan (ketik angka 1 atau 2) :");
        int input = sc.nextInt();
        System.out.println();

        if (menu == 1 || menu == 2 || menu == 3) {
            if (input == 1) {
                // Baca Input
                System.out.println("Masukkan jumlah baris");
                int baris = sc.nextInt();
                System.out.println("Masukkan jumlah kolom");
                int kolom = sc.nextInt();

                // Define matriks
                double[][] matr = new double[baris][kolom];
                System.out.println("Baca matriks");
                IO.readByKeyboard(matr, baris, kolom);

                switch (menu) {
                    case 1:
                        switch (subMenu) {
                            case 'a':
                                // SPLGauss
                                SPL.SPLGauss(matr, baris, kolom);
                                break;
                            case 'b':
                                // SPLGaussJordan
                                SPL.SPLGaussJordan(matr, baris, kolom);
                                break;
                            case 'c':
                                // SPLInvers
                                SPL.SPLInvers(matr, baris, kolom);
                                break;
                            case 'd':
                                // SPLCramer
                                SPL.SPLCramer(matr, baris, kolom);
                                break;
                        }
                        break;
                    case 2:
                        switch (subMenu) {
                            case 'a':
                                // detGauss
                                determinan.detGauss(matr, baris, kolom);
                                break;
                            case 'b':
                                // detKofaktor
                                determinan.detKofak(matr, baris, kolom);
                                break;
                        }
                        break;
                    case 3:
                        switch (subMenu) {
                            case 'a':
                                // inversGaussJordan
                                invers.inversGaussJordan(matr, baris);
                                break;
                            case 'b':
                                // inversAdjoint
                                invers.inverseAdjoint(matr, baris, kolom);
                                break;
                        }
                        break;
                }
            }
        } else if (menu == 4) {
            // polinom
            if (input == 1) {
                // Baca Input
                System.out.println("Masukkan nilai n");
                int n = sc.nextInt();
                System.out.println("Masukkan jumlah titik");
                int totalPoint = sc.nextInt();
                System.out.println("Baca titik");
                double[][] points = new double[totalPoint][2];

                for (int i = 0; i < totalPoint; i++) {
                    for (int j = 0; j < 2; j++) {
                        points[i][j] = sc.nextDouble();
                    }
                }
                // IO.readByKeyboard(points, totalPoint, 2);
                System.out.println("Masukkan nilai x yang ingin ditafsir");
                int x = sc.nextInt();
                polinom.polinom(n, points, x);
            }
        } else if (menu == 5) {
            // bicubic
            if (input == 1) {

            }
        } else if (menu == 6) {
            // regresi
            if (input == 1) {

            }
        } else if (menu == 7) {
            System.out.println("Terima kasih telah menggunakan kalkulator matriks kami.");
            isContinue = false;
        }
        System.out.println();
        sc.close();
    }
    // }
}
