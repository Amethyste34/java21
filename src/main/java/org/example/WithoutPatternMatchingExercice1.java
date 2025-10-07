package org.example;

import java.util.Scanner;

public class WithoutPatternMatchingExercice1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Entrez un jour de la semaine : ");
        String jour = scanner.nextLine().toLowerCase();

        switch (jour) {
            case "lundi":
            case "mardi":
            case "mercredi":
            case "jeudi":
            case "vendredi":
                System.out.println("C'est un jour de travail.");
                break;
            case "samedi":
            case "dimanche":
                System.out.println("C'est le week-end !");
                break;
            default:
                System.out.println("Jour non reconnu. Veuillez entrer un jour valide.");
                break;
        }

        scanner.close();
    }
}