/**
 * Api POJO
 */
public class Api {

    private final String apiURL;
    private final String urlToConvert;
    private final String licenseKey;

    public Api(String apiURL, String urlToConvert, String licenseKey) {
        this.apiURL = apiURL;
        this.urlToConvert = urlToConvert;
        this.licenseKey = licenseKey;
    }

    public String getApiURL() {
        return apiURL;
    }


    public String getUrlToConvert() {
        return urlToConvert;
    }



    public String getLicenseKey() {
        return licenseKey;
    }



}
