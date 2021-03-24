/**
 * Sample Skeleton for 'Scene.fxml' Controller Class
 */

package it.polito.tdp.corsi;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import it.polito.tdp.corsi.model.Corso;
import it.polito.tdp.corsi.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {

	private Model model;
	
    @FXML // ResourceBundle that was given to the FXMLLoader
    private ResourceBundle resources;

    @FXML // URL location of the FXML file that was given to the FXMLLoader
    private URL location;

    @FXML // fx:id="txtPeriodo"
    private TextField txtPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="txtCorso"
    private TextField txtCorso; // Value injected by FXMLLoader

    @FXML // fx:id="btnCorsiPerPeriodo"
    private Button btnCorsiPerPeriodo; // Value injected by FXMLLoader

    @FXML // fx:id="btnNumeroStudenti"
    private Button btnNumeroStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnStudenti"
    private Button btnStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="btnDivisioneStudenti"
    private Button btnDivisioneStudenti; // Value injected by FXMLLoader

    @FXML // fx:id="txtRisultato"
    private TextArea txtRisultato; // Value injected by FXMLLoader

    @FXML
    void corsiPerPeriodo(ActionEvent event) {
    	
    	txtRisultato.clear();
    	/**
    	 * faccio il controllo dell'input con try/catch per gestire eventuali eccezioni
    	 */
    	
    	if(txtPeriodo.getText()==null) {
    		txtRisultato.setText("DEVI INSERIRE UN NUMERO (1 o 2 ) PER IL PERIODO DIDATTICO");
    		return;
    	}
    		
    	String periodoStringa = txtPeriodo.getText();
    	Integer periodo;
    	try {
    		periodo= Integer.parseInt(periodoStringa);
    	} catch(NumberFormatException ne) {
    		txtRisultato.setText("DEVI INSERIRE UN NUMERO (1 o 2 ) PER IL PERIODO DIDATTICO");
    		return;
    	}
    	
    	if(periodo <1 || periodo> 2) {
    		txtRisultato.setText("DEVI INSERIRE UN NUMERO (1 o 2 ) PER IL PERIODO DIDATTICO");
    		return;
    		
    	}
    	List<Corso>corsi =this.model.getCorsiByPeriodo(periodo);
    	for(Corso c: corsi) {
    		txtRisultato.appendText(c.toString()+ "\n");
    	}
    	
    }

    @FXML
    void numeroStudenti(ActionEvent event) {

    	txtRisultato.clear();
    	/**
    	 * faccio il controllo dell'input con try/catch per gestire eventuali eccezioni
    	 */
    	
    	if(txtPeriodo.getText()==null) {
    		txtRisultato.setText("DEVI INSERIRE UN NUMERO (1 o 2 ) PER IL PERIODO DIDATTICO");
    		return;
    	}
    		
    	String periodoStringa = txtPeriodo.getText();
    	Integer periodo;
    	try {
    		periodo= Integer.parseInt(periodoStringa);
    	} catch(NumberFormatException ne) {
    		txtRisultato.setText("DEVI INSERIRE UN NUMERO (1 o 2 ) PER IL PERIODO DIDATTICO");
    		return;
    	}
    	
    	if(periodo <1 || periodo> 2) {
    		txtRisultato.setText("DEVI INSERIRE UN NUMERO (1 o 2 ) PER IL PERIODO DIDATTICO");
    		return;
    		
    	}
    	
    	/**
    	 * qui è tutto corretto --> collego la lista/mapppa/string al metodo corrispondente nel model
    	 */
    	
    	Map<Corso,Integer>corsiIscrizioni =this.model.getIscrittiByPeriodo(periodo);
    	
    	//scorro la mappa per keyset --> per chiave
    	for(Corso c: corsiIscrizioni.keySet()) {
    		txtRisultato.appendText(c.toString()); //NON SOSTITUISCE , E' DIVERSO DA SETTEXT
    		Integer n= corsiIscrizioni.get(c);  //prendo il numero di iscritti dalla mappa
    	txtRisultato.appendText("\t"+ n + "\n");  // \t serve per dare una tabulazione
    	}
    	
    }
    

    @FXML
    void stampaDivisione(ActionEvent event) {

    }

    @FXML
    void stampaStudenti(ActionEvent event) {

    }

    @FXML // This method is called by the FXMLLoader when initialization is complete
    void initialize() {
        assert txtPeriodo != null : "fx:id=\"txtPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtCorso != null : "fx:id=\"txtCorso\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnCorsiPerPeriodo != null : "fx:id=\"btnCorsiPerPeriodo\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnNumeroStudenti != null : "fx:id=\"btnNumeroStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnStudenti != null : "fx:id=\"btnStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert btnDivisioneStudenti != null : "fx:id=\"btnDivisioneStudenti\" was not injected: check your FXML file 'Scene.fxml'.";
        assert txtRisultato != null : "fx:id=\"txtRisultato\" was not injected: check your FXML file 'Scene.fxml'.";

    }
    
    public void setModel(Model model) {
    	this.model = model;
    }
    
    
}