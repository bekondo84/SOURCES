/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kerenedu.dashboard;

import com.core.base.BaseElement;
import java.io.Serializable;

/**
 *
 * @author BEKO
 */
public class Raccourci extends BaseElement implements Serializable,Comparable<Raccourci>{

    private String code ;
    
    private String intitule ;
    
    private String icone ;
    
    private long quantite;
    

    public Raccourci() {
    }

    /**
     * 
     * @param code
     * @param intitule
     * @param icone 
     */
    public Raccourci(String code, String intitule, String icone) {
        this.code = code;
        this.intitule = intitule;
        this.icone = icone;
    }

    
    
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIntitule() {
        return intitule;
    }

    public String getIcone() {
        return icone;
    }

    public long getQuantite() {
		return quantite;
	}

	public void setQuantite(long quantite) {
		this.quantite = quantite;
	}

	public void setIcone(String icone) {
        this.icone = icone;
    }
    
    

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

	public int compareTo(Raccourci o) {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
    
  
}
