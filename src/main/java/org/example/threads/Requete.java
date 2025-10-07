package org.example.threads;

import java.util.Random;

public class Requete {
    private final int id;
    private static final Random random = new Random();

    public Requete(int id) {
        this.id = id;
    }

    public void traiter() {
        try {
            // Afficher uniquement les 20 premières requêtes pour ne pas polluer la sortie
            if (id < 20) {
                System.out.println(STR."\uD83D\uDD04 Traitement de la requête #\{id} par \{Thread.currentThread().getName()}");
            }

            // Simuler un traitement avec un temps aléatoire entre 100 et 500 ms
            int tempsTraitement = 100 + random.nextInt(401); // 100 à 500 ms
            Thread.sleep(tempsTraitement);

            // Afficher la fin du traitement
            if (id < 20) {
                System.out.println(STR."✅ Requête #\{id} terminée (\{tempsTraitement} ms)");
            }
        } catch (InterruptedException e) {
            System.err.println(STR."❌ Requête #\{id} interrompue");
            Thread.currentThread().interrupt();
        }
    }

    public int getId() {
        return id;
    }
}