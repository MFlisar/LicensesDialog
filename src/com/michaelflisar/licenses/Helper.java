
package com.michaelflisar.licenses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class Helper
{
    public static String readFile(String link)
    {
        try
        {
            // Create a URL for the desired page
            URL url = new URL(link);

            // Read all the text returned by the server
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            String text = "";
            String str;
            while ((str = in.readLine()) != null)
                text += str + "\n";
            in.close();
            return text;
        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return e.getMessage();
        }
    }
}
