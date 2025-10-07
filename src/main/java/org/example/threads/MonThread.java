package org.example.threads;

public class MonThread implements Runnable {
    private final String nom;

    public MonThread(String nom) {
        this.nom = nom;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread en cours : " + nom + " - Itération " + i);

            try {
                // Pause de 500 millisecondes
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.err.println("Thread " + nom + " interrompu : " + e.getMessage());
                Thread.currentThread().interrupt(); // Restaurer le statut d'interruption
            }
        }
        System.out.println("✓ Thread " + nom + " terminé.");
    }
}