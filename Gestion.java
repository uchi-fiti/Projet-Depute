package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class Gestion {
    public Vector <Faritany> faritany;
    public Gestion(Vector <Faritany> f)
    {
        this.faritany = f;
    }
    private Vector <String[]> loadDeputes(String filename) throws Exception
    {
        Vector <String[]> deputesInFile = new Vector<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // line = line.replace(";", "");
                String[] pieceData = line.split(";;");
                deputesInFile.add(pieceData);
            }
        }
        catch (Exception e) {
            throw new RuntimeException();
        }
        return deputesInFile;
    }
//     Vector<String[]> getDeputes(String faritany, String faritra, String distrika, String bv) {
//     Vector<String[]> retour = new Vector<>();

//     if (bv == null) {
//         retour.add(deputesByDistrika.getOrDefault(bv, new String[0]));
//         return retour;
//     }

//     if (distrika == null) {
//         for (String[] deputes : deputesByDistrika.values()) {
//             retour.add(deputes);
//         }
//         return retour;
//     }

//     if (faritra == null) {
//         // Collect all deputes for all faritra
//         for (String[] deputes : deputesByFaritra.values()) {
//             retour.add(deputes);
//         }
//         return retour;
//     }

//     if (faritany == null) {
//         // Collect all deputes for all faritany
//         for (String[] deputes : deputesByFaritany.values()) {
//             retour.add(deputes);
//         }
//         return retour;
//     }

//     // Default case: retrieve deputes for the specific distrika
//     String[] deputes = deputesByDistrika.getOrDefault(distrika, new String[0]);
//     retour.add(deputes);
//     return retour;
// }
public Vector <String[]> getDeputesByBv(String bv, String filename)
{
    Vector <String[]> retour = new Vector<>();
    Vector <String[]> loaded;
    try {
        loaded = loadDeputes(filename);
    }
    catch (Exception e)
    {
        throw new RuntimeException();
    }
    for(String [] s : loaded)
    {
        if(s[3].equals(bv))
        {
            retour.add(s);
        }
    }
    return retour;
}

