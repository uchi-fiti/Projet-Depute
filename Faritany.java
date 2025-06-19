package data;
import java.util.Vector;
public class Faritany {
    private String name;
    private Vector<Faritra> faritra;

    public Faritany(String name) {
        this.name = name;
        this.faritra = new Vector<>();
    }

    public Faritany(String name, Vector<Faritra> faritra) {
        this.name = name;
        this.faritra = faritra;
    }

    public String getName() {
        return name;
    }

    public Vector<Faritra> getFaritra() {
        return faritra;
    }

    public void setFaritra(Vector<Faritra> Faritra) {
        this.faritra = Faritra;
    }
    @Override
public String toString() {
    return this.name;
}
}