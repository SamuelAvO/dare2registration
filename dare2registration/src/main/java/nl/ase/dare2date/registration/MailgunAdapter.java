package nl.ase.dare2date.registration;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.api.representation.Form;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.sun.xml.internal.stream.Entity;

import javax.ws.rs.core.MediaType;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by samuel on 2-6-14.
 */
public class MailgunAdapter implements IVerifyEmail{
    public boolean verifyEmail(String email) {

        try {
            //execute();
            return executeMailgunEmailValidation(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean executeMailgunEmailValidation(String email) throws Exception{
        //pubkey-177ymudf6a3jq3rer-z6gfro-c36agq8

        URL yahoo = new URL("http://www.yahoo.com/");
        URLConnection yc = yahoo.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream()));
        String inputLine;

        while ((inputLine = in.readLine()) != null)
            System.out.println(inputLine);
        in.close();
        return true;
    }

    public String execute() throws Exception{

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api","key-8uyia6562v-dr6j5m3a1gx0d4cogmkx7"));

        WebResource webResource = client.resource("https://api.mailgun.net/v2/sandbox03f8bee02d4443a1aa757263823c4519.mailgun.org" +
                "/messages");

        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Yo <rascal@sandbox03f8bee02d4443a1aa757263823c4519.mailgun.org>");
        formData.add("to", "SamuelvanOostveen@gmail.com");
        formData.add("subject", "Hello");
        formData.add("text", "Mailgun werkt!!!!!!!!!");

        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        String output = clientResponse.getEntity(String.class);

        System.out.println("@@@@@@@@" + output);

        //setMessage("Your email has been sent successfully : " + output);
        return "SUCCESS";
    }

}
