package model;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.util.ArrayList;

public class Scrapp {

    private static final String mlURL = "https://lista.mercadolivre.com.br/";
    private WebClient client;


    public Scrapp(){
        client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }


    private ArrayList<Produto> validTag(HtmlElement item) {

        String pageURL = mlURL + item;

        try {

            HtmlPage page = client.getPage(pageURL);

            String xPathAddr = "//ol[@class='ui-search-layout ui-search-layout--stack']";

            if(item.getFirstByXPath(xPathAddr)) {

                ArrayList<Produto> items = new ArrayList<Produto>(item.getByXPath("//li*[@class='ui-search-layout__item']"));

                for(int i = 0; i < items.size(); i++){

                    Produto newItem = new Produto(

                    );

                    ArrayList<Produto> itemArray = new ArrayList<>();
                    itemArray.add(newItem);
                    return itemArray;
                }

            } else {


            }

        } catch (IOException e) {

            System.out.println(e);

        }

        return null;
    }

    private static String getName(HtmlElement item){

        String xPathAddr = "//h2[@class='ui-search-item__title']";
        HtmlElement itemName = (HtmlElement) item.getFirstByXPath(xPathAddr);
        return (itemName)
    }

}
