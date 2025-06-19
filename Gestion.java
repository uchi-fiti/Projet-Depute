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
public Vector <String[]> getDeputesByFaritra(String faritra, String filename)
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
        if(s[1].equals(faritra))
        {
            retour.add(s);
        }
    }
    return retour;
}
public Vector <String[]> getDeputesByFaritany(String faritany, String filename)
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
        if(s[0].equals(faritany))
        {
            retour.add(s);
        }
    }
    return retour;
}
public Vector <String[]> getAllDeputes(String filename)
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
        retour.add(s);
    }
    return retour;
}
public District getDistrict(String nom_district)
{
    for(Faritany f : faritany)
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
    return new District("Skibidi didn't find any district", null);
}
public Vector <Depute> getElus(String district, String filename)
{
    Vector <String[]> deputesByDistricts = getDeputesByDistricts(district, filename);
    Vector <Depute> retour = new Vector<>();
    deputesByDistricts.sort((a, b) -> Integer.compare(Integer.parseInt(a[5]), Integer.parseInt(b[5])));
    District currentDistrict = getDistrict(district);
    for(int i = 0; i < currentDistrict.getnombreElus(); i++)
    {
        Depute temp = new Depute(deputesByDistricts.get(i)[4]);
        temp.setDistrict(currentDistrict);
        retour.add(temp);
    }
    return retour;
}
// public static Map<String, Map<String, Integer>> toHashMap(Vector<String[]> datas) {
//     Map<String, Map<String, Integer>> retour = new HashMap<>();
//     int added = 0;

//     for (String[] s : datas) {
//         String district = s[2];     
//         String depute = s[4];       
//         int value = Integer.parseInt(s[5]); 

//         Map<String, Integer> deputes = retour.computeIfAbsent(district, k -> new HashMap<>());
//         //raha efa misy le value ao am key district de retourneny fotsiny zany value zany
//         //else (raha mbola tsisy le value ao le key district) de mamorona key vaovao avec new hashmap ny valeur
//         deputes.put(depute, deputes.getOrDefault(depute, 0) + value);

//         added++;
//     }
//     System.out.println("Added " + added + " times");
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