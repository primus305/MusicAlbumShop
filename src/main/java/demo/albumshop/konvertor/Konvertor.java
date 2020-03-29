/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package demo.albumshop.konvertor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

/**
 *
 * @author rancha
 */
public class Konvertor {

    public String getRezultat(String izValute, String uValutu, BigDecimal iznos) {
        String iznosString = String.valueOf(iznos);
        String res = "";
        StringBuilder response = new StringBuilder();
        try {
            String url = "https://api.kursna-lista.info/0e0156083e1ccc17dc40319ca542628a/konvertor/"+izValute+"/"+uValutu+"/"+iznosString;

            URL u = new URL(url);
            HttpURLConnection req = (HttpURLConnection) u.openConnection();
            req.setRequestMethod("GET");
            req.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10_5_8; en-US) AppleWebKit/532.5 (KHTML, like Gecko) Chrome/4.0.249.0 Safari/532.5");
            System.out.println(req.getRequestMethod());
            System.out.println(req.getResponseMessage());
            System.out.println(req.getResponseCode());
            if (req.getResponseCode() == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(req.getInputStream()));

                while ((res = in.readLine()) != null) {
                    response.append(res);
                }
                in.close();

                System.out.println(response.toString());

                JSONObject json = new JSONObject(response.toString());
                System.out.println("Vrednost: "+json.getJSONObject("result").getString("value"));
                res = json.getJSONObject("result").getString("value");
            } else {
                System.out.println("Problem sa konekcijom!");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
            res = "GRESKA!!!";
        }

        return res;
    }
}
