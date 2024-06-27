module org.m0d3rn1ca.restaurantcrm {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens org.m0d3rn1ca.restaurantcrm to javafx.fxml;
    exports org.m0d3rn1ca.restaurantcrm;
    exports org.m0d3rn1ca.restaurantcrm.controllers;
    opens org.m0d3rn1ca.restaurantcrm.controllers to javafx.fxml;
}