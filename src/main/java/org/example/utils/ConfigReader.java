package org.example.utils;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigReader {

    private static String  reader (String key){
        Properties properties = new Properties();
        try {
            InputStream inputStream = Files.newInputStream(Paths.get("config.properties"));
            properties.load(inputStream);
        } catch (IOException e) {
            System.out.println("error!" + e.getMessage());
        }
        return properties.getProperty(key);
    }



     public static String get(String key){
        return reader(key);
     }

     public static long getlong(String key){
        return Long.parseLong(reader(key));
     }


    }
