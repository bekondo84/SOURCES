
package com.megatimgroup.views.referentiels;

import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.clients.AbstractListTemplateFrame;
import com.megatim.common.clients.AbstractTableBaseListModel;
import com.megatim.common.clients.PaginationStep;
import com.megatim.common.services.IocContext;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.common.utilities.ResourcesBundle;
import com.megatim.common.utilities.TypeOperation;
import com.megatimgroup.ebaytools.client.EbayUtilities;
import com.megatimgroup.model.referentiels.PrecisionDateNaissance;


/**
 * Fenetre interne PrecisionDateNaissanceIFrame
 * @since Sun Apr 30 15:49:47 WAT 2017
 * 
 */
public class PrecisionDateNaissanceIFrame
    extends AbstractListTemplateFrame<PrecisionDateNaissance, String>
{

    private MessagesBundle bundle;
    private ResourcesBundle resourcesbundle;
    public final static String FRAME_NAME = ("com.megatimgroup.views.referentiels.PrecisionDateNaissanceIFrame");

    /**
     * Constructeur par defaut
     * 
     */
    public PrecisionDateNaissanceIFrame() {
         super();
    }

    /**
     * Methode permettant de retourner la cle primaire
     * 
     * @param object
     * @return
     *     java.lang.String
     */
    @Override
    public String getPrimaryKey(PrecisionDateNaissance object) {
        return object.getCode() ;
    }

    /**
     * Methode permettant de retourner nom de l'action
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getActionName() {
         return "precisiondatenaissanceAction" ; 
    }

    /**
     * Methode permettant de retourner les parametres pour le reporting
     * 
     * @return
     *     java.util.Map
     */
    @Override
    public Map getReportParameters() {
        Map parametres = new HashMap();
        parametres.put("titreRapport", MessagesBundle.getMessage("precisiondatenaissance.title.report")) ;
        parametres.put("piedRapport", MessagesBundle.getMessage("precisiondatenaissance.footer.report")) ;
        parametres.put("nombreLignesRapport", getSelectedObjects().size()) ;
        return parametres ; 
    }

    /**
     * Methode permettant de retourner le nom du fichier Jasper
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getJasperFileName() {
        if ((getSelectedObjects().size()==0 || getSelectedObjects().isEmpty())) {
            return null;
        } else {
            if ((getSelectedObjects().size()==1)) {
                return (ResourcesBundle.getResource("precisiondatenaissance.detail.report"));
            } else {
                return (ResourcesBundle.getResource("precisiondatenaissance.list.report"));
            }
        }
    }

    /**
     * Methode permettant de retourner le titre de la fenetre
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getWindowTitle() {
        return (MessagesBundle.getMessage("precisiondatenaissance.list"));
    }

    /**
     * Methode permettant de retourner l'icone de la fenetre
     * 
     * @return
     *     javax.swing.ImageIcon
     */
    @Override
    public ImageIcon getImage() {
        try{
        ImageIcon icon = null;
        icon = new ImageIcon(getClass().getResource(ResourcesBundle.getResource("precisiondatenaissance.list.image")));
        return icon;
        } catch(Exception ex ){;}
        return null;
    }

    /**
     * Methode permettant de retourner le panel des champs
     * 
     * @return
     *     javax.swing.JPanel
     */
    @Override
    public JPanel getCriteriaPanel() {
        if ((criteriaPanel==null)) {
            criteriaPanel = new PrecisionDateNaissanceCriteriaPanel();
        }
        return (criteriaPanel);
    }

    /**
     * Methode permettant de retourner le manager
     * 
     * @return
     *     com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager
     */
    @Override
    public GenericManager<PrecisionDateNaissance, String> getManager()
        throws Exception
    {
        IocContext context = new IocContext();
        return (GenericManager)context.lookup("com.megatimgroup.core.impl.referentiels.PrecisionDateNaissanceManagerImpl");
    }

    /**
     * Methode permettant de retourner le modele de tableau
     * 
     * @return
     *     com.megatim.common.clients.AbstractTableBaseListModel
     */
    @Override
    public AbstractTableBaseListModel getTableModel() {
        if ((model==null)) {
             model = new PrecisionDateNaissanceModel();
        }
        return (model);
    }

    /**
     * Methode permettant de retourner le nom complet de la classe
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public String getWindowClassName() {
        return "com.megatimgroup.views.referentiels.PrecisionDateNaissanceIFrame" ;
    }

    /**
     * Methode permettant de retourner nom complet de la classe
     * 
     * @return
     *     java.lang.String
     */
    @Override
    public JDialog getEditDialog(PrecisionDateNaissance object, GenericManager manager, TypeOperation typeOperation, JFrame window)
        throws Exception
    {
        if ((object==null)) {
             object = new PrecisionDateNaissance();
        }
        PrecisionDateNaissanceDialog  dialog = new PrecisionDateNaissanceDialog(getApplicationFrame() ,true, typeOperation) ;
         dialog.setCurrentObject(object);
         dialog.setManager(manager);
        return (dialog);
    }

    /**
     * Methode permettant de retourner l'instance de la fenetre principale
     * 
     * @return
     *     javax.swing.JFrame
     */
    @Override
    public JFrame getApplicationFrame() {
        return com.megatimgroup.views.principal.PrincipalScreen.FRAME ;
    }

    /**
     * Methode permettant de retourner une instance de la pagination
     * 
     * @return
     *     com.megatim.common.clients.PaginationStep
     */
    @Override
    public PaginationStep getPagination() {
        pagination =  new PaginationStep(20);
        return pagination ; 
    }
    @Override
    public ImageIcon getIconePage() {
        ImageIcon icon = EbayUtilities.getIcone();
        return icon;
    }
}
