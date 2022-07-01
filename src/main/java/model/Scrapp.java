package model;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class Scrapp {

    private static final String mlURL = "https://lista.mercadolivre.com.br/";
    private WebClient client;


    public Scrapp(){
        client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);
    }


    public ArrayList<Produto> validTag(String itemURL) {

        ArrayList<Produto> itemArray = new ArrayList<>();
        ArrayList<String> teste = new ArrayList<>();

        try {
            String pageURL = mlURL + URLEncoder.encode(itemURL,"UTF-8");
            HtmlPage page = client.getPage(pageURL);


            String xPathAddr = "//ol[@class='ui-search-layout ui-search-layout--stack']";
            List<?> itemResult = (List<?>) page.getByXPath(xPathAddr);



            if(!itemResult.contains(xPathAddr)) {

                for(int i = 0; i < itemResult.size(); i++){

                    HtmlElement item = (HtmlElement) itemResult.get(i);

                    String produtoNome = getName(item) == null ? "Sem nome" : getName(item);
                    String produtoPreco = getPrice(item) == null ? "Sem preço" : getPrice(item);
                    String produtoDiscount = getDiscount(item) == null ? "Sem desconto" : getDiscount(item);

                    Produto newItem = new Produto(
                            produtoNome,
                            produtoPreco,
//                            produtoDiscount
                            "0,0"
                    );

                    System.out.println(produtoNome);
                    System.out.println(produtoPreco);
                    System.out.println(produtoDiscount);

                    itemArray.add(newItem);

                    return itemArray;
                }

            } else {

                System.out.println("algum");

            }

        } catch (IOException e) {

            System.out.println(e);

        }

        return itemArray;
    }

    private static String getName(HtmlElement item){

        String xPathAddr = "//h2[@class='ui-search-item__title']";
        HtmlElement itemName = (HtmlElement) item.getFirstByXPath(xPathAddr);
        return (itemName == null) ? "null" : itemName.asNormalizedText();
    }

    private static String getPrice(HtmlElement item){
        String xPathAddr = "//span[@class='price-tag-amount']";
        HtmlElement itemPrice = (HtmlElement) item.getFirstByXPath(xPathAddr);
        return  (itemPrice == null) ? "null" : itemPrice.asNormalizedText();
    }

    private static String getDiscount(HtmlElement item) {
        String xPathAddr = "//span[2][@class='price-tag-amount']";
        HtmlElement itemDiscount = (HtmlElement) item.getFirstByXPath(xPathAddr);
        return  (itemDiscount == null) ? "null" : itemDiscount.asNormalizedText();
    }

}

