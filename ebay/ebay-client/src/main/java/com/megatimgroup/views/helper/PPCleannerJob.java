/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatimgroup.views.helper;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.utilities.MessagesBundle;
import com.megatimgroup.ebaytools.client.AbstractJobExecutor;
import com.megatimgroup.ebaytools.client.MessageType;
import com.megatimgroup.model.operations.DeclarationPP;
import java.util.List;
import java.util.ResourceBundle;

/**
 *
 * @author Commercial_2
 */
public class PPCleannerJob extends AbstractJobExecutor{

    /**
     * 
     * @param nextJob 
     */
    public PPCleannerJob(AbstractJobExecutor nextJob) {
        super(nextJob);
        
         
         MessagesBundle.setBundle(ResourceBundle.getBundle("consolemessages"));
        //bundle = MessagesBundle.getInstace("consolemessages");
        //Initialisation du Dispatcher validate.header
        initialieMessageDispatcher(MessagesBundle.getMessage("pp.clean.header"));  
    }

    
    
    @Override
    public void process() {
        try{
            //Message de demarrage
            publish(buildMessage(MessagesBundle.getMessage("pp.clean.start"), MessageType.INITIAL));
            //Injection du manager
            GenericManager manager = ManagerHelper.getManager2(DeclarationPP.class);         
            //Recuperation de la liste des Declarations
            List<DeclarationPP> datas = manager.findAll();
            if(datas!=null&&!datas.isEmpty()){     
               //Message de demarrage
               publish(buildMessage(MessagesBundle.getMessage("pp.clean.load"), MessageType.IN_PROGRESS));
               //Nettoyage de la base de données
               for(DeclarationPP data : datas){
                   manager.delete(data.getReference());
               }
               publish(buildMessage(MessagesBundle.getMessage("pp.clean.clean"), MessageType.IN_PROGRESS));
            }
            publish(buildMessage(MessagesBundle.getMessage("pp.clean.end"), MessageType.DONE));
        }catch(Exception ex){
             publish(buildMessage(MessagesBundle.getMessage("pp.clean.error"), MessageType.IN_ERROR));
       }
         if(nextJob!=null){
             nextJob.setListener(listener);            
            //Execution
            nextJob.process();
        }
    }
    
}
