package com.example.proyectofinalandroid.controller.baseDeDatos;

import android.net.Uri;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

/**
 * Esta clase permite hacer peticiones al servidor, para obtener
 * los datos de la base de datos
 *
 * @author Fernando
 */
public class HttpRequest {
    /**
     * Enviar peticiones de ACTUALIZACIÓN
     * @param url es la url a la que se le hace la peticion
     * @param values son los valores que se le pasan a la peticion
     * @return el resultado de la peticion
     * @author Fernando
     */
    public static String POST_REQUEST(String url, String values) {
        try {
            StringBuilder result = new StringBuilder();
            URL url2 = new URL(url);
            URLConnection conn = url2.openConnection();
            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(values);
            wr.flush();
            BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            wr.close();
            rd.close();
            return result.toString();
        } catch (IOException e) {
            return e.toString();
        }
    }

    /**
     * Solicitar la ejecución de consultas select
     *
     * @param url es la url a la que se le hace la peticion
     * @param values son los valores que se le pasan a la peticion
     * @return el resultado de la peticion
     * @author Fernando
     */
    public static String GET_REQUEST(String url, Map<String, String> params) {
        try {
            Uri.Builder uriBuilder = Uri.parse(url).buildUpon();
            for (Map.Entry<String, String> entry : params.entrySet()) {
                uriBuilder.appendQueryParameter(entry.getKey(), entry.getValue());
            }
            String urlString = uriBuilder.build().toString();
            URL requestUrl = new URL(urlString);

            HttpURLConnection connection = (HttpURLConnection) requestUrl.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000); // 5 segundos de tiempo de espera
            connection.setReadTimeout(5000); // 5 segundos de tiempo de espera

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = in.readLine()) != null) {
                    response.append(line);
                }
                in.close();
                return response.toString();
            } else {
                return "Error en la solicitud: " + responseCode;
            }
        } catch (IOException e) {
            return "Error en la conexión: " + e.getMessage();
        }
    }

}
