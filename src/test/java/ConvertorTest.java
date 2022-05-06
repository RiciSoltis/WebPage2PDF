import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

public class ConvertorTest {


    @Test
    public void wrongWebSite() throws IOException {
        File file = new File("test.pdf");
        URL url = new URL("https://selectpdf.com/api2/convert/?key=de3874e6-e576-4278-938d-01b9dabb40b4&url=test");
        HttpURLConnection connection =Convertor.createPDFFile(url,file);
        assertEquals(499,connection.getResponseCode());

    }

    @Test
    public void wrongApiKey() throws IOException {
        File file = new File("test.pdf");
        URL url = new URL("https://selectpdf.com/api2/convert/?key=de3874e6-e576-4278-9-01b9dabb40b4&url=test");
        HttpURLConnection connection =Convertor.createPDFFile(url,file);
        assertEquals(401,connection.getResponseCode());

    }

    @Test
    public void validTest() throws IOException {
        File file = new File("test.pdf");
        URL url = new URL("https://selectpdf.com/api2/convert/?key=de3874e6-e576-4278-938d-01b9dabb40b4&url=www.cgm.com");
        HttpURLConnection connection =Convertor.createPDFFile(url,file);
        assertEquals(200,connection.getResponseCode());

    }
}