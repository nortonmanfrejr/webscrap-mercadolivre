module makrosystem.request {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires com.fasterxml.jackson.databind;
    requires htmlunit;

    opens makrosystem.request to javafx.fxml;
    exports makrosystem.request;
}