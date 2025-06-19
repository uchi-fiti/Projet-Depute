package data;
import java.util.Vector;

public class District{
    String name;
    int nombreElus;
    Vector<Depute> deputes;
    Vector<PartiPolitique> pps;
    Vector<BureauVote> bureaux;
    
    public District(String name, int nombreElus) {
        this.name = name;
        this.nombreElus = nombreElus;
        this.bureaux = new Vector<>();
        this.deputes = new Vector<>();
    }
        public District(Vector<Depute> candidats,String name, int nombreElus) {
        this.deputes = candidats;
        this.name = name;
        this.nombreElus = nombreElus;
        this.bureaux = new Vector<>();
        this.deputes = new Vector<>();
    }
        public District(Vector<Depute> candidats,Vector<BureauVote> Bureaux,String name, int nombreElus) {
        this.deputes = candidats;
        this.name = name;
        this.bureaux = Bureaux;
        this.nombreElus = nombreElus;
    }
    public District(String name, Vector<Depute> candidats)
    {
        this.name = name;
        this.deputes = candidats;
    }
    
    public String getName() {
        return name;
    }
    public Vector<Depute> getDeputes() {
        return deputes;
    }
        public int getnombreElus() {
        return nombreElus;
    }
    public void addDepute(Depute depute) {
        deputes.add(depute);
    }
    public void setDeputes(Vector<Depute> deputes) {
        this.deputes = deputes;
    }
    public Vector<BureauVote> getBureaux() {
        return bureaux;
    }
    public void setBureaux(Vector<BureauVote> bureaux) {
        this.bureaux = bureaux;
    }
    public Vector<PartiPolitique> getPP()
    {
        return pps;
    }
    @Override
public String toString() {
    return this.name;
}
}