/**
 * 
 */
package com.kerenedu.configuration;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Predicate;

/**
 * @author ntchuente
 * niveau d'une classe CP A, CP B ... CE1 A
 */
@Table
@Entity(name = "e_niv_cls")
public class Niveau extends BaseElement implements Serializable, Comparable<Niveau> {

	@Column(name = "CODE", unique = true)
	@Predicate(label="CODE",optional=false,updatable=false,search=true)
	protected String code;

	@Column(name = "DESCRIPTION")
	@Predicate(label="DESCRIPTION",optional=false,updatable=false,search=true)
	protected String description;

	public Niveau() {
		super();

	}
	public Niveau(String code, String description) {
		super();
		this.code = code;
		this.description = description;
	}
	
	public Niveau(Niveau niveau) {
		super(niveau.id, niveau.designation, niveau.moduleName,0L);
		this.code = niveau.code;
		this.description = niveau.description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	
	@Override
	public String getEditTitle() {
		// TODO Auto-generated method stub
		return "Gestion des Niveau de Classe";
	}

	@Override
	public String getListTitle() {
		// TODO Auto-generated method stub
		return "Liste des Niveau de Classe" ;
	}

	@Override
	public String getModuleName() {
		// TODO Auto-generated method stub
		return "kereneducation";
	}

	@Override
	public String getDesignation() {
		// TODO Auto-generated method stub
		return toString();
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 79 * hash + Objects.hashCode(this.code);
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
		final Niveau other = (Niveau) obj;
		if (!Objects.equals(this.code, other.code)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return code + " - " + description;
	}

	public int compareTo(Niveau o) {
		// TODO Auto-generated method stub
		return 0;
	}

}
