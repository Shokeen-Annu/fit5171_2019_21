package rockets.dataaccess.neo4j;

import com.google.common.collect.Sets;
import org.junit.jupiter.api.*;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.harness.ServerControls;
import org.neo4j.harness.TestServerBuilders;
import org.neo4j.ogm.cypher.Filter;
import org.neo4j.ogm.cypher.Filters;
import org.neo4j.ogm.drivers.embedded.driver.EmbeddedDriver;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;
import rockets.dataaccess.DAO;
import rockets.model.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.neo4j.ogm.cypher.ComparisonOperator.EQUALS;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Neo4jDAOUnitTest {
    private DAO dao;
    private Session session;
    private SessionFactory sessionFactory;

    private LaunchServiceProvider esa;
    private LaunchServiceProvider spacex;
    private Rocket rocket;
    private LspRevenue rev;

    @BeforeAll
    public void initializeNeo4j() {
        ServerControls embeddedDatabaseServer = TestServerBuilders.newInProcessBuilder().newServer();
        GraphDatabaseService dbService = embeddedDatabaseServer.graph();
        EmbeddedDriver driver = new EmbeddedDriver(dbService);
        sessionFactory = new SessionFactory(driver, User.class.getPackage().getName());
        session = sessionFactory.openSession();
        dao = new Neo4jDAO(session);
    }

    @BeforeEach
    public void setup() {
        esa = new LaunchServiceProvider("ESA", 1970, "Europe");
        spacex = new LaunchServiceProvider("SpaceX", 2002, "USA");
        rocket = new Rocket("F9", "USA", spacex);
        rev = new LspRevenue(2008, new BigDecimal(25000),spacex);
    }

    @Test
    public void shouldCreateNeo4jDAOSuccessfully() {
        assertNotNull(dao);
    }

    @Test
    public void shouldCreateARocketSuccessfully() {
        rocket.setWikilink("https://en.wikipedia.org/wiki/Falcon_9");
        Rocket graphRocket = dao.createOrUpdate(rocket);
        assertNotNull(graphRocket.getId());
        assertEquals(rocket, graphRocket);
        LaunchServiceProvider manufacturer = graphRocket.getManufacturer();
        assertNotNull(manufacturer.getId());
        assertEquals(rocket.getWikilink(), graphRocket.getWikilink());
        assertEquals(spacex, manufacturer);
    }

    @Test
    public void shouldUpdateRocketAttributeSuccessfully() {
        rocket.setWikilink("https://en.wikipedia.org/wiki/Falcon_9");

        Rocket graphRocket = dao.createOrUpdate(rocket);
        assertNotNull(graphRocket.getId());
        assertEquals(rocket, graphRocket);

        String newLink = "http://adifferentlink.com";
        rocket.setWikilink(newLink);
        dao.createOrUpdate(rocket);
        graphRocket = dao.load(Rocket.class, rocket.getId());
        assertEquals(newLink, graphRocket.getWikilink());
    }

    @Test
    public void shouldNotSaveTwoSameRockets() {
        assertNull(spacex.getId());

        Rocket rocket1 = new Rocket("F9", "USA", spacex);
        Rocket rocket2 = new Rocket("F9", "USA", spacex);
        assertEquals(rocket1, rocket2);
        dao.createOrUpdate(rocket1);
        assertNotNull(spacex.getId());
        Collection<Rocket> rockets = dao.loadAll(Rocket.class);
        assertEquals(1, rockets.size());
        Collection<LaunchServiceProvider> manufacturers = dao.loadAll(LaunchServiceProvider.class);
        assertEquals(1, manufacturers.size());
        dao.createOrUpdate(rocket2);
        manufacturers = dao.loadAll(LaunchServiceProvider.class);
        assertEquals(1, manufacturers.size());
        rockets = dao.loadAll(Rocket.class);
        assertEquals(1, rockets.size());
    }

    @Test
    public void shouldLoadAllRockets() {
        Set<Rocket> rockets = Sets.newHashSet(
                new Rocket("Ariane4", "France", esa),
                new Rocket("F5", "USA", spacex),
                new Rocket("BFR", "USA", spacex)
        );

        for (Rocket r : rockets) {
            dao.createOrUpdate(r);
        }

        Collection<Rocket> loadedRockets = dao.loadAll(Rocket.class);
        assertEquals(rockets.size(), loadedRockets.size());
        for (Rocket r : rockets) {
            assertTrue(rockets.contains(r));
        }
    }

    @Test
    public void shouldCreateALaunchSuccessfully() {
        Launch launch = new Launch();
        launch.setLaunchDate(LocalDate.of(2017, 1, 1));
        launch.setLaunchVehicle(rocket);
        launch.setLaunchSite("VAFB");
        launch.setOrbit("LEO");
        dao.createOrUpdate(launch);

        Collection<Launch> launches = dao.loadAll(Launch.class);
        assertFalse(launches.isEmpty());
        assertTrue(launches.contains(launch));
    }


    @Test
    public void shouldUpdateLaunchAttributesSuccessfully() {
        Launch launch = new Launch();
        launch.setLaunchDate(LocalDate.of(2017, 1, 1));
        launch.setLaunchVehicle(rocket);
        launch.setLaunchSite("VAFB");
        launch.setOrbit("LEO");
        dao.createOrUpdate(launch);

        Collection<Launch> launches = dao.loadAll(Launch.class);

        Launch loadedLaunch = launches.iterator().next();
        assertNull(loadedLaunch.getFunction());

        launch.setFunction("experimental");
        dao.createOrUpdate(launch);
        launches = dao.loadAll(Launch.class);
        assertEquals(1, launches.size());
        loadedLaunch = launches.iterator().next();
        assertEquals("experimental", loadedLaunch.getFunction());
    }

    @Test
    public void shouldDeleteRocketWithoutDeleteLSP() {
        dao.createOrUpdate(rocket);
        assertNotNull(rocket.getId());
        assertNotNull(rocket.getManufacturer().getId());
        assertFalse(dao.loadAll(Rocket.class).isEmpty());
        assertFalse(dao.loadAll(LaunchServiceProvider.class).isEmpty());
        dao.delete(rocket);
        assertTrue(dao.loadAll(Rocket.class).isEmpty());
        assertFalse(dao.loadAll(LaunchServiceProvider.class).isEmpty());
    }

    @Test
    public void shouldDeleteRevenueWithoutLSP() {
        dao.createOrUpdate(rev);
        assertNotNull(rev.getId());
        assertNotNull(rev.getLsp().getId());
        assertFalse(dao.loadAll(LspRevenue.class).isEmpty());
        assertFalse(dao.loadAll(LaunchServiceProvider.class).isEmpty());
        dao.delete(rev);
        assertTrue(dao.loadAll(LspRevenue.class).isEmpty());
        assertFalse(dao.loadAll(LaunchServiceProvider.class).isEmpty());
    }


    @Test
    public void shouldDeleteLspRevenue() {
        dao.createOrUpdate(rev);
        assertNotNull(rev.getId());
        assertFalse(dao.loadAll(LspRevenue.class).isEmpty());
        dao.delete(rev);
        assertTrue(dao.loadAll(LspRevenue.class).isEmpty());
    }


    @Test
    public void shouldDeletePayload() {
        Payload pay = new Payload();
        pay.setName("abc");
        pay.setType(Payload.TypeOfPayload.SATELLITE);
        pay.setWeight(3200.00);
        dao.createOrUpdate(pay);
        assertNotNull(pay.getId());
        assertFalse(dao.loadAll(Payload.class).isEmpty());
        dao.delete(pay);
        assertTrue(dao.loadAll(Payload.class).isEmpty());
    }

    @Test
    public void shouldDeleteLaunchServiceProvider() {
        dao.createOrUpdate(esa);
        assertNotNull(esa.getId());
        assertFalse(dao.loadAll(LaunchServiceProvider.class).isEmpty());
        dao.delete(esa);
        assertTrue(dao.loadAll(LaunchServiceProvider.class).isEmpty());
    }

    @Test
    public void shouldDeleteLaunch() {
        Launch launch = new Launch();
        launch.setLaunchDate(LocalDate.of(1993,9,21));
        launch.setLaunchVehicle(rocket);
        launch.setLaunchSite("abc");
        launch.setLaunchOutcome(Launch.LaunchOutcome.SUCCESSFUL);
        dao.createOrUpdate(launch);
        assertNotNull(launch.getId());
        assertFalse(dao.loadAll(Launch.class).isEmpty());
        dao.delete(launch);
        assertTrue(dao.loadAll(Launch.class).isEmpty());
    }

    @Test
    public void shouldLoadLspRevenueWithFilters()
    {
        LaunchServiceProvider provider = new LaunchServiceProvider("SpaceX",2002,"USA");
        ArrayList<LspRevenue> revenues = new ArrayList<LspRevenue>();
        LspRevenue r1 = new LspRevenue(2018,new BigDecimal(35.33),provider);
        LspRevenue r2 = new LspRevenue(2017,new BigDecimal(50.33),provider);
        LspRevenue r3 = new LspRevenue(2018,new BigDecimal(65.33),provider);

        revenues.add(r1);
        revenues.add(r2);
        revenues.add(r3);

        for (LspRevenue r : revenues) {
            dao.createOrUpdate(r);
        }

        Filters filters = new Filters();
        filters.add(new Filter("year", EQUALS,2018));

        Collection<LspRevenue> loadedRevenues = dao.loadWithFilter(LspRevenue.class,filters);
        assertEquals(loadedRevenues.size(),2);

    }

    @AfterEach
    public void tearDown() {
        session.purgeDatabase();
    }

    @AfterAll
    public void closeNeo4jSession() {
        session.clear();
        sessionFactory.close();
    }
}