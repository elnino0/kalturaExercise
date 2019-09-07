import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import httpclient.Httpclient;
import jsons.ErrorResponse;
import jsons.RegisterRequestJson;
import jsons.RegisterResponseJson;
import jsons.User;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.UUID;

public class MainTest {
    static private String url = "https://rest-eus1.ott.kaltura.com/restful_v5_0/api_v3/service/ottuser/action/register";
    @Test
    public void positiveTest() throws IOException {
        System.out.println(" PositiveTest start ");
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        User user = new User().withAddress("xympdpkyymlh fake address").withCity("xympdpkyymlh fake city").withCountryId(7)
                .withEmail("qaott2018+xympdpkyymlh1537875168491@gmail.com").withExternalId(uuid).withFirstName("Etian").withLastName("Baruch").withObjectType("KalturaOTTUser").withUsername("crissSomething"+uuid.substring(0,10));
        RegisterRequestJson registerRequestJson = new RegisterRequestJson().withUser(user).withPartnerId(185).withPassword("password");
        System.out.println("json created");
        Httpclient httpclient = new Httpclient();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(" Sending register request ");
        System.out.println( " with body " + registerRequestJson);
        HttpResponse httpResponse = httpclient.post(url,gson.toJson(registerRequestJson));
        String JsonResponse = inputstreamToString(httpResponse.getEntity().getContent());
        System.out.println("reponse : \n " + JsonResponse );
        System.out.println("reponse status: \n " + httpResponse.getStatusLine());
        Assert.assertEquals("bad http status", httpResponse.getStatusLine().toString(),"HTTP/1.1 200 OK");
        RegisterResponseJson  registerResponseJson = gson.fromJson(JsonResponse, RegisterResponseJson.class);
        Assert.assertNotNull("bad body in response ",registerResponseJson.getResult().getCountryId());

        Assert.assertEquals(registerResponseJson.getResult().getCountryId(),user.getCountryId());
        Assert.assertEquals(registerResponseJson.getResult().getEmail(),user.getEmail());
        Assert.assertEquals(registerResponseJson.getResult().getFirstName(),user.getFirstName());
        Assert.assertEquals(registerResponseJson.getResult().getLastName(),user.getLastName());

        System.out.println(" PositiveTest pass ");

    }

    @Test
    public void alreadyExistUserName() throws IOException {
        System.out.println(" PositiveTest start ");
        final String uuid = UUID.randomUUID().toString().replace("-", "");
        User user = new User().withAddress("xympdpkyymlh fake address").withCity("xympdpkyymlh fake city").withCountryId(7)
                .withEmail("qaott2018+xympdpkyymlh1537875168491@gmail.com").withExternalId(uuid).withFirstName("Etian").withLastName("Baruch").withObjectType("KalturaOTTUser").withUsername("crissSomething");
        RegisterRequestJson registerRequestJson = new RegisterRequestJson().withUser(user).withPartnerId(185).withPassword("password");
        System.out.println("json created");
        Httpclient httpclient = new Httpclient();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(" Sending register request ");
        System.out.println( " with body " + registerRequestJson);
        HttpResponse httpResponse = httpclient.post(url,gson.toJson(registerRequestJson));
        String JsonResponse = inputstreamToString(httpResponse.getEntity().getContent());
        System.out.println("reponse : \n " + JsonResponse );
        System.out.println("reponse status: \n " + httpResponse.getStatusLine());
        Assert.assertEquals("bad http status", httpResponse.getStatusLine().toString(),"HTTP/1.1 200 OK");
        ErrorResponse registerResponseJson = gson.fromJson(JsonResponse, ErrorResponse.class);
        Assert.assertNotNull("bad body in response ",registerResponseJson.getResult().getError());
        Assert.assertEquals(registerResponseJson.getResult().getError().getCode(),"2014");
    }

    @Test
    public void alreadyExistExternalId() throws IOException {
        System.out.println(" PositiveTest start ");
        final String externalId = "910752d8835945c68fb0d7d7a37fddf4";
        User user = new User().withAddress("xympdpkyymlh fake address").withCity("xympdpkyymlh fake city").withCountryId(7)
                .withEmail("qaott2018+xympdpkyymlh1537875168491@gmail.com").withExternalId(externalId).withFirstName("Etian").withLastName("Baruch").withObjectType("KalturaOTTUser").withUsername("crissSomething"+externalId.substring(0,10));
        RegisterRequestJson registerRequestJson = new RegisterRequestJson().withUser(user).withPartnerId(185).withPassword("password");
        System.out.println("json created");
        Httpclient httpclient = new Httpclient();
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        System.out.println(" Sending register request ");
        System.out.println( " with body " + registerRequestJson);
        HttpResponse httpResponse = httpclient.post(url,gson.toJson(registerRequestJson));
        String JsonResponse = inputstreamToString(httpResponse.getEntity().getContent());
        System.out.println("reponse : \n " + JsonResponse );
        System.out.println("reponse status: \n " + httpResponse.getStatusLine());
        Assert.assertEquals("bad http status", httpResponse.getStatusLine().toString(),"HTTP/1.1 200 OK");
        ErrorResponse registerResponseJson = gson.fromJson(JsonResponse, ErrorResponse.class);
        Assert.assertNotNull("bad body in response ",registerResponseJson.getResult().getError());
        Assert.assertEquals(registerResponseJson.getResult().getError().getCode(),"2014");
    }

    private  String inputstreamToString(InputStream inputStream) throws IOException {
        String theString = null;
        StringWriter writer = new StringWriter();
        try {

            IOUtils.copy(inputStream, writer, "utf-8");
             theString = writer.toString();
        }finally {
            writer.close();
            return  theString;
        }
    }

}
