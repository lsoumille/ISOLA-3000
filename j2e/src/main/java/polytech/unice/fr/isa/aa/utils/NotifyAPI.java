package polytech.unice.fr.isa.aa.utils;

import org.apache.cxf.jaxrs.client.WebClient;
import org.json.JSONObject;

import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lucas on 03/04/16.
 */
public class NotifyAPI {
    private String url = "http://localhost:9091";

    public boolean sendNotify(String message, String object, List<String> mails) {
        JSONObject request = new JSONObject().put("Message", message).put("Object", object).put("Mails", mails);
        String str = "";
        try {
            str = WebClient.create(url).path("/mail")
                    .accept(MediaType.APPLICATION_JSON_TYPE).header("Content-Type", MediaType.APPLICATION_JSON)
                    .post(request.toString(), String.class);
        } catch (Exception e) {

        }

        return (Integer.parseInt(str) == 0);

    }

    public static void main(String[] args) {
        NotifyAPI api = new NotifyAPI();
        List<String> mails = new ArrayList<>();
        mails.add("lucassoumille@yahoo.fr");
        api.sendNotify("Tu suces ?", "Coucou", mails);
    }
}
