package org.example;

// Record pour les informations communes
record Infos(String marque, int puissance) {}

// Hiérarchie scellée Vehicule
sealed interface Vehicule permits Voiture, Moto {}

// Record Voiture
record Voiture(Infos infos) implements Vehicule {}

// Record Moto
record Moto(Infos infos) implements Vehicule {}

public class Exercice2NestedRecordPatternsAndSealedHierarchy {
    public static void main(String[] args) {
        // Création de différents véhicules
        Vehicule voiture1 = new Voiture(new Infos("BMW", 400));
        Vehicule voiture2 = new Voiture(new Infos("Mercedes", 300));
        Vehicule moto1 = new Moto(new Infos("Yamaha", 120));
        Vehicule moto2 = new Moto(new Infos("Ducati", 150));

        // Affichage des informations
        afficherVehicule(voiture1);
        afficherVehicule(voiture2);
        afficherVehicule(moto1);
        afficherVehicule(moto2);
    }

    // Méthode utilisant les record patterns imbriqués (Java 21+)
    private static void afficherVehicule(Vehicule vehicule) {
        String message = switch (vehicule) {
            case Voiture(Infos(String marque, int puissance)) ->
                    String.format("🚗 Voiture %s de %d chevaux", marque, puissance);
            case Moto(Infos(String marque, int puissance)) ->
                    String.format("🏍️ Moto %s de %d chevaux", marque, puissance);
            default -> "Type de véhicule inconnu";
        };

        System.out.println(message);
    }
}