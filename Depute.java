package data;


public class Depute {
    private String name;
    private Depute second;
    private District district;
    
    public Depute(String name) {
        this.name = name;
        
        this.second = null; 
    }

    public String getName() {
        return name;
    }

    public Depute getSecond() {
        return second;
    }
    public void setSecond(Depute second) {
        this.second = second;
    }
    public District getDistrict() {
        return district;
    }
    public void setDistrict(District district) {
        this.district = district;
    }
    @Override
public String toString() {
    return this.name;
}
}
