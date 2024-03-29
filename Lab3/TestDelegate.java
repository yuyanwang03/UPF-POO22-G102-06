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

            for (String d : del[4].split("[.]")) {tempDays.add(d);}
            for (String h : del[5].split("[.]")) {tempHours.add(Integer.parseInt(h));}

            Availability tempAvailability = new Availability(tempDays, tempHours);
            tempDelegate.setAvailability(tempAvailability);

            // 会在memory做改变吗? 还只是temporal的
            tempDelegate.setHeadOf(tempHeadquarter);
            tempHeadquarter.setHead(tempDelegate);
            delegates.add(tempDelegate);
        }

        // // Code testing. Uncomment all to see
        // System.out.println(Utility.toString(organization.getDelegates()));
        // System.out.println(Utility.toString(organization.getAllMembers()));

        
        // Regular r1 = new Regular("Regular1 name", 6483943, "r1email", headquarters.get(0), delegates.get(6));
        // Regular r2 = new Regular("Regular2 name", 65943, "r2email", headquarters.get(2), delegates.get(6));
        // Regular r3 = new Regular("Regular3 name", 6486543, "r3email", headquarters.get(4), delegates.get(4));
        // Regular r4 = new Regular("Regular4 name", 12943, "r4email", headquarters.get(0), delegates.get(6));
        // Regular r5 = new Regular("Regular5 name", 943943, "r5email", headquarters.get(4), delegates.get(6));
        // Delegate d1 = new Delegate("Delegate1 name", 12344, "d1email", delegates.get(0).getHeadquarter());

        // Image del = delegates.get(0).genDelegateQR(new QRLib());
        // Image reg = delegates.get(6).genRegularQR(new QRLib());

        // delegates.get(3).signUpRegular(r1, new QRLib(), reg);
        // delegates.get(2).signUpRegular(r2, new QRLib(), reg);
        // delegates.get(4).signUpRegular(r3, new QRLib(), reg);
        // delegates.get(1).signUpRegular(r4, new QRLib(), reg);
        // delegates.get(6).signUpRegular(r5, new QRLib(), reg);

        // // It can be observed that Regular3 is not a member of the organization because it was instantiated with delegate #4 but the QR it has is the one generated by delegate #6
        // System.out.println(Utility.toString(organization.getAllMembers()));

        // delegates.get(0).signUpDelegate(d1, new QRLib(), del);
        // // It can be observed that a delegate has been substituted by a new delegate, which is exactly what the program has to do
        // System.out.println(Utility.toString(organization.getDelegates()));

        // System.out.println(Utility.toString(organization.getActions()));
        // System.out.println(Utility.toString(organization.getAllInfoActions()));
        
        // Date date1 = new Date(2022, 11, 15);
        // Date date2 = new Date(2022, 11, 14);
        // Action a1 = new Action("Action1 name", date1, 1);
        // delegates.get(3).proposeAction(a1);

        // delegates.get(4).signUpAction(date1);
        // delegates.get(5).signUpAction(date2);
        // delegates.get(6).signUpAction(date1);

        // // Only one instance of action was created and 2 headquarters have been signed up to that specific action
        // System.out.println(Utility.toString(organization.getActions()));
        // System.out.println(Utility.toString(organization.getAllInfoActions()));

        System.out.println("\nExited the program without errors :)\n");
    }
}
