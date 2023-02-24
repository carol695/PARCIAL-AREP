package edu.escuelaing.arem.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HtttpConection {
    private static final String USER_AGENT = "Mozilla/5.0";


    // 1. Crear clase HttpConnection
    // 2. Copiar HttpConnection
    // 3. Cambiar método a getApi y retorna StringBuffer y recibe un string Ciudad
    // 4. Copiar API del clima + ciudad + la key
    public static StringBuffer getApi( String ciudad) throws IOException {
        String GET_URL = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=" + ciudad + "&apikey=Q1QZFVJQ21K7C6XM";
        URL obj = new URL(GET_URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);

        //The following invocation perform the connection implicitly before getting the code
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        // Antes del if colocal response y luego del get donde retornar response.
        StringBuffer response = new StringBuffer();
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            //5.  print result
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
        System.out.println("GET DONE");
        return response;
    }

}
