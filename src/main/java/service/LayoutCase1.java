package service;

import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import java.util.List;

public class LayoutCase1 {

    public static void catchFirst(String produto){

        // Instancia do client

        WebClient client = new WebClient();
        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        // URL a ser raspada e termos de envio e requisição
        String searchUrl = "https://lista.mercadolivre.com.br/" + produto;

        try {

            HtmlPage page = client.getPage(searchUrl);

            List<HtmlElement> items = page.getByXPath("//ol[@class='ui-search-layout ui-search-layout--stack']");

            if(!items.isEmpty()) {

                for (HtmlElement item : items) {

                    HtmlElement nome = item.getFirstByXPath("//h2[@class='ui-search-item__title']");
                    HtmlElement preco = item.getFirstByXPath("//span[@class='price-tag-amount']");

                    String nomeProduto = nome == null ? "Produto sem nome." : nome.asNormalizedText();
                    String precoProduto = preco == null ? "R$0.0" : preco.asNormalizedText();

                    System.out.println("Nome Produto: " + nomeProduto);
                    System.out.println("Preço Produto: " + precoProduto);

                    // Verificar a existencia de um desconto

                    HtmlElement divDesconto = item.getFirstByXPath("//div[@class='ui-search-price__second-line']");

                    if(!items.contains(divDesconto)) {

                        HtmlElement desconto = divDesconto.getFirstByXPath("span[1]/span[2]");
                        String descontoPrice = desconto == null ? "R$0.0" : desconto.asNormalizedText();
                        System.out.println("Desconto: " + descontoPrice);

                    }

                }

            } else {

                System.out.println("Produto não encontrado.");
            }
        } catch (Exception e) {

            e.printStackTrace();

        }
    }
}
