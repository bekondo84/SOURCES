/**
 * 
 */
package com.keren.model.carrieres;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.core.base.BaseElement;
import com.core.base.State;
import com.keren.model.employes.Employe;
import com.keren.model.employes.Fonction;
import com.keren.model.employes.Poste;
import com.megatim.common.annotations.Observer;
import com.megatim.common.annotations.Predicate;

/**
 * @author BEKO
 *
 */
@Entity
@Table(name="T_AFFERH")
public class Affectation extends BaseElement implements Serializable, Comparable<Affectation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2846218232843823234L;
	
	@ManyToOne
	@JoinColumn(name="EMPL_ID")
	@Predicate(label="Salarié",type=Employe.class,target="many-to-one",optional=false,search=true,observable=true)
	private Employe salarie ;
	
	@Predicate(label="Référence",optional=false,search=true,unique=true)
	@Column(unique=true)
	private String code ;
	
	@ManyToOne
	@JoinColumn(name="POSTA_ID")
	@Predicate(label="Ancien Poste",type=Poste.class,target="many-to-one",optional=false,search=true,editable=false)
	@Observer(observable="salarie",source="field:poste")
	private Poste posteA ;
	
	@ManyToOne
	@JoinColumn(name="POSTN_ID")
	@Predicate(label="Nouveau Poste",type=Poste.class,target="many-to-one",optional=false,search=true)
	private Poste posteN ;
	
	@ManyToOne
	@JoinColumn(name="FONCA_ID")
	@Predicate(label="Ancien Fonction",type=Fonction.class,target="many-to-one",optional=false,search=true,editable=false)
	@Observer(observable="salarie",source="field:fonction")
	private Fonction fonctionA ;
	
	@ManyToOne
	@JoinColumn(name="FONCN_ID")
	@Predicate(label="Nouvelle Fonction",type=Fonction.class,target="many-to-one",optional=false,search=true)
	private Fonction fonctionN ;
	
	@Predicate(label="Ancien lieu de travail",search=true,editable=false)
	@Observer(observable="salarie",source="field:lieuaffectation")
	private String lieuA ;
	
	@Predicate(label="Nouveau lieu de travail",search=true)
	private String lieuN;

	@Temporal(TemporalType.DATE)
	@Predicate(label="Date de la décision",type=Date.class,target="date")
	private Date decision ;
	
	@Temporal(TemporalType.DATE)
	@Predicate(label="Date prise d'effet",type=Date.class,target="date")
	private Date deffet;
	
	@Predicate(label="Etat",hide=true,search=true)
	private String state ="etabli";
	

	/**
	 * 
	 */
	public Affectation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 * @param designation
	 * @param moduleName
	 */
	public Affectation(long id, String designation, String moduleName) {
		super(id, designation, moduleName,0L);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * @param id
	 * @param designation
	 * @param moduleName
	 * @param salarie
	 * @param code
	 * @param posteA
	 * @param posteN
	 * @param fonctionA
	 * @param fonctionN
	 * @param lieuA
	 * @param lieuN
	 * @param deffet
	 * @param decision
	 */

	public Affectation(long id, String designation, String moduleName, Employe salarie, String code, Poste posteA,
			Poste posteN, Fonction fonctionA, Fonction fonctionN, String lieuA, String lieuN, Date deffet,
			Date decision) {
		super(id, designation, moduleName,0L);
		this.salarie = salarie;
		this.code = code;
		this.posteA = posteA;
		this.posteN = posteN;
		this.fonctionA = fonctionA;
		this.fonctionN = fonctionN;
		this.lieuA = lieuA;
		this.lieuN = lieuN;
		this.deffet = deffet;
		this.decision = decision;
	}
	
	public Affectation(Affectation affect) {
		super(affect.id, affect.designation, affect.moduleName,affect.compareid);
		if(affect.salarie!=null){
			this.salarie = new Employe(affect.salarie);
		}
		this.code = affect.code;
		if(affect.posteA!=null){
			this.posteA = new Poste(affect.posteA);
		}
		if(affect.posteN!=null){
			this.posteN = new Poste(affect.posteN);
		}
		if(affect.fonctionA!=null){
			this.fonctionA = new Fonction(fonctionA);
		}
		if(affect.fonctionN!=null){
			this.fonctionN = new Fonction(affect.fonctionN);
		}
		this.lieuA = affect.lieuA;
		this.lieuN = affect.lieuN;
		this.deffet = affect.deffet;
		this.decision = affect.decision;
		this.state = affect.state;
	}
	
	

	public Employe getSalarie() {
		return salarie;
	}

	public void setSalarie(Employe salarie) {
		this.salarie = salarie;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Poste getPosteA() {
		return posteA;
	}

	public void setPosteA(Poste posteA) {
		this.posteA = posteA;
	}

	public Poste getPosteN() {
		return posteN;
	}

	public void setPosteN(Poste posteN) {
		this.posteN = posteN;
	}

	public Fonction getFonctionA() {
		return fonctionA;
	}

	public void setFonctionA(Fonction fonctionA) {
		this.fonctionA = fonctionA;
	}

	public Fonction getFonctionN() {
		return fonctionN;
	}

	public void setFonctionN(Fonction fonctionN) {
		this.fonctionN = fonctionN;
	}

	public String getLieuA() {
		return lieuA;
	}

	public void setLieuA(String lieuA) {
		this.lieuA = lieuA;
	}

	public String getLieuN() {
		return lieuN;
	}

	public void setLieuN(String lieuN) {
		this.lieuN = lieuN;
	}

	public Date getDeffet() {
		return deffet;
	}

	public void setDeffet(Date deffet) {
		this.deffet = deffet;
	}

	public Date getDecision() {
		return decision;
	}

	public void setDecision(Date decision) {
		this.decision = decision;
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
		return "Affection";
	}

	@Override
	public String getListTitle() {
		// TODO Auto-generated method stub
		return "Affections";
	}

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return "kerenrh";
	}

	@Override
	public String getDesignation() {
		// TODO Auto-generated method stub
		return salarie.getDesignation();
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
		states.add(new State("valide", "Validé"));
//		states.add(new State("annule", "Annulé"));
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
	public int compareTo(Affectation o) {
		// TODO Auto-generated method stub
		return code.compareTo(o.code);
	}

}
