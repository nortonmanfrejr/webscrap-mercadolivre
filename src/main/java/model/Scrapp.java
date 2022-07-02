package model;

import com.fasterxml.jackson.databind.ObjectMapper;
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

        try {
            String pageURL = mlURL + URLEncoder.encode(itemURL,"UTF-8");
            HtmlPage page = client.getPage(pageURL);


            String xPathAddr = "//li[1]";
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
                            produtoDiscount
                    );

                    ObjectMapper mapp = new ObjectMapper();
                    String jsonString = mapp.writeValueAsString(newItem);
                    System.out.println(jsonString);

                    itemArray.add(newItem);
                    return itemArray;
                }

            } else {

                return null;

            }

        } catch (IOException e) {

            System.out.println(e);

        }

        return itemArray;
    }

    /**
     * Função que pega o nome do produto através do xPath, caso o primeiro produto retornar nulo,
     * ele retorna o primeiro produto da  GRID.
     *
     * @param item a ser buscado.
     * @return o nome do produto.
     * */
    private static String getName(HtmlElement item){

        String xPathAddr = "//h2"; // Campo localizado o item
        HtmlElement itemName = (HtmlElement) item.getFirstByXPath(xPathAddr);

        return  (itemName == null) ? "null" : itemName.asNormalizedText(); // Enviando nome.
    }

    /**
     * Função que pega o preco do produto através do xPath, caso o primeiro produto retornar nulo,
     * ele retorna o primeiro produto da GRID.
     *
     * @param item a ser buscado.
     * @return o preco do produto.
     * */
    private static String getPrice(HtmlElement item){

        String xPathAddr = "//span[@class='price-tag-amount']";
        HtmlElement itemPrice = (HtmlElement) item.getFirstByXPath(xPathAddr);

        return  (itemPrice == null) ? "null" : itemPrice.asNormalizedText();
    }

    /**
     * Função que pega o desconto do produto através do xPath, caso o primeiro produto retornar nulo,
     * ele retorna o primeiro produto da  GRID.
     *
     * @param item a ser buscado.
     * @return o desconto do produto.
     * */
    private static String getDiscount(HtmlElement item) {

        String divPath = "//s[@div='price-tag ui-search-price__part ui-search-price__original-value price-tag__disabled']";
        List<?> discountResult = (List<?>) item.getByXPath(divPath);

        if(!discountResult.contains(divPath)) {

            String xPathAddr = "//span[@class='price-tag ui-search-price__part']/span[2]";
            HtmlElement discount = (HtmlElement) item.getFirstByXPath(xPathAddr);
            return (discount == null) ? "Sem desconto!!" : discount.asNormalizedText();
        } else {

            return "erro";
        }

    }


}

