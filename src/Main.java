import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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
    static RegresiLinierBerganda regresi = new RegresiLinierBerganda();

    static double[] res;
    static double[][] m;
    static String[][] text;

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

        System.out.println("SELAMAT DATANG DI KALKULATOR MATRIKS");
        System.out.println("");
        System.out.println("    ██████╗ ██████╗  ██╗  ");
        System.out.println("    ╚════██╗██╔══██╗███║  ");
        System.out.println("     █████╔╝██████╔╝╚██║  ");
        System.out.println("    ██╔═══╝ ██╔══██╗ ██║  ");
        System.out.println("    ███████╗██████╔╝ ██║  ");
        System.out.println("    ╚══════╝╚═════╝  ╚═╝  ");
        System.out.println("");

        boolean isContinue = true;

        // System.out.println("PILIHAN MENU");
        // System.out.println(listMenu);

        Scanner sc = new Scanner(System.in); // untuk input keyboard

        while (isContinue) {
            System.out.println("PILIHAN MENU");
            System.out.println(listMenu);

            System.out.println("Masukkan angka menu yang ingin dijalankan (Contoh: 1, 2, 3) :");
            int menu = sc.nextInt();

            while (menu < 1 || menu > 7) {
                System.out.println("Masukan tidak valid, silakan ulangi");
                menu = sc.nextInt();
            }
            System.out.println();

            if (menu == 7) {
                System.out.println("Terima kasih telah menggunakan kalkulator matriks kami.");
                break;
            }

            switch (menu) {
                case 1:
                    System.out.println("--- SISTEM PERSAMAAN LINIER ---");
                    break;
                case 2:
                    System.out.println("--- DETERMINAN ---");
                    break;
                case 3:
                    System.out.println("--- MATRIKS BALIKAN ---");
                    break;
                case 4:
                    System.out.println("--- INTERPOLASI POLINOM ---");
                    break;
                case 5:
                    System.out.println("--- INTERPOLASI BICUBIC ---");
                    break;
                case 6:
                    System.out.println("--- REGRESI LINIER BERGANDA ---");
                    break;
            }

            char subMenu = 'a'; // default subMenu
            if (menu == 1 || menu == 2 || menu == 3) {
                System.out.println("Masukkan huruf sub-menu yang ingin dijalankan (Contoh: a, b, c) :");
                subMenu = sc.next().charAt(0);

                if (menu == 1) {
                    while (subMenu != 'a' && subMenu != 'b' && subMenu != 'c' && subMenu != 'd') {
                        System.out.println("Masukan tidak valid, silakan ulangi");
                        subMenu = sc.next().charAt(0);
                    }
                } else if (menu == 2 || menu == 3) {
                    while (subMenu != 'a' && subMenu != 'b') {
                        System.out.println("Masukan tidak valid, silakan ulangi");
                        subMenu = sc.next().charAt(0);
                    }
                }
                System.out.println();
            }

            System.out.println("PILIHAN INPUT");
            System.out.println("1. Keyboard");
            System.out.println("2. File");

            System.out.println("Masukkan pilihan input yang diinginkan (ketik angka 1 atau 2) :");
            int input = sc.nextInt();
            while (input != 1 && input != 2) {
                System.out.println("Masukan tidak valid, silakan ulangi");
                input = sc.nextInt();
            }
            System.out.println();

            if (menu == 1 || menu == 2 || menu == 3) {
                int baris, kolom;
                double[][] matr;
                if (input == 1) { // dari keyboard
                    // Baca Input
                    System.out.println("Masukkan jumlah baris");
                    baris = sc.nextInt();
                    System.out.println("Masukkan jumlah kolom");
                    kolom = sc.nextInt();

                    // Define matriks
                    matr = new double[baris][kolom];
                    System.out.println("Baca matriks");
                    IO.readByKeyboard(sc, matr, baris, kolom);

                } else { // input == 2 (dari file)
                    System.out.println("Masukkan path dari file yang ingin dibaca");
                    String path = sc.next();
                    matr = IO.readByFile(path);
                    baris = matr.length;
                    kolom = matr[0].length;
                    System.out.println();
                }

                switch (menu) {
                    case 1: // SPL
                        switch (subMenu) {
                            case 'a':
                                System.out.println("~ Sistem Persamaan Linier dengan Metode Eliminasi Gauss ~");
                                // SPLGauss
                                res = SPL.SPLGauss(matr, baris, kolom);
                                m = new double[res.length][1];

                                for (int i = 0; i < res.length; i++) {
                                    m[i][0] = Math.round(res[i] * 1000) / 1000;
                                }

                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                            case 'b':
                                System.out.println("~ Sistem Persamaan Linier dengan Metode Eliminasi Gauss-Jordan ~");
                                // SPLGaussJordan
                                res = SPL.SPLGaussJordan(matr, baris, kolom);
                                m = new double[res.length][1];

                                for (int i = 0; i < res.length; i++) {
                                    m[i][0] = Math.round(res[i] * 1000) / 1000;
                                }

                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                            case 'c':
                                System.out.println("~ Sistem Persamaan Linier dengan Metode Matriks Balikan ~");
                                // SPLInvers
                                m = SPL.SPLInvers(matr, baris, kolom);

                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                            case 'd':
                                System.out.println("~ Sistem Persamaan Linier dengan Kaidah Cramer ~");
                                // SPLCramer
                                res = SPL.SPLCramer(matr, baris, kolom);
                                m = new double[res.length][1];

                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                        }
                        break;
                    case 2:
                        switch (subMenu) {
                            case 'a':
                                System.out.println("~ Determinan dengan Metode Eliminasi Gauss ~");
                                // detGauss
                                m = new double[1][1];
                                m[0][0] = determinan.detGauss(matr, baris, kolom);
                                m[0][0] = Math.round(m[0][0] * 1000.0) / 1000.0;

                                System.out.printf("Nilai determinan: %.03f\n", m[0][0]);

                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                            case 'b':
                                System.out.println("~ Determinan dengan Metode Kofaktor ~");
                                // detKofaktor
                                m = new double[1][1];
                                m[0][0] = determinan.detKofak(matr, baris, kolom);
                                m[0][0] = Math.round(m[0][0] * 1000.0) / 1000.0;

                                System.out.printf("Nilai determinan: %.03f\n", m[0][0]);

                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                        }
                        break;
                    case 3:
                        switch (subMenu) {
                            case 'a':
                                System.out.println("~ Matriks Balikan dengan Metode Eliminasi Gauss-Jordan ~");
                                // inversGaussJordan
                                m = new double[baris][baris];

                                System.out.println("Matriks balikan:");
                                m = invers.inversGaussJordan(matr, baris);
                                IO.printToScreen(m, baris, baris);
                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                            case 'b':
                                System.out.println("~ Matriks Balikan dengan Metode Adjoint ~");
                                // inversAdjoint
                                m = new double[baris][baris];

                                System.out.println("Matriks balikan:");
                                m = invers.inverseAdjoint(matr, baris, kolom);
                                IO.writeFile("test/writeFileTesting.txt", m);
                                break;
                        }
                        break;
                }
            } else if (menu == 4) {
                // polinom
                text = new String[2][1];
                if (input == 1) { // dari keyboard
                    // Baca Input
                    System.out.println("Masukkan nilai n");
                    int n = sc.nextInt();
                    System.out.println("Baca titik");
                    double[][] points = new double[n][2];

                    for (int i = 0; i < n; i++) {
                        for (int j = 0; j < 2; j++) {
                            points[i][j] = sc.nextDouble();
                        }
                    }
                    System.out.println("Masukkan nilai x yang ingin ditafsir");
                    double x = sc.nextDouble();
                    text = polinom.polinom(n, points, x);

                    IO.writeFileString("test/writeFileTesting.txt", text);
                } else { // input == 2 (dari file)
                    System.out.println("Masukkan path dari file yang ingin dibaca");
                    String path = sc.next();
                    double[][] points = IO.readByFile(path);
                    System.out.println();
                    System.out.println("Masukkan nilai x yang ingin ditafsir");
                    double x = sc.nextDouble();
                    text = polinom.polinom(points.length, points, x);

                    IO.writeFileString("test/writeFileTesting.txt", text);
                }
            } else if (menu == 5) {
                // bicubic
                // Assign matr dengan matriks bicubic hasil mikir sejam kita
                double[][] matr = bicubic.matriksBicubic();

                // Inverskan matriks tersebut
                invers.inversGaussJordan(matr, 16);

                // Define matriks dan nilai yang akan dianalisis
                double[][] A = new double[4][4];
                double[][] trans1 = new double[4][4];
                double[][] pers = new double[16][1];
                double[][] kali = new double[16][1];
                double[][] xval = new double[1][4];
                double[][] yval = new double[4][1];
                double[][] hasil1 = new double[1][4];
                double[][] hasil2 = new double[1][1];

                // assign default x dan y (biar ga error)
                double x = 0;
                double y = 0;

                if (input == 1) {
                    // Nah sekarang minta matriks
                    System.out.println("Masukkan matriks yang akan di interpolasi bicubic");
                    int m, n;

                    for (m = 0; m < 4; m++) {
                        for (n = 0; n < 4; n++) {
                            A[m][n] = sc.nextDouble();
                        }
                    }

                    // Masukkan titik yang ingin dianalisis
                    System.out.println("Masukkan nilai x dan y yang akan dianalisis");
                    x = sc.nextDouble();
                    y = sc.nextDouble();
                } else { // input == 2 (dari file)
                    System.out.println("Masukkan path dari file yang ingin dibaca");
                    String path = sc.next();
                    try {
                        File myObj = new File(path);
                        Scanner myReader = new Scanner(myObj);
                        int i = 0;
                        int j = 0;
                        while (myReader.hasNextLine()) {
                            if (i > 3) {
                                break;
                            }
                            String data = myReader.nextLine();
                            String[] splitStr = data.split("\\s+");
                            j = 0;
                            for (String str : splitStr) {
                                A[i][j] = Double.parseDouble(str);
                                j += 1;
                            }
                            i += 1;
                        }
                        String data = myReader.nextLine();
                        String[] splitStr = data.split("\\s+");
                        x = Double.parseDouble(splitStr[0]);
                        y = Double.parseDouble(splitStr[1]);

                        System.out.println("Berhasil membaca file pada: " + path);
                        myReader.close();

                    } catch (FileNotFoundException e) {
                        System.out.println("An error occurred.");
                        e.printStackTrace();
                    }
                }

                // Transpose dulu matriks yang diinputkan
                trans1 = bicubic.matriksTranspose(A, 4, 4);

                // Konversi dari bentuk 4x4 jadi 16x1
                pers = bicubic.matriksConvert(trans1, 4, 4, 16, 1);

                // Perkalian transpose dengan matriks kofaktor kitah, ketemu koef A
                kali = bicubic.matriksKali(matr, pers, 16, 1, 16);

                // Konversi hasil koef A dari 16x1 ke 4x4
                A = bicubic.matriksConvert(kali, 16, 1, 4, 4);

                // Transpose koef
                A = bicubic.matriksTranspose(A, 4, 4);

                // Bikin list baru isinya [1,x,x^2,x^3] sama [1,y,y^2,y^3]
                for (int i = 0; i < 4; i++) {
                    xval[0][i] = Math.pow(x, i);
                    yval[i][0] = Math.pow(y, i);
                }

                // Perkalian matriks
                hasil1 = bicubic.matriksKali(xval, A, 1, 4, 4);

                hasil2 = bicubic.matriksKali(hasil1, yval, 1, 1, 4);

                // Keluar nilainya YEY
                System.out.println("Nilai hasil interpolasi adalah");
                System.out.printf("%.3f\n", hasil2[0][0]);

                m = new double[1][1];
                m[0][0] = Math.round(hasil2[0][0] * 1000.0) / 1000.0;

                IO.writeFile("test/writeFileTesting.txt", m);

            } else if (menu == 6) {
                double[][] matriks;
                double[] y;
                int n, m; // n = jumlah peubah x, m = jumlah sampel
                // regresi
                if (input == 1) {
                    // masukan dari pengguna
                    System.out.println("Masukkan jumlah peubah : ");
                    n = sc.nextInt();
                    System.out.println("Masukkan jumlah sampel : ");
                    m = sc.nextInt();
                    System.out.println("Masukkan matriks persamaan : ");
                    matriks = new double[m][n];
                    y = new double[m];

                    for (int i = 0; i < m; i++) {
                        for (int j = 0; j < n; j++) {
                            matriks[i][j] = sc.nextDouble();
                        }
                        y[i] = sc.nextDouble();
                    }
                } else { // input == 2 (dari file)
                    System.out.println("Masukkan path dari file yang ingin dibaca");
                    String path = sc.next();
                    double[][] tempMatrix = IO.readByFile(path);
                    matriks = new double[tempMatrix.length][tempMatrix[0].length - 1];
                    y = new double[tempMatrix.length];
                    for (int i = 0; i < matriks.length; i++) {
                        for (int j = 0; j < matriks[i].length; j++) {
                            matriks[i][j] = tempMatrix[i][j];
                        }
                        y[i] = tempMatrix[i][tempMatrix[0].length - 1];
                    }
                }

                // Hasil dari regresi linier
                double[] hasil = regresi.hasilRegresi(matriks, y);

                // Output persamaan regresi linier berganda
                System.out.printf("Persamaan hasil regresi linier berganda adalah y = %.3f", hasil[0]);
                for (int i = 1; i < hasil.length; i++) {
                    System.out.printf(" + %.3f x%d", hasil[i], i);
                }

                // Sisa taksiran
                System.out.println();
                System.out.println("\nTaksiran nilai fungsi");
                System.out.printf("Masukkan %d peubah yang ingin ditaksir : \n", hasil.length - 1);
                double[] taksir = new double[hasil.length];
                for (int i = 0; i < hasil.length - 1; i++) {
                    taksir[i] = sc.nextDouble();
                }
                double result = hasil[0];
                for (int i = 0; i < hasil.length - 1; i++) {
                    result += hasil[i + 1] * taksir[i];
                }
                System.out.printf("Nilai taksirannya adalah %.03f\n", result);

                // buat output ke file (belum selesai)
                // text = ...;
                // IO.writeFileString("test/writeFileTesting.txt", text);
            }
            System.out.println("");
            System.out.println("------------------------------------------------------------------------");
            System.out.println("");
        }
        sc.close();
    }
}
