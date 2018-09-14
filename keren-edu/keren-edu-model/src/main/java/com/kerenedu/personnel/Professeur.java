/**
 * 
 */
package com.kerenedu.personnel;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;

import com.core.base.BaseElement;

import com.megatim.common.annotations.Predicate;

/**
 * @author ntchuente
 *
 */

@Table
@Entity(name = "e_professeur")
public class Professeur extends BaseElement implements Serializable, Comparable<Professeur> {

	@Column(name = "PHOTO" )	
	@Predicate(label = "PHOTO",target = "image"  , sequence=1)
	private String image ;
	 
	
	@Column(name = "NOM", unique=true)
	@Predicate(label="NOM",optional=false,updatable=true,search=true, sequence=4, colsequence=2)
	protected String nom;
	
	@Column(name = "PRENOM")
	@Predicate(label="PRENOM",optional=true,updatable=true,search=true , sequence=3, colsequence=3)
	protected String prenon;
	
	@Column(name = "DATENAIS")
	@Temporal(javax.persistence.TemporalType.DATE)
	@Predicate(label="DATE NAISS.",optional=true,updatable=true,search=true, type=Date.class, target="date", sequence=5, colsequence=4)
	protected Date dateNais ;

	@Column(name = "EMAIL")
	@Predicate(label="EMAIL",optional=true,updatable=true,search=false , target="email", sequence=6)
	protected String email;
		
	@Column(name = "SEXE")
	@Predicate(label="SEXE",optional=false,updatable=true,search=false, target="combobox", values="Masculin;Feminin" , sequence=7)
	protected String sexe="0";
	
	@Column(name = "Statut")
	@Predicate(label="Statut" , search=true,target="combobox",values="Directrice(eur);Responsable Administratif;Enseignant;Animateur Pédagogique;Autres" ,optional=true , sequence=8)
	private String role ="0";
	
	@ManyToOne
    @JoinColumn(name = "DIP_ID")
	@Predicate(label="DIPLOME",updatable=true,type=Diplome.class , target="many-to-one",search=false, sequence=10, optional=true)
    protected Diplome diplome;
	
	
    @Column(name = "STATUS_ID")
	@Predicate(label="Type Contrat",optional=true,updatable=true,search=false, target="combobox", values="Vacataire;Permanent(e)" , sequence=9)
    protected String status;
	
	@Column(name ="TELEPHONE")
	@Predicate(label = "TELEPHONE",type = String.class,search = false, sequence=11,pattern="[0-9]")
	private String contact ;
	
	@Column(name ="SAL")
	@Predicate(label = "SALAIRE",type = Long.class,search = false, sequence=11,pattern="[0-9]", group=true,groupLabel="Information Financieres",
			groupName="tab1")
	private Long salaire = new Long(0) ;
	
	@Column(name ="Tx_H")
	@Predicate(label = "TAUX HORAIRE",type = Long.class,search = false, sequence=11,pattern="[0-9]", group=true,groupLabel="Information Financieres",
			groupName="tab1")
	private Long thoraire = new Long(0) ;
	
	@Column(name ="NUM_BAN")
	@Predicate(label = "NUMERO DE COMPTE BANCAIRE",type = Long.class,search = false, sequence=11,pattern="[0-9]", group=true,groupLabel="Information Financieres",
			groupName="tab1")
	private Long numBan = new Long(0) ;
	
	
	// ajout tab inscription 
	// ajout tab absence
	
	public Professeur() {
	
		
	}
	
	
	public Professeur(String image, String matricule, Date dateNais, String lNais, String nom,
			String email, String prenon,String sexe, String sitFamiliale, String telPere, 
			String emailPere, String telMere, String emailMere,String status, Diplome diplome,String contact) {
		super();
		this.image = image;
		this.dateNais = dateNais;
		this.nom = nom;
		this.email = email;
		this.prenon = prenon;
		this.sexe = sexe;
		this.status = status;
		this.contact=contact;
		this.diplome=diplome ;
	}


	public Professeur(Professeur eleve) {
		super(eleve.id, eleve.designation, eleve.moduleName,0L);
		this.image = eleve.image;
		this.dateNais = eleve.dateNais;
		this.nom = eleve.nom;
		this.email = eleve.email;
		this.prenon =eleve. prenon;
		this.sexe = eleve.sexe;
		
		if(eleve.status!=null){
			this.status =eleve.status ;
		}
		this.contact=eleve.contact;
		if(eleve.diplome!=null){
		this.diplome= new Diplome(eleve.diplome);
		}
		this.salaire= eleve.salaire;
		this.thoraire=eleve.thoraire;
		this.numBan=eleve.numBan;
		this.role=eleve.role;
	
	}

	@Override
	public String getEditTitle() {
		// TODO Auto-generated method stub
		return "Edition des Enseignants/Personnels";
	}

	@Override
	public String getListTitle() {
		// TODO Auto-generated method stub
		return "Gestion des Enseignants/Personnels";
	}

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return "kereneducation";
	}

	@Override
	public String getDesignation() {
		// TODO Auto-generated method stub
		return nom+" "+prenon;
	}

	
	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public String getNom() {
		return nom;
	}



	public void setNom(String nom) {
		this.nom = nom;
	}



	public String getPrenon() {
		return prenon;
	}



	public void setPrenon(String prenon) {
		this.prenon = prenon;
	}



	public String getSexe() {
		return sexe;
	}



	public void setSexe(String sexe) {
		this.sexe = sexe;
	}


	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getContact() {
		return contact;
	}


	public void setContact(String contact) {
		this.contact = contact;
	}
	

	public Date getDateNais() {
		return dateNais;
	}

	public Diplome getDiplome() {
		return diplome;
	}


	public void setDiplome(Diplome diplome) {
		this.diplome = diplome;
	}


	public String getRole() {
		return role;
	}


	public void setRole(String role) {
		this.role = role;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public void setDateNais(Date dateNais) {
		this.dateNais = dateNais;
	}


	public Long getSalaire() {
		return salaire;
	}


	public void setSalaire(Long salaire) {
		this.salaire = salaire;
	}


	public Long getThoraire() {
		return thoraire;
	}


	public void setThoraire(Long thoraire) {
		this.thoraire = thoraire;
	}


	public Long getNumBan() {
		return numBan;
	}


	public void setNumBan(Long numBan) {
		this.numBan = numBan;
	}


	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.id);
		return hash;
	}



	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Professeur other = (Professeur) obj;
		if (!Objects.equals(this.id, other.id)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCreateonfield() {
		return false;
	}


	public int compareTo(Professeur o) {
		// TODO Auto-generated method stub
		return 0;
	}


}
