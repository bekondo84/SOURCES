/**
 * 
 */
package com.keren.model.recrutement;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.core.base.BaseElement;
import com.core.base.State;
import com.keren.model.structures.Ville;
import com.megatim.common.annotations.Predicate;

/**
 * @author BEKO
 *
 */
@Entity
@Table(name="T_BESRECRH")
public class BesionRecrutement extends BaseElement implements Serializable, Comparable<BesionRecrutement> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 247536807190133977L;
	
	@ManyToOne
	@JoinColumn(name="EMP_ID")
	@Predicate(label="Emploi concerné",type=Emploi.class,target="many-to-one",optional=false,search=true)
	private Emploi emploi;
	
	@ManyToOne
	@JoinColumn(name="VILL_ID")
	@Predicate(label="Lieu de recrutement",type=Ville.class,target="many-to-one",optional=false,search=true)
	private Ville lieu ;
	
	@Predicate(label="Nombre de Places",type=Short.class,optional=false,search=true)
	private Short place;
	
	@Predicate(label=".",target="textarea",group=true,groupName="group1",groupLabel="Motivation du besion",search=true)
	private String motivation ;
	
	@Predicate(label="Etat",hide=true)
	private String state = "etabli";

	
	/**
	 * 
	 */
	public BesionRecrutement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param designation
	 * @param moduleName
	 */
	public BesionRecrutement(long id, String designation, String moduleName) {
		super(id, designation, moduleName,0L);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param id
	 * @param designation
	 * @param moduleName
	 * @param emploi
	 * @param lieu
	 * @param place
	 * @param motivation
	 */

	public BesionRecrutement(long id, String designation, String moduleName, Emploi emploi, Ville lieu, Short place,
			String motivation) {
		super(id, designation, moduleName,0L);
		this.emploi = emploi;
		this.lieu = lieu;
		this.place = place;
		this.motivation = motivation;
	}
	
	/**
	 * 
	 * @param besion
	 */
	public BesionRecrutement(BesionRecrutement besion) {
		super(besion.id, besion.designation, besion.moduleName,besion.compareid);
		this.emploi = besion.emploi;
		this.lieu = besion.lieu;
		this.place = besion.place;
		this.motivation = besion.motivation;
		this.state = besion.state;
	}
	
	

	public Emploi getEmploi() {
		return emploi;
	}

	public void setEmploi(Emploi emploi) {
		this.emploi = emploi;
	}

	public Ville getLieu() {
		return lieu;
	}

	public void setLieu(Ville lieu) {
		this.lieu = lieu;
	}

	public Short getPlace() {
		return place;
	}

	public void setPlace(Short place) {
		this.place = place;
	}

	public String getMotivation() {
		return motivation;
	}

	public void setMotivation(String motivation) {
		this.motivation = motivation;
	}	
	

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String getEditTitle() {
		// TODO Auto-generated method stub
		return "Expression du Besion";
	}

	@Override
	public String getListTitle() {
		// TODO Auto-generated method stub
		return "Expression des Besions";
	}

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return "kerenrh";
	}

	@Override
	public String getDesignation() {
		// TODO Auto-generated method stub
		return emploi.getDesignation();
	}

	@Override
	public String getSerial() {
		// TODO Auto-generated method stub
		return Long.toString(serialVersionUID);
	}

	@Override
	public boolean isActivefilelien() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public List<State> getStates() {
		// TODO Auto-generated method stub
		List<State> states = new ArrayList<State>();
		states.add(new State("etabli", "Brouillon"));
		states.add(new State("valide", "Validée"));
		//states.add(new State("annule", "Annulée"));
		return states;
	}

	@Override
	public boolean isActivatefollower() {
		// TODO Auto-generated method stub
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(BesionRecrutement o) {
		// TODO Auto-generated method stub
		return emploi.compareTo(o.emploi);
	}

}
