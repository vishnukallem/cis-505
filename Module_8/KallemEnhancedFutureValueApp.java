import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class KallemEnhancedFutureValueApp extends Application {
    
    private TextField txtMonthlyPayment = new TextField ();
    
    private TextField txtInterestRate = new TextField ();
    
    private TextArea txtResults = new TextArea();
    
    private Label lblmonthlypayment = new Label("Monthly Payment:");
    
    private Label lblinterestRate = new Label("Interest Rate:");
    
    private Label lblinterestRateFormat = new Label("Enter 11.1% as 11:");
    
    private Label lblyears= new Label("Years:");
    
    private Label lblcalculate = new Label("Calculate:");
    private Label lblFutureValueDate = new Label();
    
    private ComboBox<Integer> cmb = new ComboBox<Integer>();
    
    private Button btnCalculate = new Button("Calculate");
    
    Button btnCear = new Button("Clear");
  
    @Override
    public void start(Stage primaryStage) {
        txtMonthlyPayment.setText("");
        txtInterestRate.setText("");
        txtResults.setText("");
        lblcalculate.setText("");
        lblyears.setText("0");
        lblFutureValueDate.setText("");
        btnCalculate.setOnAction(event -> calculateResults());
        btnCear.setOnAction((e -> clearFormFields()));

        GridPane pane = new GridPane();
       
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5,12.5,13.5,14.5));
        pane.setVgap(5.5);
        pane.setHgap(5.5);
        lblinterestRateFormat.setTextFill(Color.RED);
        pane.add(lblmonthlypayment, 0, 0);
        pane.add(txtMonthlyPayment, 1, 0);
        pane.add(lblinterestRate, 0, 1);
        pane.add(txtInterestRate, 1, 1);
        GridPane.setHalignment(lblinterestRateFormat, HPos.RIGHT);
        pane.add(lblinterestRateFormat, 1, 2);
        pane.add(lblyears,0, 3);
        cmb.getItems().addAll(1, 2, 3, 4, 5);
        pane.add(cmb, 1, 3);
        HBox actionBtnContainer = new HBox();
        actionBtnContainer.setPadding(new Insets(15,0,15,30));
        actionBtnContainer.setSpacing(10);
        actionBtnContainer.getChildren().add(btnCear);
        actionBtnContainer.getChildren().add(btnCalculate);
       
        pane.add(actionBtnContainer, 1, 4);
        txtResults.setPrefSize(150, 100);
        pane.add(lblFutureValueDate, 0, 5);
        pane.add(txtResults, 0, 6);
         
        primaryStage.setScene(new Scene(pane, 350, 300));
        primaryStage.setTitle("Kallem Future Value app");
        primaryStage.show();
    }

    private void clearFormFields(){
        txtMonthlyPayment.setText("");
        txtInterestRate.setText("");
        txtResults.setText("");
        lblcalculate.setText("");
        lblyears.setText("0");
        lblFutureValueDate.setText("");

    }

    private void calculateResults(){
       double calResults = FinanceCalculator.calculateFutureValue(Double.parseDouble(txtMonthlyPayment.getText()),Double.parseDouble(txtInterestRate.getText()),cmb.getValue().intValue());
       Date today = new Date();
    // SimpleDateFormat is needed for formatting
    SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
    String formattedDate = formatter.format(today);
    String futureValuetext = "Calculation as of "+formattedDate;
    lblFutureValueDate.setText(futureValuetext);
       String resultsText = "The future value is "+String.valueOf(calResults);
       txtResults.setText(resultsText);
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
