package powerbi.service;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import java.util.HashMap;
import java.util.Map;


@Service
public class PowerBiService {

    public String getEmbedToken() throws IOException, InterruptedException {
        String accessTokenBody =  getAccessToken();
        String regex = "\"access_token\":\"([^\"]+)\"";
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(regex);
        java.util.regex.Matcher matcher = pattern.matcher(accessTokenBody);
        String accessToken="";
        // Check if the pattern is found
        if (matcher.find()) {
             accessToken = matcher.group(1);
            System.out.println("Access Token: " + accessToken);
        } else {
            System.out.println("Access Token not found in the input string.");
        }
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://api.powerbi.com/v1.0/myorg/groups/538995c7-826d-4ccb-92be-b5b133e698aa/reports/cd026166-bcd5-4cb6-ac44-edbda2d78829"))
                .header("Authorization","Bearer "+accessToken)
                .GET()
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> accessTokenBodyHit = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println("embed token"+accessTokenBodyHit.body());
        String embedRegex = "\"embedUrl\":\"([^\"]+)\"";
        java.util.regex.Pattern pattern1 = java.util.regex.Pattern.compile(embedRegex);
        java.util.regex.Matcher matcher1 = pattern1.matcher(accessTokenBodyHit.body());
        String embedUrl="";
        if (matcher1.find()) {
            embedUrl = matcher1.group(1);
            System.out.println("embedUrl: " + embedUrl);
        } else {
            System.out.println("embedUrl not found in the input string.");
        }
        return embedUrl;
    }
    public String getAccessToken() throws IOException, InterruptedException {
        Map<Object, Object> data = new HashMap<>();
        data.put("grant_type", "client_credentials");
        data.put("client_id", "202998ae-fab2-45c1-bbe0-b5ce7c244d87");
        data.put("client_secret", "QNx8Q~m8O_cESvXrq31qK3J9zSMRj6LpTR0_pa19");
        data.put("scope","https://analysis.windows.net/powerbi/api/.default");
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://login.microsoftonline.com/9652d7c2-1ccf-4940-8151-4a92bd474ed0/oauth2/v2.0/token"))
                .header(HttpHeaders.CACHE_CONTROL, "max-age=0")
                .POST(buildFormDataFromMap(data))
                .build();
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpResponse<String> accessTokenBody = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(accessTokenBody.body());
        return accessTokenBody.body();
    }

    private static HttpRequest.BodyPublisher buildFormDataFromMap(Map<Object, Object> data) {
        var builder = new StringBuilder();
        for (Map.Entry<Object, Object> entry : data.entrySet()) {
            if (builder.length() > 0) {
                builder.append("&");
            }
            builder.append(URLEncoder.encode(entry.getKey().toString(), StandardCharsets.UTF_8));
            builder.append("=");
            builder.append(URLEncoder.encode(entry.getValue().toString(), StandardCharsets.UTF_8));
        }
        return HttpRequest.BodyPublishers.ofString(builder.toString());
    }
}
