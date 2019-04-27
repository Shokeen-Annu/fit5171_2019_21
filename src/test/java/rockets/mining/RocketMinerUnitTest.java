package rockets.mining;

import com.google.common.collect.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rockets.dataaccess.DAO;
import rockets.dataaccess.neo4j.Neo4jDAO;
import rockets.model.Launch;
import rockets.model.LaunchServiceProvider;
import rockets.model.LspRevenue;
import rockets.model.Rocket;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.Random;

import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RocketMinerUnitTest {
    Logger logger = LoggerFactory.getLogger(RocketMinerUnitTest.class);

    private DAO dao;
    private RocketMiner miner;
    private List<Rocket> rockets;
    private List<LaunchServiceProvider> lsps;
    private List<Launch> launches;
    private List<Launch> launches1;

    @BeforeEach
    public void setUp() {
        dao = mock(Neo4jDAO.class);
        miner = new RocketMiner(dao);
        rockets = Lists.newArrayList();
        lsps = Arrays.asList(
                new LaunchServiceProvider("ULA", 1990, "USA"),
                new LaunchServiceProvider("SpaceX", 2002, "USA"),
                new LaunchServiceProvider("ESA", 1975, "Europe ")
        );

        // index of lsp of each rocket
        int[] lspIndex = new int[]{0, 0, 0, 1, 1};
        // 5 rockets
        for (int i = 0; i < 5; i++) {
            rockets.add(new Rocket("rocket_" + i, "USA", lsps.get(lspIndex[i])));
        }
        // month of each launch
        int[] months = new int[]{1, 6, 4, 3, 4, 11, 6, 5, 12, 5};

        // index of rocket of each launch
        int[] rocketIndex = new int[]{0, 0, 0, 0, 1, 1, 1, 2, 2, 3};

        // index of lsp for each launch
        int[] launchLspIndex = new int[] {1,0,2,2,0,0,1,2,1,0};

        // index of launch outcome for each: Added by Pranav
        Launch.LaunchOutcome[] launchoutcome1 = new Launch.LaunchOutcome[]{Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.SUCCESSFUL};
        Launch.LaunchOutcome[] launchoutcome2 = new Launch.LaunchOutcome[]{Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.SUCCESSFUL};
        Launch.LaunchOutcome[] launchoutcome3 = new Launch.LaunchOutcome[]{Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.SUCCESSFUL,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.FAILED,Launch.LaunchOutcome.SUCCESSFUL};
        // index of price for each launch : Added by Pranav
        BigDecimal[] launchPrice = new BigDecimal[] {new BigDecimal(2.33), new BigDecimal(4.33),new BigDecimal(7.33),new BigDecimal(13.23),new BigDecimal(2.33),new BigDecimal(2.33),new BigDecimal(27.33), new BigDecimal(12.33),new BigDecimal(2.83),new BigDecimal(6.33)};

        // 10 launches
        launches = IntStream.range(0, 10).mapToObj(i -> {

            logger.info("create " + i + " launch in month: " + months[i]);
            Launch l = new Launch();
            l.setLaunchDate(LocalDate.of(2017, months[i], 1));
            l.setLaunchVehicle(rockets.get(rocketIndex[i]));
            l.setLaunchServiceProvider(rockets.get(rocketIndex[i]).getManufacturer());
            l.setLaunchSite("VAFB");
            l.setOrbit("LEO");
            l.setPrice(launchPrice[i]);
            l.setLaunchServiceProvider(lsps.get(launchLspIndex[i]));
            spy(l);
            return l;
        }).collect(Collectors.toList());

        // 10 launches : Added by Pranav
        launches1 = new ArrayList<>();
        for(LaunchServiceProvider lsp: lsps) {
            int i;
            for(i=0;i<10;i++) {

                logger.info("create " + i + " launch in month: " + months[i]);
                Launch l1 = new Launch();
                l1.setLaunchDate(LocalDate.of(2017, months[i], 1));
                l1.setLaunchVehicle(rockets.get(rocketIndex[i]));
                l1.setLaunchSite("VAFB");
                l1.setOrbit("LEO");
                l1.setPrice(launchPrice[i]);
                l1.setLaunchServiceProvider(lsp);
                if(lsps.get(0).equals(lsp))
                    l1.setLaunchOutcome(launchoutcome1[i]);
                else if(lsps.get(1).equals(lsp))
                    l1.setLaunchOutcome(launchoutcome2[i]);
                else
                    l1.setLaunchOutcome(launchoutcome3[i]);
                launches1.add(l1);
            }

        }
    }


    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3})
    public void shouldReturnTopMostRecentLaunches(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Launch> sortedLaunches = new ArrayList<>(launches);
        sortedLaunches.sort((a, b) -> -a.getLaunchDate().compareTo(b.getLaunchDate()));
        List<Launch> loadedLaunches = miner.mostRecentLaunches(k);
        assertEquals(k, loadedLaunches.size());
        assertEquals(sortedLaunches.subList(0, k), loadedLaunches);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void shouldReturnTopMostExpensiveLaunches(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List<Launch> sortedLaunches = new ArrayList<>(launches);
        sortedLaunches.sort((a, b) -> -a.getPrice().compareTo(b.getPrice()));
        List<Launch> loadedLaunches = miner.mostExpensiveLaunches(k);
        assertEquals(k, loadedLaunches.size());
        assertEquals(sortedLaunches.subList(0, k), loadedLaunches);
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void shouldReturnTopMostLaunchedRockets(int k) {
        when(dao.loadAll(Rocket.class)).thenReturn(rockets);
        when(dao.loadAll(Launch.class)).thenReturn(launches);
       List<Rocket> r = new ArrayList<>();
       r.add(new Rocket("rocket_" + 0, "USA", new LaunchServiceProvider("ULA", 1990, "USA")));
       r.add(new Rocket("rocket_" + 1, "USA", new LaunchServiceProvider("ULA", 1990, "USA")));
       r.add(new Rocket("rocket_" + 2, "USA", new LaunchServiceProvider("ULA", 1990, "USA")));
       r.add(new Rocket("rocket_" + 3, "USA",new LaunchServiceProvider("SpaceX", 2002, "USA")));
        //List<Rocket> mockRocket = spy(r);
        List mockRocket = mock(ArrayList.class);
        when(mockRocket.get(0)).thenReturn(r.get(0));
        List<Rocket> loadedRockets = miner.mostLaunchedRockets(k);
        assertEquals(k, loadedRockets.size());
        assertEquals(mockRocket.get(0), loadedRockets.get(0)); // checks the top rocket launched
        assertEquals(r.subList(0, k), loadedRockets); // checks all the top k rockets launched
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    public void shouldReturnTopMostMostReliableLaunchServiceProviders(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches1);
        when(dao.loadAll(LaunchServiceProvider.class)).thenReturn(lsps);
        List<LaunchServiceProvider> lsp = new ArrayList<>();
        lsp.add(new LaunchServiceProvider("ULA", 1990, "USA"));
        lsp.add(new LaunchServiceProvider("ESA", 1975, "Europe "));
        lsp.add(new LaunchServiceProvider("SpaceX", 2002, "USA"));
        List<LaunchServiceProvider> reliableLSP = miner.mostReliableLaunchServiceProviders(k);
        assertEquals(k, reliableLSP.size());
        assertEquals(lsp.subList(0, k), reliableLSP);
    }


    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void shouldReturnTopLSPWithHighestRevenueForValidK(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        List mockedTopLSPList=mock(List.class);
        LaunchServiceProvider mockLsp1 = new LaunchServiceProvider("ULA", 1990, "USA");

        when(mockedTopLSPList.get(0)).thenReturn(mockLsp1);
        List<LaunchServiceProvider> loadedLsps = miner.highestRevenueLaunchServiceProviders(k,2017);
        assertEquals(k, loadedLsps.size());
        assertEquals(mockedTopLSPList.get(0),loadedLsps.get(0));
    }
    @ParameterizedTest
    @ValueSource(ints = {-1,0})
    public void shouldThrowExceptionWhenInputToTopLSPIsInvalid(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);

        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class,()->miner.highestRevenueLaunchServiceProviders(k,2017));
        assertEquals(exception.getMessage(),"k should be greater than 0");
    }
    @ParameterizedTest
    @ValueSource(ints = {1,2})
    public void shouldReturnLaunchServiceProvidersWithHighestRevenue(int k) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        Map<LaunchServiceProvider,BigDecimal> launchRevenue=launches.stream()
                .filter(a->a.getLaunchDate().getYear()==2017)
                .collect(Collectors.groupingBy(Launch::getLaunchServiceProvider,Collectors.reducing(BigDecimal.ZERO,Launch::getPrice,BigDecimal::add)));
        Map<LaunchServiceProvider,BigDecimal> sortedRevenue = launchRevenue.entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue,(e1,e2)->e2, LinkedHashMap::new ));
        List<LaunchServiceProvider> sortedLsps = Lists.newArrayList();
        for(Map.Entry<LaunchServiceProvider,BigDecimal> item:sortedRevenue.entrySet())
        {
           sortedLsps.add(item.getKey());
        }
        List<LaunchServiceProvider> loadedLsps = miner.highestRevenueLaunchServiceProviders(k,2017);
        assertEquals(k, loadedLsps.size());
        assertEquals(sortedLsps.subList(0, k), loadedLsps);
    }
    @ParameterizedTest
    @ValueSource(strings = {"LEO"})
    public void shouldReturnCountryWithMaximumRockets(String orbit) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);

        String country = miner.dominantCountry(orbit);
        RocketMiner mockRM=mock(RocketMiner.class);
        when(mockRM.dominantCountry(orbit)).thenReturn("usa");
        assertEquals(mockRM.dominantCountry(orbit),country);
    }
    @ParameterizedTest
    @ValueSource(strings = {"LEO"})
    public void negativeTestForDominantCountry(String orbit) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);

        String country = miner.dominantCountry(orbit);
        RocketMiner mockRM=mock(RocketMiner.class);
        when(mockRM.dominantCountry(orbit)).thenReturn("europe");
        assertNotEquals(mockRM.dominantCountry(orbit),country);
    }
    @ParameterizedTest
    @ValueSource(strings = {""," "})
    public void shouldThrowExceptionWhenInputToDominantCountryIsEmpty(String orbit) {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        IllegalArgumentException exception =  assertThrows(IllegalArgumentException.class,()->miner.dominantCountry(orbit));
        assertEquals(exception.getMessage(),"orbit should not be null or empty");

    }

    @Test
    public void shouldThrowExceptionWhenInputToDominantCountryIsNull() {
        when(dao.loadAll(Launch.class)).thenReturn(launches);
        NullPointerException exception =  assertThrows(NullPointerException.class,()->miner.dominantCountry(null));
        assertEquals(exception.getMessage(),"orbit should not be null or empty");

    }
}