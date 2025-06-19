package main;

import affichage.*;
import data.*;
import java.util.Vector;

public class main {
    public static void main(String[] args) {
        // =================================================================
        // 1. DÉFINITION DES DÉPUTÉS (must be created before parties)
        // =================================================================
        Depute d1 = new Depute("Rakoto Jean");
        Depute d2 = new Depute("Rasoa Kely");
        Depute d3 = new Depute("Bema Lahy");
        Depute d4 = new Depute("Soa Fara");
        Depute d5 = new Depute("Zoky Be");
        Depute d6 = new Depute("Zandry Kely");

        // =================================================================
        // 2. DÉFINITION DES PARTIS POLITIQUES (using the available constructor)
        // =================================================================
        Vector<Depute> aroDeputes = new Vector<>();
        aroDeputes.add(d1);
        aroDeputes.add(d2);
        PartiPolitique aro = new PartiPolitique(aroDeputes);

        Vector<Depute> mtsDeputes = new Vector<>();
        mtsDeputes.add(d3);
        mtsDeputes.add(d4);
        PartiPolitique mts = new PartiPolitique(mtsDeputes);

        Vector<Depute> kdeDeputes = new Vector<>();
        kdeDeputes.add(d5);
        kdeDeputes.add(d6);
        PartiPolitique kde = new PartiPolitique(kdeDeputes);

        // =================================================================
        // 3. CRÉATION DE LA STRUCTURE GÉOGRAPHIQUE ET POLITIQUE
        // =================================================================
        Vector<Faritany> listeFaritany = new Vector<>();

        // === FARITANY: ANTANANARIVO ===
        Faritany antananarivo = new Faritany("Antananarivo");
        listeFaritany.add(antananarivo);

        // --- Faritra: Analamanga ---
        Faritra analamanga = new Faritra("Analamanga");
        antananarivo.getFaritra().add(analamanga);

        // District: Antananarivo Avaradrano
        Vector<BureauVote> bvAvaradrano = new Vector<>();
        bvAvaradrano.add(new BureauVote("Ambohimanga Rova"));
        bvAvaradrano.add(new BureauVote("Anosy Avaratra"));
        Vector<Depute> deputesAvaradrano = new Vector<>();
        deputesAvaradrano.add(d1);
        deputesAvaradrano.add(d3);
        // Using constructor: District(Vector<Depute> candidats, Vector<BureauVote> Bureaux, String name, int nombreElus)
        District avaradrano = new District(deputesAvaradrano, bvAvaradrano, "Antananarivo Avaradrano", 2);
        analamanga.getDistricts().add(avaradrano);

        // District: Manjakandriana
        Vector<BureauVote> bvManjakandriana = new Vector<>();
        bvManjakandriana.add(new BureauVote("Manjakandriana Centre"));
        bvManjakandriana.add(new BureauVote("Ambatomanga"));
        Vector<Depute> deputesManjakandriana = new Vector<>();
        deputesManjakandriana.add(d2);
        deputesManjakandriana.add(d4);
        District manjakandriana = new District(deputesManjakandriana, bvManjakandriana, "Manjakandriana", 2);
        analamanga.getDistricts().add(manjakandriana);

        // === FARITANY: TOAMASINA ===
        Faritany toamasina = new Faritany("Toamasina");
        listeFaritany.add(toamasina);

        // --- Faritra: Atsinanana ---
        Faritra atsinanana = new Faritra("Atsinanana");
        toamasina.getFaritra().add(atsinanana);

        // District: Toamasina I
        Vector<BureauVote> bvToamasina1 = new Vector<>();
        bvToamasina1.add(new BureauVote("Anjoma"));
        bvToamasina1.add(new BureauVote("Ampasimazava"));
        Vector<Depute> deputesToamasina1 = new Vector<>();
        deputesToamasina1.add(d5);
        deputesToamasina1.add(d6);
        District toamasina1 = new District(deputesToamasina1, bvToamasina1, "Toamasina I", 2);
        atsinanana.getDistricts().add(toamasina1);

        // =================================================================
        // 4. LANCEMENT DES INTERFACES GRAPHIQUES
        // =================================================================
        // These lines will create two separate windows, one for data entry and one for results.
        new FormCandidat(listeFaritany);
        new FormResult(listeFaritany);
    }
}
