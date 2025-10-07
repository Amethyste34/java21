package org.example;

// Définition du record Personne
record Personne(String nom, int age, String ville) {
}

public class PersonneRecordExercice2bis {
    public static void main(String[] args) {
        // Création de plusieurs instances du record Personne
        Personne p1 = new Personne("Alice", 25, "Paris");
        Personne p2 = new Personne("Bob", 17, "Lyon");
        Personne p3 = new Personne("Charlie", 30, "Marseille");
        Personne p4 = new Personne("Diane", 16, "Toulouse");

        // Affichage des informations avec toString()
        System.out.println("=== Informations des personnes ===");
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);

        System.out.println("\n=== Test de majorité ===");

        // Test de majorité pour chaque personne
        verifierMajorite(p1);
        verifierMajorite(p2);
        verifierMajorite(p3);
        verifierMajorite(p4);
    }

    // Méthode pour vérifier si une personne est majeure
    private static void verifierMajorite(Personne personne) {
        if (personne.age() >= 18) {
            System.out.println(personne.nom() + " est majeur(e) (" + personne.age() + " ans).");
        } else {
            System.out.println(personne.nom() + " est mineur(e) (" + personne.age() + " ans).");
        }
    }
}