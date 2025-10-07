package org.example.threads;

public class Exercice1ThreadClassique {
    static void main(String[] args) {
        System.out.println("=== Démarrage des threads ===\n");

        // Création de deux instances de Runnable
        MonThread runnable1 = new MonThread("Thread-A");
        MonThread runnable2 = new MonThread("Thread-B");

        // Création des threads
        Thread thread1 = new Thread(runnable1);
        Thread thread2 = new Thread(runnable2);

        // Démarrage des threads
        thread1.start();
        thread2.start();

        // Attendre que les threads se terminent (optionnel)
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            System.err.println("Thread principal interrompu : " + e.getMessage());
            Thread.currentThread().interrupt();
        }

        System.out.println("\n=== Tous les threads sont terminés ===");
    }
}