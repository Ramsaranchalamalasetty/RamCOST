package com.cost.utils;

import com.cost.factory.Pages;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class Helpers extends Pages {
    protected static Properties prop;
    public Properties loadPropertiesFiles(String file) {
        try {
            prop = new Properties();
            InputStream propertyFile;
            propertyFile = new FileInputStream(
                    System.getProperty( "user.dir" ) + "/src/test/resources/config/" + file + ".properties" );
            prop.load( propertyFile );
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return prop;
    }


}
