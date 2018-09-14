/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.resources;

import com.kerem.core.CommonTools;
import com.kerem.core.FileHelper;
import java.io.File;
import java.io.IOException;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

/**
 *
 * @author Commercial_2
 */
@Path("/resource")
public class UploadFileRSImpl  implements UploadFileRS{

     @Override
    public Response downloadImageFileFree(String filename) {
        //To change body of generated methods, choose Tools | Templates.
        try {
            //To change body of generated methods, choose Tools | Templates.
            File fichier = new File(FileHelper.getStaticDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath());
            if(!fichier.exists()||!fichier.isFile()){
                fichier = new File(FileHelper.getStaticDirectory()+File.separator+"avatar.png");
            }
            return CommonTools.getImage(fichier);
        } catch (IOException ex) {
             Response.serverError().build();
        }
        return Response.noContent().build();
    }

     @Override
    public Response downloadTextFile(String filename) {
       //To change body of generated methods, choose Tools | Templates.
         try{
                String resourceDir = FileHelper.getStaticDirectory()+File.separator+filename;
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getText(file,file.getName());
                }else{
                    return Response.noContent().build();
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }

    @Override
    public String getTextFileContaint(String filename) {
        //To change body of generated methods, choose Tools | Templates.
         try{
                String resourceDir = FileHelper.getStaticDirectory()+File.separator+filename;
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getTextStream(file,file.getName());
                }else{
                    return null;
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }
    
}
