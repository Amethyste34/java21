package org.example.sequenced_collections;

import java.util.LinkedList;
import java.util.SequencedCollection;

public class Exercice2GestionCommandes {
    public static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("       SYSTÈME DE GESTION DE COMMANDES EN LIGNE");
        System.out.println("═══════════════════════════════════════════════════════\n");

        // 1. Création d'une SequencedCollection avec LinkedList
        SequencedCollection<String> commandes = new LinkedList<>();

        // 2. Ajout de commandes normales avec addLast()
        System.out.println("📦 Ajout de commandes normales :");
        commandes.addLast("Commande n°101 - Tom");
        System.out.println("   ✓ Commande n°101 - Tom ajoutée");

        commandes.addLast("Commande n°102 - Sarah");
        System.out.println("   ✓ Commande n°102 - Sarah ajoutée");

        commandes.addLast("Commande n°103 - Marc");
        System.out.println("   ✓ Commande n°103 - Marc ajoutée");

        System.out.println(STR."""
            \uD83D\uDCCB File des commandes : \{commandes}""");
        System.out.println();

        // 3. Une commande urgente arrive
        System.out.println("🚨 COMMANDE URGENTE ! Ajout en priorité avec addFirst()");
        commandes.addFirst("Commande n°100 - Urgente");
        System.out.println("   ✓ Commande n°100 - Urgente ajoutée en priorité");

        System.out.println(STR."""
            \uD83D\uDCCB File des commandes après urgence : \{commandes}""");
        System.out.println();

        // 4. Affichage de la première et dernière commande
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("    INFORMATIONS SUR LA FILE");
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println(STR."\uD83D\uDD1C Première commande à traiter : \{commandes.getFirst()}");
        System.out.println(STR."\uD83D\uDD1A Dernière commande ajoutée     : \{commandes.getLast()}");
        System.out.println();

        // 5. Simulation du traitement des commandes
        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("    TRAITEMENT DES COMMANDES");
        System.out.println("═══════════════════════════════════════════════════════\n");

        traiterCommande(commandes);
        traiterCommande(commandes);

        // 6. Ajout d'une nouvelle commande en cours de traitement
        System.out.println("📦 Nouvelle commande arrive pendant le traitement :");
        commandes.addLast("Commande n°104 - Julie");
        System.out.println("   ✓ Commande n°104 - Julie ajoutée\n");

        System.out.println(STR."\uD83D\uDCCB File des commandes mise à jour : \{commandes}");
        System.out.println();

        // Traitement des commandes restantes
        System.out.println("🔄 Traitement des commandes restantes :\n");
        while (!commandes.isEmpty()) {
            traiterCommande(commandes);
        }

        System.out.println("═══════════════════════════════════════════════════════");
        System.out.println("✅ Toutes les commandes ont été traitées !");
        System.out.println("═══════════════════════════════════════════════════════");
    }

    /**
     * Méthode qui simule le traitement d'une commande
     * - Retire la première commande de la file
     * - Affiche la commande traitée
     * - Affiche la file mise à jour
     */
    private static void traiterCommande(SequencedCollection<String> commandes) {
        if (commandes.isEmpty()) {
            System.out.println("⚠️  Aucune commande à traiter.\n");
            return;
        }

        // Retirer la première commande
        String commandeTraitee = commandes.removeFirst();

        // Afficher la commande traitée
        System.out.println(STR."⚙\uFE0F  Traitement en cours : \{commandeTraitee}");

        // Simuler un traitement
        try {
            Thread.sleep(500); // Pause de 500ms pour simuler le traitement
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        System.out.println(STR."✅ \{commandeTraitee} → TRAITÉE");

        // Afficher la file mise à jour
        if (commandes.isEmpty()) {
            System.out.println("📋 File des commandes : [vide]");
        } else {
            System.out.println(STR."\uD83D\uDCCB File des commandes : \{commandes}");
            System.out.println(STR."   Nombre de commandes restantes : \{commandes.size()}");
        }

        System.out.println();
    }
}