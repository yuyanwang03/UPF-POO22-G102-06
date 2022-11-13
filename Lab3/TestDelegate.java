import java.util.*;

public class TestDelegate {
    public static void main(String[] args){
        // Read xml files
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
            Headquarter tempHeadquarter = Utility.getObject(del[3], headquarters);
            Delegate tempDelegate = new Delegate(del[0], Integer.parseInt(del[1]), del[2], tempHeadquarter);
            LinkedList<String> tempDays = new LinkedList<String>();
            LinkedList<Integer> tempHours = new LinkedList<Integer>();

            for (String d : del[4].split("[.]")){tempDays.add(d);}
            for (String h : del[5].split("[.]")){tempHours.add(Integer.parseInt(h));}

            Availability tempAvailability = new Availability(tempDays, tempHours);
            tempDelegate.setAvailability(tempAvailability);

            // 会在memory做改变吗? 还只是temporal的
            tempDelegate.setHeadOf(tempHeadquarter);
            tempHeadquarter.setHead(tempDelegate);
            delegates.add(tempDelegate);
        }

        // Code testing
        Image del = delegates.get(0).genDelegateQR(new QRLib());
        Image reg = delegates.get(6).genRegularQR(new QRLib());

    }
}
