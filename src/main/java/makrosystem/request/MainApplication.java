package makrosystem.request;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class MainApplication extends Application {

    public static Label lbNome;
    public static Label lbPrice;
    public static Label lbDiscount;
    @Override
    public void start(Stage stage) throws Exception {
        Stage window = new Stage();

        Scrapp scrap = new Scrapp(); // Construtor

        //-----------------------

        Label lbTitle = new Label("Requisição ML");
        lbTitle.setStyle("-fx-font-size: 24px");

        TextField tx = new TextField();
        tx.setPromptText("Item a ser buscado");
        tx.setPrefWidth(150);
        tx.setOnAction(e -> scrap.validTag(tx.getText()));

        Button b = new Button("Read...");
        b.setPrefWidth(100);
        b.setOnAction( e -> {
            scrap.validTag(tx.getText());
        });

        lbNome = new Label();
        lbNome.setStyle("-fx-font-size: 14px");
        lbPrice = new Label();
        lbPrice.setStyle("-fx-font-size: 14px");
        lbDiscount = new Label();
        lbDiscount.setStyle("-fx-font-size: 14px");

        VBox box = new VBox(5);
        box.getChildren().addAll(lbTitle,tx,b,lbNome,lbPrice,lbDiscount);
        box.setAlignment(Pos.CENTER);
        box.setPadding(new Insets(0,30,0,30));
        Scene sc = new Scene(box,500,300);


        //-----------------------

        window.setTitle("Makro System, requisição web");
        window.setResizable(true);
        window.setScene(sc);
        setUserAgentStylesheet(STYLESHEET_CASPIAN);
        window.show();
    }



    public static void main(String[] args) {
        launch();
    }
}