/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.core.resources;

import com.google.gson.Gson;
import com.kerem.core.CommonTools;
import com.kerem.core.FileHelper;
import java.io.File;
import java.io.IOException;
import javax.ws.rs.Path;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

/**
 *
 * @author Commercial_2
 */
@Path("/resource")
public class UploadFileRSImpl  implements UploadFileRS{

    
    @Override
    public Response downloadImageFileFreeForModule(HttpHeaders headers, String module, String filename) {
         try {
            Gson gson = new Gson();
            String _module = module;
             FileHelper.setCurrentModule(module);
            //To change body of generated methods, choose Tools | Templates.
            File fichier = new File(FileHelper.getStaticDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath()+"   module ==== "+_module);
            if(!fichier.exists()||!fichier.isFile()){
                FileHelper.setCurrentModule(null);
                fichier = new File(FileHelper.getStaticDirectory()+File.separator+"avatar.png");
            }
            return CommonTools.getImage(fichier);
        } catch (IOException ex) {
             Response.serverError().build();
        }
        return Response.noContent().build();
    }
    
    
    @Override
    public Response downloadImageFileFreeForModule(HttpHeaders headers, String entity, String module, String filename) {
         try {
            Gson gson = new Gson();
            String _module = module;
            if(_module!=null && !_module.trim().equalsIgnoreCase("kerencore")){
                FileHelper.setCurrentModule(module);
            }//end if(_module!=null && !_module.trim().equalsIgnoreCase("kerencore")){
            //To change body of generated methods, choose Tools | Templates.
            File fichier = new File(FileHelper.getStaticDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath()+"   module ==== "+_module+" ============= inject EJB : ");
            if(!fichier.exists()||!fichier.isFile()){
                FileHelper.setCurrentModule(null);
                fichier = new File(FileHelper.getStaticDirectory()+File.separator+"avatar.png");
            }
            return CommonTools.getImage(fichier,filename);
        } catch (IOException ex) {
             Response.serverError().build();
        }
        return Response.noContent().build();
    }

    
     @Override
    public Response downloadImageFileFree(@Context HttpHeaders headers ,String filename) {
        //To change body of generated methods, choose Tools | Templates.
        try {
            Gson gson = new Gson();
            String _module = null;
             FileHelper.setCurrentModule(null);
            if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
                _module = gson.fromJson(headers.getRequestHeader("modulename").get(0), String.class);               
            }//end if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
            if(_module!=null && !_module.isEmpty()){
                FileHelper.setCurrentModule(_module);
            }//end if(_module!=null && !_module.isEmpty()){
            //To change body of generated methods, choose Tools | Templates.
            File fichier = new File(FileHelper.getStaticDirectory()+File.separator+filename);
//            System.out.println(UploadFileRSImpl.class.toString()+" ==== "+fichier.getAbsolutePath()+"   module ==== "+_module);
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
    public Response downloadTextFile(@Context HttpHeaders headers ,String filename) {
       //To change body of generated methods, choose Tools | Templates.
         try{   Gson gson = new Gson();
                String _module = null;
                 FileHelper.setCurrentModule(null);
                if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
                    _module = gson.fromJson(headers.getRequestHeader("modulename").get(0), String.class);
//                    FileHelper.setCurrentModule(_module);
                }//end if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
                if(_module!=null && !_module.isEmpty()){
                    FileHelper.setCurrentModule(_module);
                }//end if(_module!=null && !_module.isEmpty()){
                FileHelper.setCurrentModule(_module);
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
    public String getTextFileContaint(@Context HttpHeaders headers ,String filename) {
        //To change body of generated methods, choose Tools | Templates.
         try{
                Gson gson = new Gson();
                String _module = null;
                 FileHelper.setCurrentModule(null);
                if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
                    _module = gson.fromJson(headers.getRequestHeader("modulename").get(0), String.class);
                    FileHelper.setCurrentModule(null);
                }//end if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
                if(_module!=null && !_module.isEmpty()){
                    FileHelper.setCurrentModule(_module);
                }//end if(_module!=null && !_module.isEmpty()){
                FileHelper.setCurrentModule(_module);
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

    @Override
    public Response downloadJsonFile(HttpHeaders headers, String filename) {
        //To change body of generated methods, choose Tools | Templates.
         try{
                Gson gson = new Gson();
//                String _module = null;
                 FileHelper.setCurrentModule(null);
//                if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
//                    _module = gson.fromJson(headers.getRequestHeader("modulename").get(0), String.class);
//                    FileHelper.setCurrentModule(null);
//                }//end if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
//                if(_module!=null && !_module.isEmpty()){
//                    FileHelper.setCurrentModule(_module);
//                }//end if(_module!=null && !_module.isEmpty()){
//                FileHelper.setCurrentModule(_module);
                String resourceDir = FileHelper.getStaticDirectory()+File.separator+filename;
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getText(file,file.getName());
                }else{
                    return null;
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }

    @Override
    public Response translate(HttpHeaders headers, String lang) {
        //To change body of generated methods, choose Tools | Template
        try{
//               System.out.println(UploadFileRSImpl.class.toString()+".translate(HttpHeaders headers, String lang) ========================================= language : "+lang);
                Gson gson = new Gson();
                FileHelper.setCurrentModule(null);
                String resourceDir = FileHelper.getConfigDirectory()+File.separator+lang+".json";
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getText(file,file.getName());
                }else{
                    return null;
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }
    
//    /**
//     * 
//     * @param filename
//     * @param entity
//     * @param modele
//     * @return 
//     */
//    private String getStorefile(String filename , String entity , String modele){
//        StringBuilder builder = new StringBuilder("SELECT STORE FROM T_RESREG r WHERE r.SRC ='"+filename+"'");
//        builder.append("  AND  r.ENTITY = '"+entity+"'");
//        if(!modele.trim().equalsIgnoreCase("kerencore")){
//            builder.append("  AND  r.MODELE = '"+modele+"'");
//        }//end if(!modele.trim().equalsIgnoreCase("kerencore")){
//        try {
//            javax.naming.Context ic = new javax.naming.InitialContext();
//            ds = (DataSource) ic.lookup("java:/KEREN");
//            Connection connection = ds.getConnection();
//            Statement statement = connection.createStatement();
//            ResultSet resultset = statement.executeQuery(builder.toString());
//            String store = null;
//            while(resultset.next()){
//                store = resultset.getString("STORE");
//            }//end while(resultset.next()){
//            return store;
//        } catch (SQLException ex) {
//            Logger.getLogger(UploadFileRSImpl.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (NamingException ex) {
//            Logger.getLogger(UploadFileRSImpl.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return null;
//    }

    @Override
    public Response getFile(HttpHeaders headers, String filename) {
        //To change body of generated methods, choose Tools | Templates.
         try{   Gson gson = new Gson();
                String _module = null;
                 FileHelper.setCurrentModule(null);
                if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
                    _module = gson.fromJson(headers.getRequestHeader("modulename").get(0), String.class);
//                    FileHelper.setCurrentModule(_module);
                }//end if(headers.getRequestHeader("modulename")!=null && !headers.getRequestHeader("modulename").isEmpty()){
                if(_module!=null && !_module.isEmpty()){
                    FileHelper.setCurrentModule(_module);
                }//end if(_module!=null && !_module.isEmpty()){
                FileHelper.setCurrentModule(_module);
                String resourceDir = FileHelper.getStaticDirectory()+File.separator+filename;
                File file = new File(resourceDir);
                if(file.exists()){
                    return CommonTools.getStream(file,filename);
                }else{
                    return Response.noContent().build();
                }//end if(file.exists())
        }catch(Exception ex){
            throw new WebApplicationException(ex, Response.serverError().build());
        }
    }

}
