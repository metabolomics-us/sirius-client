package us.metabolomics.sirius.sirius_client;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class SiriusClient {
    // SIRIUS API endpoint
    private static final String API_URL = "https://sirius.metabolomics.us/formulas/";

    // Method to query the API with a request object
    public ResultLists querySirius(Request request) {
        RestTemplate restTemplate = new RestTemplate();

        // http headers to tell the API that we are sending a json payload
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        // entity becomes the whole package, our request body + headers 
        HttpEntity<Request> entity = new HttpEntity<>(request, headers);

        // try to call API and return if successful, catch errors and report accordingly if something fails
        try {
            ResponseEntity<ResultLists> response = restTemplate.postForEntity(API_URL, entity, ResultLists.class);
            return response.getBody();
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.err.println("API Error: " + e.getStatusCode() + " - " + e.getResponseBodyAsString()); 
        } catch (RestClientException e) {
            System.err.println("Request failed: " + e.getMessage());
        }

        throw new RuntimeException("Something unexepected happened. The API query failed but an error was not caught.");
    }
}
