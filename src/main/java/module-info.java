module com.ntic.selfdrivingcarsimulator {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.ntic.selfdrivingcarsimulator to javafx.fxml;
    exports com.ntic.selfdrivingcarsimulator;
    exports com.ntic.selfdrivingcarsimulator.controller;
    opens com.ntic.selfdrivingcarsimulator.controller to javafx.fxml;
}