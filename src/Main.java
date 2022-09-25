import java.util.Scanner; // for I/O purposes

public class Main {
    public static void main(String[] args) {
        // Membuat semua instance kelas
        // I/O
        InputOutput IO = new InputOutput();
        Scanner sc = new Scanner(System.in); // untuk input keyboard
        // File dasar
        Gauss gauss = new Gauss();
        GaussJordan gaussjordan = new GaussJordan();
        Kofaktor kofaktor = new Kofaktor();
        // File lanjutan
        SPL SPL = new SPL();
        Determinan determinan = new Determinan();
        Invers invers = new Invers();
        // File pengembangan
        Polinom polinom = new Polinom();
        Bicubic bicubic = new Bicubic();
        Regresi regresi = new Regresi();

        String listMenu = """
                1. Sistem Persamaaan Linier
                    a. Metode eliminasi Gauss
                    b. Metode eliminasi Gauss-Jordan
                    c. Metode matriks balikan
                    d. Kaidah Cramer
                2. Determinan
                    a. Metode eliminasi Gauss
                    b. Metode eliminasi Gauss-Jordan
                    c. Kofaktor
                3. Matriks balikan
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

        while (isContinue) {
            System.out.println("Masukkan pilihan menu yang ingin dijalankan (Contoh: 1a, 2c, 4) :");
            String menu = sc.nextLine();
            System.out.println();

            System.out.println("PILIHAN INPUT");
            System.out.println("1. Keyboard");
            System.out.println("2. File");

            System.out.println("Masukkan pilihan input yang diinginkan (ketik angka 1 atau 2) :");
            int input = sc.nextInt();

            switch (input) {
                case 1:
                    break;
                case 2:
                    break;
            }

            switch (menu) {
                case "1a":
                    break;
                case "1b":
                    break;
                case "1c":
                    break;
                case "1d":
                    break;
                case "2a":
                    break;
                case "2b":
                    break;
                case "2c":
                    break;
                case "3a":
                    break;
                case "3b":
                    break;
                case "4":
                    break;
                case "5":
                    break;
                case "6":
                    break;
                case "7":
                    System.out.println("Terima kasih telah menggunakan kalkulator matriks kami.");
                    isContinue = false;
                    break;
            }
        }
    }
}
