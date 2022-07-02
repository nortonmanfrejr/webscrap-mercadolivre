import com.gargoylesoftware.htmlunit.html.HtmlElement;
import model.Scrapp;

import java.io.IOException;
import java.util.Scanner;


public class MainApplication{


    public static void main(String[] args) throws IOException {

       Scrapp sc = new Scrapp();

       sc.validTag("monitor");

    }


}
