package view;

import java.util.Scanner;

public class UI {
    public static String readString(String msg){
        Scanner teclado = new Scanner(System.in);
        System.out.print(msg);
        return teclado.nextLine();
    }

    public static int readInt(String msg, int min, int max) {
        int input;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print(msg);
            while (!scanner.hasNextInt()) {
                System.out.println("Por favor, ingresa un número válido.");
                scanner.next(); // Consumir la entrada no válida
            }
            input = scanner.nextInt();
        } while (input < min || input > max);

        scanner.nextLine(); // Consumir el salto de línea pendiente
        return input;
    }
}