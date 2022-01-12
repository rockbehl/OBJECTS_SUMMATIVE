import java.util.*;

public class Root {
    static String[] CITIES = {"SAN FRANCISCO", "BOSTON", "NEW YORK CITY", "WASHINGTON D.C", "LOS ANGLES", "PHILADELPHIA", "MIAMI"};

    public static void main(String[] args) {
        System.out.println("press ENTER to start");
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        Musician[] Ensemble = new Musician[10];
        boolean mainMenue = true;

        Ensemble[0] = new Musician("Sahil", "Xylophone");
        Ensemble[1] = new Musician("Aaryah", "Didgeridoo");
        Ensemble[2] = new Musician("Mahir", "Tarpa");
        Ensemble[3] = new Musician("Ryan", "Trumpet");
        Ensemble[4] = new Musician("Shanaya", "Saxophone");

        for (int k = 0; k < 5; k++) {
            for (int i = 0; i < Ensemble.length; i++) {
                if (Ensemble[i] == null) {
                    continue;
                }
                Ensemble[i].perform(CITIES[rand.nextInt(6)]);
            }
        }

        int userSel = 0;

        while (mainMenue) {
                sc.nextLine();
                System.out.println("-=-=-=-=-=-=-=-");
                System.out.println(" | MAIN MENU | ");
                System.out.println("-=-=-=-=-=-=-=-");
                System.out.println("1. List Orchestra");
                System.out.println("2. Add Performer");
                System.out.println("3. Perform");
                System.out.println("4. Find Best Performance");
                System.out.println("5. Sort Performers");
                System.out.println("6. List By Venue");
                System.out.println("7. Find Best Venue***");
                System.out.println("8. Exit");
                System.out.print("||     ");
                try {
                    userSel = sc.nextInt();

                    System.out.println();

                    switch (userSel) {
                        case 1 -> listOrc(Ensemble);
                        case 2 -> addElm(Ensemble);
                        case 3 -> startPerform(Ensemble);
                        case 4 -> findBest(Ensemble);
                        case 5 -> findBestRanks(Ensemble);
                        case 6 -> listByVenue(Ensemble);
                        //case 7 -> findBestVenue(Ensemble);
                        case 8 -> mainMenue = false;
                        default -> System.out.println("Error, try again");
                    }
                } catch (Exception e) {
                    System.out.println("Try again!");
                }




        }

    }

    public static void listOrc(Musician[] arr){
        System.out.println();
        System.out.println();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                continue;
            }
            System.out.print((i+1)+". ");
            arr[i].getMusicianDeets();
        }
        System.out.println();
        System.out.println();
    }

    public static void addElm(Musician[] arr){
        Scanner sc = new Scanner(System.in);
        int n =0;
        String userName;
        String userInstrument;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != null){
                n++;
            }
        }

        System.out.println();
        System.out.println();
        System.out.print("Enter name of new Musician: ");
        userName = sc.nextLine();
        System.out.print("Enter name of new Musician's Instrument: ");
        userInstrument = sc.nextLine();

        arr[n] = new Musician(userName, userInstrument);
    }

    public static void startPerform(Musician[] arr){
        listOrc(arr);

        Scanner sc = new Scanner(System.in);
        boolean valid = false;
        String userInp = "";
        int userInpLocal = 0;

        while (!valid) {

            System.out.print("Performance Location: ");
            userInp = sc.nextLine();
            System.out.print("Performer number: ");
            userInpLocal = sc.nextInt();

            if (arr[userInpLocal-1] != null) {
                valid = true;
            }
        }

        arr[userInpLocal-1].perform(userInp);
        System.out.println(arr[userInpLocal-1].getNAME()+", "+arr[userInpLocal-1].getLastLOCATION()+" ("+arr[userInpLocal-1].getLastRATING()+") ");
        listOrc(arr);

    }

    public static void findBest(Musician[] arr){
        for (int i = 0; i < arr.length-1; i++)
        {
            if (arr[i] == null){
                continue;
            }
            int min_idx = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] == null){
                    continue;
                }
                if (arr[j].getBestRatings() > arr[min_idx].getBestRatings()) {
                    min_idx = j;
                }
            }
            Musician temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Best Performer: "+arr[0].getNAME()+", "+arr[0].getINSTRUMENT()+" ("+arr[0].getBestRatings()+", "+arr[0].getLocationsBest()+") ");
    }

    public static void findBestRanks(Musician[] arr){
        for (int i = 0; i < arr.length-1; i++)
        {
            if (arr[i] == null){
                continue;
            }
            int min_idx = i;
            for (int j = i+1; j < arr.length; j++) {
                if (arr[j] == null){
                    continue;
                }
                if (arr[j].getAve() > arr[min_idx].getAve()) {
                    min_idx = j;
                }
            }
            Musician temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Best Performers: ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                continue;
            }
            System.out.print(i+1+"/ ");
            arr[i].getMusicianDeets();
        }
    }

    public static void listByVenue(Musician[] arr){
        Scanner sc = new Scanner(System.in);
        int n =0;

        System.out.print("Venue: ");
        String userInp = sc.nextLine();
        userInp = userInp.toUpperCase(Locale.ROOT);

        System.out.println("Performers are: ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == null){
                continue;
            }
            for (int j = 0; j < arr[i].getLocations().length; j++) {
                if (arr[i].getLocationsElement(j) == null){
                    continue;
                }
                if (arr[i].getLocationsElement(j).equals(userInp)){
                    System.out.println("/ "+arr[i].getNAME()+" "+arr[i].getRatingsElements(j));
                    n++;
                }
            }
        }
        if (n == 0){
            System.out.println("No one has performed here!");
        }
    }

}
