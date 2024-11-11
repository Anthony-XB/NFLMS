module NFLMS {

//    module com.CEN30241.nflms {

    requires static lombok;
    requires java.desktop;


    requires java.sql;
    requires com.fasterxml.jackson.databind;
//    requires javafx.graphics;
//    requires javafx.controls;
//    requires javafx.fxml;
//    requires javafx.base;


    exports com.CEN30241.nflms.Controllers;


    opens com.CEN30241.nflms.Controllers to javafx.fxml;


    opens com.CEN30241.nflms to javafx.fxml;


    exports com.CEN30241.nflms;

}

//}