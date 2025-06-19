package affichage;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import data.*;

public class FormResult extends JPanel {

    private final JComboBox<Faritany> dropdown_faritany;
    private final JComboBox<Faritra> dropdown_faritra;
    private final JComboBox<District> dropdown_distrika;
    private final JComboBox<BureauVote> dropdown_bureauDeVote;

    Vector <Faritany> liste_faritany;
    Gestion g;
    private final JButton submitButton;
    public FormResult(Vector <Faritany> lf) {
        this.liste_faritany = lf;
        // initializeData();
        g = new Gestion(liste_faritany);
        dropdown_faritany = new JComboBox<>(liste_faritany);
        dropdown_faritra = new JComboBox<>();
        dropdown_distrika = new JComboBox<>();
        dropdown_bureauDeVote = new JComboBox<>();

        dropdown_faritany.addActionListener(e -> updateFaritra());
        dropdown_faritra.addActionListener(e -> updateDistrika());
        dropdown_distrika.addActionListener(e -> updateBV());

        submitButton = new JButton("Submit");
        submitButton.addActionListener(e -> handleSubmit("votes.txt"));
        

        setLayout(new GridLayout(5, 2, 5, 5));
        add(new JLabel("Faritany:"));
        add(dropdown_faritany);
        add(new JLabel("Faritra:"));
        add(dropdown_faritra);
        add(new JLabel("Distrika:"));
        add(dropdown_distrika);
        add(new JLabel("Bureau de vote:"));
        add(dropdown_bureauDeVote);
        add(submitButton);
        
        JFrame frame = new JFrame("Formulaire Resultat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        frame.add(this);
        // frame.getContentPane().add(new FormCandidat());    
        frame.setVisible(true);
        
        updateFaritra();
    }
private void handleSubmit(String filename){
    Faritany faritany = getSelectedFaritany();
    Faritra faritra = getSelectedFaritra();
    District distrika = getSelectedDistrika();
    BureauVote bv = getSelectedBv();
    Vector <String[]> datas;
    // Map <String, Map <String, Integer>> deputesToShow;
    if(faritany == null)
    {
        datas = g.getAllDeputes(filename);
    }
    else if(faritany != null && faritra == null)
    {
        String faritanyS = faritany.toString();
        datas = g.getDeputesByFaritany(faritanyS, filename);
    }
    else if(faritany != null && faritra != null && distrika == null)
    {
        String faritraS = faritra.toString();
        datas = g.getDeputesByFaritra(faritraS, filename);
    }
    else if(faritany != null && faritra != null && distrika != null && bv == null)
    {
        String distrikaS = distrika.toString();
        datas = g.getDeputesByDistricts(distrikaS, filename);
    }
    else {
        String bvS = bv.toString();
        datas = g.getDeputesByBv(bvS, filename);
    }
    System.out.println("Deputes loaded from file: ");
    for(String [] s : datas)
    {
        System.out.println("Faritany: " + s[0] + ", Faritra: " + s[1] + ", Distrika: " + s[2] + "Bv: " + s[3] + 
        ", Deputes: " + s[4] + "Nombre de votes: " + s[5]);
    }
    // deputesToShow = Gestion.toHashMap(datas);
    // for(Map.Entry <String, Map <String, Integer>> entry : deputesToShow.entrySet())
    // {
    //     System.out.println("Distrika: "+ entry.getKey());
    //     for(Map.Entry<String, Integer> entry3 : entry.getValue().entrySet())
    //     {
    //         System.out.println("Depute: " + entry3.getKey() + "Nombre de votes: " + entry3.getValue());
    //     }
    // }
    // Map <String, Map <String, Integer>> elus = Gestion.getElus(deputesToShow);
    // System.out.println("Les elus: ");
    // for(Map.Entry <String, Map <String, Integer>> entry : elus.entrySet())
    // {
    //     System.out.println("Distrika: "+ entry.getKey());
    //     for(Map.Entry<String, Integer> entry3 : entry.getValue().entrySet())
    //     {
    //         System.out.println("Depute: " + entry3.getKey() + "Nombre de votes: " + entry3.getValue());
            
    //     }
    // }
    // Tableau t = new Tableau(elus);
    // // Vector <String[]> loaded;
    // // try {
    //     //     loaded = Gestion.loadDeputes(filename);
    //     // }
    //     // catch (Exception e)
    //     // {
    //         //     throw new RuntimeException();
    //         // }
    // JFrame result = new JFrame("Resulat des elections");
    // result.add(t);
    // // 
    // result.setSize(1366, 768);
    // result.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // result.setVisible(true);
}
// private void initializeData() {
//     // 6 provinces
//     String[] provinces = new String[]
//         {"Antananarivo", "Antsiranana", "Mahajanga",
//         "Toamasina", "Fianarantsoa", "Toliara"
//     };

//     // Provinces → Régions
//     faritraByFaritany.put(null, new String[0]);
//     faritraByFaritany.put("Antananarivo", new String[]{null, "Analamanga", "Vakinankaratra", "Itasy", "Bongolava"});
//     faritraByFaritany.put("Antsiranana", new String[]{null, "Diana", "Sava"});
//     faritraByFaritany.put("Mahajanga", new String[]{null, "Boeny", "Betsiboka", "Melaky", "Sofia"});
//     faritraByFaritany.put("Toamasina", new String[]{null, "Alaotra-Mangoro", "Atsinanana", "Analanjirofo"});
//     faritraByFaritany.put("Fianarantsoa", new String[]{null, "Amoron'i Mania", "Haute Matsiatra", "Vatovavy", "Atsimo-Atsinanana", "Ihorombe", "Fitovinany"});
//     faritraByFaritany.put("Toliara", new String[]{null, "Menabe", "Atsimo-Andrefana", "Androy", "Anosy"});

//     // Régions → Districts (extraits pour Analamanga, Diana ; répliquer pour toutes)
//     distrikaByFaritra.put(null, new String[0]);
//     distrikaByFaritra.put("Analamanga", new String[]{null, "Antananarivo-Renivohitra", "Antananarivo-Avaradrano", "Antananarivo-Atsimondrano", "Ambohidratrimo", "Andramasina", "Anjozorobe", "Ankazobe", "Manjakandriana"});
//     distrikaByFaritra.put("Vakinankaratra", new String[]{null, "Antsirabe I", "Antsirabe II", "Ambatolampy", "Antanifotsy", "Betafo", "Faratsiho", "Mandoto"});
//     distrikaByFaritra.put("Itasy", new String[]{null, "Arivonimamo", "Miarinarivo", "Soavinandriana"});
//     distrikaByFaritra.put("Bongolava", new String[]{null, "Tsiroanomandidy", "Fenoarivobe"});

//     // Province d'Antsiranana
//     distrikaByFaritra.put("Diana", new String[]{null, "Antsiranana I", "Antsiranana II", "Ambanja", "Ambilobe", "Nosy-Be"});
//     distrikaByFaritra.put("Sava", new String[]{null, "Sambava", "Antalaha", "Vohemar", "Andapa"});

//     // Province de Mahajanga
//     distrikaByFaritra.put("Boeny", new String[]{null, "Mahajanga I", "Mahajanga II", "Marovoay", "Ambato-Boeni", "Mitsinjo", "Soalala"});
//     distrikaByFaritra.put("Betsiboka", new String[]{null, "Maevatanana", "Tsaratanana", "Kandreho"});
//     distrikaByFaritra.put("Melaky", new String[]{null, "Maintirano", "Besalampy", "Antsalova", "Morafenobe", "Ambatomainty"});
//     distrikaByFaritra.put("Sofia", new String[]{null, "Antsohihy", "Port-Bergé", "Befandriana-Nord", "Mandritsara", "Analalava", "Mampikony", "Bealanana"});

//     // Province de Toamasina
//     distrikaByFaritra.put("Alaotra-Mangoro", new String[]{null, "Ambatondrazaka", "Amparafaravola", "Andilamena", "Anosibe An'ala", "Moramanga"});
//     distrikaByFaritra.put("Atsinanana", new String[]{null, "Toamasina I", "Toamasina II", "Brickaville", "Vatomandry", "Mahanoro", "Marolambo", "Antanambao Manampotsy"});
//     distrikaByFaritra.put("Analanjirofo", new String[]{null, "Fenoarivo Atsinanana", "Soanierana Ivongo", "Sainte-Marie", "Mananara Nord", "Maroantsetra", "Vavatenina"});

//     // Province de Fianarantsoa
//     distrikaByFaritra.put("Amoron'i Mania", new String[]{null, "Ambositra", "Ambatofinandrahana", "Manandriana", "Fandriana"});
//     distrikaByFaritra.put("Haute Matsiatra", new String[]{null, "Fianarantsoa I", "Ambalavao", "Ambohimahasoa", "Ikalamavony", "Isandra", "Lalangina", "Vohibato"});
//     distrikaByFaritra.put("Vatovavy", new String[]{null, "Mananjary", "Nosy Varika", "Ifanadiana"});
//     distrikaByFaritra.put("Fitovinany", new String[]{null, "Manakara", "Vohipeno", "Ikongo"});
//     distrikaByFaritra.put("Atsimo-Atsinanana", new String[]{null, "Farafangana", "Vangaindrano", "Vondrozo", "Befotaka", "Midongy-Atsimo"});
//     distrikaByFaritra.put("Ihorombe", new String[]{null, "Ihosy", "Iakora", "Ivohibe"});

//     // Province de Toliara
//     distrikaByFaritra.put("Menabe", new String[]{null, "Morondava", "Mahabo", "Manja", "Miandrivazo", "Belo-sur-Tsiribihina"});
//     distrikaByFaritra.put("Atsimo-Andrefana", new String[]{null, "Toliara I", "Toliara II", "Ampanihy", "Ankazoabo", "Benenitra", "Beroroha", "Betioky-Atsimo", "Morombe", "Sakaraha"});
//     distrikaByFaritra.put("Androy", new String[]{null, "Ambovombe-Androy", "Bekily", "Beloha", "Tsiombe"});
//     distrikaByFaritra.put("Anosy", new String[]{null, "Tolagnaro", "Amboasary-Atsimo", "Betroka"});
//     // → à compléter pour chaque région...
//     // Districts → bureaux de vote et députés
//     // int counter = 0;
//     bureauDeVoteByDistrika.put(null, new String[0]);
//     for (var entry : distrikaByFaritra.entrySet()) {
//         String region = entry.getKey();
//         for (String distrika : entry.getValue()) {
//             // créer 3 BV par distrika
//             // counter++;
//             if(distrika != null)
//             {
//                 List<String> bvList = new ArrayList<>();
//                 bvList.add(null);
//                 for (int i = 1; i <= 3; i++) {
//                     String bv = distrika + "_BV" + i;
//                     bvList.add(bv);
//                 }
//                 List<String> deputeList = new ArrayList<>();
//                 deputeList.add(null);
//                 for (int i = 1; i <= 10; i++) {
//                     String depute = distrika + "_depute_" + i;
//                     deputeList.add(depute);
//                 }
//                 String [] bvstring = bvList.toArray(new String[0]);
//                 String [] deputestring = deputeList.toArray(new String[0]);
//                 bureauDeVoteByDistrika.put(distrika, bvstring);
//                 deputesByDistrika.put(distrika, deputestring);
//             }
//         }
//     }
//     // System.out.println("Il y a " + counter + " distrika.");
// }

private void updateFaritra() {
    Faritany faritany_selected = (Faritany) dropdown_faritany.getSelectedItem();
    System.out.println("Faritany choisi: " + faritany_selected);
    Vector <Faritra> faritra = faritany_selected.getFaritra();
    // System.out.println("Tous les regions dans " + faritany_selected + ": ");
    // for(String a : faritra)
    // {
    //     System.out.println("Regions: " + a);
    // }
    dropdown_faritra.setModel(new DefaultComboBoxModel<>(faritra));
    updateDistrika(); 
    System.out.println("Distrika updated!");
}

private void updateDistrika() {
    Faritra faritra_selected = (Faritra) dropdown_faritra.getSelectedItem();
    System.out.println("Faritra choisi: " + faritra_selected);
    Vector <District> district = faritra_selected.getDistricts();
    // System.out.println("Tous les districts dans " + faritra_selected + ": ");
    // for(String a : distrika)
    // {
    //     System.out.println("Distrika: " + a);
    // }
    dropdown_distrika.setModel(new DefaultComboBoxModel<>(district));
    updateBV();
}
private void updateBV() {
    District distrika_selected = (District) dropdown_distrika.getSelectedItem();
    Vector <BureauVote> bv = distrika_selected.getBureaux();
    dropdown_bureauDeVote.setModel(new DefaultComboBoxModel<>(bv));
}

public Faritany getSelectedFaritany()
    {
        return (Faritany) dropdown_faritany.getSelectedItem();
    }
        public Faritra getSelectedFaritra()
    {
        return (Faritra) dropdown_faritra.getSelectedItem();
    }
        public District getSelectedDistrika()
    {
        return (District) dropdown_distrika.getSelectedItem();
    }
        public BureauVote getSelectedBv()
    {
        return (BureauVote) dropdown_bureauDeVote.getSelectedItem();
    }
}