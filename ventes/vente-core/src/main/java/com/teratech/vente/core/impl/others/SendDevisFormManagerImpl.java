
package com.teratech.vente.core.impl.others;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import com.bekosoftware.genericdaolayer.dao.ifaces.GenericDAO;
import com.bekosoftware.genericmanagerlayer.core.impl.AbstractGenericManager;
import com.kerem.commons.KerenCoreMDBHelper;
import com.teratech.vente.core.ifaces.others.SendDevisFormManagerLocal;
import com.teratech.vente.core.ifaces.others.SendDevisFormManagerRemote;
import com.teratech.vente.dao.ifaces.others.SendDevisFormDAOLocal;
import com.teratech.vente.model.others.SendDevisForm;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.jms.JMSException;
import javax.jms.Topic;
import javax.naming.NamingException;

@TransactionAttribute
@Stateless(mappedName = "SendDevisFormManager")
public class SendDevisFormManagerImpl
    extends AbstractGenericManager<SendDevisForm, Long>
    implements SendDevisFormManagerLocal, SendDevisFormManagerRemote
{

    @EJB(name = "SendDevisFormDAO")
    protected SendDevisFormDAOLocal dao;

    @Resource(lookup = "java:/kerencore/coremdb")
    private Topic topic ;
    
    public SendDevisFormManagerImpl() {
    }

    @Override
    public GenericDAO<SendDevisForm, Long> getDao() {
        return dao;
    }

    @Override
    public String getEntityIdName() {
        return "id";
    }

    /**
     * mailMessageProducer(String from , String to , Date date , String subject,String text,List<String> cc,List<String> joints)
     * @param entity 
     */
    @Override
    public void sendMail(SendDevisForm entity) {
        try {
            //To change body of generated methods, choose Tools | Templates.
            KerenCoreMDBHelper.mailMessageProducer("bekondo84dieu@gmail.com", "bekondo84dieu@gmail.com", new Date(), "Test envoie devis", "Trouvez ci-joint le devis que vous avez demandez \n Nous attendons votre retour coordialement", new ArrayList<String>(), new ArrayList<String>());
        } catch (JMSException ex) {
            Logger.getLogger(SendDevisFormManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NamingException ex) {
            Logger.getLogger(SendDevisFormManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
