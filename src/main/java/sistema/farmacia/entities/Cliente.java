package sistema.farmacia.entities;

import javax.persistence.Entity;

@Entity
public class Cliente extends Pessoa {
	
	private String receita;

	public String getReceita() {
		return receita;
	}

	public void setReceita(String receita) {
		this.receita = receita;
	}
}
