/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * BannierePanel.java
 *
 * Created on 30 mai 2016, 11:24:23
 */
package com.megatim.common.clients;

import java.awt.Graphics;
import java.awt.Image;

/**
 *
 * @author DEV_4
 */
public class BannierePanel extends javax.swing.JPanel {
	
	  Image image = null;
	    
	  public BannierePanel(Image image) {
	      this.image = image;
	  }
	  public BannierePanel() {
	  }
	  public void setImage(Image image){
	      this.image = image;
	  }
	  public Image getImage(Image image){
	      return image;
	  }
	  public void paintComponent(Graphics g) {
	      super.paintComponent(g); //paint background
	      if (image != null) { //there is a picture: draw it
	          int height = this.getSize().height;
	          int width = this.getSize().width;
	          //g.drawImage(image, 0, 0, this); //use image size          
	          g.drawImage(image,0,0, width, height, this);
	      }
	  } 
	  /*

    /** Creates new form BannierePanel */
  /*  public BannierePanel() {
        initComponents();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
   // @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
   /* private void initComponents() {

        jLabel1 = new javax.swing.JLabel();

        jLabel1.setText("                                     ICI EST INSCRIT LA BANNIERRE");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
        );
    }*/// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
   // private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables*/
}