public Vector <String[]> getDeputesByDistricts(String district, String filename)
{
    Vector <String[]> retour = new Vector<>();
    Vector <String[]> loaded;
    try {
        loaded = loadDeputes(filename);
    }
    catch (Exception e)
    {
        throw new RuntimeException();
    }
    for(String [] s : loaded)
    {
        if(s[2].equals(district))
        {
            retour.add(s);
        }
    }
    return retour;
}
public District getDistrict(String nom_district)
{
    for(Faritany f : faritany)
    {
        if(f != null)
        {
            for(Faritra ff : f.getFaritra())
            {
                for(District d : ff.getDistricts())
                {
                    if(d.getName().equals(nom_district))
                    {
                        return d;
                    }
                }
            }
        }
    }
    return new District("Skibidi didn't find any district", null);
}
public Vector <Depute> getElus(String district, String filename)
{
    Vector <String[]> deputesByDistricts = getDeputesByDistricts(district, filename);
    Vector <Depute> retour = new Vector<>();
    if(deputesByDistricts.isEmpty())
    {
        return retour;
    }
    Depute elu;
    Depute elu2;
    deputesByDistricts.sort((a, b) -> Integer.compare(Integer.parseInt(b[5]), Integer.parseInt(a[5])));
    // Map <String, Map <String, Integer>> deputes = toHashMap(deputesByDistricts);
    District currentDistrict = getDistrict(district);
    // for(int i = 0; i < currentDistrict.getnombreElus(); i++)
    // {
    //     Depute temp = new Depute(deputesByDistricts.get(i)[4]);
    //     temp.setDistrict(currentDistrict);
    //     retour.add(temp);
    // }
    Vector <Depute> sorted = toVectorDepute(deputesByDistricts);
    retour.add(sorted.get(0));
    if(currentDistrict.getnombreElus() == 2)
    {
        elu = sorted.get(0);
        elu2 = sorted.get(1);
        Depute second = findSecond(elu, sorted);
        if(nombreDeVotes(elu, deputesByDistricts) > 2*nombreDeVotes(elu2, deputesByDistricts) && !second.equals(elu2))
        {
            retour.add(second);
        }
        else
        {
            retour.add(elu2);
        }
    }
    return retour;
}
public int nombreDeVotes(Depute d, Vector <String[]> datas)
{
    int nbvotes = 0;
    int retour = 0;
    for(String [] s : datas)
    {
        String district = s[2];
        String nomD = s[4];
        nbvotes =(int) Integer.parseInt(s[5]);
        if(d.getName().equals(nomD) && d.getDistrict().getName().equals(district))
        {
            retour += nbvotes;
        }
    }
    return retour;
}
public Depute findSecond(Depute d, Vector <Depute> datas)
{
    Vector <Depute> ds = deputesMmCritere(d, datas);
    return ds.get(1);
}
public Vector <Depute> deputesMmCritere(Depute d, Vector <Depute> deputes)
{
    Vector <Depute> retour = new Vector<>();
    for(Depute dd : deputes)
    {
        if(partiPolitique(dd).equals(partiPolitique(d)) && district(dd).equals(district(d)))
        {
            retour.add(dd);
        }
    }
    return retour;
}
public District district(Depute d)
{
    for(Faritany f : faritany)
    {
        for(Faritra ff : f.getFaritra())
        {
            for(District dd : ff.getDistricts())
            {
                if(dd.equals(d))
                {
                    return dd;
                }
            }
        }
    }
    return null;
}
public PartiPolitique partiPolitique(Depute d)
{
    for(Faritany f : faritany)
    {
        if(f != null)
        {
            for(Faritra ff : f.getFaritra())
            {
                for(District dd : ff.getDistricts())
                {
                    for(PartiPolitique pp : dd.getPP())
                    {
                        if(pp.getDeputes().contains(d))
                        {
                            return pp;
                        }
                    }
                }
            }
        }
    }
    return null;
}
public Vector <Depute> toVectorDepute(Vector <String[]> datas)
{
    Vector <Depute> retour = new Vector<>();
    for(String [] s : datas)
    {
        String nom = s[4];
        String district = s[2];
        Depute temp = new Depute(nom);
        temp.setDistrict(getDistrict(district));
        retour.add(temp);
    }
    return retour;
}
public District getDistrictOfBv(BureauVote v)
{
        for(Faritany f : faritany)
    {
        for(Faritra ff : f.getFaritra())
        {
            for(District dd : ff.getDistricts())
            {
                if(dd.getBureaux().contains(v))
                {
                    return dd;
                }
            }
        }
    }
    return null;
}
public BureauVote getBv(String bv)
{
    for(Faritany f : faritany)
    {
        for(Faritra ff : f.getFaritra())
        {
            for(District d : ff.getDistricts())
            {
                for(BureauVote v : d.getBureaux())
                {
                    if(v.getName().equals(bv))
                    {
                        return v;
                    }
                }
            }
        }
    }
    return null;
}
public Vector <Depute> getElusbyBv(String bv, String filename)
{
    Vector <String[]> deputesByBv = getDeputesByBv(bv, filename);
    Vector <Depute> retour = new Vector<>();
    Depute elu;
    Depute elu2;
    deputesByBv.sort((a, b) -> Integer.compare(Integer.parseInt(b[5]), Integer.parseInt(a[5])));
    // Map <String, Map <String, Integer>> deputes = toHashMap(deputesByDistricts);
    BureauVote currentBureauVote = getBv(bv);
    District currentDistrict = getDistrictOfBv(currentBureauVote);
    // for(int i = 0; i < currentDistrict.getnombreElus(); i++)
    // {
    //     Depute temp = new Depute(deputesByDistricts.get(i)[4]);
    //     temp.setDistrict(currentDistrict);
    //     retour.add(temp);
    // }
    Vector <Depute> sorted = toVectorDepute(deputesByBv);
    retour.add(sorted.get(0));
    if(currentDistrict.getnombreElus() == 2)
    {
        elu = sorted.get(0);
        elu2 = sorted.get(1);
        Depute second = findSecond(elu, sorted);
        if(nombreDeVotes(elu, deputesByBv) > 2*nombreDeVotes(elu2, deputesByBv) && !second.equals(elu2))
        {
            retour.add(second);
        }
        else
        {
            retour.add(elu2);
        }
    }
    return retour;
}
// public static Map<String, Map<String, Integer>> toHashMap(Vector<String[]> datas) {
//     Map<String, Map<String, Integer>> retour = new HashMap<>();

//     for (String[] s : datas) {
//         String district = s[2];     
//         String depute = s[4];       
//         int value = Integer.parseInt(s[5]); 

//         Map<String, Integer> deputes = retour.computeIfAbsent(district, k -> new HashMap<>());
//         //raha efa misy le value ao am key district de retourneny fotsiny zany value zany
//         //else (raha mbola tsisy le value ao le key district) de mamorona key vaovao avec new hashmap ny valeur
//         deputes.put(depute, deputes.getOrDefault(depute, 0) + value);
//     }
//     return retour;
// }
// public static Map<String, Map<String, Integer>> getElus(Map<String, Map<String, Integer>> votesParDistrict) {
//     Map<String, Map<String, Integer>> elus = new HashMap<>();

//     for (Map.Entry<String, Map<String, Integer>> entry : votesParDistrict.entrySet()) {
//         String districtFull = entry.getKey();
//         String[] districtParts = districtFull.split(":");
//         String districtName = districtParts[0];
//         int nombre_elus = Integer.parseInt(districtParts[1]);

//         Map<String, Integer> deputes = entry.getValue();

//         List<Map.Entry<String, Integer>> sortedDeputes = new ArrayList<>(deputes.entrySet());
//         sortedDeputes.sort((a, b) -> b.getValue().compareTo(a.getValue()));

//         Map<String, Integer> eluMap = new HashMap<>();
//         for (int i = 0; i < Math.min(nombre_elus, sortedDeputes.size()); i++) {
//             Map.Entry<String, Integer> eluEntry = sortedDeputes.get(i);
//             eluMap.put(eluEntry.getKey(), eluEntry.getValue());
//         }

//         elus.put(districtName, eluMap);
//     }

//     return elus;
// }
}