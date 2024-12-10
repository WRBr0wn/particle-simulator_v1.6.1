import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;

public class ParticleAPI {
    public JsonNode getInitialObstacles() {
        try {
            OkHttpClient client2 = new OkHttpClient().newBuilder().build();
            Request request = new Request.Builder().url("http://127.0.0.1:5000/obstacles").get().build();
            Response response = client2.newCall(request).execute();
        
            String json = response.body().string();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode jsonNode = mapper.readTree(json);
            return jsonNode;
        } catch (Exception e) {
            System.out.println("GET Error");
            return null;
        }
    }
    public void addObstacle(float x, float y) {
        try {
            float id = x+y;
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, "{\r\n    \"x\":"+ x +",\r\n    \"y\":"+ y +",\r\n    \"id\":"+ id +"\r\n}");
            Request request = new Request.Builder().url("http://127.0.0.1:5000/obstacle").method("POST", body).addHeader("Content-Type", "application/json").build();
            Response response = client.newCall(request).execute();
        }
        catch(Exception e) {
            System.out.println("POST Error");
        }
    }
    public void removeObstacle(float id) {
        try {
            OkHttpClient client = new OkHttpClient().newBuilder().build();
            MediaType mediaType = MediaType.parse("application/json");
            RequestBody body = RequestBody.create(mediaType, String.valueOf(id));
            Request request = new Request.Builder().url("http://127.0.0.1:5000/obstacle").method("DELETE", body).addHeader("Content-Type", "application/json").build();
            Response response = client.newCall(request).execute();
        }
        catch(Exception e) {
            System.out.println("DEL Error");
        }
    }
}
