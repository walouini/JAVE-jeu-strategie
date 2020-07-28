package Dukes_of_the_Realm;

public class Production {
	private Chateau chateau;
	private int etat;
	
	private String unite;
	private int temps;
	
	public Production(Chateau chateau) {
		this.chateau = chateau;
		this.etat = Settings.OFF;
		this.temps = 0;
	}
	
	public void addProduction(String unite) {
		this.unite = unite;
		this.etat = Settings.ON;
		switch(unite) {
			case Settings.PRODUCTION_TYPE_CHEVALIER:
				this.temps = Settings.TIME_CHEVALIER;
				break;
			case Settings.PRODUCTION_TYPE_AMELIORATION:
				this.temps = 100 + 50*chateau.getNiveau();
				break;
		}
	}
	
	public void update() {
		this.temps --;
		if(temps==0) {
			switch(unite) {
				case Settings.PRODUCTION_TYPE_CHEVALIER:
					Chevalier chevalier = new Chevalier(chateau.getLayer(), chateau.getImage_chevalier());
					chateau.setChevaliers(chevalier);
					break;
				case Settings.PRODUCTION_TYPE_AMELIORATION:
					chateau.setNiveau(chateau.getNiveau()+1);
					break;
			}
			this.etat = Settings.OFF;
		}
	}

	public Chateau getChateau() {
		return chateau;
	}

	public void setChateau(Chateau chateau) {
		this.chateau = chateau;
	}

	public int getEtat() {
		return etat;
	}

	public String getUnite() {
		return unite;
	}

	public int getTemps() {
		return temps;
	}

	public void setTemps() {
		this.temps = this.temps-1;
	}

}
