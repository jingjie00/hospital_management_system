import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

/*
  __  _  _  ____   __  ____  ____  __   __ _  ____ 
 (  )( \/ )(  _ \ /  \(  _ \(_  _)/ _\ (  ( \(_  _)    *********************************************************  
  )( / \/ \ ) __/(  O ))   /  )( /    \/    /  )(      *         Please run by resolution with 100%            *
 (__)\_)(_/(__)   \__/(__\_) (__)\_/\_/\_)__) (__)     *********************************************************
 
 ** Person class have verify duplicate id
 ** Medical, Lab, Facility not have Id
 
 */

public class GraphicalUserInterface extends Application {

	// Object_Scence *************************************************
	static Scene doctor_Scene;
	static Scene staff_Scene;
	static Scene patient_Scene;
	static Scene lab_Scene;
	static Scene medical_Scene;
	static Scene facility_Scene;
	static Scene menu_Scene;

	// Main flow ******************************************************

	@Override
	public void start(Stage primaryStage) {
		// Error hander - Check image existence
		String imagesName[] = { "Doctor", "Staff", "Patient", "Medical", "Lab", "Facility", "WelcomePage", "Icon" };
		for (int i = 0; i < imagesName.length; i++) {
			try {
				URL imgUrl = getClass().getResource("Images/" + imagesName[i] + ".png");

				if (imgUrl == null) {
					JOptionPane.showMessageDialog(null, "Error\nMissing images", "Error", JOptionPane.ERROR_MESSAGE);
					System.exit(1);
				} else {
					ImageIO.read(imgUrl);
				}
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, "Error\nMissing images", "Error", JOptionPane.ERROR_MESSAGE);
				System.exit(1);
			}
		}

		// Object_Scence
		doctor_Scene = doctor_Scene(primaryStage);
		staff_Scene = staff_Scene(primaryStage);
		patient_Scene = patient_Scene(primaryStage);
		medical_Scene = medical_Scene(primaryStage);
		lab_Scene = lab_Scene(primaryStage);
		facility_Scene = facility_Scene(primaryStage);

		// For Close
		primaryStage.setOnCloseRequest(e -> {
			String[] option = { "Yes", "No" };
			int selection = JOptionPane.showOptionDialog(null, "Are you sure want to close", null,
					JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, option, option[0]);
			if (selection == 0)
				System.out.println("Thank you for using our program.");
			else
				e.consume();
		});

		// Menu Scene and start page
		menu_Scene = menu_scene(primaryStage);
		startPage(primaryStage);

