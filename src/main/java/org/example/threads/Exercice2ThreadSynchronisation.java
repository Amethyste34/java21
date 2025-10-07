package org.example.threads;

public class Exercice2ThreadSynchronisation {
    static void main(String[] args) {
        // Création d'un compte avec un solde initial
        CompteBancaire compte = new CompteBancaire(1000);

        System.out.println("═══════════════════════════════════");
        System.out.println("    SIMULATION COMPTE BANCAIRE");
        System.out.println("═══════════════════════════════════");
        System.out.println(STR."Solde initial : \{compte.getSolde()}€");
        System.out.println("─────────────────────────────────\n");

        // Création de deux threads qui tentent de retirer de l'argent
        Thread thread1 = new Thread(new ThreadRetrait(compte, 300, "Client-A", 3));
        Thread thread2 = new Thread(new ThreadRetrait(compte, 250, "Client-B", 3));

        // Démarrage des threads
        thread1.start();
        thread2.start();

        // Attendre que les threads se terminent
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Thread principal interrompu");
            Thread.currentThread().interrupt();
        }

        System.out.println("\n═══════════════════════════════════");
        System.out.println("    FIN DES OPÉRATIONS");
        System.out.println("═══════════════════════════════════");
        System.out.println("Solde final : " + compte.getSolde() + "€");
    }
}