import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class api {

    public static void main(String[] args) {
    	
        try {
            String endpoint = "http://127.0.0.1:5000/";
            URL url = new URL(endpoint);

            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");

            int estado = con.getResponseCode();
            if (estado == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String entrada;
                StringBuilder respuesta = new StringBuilder();

                while ((entrada = in.readLine()) != null) {
                    respuesta.append(entrada);
                }
                in.close();

                System.out.println("Respuesta de la API:");
                System.out.println(respuesta.toString());
            } else {
                System.out.println("Error al conectar con la API. Código de estado: " + estado);
            }

            con.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}