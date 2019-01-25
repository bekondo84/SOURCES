/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.teratech.achat.model.base;

import com.core.base.BaseElement;
import com.megatim.common.annotations.Filter;
import com.megatim.common.annotations.Predicate;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author BEKO
 */
@Entity
@Table(name = "T_ART")
public class Article extends BaseElement implements Serializable,Comparable<Article>{

    @Predicate(label = "Photo",target = "image")
    private String image ;
    
    @Predicate(label = "reference.interne",optional = false,unique = true,search = true)
    private String code ;
    
    @Predicate(label = "intitule",search = true)
    private String intitule ;
    
    @Predicate(label = "article.vendu",type = Boolean.class,search = true)
    private Boolean vendu = Boolean.FALSE;
    
     @Predicate(label = "article.acheté",type = Boolean.class,search = true)
    private Boolean achete = Boolean.FALSE;
    
    @Predicate(label = "type.article",target = "combobox",values = "Article stockable;Utilisation directe;Service;Immobilisation",group =true,groupName = "group1",groupLabel = "informations.generales")
    private String type = "0";    
    
    @Predicate(label = "Actif",type = Boolean.class,group =true,groupName = "group1",groupLabel = "informations.generales")
    private Boolean actif = Boolean.TRUE;    
    
    @ManyToOne
    @JoinColumn(name = "FAAR_ID")
    @Predicate(label = "Famille.articles",type = FamilleArticle.class,target = "many-to-one",group =true,groupName = "group1",groupLabel = "informations.generales")
    @Filter(value = "[{\"fieldName\":\"type\",\"value\":\"0\"}]")
    private FamilleArticle famille ;
    
    @Predicate(label = "code.barre.ean13",group =true,groupName = "group1",groupLabel = "informations.generales")
    private String codebarre ;
    
    @Predicate(label = "prix.achat.ht",type = Double.class,group =true,groupName = "group1",groupLabel = "informations.generales")
    private Double puachat ;
    
    @ManyToOne
    @JoinColumn(name = "UNAC_ID")
    @Predicate(label = "unite.achat",type = UniteAchat.class,target = "many-to-one",search = true)
    private UniteAchat uniteachat ;
    
    @Predicate(label = "prix.vente.ht",type = Double.class,group =true,groupName = "group1",groupLabel = "informations.generales")
    private Double puvente ;
    
    @ManyToOne
    @JoinColumn(name = "UNGE_ID")
    @Predicate(label = "unite.vente",type = UniteGestion.class,target = "many-to-one",search = true)
    private UniteGestion unitevente ;
    
   @Predicate(label = "reference.fabriquant",group =true,groupName = "group1",groupLabel = "informations.generales")
    private String reference ;  
    
   @Predicate(label = "suivi.stock",updatable = false,target = "combobox",values = "Aucune;Sérialisé;CMUP;FIFO;LIFO;Par lot",group = true,groupName = "group2",groupLabel = "complement")
   private String politiquestock = "0" ;
   
   @Predicate(label = "cout.stockage",type = Double.class,group = true,groupName = "group2",groupLabel = "complement")
   private Double coutstockage =0.0;
   
   @Predicate(label = "cout.transport",type = Double.class,group = true,groupName = "group2",groupLabel = "complement")
   private Double couttransp=0.0;

   @ManyToOne
   @JoinColumn(name = "ART_ID")
   @Predicate(label = "substitution",type = Article.class,target = "many-to-one",group = true,groupName = "group2",groupLabel = "complement")
   private Article substitut ;
   
   @Predicate(label = "unite.poid",target = "combobox",values = "Tonne;Quintal;Kilogramme;Gramme;Milligramme",group = true,groupName = "group2",groupLabel = "complement")
   private String unitepoid ="0";
   
   @Predicate(label = "poid.net",type = Double.class,group = true,groupName = "group2",groupLabel = "complement")
   private Double poidnet ;
   
   @Predicate(label = "poid.brut",type = Double.class,group = true,groupName = "group2",groupLabel = "complement")
   private Double poidbrut ;
   
   @Predicate(label = "delai.livraison.jour",type = Short.class,group = true,groupName = "group2",groupLabel = "complement")
   private Short delaiL = 0;
   
   @Predicate(label = "garantie.jour",type = Short.class,group = true,groupName = "group2",groupLabel = "complement")
   private Short garantie = 0;
   
   @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "article")
