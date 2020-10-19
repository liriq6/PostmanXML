package web_service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class xmlParser {

    public static String xmlParser() {
        String body = null;
        try (BufferedReader br = new BufferedReader(new FileReader("XML.xml"))) {
            String s;
            while ((s = br.readLine()) != null) {
                body=body+s;
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        return body;
    }
}