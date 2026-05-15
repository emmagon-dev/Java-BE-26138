package util;

import java.util.Scanner;

public class Validador {

    public static int leerEntero(Scanner scanner) {

        while (!scanner.hasNextInt()) {

            System.out.println("Debe ingresar un número entero.");
            scanner.next();
        }

        int numero = scanner.nextInt();
        scanner.nextLine();

        return numero;
    }

    public static double leerDouble(Scanner scanner) {

        while (!scanner.hasNextDouble()) {

            System.out.println("Debe ingresar un número válido.");
            scanner.next();
        }

        double numero = scanner.nextDouble();
        scanner.nextLine();

        return numero;
    }
}