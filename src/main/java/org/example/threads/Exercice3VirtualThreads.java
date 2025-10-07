package org.example.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercice3VirtualThreads {

    private static final int NOMBRE_TACHES = 10_000;
    private static final int DUREE_SOMMEIL_MS = 100;

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  COMPARAISON : Virtual Threads vs Threads Classiques");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println(STR."Nombre de tâches : \{NOMBRE_TACHES}");
        System.out.println(STR."""
            Durée de sommeil par tâche : \{DUREE_SOMMEIL_MS} ms
            """);

        // Test avec Virtual Threads
        System.out.println("🚀 Test avec VIRTUAL THREADS...");
        long tempsVirtual = executerAvecVirtualThreads();
        System.out.println(STR."""
            ✓ Temps d'exécution (Virtual Threads) : \{tempsVirtual} ms
            """);

        // Test avec Thread Pool classique
        System.out.println("🐌 Test avec THREAD POOL CLASSIQUE (10 threads)...");
        long tempsClassique = executerAvecThreadPoolClassique();
        System.out.println(STR."""
            ✓ Temps d'exécution (Thread Pool) : \{tempsClassique} ms
            """);

        // Comparaison
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("  RÉSULTATS");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println(STR."Virtual Threads : \{tempsVirtual} ms");
        System.out.println(STR."Thread Pool     : \{tempsClassique} ms");
        double ratio = (double) tempsClassique / tempsVirtual;
        System.out.println(STR."""
            ⚡ Les Virtual Threads sont \{String.format("%.2f", ratio)}x plus rapides !""");
    }

    // Méthode avec Virtual Threads (Java 21)
    private static long executerAvecVirtualThreads() {
        long debut = System.currentTimeMillis();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (int i = 0; i < NOMBRE_TACHES; i++) {
                final int numeroTache = i;
                executor.submit(() -> executerTache(numeroTache));
            }
        } // L'executor se ferme automatiquement et attend la fin de toutes les tâches

        long fin = System.currentTimeMillis();
        return fin - debut;
    }

    // Méthode avec Thread Pool classique
    private static long executerAvecThreadPoolClassique() {
        long debut = System.currentTimeMillis();

        ExecutorService executor = Executors.newFixedThreadPool(10);

        try {
            for (int i = 0; i < NOMBRE_TACHES; i++) {
                final int numeroTache = i;
                executor.submit(() -> executerTache(numeroTache));
            }

            // Arrêter l'acceptation de nouvelles tâches
            executor.shutdown();

            // Attendre que toutes les tâches soient terminées
            if (!executor.awaitTermination(5, TimeUnit.MINUTES)) {
                System.err.println("Les tâches n'ont pas terminé dans le délai imparti");
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            System.err.println(STR."Interruption lors de l'attente : \{e.getMessage()}");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long fin = System.currentTimeMillis();
        return fin - debut;
    }

    // Tâche simulant une requête
    private static void executerTache(int numeroTache) {
        try {
            // Afficher le nom du thread (seulement pour les 10 premières tâches pour ne pas polluer la sortie)
            if (numeroTache < 10) {
                System.out.println(STR."Tâche \{numeroTache} - Thread : \{Thread.currentThread()}");
            }

            // Simuler un travail (ex: requête réseau, I/O)
            Thread.sleep(DUREE_SOMMEIL_MS);

            // Afficher la fin (seulement pour les 10 premières tâches)
            if (numeroTache < 10) {
                System.out.println(STR."Tâche \{numeroTache} terminée !");
            }

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}