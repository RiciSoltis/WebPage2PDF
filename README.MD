WebPage2PDF

Convertor of web page to PDF format via REST API call.
I am using https://selectpdf.com/ API to perform conversion.
Project consist of runnable bat file (Windows) to run whole process via script file.

Script running:  java -cp WebPage2PDF.jar Convertor -ak LICENSE_KEY -url WEB_PAGE_TO_CONVERT
Preconditions: you have to generate your own LICENSE_KEY for authorization. Link to generate LICENSE_KEY https://selectpdf.com/api/RequestKey.aspx

Future upgrade

Accept cookies in web browsers to delete those cookies in output.
