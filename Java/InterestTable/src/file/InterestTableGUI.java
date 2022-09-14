package file;

import java.text.NumberFormat;

//import gui.HandlingEvent.ButtonHandler;
//import gui.HandlingEvent.ButtonHandler;
//import gui.HandlingEvent.ButtonHandler;
import javafx.application.Application;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.event.*;

@SuppressWarnings("restriction")

public class InterestTableGUI extends Application {
	private TextField principal, rate;
	private TextArea displayArea;

	private Slider years;

	@Override /* Method in Application class */
	public void start(Stage primaryStage) {
		int sceneWidth = 600, sceneHeight = 550;
		int verSpaceBetweenNodes = 8, horSpaceBetweenNodes = 8;
		int paneBorderTop = 20, paneBorderRight = 20;
		int paneBorderBottom = 20, paneBorderLeft = 20;
		displayArea = new TextArea();
		displayArea.setEditable(false);
		displayArea.setWrapText(true);

		GridPane pane = new GridPane();
		pane.setHgap(horSpaceBetweenNodes);
		pane.setVgap(verSpaceBetweenNodes);
		pane.setPadding(new Insets(paneBorderTop, paneBorderRight, paneBorderBottom, paneBorderLeft));

		/* Adding scroll pane to text area */
		ScrollPane scrollPane = new ScrollPane(displayArea);

		/* Adding elements */

		/* Adding GUI elements to the pane */
		Label principalLabel = new Label("Principal:");
		principal = new TextField();
		pane.add(principalLabel, 0, 1);
		pane.add(principal, 1, 1);

		Label rateLabel = new Label("Rate(%):");
		rate = new TextField();
		pane.add(rateLabel, 2, 1);
		pane.add(rate, 3, 1);

		/* Horizontal slider */
		years = new Slider();
		Label sliderLabel = new Label("Number of Years:");
		years.setMin(1);
		years.setMax(25);
		years.setValue(1);
		years.setMajorTickUnit(4);
		years.setShowTickMarks(true);
		years.setShowTickLabels(true);
		pane.add(sliderLabel, 0, 3);
		pane.add(years, 1, 3);

		/* adding buttons to pane */

		Button simple = new Button("SimpleInterest");
		Button compound = new Button("CompoundInterest");
		Button both = new Button("BothInterest");
		pane.add(simple, 0, 4);
		pane.add(compound, 1, 4);
		pane.add(both, 2, 4);

		/* Inner class described bellow */
		simple.setOnAction(new SimpleInterest());

		/* Lambda expression used for compound interest */
		compound.setOnAction(e -> {
			double principalValue = Double.parseDouble(principal.getText());
			double rateValue = Double.parseDouble(rate.getText());
			double yearsValue = years.getValue();
			String displayText = "";
			displayText += "Principal: " + principalValue + ", Rate: " + rateValue + "\n";
			displayText += "Year, Compound Intrest Amount" + "\n";
			for (int i = 1; i <= yearsValue; i++) {
				double compoundIntr = ComputeInterest.compoundInterest(principalValue, rateValue, i);
				String formattedValue = NumberFormat.getCurrencyInstance().format(compoundIntr);

				displayText += i + "-->" + formattedValue + "\n";
			}
			displayArea.setText(displayText);
		});

		/* Anonymous class used for both interests */
		both.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent e) {
				double principalValue = Double.parseDouble(principal.getText());
				double rateValue = Double.parseDouble(rate.getText());
				double yearsValue = years.getValue();
				String displayText = "";
				displayText += "Principal: " + principalValue + ", Rate: " + rateValue + "\n";
				displayText += "Year, Simple Intrest Amount, Compound Intrest Amount" + "\n";
				for (int i = 1; i <= yearsValue; i++) {
					double simpleIntr = ComputeInterest.simpleInterest(principalValue, rateValue, i);
					double compoundIntr = ComputeInterest.compoundInterest(principalValue, rateValue, i);
					String formattedValue = NumberFormat.getCurrencyInstance().format(simpleIntr);
					String formattedValue2 = NumberFormat.getCurrencyInstance().format(compoundIntr);

					displayText += i + "-->" + formattedValue + "-->" + formattedValue2 + "\n";
				}
				displayArea.setText(displayText);
			}
		});

		BorderPane borderPane = new BorderPane();
		borderPane.setBottom(pane);
		borderPane.setTop(scrollPane);

		/* Display the stage */
		Scene scene = new Scene(borderPane, sceneWidth, sceneHeight);
		primaryStage.setTitle("Interest Table Calculation");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	// non-anonymous inner class for simple interest
	private class SimpleInterest implements EventHandler<ActionEvent> {

		public void handle(ActionEvent e) {
			double principalValue = Double.parseDouble(principal.getText());
			double rateValue = Double.parseDouble(rate.getText());
			double yearsValue = years.getValue();
			String displayText = "";
			displayText += "Principal: " + principalValue + ", Rate: " + rateValue + "\n";
			displayText += "Year, Simple Intrest Amount" + "\n";
			for (int i = 1; i <= yearsValue; i++) {
				double simpleIntr = ComputeInterest.simpleInterest(principalValue, rateValue, i);
				String formattedValue = NumberFormat.getCurrencyInstance().format(simpleIntr);
				displayText += i + "-->" + formattedValue + "\n";

			}
			displayArea.setText(displayText);

		}
	}

	public static void main(String[] args) {
		Application.launch(args);
	}

}
