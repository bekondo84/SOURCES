
package com.basaccount.core.impl.achats;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.basaccount.core.ifaces.achats.LigneReglementFournisseurManagerLocal;
import com.basaccount.core.ifaces.achats.LigneReglementFournisseurManagerRemote;
import com.basaccount.dao.ifaces.achats.LigneReglementFournisseurDAOLocal;
import com.basaccount.model.achats.LigneReglementFournisseur;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;

@TransactionAttribute
@Stateless(mappedName = "LigneReglementFournisseurManager")
public class LigneReglementFournisseurManagerImpl
    extends AbstractGenericManager<LigneReglementFournisseur, Long>
    implements LigneReglementFournisseurManagerLocal, LigneReglementFournisseurManagerRemote
{

    @EJB(name = "LigneReglementFournisseurDAO")
    protected LigneReglementFournisseurDAOLocal dao;

    public LigneReglementFournisseurManagerImpl() {
    }

    @Override
    public GenericDAO<LigneReglementFournisseur, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
