package nl.ase.dare2date.registration;

import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;


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

        URL mailgun = new URL("https://api.mailgun.net/v2/address/validate?address=" + email + "&api-key=pubkey-177ymudf6a3jq3rer-z6gfro-c36agq8");
        URLConnection mailgunConnection = mailgun.openConnection();
        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        mailgunConnection.getInputStream()));
        String inputLine;
        String results = "";
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
            results += inputLine;
        }

        in.close();
        //JSONObject jsonResult = new JSONObject(results).getBoolean("hh");
        //System.out.println("@@@@@@@@@@@@@@@@@@" + jsonResult.getBoolean("is_valid"));
        //System.out.println();
        return new JSONObject(results).getBoolean("is_valid");
    }



}
