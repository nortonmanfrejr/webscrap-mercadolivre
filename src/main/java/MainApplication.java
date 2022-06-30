import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.List;


import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class MainApplication{


    public static void main(String[] args) throws IOException {


        Scanner sc = new Scanner(System.in);

        System.out.println("Makro System...\nRequisição de Produtos Mercado Livre.");


        System.out.println("Insira o nome do produto a ser pesquisado: ");
        String produto = sc.next();
        catchFirst(produto);


    }

    /**
     * @param searchItem nome do item a ser buscado;
     * */
    private static void catchFirst(String searchItem){

        // Instancia do client

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        // URL a ser raspada e termos de envio e requisição
        String searchUrl = "https://lista.mercadolivre.com.br/" + searchItem;

        try {

            HtmlPage page = client.getPage(searchUrl);

            List<HtmlElement> items = page.getByXPath("//ol[@class='ui-search-layout ui-search-layout--stack']");
//            List<HtmlElement> desconto = page.getByXPath()


            /**
             * Validação para caso não tenha sido encontrado items
             * */
            if(!items.isEmpty()){

                /**
                 * Percorrendo a lista para capturar o primeiro item.
                 * */

                for(HtmlElement htmlItem : items) {


                    if(!items.contains(page.getFirstByXPath("//s[@class='price-tag ui-search-price__part ui-search-price__original-value price-tag__disabled']"))){

                        HtmlElement nome = (HtmlElement) htmlItem.getFirstByXPath("//h2[@class='ui-search-item__title']");
                        HtmlElement price = (HtmlElement) htmlItem.getFirstByXPath("//span[@class='price-tag-fraction']");
//                        HtmlElement desconto = (HtmlElement) htmlItem.getByXPath();

                        String itemName = nome.asNormalizedText(); // Transformando em texto
                        String itemPrice = price == null ? "0.0" : price.asNormalizedText();
//                        String itemDesconto = desconto == null ? "0.0" : desconto.asNormalizedText();
                        // Print

                        System.out.printf("Nome do Produto: %s\nPreço do Produto: %s\n",itemName,itemPrice);

                        break;
                    }

                    HtmlElement nome = (HtmlElement) htmlItem.getFirstByXPath("//h2[1][@class='ui-search-item__title']");
                    HtmlElement price = (HtmlElement) htmlItem.getFirstByXPath("//span[1][@class='price-tag-fraction']");

                    String itemName = nome.asNormalizedText(); // Transformando em texto
                    String itemPrice = price == null ? "0.0" : price.asNormalizedText();

                    // Print

                    System.out.printf("Nome do Produto: %s\nPreço do Produto: %s\n",itemName,itemPrice);

                }

            }

        } catch (Exception e) {

            e.printStackTrace();

        }
    }


}
