package rockets.mining;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.*;
import static org.apache.commons.lang3.Validate.notBlank;
import java.math.BigDecimal;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;


import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;

import java.util.Iterator;

public class RocketMiner {


    private DAO dao;

    public RocketMiner(DAO dao) {
        this.dao = dao;
    }

    /**
     * TODO: to be implemented & tested!
     * Returns the top-k most active rockets, as measured by number of completed launches.
     *
     * @param k the number of rockets to be returned.
     * @return the list of k most active rockets.
    */
    public List<Rocket> mostLaunchedRockets(int k) {
        Collection<Launch> launchesList = dao.loadAll(Launch.class);
        List<Rocket> r = new ArrayList<>();

        for(Launch l: launchesList) {
            if(l.getLaunchDate() != null) {
                r.add(l.getLaunchVehicle());
            }
        }
        Map<Rocket, Long> hashmap = r.stream().collect(Collectors.groupingBy(Rocket -> Rocket, Collectors.counting()));
        Map<Rocket, Long> sorted = hashmap .entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .collect( Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        List<Rocket> rockets = new ArrayList<>();
        for(Map.Entry<Rocket,Long> e: sorted.entrySet()){
            rockets.add(e.getKey());
        }
        return rockets.stream().limit(k).collect(Collectors.toList());
    }


    /*
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most reliable launch service providers as measured
     * by percentage of successful launches.
     *
     * @param k the number of launch service providers to be returned.
     * @return the list of k most reliable ones.
     */

    public List<LaunchServiceProvider> mostReliableLaunchServiceProviders(int k) {
        Collection<Launch> launches1 = dao.loadAll(Launch.class);
        Collection<LaunchServiceProvider> lsp = dao.loadAll(LaunchServiceProvider.class);
        Map<LaunchServiceProvider, Double> hashmap = new HashMap<>();
        List<LaunchServiceProvider> listOfLsp = new ArrayList<>();

        for(LaunchServiceProvider l: lsp) {
            hashmap.put(l,0.00);
        }

        for(Map.Entry<LaunchServiceProvider, Double> entry: hashmap.entrySet()) {

            int successfull = 0;
            int failed = 0;
            double perSucc = 0.0;

            for(Launch l: launches1) {

                if(l.getLaunchServiceProvider().equals(entry.getKey())) {

                    if(l.getLaunchOutcome().equals(Launch.LaunchOutcome.SUCCESSFUL))
                        successfull++;
                    else
                        failed++;
                }
            }
            int total = successfull+failed;
            if(total!=0)
                perSucc = ((double)successfull/total)*100;

            entry.setValue(perSucc);
        }
        Map<LaunchServiceProvider, Double> sorted = hashmap .entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .collect( Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));

        for(Map.Entry<LaunchServiceProvider, Double> entry: sorted.entrySet()) {
            listOfLsp.add(entry.getKey());
        }
        return listOfLsp.stream().limit(k).collect(Collectors.toList());
    }
    /**
     * <p>
     * Returns the top-k most recent launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most recent launches.
     */
    public List<Launch> mostRecentLaunches(int k) {
        //logger.info(String.format("find most recent %d %s",k,launches));
        Collection<Launch> launchesList = dao.loadAll(Launch.class);
        Comparator<Launch> launchDateComparator = (a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate());
        return launchesList.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the dominant country who has the most launched rockets in an orbit.
     *
     * @param orbit the orbit
     * @return the country who sends the most payload to the orbit
     */
    public String dominantCountry(String orbit) {
        notBlank(orbit,"orbit should not be null or empty");
        Collection<Launch> launchesList=dao.loadAll(Launch.class);
        HashMap<String,Integer> dictionary=new HashMap<>();
        Iterator<Launch> launchIterator = launchesList.iterator();
        while(launchIterator.hasNext())
        {
            Launch launch = launchIterator.next();
            String launchOrbit = launch.getOrbit().trim();
            String country = launch.getLaunchVehicle().getCountry().trim().toLowerCase();
            if(launchOrbit.equalsIgnoreCase(orbit.trim()))
            {
                if(dictionary.containsKey(country))
                {
                    int numberOfRockets = dictionary.get(country);
                    numberOfRockets += 1;
                    dictionary.replace(country,numberOfRockets);
                }
                else
                    dictionary.put(country,1);
            }

        }

        //Finding maximum rockets sent by a country
        String maxCountry="";
        int maxRockets=0;
        Iterator<String> dicIterator = dictionary.keySet().iterator();
        while(dicIterator.hasNext())
        {
            String country = dicIterator.next();
            int rockets = dictionary.get(country);
            if( rockets > maxRockets)
            {
                maxCountry = country;
                maxRockets = rockets;
            }
        }
        return maxCountry;
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most expensive launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most expensive launches.
     */
    public List<Launch> mostExpensiveLaunches(int k) {
        Collection<Launch> launchesList = dao.loadAll(Launch.class);
        Comparator<Launch> launchPriceComparator = (a, b) -> -a.getPrice().compareTo(b.getPrice());
        return launchesList.stream().sorted(launchPriceComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns a list of launch service provider that has the top-k highest
     * sales revenue in a year.
     *
     * @param k the number of launch service provider.
     * @param year the year in request
     * @return the list of k launch service providers who has the highest sales revenue.
     */
    public List<LaunchServiceProvider> highestRevenueLaunchServiceProviders(int k, int year) {

        if(k<=0)
            throw new IllegalArgumentException("k should be greater than 0");

        Collection<Launch> launchesList=dao.loadAll(Launch.class);
        Collection<LspRevenue> revenueCollection = Lists.newArrayList();
        Iterator<Launch> launchIterator = launchesList.iterator();
        while(launchIterator.hasNext())
        {
            Launch launch = launchIterator.next();
            if(launch.getLaunchDate().getYear()==year)
            {
                LaunchServiceProvider lsp = launch.getLaunchServiceProvider();
                BigDecimal price = launch.getPrice();
                ArrayList<LspRevenue> revenueArrayList=lsp.getRevenue();

                if(revenueArrayList!=null)
                {
                    boolean flag = false;
                    for(LspRevenue revenue: revenueArrayList)
                    {
                        if(revenue.getYear()==year) {
                            if(revenueCollection.contains(revenue))
                                revenueCollection.remove(revenue);
                            revenue.addRevenue(price);
                            revenueCollection.add(revenue);
                            flag = true;

                        }
                    }
                    if(!flag)
                    {
                        ArrayList<LspRevenue> revList = new ArrayList<>();
                        LspRevenue rev = new LspRevenue(year, price,lsp);
                        revList.add(rev);
                        lsp.setRevenue(revList);
                        revenueCollection.add(rev);

                    }
                }
                else {
                    ArrayList<LspRevenue> revList = new ArrayList<>();
                    LspRevenue rev = new LspRevenue(year, price,lsp);
                    revList.add(rev);
                    lsp.setRevenue(revList);
                    revenueCollection.add(rev);
                }
            }
        }

        Comparator<LspRevenue> revenueComparator = (a, b) -> -a.getRevenue().compareTo(b.getRevenue());
        List<LspRevenue> sortedRevenue = revenueCollection.stream().sorted(revenueComparator).limit(k).collect(Collectors.toList());
        List<LaunchServiceProvider> lspList = new ArrayList<>();
        for(LspRevenue revenue:sortedRevenue)
        {
            lspList.add(revenue.getLsp());
        }

        return lspList;
    }
}