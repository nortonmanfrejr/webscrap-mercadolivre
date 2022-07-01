import java.io.IOException;
import java.util.Scanner;


public class MainApplication{


    public static void main(String[] args) throws IOException {


        Scanner sc = new Scanner(System.in);

        System.out.println("Makro System...\nRequisição de Produtos Mercado Livre.");


        System.out.println("Insira o nome do produto a ser pesquisado: ");
        String produto = "Samsung";
        LayoutCase1.catchFirst(produto);



    }


}
