package spike_Swing_FX;

import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.text.Font;
import javafx.geometry.Insets;
import javafx.scene.control.TableView;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.Group;
import javafx.scene.control.TextField;

 
public class FX extends Application {
 
    private TableView<employee> table = new TableView<employee>();
    private final ObservableList<employee> data =
        FXCollections.observableArrayList(
            new employee("Dineth", "Smith", "CEO", "606,900"),
            new employee("Menna", "Ghanem", "CMO", "10,8243"),
            new employee("Abdelrahman", "Ghanem", "administrative assistant", "43,124"));
   
    public static void main(String[] args) {
        launch(args);
    }
 
    public void start(Stage stage) {
        Scene scene = new Scene(new Group());
        stage.setTitle("Table View Sample");
        stage.setWidth(600);
        stage.setHeight(500);
 
        Label lbl = new Label("Table of Employees in a Company");
        lbl.setFont(new Font("Verdana", 20));
 
        table.setEditable(true);
 
        TableColumn firstNameColumn = new TableColumn("First Name");
        firstNameColumn.setMinWidth(245);
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<employee, String>("firstName"));
 
        TableColumn lastNameColumn = new TableColumn("Last Name");
        lastNameColumn.setMinWidth(245);
        lastNameColumn.setCellValueFactory(new PropertyValueFactory<employee, String>("lastName"));
 
        TableColumn positionColumn = new TableColumn("Position");
        positionColumn.setMinWidth(245);
        positionColumn.setCellValueFactory(new PropertyValueFactory<employee, String>("position"));
		
		TableColumn salaryColumn = new TableColumn("Salary");
        salaryColumn.setMinWidth(245);
        salaryColumn.setCellValueFactory(new PropertyValueFactory<employee, String>("salary"));
 
        table.setItems(data);
        table.getColumns().addAll(firstNameColumn, lastNameColumn, positionColumn, salaryColumn);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(5);
        vbox.setPadding(new Insets(20, 1, 1, 20));
        vbox.getChildren().addAll(lbl, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        stage.setScene(scene);
        stage.show();
    }
 
    public static class employee {

        private final SimpleStringProperty firstName;
        private final SimpleStringProperty lastName;
        private final SimpleStringProperty position;
		private final SimpleStringProperty salary;
 
        private employee(String firstNameIn, String lastNameIn, String positionIn, String salaryIn) {
            this.firstName = new SimpleStringProperty(firstNameIn);
            this.lastName = new SimpleStringProperty(lastNameIn);
            this.position = new SimpleStringProperty(positionIn);
			this.salary = new SimpleStringProperty(salaryIn);
        }
    }
} 