module org.jide.studentgradingsystem {
    requires javafx.controls;
    requires javafx.fxml;
    exports org.jide.studentgradingsystem.gui to javafx.graphics;
    opens org.jide.studentgradingsystem.console to javafx.base;


    opens org.jide.studentgradingsystem to javafx.fxml;
    exports org.jide.studentgradingsystem;
}