//   @Predicate(label = " ",type = LienEmplacement.class,target = "one-to-many",group = true,groupName = "group3",groupLabel = "Stockage",edittable = true)
   private List<LienEmplacement> stockages = new ArrayList<LienEmplacement>();
   
   @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
   @JoinColumn(name = "ART_ID")
   @Predicate(label = " ",type = Controle.class,target = "one-to-many",group = true,groupName = "group4",groupLabel = "controle.qualite",edittable = true)
   private List<Controle> controles = new ArrayList<Controle>();
    /**
     * 
     */
    public Article() {
    }

    public Article(String image, String code, String intitule, FamilleArticle famille, String codebarre, Double puachat, UniteAchat uniteachat, Double puvente, UniteGestion unitevente, String reference) {
        this.image = image;
        this.code = code;
        this.intitule = intitule;
        this.famille = famille;
        this.codebarre = codebarre;
        this.puachat = puachat;
        this.uniteachat = uniteachat;
        this.puvente = puvente;
        this.unitevente = unitevente;
        this.reference = reference;
    }

    public Article(String image, String code, String intitule, FamilleArticle famille, String codebarre, Double puachat, UniteAchat uniteachat, Double puvente, UniteGestion unitevente, String reference, Article substitut, Double poidnet, Double poidbrut, long id, String designation, String moduleName) {
        super(id, designation, moduleName,0L);
        this.image = image;
        this.code = code;
        this.intitule = intitule;
        this.famille = famille;
        this.codebarre = codebarre;
        this.puachat = puachat;
        this.uniteachat = uniteachat;
        this.puvente = puvente;
        this.unitevente = unitevente;
        this.reference = reference;
        this.substitut = substitut;
        this.poidnet = poidnet;
        this.poidbrut = poidbrut;
    }

    
    /**
     * 
     * @param art 
     */
    public Article(Article art) {
        super(art.id, art.designation, art.moduleName ,art.compareid);
        this.image = art.image;
        this.code = art.code;
        this.intitule = art.intitule;
        this.famille = art.famille;
        this.codebarre = art.codebarre;
        this.puachat = art.puachat;
        this.uniteachat = art.uniteachat;
        this.puvente = art.puvente;
        this.unitevente = art.unitevente;
        this.reference = art.reference;
        this.politiquestock = art.politiquestock;
        this.coutstockage = art.coutstockage;
        this.couttransp = art.couttransp;
        if(art.substitut!=null){
            this.substitut = new Article(art.substitut);
        }
        this.unitepoid = art.unitepoid;
        this.poidnet = art.poidnet;
        this.poidbrut = art.poidbrut;
        this.delaiL = art.delaiL;
        this.garantie = art.garantie;
        this.achete = art.getAchete();
        this.vendu = art.getVendu();
        this.setOwnermodule(art.getOwnermodule());
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

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Boolean getVendu() {
        return vendu;
    }

    public void setVendu(Boolean vendu) {
        this.vendu = vendu;
    }

    public Boolean getAchete() {
        return achete;
    }

    public void setAchete(Boolean achete) {
        this.achete = achete;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean getActif() {
        return actif;
    }

    public void setActif(Boolean actif) {
        this.actif = actif;
    }

    public FamilleArticle getFamille() {
        return famille;
    }

    public void setFamille(FamilleArticle famille) {
        this.famille = famille;
    }

    public String getCodebarre() {
        return codebarre;
    }

    public void setCodebarre(String codebarre) {
        this.codebarre = codebarre;
    }

    public Double getPuachat() {
        return puachat;
    }

    public void setPuachat(Double puachat) {
        this.puachat = puachat;
    }

    public UniteAchat getUniteachat() {
        return uniteachat;
    }

    public void setUniteachat(UniteAchat uniteachat) {
        this.uniteachat = uniteachat;
    }

    public Double getPuvente() {
        return puvente;
    }

    public void setPuvente(Double puvente) {
        this.puvente = puvente;
    }

    public UniteGestion getUnitevente() {
        return unitevente;
    }

    public void setUnitevente(UniteGestion unitevente) {
        this.unitevente = unitevente;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public List<LienEmplacement> getStockages() {
        return stockages;
    }

    public void setStockages(List<LienEmplacement> stockages) {
        this.stockages = stockages;
    }

    public List<Controle> getControles() {
        return controles;
    }

    public void setControles(List<Controle> controles) {
        this.controles = controles;
    }
    
    

    @Override
    public String getDesignation() {
        return code+" - "+intitule; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getModuleName() {
        return "teratechachat"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getOwnermodule() {
        return "baseaccount"; //To change body of generated methods, choose Tools | Templates.
    }
    
    

    @Override
    public String getListTitle() {
        return "articles"; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getEditTitle() {
        return "article"; //To change body of generated methods, choose Tools | Templates.
    }

    public String getPolitiquestock() {
        return politiquestock;
    }

    public void setPolitiquestock(String politiquestock) {
        this.politiquestock = politiquestock;
    }

    public Double getCoutstockage() {
        return coutstockage;
    }

    public void setCoutstockage(Double coutstockage) {
        this.coutstockage = coutstockage;
    }

    public Double getCouttransp() {
        return couttransp;
    }

    public void setCouttransp(Double couttransp) {
        this.couttransp = couttransp;
    }

    public Article getSubstitut() {
        return substitut;
    }

    public void setSubstitut(Article substitut) {
        this.substitut = substitut;
    }

    public String getUnitepoid() {
        return unitepoid;
    }

    public void setUnitepoid(String unitepoid) {
        this.unitepoid = unitepoid;
    }

    public Double getPoidnet() {
        return poidnet;
    }

    public void setPoidnet(Double poidnet) {
        this.poidnet = poidnet;
    }

    public Double getPoidbrut() {
        return poidbrut;
    }

    public void setPoidbrut(Double poidbrut) {
        this.poidbrut = poidbrut;
    }

    public Short getDelaiL() {
        return delaiL;
    }

    public void setDelaiL(Short delaiL) {
        this.delaiL = delaiL;
    }

    public Short getGarantie() {
        return garantie;
    }

    public void setGarantie(Short garantie) {
        this.garantie = garantie;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + Objects.hashCode(this.code);
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
        final Article other = (Article) obj;
        if (!Objects.equals(this.code, other.code)) {
            return false;
        }
        return true;
    }
    
    
    
    
    @Override
    public int compareTo(Article o) {
        //To change body of generated methods, choose Tools | Templates.
        return code.compareTo(o.code);
    }
    
}
