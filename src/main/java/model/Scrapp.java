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


            String xPathAddr = "//*[@id='root-app']/div/div[2]/section/ol/li[1]";
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

    /**
     * Função que pega o nome do produto através do xPath, caso o primeiro produto retornar nulo,
     * ele retorna o primeiro produto da  GRID.
     *
     * @param item a ser buscado.
     * @return o nome do produto.
     * */
    private static String getName(HtmlElement item){

        String xPathAddr = "/html/body/main/div/div[2]/section/ol/li[1]/div/div/div[2]/div[2]/a[1]/h2";
        HtmlElement itemName = (HtmlElement) item.getFirstByXPath(xPathAddr);
        String x = (itemName == null) ? "null" : itemName.asNormalizedText();

        if(x.equals("null")){

            xPathAddr = "//*[@id=\'root-app\']/div/div[2]/section/ol[1]/li[1]/div/div/a/div/div[5]/h2";
            itemName = (HtmlElement) item.getFirstByXPath(xPathAddr);

            return (itemName == null) ? "null" : itemName.asNormalizedText();
        }

        return x;
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

        String xPathAddr = "//*[@id=\'root-app\']/div/div[2]/section/ol/li[1]/div/div/div[2]/div[3]/div[1]/div[1]/div/div/div/span[1]/span[2]/span[2]";
        HtmlElement itemDiscount = (HtmlElement) item.getFirstByXPath(xPathAddr);
        String x = (itemDiscount == null) ? "null" : itemDiscount.asNormalizedText();

        if(x.equals("null")) {

            xPathAddr = "//*[@id=\'root-app\']/div/div[2]/section/ol[1]/li[1]/div/div/a/div/div[2]/div/div/div/span[1]/span[2]";
            itemDiscount = (HtmlElement)  item.getFirstByXPath(xPathAddr);

            return (itemDiscount == null) ? "null" : itemDiscount.asNormalizedText();
        }

        return  x;
    }

}

