package data;

import java.util.HashMap;

public class BureauVote {
    String name;
    HashMap<Depute, Integer> resultats;
    public BureauVote(String name) {
        this.name = name;
        this.resultats = new HashMap<>();
    }
    BureauVote(String name, HashMap<Depute, Integer> resultats) {
        this.name = name;
        this.resultats = resultats;
    }
    public String getName() {
        return name;
    }
    public HashMap<Depute, Integer> getResultats() {
        return resultats;
    }
    public void setResultats(HashMap<Depute, Integer> resultats) {
        this.resultats = resultats;
    }
    public void addResultat(Depute depute, int votes) {
        resultats.put(depute, votes);
    }
    public int getVotes(Depute depute) {
        return resultats.getOrDefault(depute, 0);
    }
    @Override
public String toString() {
    return this.name;
}
}
