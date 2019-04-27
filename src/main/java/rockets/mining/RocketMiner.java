package rockets.mining;

import com.google.common.collect.Lists;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.harness.TestServerBuilders;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.dataaccess.neo4j.Neo4jDAO;
import rockets.model.*;
import static org.apache.commons.lang3.Validate.notBlank;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Iterator;
import org.neo4j.harness.ServerControls;

import static org.neo4j.ogm.cypher.ComparisonOperator.EQUALS;

public class RocketMiner {

    private static Logger logger = LoggerFactory.getLogger(RocketMiner.class);
    private DAO dao;
    private DAO sessionDao;
    private Session session;
    private SessionFactory sessionFactory;
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
        return null;
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
    /**
    public List<LaunchServiceProvider> mostReliableLaunchServiceProviders(int k) {
        logger.info("find most launched rockets " + k + " launches");
        Collection<Launch> launches = dao.loadAll(Launch.class);
        Collection<LaunchServiceProvider> launcheService = dao.loadAll(LaunchServiceProvider.class);
        List<launches> list = new ArrayList<>();
        List<LaunchServiceProvider> serviceList = new ArrayList<>();
        Iterator iterator = list.iterator();

        for(int i = 0; i<list.size(); i++)
        {
            Launch abc = list.get(i);
            if(abc.getLaunchOutcome().equals(Launch.LaunchOutcome.SUCCESSFUL))
            serviceList.add(abc.getLaunchServiceProvider());
        }

        Comparator<Launch> launchComparator = (a, b) -> -a.getLaunchOutcome().compareTo(b.getLaunchOutcome());
        return null;
    }*/

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
    public String dominantCountry(String orbit) {
        notBlank(orbit,"orbit should not be null or empty");
        Collection<Launch> launches=dao.loadAll(Launch.class);
        HashMap<String,Integer> dictionary=new HashMap<String,Integer>();
        Iterator<Launch> launchIterator = launches.iterator();
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

        logger.info("find top "+k+" launch service providers with highest revenue");

        if(k<=0)
            throw new IllegalArgumentException("k should be greater than 0");

        Collection<Launch> launches=dao.loadAll(Launch.class);
        Collection<LspRevenue> revenueCollection = Lists.newArrayList();
        Iterator<Launch> launchIterator = launches.iterator();
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
                            logger.info(revenue.getLsp()+" : "+revenue.getRevenue());
                        }
                    }
                    if(!flag)
                    {
                        ArrayList<LspRevenue> revList = new ArrayList<LspRevenue>();
                        LspRevenue rev = new LspRevenue(year, price,lsp);
                        revList.add(rev);
                        lsp.setRevenue(revList);
                        revenueCollection.add(rev);

                    }
                }
                else {
                    ArrayList<LspRevenue> revList = new ArrayList<LspRevenue>();
                    LspRevenue rev = new LspRevenue(year, price,lsp);
                    revList.add(rev);
                    lsp.setRevenue(revList);
                    revenueCollection.add(rev);
                }
            }
        }

       // Collection<LspRevenue> revenueCollection = sessionDao.loadAll(LspRevenue.class);
        Comparator<LspRevenue> revenueComparator = (a, b) -> -a.getRevenue().compareTo(b.getRevenue());
        List<LspRevenue> sortedRevenue = revenueCollection.stream().sorted(revenueComparator).limit(k).collect(Collectors.toList());
        List<LaunchServiceProvider> lspList = new ArrayList<LaunchServiceProvider>();
        for(LspRevenue revenue:sortedRevenue)
        {
            lspList.add(revenue.getLsp());
        }

        return lspList;
    }
}