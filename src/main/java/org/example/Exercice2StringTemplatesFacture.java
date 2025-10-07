package org.example;

public class Exercice2StringTemplatesFacture {
    public static void main(String[] args) {
        genererFacture("Alice", "Casque Bluetooth", 79.90, 2);
        System.out.println("\n" + "=".repeat(40) + "\n");
        genererFacture("Romain", "Clavier mécanique", 129.99, 1);
    }

    private static void genererFacture(String customer, String product, double price, int quantity) {
        double total = price * quantity;

        // Utilisation d'un text block avec String Templates
        String facture = STR."""
                === FACTURE ===
                Client       : \{customer}
                Produit      : \{product}
                Quantité     : \{quantity}
                Prix unitaire: \{String.format("%.2f", price)} €
                Total HT     : \{String.format("%.2f", total)} €
                TVA (20%)    : \{String.format("%.2f", calculerTVA(total))} €
                Montant TTC  : \{String.format("%.2f", total + calculerTVA(total))} €
                """;

        System.out.println(facture);
    }

    // Fonction pour calculer la TVA à 20%
    private static double calculerTVA(double montantHT) {
        return montantHT * 0.20;
    }
}