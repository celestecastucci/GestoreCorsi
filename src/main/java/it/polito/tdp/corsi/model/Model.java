package it.polito.tdp.corsi.model;

import java.util.List;
import java.util.Map;

import it.polito.tdp.corsi.db.CorsoDAO;

public class Model {
	
	//avrà riferimento come attributo alla classe CorsoDAO
	
	private CorsoDAO corsoDao;
	
	public Model() {
		corsoDao= new CorsoDAO();
	}
	
	/**
	 * metodo uguale al DAO, è solo per fare da chiamante tra il DAO E IL FXMLController
	 * @param pd
	 * @return
	 */
	public List<Corso> getCorsiByPeriodo(Integer pd){
		return corsoDao.getCorsiByPeriodo(pd);
	}
	//ora vado in FXMLController
	
	public Map<Corso,Integer>getIscrittiByPeriodo(Integer pd){
		return corsoDao.getIscrittiByPeriodo(pd);
	}
	//ora vado in FXMLController
}
