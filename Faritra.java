package data;
import java.util.Vector;
public class Faritra {
    String name;
    Vector<District> districts;
    public Faritra(String name) {
        this.name = name;
        this.districts = new Vector<>();
    }
    public Faritra(String name, Vector<District> districts) {
        this.name = name;
        this.districts = districts;
    }
    public String getName() {
        return name;
    }
    public Vector<District> getDistricts() {
        return districts;
    }
    public void setDistricts(Vector<District> districts) {
        this.districts = districts;
    }
    @Override
public String toString() {
    return this.name;
}
}
