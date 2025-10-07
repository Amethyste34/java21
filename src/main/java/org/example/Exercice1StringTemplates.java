package org.example;

import java.util.Scanner;

public class Exercice1StringTemplates {
    public static void main(String[] args) {
        demanderInformations();
    }

    private static void demanderInformations() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez votre prénom : ");
        String prenom = scanner.nextLine();

        System.out.print("Entrez votre âge : ");
        int age = scanner.nextInt();
        scanner.nextLine(); // Consommer le retour à la ligne

        System.out.print("Entrez votre ville : ");
        String ville = scanner.nextLine();

        // Utilisation des String Templates avec STR
        String message = STR."Bonjour \{prenom} ! Tu as \{age} ans et tu habites à \{ville}.";

        System.out.println("\n" + message);

        scanner.close();
    }
}