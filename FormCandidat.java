package affichage;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormCandidat extends JPanel {

    private final JComboBox<String> dropdown_faritany;
    private final JComboBox<String> dropdown_faritra;
    private final JComboBox<String> dropdown_distrika;
    private final JComboBox<String> dropdown_bureauDeVote;
    private final JComboBox<String> dropdown_depute;
    private JTextField nombredevotes;

    private final Map<String, String[]> faritraByFaritany = new HashMap<>();
    private final Map<String, String[]> distrikaByFaritra = new HashMap<>();
    private final Map<String, String[]> bureauDeVoteByDistrika = new HashMap<>();
    private final Map<String, String[]> deputesByDistrika = new HashMap<>();
    private final JButton submitButton;
    

    public FormCandidat() {
        initializeData();

        dropdown_faritany = new JComboBox<>(faritraByFaritany.keySet().toArray(new String[0]));
        dropdown_faritra = new JComboBox<>();
        dropdown_distrika = new JComboBox<>();
        dropdown_bureauDeVote = new JComboBox<>();
        dropdown_depute = new JComboBox<>();

        dropdown_faritany.addActionListener(e -> updateFaritra());
        dropdown_faritra.addActionListener(e -> updateDistrika());
        dropdown_distrika.addActionListener(e -> updateBV());
        dropdown_bureauDeVote.addActionListener(e -> updateDepute());

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
        add(new JLabel("Depute:"));
        add(dropdown_depute);
        add(new JLabel("Nombre de votes:"));
        add(nombredevotes = new JTextField(5));
        add(submitButton);
        
        JFrame frame = new JFrame("Formulaire Candidat");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1366, 768);
        // frame.getContentPane().add(new FormCandidat());
        // frame.pack();
        // frame.setLocationRelativeTo(null);
        frame.add(this);
        frame.setVisible(true);
        
        updateFaritra();
    }
    private void handleSubmit(String filename) {
        String faritany = getSelectedFaritany();
        String faritra = getSelectedFaritra();
        String distrika = getSelectedDistrika();
        String bv = getSelectedBv();
        String depute = getSelectedDepute();
        String nombreDeVotes = getNombreVotes();
        System.out.println("Nombre de votes: " + nombreDeVotes);

        if (faritany == null || faritra == null || distrika == null || bv == null || depute == null) {
            JOptionPane.showMessageDialog(this, "Please make a selection in all fields.", "Incomplete Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String dataLine = String.join(";;", faritany, faritra, distrika, bv, depute, nombreDeVotes);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(dataLine);
            writer.newLine();
            JOptionPane.showMessageDialog(this, "Information saved successfully to " + filename);
        }
        catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
    private void insertNombreVote()
    {
        nombredevotes = new JTextField(10);
        add(nombredevotes);
        System.out.println("Nombre de votes added! ");
    }
private void initializeData() {
    // 6 provinces
    String[] provinces = new String[]
        {"Antananarivo", "Antsiranana", "Mahajanga",
        "Toamasina", "Fianarantsoa", "Toliara"
    };

    // Provinces → Régions
    faritraByFaritany.put(null, new String[0]);
    faritraByFaritany.put("Antananarivo", new String[]{null, "Analamanga", "Vakinankaratra", "Itasy", "Bongolava"});
    faritraByFaritany.put("Antsiranana", new String[]{null, "Diana", "Sava"});
    faritraByFaritany.put("Mahajanga", new String[]{null, "Boeny", "Betsiboka", "Melaky", "Sofia"});
    faritraByFaritany.put("Toamasina", new String[]{null, "Alaotra-Mangoro", "Atsinanana", "Analanjirofo"});
    faritraByFaritany.put("Fianarantsoa", new String[]{null, "Amoron'i Mania", "Haute Matsiatra", "Vatovavy", "Atsimo-Atsinanana", "Ihorombe", "Fitovinany"});
    faritraByFaritany.put("Toliara", new String[]{null, "Menabe", "Atsimo-Andrefana", "Androy", "Anosy"});

    // Régions → Districts (extraits pour Analamanga, Diana ; répliquer pour toutes)
    distrikaByFaritra.put(null, new String[0]);
    distrikaByFaritra.put("Analamanga", new String[]{null, "Antananarivo-Renivohitra", "Antananarivo-Avaradrano", "Antananarivo-Atsimondrano", "Ambohidratrimo", "Andramasina", "Anjozorobe", "Ankazobe", "Manjakandriana"});
    distrikaByFaritra.put("Vakinankaratra", new String[]{null, "Antsirabe I", "Antsirabe II", "Ambatolampy", "Antanifotsy", "Betafo", "Faratsiho", "Mandoto"});
    distrikaByFaritra.put("Itasy", new String[]{null, "Arivonimamo", "Miarinarivo", "Soavinandriana"});
    distrikaByFaritra.put("Bongolava", new String[]{null, "Tsiroanomandidy", "Fenoarivobe"});

    // Province d'Antsiranana
    distrikaByFaritra.put("Diana", new String[]{null, "Antsiranana I", "Antsiranana II", "Ambanja", "Ambilobe", "Nosy-Be"});
    distrikaByFaritra.put("Sava", new String[]{null, "Sambava", "Antalaha", "Vohemar", "Andapa"});

    // Province de Mahajanga
    distrikaByFaritra.put("Boeny", new String[]{null, "Mahajanga I", "Mahajanga II", "Marovoay", "Ambato-Boeni", "Mitsinjo", "Soalala"});
    distrikaByFaritra.put("Betsiboka", new String[]{null, "Maevatanana", "Tsaratanana", "Kandreho"});
    distrikaByFaritra.put("Melaky", new String[]{null, "Maintirano", "Besalampy", "Antsalova", "Morafenobe", "Ambatomainty"});
    distrikaByFaritra.put("Sofia", new String[]{null, "Antsohihy", "Port-Bergé", "Befandriana-Nord", "Mandritsara", "Analalava", "Mampikony", "Bealanana"});

    // Province de Toamasina
    distrikaByFaritra.put("Alaotra-Mangoro", new String[]{null, "Ambatondrazaka", "Amparafaravola", "Andilamena", "Anosibe An'ala", "Moramanga"});
    distrikaByFaritra.put("Atsinanana", new String[]{null, "Toamasina I", "Toamasina II", "Brickaville", "Vatomandry", "Mahanoro", "Marolambo", "Antanambao Manampotsy"});
    distrikaByFaritra.put("Analanjirofo", new String[]{null, "Fenoarivo Atsinanana", "Soanierana Ivongo", "Sainte-Marie", "Mananara Nord", "Maroantsetra", "Vavatenina"});

    // Province de Fianarantsoa
    distrikaByFaritra.put("Amoron'i Mania", new String[]{null, "Ambositra", "Ambatofinandrahana", "Manandriana", "Fandriana"});
    distrikaByFaritra.put("Haute Matsiatra", new String[]{null, "Fianarantsoa I", "Ambalavao", "Ambohimahasoa", "Ikalamavony", "Isandra", "Lalangina", "Vohibato"});
    distrikaByFaritra.put("Vatovavy", new String[]{null, "Mananjary", "Nosy Varika", "Ifanadiana"});
    distrikaByFaritra.put("Fitovinany", new String[]{null, "Manakara", "Vohipeno", "Ikongo"});
    distrikaByFaritra.put("Atsimo-Atsinanana", new String[]{null, "Farafangana", "Vangaindrano", "Vondrozo", "Befotaka", "Midongy-Atsimo"});
    distrikaByFaritra.put("Ihorombe", new String[]{null, "Ihosy", "Iakora", "Ivohibe"});

    // Province de Toliara
    distrikaByFaritra.put("Menabe", new String[]{null, "Morondava", "Mahabo", "Manja", "Miandrivazo", "Belo-sur-Tsiribihina"});
    distrikaByFaritra.put("Atsimo-Andrefana", new String[]{null, "Toliara I", "Toliara II", "Ampanihy", "Ankazoabo", "Benenitra", "Beroroha", "Betioky-Atsimo", "Morombe", "Sakaraha"});
    distrikaByFaritra.put("Androy", new String[]{null, "Ambovombe-Androy", "Bekily", "Beloha", "Tsiombe"});
    distrikaByFaritra.put("Anosy", new String[]{null, "Tolagnaro", "Amboasary-Atsimo", "Betroka"});
    // → à compléter pour chaque région...
    // Districts → bureaux de vote et députés
    // int counter = 0;
    bureauDeVoteByDistrika.put(null, new String[0]);
    deputesByDistrika.put(null, new String[0]);
    for (var entry : distrikaByFaritra.entrySet()) {
        String region = entry.getKey();
        for (String distrika : entry.getValue()) {
            // créer 3 BV par distrika
            // counter++;
            if(distrika != null)
            {
                List<String> bvList = new ArrayList<>();
                bvList.add(null);
                for (int i = 1; i <= 3; i++) {
                    String bv = distrika + "_BV" + i;
                    bvList.add(bv);
                }
                List<String> deputeList = new ArrayList<>();
                deputeList.add(null);
                for (int i = 1; i <= 10; i++) {
                    String depute = distrika + "_depute_" + i;
                    deputeList.add(depute);
                }
                String [] bvstring = bvList.toArray(new String[0]);
                String [] deputestring = deputeList.toArray(new String[0]);
                bureauDeVoteByDistrika.put(distrika, bvstring);
                deputesByDistrika.put(distrika, deputestring);
            }
        }
    }
    // System.out.println("Il y a " + counter + " distrika.");
}

    private void updateFaritra() {
        String faritany_selected = (String) dropdown_faritany.getSelectedItem();
        System.out.println("Faritany choisi: " + faritany_selected);
        String[] faritra = faritraByFaritany.getOrDefault(faritany_selected, new String[0]);
        System.out.println("Tous les regions dans " + faritany_selected + ": ");
        for(String a : faritra)
        {
            System.out.println("Regions: " + a);
        }
        dropdown_faritra.setModel(new DefaultComboBoxModel<>(faritra));
        updateDistrika(); 
        System.out.println("Distrika updated!");
    }

    private void updateDistrika() {
        String faritra_selected = (String) dropdown_faritra.getSelectedItem();
        System.out.println("Faritra choisi: " + faritra_selected);
        String[] distrika = distrikaByFaritra.getOrDefault(faritra_selected, new String[0]);
        System.out.println("Tous les districts dans " + faritra_selected + ": ");
        for(String a : distrika)
        {
            System.out.println("Distrika: " + a);
        }
        dropdown_distrika.setModel(new DefaultComboBoxModel<>(distrika));
        updateBV();
    }
    private void updateBV() {
        String distrika_selected = (String) dropdown_distrika.getSelectedItem();
        String[] bv = bureauDeVoteByDistrika.getOrDefault(distrika_selected, new String[0]);
        dropdown_bureauDeVote.setModel(new DefaultComboBoxModel<>(bv));
        updateDepute();
    }
    private void updateDepute() {
        String distrika_selected = (String) dropdown_distrika.getSelectedItem();
        String[] depute = deputesByDistrika.getOrDefault(distrika_selected, new String[0]);
        dropdown_depute.setModel(new DefaultComboBoxModel<>(depute));
    }

    public String getSelectedFaritany()
    {
        return (String) dropdown_faritany.getSelectedItem();
    }
        public String getSelectedFaritra()
    {
        return (String) dropdown_faritra.getSelectedItem();
    }
        public String getSelectedDistrika()
    {
        return (String) dropdown_distrika.getSelectedItem();
    }
        public String getSelectedBv()
    {
        return (String) dropdown_bureauDeVote.getSelectedItem();
    }
        public String getSelectedDepute()
    {
        return (String) dropdown_depute.getSelectedItem();
    }
    public String getNombreVotes() {
            return (String) nombredevotes.getText();
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Formulaire Candidat");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(new FormCandidat());
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}