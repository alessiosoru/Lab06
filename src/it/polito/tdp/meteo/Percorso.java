package it.polito.tdp.meteo;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.meteo.bean.Citta;
import it.polito.tdp.meteo.bean.SimpleCity;

public class Percorso {
	
	private List<SimpleCity> percorso;
//	private int numeroSpostamenti; //non mi servono gli spostamento perchè tengo il conto per ogni giorno e non lo faccio alla fine
	private Double punteggio;
	
	public Percorso() {
		this.percorso = new ArrayList<SimpleCity>();
//		this.numeroSpostamenti = 0;
		this.punteggio = 0.0;
	}
	
	public List<SimpleCity> getPercorso() {
		return percorso;
	}
	public void setPercorso(List<SimpleCity> percorso) {
		this.percorso = percorso;
	}
//	public int getNumeroSpostamenti() {
//		return numeroSpostamenti;
//	}
//	public void setNumeroSpostamenti(int numeroSpostamenti) {
//		this.numeroSpostamenti = numeroSpostamenti;
//	}

	
	public Double getPunteggio() {
		return punteggio;
	}

	public void setPunteggio(Double punteggio) {
		this.punteggio = punteggio;
	}

	@Override
	public String toString() {
		String sequenzaCitta="";
		for(SimpleCity citta : percorso) {
			sequenzaCitta = sequenzaCitta + citta.toString();
		}
		return sequenzaCitta;
	}

	@Override
	protected Percorso clone() {
		Percorso p = new Percorso();
//		p.numeroSpostamenti=this.numeroSpostamenti;
		p.percorso= new ArrayList<SimpleCity>(this.percorso);
		p.punteggio=this.punteggio;
		return p;
	}

	public void add(SimpleCity cit) {
		percorso.add(cit);		
	}

	public void remove(SimpleCity cit) {
		percorso.remove(cit);
		
	}

	public double calolaPunteggioTotale() {
		for(SimpleCity sc : percorso) {
			punteggio = punteggio+sc.getCosto();
		}
		return punteggio;
	}

	public int numeroGiorniInCitta(Citta c) {
		int gg=0;
		for(SimpleCity curr : percorso) {
			if(c.getNome().compareTo(curr.getNome())==0)
				gg++;
		}
		return gg;
	}
	
	
	
	

}
