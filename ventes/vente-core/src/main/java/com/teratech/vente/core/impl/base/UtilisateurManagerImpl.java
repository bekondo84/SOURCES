
package com.teratech.vente.core.impl.base;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.teratech.vente.core.ifaces.base.UtilisateurManagerLocal;
import com.teratech.vente.core.ifaces.base.UtilisateurManagerRemote;
import com.teratech.vente.dao.ifaces.base.UtilisateurDAOLocal;
import com.teratech.vente.model.base.Utilisateur;

@TransactionAttribute
@Stateless(mappedName = "UtilisateurManager")
public class UtilisateurManagerImpl
    extends AbstractGenericManager<Utilisateur, Long>
    implements UtilisateurManagerLocal, UtilisateurManagerRemote
{

    @EJB(name = "UtilisateurDAO")
    protected UtilisateurDAOLocal dao;

    public UtilisateurManagerImpl() {
    }

    @Override
    public GenericDAO<Utilisateur, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

}
