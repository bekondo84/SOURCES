package com.kerenedu.core.impl.report;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericdaolayer.dao.tools.Predicat;
import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.core.tools.DateHelper;
import com.kerenedu.configuration.CacheMemory;
import com.kerenedu.core.ifaces.report.ViewAnniversaireManagerLocal;
import com.kerenedu.core.ifaces.report.ViewAnniversaireManagerRemote;
import com.kerenedu.dao.ifaces.report.ViewAnniversaireDAOLocal;
import com.kerenedu.inscription.Inscription;
import com.kerenedu.inscription.InscriptionDAOLocal;
import com.kerenedu.model.report.ViewAnniversaire;
import com.kerenedu.model.report.ViewAnniversaireModal;
import com.megatim.common.annotations.OrderType;

@TransactionAttribute
@Stateless(mappedName = "ViewAnniversaireManager")
public class ViewAnniversaireManagerImpl
    extends AbstractGenericManager<ViewAnniversaire, Long>
    implements ViewAnniversaireManagerLocal, ViewAnniversaireManagerRemote
{

    @EJB(name = "ViewAnniversaireDAO")
    protected ViewAnniversaireDAOLocal dao;
    
    @EJB(name = "InscriptionDAO")
    protected InscriptionDAOLocal daoIns;

    public ViewAnniversaireManagerImpl() {
    }

    @Override
    public GenericDAO<ViewAnniversaire, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }
    
    @Override
   	public List<ViewAnniversaire> filter(List<Predicat> predicats, Map<String, OrderType> orders, Set<String> properties,
   			int firstResult, int maxResult) {
    	predicats.addAll(CacheMemory.defaultPredicatsCycleAnnee());
   		// TODO Auto-generated method stub
    	List<Inscription> listIns = daoIns.filter(predicats, orders, properties, firstResult, maxResult);
    	List<ViewAnniversaire> result = new ArrayList<ViewAnniversaire>();
    	for(Inscription i :listIns){
    		result.add(new ViewAnniversaire(i));
    	}
   		return result;
   	}

   	@Override
   	public ViewAnniversaire find(String propertyName, Long entityID) {
   		// TODO Auto-generated method stub
   		Inscription ins= daoIns.findByPrimaryKey("id", entityID);
   		//ViewAnniversaire elev = super.find(propertyName, entityID);
   		ViewAnniversaire inscrip = new ViewAnniversaire(ins);
//   		for(Eleve serv: elev.getElevelist()){
//   			inscrip.getElevelist().add(new Eleve(serv));
//   		}
   		return inscrip;
   	}

   	@Override
   	public List<ViewAnniversaire> findAll() {
   		// TODO Auto-generated method stub
   		RestrictionsContainer container = RestrictionsContainer.newInstance();
    	String annnescolaire = CacheMemory.getCurrentannee();
		if (annnescolaire != null) {
			container.addEq("anneScolaire", annnescolaire);
		}
   		List<Inscription> inst = daoIns.filter(container.getPredicats(), null, null, 0, 0);
   		List<ViewAnniversaire> result = new ArrayList<ViewAnniversaire>();
   		for(Inscription elev:inst){
   			result.add(new ViewAnniversaire(elev));
   		}
   		return result;
   	}
   	
   	

   	@Override
   	public ViewAnniversaire delete(Long id) {
   		// TODO Auto-generated method stub
   		ViewAnniversaire elev = super.delete(id);
   		return new ViewAnniversaire(elev);
   	}

	@Override
	public List<ViewAnniversaire> getCriteres(ViewAnniversaireModal critere) {
		// To change body of generated methods, choose Tools | Templates.
				RestrictionsContainer container = RestrictionsContainer.newInstance();
			
				if (critere != null) {
					container = RestrictionsContainer.newInstance();
//					if (critere.getDatInsfin() != null) {
//						container.addLe("eleve.dateNais", DateHelper.formatDate(critere.getDatInsfin()));
//					}
//					
//					if (critere.getDatInsdeb() != null) {
//						container.addGe("eleve.dateNais", DateHelper.formatDate(critere.getDatInsdeb()));
//					}

					if (critere.getClasse() != null) {
						container.addEq("classe.id", critere.getClasse().getId());
					}

				}
				List<Inscription> datas = daoIns.filter(container.getPredicats(), null, new HashSet<String>(), -1, 0);
				List<ViewAnniversaire> result = new ArrayList<ViewAnniversaire>();
				if (datas != null) {
					for (Inscription aniv : datas) {
						System.out.println("ViewAnniversaireManagerImpl.getCriteres() mois date "+DateHelper.getMonthName(aniv.getEleve().getDateNais()));
						System.out.println("ViewAnniversaireManagerImpl.getCriteres() mois select "+getMonth(Integer.parseInt(critere.getMois())));
					if(DateHelper.getMonthName(aniv.getEleve().getDateNais())==getMonth(Integer.parseInt(critere.getMois())))
						result.add(new ViewAnniversaire(aniv));
					}
				} // fin if(datas!=null)
				return result;
	}

	@Override
	public List<ViewAnniversaire> getCriteres(ViewAnniversaire critere) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public String getMonth(int month){
		String monthString;
        switch (month) {
            case 0:  monthString = "Janvier";
                     break;
            case 1:  monthString = "Fevrier";
                     break;
            case 2:  monthString = "Mars";
                     break;
            case 3:  monthString = "Avril";
                     break;
            case 4:  monthString = "Mai";
                     break;
            case 5:  monthString = "Juin";
                     break;
            case 6:  monthString = "Juillet";
                     break;
            case 7:  monthString = "Aout";
                     break;
            case 8:  monthString = "Septembre";
                     break;
            case 9: monthString = "Octobre";
                     break;
            case 10: monthString = "Novembre";
                     break;
            case 11: monthString = "Decembre";
                     break;
            default: monthString = "Invalid month";
                     break;
	}
		return monthString;
	}



}
