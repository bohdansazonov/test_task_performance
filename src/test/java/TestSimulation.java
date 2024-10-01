import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.*;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;


public class TestSimulation extends Simulation {

    private static String APP_BASE_URL = "https://emojihub.yurace.pro";
    private static int USERS_QUANTITY = 10;
    private static int DURATION_SECONDS = 30;

    HttpProtocolBuilder httpProtocol =
            http.baseUrl(APP_BASE_URL)
                    .acceptHeader("text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
                    .userAgentHeader("Mozilla/5.0 (Macintosh; Intel Mac OS X 10.15; rv:109.0) Gecko/20100101 Firefox/119.0");

    // Scenario 1 will get random emoji
    ScenarioBuilder scenario1 = scenario("Get random emoji")
            .exec(
                    http("Random emoji")
                            .get("/api/random")
            );

    //Scenario 2 will get random emoji from 'Flags' category
    ScenarioBuilder scenario2 = scenario("Get random flag emoji")
            .exec(
                    http("random flag")
                            .get("/api/all/category/travel-and-places")
            );

    //Scenario 3 will get all emojis from group 'Birds'
    ScenarioBuilder scenario3 = scenario("TestSimulation")
            .exec(
                    http("bird")
                            .get("/api/all/group/animal-bird")
            );


    //Test simulation will run 3 scenarios simultaneously
    //For each scenario 5 users will send request each second during 30 seconds
    {
        setUp(
                scenario1.injectOpen(constantUsersPerSec(USERS_QUANTITY).during(DURATION_SECONDS))
                        .protocols(httpProtocol),
                scenario2.injectOpen(constantUsersPerSec(USERS_QUANTITY).during(DURATION_SECONDS))
                        .protocols(httpProtocol),
                scenario3.injectOpen(constantUsersPerSec(USERS_QUANTITY).during(DURATION_SECONDS))
                        .protocols(httpProtocol)
        );
    }
}
