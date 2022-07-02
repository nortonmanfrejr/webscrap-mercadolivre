import com.gargoylesoftware.htmlunit.html.HtmlElement;
import model.Scrapp;

import java.io.IOException;
import java.util.Scanner;


public class MainApplication{


    public static void main(String[] args) throws IOException {

        Scrapp scrapp = new Scrapp();

        System.out.println("GRID LAYOUT");
        System.out.println(scrapp.validTag("Monitor"));

        System.out.println("STACK LAYOUT");
        System.out.println(scrapp.validTag("Samsung"));

    }


}
