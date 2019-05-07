package it.polito.tdp.model;

import java.time.Month;
import java.util.List;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.SimpleCity;
import it.polito.tdp.meteo.db.MeteoDAO;

public class Model {

	private final static int COST = 100;
	private final static int NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN = 3;
	private final static int NUMERO_GIORNI_CITTA_MAX = 6;
	private final static int NUMERO_GIORNI_TOTALI = 15;

	
	private List<Citta> allCitta;
	
	private Percorso percorsoBest;
	
	public Model() {
		MeteoDAO dao = new MeteoDAO();
		this.allCitta=dao.getAllCitta();
		percorsoBest = new Percorso();
	}
	
	public List<Citta> getAllCitta() {
		return this.allCitta;
	}


	public Double getUmiditaMedia(Month mese, Citta citta) {
		MeteoDAO dao = new MeteoDAO();
		return dao.getUmiditaMedia(mese, citta);
	}
		
		

	public String trovaSequenza(Month mese) {
		Percorso parziale = new Percorso();
		
		cercaPercorso(parziale, 0, mese);
		return this.percorsoBest.toString();
	}


	private void cercaPercorso(Percorso parziale, int L, Month mese) {
	
		if(L==this.NUMERO_GIORNI_TOTALI && controllaParziale(parziale) &&
			percorsoBest.getPunteggio()>punteggioSoluzione(parziale)) {
			percorsoBest = parziale.clone();
		}
		
		for(Citta c: this.getAllCitta()) {
			int spostamento = 0; // se cambia città è 1 per poter moltiplicare con 100
			if(L!=0 && parziale.getPercorso().get(L-1).getNome().compareTo(c.getNome())!=0) {
				spostamento = 1;
			}
			
			int costoGiornaliero = this.getUmiditaMedia(mese, c).intValue()+COST*spostamento;
			SimpleCity cit = new SimpleCity(c.getNome(), costoGiornaliero);
			parziale.add(cit);
			cercaPercorso(parziale, L+1, mese);
			parziale.remove(cit);
		}
		
		
	}

//	private Double punteggioSoluzione(List<SimpleCity> soluzioneCandidata) {
	private Double punteggioSoluzione(Percorso parziale) {
		double score = 0.0;
		score = parziale.calolaPunteggioTotale();// modifico il punteggio interno al percorso
		return score;
	}

//	private boolean controllaParziale(List<SimpleCity> parziale) {
	private boolean controllaParziale(Percorso parziale) {
		for(Citta c : getAllCitta()) {
			int gg = parziale.numeroGiorniInCitta(c);
			if(gg<this.NUMERO_GIORNI_CITTA_CONSECUTIVI_MIN||gg>this.NUMERO_GIORNI_CITTA_MAX)
				return false; // non valido
		}
		return true; // valido
	}

}
