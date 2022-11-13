import java.util.*;

public class TestDelegate {
    public static void main(String[] args){
        LinkedList<Region> regions = new LinkedList<Region>();
        LinkedList<City> cities = new LinkedList<City>();
        Organization organization = new Organization("Lab3 Organization");
        LinkedList<Headquarter> headquarters = new LinkedList<Headquarter>();
        LinkedList<Delegate> delegates = new LinkedList<Delegate>();
        
        LinkedList<String[]> xmlRegions = Utility.readXML("region");
        for (String[] reg: xmlRegions){ 
            LinkedList<City> regionCities = new LinkedList<City>();
            Region tempReg = new Region(reg[0]);
            String[] tempCities = Arrays.copyOfRange(reg, 1, reg.length/2+1);
            String[] tempPopulation = Arrays.copyOfRange(reg, reg.length/2+1, reg.length);
            // System.out.println(tempCities[tempCities.length-1]);
            // System.out.println(tempPopulation[tempPopulation.length-1]);
            for (int i =0; i<tempCities.length; i++){
                City tempCity = new City(tempCities[i], Integer.parseInt(tempPopulation[i]));
                cities.add(tempCity);
                regionCities.add(tempCity);
            }

            tempReg.setCities(regionCities);
            regions.add(tempReg);
        }

        LinkedList<String[]> xmlHeadquarter = Utility.readXML("headquarter");
        for (String[] headq: xmlHeadquarter){ 
            Headquarter tempHeadquarter = new Headquarter(headq[0], headq[1], organization);
            LinkedList<City> headqCities = new LinkedList<City>();
            String[] tempCities = Arrays.copyOfRange(headq, 2, headq.length);

            for (int i=0; i<tempCities.length; i++){
                City tempCity = Utility.getObject(tempCities[i], cities);
                headqCities.add(tempCity);
            }
            
            tempHeadquarter.setCities(headqCities);
            headquarters.add(tempHeadquarter);
            organization.addHeadquarter(tempHeadquarter);
        }

        LinkedList<String[]> xmlDelelegate = Utility.readXML("head");
        for (String[] del: xmlDelelegate){ 
            // 记，用ulitities 的getobject时，得写tostring 
            Headquarter tempHeadquarter = Utility.getObject(del[3], headquarters);
            Delegate tempDelegate = new Delegate(del[0], Integer.parseInt(del[1]), del[2], tempHeadquarter);
            LinkedList<String> tempDays = new LinkedList<String>();
            LinkedList<Integer> tempHours = new LinkedList<Integer>();
            // String[] da = del[4].split("[.]");
            // String[] ho = del[5].split(".");
            for (String d : del[4].split("[.]")){
                // System.out.println(d);
                tempDays.add(d);
            }
            for (String h : del[5].split("[.]")){
                // System.out.println(h);
                tempHours.add(Integer.parseInt(h));
            }

            Availability tempAvailability = new Availability(tempDays, tempHours);
            tempDelegate.setAvailability(tempAvailability);

            // 会在memory做改变吗? 还只是temporal的
            tempDelegate.setHeadOf(tempHeadquarter);
            tempHeadquarter.setHead(tempDelegate);
            delegates.add(tempDelegate);
        }

        
    }
}
