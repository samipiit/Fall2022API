import client.NewsAPIClient;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import org.apache.http.HttpConnection;
import org.junit.Test;

import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.Matchers.hasSize;

public class TestNewsAPI extends NewsAPIClient {

    @Test
    public void testGetTopHeadlines() {
        HashMap<String, String> params = new HashMap<>();
        params.put("q", "midterms");

        String endpoint = config.url + config.serviceRoute + config.headlinesResource;
        String headerName = "X-Api-Key";
        String apiKey = config.apiKey;

        Response response = get(endpoint, params, headerName, apiKey);
        response.prettyPeek();

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK);
    }

    @Test
    public void testSources() {
        List<String> sources = getSources();

        for (String source : sources) {
            System.out.println(source);
        }
    }

    @Test
    public void testResponseSize() {
        HashMap<String, String> params = new HashMap<>();
        params.put("q", "midterms");

        String endpoint = config.url + config.serviceRoute + config.headlinesResource;
        String headerName = "X-Api-Key";
        String apiKey = config.apiKey;

        Response response = get(endpoint, params, headerName, apiKey);

        response.then().assertThat().statusCode(HttpURLConnection.HTTP_OK).body("totalResults", greaterThanOrEqualTo(25));
    }

}
