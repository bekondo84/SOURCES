/**
 * 
 */
package com.kerenedu.configuration;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

import com.core.base.BaseElement;
import com.core.calendar.Event;
import com.megatim.common.annotations.Predicate;

/**
 * @author Nadege
 *
 */
@Entity
@Table(name = "T_EVENT")
@XmlRootElement
public class EventEdu extends BaseElement implements Serializable,Comparable<Event>{

    @Predicate(label="Titre",optional = false,min = 2,search = true)    
    private String title ;
    
    @Predicate(label = "Description",optional = false,search = true)
    private String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Predicate(target = "date",optional = false,type = Date.class,search = true)
    private Date start;
    
    @Temporal(TemporalType.TIMESTAMP)    
    private Date end;
    
    //@Temporal(TemporalType.TIME)
    private String duree ;
    
    private boolean recurrent = false ;
    
    private short confidentialite =0;
    
    private short disponibilite =0;
    
    private String lieu ;
    
    private boolean allDay = false;

    private boolean notify = true ;
    
    
    @ManyToOne
    @JoinColumn(name = "OWN_ID")
    private UserEducation owner; 
    
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "T_USER_EVENT" ,joinColumns = @JoinColumn(name = "EVENT_ID"),inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<UserEducation> participants = new ArrayList<UserEducation>();
    
    @ManyToOne
    @JoinColumn(name = "RAP_ID")
    private RappelE rappel ;
    
    
    /**
     * 
     */
    public EventEdu() {
    }

    /**
     * 
     * @param title
     * @param description
     * @param start
     * @param end
     * @param duree
     * @param lieu
     * @param allDay 
     */
    public EventEdu(String title, String description, Date start, Date end, String duree, String lieu, boolean allDay) {
        this.title = title;
        this.description = description;
        this.start = start;
        this.end = end;
        this.duree = duree;
        this.lieu = lieu;
        this.allDay = allDay;
    }
    
     public EventEdu(EventEdu event) {
        this.id = event.id;
        this.title = event.title;
        this.description = event.description;
        this.start = event.start;
        this.end = event.end;
        this.duree = event.duree;
        this.recurrent=event.recurrent;
        this.confidentialite = event.confidentialite;
        this.disponibilite=event.disponibilite;
        this.lieu = event.lieu;
        this.allDay = event.allDay;
        this.notify = event.notify;
        this.owner = new UserEducation(event.owner);
        this.participants= new ArrayList<UserEducation>();
        this.rappel=new RappelE(event.rappel);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDuree() {
        return duree;
    }

    public void setDuree(String duree) {
        this.duree = duree;
    }

    public boolean isRecurrent() {
        return recurrent;
    }

    public void setRecurrent(boolean recurrent) {
        this.recurrent = recurrent;
    }

    public short getConfidentialite() {
        return confidentialite;
    }

    public void setConfidentialite(short confidentialite) {
        this.confidentialite = confidentialite;
    }



    public short getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(short disponibilite) {
        this.disponibilite = disponibilite;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public boolean isAllDay() {
        return allDay;
    }

    public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

   

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
    }

    public UserEducation getOwner() {
        return owner;
    }

    public List<UserEducation> getParticipants() {
		return participants;
	}

	public void setParticipants(List<UserEducation> participants) {
		this.participants = participants;
	}

	public void setOwner(UserEducation owner) {
        this.owner = owner;
    }

	public RappelE getRappel() {
		return rappel;
	}

	public void setRappel(RappelE rappel) {
		this.rappel = rappel;
	}

	public int compareTo(Event o) {
		// TODO Auto-generated method stub
		return 0;
	}
    


}