		// For title
		primaryStage.getIcons().add(new Image("Images//Icon.png"));
		primaryStage.setTitle("Hospital Management System");
		primaryStage.setX(Screen.getPrimary().getVisualBounds().getMinX());
		primaryStage.setY(Screen.getPrimary().getVisualBounds().getMinY());
		primaryStage.setWidth(Screen.getPrimary().getVisualBounds().getWidth());
		primaryStage.setHeight(Screen.getPrimary().getVisualBounds().getHeight());
		primaryStage.setMaximized(true);
		primaryStage.setResizable(true);
		primaryStage.show();
	}

	public static void guiMain() {
		launch();
	}

	public Button back(Stage primaryStage) {
		Button back = button_self("< Back To Menu", "PINK", "GREY");
		back.setOnAction(e -> {
			primaryStage.setScene(menu_Scene);
		});
		back.setOnKeyPressed(e -> {
			if (e.getCode().equals(KeyCode.ENTER))
				primaryStage.setScene(menu_Scene);
		});
		return back;
	}

	// Repeated Object Scene
	// ***************************************************************************

	public Scene doctor_Scene(Stage primaryStage) {
		String table_head[] = { "Id", "Name", "Specialist", "Work Time", "Qualification", "Room" };
		Button button[] = button_collection();
		BorderPane borderPane = new BorderPane();
		GridPane gridPane_center = new GridPane();
		object_general(primaryStage, borderPane, gridPane_center, button, "Doctor", table_head,
				HospitalManagement.doctors, 1);
		TextField text1 = inputString("Enter Doctor Id");
		TextField text2 = inputString("Enter Doctor Name");
		TextField text3 = inputString("Enter Specialist");

		HBox group = new HBox();

		Spinner<Integer> spinner1 = new Spinner<Integer>(1, 12, 8);
		Spinner<Integer> spinner2 = new Spinner<Integer>(1, 12, 8);
		Spinner<String> spinner3 = new Spinner<>(1, 2, 1);
		ObservableList<String> times = FXCollections.observableArrayList("PM", "AM");
		SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(times);
		valueFactory.setValue("AM");
		spinner3.setValueFactory(valueFactory);

		spinner1.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
		spinner2.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
		spinner3.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
		HBox hbox = new HBox();
		hbox.getChildren().add(new Text(" - "));
		hbox.setPrefSize(10, 20);
		hbox.setAlignment(Pos.CENTER);
		group.getChildren().addAll(spinner1, hbox, spinner2, new Text("   "), spinner3);

		spinner1.setPrefSize(60, 20);
		spinner2.setPrefSize(60, 20);
		spinner3.setPrefSize(60, 20);
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().add("MBBS, MD");
		comboBox.getItems().add("MBBS, MS");
		comboBox.getSelectionModel().select(1);
		comboBox.setEditable(true);
		comboBox.setPrefSize(300, 40);
		comboBox.setPromptText("Enter Qualification (can be none)");//for intern people
		comboBox.setValue("");
		TextField text6 = inputInt("Enter Room Number");

		// Modify
		button[2].setOnAction(e -> {
			borderPane.setRight(button[4]);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.doctors, false, true, -1, 1);

		});

		// sort
		button[0].setOnAction(e -> {
			borderPane.setRight(null);
			HospitalParent.sort(HospitalManagement.doctors, null, null, null);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.doctors, false, false, -1, 1);
		});

		// add
		button[1].setOnAction(e -> {
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.doctors, false, false, -1, 1);
			GridPane gridPane_right = new GridPane();
			gridPane_right.add(header("ADD", 3), 1, 0, 4, 1);
			gridPane_right.add(label("Id"), 1, 1);
			gridPane_right.add(label(":"), 2, 1);
			gridPane_right.add(text1, 3, 1);
			gridPane_right.add(new Text(""), 4, 1);

			gridPane_right.add(label("Name"), 1, 2);
			gridPane_right.add(label(": Dr."), 2, 2);
			gridPane_right.add(text2, 3, 2);
			gridPane_right.add(new Text(""), 4, 2);

			gridPane_right.add(label("Specialist"), 1, 3);
			gridPane_right.add(label(":"), 2, 3);
			gridPane_right.add(text3, 3, 3);
			gridPane_right.add(new Text(""), 4, 3);

			gridPane_right.add(label("WorkTime"), 1, 4);
			gridPane_right.add(label(":"), 2, 4);
			gridPane_right.add(group, 3, 4, 2, 1);

			gridPane_right.add(label("Qualificaton"), 1, 5);
			gridPane_right.add(label(":"), 2, 5);
			gridPane_right.add(comboBox, 3, 5);
			gridPane_right.add(new Text(""), 4, 5);

			gridPane_right.add(label("Room"), 1, 6);
			gridPane_right.add(label(":"), 2, 6);
			gridPane_right.add(text6, 3, 6);
			gridPane_right.add(new Text(""), 4, 6);

			gridPane_right.add(button[5], 1, 7, 2, 1);// Save
			gridPane_right.add(button[4], 3, 7, 2, 1);// Cancel

			gridPane_right.setHgap(10);
			gridPane_right.setVgap(30);

			VBox vbox_right = new VBox();
			vbox_right.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
			vbox_right.getChildren().addAll(gridPane_right);
			borderPane.setRight(vbox_right);
		});

		// save
		button[5].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text3.getText().trim().isEmpty() && !text6.getText().trim().isEmpty()) {

				try {
					Doctor temp = new Doctor(text1.getText(), text2.getText(), text3.getText(),
							spinner1.getValue() + "-" + spinner2.getValue() + spinner3.getValue(), comboBox.getValue(),
							Integer.parseInt(text6.getText()));

					if (!Person.duplicateCheck(HospitalManagement.doctors, temp)) {
						JOptionPane.showMessageDialog(null, "Duplicate Id");
						text1.setText("");
					} else {
						HospitalManagement.doctors.add(temp);
						borderPane.setRight(null);
						show(borderPane, gridPane_center, button, table_head, HospitalManagement.doctors, false, false,
								-1, 1);
						text1.setText("");
						text2.setText("");
						text3.setText("");
						comboBox.setValue("");
						text6.setText("");
					}
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Room number should be integer and less than 2147483647");
				}

			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});

		return new Scene(borderPane);
	}

	public void doctorModify(BorderPane borderPane, GridPane gridPane_center, String[] table_head, Button button[],
			int index, int num) {
		String[] ans = HospitalManagement.doctors.get(index).showInfo();
		TextField text1 = inputString("Enter Doctor Id");
		text1.setText(HospitalManagement.doctors.get(index).getId());
		text1.setEditable(false);
		TextField text2 = inputString(ans[1].substring(3));
		TextField text3 = inputString(ans[2]);

		HBox group = new HBox();

		Spinner<Integer> spinner1 = new Spinner<Integer>(1, 12, 0);
		Spinner<Integer> spinner2 = new Spinner<Integer>(1, 12, 0);
		Spinner<String> spinner3 = new Spinner<>(1, 2, 1);
		ObservableList<String> times = FXCollections.observableArrayList("PM", "AM");
		SpinnerValueFactory<String> valueFactory = new SpinnerValueFactory.ListSpinnerValueFactory<String>(times);
		valueFactory.setValue("AM");
		spinner3.setValueFactory(valueFactory);

		spinner1.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
		spinner2.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
		spinner3.getStyleClass().add(Spinner.STYLE_CLASS_SPLIT_ARROWS_VERTICAL);
		HBox hbox = new HBox();
		hbox.getChildren().add(new Text(" - "));
		hbox.setPrefSize(10, 20);
		hbox.setAlignment(Pos.CENTER);
		group.getChildren().addAll(spinner1, hbox, spinner2, new Text("   "), spinner3);

		spinner1.setPrefSize(60, 20);
		spinner2.setPrefSize(60, 20);
		spinner3.setPrefSize(60, 20);
		ComboBox<String> comboBox = new ComboBox<String>();
		comboBox.getItems().add("MBBS, MD");
		comboBox.getItems().add("MBBS, MS");
		comboBox.getSelectionModel().select(1);
		comboBox.setEditable(true);
		comboBox.setPrefSize(300, 40);
		comboBox.setPromptText(ans[4] + " (can be none)");//for intern people
		comboBox.setValue("");
		TextField text6 = inputInt(ans[5]);

		GridPane gridPane_right = new GridPane();
		gridPane_right.add(header("MODIFY", 3), 1, 0, 4, 1);
		gridPane_right.add(label("Id"), 1, 1);
		gridPane_right.add(label(":"), 2, 1);
		gridPane_right.add(text1, 3, 1);
		gridPane_right.add(new Text(""), 4, 1);

		gridPane_right.add(label("Name"), 1, 2);
		gridPane_right.add(label(": Dr."), 2, 2);
		gridPane_right.add(text2, 3, 2);
		gridPane_right.add(new Text(""), 4, 2);

		gridPane_right.add(label("Specialist"), 1, 3);
		gridPane_right.add(label(":"), 2, 3);
		gridPane_right.add(text3, 3, 3);
		gridPane_right.add(new Text(""), 4, 3);

		gridPane_right.add(label("WorkTime"), 1, 4);
		gridPane_right.add(label(":"), 2, 4);
		gridPane_right.add(group, 3, 4, 2, 1);

		gridPane_right.add(label("Qualificaton"), 1, 5);
		gridPane_right.add(label(":"), 2, 5);
		gridPane_right.add(comboBox, 3, 5);
		gridPane_right.add(new Text(""), 4, 5);

		gridPane_right.add(label("Room"), 1, 6);
		gridPane_right.add(label(":"), 2, 6);
		gridPane_right.add(text6, 3, 6);
		gridPane_right.add(new Text(""), 4, 6);

		gridPane_right.add(button[6], 1, 7, 2, 1);// Save
		gridPane_right.add(button[4], 3, 7, 2, 1);// Cancel

		gridPane_right.setHgap(10);
		gridPane_right.setVgap(30);

		VBox vbox_right = new VBox();
		vbox_right
				.setStyle("-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
		vbox_right.getChildren().addAll(gridPane_right);
		borderPane.setRight(vbox_right);

		button[6].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text3.getText().trim().isEmpty() && !text6.getText().trim().isEmpty()) {
				try {
					Doctor temp = new Doctor(text1.getText(), text2.getText(), text3.getText(),
							spinner1.getValue() + "-" + spinner2.getValue() + spinner3.getValue(), comboBox.getValue(),
							Integer.parseInt(text6.getText()));

					HospitalManagement.doctors.set(index, temp);
					borderPane.setRight(null);
					show(borderPane, gridPane_center, button, table_head, HospitalManagement.doctors, false, false, -1,
							1);
					text1.setText("");
					text2.setText("");
					text3.setText("");
					comboBox.setValue("");
					text6.setText("");
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Room number should be integer and less than 2147483647");
				}
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});

		borderPane.setRight(vbox_right);
	}

	public Scene staff_Scene(Stage primaryStage) {
		String table_head[] = { "Id", "Name", "Designation", "Sex", "Salary" };
		Button button[] = button_collection();
		BorderPane borderPane = new BorderPane();
		GridPane gridPane_center = new GridPane();
		object_general(primaryStage, borderPane, gridPane_center, button, "Staff", table_head,
				HospitalManagement.staffs, 2);
		TextField text1 = inputString("Enter Staff Id");
		TextField text2 = inputString("Enter Staff Name");
		TextField text3 = inputString("Enter Designation");
		ComboBox<Integer> comboBox = comboBox(150);
		comboBox.getSelectionModel().select(21);
		ToggleGroup sex = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setToggleGroup(sex);
		rb1.setSelected(true);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setToggleGroup(sex);
		HBox radiogroup = new HBox();
		radiogroup.getChildren().addAll(rb1, new Text("\t\t"), rb2);
		TextField text5 = inputInt("Enter Salary");

		// Modify
		button[2].setOnAction(e -> {
			borderPane.setRight(button[4]);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.staffs, false, true, -1, 2);

		});
		// sort
		button[0].setOnAction(e -> {
			borderPane.setRight(null);
			HospitalParent.sort(HospitalManagement.staffs, null, null, null);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.staffs, false, false, -1, 2);
		});

		// add
		button[1].setOnAction(e -> {
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.staffs, false, false, -1, 2);
			GridPane gridPane_right = new GridPane();
			gridPane_right.add(header("ADD", 3), 1, 0, 4, 1);
			gridPane_right.add(label("Id"), 1, 1);
			gridPane_right.add(label(":"), 2, 1);
			gridPane_right.add(text1, 3, 1);
			gridPane_right.add(new Text(""), 4, 1);

			gridPane_right.add(label("Name"), 1, 2);
			gridPane_right.add(label(":"), 2, 2);
			gridPane_right.add(text2, 3, 2);
			gridPane_right.add(new Text(""), 4, 2);

			gridPane_right.add(label("Designation"), 1, 3);
			gridPane_right.add(label(":"), 2, 3);
			gridPane_right.add(text3, 3, 3);
			gridPane_right.add(new Text(""), 4, 3);

			gridPane_right.add(label("Sex"), 1, 4);
			gridPane_right.add(label(":"), 2, 4);
			gridPane_right.add(radiogroup, 3, 4, 2, 1);

			gridPane_right.add(label("Salary"), 1, 5);
			gridPane_right.add(label(":"), 2, 5);
			gridPane_right.add(text5, 3, 5);
			gridPane_right.add(new Text(""), 4, 5);

			gridPane_right.add(button[5], 1, 6, 2, 1);// Save
			gridPane_right.add(button[4], 3, 6, 2, 1);// Cancel

			gridPane_right.setHgap(10);
			gridPane_right.setVgap(30);

			VBox vbox_right = new VBox();
			vbox_right.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
			vbox_right.getChildren().addAll(gridPane_right);
			borderPane.setRight(vbox_right);
		});

		// save
		button[5].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text3.getText().trim().isEmpty() && !text5.getText().trim().isEmpty()) {
				try {
					Staff temp;

					if (rb1.isSelected())
						temp = new Staff(text1.getText(), text2.getText(), text3.getText(), "Male",
								Integer.parseInt(text5.getText()));
					else
						temp = new Staff(text1.getText(), text2.getText(), text3.getText(), "Female",
								Integer.parseInt(text5.getText()));

					// check duplicate
					if (!Person.duplicateCheck(HospitalManagement.staffs, temp)) {
						JOptionPane.showMessageDialog(null, "Duplicate Id");
						text1.setText("");
					} else {
						HospitalManagement.staffs.add(temp);
						borderPane.setRight(null);
						show(borderPane, gridPane_center, button, table_head, HospitalManagement.staffs, false, false,
								-1, 2);
						text1.setText("");
						text2.setText("");
						text3.setText("");
						text5.setText("");
					}
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Salary should be integer and less than 2147483647");
				}
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});

		return new Scene(borderPane);
	}

	public void staffModify(BorderPane borderPane, GridPane gridPane_center, String[] table_head, Button button[],
			int index, int num) {
		GridPane gridPane_right = new GridPane();
		String[] ans = HospitalManagement.staffs.get(index).showInfo();
		TextField text1 = inputString("Enter Staff Id");
		text1.setText(HospitalManagement.staffs.get(index).getId());
		text1.setEditable(false);
		TextField text2 = inputString(ans[1]);
		TextField text3 = inputString(ans[2]);
		ComboBox<Integer> comboBox = comboBox(150);
		comboBox.getSelectionModel().select(21);
		ToggleGroup sex = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setToggleGroup(sex);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setToggleGroup(sex);
		if (ans[3] == "Male")
			rb1.setSelected(true);
		else
			rb2.setSelected(true);
		HBox radiogroup = new HBox();
		radiogroup.getChildren().addAll(rb1, new Text("\t\t"), rb2);
		TextField text5 = inputInt(ans[4]);

		gridPane_right.add(header("MODIFY", 3), 1, 0, 4, 1);
		gridPane_right.add(label("Id"), 1, 1);
		gridPane_right.add(label(":"), 2, 1);
		gridPane_right.add(text1, 3, 1);
		gridPane_right.add(new Text(""), 4, 1);

		gridPane_right.add(label("Name"), 1, 2);
		gridPane_right.add(label(":"), 2, 2);
		gridPane_right.add(text2, 3, 2);
		gridPane_right.add(new Text(""), 4, 2);

		gridPane_right.add(label("Designation"), 1, 3);
		gridPane_right.add(label(":"), 2, 3);
		gridPane_right.add(text3, 3, 3);
		gridPane_right.add(new Text(""), 4, 3);

		gridPane_right.add(label("Sex"), 1, 4);
		gridPane_right.add(label(":"), 2, 4);
		gridPane_right.add(radiogroup, 3, 4, 2, 1);

		gridPane_right.add(label("Salary"), 1, 5);
		gridPane_right.add(label(":"), 2, 5);
		gridPane_right.add(text5, 3, 5);
		gridPane_right.add(new Text(""), 4, 5);

		gridPane_right.add(button[6], 1, 6, 2, 1);// Save
		gridPane_right.add(button[4], 3, 6, 2, 1);// Cancel

		gridPane_right.setHgap(10);
		gridPane_right.setVgap(30);

		VBox vbox_right = new VBox();
		vbox_right
				.setStyle("-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
		vbox_right.getChildren().addAll(gridPane_right);

		button[6].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text3.getText().trim().isEmpty() && !text5.getText().trim().isEmpty()) {
				try {
					Staff temp;
					if (rb1.isSelected())
						temp = new Staff(text1.getText(), text2.getText(), text3.getText(), "Male",
								Integer.parseInt(text5.getText()));
					else
						temp = new Staff(text1.getText(), text2.getText(), text3.getText(), "Female",
								Integer.parseInt(text5.getText()));

					HospitalManagement.staffs.set(index, temp);
					borderPane.setRight(null);
					show(borderPane, gridPane_center, button, table_head, HospitalManagement.staffs, false, false, -1,
							2);
					text1.setText("");
					text2.setText("");
					text3.setText("");
					text5.setText("");
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Salary should be integer and less than 2147483647");
				}

			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});

		borderPane.setRight(vbox_right);
	}

	public Scene patient_Scene(Stage primaryStage) {
		String table_head[] = { "Id", "Name", "Disease", "Sex", "Admit Status", "Age" };
		Button button[] = button_collection();
		BorderPane borderPane = new BorderPane();
		GridPane gridPane_center = new GridPane();
		object_general(primaryStage, borderPane, gridPane_center, button, "Patients", table_head,
				HospitalManagement.patients, 3);
		TextField text1 = inputString("Enter Patient Id");
		TextField text2 = inputString("Enter Patient Name");
		TextField text3 = inputString("Enter Patient disease");
		TextField text5 = inputString("Enter Admit Status");
		ComboBox<Integer> comboBox = comboBox(150);
		comboBox.getSelectionModel().select(21);
		ToggleGroup sex = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setToggleGroup(sex);
		rb1.setSelected(true);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setToggleGroup(sex);
		HBox radiogroup = new HBox();
		radiogroup.getChildren().addAll(rb1, new Text("\t\t"), rb2);

		// Modify
		button[2].setOnAction(e -> {
			borderPane.setRight(button[4]);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.patients, false, true, -1, 3);

		});

		// sort
		button[0].setOnAction(e -> {
			borderPane.setRight(null);
			HospitalParent.sort(HospitalManagement.patients, null, null, null);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.patients, false, false, -1, 3);
		});

		// add
		button[1].setOnAction(e -> {
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.patients, false, false, -1, 3);
			GridPane gridPane_right = new GridPane();
			gridPane_right.add(header("ADD", 3), 1, 0, 4, 1);
			gridPane_right.add(label("Id"), 1, 1);
			gridPane_right.add(label(":"), 2, 1);
			gridPane_right.add(text1, 3, 1);
			gridPane_right.add(new Text(""), 4, 1);

			gridPane_right.add(label("Name"), 1, 2);
			gridPane_right.add(label(":"), 2, 2);
			gridPane_right.add(text2, 3, 2);
			gridPane_right.add(new Text(""), 4, 2);

			gridPane_right.add(label("Disease"), 1, 3);
			gridPane_right.add(label(":"), 2, 3);
			gridPane_right.add(text3, 3, 3);
			gridPane_right.add(new Text(""), 4, 3);

			gridPane_right.add(label("Sex"), 1, 4);
			gridPane_right.add(label(":"), 2, 4);
			gridPane_right.add(radiogroup, 3, 4, 2, 1);

			gridPane_right.add(label("Admit Status"), 1, 5);
			gridPane_right.add(label(":"), 2, 5);
			gridPane_right.add(text5, 3, 5);
			gridPane_right.add(new Text(""), 4, 5);

			gridPane_right.add(label("Age"), 1, 6);
			gridPane_right.add(label(":"), 2, 6);

			gridPane_right.add(comboBox, 3, 6);
			gridPane_right.add(new Text(""), 4, 6);

			gridPane_right.add(button[5], 1, 7, 2, 1);// Save
			gridPane_right.add(button[4], 3, 7, 2, 1);// Cancel

			gridPane_right.setHgap(10);
			gridPane_right.setVgap(30);

			VBox vbox_right = new VBox();
			vbox_right.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
			vbox_right.getChildren().addAll(gridPane_right);
			borderPane.setRight(vbox_right);
		});

		// save
		button[5].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text3.getText().trim().isEmpty() && !text5.getText().trim().isEmpty()) {
				Patient temp;
				if (rb1.isSelected())
					temp = new Patient(text1.getText(), text2.getText(), text3.getText(), "Male", text5.getText(),
							comboBox.getValue());
				else
					temp = new Patient(text1.getText(), text2.getText(), text3.getText(), "Female", text5.getText(),
							comboBox.getValue());
				if (!Person.duplicateCheck(HospitalManagement.patients, temp)) {
					JOptionPane.showMessageDialog(null, "Duplicate Id");
					text1.setText("");
				} else {
					HospitalManagement.patients.add(temp);
					borderPane.setRight(null);
					show(borderPane, gridPane_center, button, table_head, HospitalManagement.patients, false, false, -1,
							3);
					text1.setText("");
					text2.setText("");
					text3.setText("");
					text5.setText("");
				}
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});

		return new Scene(borderPane);
	}

	public void patientModify(BorderPane borderPane, GridPane gridPane_center, String[] table_head, Button button[],
			int index, int num) {
		GridPane gridPane_right = new GridPane();
		String ans[] = HospitalManagement.patients.get(index).showInfo();
		TextField text1 = inputString(ans[0]);
		text1.setText(HospitalManagement.patients.get(index).getId());
		text1.setEditable(false);
		TextField text2 = inputString(ans[1]);
		TextField text3 = inputString(ans[2]);
		TextField text5 = inputString(ans[4]);
		ComboBox<Integer> comboBox = comboBox(150);
		comboBox.getSelectionModel().select(Integer.parseInt(ans[5]));
		ToggleGroup sex = new ToggleGroup();
		RadioButton rb1 = new RadioButton("Male");
		rb1.setToggleGroup(sex);
		RadioButton rb2 = new RadioButton("Female");
		rb2.setToggleGroup(sex);
		if (ans[3] == "Male")
			rb1.setSelected(true);
		else
			rb2.setSelected(true);
		HBox radiogroup = new HBox();
		radiogroup.getChildren().addAll(rb1, new Text("\t\t"), rb2);

		gridPane_right.add(header("MODIFY", 3), 1, 0, 4, 1);
		gridPane_right.add(label("Id"), 1, 1);
		gridPane_right.add(label(":"), 2, 1);
		gridPane_right.add(text1, 3, 1);
		gridPane_right.add(new Text(""), 4, 1);

		gridPane_right.add(label("Name"), 1, 2);
		gridPane_right.add(label(":"), 2, 2);
		gridPane_right.add(text2, 3, 2);
		gridPane_right.add(new Text(""), 4, 2);

		gridPane_right.add(label("Disease"), 1, 3);
		gridPane_right.add(label(":"), 2, 3);
		gridPane_right.add(text3, 3, 3);
		gridPane_right.add(new Text(""), 4, 3);

		gridPane_right.add(label("Sex"), 1, 4);
		gridPane_right.add(label(":"), 2, 4);
		gridPane_right.add(radiogroup, 3, 4, 2, 1);

		gridPane_right.add(label("Admit Status"), 1, 5);
		gridPane_right.add(label(":"), 2, 5);
		gridPane_right.add(text5, 3, 5);
		gridPane_right.add(new Text(""), 4, 5);

		gridPane_right.add(label("Age"), 1, 6);
		gridPane_right.add(label(":"), 2, 6);

		gridPane_right.add(comboBox, 3, 6);
		gridPane_right.add(new Text(""), 4, 6);

		gridPane_right.add(button[6], 1, 7, 2, 1);// Save
		gridPane_right.add(button[4], 3, 7, 2, 1);// Cancel

		gridPane_right.setHgap(10);
		gridPane_right.setVgap(30);

		VBox vbox_right = new VBox();
		vbox_right
				.setStyle("-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
		vbox_right.getChildren().addAll(gridPane_right);

		button[6].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text3.getText().trim().isEmpty() && !text5.getText().trim().isEmpty()) {
				if (rb1.isSelected())
					HospitalManagement.patients.set(index, new Patient(text1.getText(), text2.getText(),
							text3.getText(), "Male", text5.getText(), comboBox.getValue()));
				else
					HospitalManagement.patients.set(index, new Patient(text1.getText(), text2.getText(),
							text3.getText(), "Female", text5.getText(), comboBox.getValue()));
				borderPane.setRight(null);
				show(borderPane, gridPane_center, button, table_head, HospitalManagement.patients, false, false, -1, 3);
				text1.setText("");
				text1.setText("");
				text2.setText("");
				text3.setText("");
				text5.setText("");
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});

		borderPane.setRight(vbox_right);
	}

	public Scene medical_Scene(Stage primaryStage) {
		String table_head[] = { "Name", "Manufacturer", "ExpiryDate", "Cost", "Count" };
		Button button[] = button_collection();
		BorderPane borderPane = new BorderPane();
		GridPane gridPane_center = new GridPane();
		object_general(primaryStage, borderPane, gridPane_center, button, "Medical", table_head,
				HospitalManagement.medicals, 4);
		TextField text1 = inputString("Enter medical name");
		TextField text2 = inputString("Enter manufacturer");
		DatePicker datePicker = new DatePicker(LocalDate.now());
		datePicker.setPrefSize(300, 40);
		TextField text4 = inputInt("Enter cost (RM)");
		TextField text5 = inputInt("Enter quantity count");

		// Modify
		button[2].setOnAction(e -> {
			borderPane.setRight(button[4]);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.medicals, false, true, -1, 4);

		});

		// sort
		button[0].setOnAction(e -> {
			borderPane.setRight(null);
			HospitalParent.sort(null, HospitalManagement.medicals, null, null);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.medicals, false, false, -1, 4);
		});

		// add
		button[1].setOnAction(e -> {
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.medicals, false, false, -1, 4);
			GridPane gridPane_right = new GridPane();
			gridPane_right.add(header("ADD", 3), 1, 0, 4, 1);
			gridPane_right.add(label("Name"), 1, 1);
			gridPane_right.add(label(":"), 2, 1);
			gridPane_right.add(text1, 3, 1);
			gridPane_right.add(new Text(""), 4, 1);

			gridPane_right.add(label("Manufacturer"), 1, 2);
			gridPane_right.add(label(":"), 2, 2);
			gridPane_right.add(text2, 3, 2);
			gridPane_right.add(new Text(""), 4, 2);

			gridPane_right.add(label("Expiry Date"), 1, 3);
			gridPane_right.add(label(":"), 2, 3);
			gridPane_right.add(datePicker, 3, 3);
			gridPane_right.add(new Text(""), 4, 3);

			gridPane_right.add(label("Cost"), 1, 4);
			gridPane_right.add(label(":"), 2, 4);
			text4.setTextFormatter(new TextFormatter<>(c -> {
				if (!c.getControlNewText().matches("\\d*"))
					return null;
				else
					return c;
			}));
			gridPane_right.add(text4, 3, 4);
			gridPane_right.add(new Text(""), 4, 3);

			gridPane_right.add(label("Count"), 1, 5);
			gridPane_right.add(label(":"), 2, 5);
			text5.setTextFormatter(new TextFormatter<>(c -> {
				if (!c.getControlNewText().matches("\\d*"))
					return null;
				else
					return c;
			}));
			gridPane_right.add(text5, 3, 5);
			gridPane_right.add(new Text(""), 4, 5);

			gridPane_right.add(button[5], 1, 6, 2, 1);// Save
			gridPane_right.add(button[4], 3, 6, 2, 1);// Cancel

			gridPane_right.setHgap(10);
			gridPane_right.setVgap(30);

			VBox vbox_right = new VBox();
			vbox_right.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
			vbox_right.getChildren().addAll(gridPane_right);
			borderPane.setRight(vbox_right);
		});

		// save
		button[5].setOnAction(e -> {

			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text4.getText().trim().isEmpty() && !text5.getText().trim().isEmpty()) {
				try {
					LocalDate date = datePicker.getValue();
					String dates = String.format("%02d-%02d-%4d", date.getDayOfMonth(), date.getMonthValue(),
							date.getYear());

					HospitalManagement.medicals.add(new Medical(text1.getText(), text2.getText(), dates,
							Integer.parseInt(text4.getText()), Integer.parseInt(text5.getText())));
					text1.setText("");
					text2.setText("");
					text4.setText("");
					text5.setText("");
					borderPane.setRight(null);
					show(borderPane, gridPane_center, button, table_head, HospitalManagement.medicals, false, false, -1,
							4);
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Cost and Count should be integer and less than 2147483647");
				}
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");

		});

		return new Scene(borderPane);
	}

	public void medicalModify(BorderPane borderPane, GridPane gridPane_center, String[] table_head, Button button[],
			int index, int num) {
		TextField text1 = inputString(HospitalManagement.medicals.get(index).getName());
		TextField text2 = inputString(HospitalManagement.medicals.get(index).getManufacturer());
		DatePicker datePicker = new DatePicker(LocalDate.now());
		datePicker.setPrefSize(300, 40);

		TextField text4 = inputInt(HospitalManagement.medicals.get(index).getCost() + "");
		TextField text5 = inputInt(HospitalManagement.medicals.get(index).getCount() + "");

		GridPane gridPane_right = new GridPane();
		gridPane_right.add(header("MODIFY", 3), 1, 0, 4, 1);
		gridPane_right.add(label("Name"), 1, 1);
		gridPane_right.add(label(":"), 2, 1);
		gridPane_right.add(text1, 3, 1);
		gridPane_right.add(new Text(""), 4, 1);

		gridPane_right.add(label("Manufacturer"), 1, 2);
		gridPane_right.add(label(":"), 2, 2);
		gridPane_right.add(text2, 3, 2);
		gridPane_right.add(new Text(""), 4, 2);

		gridPane_right.add(label("Expiry Date"), 1, 3);
		gridPane_right.add(label(":"), 2, 3);
		gridPane_right.add(datePicker, 3, 3);
		gridPane_right.add(new Text(""), 4, 3);

		gridPane_right.add(label("Cost"), 1, 4);
		gridPane_right.add(label(":"), 2, 4);
		text4.setTextFormatter(new TextFormatter<>(c -> {
			if (!c.getControlNewText().matches("\\d*"))
				return null;
			else
				return c;
		}));
		gridPane_right.add(text4, 3, 4);
		gridPane_right.add(new Text(""), 4, 3);

		gridPane_right.add(label("Count"), 1, 5);
		gridPane_right.add(label(":"), 2, 5);
		text5.setTextFormatter(new TextFormatter<>(c -> {
			if (!c.getControlNewText().matches("\\d*"))
				return null;
			else
				return c;
		}));
		gridPane_right.add(text5, 3, 5);
		gridPane_right.add(new Text(""), 4, 5);

		gridPane_right.add(button[6], 1, 6, 2, 1);// Save
		gridPane_right.add(button[4], 3, 6, 2, 1);// Cancel

		gridPane_right.setHgap(10);
		gridPane_right.setVgap(30);

		VBox vbox_right = new VBox();
		vbox_right
				.setStyle("-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
		vbox_right.getChildren().addAll(gridPane_right);
		borderPane.setRight(vbox_right);

		button[6].setOnAction(e -> {

			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()
					&& !text4.getText().trim().isEmpty() && !text5.getText().trim().isEmpty()) {
				try {
					LocalDate date = datePicker.getValue();
					String dates = String.format("%02d-%02d-%4d", date.getDayOfMonth(), date.getMonthValue(),
							date.getYear());

					HospitalManagement.medicals.set(index, new Medical(text1.getText(), text2.getText(), dates,
							Integer.parseInt(text4.getText()), Integer.parseInt(text5.getText())));
					text1.setText("");
					text2.setText("");
					text4.setText("");
					text5.setText("");
					borderPane.setRight(null);
					show(borderPane, gridPane_center, button, table_head, HospitalManagement.medicals, false, false, -1,
							4);
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Cost and Count should be integer and less than 2147483647");
				}

			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");

		});

	}

	public Scene lab_Scene(Stage primaryStage) {
		String[] table_head = { "Lab", "Cost" };
		Button button[] = button_collection();
		BorderPane borderPane = new BorderPane();
		GridPane gridPane_center = new GridPane();
		object_general(primaryStage, borderPane, gridPane_center, button, "Lab", table_head,
				HospitalManagement.laboratories, 5);
		TextField text1 = inputString("Enter Lab name");
		TextField text2 = inputInt("Enter cost (RM)");

		// Modify
		button[2].setOnAction(e -> {
			borderPane.setRight(button[4]);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.laboratories, false, true, -1, 5);

		});

		// sort
		button[0].setOnAction(e -> {
			borderPane.setRight(null);
			HospitalParent.sort(null, null, HospitalManagement.laboratories, null);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.laboratories, false, false, -1, 5);
		});

		// add
		button[1].setOnAction(e -> {
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.laboratories, false, false, -1, 5);

			GridPane gridPane_right = new GridPane();
			gridPane_right.add(header("ADD", 3), 1, 0, 4, 1);
			gridPane_right.add(label("Lab name"), 1, 1);
			gridPane_right.add(label(":"), 2, 1);
			gridPane_right.add(text1, 3, 1);
			gridPane_right.add(new Text(""), 4, 1);

			gridPane_right.add(label("Cost"), 1, 2);
			gridPane_right.add(label(":"), 2, 2);
			gridPane_right.add(text2, 3, 2);
			gridPane_right.add(new Text(""), 4, 2);

			gridPane_right.add(button[5], 1, 3, 2, 1);// Save
			gridPane_right.add(button[4], 3, 3, 2, 1);// Cancel

			gridPane_right.setHgap(10);
			gridPane_right.setVgap(30);

			VBox vbox_right = new VBox();
			vbox_right.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
			vbox_right.getChildren().addAll(gridPane_right);
			borderPane.setRight(vbox_right);
		});

		// save
		button[5].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()) {
				try {
					HospitalManagement.laboratories.add(new Lab(text1.getText(), Integer.parseInt(text2.getText())));
					borderPane.setRight(null);
					show(borderPane, gridPane_center, button, table_head, HospitalManagement.laboratories, false, false,
							-1, 5);
					text1.setText("");
					text2.setText("");
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Cost should be integer and less than c");
				}
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});
		return new Scene(borderPane);
	}

	public void labModify(BorderPane borderPane, GridPane gridPane_center, String[] table_head, Button button[],
			int index, int num) {
		TextField text1 = inputString(HospitalManagement.laboratories.get(index).getLab());
		TextField text2 = inputInt("RM " + HospitalManagement.laboratories.get(index).getCost());

		GridPane gridPane_right = new GridPane();
		gridPane_right.add(header("MODIFY", 3), 1, 0, 4, 1);
		gridPane_right.add(label("Lab name"), 1, 1);
		gridPane_right.add(label(":"), 2, 1);
		gridPane_right.add(text1, 3, 1);
		gridPane_right.add(new Text(""), 4, 1);

		gridPane_right.add(label("Cost"), 1, 2);
		gridPane_right.add(label(":"), 2, 2);
		gridPane_right.add(text2, 3, 2);
		gridPane_right.add(new Text(""), 4, 2);

		gridPane_right.add(button[6], 1, 3, 2, 1);// Save
		gridPane_right.add(button[4], 3, 3, 2, 1);// Cancel

		gridPane_right.setHgap(10);
		gridPane_right.setVgap(30);

		VBox vbox_right = new VBox();
		vbox_right
				.setStyle("-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
		vbox_right.getChildren().addAll(gridPane_right);

		button[6].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty() && !text2.getText().trim().isEmpty()) {
				try {
					HospitalManagement.laboratories.set(index,
							new Lab(text1.getText(), Integer.parseInt(text2.getText())));
					borderPane.setRight(null);
					show(borderPane, gridPane_center, button, table_head, HospitalManagement.laboratories, false, false,
							-1, 5);
					text1.setText("");
					text2.setText("");
				} catch (NumberFormatException x) {
					JOptionPane.showMessageDialog(null, "Cost should be integer and less than 2147483647");
				}
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});
		borderPane.setRight(vbox_right);

	}

	public Scene facility_Scene(Stage primaryStage) {
		String[] table_head = { "Facility" };
		Button button[] = button_collection();
		BorderPane borderPane = new BorderPane();
		GridPane gridPane_center = new GridPane();
		object_general(primaryStage, borderPane, gridPane_center, button, "Facility", table_head,
				HospitalManagement.facilities, 6);
		TextField text1 = inputString("Enter facility name");

		// Modify
		button[2].setOnAction(e -> {
			borderPane.setRight(button[4]);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.facilities, false, true, -1, 6);

		});
		// Sort
		button[0].setOnAction(e -> {
			borderPane.setRight(null);
			HospitalParent.sort(null, null, null, HospitalManagement.facilities);
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.facilities, false, false, -1, 6);
		});

		// Add
		button[1].setOnAction(e -> {
			show(borderPane, gridPane_center, button, table_head, HospitalManagement.facilities, false, false, -1, 6);
			GridPane gridPane_right = new GridPane();
			gridPane_right.add(header("ADD", 3), 1, 0, 4, 1);
			gridPane_right.add(label("Facility name"), 1, 1);
			gridPane_right.add(label(":"), 2, 1);
			gridPane_right.add(text1, 3, 1);
			gridPane_right.add(new Text(""), 4, 1);

			gridPane_right.add(button[5], 1, 2, 2, 1);// Save
			gridPane_right.add(button[4], 3, 2, 2, 1);// Cancel

			gridPane_right.setHgap(10);
			gridPane_right.setVgap(30);

			VBox vbox_right = new VBox();
			vbox_right.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
			vbox_right.getChildren().addAll(gridPane_right);
			borderPane.setRight(vbox_right);
		});

		// save
		button[5].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty()) {
				HospitalManagement.facilities.add(new Facility(text1.getText()));
				borderPane.setRight(null);
				show(borderPane, gridPane_center, button, table_head, HospitalManagement.facilities, false, false, -1,
						6);
				text1.setText("");
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});

		return new Scene(borderPane);
	}

	public void facilityModify(BorderPane borderPane, GridPane gridPane_center, String[] table_head, Button button[],
			int index, int num) {

		TextField text1 = inputString(HospitalManagement.facilities.get(index).getFacility());
		GridPane gridPane_right = new GridPane();
		gridPane_right.add(header("MODIFY", 3), 1, 0, 4, 1);
		gridPane_right.add(label("Facility name"), 1, 1);
		gridPane_right.add(label(":"), 2, 1);
		gridPane_right.add(text1, 3, 1);
		gridPane_right.add(new Text(""), 4, 1);

		gridPane_right.add(button[6], 1, 2, 2, 1);// Save
		gridPane_right.add(button[4], 3, 2, 2, 1);// Cancel

		gridPane_right.setHgap(10);
		gridPane_right.setVgap(30);

		VBox vbox_right = new VBox();
		vbox_right
				.setStyle("-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
		vbox_right.getChildren().addAll(gridPane_right);
		borderPane.setRight(vbox_right);
		button[6].setOnAction(e -> {
			if (!text1.getText().trim().isEmpty()) {
				HospitalManagement.facilities.set(index, new Facility(text1.getText()));
				borderPane.setRight(null);
				show(borderPane, gridPane_center, button, table_head, HospitalManagement.facilities, false, false, -1,
						num);
				text1.setText("");
			} else
				JOptionPane.showMessageDialog(null, "Please fill all the information");
		});
	}

	// General Repeated Cases for each object
	// *********************************************************************************

	// Here can call everything repeated in the six class

	public void object_general(Stage primaryStage, BorderPane borderPane, GridPane gridPane_center, Button[] button,
			String className, String[] table_head, ArrayList<? extends HospitalParent> arrayList, int num) {

		gridPane_center.add(header(className, 2), 0, 0);

		ScrollPane scrollPane = new ScrollPane(gridPane_center);
		scrollPane.setFitToHeight(true);
		scrollPane.setVbarPolicy(ScrollBarPolicy.ALWAYS);

		borderPane.setTop(object_top(className));
		borderPane.setLeft(object_left(back(primaryStage), button));
		borderPane.setCenter(scrollPane);
		borderPane.setBottom(logout(primaryStage));
		show(borderPane, gridPane_center, button, table_head, arrayList, false, false, -1, num);

		// Delete
		button[3].setOnAction(e -> {
			borderPane.setRight(button[4]);
			gridPane_center.getChildren().clear();
			gridPane_center.add(header(className, 2), 0, 0);
			gridPane_center.isGridLinesVisible();
			show(borderPane, gridPane_center, button, table_head, arrayList, true, false, -1, num);

		});

		// cancel
		button[4].setOnAction(e -> {
			show(borderPane, gridPane_center, button, table_head, arrayList, false, false, -1, num);
			borderPane.setRight(null);
		});

	}

	// Show the result

	public void show(BorderPane borderPane, GridPane gridPane_center, Button[] button, String head[],
			ArrayList<? extends HospitalParent> array, boolean flag, boolean flag2, int index, int num) {

		gridPane_center.getChildren().clear();
		for (int i = 0; i < head.length; i++)
			gridPane_center.add(header(head[i], 2), i, 0);

		for (int i = 0; i < array.size(); i++) {
			String temp[] = array.get(i).showInfo();
			for (int j = 0; j < temp.length; j++) {
				HBox hbox = new HBox();
				Label label = new Label(temp[j]);
				label.setWrapText(true);
				Tooltip.install(hbox, new Tooltip(temp[j]));
				hbox.getChildren().add(label);
				hbox.setPrefHeight(10);
				hbox.setMinWidth(170);

				if (i != index)
					hbox.setStyle("-fx-padding: 10;-fx-border-color: black; -fx-border-width: 1 1 1 1;");
				else
					hbox.setStyle(
							"-fx-padding: 10;-fx-border-color: black;-fx-background-color: yellow; -fx-border-width: 1 1 1 1;");
				if (flag) {
					final int in = i;
					hbox.setOnMouseClicked(e -> {
						array.remove(in);
						gridPane_center.getChildren().clear();
						show(borderPane, gridPane_center, button, head, array, true, false, -1, num);

					});
					hbox.setOnMouseEntered(e -> {
						hbox.setStyle(
								"-fx-padding: 10;-fx-border-color: black;-fx-background-color: red; -fx-border-width: 1 1 1 1;");

					});
					hbox.setOnMouseExited(e -> {
						hbox.setStyle("-fx-padding: 10;-fx-border-color: black; -fx-border-width: 1 1 1 1;");
					});
				} else if (flag2) {
					final int ind = i;
					hbox.setOnMouseClicked(e -> {
						switch (num) {
						case 1:
							doctorModify(borderPane, gridPane_center, head, button, ind, num);
							break;
						case 2:
							staffModify(borderPane, gridPane_center, head, button, ind, num);
							break;
						case 3:
							patientModify(borderPane, gridPane_center, head, button, ind, num);
							break;
						case 4:
							medicalModify(borderPane, gridPane_center, head, button, ind, num);
							break;
						case 5:
							labModify(borderPane, gridPane_center, head, button, ind, num);
							break;
						case 6:
							facilityModify(borderPane, gridPane_center, head, button, ind, num);
							break;
						}
						show(borderPane, gridPane_center, button, head, array, false, false, ind, num);
					});
					hbox.setOnMouseEntered(e -> {
						hbox.setStyle(
								"-fx-padding: 10;-fx-border-color: black;-fx-background-color: yellow; -fx-border-width: 1 1 1 1;");
					});
					hbox.setOnMouseExited(e -> {
						hbox.setStyle("-fx-padding: 10;-fx-border-color: black; -fx-border-width: 1 1 1 1;");
					});
				} else if (i != index) {
					hbox.setOnMouseEntered(e -> {
						hbox.setStyle(
								"-fx-padding: 10;-fx-border-color: black;-fx-background-color: grey; -fx-border-width: 1 1 1 1;");
					});
					hbox.setOnMouseExited(e -> {
						hbox.setStyle("-fx-padding: 10;-fx-border-color: black; -fx-border-width: 1 1 1 1;");
					});
				}
				gridPane_center.add(hbox, j, i + 1);
			}

			if (flag) {
				final int in = i;
				Button dlt = new Button("X");
				dlt.setStyle("-fx-background-color:Red;");
				dlt.setOnAction(e -> {
					array.remove(in);
					gridPane_center.getChildren().clear();
					show(borderPane, gridPane_center, button, head, array, true, false, -1, num);

				});
				gridPane_center.add(dlt, temp.length, i + 1);
			}

			if (flag2) {
				final int ind = i;
				Button mdf = new Button("-");
				mdf.setPrefSize(20, 20);
				mdf.setStyle("-fx-background-color:Yellow;");
				mdf.setOnAction(e -> {
					show(borderPane, gridPane_center, button, head, array, false, false, ind, num);
					// modify loop
					switch (num) {
					case 1:
						doctorModify(borderPane, gridPane_center, head, button, ind, num);
						break;
					case 2:
						staffModify(borderPane, gridPane_center, head, button, ind, num);
						break;
					case 3:
						patientModify(borderPane, gridPane_center, head, button, ind, num);
						break;
					case 4:
						medicalModify(borderPane, gridPane_center, head, button, ind, num);
						break;
					case 5:
						labModify(borderPane, gridPane_center, head, button, ind, num);
						break;
					case 6:
						facilityModify(borderPane, gridPane_center, head, button, ind, num);
						break;
					}
				});
				gridPane_center.add(mdf, temp.length, i + 1);
			}
		}

	}

	// Menu Layout
	// *************************************************************************************************************

	public Scene menu_scene(Stage primaryStage) {
		GridPane menu_GridPane = new GridPane();
		menu_GridPane.add(header("Menu Selection", 1), 0, 0, 3, 1);
		String[] icon = { "Doctor", "Staff", "Patient", "Medical", "Lab", "Facility" };
		for (int i = 0; i < 6; i++) {

			ImageView menu_Icon = new ImageView(new Image("Images//" + icon[i] + ".png"));
			Button menu_Button = new Button();
			menu_Button.setGraphic(menu_Icon);
			menu_Button.setEffect(new DropShadow(20, Color.BLACK));
			menu_Icon.setStyle("-fx-border-radius: 0 0 18 18;");
			Text textField = new Text(icon[i]);
			textField.setFont(Font.font(null, FontWeight.BOLD, FontPosture.REGULAR, 30));

			VBox vbox = new VBox();
			vbox.getChildren().addAll(textField, menu_Button);
			vbox.setAlignment(Pos.CENTER);
			Tooltip.install(vbox, new Tooltip("Click me to enter " + icon[i].toLowerCase() + " function!"));
			menu_GridPane.add(vbox, i % 3, i / 3 + 1);

			switch (i) {
			case 0:
				vbox.setOnMouseClicked(e -> {
					primaryStage.setScene(doctor_Scene);
				});
				menu_Button.setOnAction(e -> {
					primaryStage.setScene(doctor_Scene);
				});
				menu_Button.setOnKeyPressed(e -> {
					if (e.getCode().equals(KeyCode.ENTER))
						primaryStage.setScene(doctor_Scene);
				});
				break;
			case 1:
				vbox.setOnMouseClicked(e -> {
					primaryStage.setScene(staff_Scene);
				});
				menu_Button.setOnAction(e -> {
					primaryStage.setScene(staff_Scene);
				});
				menu_Button.setOnKeyPressed(e -> {
					if (e.getCode().equals(KeyCode.ENTER))
						primaryStage.setScene(staff_Scene);
				});
				break;
			case 2:
				vbox.setOnMouseClicked(e -> {
					primaryStage.setScene(patient_Scene);
				});
				menu_Button.setOnAction(e -> {
					primaryStage.setScene(patient_Scene);
				});
				menu_Button.setOnKeyPressed(e -> {
					if (e.getCode().equals(KeyCode.ENTER))
						primaryStage.setScene(patient_Scene);
				});
				break;
			case 3:
				vbox.setOnMouseClicked(e -> {
					primaryStage.setScene(medical_Scene);
				});
				menu_Button.setOnAction(e -> {
					primaryStage.setScene(medical_Scene);
				});
				menu_Button.setOnKeyPressed(e -> {
					if (e.getCode().equals(KeyCode.ENTER))
						primaryStage.setScene(medical_Scene);
				});
				break;
			case 4:
				vbox.setOnMouseClicked(e -> {
					primaryStage.setScene(lab_Scene);
				});
				menu_Button.setOnAction(e -> {
					primaryStage.setScene(lab_Scene);
				});
				menu_Button.setOnKeyPressed(e -> {
					if (e.getCode().equals(KeyCode.ENTER))
						primaryStage.setScene(lab_Scene);
				});
				break;
			case 5:
				vbox.setOnMouseClicked(e -> {
					primaryStage.setScene(facility_Scene);
				});
				menu_Button.setOnAction(e -> {
					primaryStage.setScene(facility_Scene);
				});
				menu_Button.setOnKeyPressed(e -> {
					if (e.getCode().equals(KeyCode.ENTER))
						primaryStage.setScene(facility_Scene);
				});
				break;
			}
			menu_Button.setStyle(
					"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:black; -fx-background-radius:15");
			menu_Button.setOnMouseEntered(e -> menu_Button.setStyle(
					"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color: darkblue; -fx-background-radius:15"));
			menu_Button.setOnMouseExited(e -> menu_Button.setStyle(
					"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color: black; -fx-background-radius:15"));
			menu_Button.setOnMousePressed(e -> menu_Button.setStyle(
					"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:BLUE; -fx-background-radius:15"));
			menu_Button.setAlignment(Pos.CENTER);
		}
		menu_GridPane.setHgap(100);
		menu_GridPane.setVgap(50);
		menu_GridPane.setAlignment(Pos.CENTER);

		BorderPane menu_BorderPane = new BorderPane();
		menu_BorderPane.setTop(header("Hospital Management System", 0));
		menu_BorderPane.setCenter(menu_GridPane);
		menu_BorderPane.setBottom(logout(primaryStage));
		Scene menu_Scene = new Scene(menu_BorderPane);
		return menu_Scene;

	}

	// Start Page Layout
	// **********************************************************************************************

	public void startPage(Stage primaryStage) {

		Button startPage_Button = new Button();
		ImageView startPage_Img = new ImageView(new Image("Images//WelcomePage.png"));
		Tooltip.install(startPage_Img, new Tooltip("Click me to enter menu!"));
		startPage_Img.setFitHeight(400);
		startPage_Img.setFitWidth(600);
		startPage_Button.setGraphic(startPage_Img);
		startPage_Img.setStyle("-fx-border-radius: 0 0 18 18;");
		startPage_Button.setEffect(new DropShadow(20, Color.BLACK));
		startPage_Button.setStyle(
				"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:black; -fx-background-radius:15");
		startPage_Button.setOnMouseEntered(e -> startPage_Button.setStyle(
				"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color: darkblue; -fx-background-radius:15"));
		startPage_Button.setOnMouseExited(e -> startPage_Button.setStyle(
				"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color: black; -fx-background-radius:15"));
		startPage_Button.setOnMousePressed(e -> startPage_Button.setStyle(
				"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:blue; -fx-background-radius:15"));
		startPage_Button.setAlignment(Pos.CENTER);
		Tooltip.install(startPage_Button, new Tooltip("Click me to continue!"));

		Button quit = new Button();
		quit.setText("Quit");
		quit.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:Pink");
		quit.setMaxSize(Screen.getPrimary().getVisualBounds().getWidth(), 30);

		BorderPane startPage_BorderPane = new BorderPane();
		startPage_BorderPane.setTop(header("Welcome to Hospital Management System", 0));
		startPage_BorderPane.setCenter(startPage_Button);
		startPage_BorderPane.setBottom(quit);

		startPage_BorderPane
				.setBackground(new Background(new BackgroundFill(Color.ALICEBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

		Scene startPage_Scene = new Scene(startPage_BorderPane);

		startPage_Button.setOnAction(e -> {
			do {
				JPanel panel = new JPanel();
				JLabel label = new JLabel("Enter a password:");
				JPasswordField pass = new JPasswordField(10);
				panel.add(label);
				panel.add(pass);

				String[] options = new String[] { "OK", "Cancel" };
				int option = JOptionPane.showOptionDialog(null, panel, "Enter password", JOptionPane.NO_OPTION,
						JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
				if (option == 0) // pressing OK button
				{
					if (String.valueOf(pass.getPassword()).equals("OOPP")) {
						primaryStage.setScene(menu_Scene);
						break;
					} else
						JOptionPane.showMessageDialog(null,
								String.valueOf(pass.getPassword())
										+ ", this password is incorrect!!!\nDefault password is 'OOPP'",
								"Password Invalid", JOptionPane.INFORMATION_MESSAGE);

				} else
					break;
			} while (true);
		});
		quit.setOnMouseEntered(e -> {
			quit.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:Darkred");
		});
		quit.setOnMouseExited(e -> {
			quit.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:pink");
		});
		quit.setOnAction(e -> {
			primaryStage.hide();
		});
		primaryStage.setScene(startPage_Scene);
	}

	// Repeated element and decoration
	// ********************************************************************************************************

	public Button logout(Stage primaryStage) {
		Button logout = new Button();
		logout.setText("Logout");
		logout.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:Pink");
		logout.setMaxSize(Screen.getPrimary().getVisualBounds().getWidth(), 30);

		logout.setOnMouseEntered(e -> {
			logout.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:Darkred");
		});
		logout.setOnMouseExited(e -> {
			logout.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:pink");
		});
		logout.setOnAction(e -> {
			startPage(primaryStage);
		});
		return logout;
	}

	public VBox object_top(String className) {
		VBox vbox_top = new VBox();
		vbox_top.getChildren().addAll(header("Hospital Management System", 0), header(className, 1));
		return vbox_top;
	}

	public VBox object_left(Button back, Button button[]) {
		VBox vbox_left = new VBox();
		vbox_left.setStyle("-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: solid;");
		GridPane gridPane_left = new GridPane();
		gridPane_left.add(back, 1, 1);
		gridPane_left.add(new Text(""), 2, 1);

		gridPane_left.add(button[0], 1, 2);
		gridPane_left.add(button[1], 1, 3);
		gridPane_left.add(button[2], 1, 4);
		gridPane_left.add(button[3], 1, 5);
		gridPane_left.setHgap(10);
		gridPane_left.setVgap(30);
		vbox_left.getChildren().add(gridPane_left);

		VBox.setMargin(vbox_left, new Insets(20, 10, 20, 10));
		return vbox_left;
	}

	public Text label(String label) {
		Text text = new Text(label);
		text.setFont(Font.font("Verdana", 20));
		return text;
	}

	public TextField inputString(String ans) {
		TextField text_field = new TextField();
		text_field.setFont(Font.font("Verdana", 20));
		text_field.setPromptText(ans);
		return text_field;
	}

	public TextField inputInt(String ans) {
		TextField text_field = new TextField();
		text_field.setFont(Font.font("Verdana", 20));
		text_field.setPromptText(ans);
		text_field.setTextFormatter(new TextFormatter<>(c -> {
			if (!c.getControlNewText().matches("\\d*"))
				return null;
			else
				return c;
		}));
		return text_field;
	}

	public HBox header(String text, int i) {
		Text header_Text = new Text(text);
		HBox header = new HBox();
		if (i == 0) {
			header_Text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
			header_Text.setFill(Color.RED);

			DropShadow ds = new DropShadow();
			ds.setOffsetY(3.0f);
			ds.setColor(Color.color(0.5f, 0.5f, 0.5f));
			header_Text.setEffect(ds);

			Text date = new Text();
			Text time = new Text();
			Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
				LocalTime currentTime = LocalTime.now();
				time.setText(String.format("%02d : %02d : %02d", currentTime.getHour(), currentTime.getMinute(),
						currentTime.getSecond()));
				LocalDate currentDate = LocalDate.now();
				date.setText(String.format("%02d / %02d / %04d", currentDate.getDayOfMonth(),
						currentDate.getMonthValue(), currentDate.getYear()));
			}), new KeyFrame(Duration.seconds(1)));
			clock.setCycleCount(Animation.INDEFINITE);
			clock.play();
			date.setFont(Font.font("verdana", FontPosture.REGULAR, 20));
			time.setFont(Font.font("verdana", FontPosture.REGULAR, 20));

			BorderPane borderPane = new BorderPane();
			borderPane.setTop(header_Text);
			borderPane.setLeft(date);
			borderPane.setRight(time);

			header.getChildren().add(borderPane);
		} else if (i == 1)

		{
			header_Text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
			header_Text.setFill(Color.BLUE);
			header.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-border-style: dashed;");
			header.getChildren().add(header_Text);
		} else if (i == 2) {
			header_Text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 20));
			header_Text.setFill(Color.BLACK);
			header.getChildren().add(header_Text);
		} else if (i == 3) {
			header_Text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 40));
			header_Text.setFill(Color.BLACK);
			header.setStyle(
					"-fx-border-color: black;-fx-border-insets: 5;-fx-border-width: 3;-fx-background-color:white;-fx-border-style: solid; -fx-border-radius: 10;");
			header.getChildren().add(header_Text);
		}

		header.setMaxWidth(Double.MAX_VALUE);
		header.setAlignment(Pos.TOP_CENTER);

		return header;
	}

	public ComboBox<Integer> comboBox(int max) {
		ComboBox<Integer> comboBox = new ComboBox<Integer>();
		comboBox.setPrefSize(260, 40);
		for (int i = 0; i < 150; i++) {
			comboBox.getItems().add(i);
		}
		return comboBox;
	}

	public Button[] button_collection() {
		Button[] button = new Button[7];
		button[0] = button_self("Sort", "CHARTREUSE", "GREEN");
		button[1] = button_self("Add", "AQUA", "BLUE");
		button[2] = button_self("Modify", "YELLOW", "ORANGE");
		button[3] = button_self("Delete", "RED", "DARKRED");
		button[4] = button_self("Cancel", "PINK", "RED");
		button[5] = button_self("Save", "LIGHTBLUE", "BLUE");
		button[6] = button_self("Save", "LIGHTBLUE", "BLUE");

		Tooltip.install(button[0], new Tooltip("Click me to sort!"));
		Tooltip.install(button[1], new Tooltip("Click me to add item!"));
		Tooltip.install(button[2],
				new Tooltip("Click me to modify. After click you can just click which row you want to edit!"));
		Tooltip.install(button[3], new Tooltip("Click me to delete!"));
		Tooltip.install(button[4], new Tooltip("Click me to cancel (Not be saved)!"));
		Tooltip.install(button[5], new Tooltip("Click me to save your work!"));
		Tooltip.install(button[6], new Tooltip("Click me to save your work!"));
		return button;
	}

	public Button button_self(String text, String color1, String color2) {
		Button back = new Button();
		back.setPrefSize(235, 80);
		back.setText(text);
		back.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color: " + color1
				+ "; -fx-background-radius:15");
		back.setOnMouseEntered(e -> back.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color: "
				+ color2 + "; -fx-background-radius:15"));
		back.setOnMouseExited(e -> back.setStyle("-fx-font-size:25; -fx-font-weight:bold;-fx-background-color: "
				+ color1 + "; -fx-background-radius:15"));
		back.setOnMousePressed(e -> back.setStyle(
				"-fx-font-size:25; -fx-font-weight:bold;-fx-background-color:BLACK; -fx-background-radius:15"));
		back.setAlignment(Pos.CENTER);
		return back;
	}

}
