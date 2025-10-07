package org.example;

// Classe abstraite scellée Utilisateur
sealed abstract class Utilisateur permits Administrateur, Moderateur, Membre {
    private final String nom;

    public Utilisateur(String nom) {
        this.nom = nom;
    }

    public String getNom() {
        return nom;
    }
}

//Sous-classe Administrateur
final class Administrateur extends Utilisateur {
    private final int niveauAcces;

    public Administrateur(String nom, int niveauAcces) {
        super(nom);
        this.niveauAcces = niveauAcces;
    }

    public int getNiveauAcces() {
        return niveauAcces;
    }
}

// Sous-classe Moderateur
final class Moderateur extends Utilisateur {
    private final String zoneModeration;

    public Moderateur(String nom, String zoneModeration) {
        super(nom);
        this.zoneModeration = zoneModeration;
    }

    public String getZoneModeration() {
        return zoneModeration;
    }
}

// Sous-classe Membre
final class Membre extends Utilisateur {
    private final int points;

    public Membre(String nom, int points) {
        super(nom);
        this.points = points;
    }

    public int getPoints() {
        return points;
    }
}

public class Exercice1SealedClasses {
    public static void main(String[] args) {
        // Création de différents utilisateurs
        Utilisateur admin = new Administrateur("Alice", 5);
        Utilisateur modo = new Moderateur("Bob", "Forum général");
        Utilisateur membre = new Membre("Charlie", 350);

        // Affichage des messages personnalisés
        afficherInfoUtilisateur(admin);
        afficherInfoUtilisateur(modo);
        afficherInfoUtilisateur(membre);
    }

    // Méthode utilisant un switch expression avec pattern matching
    private static void afficherInfoUtilisateur(Utilisateur utilisateur) {
        String message = switch (utilisateur) {
            case Administrateur admin ->
                    String.format("👑 %s est Administrateur avec un niveau d'accès %d",
                            admin.getNom(), admin.getNiveauAcces());
            case Moderateur modo ->
                    String.format("🛡️ %s est Modérateur de la zone '%s'",
                            modo.getNom(), modo.getZoneModeration());
            case Membre membre ->
                    String.format("👤 %s est Membre avec %d points",
                            membre.getNom(), membre.getPoints());
        };

        System.out.println(message);
    }
}