package it.polito.tdp.corsi.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.model.Corso;

public class CorsoDAO {

	//dato un periodo didatt vogliamo dei corsi
	//dobbiamo scegliere il tipo di metodo che vogliamo
	//ci servono tutte le info sui corsi --> ENTRA IN GIOCO UN NUOVO PATTERN ---> PATTERN ORM
	
	/**
	 * IL PATTERN ORM --> per ogni tabella che vogliamo modellare nel nostro programma creiamo una corrispettiva classe in java
	 * 
	 */
	//dobbiamo definire la classe corso in model
	public List<Corso> getCorsiByPeriodo(Integer periodo){
		//COPIO QUERY DA HEIDI SQP
		//devo togliere gli \n e metto SEMPRE SPAZIO PRIMA DI CAMBIARE RIGA
		String sql= "SELECT * "    //* vuol dire prendi tutti gli attributi
				+ "FROM corso "    //dalla classe corso
				+ "WHERE pd =? ";    //cosa --> il periodo --> ? significa ch lo scrive l'utente
		

		
		List<Corso>result= new ArrayList<Corso>();
		
		//creo try catch per gestire eccezioni di tipo SQL
		try {
			Connection conn= DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
		st.setInt(1, periodo); //1 è il primo parametro nella query --> il primo punto interrogativo 
		//che corrisponde al periodo passato come parametro
		
		ResultSet rs= st.executeQuery();
		
		//per ogni riga mi creo un Corso e lo aggiungo alla lista
		while(rs.next()) {
			Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
			result.add(c);
			
		}
		rs.close();
		st.close();
		conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
				
		}
		return result;
	}
	
	
	/**
	 * PER IL SECONDO PUNTO --> CREO QUERY IN HEIDI --> POI VEDO QUI IL METODO DA IMPLEMENTARE
	 * CREO QUI UN METODO MAPPA DOVE KEY=CORSO E VALORE=NUMERO ISCRITTI
	 * 
	 */
	public Map<Corso,Integer>getIscrittiByPeriodo(Integer periodo){
		
		//in heidi ho messo come pd=1 per provare se venisse giusta facendo il run
		//qui metto il punto ? ogni volta che voglio far scegliere l'utente
		/**
		 * ATTENZIONE : GROUP BY viene fatta perchè in select ho un COUNT
		 * nella group by metto tutti gli elementi prima di COUNT --> SEMPRE
		 */
		String sql= "SELECT c.codins, c.nome, c.crediti, c.pd, COUNT(*) AS tot "
				+ "FROM corso c, iscrizione i "
				+ "WHERE c.codins = i.codins AND c.pd = ? "
				+ "GROUP BY c.codins, c.nome, c.crediti, c.pd";

		//creo mappa in cui memorizzare
		Map<Corso,Integer>result= new HashMap<Corso,Integer>();
		
		//creo try catch per gestire eccezioni di tipo SQL
		try {
			Connection conn= DBConnect.getConnection();
			PreparedStatement st= conn.prepareStatement(sql);
		st.setInt(1, periodo); 
		ResultSet rs= st.executeQuery();
		
		//per ogni riga mi creo un Corso e lo aggiungo alla lista
		while(rs.next()) {
			Corso c= new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
			Integer n=rs.getInt("tot");
			result.put(c,n);
			
		}
		rs.close();
		st.close();
		conn.close();
		}catch(SQLException e) {
			throw new RuntimeException(e);
				
		}
		return result;
		
	}
}
