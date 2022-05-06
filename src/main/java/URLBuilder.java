/**
 * Class to handle url creating
 */
public class URLBuilder {

    /**
     * method to create url call for api
     * @param api object API with license key, api url and url to convert
     * @return prepared url to call
     */
    public static String prepareUrl(Api api){
        return api.getApiURL() + "?" + "key=" +
                api.getLicenseKey() + "&url=" + api.getUrlToConvert();
    }
}
