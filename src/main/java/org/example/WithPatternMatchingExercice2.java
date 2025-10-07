package org.example;

import java.util.Scanner;

public class WithPatternMatchingExercice2 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez une valeur (nombre, texte ou laissez vide pour null) : ");
        String input = scanner.nextLine();

        Object valeur;

        // Détermination du type de valeur selon les instructions
        if (input.isBlank()) {
            valeur = null;
        } else {
            try {
                valeur = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                valeur = input;
            }
        }

        // Pattern matching avec switch (Java 17+)
        String message = switch (valeur) {
            case null -> "La valeur est null.";
            case Integer i -> "C'est un nombre : " + i;
            case String s -> "C'est une chaîne : " + s;
            default -> "Type non reconnu.";
        };

        System.out.println(message);

        scanner.close();
    }
}