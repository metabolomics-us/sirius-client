package us.metabolomics.sirius.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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
    private static final Logger logger = LoggerFactory.getLogger(SiriusClient.class);

    @Value("${sirius.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate;

    @Autowired
    public SiriusClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResultLists querySirius(Request request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Request> entity = new HttpEntity<>(request, headers);

        try {
            ResponseEntity<ResultLists> response = restTemplate.postForEntity(apiUrl, entity, ResultLists.class);
            ResultLists results = response.getBody();
            if (results == null) {
                throw new RuntimeException("API returned empty response");
            }
            return results;
        } catch (HttpClientErrorException e) {
            logger.error("Client error when calling Sirius API: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new SiriusApiException("Client error when calling Sirius API", e);
        } catch (HttpServerErrorException e) {
            logger.error("Server error from Sirius API: {} - {}", e.getStatusCode(), e.getResponseBodyAsString());
            throw new SiriusApiException("Server error from Sirius API", e);
        } catch (RestClientException e) {
            logger.error("Failed to communicate with Sirius API", e);
            throw new SiriusApiException("Failed to communicate with Sirius API", e);
        }
    }
}