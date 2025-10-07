package org.example.sequenced_collections;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.SequencedCollection;

public class Exercice1SequencedCollections {
    static void main(String[] args) {
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("    DÉMONSTRATION DES SEQUENCED COLLECTIONS");
        System.out.println("═══════════════════════════════════════════════════\n");

        // Création d'une SequencedCollection avec LinkedList
        // (ArrayList et LinkedList implémentent SequencedCollection depuis Java 21)
        SequencedCollection<String> noms = new LinkedList<>();

        // Ajout initial de noms
        noms.add("Alice");
        noms.add("Bob");
        noms.add("Charlie");

        System.out.println(STR."\uD83D\uDCCB Collection initiale : \{noms}");
        System.out.println();

        // 1. Ajouter un élément au début avec addFirst()
        System.out.println("➕ Ajout de 'Zoé' au début avec addFirst()");
        noms.addFirst("Zoé");
        System.out.println(STR."   Résultat : \{noms}");
        System.out.println();

        // 2. Ajouter un élément à la fin avec addLast()
        System.out.println("➕ Ajout de 'Maxime' à la fin avec addLast()");
        noms.addLast("Maxime");
        System.out.println(STR."   Résultat : \{noms}");
        System.out.println();

        // 3. Afficher le premier élément avec getFirst()
        System.out.println(STR."\uD83D\uDD0D Premier élément : \{noms.getFirst()}");

        // 4. Afficher le dernier élément avec getLast()
        System.out.println(STR."\uD83D\uDD0D Dernier élément : \{noms.getLast()}");
        System.out.println();

        // 5. Supprimer le premier élément avec removeFirst()
        System.out.println("➖ Suppression du premier élément avec removeFirst()");
        String supprime = noms.removeFirst();
        System.out.println(STR."   Élément supprimé : \{supprime}");
        System.out.println(STR."   Collection après suppression : \{noms}");
        System.out.println();

        // Bonus : Utilisation de reversed() (nouvelle méthode)
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("    BONUS : MÉTHODE REVERSED()");
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println(STR."Collection normale : \{noms}");
        System.out.println(STR."Collection inversée : \{noms.reversed()}");
        System.out.println();

        // Démonstration avec ArrayList
        demonstrationAvecArrayList();
    }

    private static void demonstrationAvecArrayList() {
        System.out.println("═══════════════════════════════════════════════════");
        System.out.println("    DÉMONSTRATION AVEC ARRAYLIST");
        System.out.println("═══════════════════════════════════════════════════\n");

        SequencedCollection<Integer> nombres = new ArrayList<>();

        // Ajout d'éléments
        nombres.add(10);
        nombres.add(20);
        nombres.add(30);

        System.out.println(STR."Collection initiale : \{nombres}");

        // Manipulation avec les nouvelles méthodes
        nombres.addFirst(5);
        System.out.println(STR."Après addFirst(5) : \{nombres}");

        nombres.addLast(40);
        System.out.println(STR."Après addLast(40) : \{nombres}");

        System.out.println(STR."""
            Premier : \{nombres.getFirst()}""");
        System.out.println(STR."Dernier : \{nombres.getLast()}");

        nombres.removeLast();
        System.out.println(STR."""
            Après removeLast() : \{nombres}""");

        System.out.println("\n═══════════════════════════════════════════════════");
    }
}