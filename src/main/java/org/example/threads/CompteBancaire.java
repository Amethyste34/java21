package org.example.threads;

// Classe CompteBancaire avec synchronisation
public class CompteBancaire {
    private int solde;

    public CompteBancaire(int soldeInitial) {
        this.solde = soldeInitial;
    }

    // Méthode synchronisée pour éviter les problèmes de concurrence
    public synchronized void retirer(int montant, String nomThread) {
        System.out.println(STR."[\{nomThread}] Tentative de retrait de \{montant}€");
        System.out.println(STR."[\{nomThread}] Solde actuel : \{solde}€");

        if (solde >= montant) {
            try {
                // Simulation d'un traitement (lecture BD, vérifications, etc.)
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }

            solde -= montant;
            System.out.println(STR."[\{nomThread}] ✓ Retrait effectué : \{montant}€");
            System.out.println(STR."[\{nomThread}] Nouveau solde : \{solde}€");
        } else {
            System.out.println(STR."[\{nomThread}] ✗ Retrait refusé : solde insuffisant !");
        }

        System.out.println("─────────────────────────────────");
    }

    public synchronized int getSolde() {
        return solde;
    }
}