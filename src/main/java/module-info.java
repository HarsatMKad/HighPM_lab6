module org.example.highpm_lab6ver2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    opens org.example.highpm_lab6ver2 to javafx.fxml;
    exports org.example.highpm_lab6ver2;
}