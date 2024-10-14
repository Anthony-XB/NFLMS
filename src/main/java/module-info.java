module com.example.nflms {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires static lombok;


    // Open the package containing Menu to allow JavaFX reflection access
    opens com.CEN30241.nflms.Controllers to javafx.graphics, javafx.fxml;

    // Export the main package
    exports com.CEN30241.nflms;
}