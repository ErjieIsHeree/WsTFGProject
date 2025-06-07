import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class apiClass {
    public static void main(String[] args) {
        try {
            // URL del endpoint de la API
            URL url = new URL("http://localhost:5000/");

            // Establecer la conexión
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            conn.setRequestProperty("Accept", "application/json");
            conn.setDoOutput(true);

            // Datos JSON a enviar
            String jsonInputString = "{\"nombre\": \"Juan\", \"edad\": 30}";

            // Enviar los datos
            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
                os.write(input, 0, input.length);
            }

            // Leer la respuesta
            int code = conn.getResponseCode();
            System.out.println("Código de respuesta: " + code);

            try (BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8))) {
                StringBuilder response = new StringBuilder();
                String responseLine;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                System.out.println("Respuesta del servidor: " + response.toString());
            }

            conn.disconnect();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}