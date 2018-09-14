/*                                       
 * To change this license header, choose License Headers in Project Properties.                                      
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.megatim.common.clients;

import com.bekosoftware.genericdaolayer.dao.tools.RestrictionsContainer;
import com.bekosoftware.genericmanagerlayer.core.ifaces.GenericManager;
import com.megatim.common.annotationsprocessor.ValidateAndFillBeans;
import com.megatim.common.context.ToolsContext;
import com.megatim.common.logger.CustomLogger;
import com.megatim.common.utilities.MessagesBundle;
import com.megatim.common.utilities.TypeOperation;
import com.megatim.security.enumerations.EnumStatutAutorisation;
import com.megatim.security.model.Autorisation;

import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.CacheRetrieveMode;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.border.TitledBorder;
import javax.swing.table.TableRowSorter;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JRViewer;

import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author DEV_4
 * @param <T>
 * @param <PK>
 */
public abstract class AbstractListTemplateFrame<T extends Object, PK extends Serializable>
        extends javax.swing.JInternalFrame implements MouseListener,
        KeyListener {

    /**
     * Creates new form AbstractListTemplateFrame
     */
    public AbstractListTemplateFrame() {
        super(" ", true, false, true);
        try {
            bundle = ResourceBundle.getBundle("messages");
            initComponents();
            pagination = getPagination();
            pagination.setSize(count());
            buildViewComponents();
            this.setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
            // buildViewComponents();
            this.setResizable(true);
            this.addKeyListener(this);
            if ((pagination == null)
                    || (pagination.getSize() <= pagination.getStepSize())) {
                btnext.setEnabled(false);
                btprevious.setEnabled(false);
                btfirst.setEnabled(false);
                btlast.setEnabled(false);
            }
            // Application des regles de securite
            securisationFrame();
        } catch (Exception ex) {
//            ex.printStackTrace();
            Messages.Messages(
                    getApplicationFrame(),
                    true,
                    NotificationType.ERROR,
                    "Erreur lors du traitement\nVeuillez consulter les d�tails",
                    ex.getMessage(), "");
            this.dispose();
        }
    }

    /**
     * Applique les r_gles de s�curit� a la fenetre courante
     */
    protected void securisationFrame() {

        if (getAutorisation() != null) {
            if (EnumStatutAutorisation.READ.equals(getAutorisation()
                    .getStatut()) || goToReadMode()) {
                btnew.setEnabled(false);
                btupdate.setEnabled(false);
                btdelete.setEnabled(false);
                btnValider.setEnabled(false);
                btnRejeter.setEnabled(false);
            } else if (EnumStatutAutorisation.WRITE.equals(getAutorisation()
                    .getStatut()) || goToWriteMode()) {
                btdelete.setEnabled(false);
            }
        }
    }
    
    /**
     * Operation Speciale qui permet egalement de passer en mode lecture
     */
    public boolean goToReadMode(){
       
       //On teste si l'actionName est dans la liste 
       if(ToolsContext.OBJECTS_LIST.contains(this.getAutorisation().getNomAutorisation())){
           return true;
       }else{
           return false;
       }
    }
    
    /**
     * Operation Speciale qui permet egalement de passer en mode ecriture
     */
    public boolean goToWriteMode(){
        return false;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// desc="Generated Code">//GEN-BEGIN:initComponents
	private void initComponents() {

		btnew2 = new javax.swing.JButton();
		headerPanel = new javax.swing.JPanel();
		bannierePanel = new javax.swing.JPanel();
		seachactionsPanel = new javax.swing.JPanel();
		btsearch = new javax.swing.JButton();
		btreset = new javax.swing.JButton();
		filterPanel = new javax.swing.JPanel();
		footerPanel = new javax.swing.JPanel();
		actionsPanel = new javax.swing.JPanel();
		btnew = new javax.swing.JButton();
		btupdate = new javax.swing.JButton();
		btdelete = new javax.swing.JButton();
		btview = new javax.swing.JButton();
		btprint = new javax.swing.JButton();
		btexporter = new javax.swing.JButton();
		btcancel = new javax.swing.JButton();
		btnValider = new javax.swing.JButton();
		btnRejeter = new javax.swing.JButton();
		datasPanel = new javax.swing.JPanel();
		jScrollPane1 = new javax.swing.JScrollPane();
		dataTable = new javax.swing.JTable();
		paginationPanel = new javax.swing.JPanel();
		btlast = new javax.swing.JButton();
		btfirst = new javax.swing.JButton();
		btprevious = new javax.swing.JButton();
		btnext = new javax.swing.JButton();
		lbpage = new javax.swing.JLabel();

		btnew2.setText("Nouveau");
		btnew.setIcon(getNewIcon());
		btnew.setText(getNewTitle());
		btnew.setToolTipText(getNewTooltip());
		btnew2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnew2ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout bannierePanelLayout = new javax.swing.GroupLayout(
				bannierePanel);
		bannierePanel.setLayout(bannierePanelLayout);
		bannierePanelLayout.setHorizontalGroup(bannierePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		bannierePanelLayout.setVerticalGroup(bannierePanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 37, Short.MAX_VALUE));

		seachactionsPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder(""));

		btsearch.setText("Rechercher");
		btsearch.setIcon(getSeachIcon());
		btsearch.setText(getSeachTitle());
		btsearch.setToolTipText(getSearchTooltip());
		btsearch.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btsearchActionPerformed(evt);
			}
		});

		btreset.setText("Effacer");
		btreset.setIcon(getClearIcon());
		btreset.setText(getClearTitle());
		btreset.setToolTipText(getClearTooltip());
		btreset.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btresetActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout seachactionsPanelLayout = new javax.swing.GroupLayout(
				seachactionsPanel);
		seachactionsPanel.setLayout(seachactionsPanelLayout);
		seachactionsPanelLayout.setHorizontalGroup(seachactionsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(btreset, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(btsearch, javax.swing.GroupLayout.DEFAULT_SIZE,
						126, Short.MAX_VALUE));
		seachactionsPanelLayout
				.setVerticalGroup(seachactionsPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								seachactionsPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												btsearch,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												32,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(btreset)
										.addContainerGap(
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)));

		seachactionsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { btreset, btsearch });
		TitledBorder title;
		title = BorderFactory.createTitledBorder(null,
				MessagesBundle.getMessage("Crit�res de  Recherche..."),
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				CommonsUtilities.getFontBorderGroupBox(),
				CommonsUtilities.COULEUR_TITRE_FRAME);
		filterPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(
				null, MessagesBundle.getMessage("Crit�res de  Recherche..."),
				javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
				javax.swing.border.TitledBorder.DEFAULT_POSITION,
				CommonsUtilities.getFontBorderGroupBox(),
				CommonsUtilities.COULEUR_TITRE_FRAME));

		javax.swing.GroupLayout filterPanelLayout = new javax.swing.GroupLayout(
				filterPanel);
		filterPanel.setLayout(filterPanelLayout);
		filterPanelLayout.setHorizontalGroup(filterPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		filterPanelLayout.setVerticalGroup(filterPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));

		javax.swing.GroupLayout headerPanelLayout = new javax.swing.GroupLayout(
				headerPanel);
		headerPanel.setLayout(headerPanelLayout);
		headerPanelLayout
				.setHorizontalGroup(headerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addComponent(bannierePanel,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)
						.addGroup(
								headerPanelLayout
										.createSequentialGroup()
										.addComponent(
												filterPanel,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												seachactionsPanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		headerPanelLayout
				.setVerticalGroup(headerPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								headerPanelLayout
										.createSequentialGroup()
										.addComponent(
												bannierePanel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addGroup(
												headerPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.LEADING)
														.addComponent(
																filterPanel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE)
														.addComponent(
																seachactionsPanel,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																Short.MAX_VALUE))));

		javax.swing.GroupLayout footerPanelLayout = new javax.swing.GroupLayout(
				footerPanel);
		footerPanel.setLayout(footerPanelLayout);
		footerPanelLayout.setHorizontalGroup(footerPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 0, Short.MAX_VALUE));
		footerPanelLayout.setVerticalGroup(footerPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 1, Short.MAX_VALUE));

		actionsPanel.setBorder(javax.swing.BorderFactory
				.createTitledBorder(javax.swing.BorderFactory
						.createTitledBorder("")));

		btnew.setText("Nouveau");
		btnew.setIcon(getNewIcon());
		btnew.setText(getNewTitle());
		btnew.setToolTipText(getNewTooltip());
		btnew.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnewActionPerformed(evt);
			}
		});

		btupdate.setText("Modifier");
		btupdate.setIcon(getUpdateIcon());
		btupdate.setText(getUpdateTitle());
		btupdate.setToolTipText(getUpdateTooltip());
		btupdate.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btupdateActionPerformed(evt);
			}
		});

		btdelete.setText("Supprimer");
		btdelete.setIcon(getDeleteIcon());
		btdelete.setText(getDeleteTitle());
		btdelete.setToolTipText(getDeleteTooltip());
		btdelete.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btdeleteActionPerformed(evt);
			}
		});

		btview.setText("Consulter");
		btview.setIcon(getViewIcon());
		btview.setText(getViewTitle());
		btview.setToolTipText(getViewTooltip());
		btview.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btviewActionPerformed(evt);
			}
		});

		btprint.setText("Imprimer");
		btprint.setIcon(getPrintIcon());
		btprint.setText(getPrintTitle());
		btprint.setToolTipText(getPrintTooltip());
		btprint.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btprintActionPerformed(evt);
			}
		});

		btexporter.setText("Exporter");
		btexporter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btexporterActionPerformed(evt);
			}
		});

		btcancel.setText("Quitter");
		btcancel.setIcon(getCancelIcon());
		btcancel.setText(getCancelTitle());
		btcancel.setToolTipText(getCancelTooltip());
		btcancel.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btcancelActionPerformed(evt);
			}
		});

		btnValider.setText("Valider");
		btnew.setIcon(getNewIcon());
		btnew.setText(getNewTitle());
		btnew.setToolTipText(getNewTooltip());
		btnValider.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnValiderActionPerformed(evt);
			}
		});

		btnRejeter.setText("Rejeter");
		btnew.setIcon(getNewIcon());
		btnew.setText(getNewTitle());
		btnew.setToolTipText(getNewTooltip());
		btnRejeter.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnRejeterActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout actionsPanelLayout = new javax.swing.GroupLayout(
				actionsPanel);
		actionsPanel.setLayout(actionsPanelLayout);
		actionsPanelLayout.setHorizontalGroup(actionsPanelLayout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(btnew,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btupdate,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btdelete,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btview,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btexporter,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btprint,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btcancel,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btnValider,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addComponent(btnRejeter,
						javax.swing.GroupLayout.Alignment.TRAILING,
						javax.swing.GroupLayout.PREFERRED_SIZE, 127,
						javax.swing.GroupLayout.PREFERRED_SIZE));

		actionsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL,
				new java.awt.Component[] { btcancel, btdelete, btexporter,
						btnew, btprint, btupdate, btview });

		actionsPanelLayout
				.setVerticalGroup(actionsPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								actionsPanelLayout
										.createSequentialGroup()
										.addContainerGap()
										.addComponent(
												btnValider,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnRejeter,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btnew,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btupdate,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												31,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btdelete,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												32,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btview,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												32,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btexporter,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												32,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED)
										.addComponent(
												btprint,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												32,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												34, Short.MAX_VALUE)
										.addComponent(
												btcancel,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												31,
												javax.swing.GroupLayout.PREFERRED_SIZE)));

		actionsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL,
				new java.awt.Component[] { btcancel, btdelete, btexporter,
						btnew, btprint, btupdate, btview });

		dataTable.setModel(new javax.swing.table.DefaultTableModel(
				new Object[][] { { null, null, null, null },
						{ null, null, null, null }, { null, null, null, null },
						{ null, null, null, null } }, new String[] { "Title 1",
						"Title 2", "Title 3", "Title 4" }));
		dataTable.getTableHeader()
				.setFont(CommonsUtilities.getFontHederTable());
		jScrollPane1.setViewportView(dataTable);

		javax.swing.GroupLayout datasPanelLayout = new javax.swing.GroupLayout(
				datasPanel);
		datasPanel.setLayout(datasPanelLayout);
		datasPanelLayout
				.setHorizontalGroup(datasPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 0, Short.MAX_VALUE)
						.addGroup(
								datasPanelLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												datasPanelLayout
														.createSequentialGroup()
														.addContainerGap()
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																793,
																Short.MAX_VALUE))));
		datasPanelLayout
				.setVerticalGroup(datasPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGap(0, 0, Short.MAX_VALUE)
						.addGroup(
								datasPanelLayout
										.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
										.addGroup(
												javax.swing.GroupLayout.Alignment.TRAILING,
												datasPanelLayout
														.createSequentialGroup()
														.addContainerGap()
														.addComponent(
																jScrollPane1,
																javax.swing.GroupLayout.DEFAULT_SIZE,
																301,
																Short.MAX_VALUE))));

		btlast.setText(">>");
		btlast.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btlastActionPerformed(evt);
			}
		});

		btfirst.setText("<<");
		btfirst.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btfirstActionPerformed(evt);
			}
		});

		btprevious.setText("<");
		btprevious.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btpreviousActionPerformed(evt);
			}
		});

		btnext.setText(">");
		btnext.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				btnextActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout paginationPanelLayout = new javax.swing.GroupLayout(
				paginationPanel);
		paginationPanel.setLayout(paginationPanelLayout);
		paginationPanelLayout
				.setHorizontalGroup(paginationPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								paginationPanelLayout
										.createSequentialGroup()
										.addGap(217, 217, 217)
										.addComponent(btfirst)
										.addGap(27, 27, 27)
										.addComponent(
												btprevious,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												54,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(29, 29, 29)
										.addComponent(
												btnext,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												52,
												javax.swing.GroupLayout.PREFERRED_SIZE)
										.addGap(18, 18, 18)
										.addComponent(btlast)
										.addPreferredGap(
												javax.swing.LayoutStyle.ComponentPlacement.RELATED,
												226, Short.MAX_VALUE)
										.addComponent(
												lbpage,
												javax.swing.GroupLayout.PREFERRED_SIZE,
												90,
												javax.swing.GroupLayout.PREFERRED_SIZE)));
		paginationPanelLayout
				.setVerticalGroup(paginationPanelLayout
						.createParallelGroup(
								javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(
								javax.swing.GroupLayout.Alignment.TRAILING,
								paginationPanelLayout
										.createSequentialGroup()
										.addGap(0, 0, Short.MAX_VALUE)
										.addGroup(
												paginationPanelLayout
														.createParallelGroup(
																javax.swing.GroupLayout.Alignment.BASELINE)
														.addComponent(btlast)
														.addComponent(btfirst)
														.addComponent(
																btprevious)
														.addComponent(btnext)
														.addComponent(
																lbpage,
																javax.swing.GroupLayout.PREFERRED_SIZE,
																23,
																javax.swing.GroupLayout.PREFERRED_SIZE))));

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addComponent(headerPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addComponent(footerPanel,
						javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addComponent(
														datasPanel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.addComponent(
														paginationPanel,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														javax.swing.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(actionsPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)));
		layout.setVerticalGroup(layout
				.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(headerPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addGroup(
										layout.createParallelGroup(
												javax.swing.GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		actionsPanel,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addGap(37, 37,
																		37))
												.addGroup(
														layout.createSequentialGroup()
																.addComponent(
																		datasPanel,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.addPreferredGap(
																		javax.swing.LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		paginationPanel,
																		javax.swing.GroupLayout.PREFERRED_SIZE,
																		javax.swing.GroupLayout.DEFAULT_SIZE,
																		javax.swing.GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										javax.swing.LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(footerPanel,
										javax.swing.GroupLayout.PREFERRED_SIZE,
										javax.swing.GroupLayout.DEFAULT_SIZE,
										javax.swing.GroupLayout.PREFERRED_SIZE)));

		pack();
	}// </editor-fold>//GEN-END:initComponents

    private void btfirstActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btfirstActionPerformed
        // TODO add your handling code here:
        firstStep();
    }// GEN-LAST:event_btfirstActionPerformed

    private void btlastActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btlastActionPerformed
        // TODO add your handling code here:
        lastStep();
    }// GEN-LAST:event_btlastActionPerformed

    private void btpreviousActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btpreviousActionPerformed
        // TODO add your handling code here:
        previousStep();
    }// GEN-LAST:event_btpreviousActionPerformed

    private void btsearchActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btsearchActionPerformed
        try {
            // TODO add your handling code here:
            if (getPagination() != null) {
                getPagination().setSize(count());
            }
            if ((pagination == null)
                    || (pagination.getSize() <= pagination.getStepSize())) {
                btnext.setEnabled(false);
                btprevious.setEnabled(false);
                btfirst.setEnabled(false);
                btlast.setEnabled(false);
            } else {
                btnext.setEnabled(true);
                btprevious.setEnabled(true);
                btfirst.setEnabled(true);
                btlast.setEnabled(true);
            }
            search();
        } catch (Exception ex) {
            Messages.Messages(
                    getApplicationFrame(),
                    true,
                    NotificationType.ERROR,
                    "Erreur lors du traitement\nVeuillez consulter les d�tails",
                    ex.getMessage(), "");
            // Logger.getLogger(AbstractListTemplateFrame.class.getName()).log(Level.SEVERE,
            // null, ex);
        }
    }// GEN-LAST:event_btsearchActionPerformed

    private void btresetActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btresetActionPerformed
        // TODO add your handling code here:
        reset();
    }// GEN-LAST:event_btresetActionPerformed

    private void btnewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnewActionPerformed
        try {
            // TODO add your handling code here:
            nouveau();
        } catch (Exception ex) {
            Messages.Messages(
                    getApplicationFrame(),
                    true,
                    NotificationType.ERROR,
                    "Erreur lors du traitement\nVeuillez consulter les d�tails",
                    ex.getMessage(), "");
            Logger.getLogger(AbstractListTemplateFrame.class.getName()).log(
                    Level.SEVERE, null, ex);
        }
    }// GEN-LAST:event_btnewActionPerformed

    private void btupdateActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btupdateActionPerformed
        try {
            // TODO add your handling code here:
            modifier();
        } catch (Exception ex) {ex.printStackTrace();
            Messages.Messages(
                    getApplicationFrame(),
                    true,
                    NotificationType.ERROR,
                    "Erreur lors du traitement\nVeuillez consulter les d�tails",
                    ex.getMessage(), "");
            // Logger.getLogger(AbstractListTemplateFrame.class.getName()).log(Level.SEVERE,
            // null, ex);
        }
    }// GEN-LAST:event_btupdateActionPerformed

    private void btdeleteActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btdeleteActionPerformed
        try {
            // TODO add your handling code here:
            supprimer();
        } catch (IllegalStateException ex) {

            CustomLogger.loggerWriteError(getClass(), "delete()",
                    MessagesBundle.getMessage("delete.entity.error"));
            Messages.Messages(getApplicationFrame(), true,
                    NotificationType.ERROR, MessagesBundle
                    .getMessage("delete.entity.error.msg"),
                    MessagesBundle
                    .getMessage("delete.entity.error.msg.detail"),
                    "");
        } catch (Exception ex) {
            ex.printStackTrace();
            CustomLogger.loggerWriteError(getClass(), "delete()",
                    MessagesBundle.getMessage("delete.entity.error"));
            Messages.Messages(
                    getApplicationFrame(),
                    true,
                    NotificationType.ERROR,
                    "Erreur lors du traitement\nVeuillez consulter les d�tails",
                    ex.getMessage(), "");
            // Logger.getLogger(AbstractListTemplateFrame.class.getName()).log(Level.SEVERE,
            // null, ex);
        }
    }// GEN-LAST:event_btdeleteActionPerformed

    private void btviewActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btviewActionPerformed
        try {
            // TODO add your handling code here:
            consulter();
        } catch (Exception ex) {
        	ex.printStackTrace();
            CustomLogger.loggerWriteError(getClass(), "consulter()",
                    MessagesBundle.getMessage("view.entity.error"));
            Messages.Messages(getApplicationFrame(), true,
                    NotificationType.INFOS,
                    "Aucun �l�ment n'est s�lectionn� !!!", ex.getMessage(),
                    "");
             Logger.getLogger(AbstractListTemplateFrame.class.getName()).log(Level.SEVERE,
             null, ex);
        }
    }// GEN-LAST:event_btviewActionPerformed

    private void btprintActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btprintActionPerformed
        try {
            // TODO add your handling code here:
            imprimer();
        } catch (Exception ex) {ex.printStackTrace();
            CustomLogger.loggerWriteError(getClass(), "imprimer()",
                    MessagesBundle.getMessage("print.entity.error"));
            Messages.Messages(
                    getApplicationFrame(),
                    true,
                    NotificationType.ERROR,
                    "Erreur lors du traitement\nVeuillez consulter les d�tails",
                    ex.toString(), "");
            // Logger.getLogger(AbstractListTemplateFrame.class.getName()).log(Level.SEVERE,
            // null, ex);
        }
    }// GEN-LAST:event_btprintActionPerformed

    private void btcancelActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btcancelActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }// GEN-LAST:event_btcancelActionPerformed

    private void btexporterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btexporterActionPerformed
        // TODO add your handling code here:
        exporter();
    }// GEN-LAST:event_btexporterActionPerformed

    private void btnextActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnextActionPerformed
        // TODO add your handling code here:
        nextStep();
    }// GEN-LAST:event_btnextActionPerformed

    private void btnValiderActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnValiderActionPerformed
        // TODO add your handling code here:
        valider();
    }// GEN-LAST:event_btnValiderActionPerformed

    private void btnew2ActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnew2ActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnew2ActionPerformed

    private void btnRejeterActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnRejeterActionPerformed
        // TODO add your handling code here:
        rejeter();
    }// GEN-LAST:event_btnRejeterActionPerformed

	// Variables declaration - do not modify//GEN-BEGIN:variables
	public javax.swing.JPanel actionsPanel;
	private javax.swing.JPanel bannierePanel;
	protected javax.swing.JButton btcancel;
	protected javax.swing.JButton btdelete;
	protected javax.swing.JButton btexporter;
	private javax.swing.JButton btfirst;
	private javax.swing.JButton btlast;
	protected javax.swing.JButton btnRejeter;
	protected javax.swing.JButton btnValider;
	protected javax.swing.JButton btnew;
	protected javax.swing.JButton btnew2;
	private javax.swing.JButton btnext;
	private javax.swing.JButton btprevious;
	protected javax.swing.JButton btprint;
	private javax.swing.JButton btreset;
	private javax.swing.JButton btsearch;
	protected javax.swing.JButton btupdate;
	protected javax.swing.JButton btview;
	public javax.swing.JTable dataTable;
	private javax.swing.JPanel datasPanel;
	private javax.swing.JPanel filterPanel;
	private javax.swing.JPanel footerPanel;
	private javax.swing.JPanel headerPanel;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JLabel lbpage;
	private javax.swing.JPanel paginationPanel;
	private javax.swing.JPanel seachactionsPanel;
	// End of variables declaration//GEN-END:variables

    public JPanel criteriaPanel = null;

    protected AbstractTableBaseListModel model = null;

    protected PaginationStep pagination = null;

    protected ResourceBundle bundle = null;

    protected boolean exportable = false;
    /**
     * Current
     */
    protected T currentObject;

    protected TypeOperation typeOperation = TypeOperation.NEW;

    /**
     * *************************************************************************
     * *****************************
     */
    /**
     *
     *********************************************************************************************************
     */
    /**
     * Construction de l'interface graphique
     */
    public void buildViewComponents() {
        // Construction de la banniere
        buildBanniere();
        buildFooterPanel();
        buildCriteriaPanel();
        RowSorter sorter = new TableRowSorter(getTableModel());
        dataTable.setRowSorter(sorter);
        dataTable.setModel(getTableModel());
        dataTable.addMouseListener(this);
        this.setResizable(true);
        this.setClosable(true);
        btexporter.setIcon(getExportIcon());
        btexporter.setVisible(setExportable());
        btexporter.setEnabled(setExportable());
        btnValider.setIcon(getBtnValiderIcon());
        btnValider.setVisible(ifBtnValiderIsVisible());
        btnRejeter.setIcon(getBtnRejeterIcon());
        btnRejeter.setVisible(ifBtnRejeterIsVisible());
        search();
    }

    /**
     * Construction de la banniere de l'application
     */
    public void buildBanniere() {
        JLabel lblIcon = new javax.swing.JLabel();
        JLabel lblTitle = new javax.swing.JLabel();
        // Intitialisation logo
        this.setFrameIcon(new ImageIcon(getIconePage().getImage()));
        this.setTitle(getTitle());
        // traitement du panel Header
        bannierePanel.setLayout(new BorderLayout());
        if (getHeaderPanel() != null) {
            JPanel entetePanel = getTireHeaderPanel();
            lblIcon.setIcon(getImage());
            entetePanel.add(lblIcon);

            lblTitle.setFont(CommonsUtilities.getFontTitreFrame());
            lblTitle.setForeground(CommonsUtilities.COULEUR_TITRE_FRAME);
            lblTitle.setText(getWindowTitle());
            lblTitle.setName("lblTitle"); // NOI18N
            entetePanel.add(lblTitle);
            bannierePanel.add(entetePanel, BorderLayout.CENTER);
        }
    }

    public void buildCriteriaPanel() {

        filterPanel.setLayout(new BorderLayout());
        if (getCriteriaPanel() != null) {
            filterPanel.add(getCriteriaPanel(), BorderLayout.CENTER);
        }
    }

    public void buildFooterPanel() {
        filterPanel.setLayout(new BorderLayout());
        if (getFooterPanel() != null) {
            filterPanel.add(getFooterPanel(), BorderLayout.CENTER);
        }
    }

    public JButton getBtnew() {
        return btnew;
    }

    public JButton getBtupdate() {
        return btupdate;
    }

    public JButton getBtdelete() {
        return btdelete;
    }

    public JButton getBtprint() {
        return btprint;
    }

    public JButton getBtnRejeter() {
        return btnRejeter;
    }

    public JButton getBtnValider() {
        return btnValider;
    }

    /**
     * Recupere les valeurs et construit des objects
     *
     * @throws IllegalAccessException
     */
    public void collecteCurrentObjectData() throws IllegalAccessException {

        if (getCriteriaPanel() == null) {
            return;
        }
        // Validation et transfert du contenu
        if (ValidateAndFillBeans.validateBean(getCriteriaPanel())) {
            currentObject = ValidateAndFillBeans.fillBean(getCriteriaPanel(),
                    currentObject);
        }

    }

    /**
     *
     * @return
     */
    protected ImageIcon getSeachIcon() {

        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_search.png"));
    }

    /**
     *
     * @return
     */
    protected String getSeachTitle() {

        return MessagesBundle.getMessage("search.title");
    }

    /**
     *
     * @return
     */
    protected String getSearchTooltip() {
        return MessagesBundle.getMessage("search.tooltip");
    }

    /**
     *
     * @return
     */
    protected ImageIcon getClearIcon() {

        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_clear.png"));
    }

    /**
     *
     * @return
     */
    protected String getClearTitle() {
        return MessagesBundle.getMessage("clear.title");
    }

    /**
     *
     * @return
     */
    protected String getClearTooltip() {
        return MessagesBundle.getMessage("clear.tooltip");
    }

    /**
     *
     * @return
     */
    protected ImageIcon getNewIcon() {

        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_add.png"));
    }

    /**
     *
     * @return
     */
    protected String getNewTitle() {
        return MessagesBundle.getMessage("new.title");
    }

    /**
     *
     * @return
     */
    protected String getNewTooltip() {
        return MessagesBundle.getMessage("new.tooltip");
    }

    /**
     *
     * @return
     */
    protected ImageIcon getUpdateIcon() {
        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_edit.png"));
    }

    /**
     *
     * @return
     */
    protected String getUpdateTitle() {
        return MessagesBundle.getMessage("update.title");
    }

    /**
     *
     * @return
     */
    protected String getUpdateTooltip() {
        return MessagesBundle.getMessage("update.tooltip");
    }

    /**
     *
     * @return
     */
    protected ImageIcon getDeleteIcon() {
        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_delete.png"));
    }

    /**
     *
     * @return
     */
    protected String getDeleteTitle() {
        return MessagesBundle.getMessage("delete.title");
    }

    /**
     *
     * @return
     */
    protected String getDeleteTooltip() {
        return MessagesBundle.getMessage("delete.tooltip");
    }

    /**
     *
     * @return
     */
    protected ImageIcon getViewIcon() {
        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_display.png"));
    }

    /**
     *
     * @return
     */
    protected String getViewTitle() {
        return MessagesBundle.getMessage("view.title");
    }

    /**
     *
     * @return
     */
    protected String getViewTooltip() {
        return MessagesBundle.getMessage("view.tooltip");
    }

    /**
     *
     * @return
     */
    protected ImageIcon getPrintIcon() {
        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_printer.png"));
    }

    /**
     *
     * @return
     */
    protected String getPrintTitle() {
        return MessagesBundle.getMessage("print.title");
    }

    /**
     *
     * @return
     */
    protected String getPrintTooltip() {
        return MessagesBundle.getMessage("print.tooltip");
    }

    protected ImageIcon getCancelIcon() {
        return new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_cancel.png"));
    }

    protected String getCancelTitle() {
        return MessagesBundle.getMessage("cancel.title");
    }

    /**
     *
     * @return
     */
    protected String getCancelTooltip() {
        return MessagesBundle.getMessage("cancel.tooltip");
    }

    /**
     * Reinitialisation des champs
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void reset() {

        try {
            if (getCriteriaPanel() != null) {
                ValidateAndFillBeans.cleanBeanField(getCriteriaPanel());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return
     */
    public T getCurrentObject() {
        return currentObject;
    }

    /**
     *
     * @param currentObject
     * @throws IllegalAccessException
     * @throws IllegalArgumentException
     */
    public void setCurrentObject(T currentObject)
            throws IllegalArgumentException, IllegalAccessException {
        this.currentObject = currentObject;

        boolean status = beforeSetCurrentObject();

        if (!status) {
            return;
        }

        if (getCriteriaPanel() == null) {
            return;
        }

        if (currentObject != null && getCriteriaPanel() != null) {
            ValidateAndFillBeans
                    .fillViewBean(currentObject, getCriteriaPanel());
        }

        if (typeOperation == TypeOperation.UPDATE) {
            ValidateAndFillBeans
                    .updatableAnnotationProcessor(getCriteriaPanel());
        } else if (typeOperation == TypeOperation.VIEW) {
            ValidateAndFillBeans.disableAllFields(getCriteriaPanel());
        }

        // Action apres mise a jour
        afterSetCurrentObject();
    }

    /**
     *
     * @throws IllegalArgumentException
     */
    public void search() {

        try {
            // Non nullit� des criteres
            if (getCriteriaPanel() == null) {
                return;
            }
            // non nulitte du manager
            if (getManager() == null) {
                return;
            }
            // Validite des valeurs des crit�res
            if (!ValidateAndFillBeans.validateBean(getCriteriaPanel())) {
                return;
            }

            if (pagination == null) {
                return;
            }

            this.afterSearch();
            // Constructeur de contenaur de pr�dicats
            RestrictionsContainer container = ValidateAndFillBeans
                    .buildSearchCriteria(getCriteriaPanel());
            //
            List<T> resultats = new ArrayList<T>();

            if (pagination == null) {
                resultats = getManager().filter(container.getPredicats(),
                        ValidateAndFillBeans.getOrdersCriteria(), null, getHintsValues(), 0, -1);
            } else {

                resultats = getManager().filter(container.getPredicats(),
                        ValidateAndFillBeans.getOrdersCriteria(), null, getHintsValues(),
                        pagination.getOffset(), pagination.getStepSize());
            }
            // Raffraichissement des donn�es
            refresh(resultats);
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex);

        }
    }

    /**
     *
     * @throws IllegalArgumentException
     */
    public void nextStep() {

        try {
            // Initialisation de la pagination si ce n'est pas le cas
            if (pagination == null) {
                pagination = getPagination();
                pagination.setSize(count());
            }
            if (pagination == null) {
                return;
            }

            // Recuperation de l'iterateur
            PaginationStepIterator iterator = pagination.iterator();
            // Verification de la possibilite d'avancer
            if (!iterator.hasNext()) {
                return;
            }
            // Recuperer l'etape suivante
            pagination = iterator.next();
            // recherche des donn�es
            search();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void previousStep() {
        try {
            // Initialisation de la pagination
            if (pagination == null) {
                pagination = getPagination();
                pagination.setSize(count());
            }
            if (pagination == null) {
                return;
            }
            // Recuperation de l'iterateur
            PaginationStepIterator iterator = pagination.iterator();
            // Verification de la possibilite d'avancer
            if (!iterator.hasPrevious()) {
                return;
            }
            // Recuperer l'etape suivante
            pagination = iterator.previous();
            // recherche des donn�es
            search();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void firstStep() {
        try {
            // Initialisation de la pagination
            if (pagination == null) {
                pagination = getPagination();
                pagination.setSize(count());
            }
            if (pagination == null) {
                return;
            }
            // Recuperation de l'iterateur
            PaginationStepIterator iterator = pagination.iterator();

            while (iterator.hasPrevious()) {
                // Recuperer l'etape suivante
                pagination = iterator.previous();
            }

            // recherche des donn�es
            search();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public void lastStep() {
        try {
            // Initialisation de la pagination
            if (pagination == null) {
                pagination = getPagination();
                pagination.setSize(count());
            }
            if (pagination == null) {
                return;
            }
            // Recuperation de l'iterateur
            PaginationStepIterator iterator = pagination.iterator();

            while (iterator.hasNext()) {
                // Recuperer l'etape suivante
                pagination = iterator.next();
            }

            // recherche des donn�es
            search();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     *
     * @return @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public long count() throws Exception {

        // Non nullit� des criteres
        if (getCriteriaPanel() == null) {
            return 0;
        }
        // non nulitte du manager
        if (getManager() == null) {
            return 0;
        }
        // Validite des valeurs des crit�res
        if (!ValidateAndFillBeans.validateBean(getCriteriaPanel())) {
            return 0;
        }
        // Constructeur de contenaur de pr�dicats
        RestrictionsContainer container = ValidateAndFillBeans
                .buildSearchCriteria(getCriteriaPanel());
        //
        List<T> resultats = new ArrayList<T>();

        resultats = getManager().filter(container.getPredicats(),
                ValidateAndFillBeans.getOrdersCriteria(), null, 0, -1);

        return resultats.size();
    }

    /**
     * Suppression des donn�es du SI
     */
    public void delete() throws Exception {

        if (getSelectedObjects() == null) {
            return;
        }

        boolean status = beforeDelete();

        if (!status) {
            return;
        }

        List<T> objects = getSelectedObjects();

        for (T object : objects) {
            getManager().delete(getPrimaryKey(object));
        }
        // Traitement apres suppression
        afterDelete();
        // Mise a jour table
        search();
    }

    /**
     * Raffraichissement de la fen�tre
     *
     * @param datas
     */
    protected void refresh(List<T> datas) {

        // Verification de la consistence des donn�es
        if (datas == null) {
            datas = new ArrayList<T>();
        }
        // Initialisation du model
        model = getTableModel();
        // Mise a jour de la table
        model.setElements(datas);
        // mise a jour de la table
        dataTable.setModel(model);

    }

    /**
     * Panel des op�rations contenant les donn�es de cette fen�tre
     *
     * @return
     */
    protected abstract JPanel getCriteriaPanel();

    /**
     * Obtention de l'identification de l'entit�
     *
     * @param Object
     * @return
     */
    protected abstract PK getPrimaryKey(T Object);

    /**
     * Classe responsable de la mise a disposition du manager
     *
     * @return
     */
    protected abstract GenericManager getManager() throws Exception;

    /**
     * Gestion de la pagination
     *
     * @return
     */
    protected abstract PaginationStep getPagination();

    /**
     * titre du dialog de l'�tat
     *
     * @author <a href="mailto:ntchuenna@yahoo.fr">nad�ge Tchuente</a> 12 nov.
     * 2016
     * @return
     */
    protected String getTitleEtat() {
        return "Previsualiser Etat....";
    }

    /**
     * Listes des elements selectionn�s dans le tableau
     *
     * @return
     */
    protected List<T> getSelectedObjects() {

        boolean updatable = true, deletable = true;
        // Recuperation de la liste des lignes selectionn�s
        int[] selectedRows = dataTable.getSelectedRows();

        if (selectedRows == null || selectedRows.length <= 0) {
            return null;
        }

        List<T> datas = model.getElements(selectedRows);

        for (T data : datas) {

            updatable &= updatable(data);
            deletable &= deletable(data);
        }

        // btmodifier.setEnabled(updatable);
        // btsupprimer.setEnabled(deletable);
        return datas;
    }

    /**
     *
     * Classe responsable de l'edition du manager
     */
    public void nouveau() throws Exception {
        JDialog nouveauFrame = getEditDialog(null, getManager(),
                TypeOperation.NEW, getApplicationFrame());
        nouveauFrame.setLocationRelativeTo(getApplicationFrame());
        nouveauFrame.setVisible(true);
        search();
    }

    /**
     * Supprime une element e
     */
    public void supprimer() throws Exception {

        if (getSelectedObjects() == null) {
            Messages.Messages(getApplicationFrame(), true,
                    NotificationType.INFOS,
                    "Aucune ligne n'est s�lectionn�e !", "", "");

            return;
        }

        boolean choice = Messages.Messages(getApplicationFrame(), true,
                NotificationType.WARNING,
                "Voulez-voous supprimer cet �l�ment ?", "");
        // L'utilisateur decide de suppripmer
        if (choice) {
            // recuperer la liste des elements
            List<T> objects = getSelectedObjects();

            for (T object : objects) {
                getManager().delete(getPrimaryKey(object));
            }
        }
        // Rechargement des donn�es
        search();
        // Chargement des donn�es dans la liste

    }

    /**
     * Valider une element e
     */
    public void valider() {

    }

    /**
     * Rejeter une element e
     */
    public void rejeter() {

    }

    /**
     * Classe responsable de lq ;ise q jour du manager
     */
    public void modifier() throws Exception {
        // Optention de la ligne selectionne
        int selectedRow = dataTable.getSelectedRow();

        if (selectedRow < 0) {
            Messages.Messages(getApplicationFrame(), true,
                    NotificationType.INFOS,
                    "Aucune ligne n'est s�lectionn�e !", "", "");
            return;
        }

        JDialog nouveauFrame = getEditDialog(getSelectedObjects().get(0),
                getManager(), TypeOperation.UPDATE, getApplicationFrame());
        nouveauFrame.setLocationRelativeTo(null);
        nouveauFrame.setVisible(true);
        search();
    }

    /**
     * Consultation
     */
    public void consulter() throws Exception {

        if (getSelectedObjects() == null) {
            Messages.Messages(getApplicationFrame(), true,
                    NotificationType.INFOS,
                    "Aucune ligne n'est s�lectionn�e !", "", "");
            return;
        }
        // Une ligne est selectionn�e
        JDialog nouveauFrame = getEditDialog(getSelectedObjects().get(0),
                getManager(), TypeOperation.VIEW, getApplicationFrame());
        nouveauFrame.setLocationRelativeTo(getApplicationFrame());
        nouveauFrame.setVisible(true);
    }

    /**
     *
     * @throws Exception
     * @throws Exception
     */
    public void imprimer() throws Exception {

        if (model == null || model.getElements() == null
                || model.getElements().isEmpty()) {
            return;
        }

        List<T> datas = new ArrayList<T>();
        // Aucun ligne selectionn�
        if (getSelectedObjects() == null) {
            RestrictionsContainer container = ValidateAndFillBeans.buildSearchCriteria(getCriteriaPanel());
            //
            List<T> resultats = new ArrayList<T>();

            resultats = getManager().filter(container.getPredicats(), ValidateAndFillBeans.getOrdersCriteria(), null, 0, -1);
            datas = resultats;
            //datas = model.getElements();
        } else {
            datas = getSelectedObjects();
        }
        String reportFilePath = getJasperFileName();
        // Si le fichier ne se termine pas par .jasper
        if (!reportFilePath.endsWith(".jasper")) {
            reportFilePath = reportFilePath + ".jasper";
        }

        // Creation d'un File sur le fichier
        File reportFile = new File(reportFilePath);
        // System.out.println("le nom du fichier jasper a afficher :" +
        // reportFile);
        // Si l'objet n'existe pas
        if (!reportFile.exists()) {
            throw new RuntimeException(
                    "gepa.report.helper.buildreport.reportfile.notexist");
        }

        // Si l'objet n'est pas un fichier
        if (!reportFile.isFile()) {
            throw new RuntimeException(
                    "gepa.report.helper.buildreport.reportfile.notexist");
        }

        // Chargement du Rapport
        JasperReport report = null;

        // Etat rempli
        JasperPrint jasperPrint = null;

        // Chargement du report
        report = (JasperReport) JRLoader.loadObject(reportFile);
        jasperPrint = JasperFillManager.fillReport(report, this
                .getReportParameters(), new JRBeanCollectionDataSource(datas,
                        false));

        // Presentation de l'etat
        this.showReport(jasperPrint);

    }

    /**
     * Methode de presentation d'un etat
     *
     * @param jasperPrint Etat � afficher
     */
    protected void showReport(JasperPrint jasperPrint) throws Exception {

        // Si le fichier jasper est null, arret
        if (jasperPrint == null) {
            return;
        }

        // Creation du viewer
        JRViewer jrViewer = new JRViewer(jasperPrint);

        // Creation de la boite de dilogue
        JDialog dialog = new JDialog(getApplicationFrame());

        // Injection du viewer dans la boite de dialogue
        dialog.getContentPane().add(jrViewer);

        // Gestion de la fermeture de la boite
        dialog.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        // Titre de la boite
        dialog.setTitle(getTitleEtat());

        // set modal a true
        dialog.setModal(true);
        // Dimension de la boite
        dialog.setSize(1300, 700);

        // dialog.pack();
        // Centrage de la boite sur l'IHM
        dialog.setLocationRelativeTo(getApplicationFrame());
        dialog.setVisible(true);

    }

    /**
     * Fen�tre principale de l'application
     *
     * @return
     */
    protected abstract JFrame getApplicationFrame();

    /**
     * nom de l'action permettant l'appel de cette fen�tre
     *
     * @return
     */
    protected abstract String getActionName();

    /**
     * Entete de page
     *
     * @return
     */
    protected JPanel getHeaderPanel() {
        return CommonsUtilities.headerPanel();
    }

    protected JPanel getTireHeaderPanel() {
        return CommonsUtilities.titreHeaderPanel();
    }

    /**
     * Pied de page
     *
     * @return
     */
    protected JPanel getFooterPanel() {

        return CommonsUtilities.footerPanel();
    }

    /**
     * Titre de la f�nete
     *
     * @return
     */
    protected abstract String getWindowTitle();

    /**
     * Model de la table
     *
     * @return
     */
    public abstract AbstractTableBaseListModel getTableModel();

    /**
     * Nom de la classe implementant l'interface
     *
     * @return
     */
    public abstract String getWindowClassName();

    /**
     * Icon de la f�netre
     *
     * @return
     */
    protected abstract ImageIcon getImage();

    /**
     * Parametres des raport
     *
     * @return
     */
    protected abstract Map getReportParameters();

    /**
     * Nom du fichier
     *
     * @return
     */
    protected abstract String getJasperFileName();

    // protected abstract String getBundle();
    /**
     * Fenetre interne d'edition pour
     *
     * @param object
     * @param manager
     * @param typeOperation
     * @param window
     * @return
     * @throws java.lang.Exception
     */
    protected abstract JDialog getEditDialog(T object, GenericManager manager,
            TypeOperation typeOperation, JFrame window) throws Exception;

    /**
     *
     * @param currentObject
     * @return
     */
    protected boolean updatable(T currentObject) {
        return true;
    }

    /**
     *
     * @param currentObject
     * @return
     */
    protected boolean deletable(T currentObject) {
        return true;
    }

    /**
     *
     * @param object
     * @param manager
     * @param typeOperation
     * @param window
     * @return
     */
    // public abstract JInternalFrame getEditInternalFrame(T object ,
    // GenericManager manager , TypeOperation typeOperation , JFrame window);
    /**
     *
     * @return
     */
    protected boolean beforeDelete() {
        return true;
    }

    /**
     *
     * @return
     */
    protected boolean afterDelete() {
        return true;
    }

    /**
     * Actions avant la mise a jour du currrentObject
     *
     * @param currentObject
     * @return
     */
    protected boolean beforeSetCurrentObject() {
        return true;
    }

    /**
     * Actions apres mise a jour du currentObject
     *
     * @return
     */
    protected boolean afterSetCurrentObject() {
        return true;
    }

    /**
     * Verificateur de la pr�condition avant sauvegarde
     *
     * @return
     */
    protected boolean beforeSave() {
        return true;
    }

    /**
     * Verification postcondition avant sauvegarde
     *
     * @return
     */
    public boolean afterSave() {
        return true;
    }

    /**
     * controle des champs dans le panel de recherche
     *
     */
    public void afterSearch() throws Exception {
    }
    
  
    /**
     *
     * @return
     */
    public JTable getDataTable() {
        return dataTable;
    }

    @SuppressWarnings("empty-statement")
    public void mouseExited(MouseEvent e) {
        ;
    }

    @SuppressWarnings("empty-statement")
    public void mouseEntered(MouseEvent e) {
        ;
    }

    @SuppressWarnings("empty-statement")
    public void mouseReleased(MouseEvent e) {
        ;
    }

    @SuppressWarnings("empty-statement")
    public void mousePressed(MouseEvent e) {
        ;
    }

    @SuppressWarnings("empty-statement")
    public void mouseClicked(MouseEvent e) {
        ;
    }

    /**
     *
     * @param e
     */
    public void keyReleased(KeyEvent e) {

        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            this.dispose();
        }
    }

    /**
     *
     * @param e
     */
    @SuppressWarnings("empty-statement")
    public void keyPressed(KeyEvent e) {
        ;
    }

    /**
     *
     * @param e
     */
    @SuppressWarnings("empty-statement")
    public void keyTyped(KeyEvent e) {
        ;
    }

    public void exporter() {

    }

    public boolean setExportable() {
        return false;
    }

    public Icon getExportIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/export_icon.png"));
        return icon;
    }

    protected boolean ifBtnValiderIsVisible() {
        return false;
    }

    protected Icon getBtnValiderIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_add.png"));
        return icon;
    }

    protected boolean ifBtnRejeterIsVisible() {
        return false;
    }

    protected Icon getBtnRejeterIcon() {
        ImageIcon icon = new ImageIcon(getClass().getResource(
                "/com/megatim/tools/images/button_edit.png"));
        return icon;
    }

    /**
     * Renvoi le niveau d'autorisation de l'utilisateur courant
     *
     * @return
     */
    protected Autorisation getAutorisation() {
        return null;
    }

    /**
     *
     * @return
     */
    public Map getHintsValues() {
        Map<String, Object> hints = new HashMap<String, Object>();
        hints.put("eclipselink.refresh", "true");
        hints.put(QueryHints.CACHE_RETRIEVE_MODE, CacheRetrieveMode.BYPASS);
        return hints;
    }
    
    /**
     * Passer en mode lecture
     */
    public void passerEnModeLecture() {
        btnew.setEnabled(false);
        btupdate.setEnabled(false);
        btdelete.setEnabled(false);
        btnValider.setEnabled(false);
        btnRejeter.setEnabled(false);
    }
    
    public ImageIcon getIconePage() {
        ImageIcon icon = CommonsUtilities.getIcone();
        return icon;
    }
}
