package nl.highway.iungomain.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.HttpURLConnection;
import java.net.URL;

@RestController
@RequestMapping(path = "/iungo/system")
//TODO: Define access level when Oauth2 is operational.
public class SystemController {


    @Value("${spring.application.version}")
    private String version;

    // TODO: Do these methods also need to return a response-entity instead of a string?

    @RequestMapping
    public String index() {
        return "Iungo backend is up and running. Use /ping and /version for more information";
    }

    @RequestMapping(path="/ping")
    public String ping() {
            String status1 = getStatus("https://www.google.com");
            String status2 = getStatus("http://www.google.com");
            return String.format("Google https is: %s and Google http is: %s.", status1, status2);
    }

    @RequestMapping(path="/version")
    public String version(){

        return version;

        }


    public static String getStatus(String url){

        String result = "";
        try {
            URL urlObj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
            con.setRequestMethod("GET");
            // Set connection timeout
            con.setConnectTimeout(3000);
            con.connect();

            int code = con.getResponseCode();
            if (code == 200) {
                result = "On";
            }
        } catch (Exception e) {
            result = "Off";
        }
        return result;
    }
    }