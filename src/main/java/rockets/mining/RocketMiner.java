package rockets.mining;

import org.neo4j.cypher.internal.frontend.v3_2.phases.Do;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.Rocket;


import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Set;


public class RocketMiner {
    private static Logger logger = LoggerFactory.getLogger(RocketMiner.class);

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
        logger.info("find most launched rockets " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        List<Rocket> r = new ArrayList<>();

        for(Launch l: launches) {
            if(l.getLaunchDate() != null) {
                r.add(l.getLaunchVehicle());
            }
        }
        Map<Rocket, Long> hashmap = r.stream().collect(Collectors.groupingBy(Rocket -> Rocket, Collectors.counting()));
        Map<Rocket, Long> sorted = hashmap .entrySet() .stream() .sorted(Collections.reverseOrder(Map.Entry.comparingByValue())) .collect( Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        System.out.println("map after sorting by values in descending order: " + sorted);

        //Set<Entry<Rocket, Long>> entries = hashmap.entrySet();
        //Comparator<Entry<Rocket, Long>> activeRocketsComparator = (a,b) -> -a.getValue().compareTo(b.getValue());
        //List<Entry<Rocket, Long>> listOfEntries = new ArrayList<>(entries);
        //Collections.sort(listOfEntries,activeRocketsComparator);
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
        logger.info("find most reliable rockets " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        List<LaunchServiceProvider> lsp = new ArrayList<>();

        /*Map<Launch.LaunchOutcome, Double> result = launches.stream()
                .collect(
                        Collectors.groupingBy(
                                Launch::getLaunchOutcome,
                                Collectors.collectingAndThen(
                                        Collectors.mapping(Launch::getLaunchOutcome, Collectors.averagingDouble(Launch.LaunchOutcome -> {
                                            return "SUCCESSFUL".equals(Launch.LaunchOutcome.SUCCESSFUL) ? 1:0;
        })), avg -> String.format("%,0.f%%", avg*100))));*/

        Map<Launch.LaunchOutcome, List<Launch>> postsPerType = launches.stream().
                collect(Collectors.groupingBy(Launch::getLaunchOutcome));

        return lsp;
    }
    /**
     * <p>
     * Returns the top-k most recent launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most recent launches.
     */
    public List<Launch> mostRecentLaunches(int k) {
        logger.info("find most recent " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Comparator<Launch> launchDateComparator = (a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate());
        return launches.stream().sorted(launchDateComparator).limit(k).collect(Collectors.toList());
    }

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the dominant country who has the most launched rockets in an orbit.
     *
     * @param orbit the orbit
     * @return the country who sends the most payload to the orbit
     */
    public String dominantCountry(String orbit) { return null;}

    /**
     * TODO: to be implemented & tested!
     * <p>
     * Returns the top-k most expensive launches.
     *
     * @param k the number of launches to be returned.
     * @return the list of k most expensive launches.
     */
    public List<Launch> mostExpensiveLaunches(int k) {
        logger.info("find most expensive " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Comparator<Launch> launchPriceComparator = (a, b) -> -a.getPrice().compareTo(b.getPrice());
        return launches.stream().sorted(launchPriceComparator).limit(k).collect(Collectors.toList());
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
        return null;
    }
}