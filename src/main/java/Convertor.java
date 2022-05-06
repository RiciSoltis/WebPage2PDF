import org.apache.commons.cli.*;

import java.io.*;
import java.net.*;


/**
 * @author Rihchard Soltis
 * date 06.05.2022
 */
public class Convertor {

    /**
     * URL of api endpoint
     */
    private static final String API_URL = "https://selectpdf.com/api2/convert/";

    public static void main(String[] args) throws IOException {
        CommandLine commandLine = parseArguments(args);

        String licenseKey = commandLine.getOptionValue("apiKey");
        String url = commandLine.getOptionValue("url");

        String regexForPageName = url.split("\\.")[1].split("\\.")[0];
        File localFile = new File(regexForPageName +".pdf");

        Api api = new Api(API_URL,url,licenseKey);
        String endpointToCall = URLBuilder.prepareUrl(api);
        URL endpoint = new URL(endpointToCall);


        createPDFFile(endpoint, localFile);

        System.out.println(api.getUrlToConvert()+" pdf document generated successfully!");


    }


    /**
     * @param endpoint url to call
     * @param localFile file to save response from api
     * @return urlConnection for testing output
     * @throws IOException exception if api call return error
     */
    public static HttpURLConnection createPDFFile(URL endpoint, File localFile) throws IOException {
        HttpURLConnection urlConnection = null;
         try {
            urlConnection = (HttpURLConnection)endpoint.openConnection();
            BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());

            BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(localFile));

            byte[] b = new byte[8 * 1024];
            int read;
            while ((read = inputStream.read(b)) > -1) {
                outputStream.write(b, 0, read);
            }
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }
        catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());

            if (urlConnection != null) {
                if (urlConnection.getResponseCode() != 200) {
                    System.out.println("HTTP Response Code: " + urlConnection.getResponseCode());
                    System.out.println("HTTP Response Message: " + urlConnection.getResponseMessage());
                }
            }
        }

        return urlConnection;
    }

    /**
     * method to parse input arguments of program
     * @param args input arguments
     * @return parsed arguments
     */
    private static CommandLine parseArguments(String[] args) {
        CommandLine commandLine = null;
        Options options = new Options();

        Option apiKey = new Option("aK", "apiKey", true, "license key for api");
        apiKey.setRequired(true);
        options.addOption(apiKey);

        Option urlToConvert = new Option("url", "urlToConvert", true, "urlToConvert file");
        urlToConvert.setRequired(true);
        options.addOption(urlToConvert);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();


        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("utility-name", options);

            System.exit(1);
        }

        if(args.length > 4){
            System.err.println("Unsupported number number of the arguments.");
            System.exit(1);
        }
        return commandLine;
    }

}
