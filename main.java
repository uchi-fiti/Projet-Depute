package main;
import java.util.Vector;

import affichage.*;
import data.*;

public class main {
    public static void main(String[] args) {
        BureauVote bv1 = new BureauVote("BV101");
        BureauVote bv2 = new BureauVote("BV102");
        BureauVote bv3 = new BureauVote("BV201");
        BureauVote bv4 = new BureauVote("BV202");
        BureauVote bv5 = new BureauVote("BV301");
        BureauVote bv6 = new BureauVote("BV302");
        Vector<BureauVote> gro1 = new Vector<BureauVote>();
        gro1.add(bv1); gro1.add(bv2);

               Vector<BureauVote> gro2 = new Vector<BureauVote>();
        gro2.add(bv3); gro2.add(bv4);


               Vector<BureauVote> gro3 = new Vector<BureauVote>();
        gro3.add(bv5); gro3.add(bv6);

        Depute D1 = new Depute("C1");
        Depute D2 = new Depute("C2");
        Depute D3 = new Depute("C3");
        Depute D4 = new Depute("C4");
        Depute D5 = new Depute("C5");
        Depute D6 = new Depute("C6");

        Vector<Depute> gr1 = new Vector<Depute>();
        gr1.add(D1);
         gr1.add(D2);
          
                  Vector<Depute> gr2 = new Vector<Depute>();
        gr2.add(D3);
         gr2.add(D4);

                 Vector<Depute> gr3 = new Vector<Depute>();
        gr3.add(D5);
         gr3.add(D6);

        District DI1 = new District(gr1, gro1, "Ampitatafika", 1);
        District DI2 = new District(gr2, gro2, "Ambohimena", 2);
        District DI3 = new District(gr3, gro3, "Tanambao Verrerie", 2);

        Vector<District> add1 = new Vector<District>();
        add1.add(DI1);

                Vector<District> add2 = new Vector<District>();
        add2.add(DI2);

                Vector<District> add3 = new Vector<District>();
        add3.add(DI3);

        Faritra FA1 = new Faritra("Antananarivo Antsimodrano", add1);
        Faritra FA2 = new Faritra("Antsirabe", add2);
        Faritra FA3 = new Faritra("Toamasina", add3);

        Vector<Faritra> addf1 = new Vector<Faritra>();
        addf1.add(FA1);

                Vector<Faritra> addf2 = new Vector<Faritra>();
        addf2.add(FA2);

                Vector<Faritra> addf3 = new Vector<Faritra>();
        addf3.add(FA3);

        Faritany FART1 = new Faritany("Analamanga",addf1);
        Faritany FART2 = new Faritany("Vakinankaratra",addf2);
        Faritany FART3 = new Faritany("Antsinanana",addf3);

                Vector<Faritany> addf = new Vector<Faritany>();
        addf.add(FART1);
        addf.add(FART2);
        addf.add(FART3);
        FormCandidat form1 = new FormCandidat(addf);
        FormResult form2 = new FormResult(addf);
    }
}
