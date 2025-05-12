package us.metabolomics.sirius.client;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SiriusClientApplicationTests {

    // dependency injection
    @Autowired
    private SiriusClient siriusClient;

    // context test to see if app can even start (configuration, beans, etc.)
    @Test
    void contextLoads() {
    }

    // basic query test
    @Test
    void testQuerySirius() {
        // create request object
        Request req = new Request("301.22977:66.3 311.08008:1.3 399.99106:2.3", "413.26611887841", true);

        // call client
        ResultLists results = siriusClient.querySirius(req);

        // assert result object is not null lists are not empty
        assertNotNull(results, "ERROR: The results object was null.");
        assertFalse(results.getFormulas().isEmpty(), "ERROR: The predicted formulas list was empty.");
        assertFalse(results.getScores().isEmpty(), "ERROR: The predicted scores list was empty.");
        assertFalse(results.getAdducts().isEmpty(), "ERROR: The predicted adducts list was empty.");
        assertFalse(results.getPrecursors().isEmpty(), "ERROR: The predicted precursors list was empty.");

        // print predicted formulas
        System.out.println("\nPredicted Formulas:");
        for (String formula : results.getFormulas()) {
            System.out.println("- " + formula);
        }

        // print scores
        System.out.println("\nScores:");
        for (Float score : results.getScores()) {
            System.out.println("- " + score);
        }
    }
}
