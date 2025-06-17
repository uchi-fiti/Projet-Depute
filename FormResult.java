import java.awt.GridLayout;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class FormResult extends JPanel {
    private final JComboBox<String> dropdown_faritany;
    private final JComboBox<String> dropdown_faritra;
    private final JComboBox<String> dropdown_distrika;
    private final JComboBox<String> dropdown_bureauDeVote;

    private final Map<String, String[]> faritraByFaritany = new HashMap<>();
    private final Map<String, String[]> distrikaByFaritra = new HashMap<>();
    private final Map<String, String[]> bureauDeVoteByDistrika = new HashMap<>();
    private final JButton submitButton;
    

    public FormResult() {
        initializeData();

        dropdown_faritany = new JComboBox<>(faritraByFaritany.keySet().toArray(new String[0]));
        dropdown_faritra = new JComboBox<>();
        dropdown_distrika = new JComboBox<>();
        dropdown_bureauDeVote = new JComboBox<>();

        dropdown_faritany.addActionListener(e -> updateFaritra());
        dropdown_faritra.addActionListener(e -> updateDistrika());
        dropdown_distrika.addActionListener(e -> updateBV());

        submitButton = new JButton("Submit");
        // submitButton.addActionListener(e -> handleSubmit("votes.txt"));

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

        updateFaritra();
    }
    // private void handleSubmit(String filename) {
    //     String faritany = getSelectedFaritany();
    //     String faritra = getSelectedFaritra();
    //     String distrika = getSelectedDistrika();
    //     String bv = getSelectedBv();

    //     if (faritany == null || faritra == null || distrika == null || bv == null) {
    //         JOptionPane.showMessageDialog(this, "Please make a selection in all fields.", "Incomplete Selection", JOptionPane.WARNING_MESSAGE);
    //         return;
    //     }

    //     String dataLine = String.join(";;", faritany, faritra, distrika, bv, depute);

    //     try (FileWriter fw = new FileWriter(filename, true);
    //          PrintWriter pw = new PrintWriter(fw)) {
    //         pw.println(dataLine);
    //         JOptionPane.showMessageDialog(this, "Information saved successfully to " + filename);
    //     } catch (IOException e) {
    //         JOptionPane.showMessageDialog(this, "Error writing to file: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
    //         e.printStackTrace();
    //     }
    // }
private void initializeData() {
    // 6 provinces
    String[] provinces = new String[]
        {"Antananarivo", "Antsiranana", "Mahajanga",
        "Toamasina", "Fianarantsoa", "Toliara"
    };

    // Provinces → Régions
    faritraByFaritany.put("Antananarivo", new String[]{"Analamanga", "Vakinankaratra", "Itasy", "Bongolava"});
    faritraByFaritany.put("Antsiranana", new String[]{"Diana", "Sava"});
    faritraByFaritany.put("Mahajanga", new String[]{"Boeny", "Betsiboka", "Melaky", "Sofia"});
    faritraByFaritany.put("Toamasina", new String[]{"Alaotra-Mangoro", "Atsinanana", "Analanjirofo"});
    faritraByFaritany.put("Fianarantsoa", new String[]{"Amoron'i Mania", "Haute Matsiatra", "Vatovavy", "Atsimo-Atsinanana", "Ihorombe", "Fitovinany"});
    faritraByFaritany.put("Toliara", new String[]{"Menabe", "Atsimo-Andrefana", "Androy", "Anosy"});

    // Régions → Districts (extraits pour Analamanga, Diana ; répliquer pour toutes)
    distrikaByFaritra.put("Analamanga", new String[]{"Antananarivo-Renivohitra", "Antananarivo-Avaradrano", "Antananarivo-Atsimondrano", "Ambohidratrimo", "Andramasina", "Anjozorobe", "Ankazobe", "Manjakandriana"});
    distrikaByFaritra.put("Vakinankaratra", new String[]{"Antsirabe I", "Antsirabe II", "Ambatolampy", "Antanifotsy", "Betafo", "Faratsiho", "Mandoto"});
    distrikaByFaritra.put("Itasy", new String[]{"Arivonimamo", "Miarinarivo", "Soavinandriana"});
    distrikaByFaritra.put("Bongolava", new String[]{"Tsiroanomandidy", "Fenoarivobe"});

    // Province d'Antsiranana
    distrikaByFaritra.put("Diana", new String[]{"Antsiranana I", "Antsiranana II", "Ambanja", "Ambilobe", "Nosy-Be"});
    distrikaByFaritra.put("Sava", new String[]{"Sambava", "Antalaha", "Vohemar", "Andapa"});

    // Province de Mahajanga
    distrikaByFaritra.put("Boeny", new String[]{"Mahajanga I", "Mahajanga II", "Marovoay", "Ambato-Boeni", "Mitsinjo", "Soalala"});
    distrikaByFaritra.put("Betsiboka", new String[]{"Maevatanana", "Tsaratanana", "Kandreho"});
    distrikaByFaritra.put("Melaky", new String[]{"Maintirano", "Besalampy", "Antsalova", "Morafenobe", "Ambatomainty"});
    distrikaByFaritra.put("Sofia", new String[]{"Antsohihy", "Port-Bergé", "Befandriana-Nord", "Mandritsara", "Analalava", "Mampikony", "Bealanana"});

    // Province de Toamasina
    distrikaByFaritra.put("Alaotra-Mangoro", new String[]{"Ambatondrazaka", "Amparafaravola", "Andilamena", "Anosibe An'ala", "Moramanga"});
    distrikaByFaritra.put("Atsinanana", new String[]{"Toamasina I", "Toamasina II", "Brickaville", "Vatomandry", "Mahanoro", "Marolambo", "Antanambao Manampotsy"});
    distrikaByFaritra.put("Analanjirofo", new String[]{"Fenoarivo Atsinanana", "Soanierana Ivongo", "Sainte-Marie", "Mananara Nord", "Maroantsetra", "Vavatenina"});

    // Province de Fianarantsoa
    distrikaByFaritra.put("Amoron'i Mania", new String[]{"Ambositra", "Ambatofinandrahana", "Manandriana", "Fandriana"});
    distrikaByFaritra.put("Haute Matsiatra", new String[]{"Fianarantsoa I", "Ambalavao", "Ambohimahasoa", "Ikalamavony", "Isandra", "Lalangina", "Vohibato"});
    distrikaByFaritra.put("Vatovavy", new String[]{"Mananjary", "Nosy Varika", "Ifanadiana"});
    distrikaByFaritra.put("Fitovinany", new String[]{"Manakara", "Vohipeno", "Ikongo"});
    distrikaByFaritra.put("Atsimo-Atsinanana", new String[]{"Farafangana", "Vangaindrano", "Vondrozo", "Befotaka", "Midongy-Atsimo"});
    distrikaByFaritra.put("Ihorombe", new String[]{"Ihosy", "Iakora", "Ivohibe"});

    // Province de Toliara
    distrikaByFaritra.put("Menabe", new String[]{"Morondava", "Mahabo", "Manja", "Miandrivazo", "Belo-sur-Tsiribihina"});
    distrikaByFaritra.put("Atsimo-Andrefana", new String[]{"Toliara I", "Toliara II", "Ampanihy", "Ankazoabo", "Benenitra", "Beroroha", "Betioky-Atsimo", "Morombe", "Sakaraha"});
    distrikaByFaritra.put("Androy", new String[]{"Ambovombe-Androy", "Bekily", "Beloha", "Tsiombe"});
    distrikaByFaritra.put("Anosy", new String[]{"Tolagnaro", "Amboasary-Atsimo", "Betroka"});
    // → à compléter pour chaque région...

    // Districts → bureaux de vote et députés
    for (var entry : distrikaByFaritra.entrySet()) {
        String region = entry.getKey();
        for (String distrika : entry.getValue()) {
            // créer 3 BV par distrika
            List<String> bvList = new ArrayList<>();
            for (int i = 1; i <= 3; i++) {
                String bv = distrika + "_BV" + i;
                bvList.add(bv);
            }
            String [] bvstring = bvList.toArray(new String[0]);
            bureauDeVoteByDistrika.put(distrika, bvstring);
        }
    }
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
