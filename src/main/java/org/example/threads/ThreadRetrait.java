package org.example.threads;

// Classe représentant un thread qui effectue des retraits
public class ThreadRetrait implements Runnable {
    private final CompteBancaire compte;
    private final int montant;
    private final String nom;
    private final int nombreRetraits;

    public ThreadRetrait(CompteBancaire compte, int montant, String nom, int nombreRetraits) {
        this.compte = compte;
        this.montant = montant;
        this.nom = nom;
        this.nombreRetraits = nombreRetraits;
    }

    @Override
    public void run() {
        for (int i = 0; i <= nombreRetraits; i++) {
            compte.retirer(montant, STR."\{nom}-\{i}");
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
    }
}