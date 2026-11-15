module com.mycompany.assigment_2 {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.mycompany.assigment_2 to javafx.fxml;
    exports com.mycompany.assigment_2;
}
