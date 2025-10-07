package org.example.threads;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Exercice4ServeurRequetes {
    private static final int NOMBRE_REQUETES = 5_000;

    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("       SERVEUR DE TRAITEMENT DE REQUÊTES");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println(STR."Nombre de requêtes à traiter : \{NOMBRE_REQUETES}");
        System.out.println("Temps de traitement par requête : 100-500 ms (aléatoire)\n");

        // Création de la liste de requêtes
        List<Requete> requetes = creerRequetes(NOMBRE_REQUETES);

        // Test avec Virtual Threads
        System.out.println("🚀 TEST 1 : Traitement avec VIRTUAL THREADS");
        System.out.println("─────────────────────────────────────────────────────────────");
        long tempsVirtual = traiterAvecVirtualThreads(requetes);
        System.out.println(STR."""
            ✓ Temps total (Virtual Threads) : \{tempsVirtual} ms""");
        System.out.println(STR."""
            ✓ Temps moyen par requête : \{String.format("%.2f", (double) tempsVirtual / NOMBRE_REQUETES)} ms
            """);

        // Petite pause entre les deux tests
        attendre(2000);

        // Test avec Thread Pool classique
        System.out.println("═══════════════════════════════════════════════════════════\n");
        System.out.println("🐌 TEST 2 : Traitement avec THREAD POOL CLASSIQUE (100 threads)");
        System.out.println("─────────────────────────────────────────────────────────────");
        long tempsClassique = traiterAvecThreadPoolClassique(requetes);
        System.out.println(STR."""
            ✓ Temps total (Thread Pool) : \{tempsClassique} ms""");
        System.out.println(STR."""
            ✓ Temps moyen par requête : \{String.format("%.2f", (double) tempsClassique / NOMBRE_REQUETES)} ms
            """);

        // Comparaison finale
        afficherComparaison(tempsVirtual, tempsClassique);
    }

    // Créer une liste de requêtes
    private static List<Requete> creerRequetes(int nombre) {
        List<Requete> requetes = new ArrayList<>();
        for (int i = 0; i < nombre; i++) {
            requetes.add(new Requete(i));
        }
        return requetes;
    }

    // Traitement avec Virtual Threads (Java 21)
    private static long traiterAvecVirtualThreads(List<Requete> requetes) {
        long debut = System.nanoTime();

        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            for (Requete requete : requetes) {
                executor.submit(requete::traiter);
            }
        } // L'executor attend automatiquement la fin de toutes les tâches

        long fin = System.nanoTime();
        return (fin - debut) / 1_000_000; // Conversion en millisecondes
    }

    // Traitement avec Thread Pool classique
    private static long traiterAvecThreadPoolClassique(List<Requete> requetes) {
        long debut = System.nanoTime();

        ExecutorService executor = Executors.newFixedThreadPool(100);

        try {
            for (Requete requete : requetes) {
                executor.submit(requete::traiter);
            }

            executor.shutdown();

            if (!executor.awaitTermination(10, TimeUnit.MINUTES)) {
                System.err.println("⚠️ Timeout : toutes les tâches n'ont pas terminé");
                executor.shutdownNow();
            }

        } catch (InterruptedException e) {
            System.err.println("❌ Interruption lors de l'attente");
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        long fin = System.nanoTime();
        return (fin - debut) / 1_000_000; // Conversion en millisecondes
    }

    // Afficher la comparaison
    private static void afficherComparaison(long tempsVirtual, long tempsClassique) {
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("                    COMPARAISON FINALE");
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println(STR."Virtual Threads      : \{tempsVirtual} ms");
        System.out.println(STR."Thread Pool (100)    : \{tempsClassique} ms");
        System.out.println("───────────────────────────────────────────────────────────");

        double ratio = (double) tempsClassique / tempsVirtual;
        long difference = tempsClassique - tempsVirtual;

        System.out.println(STR."⚡ Gain de performance : \{String.format("%.2f", ratio)}x plus rapide");
        System.out.println(STR."⏱\uFE0F  Temps économisé     : \{difference} ms");

        if (ratio > 1.5) {
            System.out.println("\n🎉 Les Virtual Threads sont SIGNIFICATIVEMENT plus performants !");
        } else if (ratio > 1.1) {
            System.out.println("\n👍 Les Virtual Threads sont plus performants.");
        } else {
            System.out.println("\n⚖️  Performance similaire entre les deux approches.");
        }

        System.out.println("═══════════════════════════════════════════════════════════");
    }

    // Méthode utilitaire pour attendre
    private static void attendre(int millisecondes) {
        try {
            Thread.sleep(millisecondes);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}