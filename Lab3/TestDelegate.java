import java.util.*;

public class TestDelegate {
    public static void main(String[] args){
        LinkedList<Region> regions = new LinkedList<Region>();
        LinkedList<City> cities = new LinkedList<City>();

        LinkedList<String[]> xmlRegions = Utility.readXML("region");
        for (String[] reg: xmlRegions){ 
            Region temp = new Region(reg[0]);
            System.out.println(reg[0]);
            temp.setCities(cities);
        }
    }
}
