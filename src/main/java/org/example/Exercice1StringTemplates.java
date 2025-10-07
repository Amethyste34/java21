package org.example;

import java.util.Scanner;

public class Exercice1StringTemplates {
    static void main(String[] args) {
        demanderInformations();
    }

    private static void demanderInformations() {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Entrez votre prénom : ");
            String prenom = scanner.nextLine().trim();

            if (prenom.isEmpty()) {
                throw new IllegalArgumentException("Le prénom ne peut pas être vide !");
            }

            System.out.print("Entrez votre âge : ");
            int age;

            try {
                age = scanner.nextInt();
                scanner.nextLine(); // Consommer le retour à la ligne

                if (age < 0 || age > 150) {
                    throw new IllegalArgumentException("L'âge doit être entre 0 et 150 !");
                }
            } catch (java.util.InputMismatchException e) {
                scanner.nextLine(); // Nettoyer le buffer
                throw new IllegalArgumentException("L'âge doit être un nombre entier !");
            }

            System.out.print("Entrez votre ville : ");
            String ville = scanner.nextLine().trim();

            if (ville.isEmpty()) {
                throw new IllegalArgumentException("La ville ne peut pas être vide !");
            }

            // Utilisation des String Templates avec STR
            String message = STR."Bonjour \{prenom} ! Tu as \{age} ans et tu habites à \{ville}.";

            System.out.println("\n✅ " + message);

        } catch (IllegalArgumentException e) {
            System.err.println("\n❌ Erreur : " + e.getMessage());
            System.out.println("Veuillez réessayer.\n");
            demanderInformations(); // Relancer la saisie
        } catch (Exception e) {
            System.err.println("\n❌ Une erreur inattendue s'est produite : " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}