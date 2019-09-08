package httpclient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.IOException;

public class Httpclient {

    String url = "https://selfsolve.apple.com/wcResults.do";

    HttpClient client = HttpClientBuilder.create().build();

    public HttpResponse post(String url, String json) throws IOException {
        HttpPost post = new HttpPost(url);
        post.setHeader("Content-Type", "application/json");
        HttpEntity entity = new ByteArrayEntity(json.getBytes("UTF-8"));
        post.setEntity(entity);
        return client.execute(post);
    }

}
