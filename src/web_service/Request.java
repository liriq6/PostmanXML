package web_service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.nio.charset.Charset;

public class Request {

    private static String body;
    private static String bodyPath;
    private static String endPoint;
    private static HttpURLConnection httpConn;
    private static byte[] buffer;

    public Request(String bodyPath, String endPoint) {
        this.bodyPath = bodyPath;
        this.endPoint = endPoint;
    }

    public static String init() {
        xmlParser parser = new xmlParser();
        body = parser.xmlParser();
        return null;
    }

    public void action() {
        try {
            httpConn = (HttpURLConnection) new URL(endPoint).openConnection();
            buffer = (body).getBytes();
            httpConn.setRequestProperty("Content-lenght", String.valueOf(buffer.length));
            httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");

            try {
                httpConn.setRequestMethod("POST");
            } catch (ProtocolException e) {
                e.printStackTrace();
            }
            httpConn.setDoOutput(true);
            httpConn.setDoInput(true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return;
    }


    public static String end() {
        OutputStream out = null;
        try {
            out = httpConn.getOutputStream();
            out.write(buffer);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String result = "";
        System.out.println(result);
        try {
            InputStreamReader isr = new InputStreamReader(httpConn.getInputStream(), Charset.forName("UTF-8"));
            BufferedReader in = new BufferedReader(isr);
            String responce = "";

            while ((responce = in.readLine()) != null) {
                result += responce;
            }
            isr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        httpConn.disconnect();
        return null;
    }
}
