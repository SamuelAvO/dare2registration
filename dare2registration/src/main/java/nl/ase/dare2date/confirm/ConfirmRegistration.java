package nl.ase.dare2date.confirm;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import nl.ase.dare2date.Subscriber;

import javax.ws.rs.core.MediaType;

/**
 * Created by samuel on 2-6-14.
 */
public class ConfirmRegistration implements IConformRegistration {
    public boolean confirmNewSubscriber(Subscriber subscriber) {
        try {
            sendEmail(subscriber.getEmail(), "Dare2Date registration", "Welcome to Dare2Date");
        } catch (Exception e) {
            return false;
            //e.printStackTrace();
        }
        return true;
    }

    public String sendEmail(String to, String subject, String text) throws Exception{

        Client client = Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api","key-8uyia6562v-dr6j5m3a1gx0d4cogmkx7"));

        WebResource webResource = client.resource("https://api.mailgun.net/v2/sandbox03f8bee02d4443a1aa757263823c4519.mailgun.org" +
                "/messages");

        MultivaluedMapImpl formData = new MultivaluedMapImpl();
        formData.add("from", "Dare2Date <rascal@sandbox03f8bee02d4443a1aa757263823c4519.mailgun.org>");
        formData.add("to", to);
        formData.add("subject", subject);
        formData.add("text", text);

        ClientResponse clientResponse = webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class, formData);
        String output = clientResponse.getEntity(String.class);

        //System.out.println("@@@@@@@@" + output);

        //setMessage("Your email has been sent successfully : " + output);
        return "SUCCESS";
    }
}
