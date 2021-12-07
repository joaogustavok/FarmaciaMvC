package sistema.farmacia.entities;

import javax.persistence.Entity;

@Entity
public class Farmaceutico extends Pessoa {
	
	private Integer crm;

	public Integer getCrm() {
		return crm;
	}

	public void setCrm(Integer crm) {
		this.crm = crm;
	}

}
