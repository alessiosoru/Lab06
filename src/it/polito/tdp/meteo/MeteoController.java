package it.polito.tdp.meteo;

import java.net.URL;
import java.time.Month;
import java.util.ResourceBundle;

import it.polito.tdp.meteo.bean.Citta;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

public class MeteoController {
	
	private Model model;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ChoiceBox<Month> boxMese;

	@FXML
	private Button btnCalcola;

	@FXML
	private Button btnUmidita;

	@FXML
	private TextArea txtResult;

	@FXML
	void doCalcolaSequenza(ActionEvent event) {
		this.txtResult.clear();
		Month mese = this.boxMese.getValue();
		this.txtResult.appendText(model.trovaSequenza(mese));
	}

	@FXML
	void doCalcolaUmidita(ActionEvent event) {
		this.txtResult.clear();
		Month mese = this.boxMese.getValue();
		for(Citta c : model.getAllCitta()) {
			Double umidita = model.getUmiditaMedia(mese, c);
			this.txtResult.appendText(String.format("Citta %s con umidita %.2f\n", c.getNome(), umidita));
		}	
	}

	@FXML
	void initialize() {
		assert boxMese != null : "fx:id=\"boxMese\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert btnCalcola != null : "fx:id=\"btnCalcola\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert btnUmidita != null : "fx:id=\"btnUmidita\" was not injected: check your FXML file 'Meteo.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Meteo.fxml'.";
		
		for(int mese=1;mese<=12;mese++) { // popolo il menu a tendina con i mesi
			this.boxMese.getItems().add(Month.of(mese));
		}
	}

	public void setModel(Model model) {
		this.model=model;
		
		
	}

}
