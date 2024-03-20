package application;
	
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;


public class Main extends Application {
	@Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setVgap(10);
        gridPane.setHgap(10);

        // Labels
        Label nameLabel = new Label("Name:");
        Label addressLabel = new Label("Address:");
        Label cityLabel = new Label("City:");
        Label provinceLabel = new Label("Province:");
        Label postalCodeLabel = new Label("Postal Code:");
        Label phoneLabel = new Label("Phone Number:");
        Label emailLabel = new Label("Email:");
        Label majorLabel = new Label("Major:");
        Label courseLabel = new Label("Course:");
        

        // Text Fields
        TextField nameField = new TextField();
        TextField addressField = new TextField();
        TextField cityField = new TextField();
        TextField provinceField = new TextField();
        TextField postalCodeField = new TextField();
        TextField phoneField = new TextField();
        TextField emailField = new TextField();

        // Radio Buttons
        ToggleGroup majorToggleGroup = new ToggleGroup();
        RadioButton csRadioButton = new RadioButton("Computer Science");
        csRadioButton.setToggleGroup(majorToggleGroup);
        RadioButton businessRadioButton = new RadioButton("Business");
        businessRadioButton.setToggleGroup(majorToggleGroup);

        // Combo Box
        ComboBox<String> courseComboBox = new ComboBox<>();
        courseComboBox.getItems().addAll("", "", "");

        // List Box
        ListView<String> courseListBox = new ListView<>();

        

        // Text Area
        TextArea displayArea = new TextArea();
        displayArea.setEditable(false);

        // Add components to grid pane
        gridPane.add(nameLabel, 0, 0);
        gridPane.add(nameField, 1, 0);
        gridPane.add(addressLabel, 0, 1);
        gridPane.add(addressField, 1, 1);
        gridPane.add(cityLabel, 0, 2);
        gridPane.add(cityField, 1, 2);
        gridPane.add(provinceLabel, 0, 3);
        gridPane.add(provinceField, 1, 3);
        gridPane.add(postalCodeLabel, 0, 4);
        gridPane.add(postalCodeField, 1, 4);
        gridPane.add(phoneLabel, 0, 5);
        gridPane.add(phoneField, 1, 5);
        gridPane.add(emailLabel, 0, 6);
        gridPane.add(emailField, 1, 6);
        gridPane.add(majorLabel, 0, 7);
        gridPane.add(csRadioButton, 1, 7);
        gridPane.add(businessRadioButton, 1, 8);
        gridPane.add(courseLabel, 0, 9);
        gridPane.add(courseComboBox, 1, 9);
        gridPane.add(courseListBox, 1, 10);
        
        

        // Add event handlers
        courseComboBox.setOnAction(event -> {
            String selectedCourse = courseComboBox.getValue();
            if (!courseListBox.getItems().contains(selectedCourse)) {
                courseListBox.getItems().add(selectedCourse);
            }
        });

        majorToggleGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == csRadioButton) {
                courseComboBox.getItems().setAll("Java", "Python", "C#");
            } else if (newValue == businessRadioButton) {
                courseComboBox.getItems().setAll("Fundamental of Business", "Principle of Marketing", "Mathematics of Finance");
            }
        });

        Button submitButton = new Button("Display");
        submitButton.setOnAction(event -> {
            StringBuilder studentInfo = new StringBuilder();
            studentInfo.append("Name: ").append(nameField.getText()).append("\n");
            studentInfo.append("Address: ").append(addressField.getText()).append("\n");
            studentInfo.append("City: ").append(cityField.getText()).append("\n");
            studentInfo.append("Province: ").append(provinceField.getText()).append("\n");
            studentInfo.append("Postal Code: ").append(postalCodeField.getText()).append("\n");
            studentInfo.append("Phone Number: ").append(phoneField.getText()).append("\n");
            studentInfo.append("Email: ").append(emailField.getText()).append("\n");
            studentInfo.append("Major: ").append(majorToggleGroup.getSelectedToggle() != null ?
                    ((RadioButton) majorToggleGroup.getSelectedToggle()).getText() : "").append("\n");
            studentInfo.append("Courses:\n");
            courseListBox.getItems().forEach(course -> studentInfo.append("- ").append(course).append("\n"));
            
            

            displayArea.setText(studentInfo.toString());
        });

        VBox vBox = new VBox(gridPane, submitButton);
        root.setCenter(vBox);
        root.setBottom(displayArea);

        Scene scene = new Scene(root, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Student Information App");
        primaryStage.show();
    }

	
	public static void main(String[] args) {
		launch(args);
	}
}
