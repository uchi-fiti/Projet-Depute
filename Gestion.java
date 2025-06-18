package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

public class Gestion {
    private static Vector <String[]> loadDeputes(String filename) throws Exception
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
public static Vector <String[]> getDeputesByBv(String bv, String filename)
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

public static Vector <String[]> getDeputesByDistricts(String district, String filename)
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
public static Vector <String[]> getDeputesByFaritra(String faritra, String filename)
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
public static Vector <String[]> getDeputesByFaritany(String faritany, String filename)
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
public static Vector <String[]> getAllDeputes(String filename)
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
public static Map<String, Integer> results(Vector <String[]> datas)
{
    Map<String, Integer> retour = new HashMap<>();
    for(String [] s : datas)
    {
        retour.put(s[4], retour.getOrDefault(s[4], 0) + Integer.parseInt(s[5]));
    }
    return retour;
}
}