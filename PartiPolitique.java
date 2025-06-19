package data;
import java.util.Vector;
public class PartiPolitique{
    Vector<Depute> deputes;
    public PartiPolitique(Vector <Depute> d)
    {
        this.deputes = d;
    }
    public Vector<Depute> getDeputes()
    {
        return this.deputes;
    }
}