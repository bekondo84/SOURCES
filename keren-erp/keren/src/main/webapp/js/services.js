//Creation de service
/***
  Service responsable de la communication avec le back end
  Contient toute les fonctions pour les operations de CRUD
**/
angular.module('mainApp').filter('cut', function () {
        return function (value, wordwise, max, tail) {
            if (!value) return '';

            max = parseInt(max, 10);
            if (!max) return value;
            if (value.length <= max) return value;

            value = value.substr(0, max);
            if (wordwise) {
                var lastspace = value.lastIndexOf(' ');
                if (lastspace !== -1) {
                  //Also remove . and , so its gives a cleaner result.
                  if (value.charAt(lastspace-1) === '.' || value.charAt(lastspace-1) === ',') {
                    lastspace = lastspace - 1;
                  }
                  value = value.substr(0, lastspace);
                }
            }

            return value + (tail || ' …');
        };
    });
angular.module("mainApp")
.factory("restService" , function($http ,$resource , $location){
    var urlPath = "";
    //Resource rest pour l'interaction avec le back end
    var restResource = null;
     return{
            /**
             Build the restName base of the entityName
            **/
            url:function(entityName,moduleName){
                 urlPath = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/"+moduleName+"/"+entityName+"/";
                 restResource = $resource(urlPath+":path/:first/:max/:propertyname/:id/:value"
                       ,{path:'@path',first:'@first',max:'@max',id:'@id'}
                       ,{search:{
                                    method:'GET',
                                     isArray:true,
                                    params:{ first:'@first',
                                        max:'@max',
                                        path:'filter'
                                      }                              
                                 },
                          
                          update:{
                               method:'PUT'                               
                          },
                          
                          save:{
                               method:'POST'                               
                          }
                   });
                 //console.log("restService == "+urlPath);
                 return urlPath;
            },
            /**
               Return the metaData of the entity
            **/
            getMetaData:function(action){
                var lang = navigator.language || navigator.userLanguage;
//                console.log("Service.getMetaData:function(action) ===================== "+lang);
                  if(angular.isDefined(restResource)){
                      if(angular.isDefined(action) 
                              && action!=null){
                          $http.defaults.headers.common['action']=angular.toJson(action.id);
                      }//end if(angular.isDefined(action)
                     return  restResource.get({path:'meta'});
                  }
            },
            /**  
               Cancel etity
            **/
            cancel:function(entity){

            },
            /**
              Save entity in the back data store
              @entity: entity to save
            **/
            save: function(entity){
                //alert("Super vous avez appele le service restService avec :"+entity+" :::: "+urlPath);  
                 return  restResource.save(entity);
            },

            /**
              Save an array of entity in the back data store
              @entity: entities : array of entity to save
            **/
            saveAll: function(entities){

            },
            /**
              update entity in the back data store
              @entity: entity : entity to update
            **/
            update: function(entity){
                 //console.log("=========================== "+angular.toJson(entity));
                 return restResource.update(entity);
            },
           /**
              delete entity in the back data store
              @entity: entity : entity to delete
            **/
           delete:function(entity){             
              return restResource.delete({id:entity.id});
           },
           /**
             Supprime la liste des entites
             @entities: array of objects
           **/
           deleteAll:function(entities){
//               console.log("deleteAll ===== "+angular.toJson(entities));
                var ids = new Array();
                for(var i=0;i<entities.length;i++){
                    ids.push(entities[i].id);
                }
               //console.log()
               $http.defaults.headers.common['ids']=angular.toJson(ids);
                return restResource.delete();
           },
           /**
              find entity in the back data store with a specific ID
              @entity: id : id of the entity to find
            **/
           findById:function(id){
                 return restResource.get({id:id,path:'byid',propertyname:'id'});
           },
            /**
              find entity in the back data store with a specific ID
              @entity: id : id of the entity to find
            **/
           findByCompareid:function(id){
                 return restResource.get({id:id,path:'byid',propertyname:'compareid'});
           },
           /**
            * Retourn un boolean 
            * true : la contrainte d'unicite est verifier
            * false : la contrainte d'unicite est viol�e
            */
           uniqueProperty:function(propertyName , vale){
               
           },
           /**
            * Retourn un boolean 
            * true : la contrainte d'unicite est verifier
            * false : la contrainte d'unicite est viol�e
            */
           uniqueProperties:function(properties){
               $http.defaults.headers.common['properties']=angular.toJson(properties);
//               console.log(" uniqueProperties:function(properties) ========= == "+angular.toJson(properties));
                if(angular.isDefined(restResource)){
                    return  restResource.query({path:'unique'});
                }//end if(angular.isDefined(restResource))
           },
           /**
              find all entities in the back data store 
              
            **/
           findAll:function(){
               return restResource.query();
           },
           /**
              find entity in the back data store with a specific ID
              @entity: id : id of the entity to find
            **/
           findByStringProperty:function(propertyName , value){
                    return restResource.query({path:'bystringproperty',propertyname:propertyName,value:value});
           },
            /**
              find entity in the back data store with a specific ID
              @entity: id : id of the entity to find
            **/
           findByLongProperty:function(propertyName , value){
                    return restResource.query({path:'bylongproperty',propertyname:propertyName,value:value});
           },
           /**
              find entities which match a specific criteria in the back data store 
              @predicats: array of criteria({fieldName:name,fieldValue:value ,criteria:EQUAL}
              @firstResult:the index of the first result
              @maxResult : the max number of Items of the result
            **/
           filter:function(predicats ,firstResult , maxResult){
               $http.defaults.headers.common['predicats']= angular.toJson(predicats);               
                if(angular.isDefined(restResource)){
                    return  restResource.search({path:'filter',first:firstResult,max:maxResult});
                 }
           },
           treefilter:function(predicats ,firstResult , maxResult){
               $http.defaults.headers.common['predicats']= angular.toJson(predicats);               
                if(angular.isDefined(restResource)){
                    return  restResource.search({path:'tree',first:firstResult,max:maxResult});
                 }
           },
           /**
             return the number of items which match the specific criteria
            @predicats: array of criteria({fieldName:name,fieldValue:value ,criteria:EQUAL}
           **/
           count:function(predicats){
               $http.defaults.headers.common['predicats']= angular.toJson(predicats);               
                if(angular.isDefined(restResource)){
                    return  restResource.get({path:'count'});
                 }
           },
           /**
            * Upload ressource to the server
            * @param {type} files
            * @returns {undefined}
            */
           uploadFile:function(files){
//               console.log("restService.uploadFile:function(files) ========= "+angular.toJson(files));
               //URL de la resource responsable de transfert du fichier
               var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/resource/upload";
               var fd = new FormData();
               //Take the first select 
               fd.append("resources",files[0]);        
               return $http.post(url,fd
                    ,{withCredentials:true,headers:{'Content-Type':undefined},
                       transformRequest: angular.identity});               
           },
           uploadFile2:function(files){
//               console.log("restService.uploadFile:function(files) ========= "+angular.toJson(files));
               //URL de la resource responsable de transfert du fichier
               var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/resource/temporalupload";
               var fd = new FormData();
               //Take the first select 
               fd.append("resources",files[0]);        
               return $http.post(url,fd
                    ,{withCredentials:true,headers:{'Content-Type':undefined},
                       transformRequest: angular.identity});               
           },
            nothing:function(){
//               console.log("restService.uploadFile:function(files) ========= "+angular.toJson(files));
               //URL de la resource responsable de transfert du fichier
               var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/resource/empty";
               return $http.get(url);               
           },
           /**
            * 
            * @param {type} filename
            * @returns {undefined}
            */
           downloadPNG:function(filename,imgID,entity,modele,id){
//               console.log("services.downloadPNG:function(filename,imgID,entity,modele) ===================== entity : "+entity+" ========= modele : "+modele);
               var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/resource/png/"+filename;
               $http.defaults.headers.common['entity']=null;
               $http.defaults.headers.common['modulename']=null;
               $http.defaults.headers.common['entityid']=id;
               if(entity){
                   $http.defaults.headers.common['entity']= angular.lowercase(entity);
               }//end if(entity){
               if(modele){
                   $http.defaults.headers.common['modulename']= angular.lowercase(modele);
               } //end if(modele){
               $http.get(url, {responseType: "arraybuffer"})
                       .then(function(response){
                                var arrayBufferView = new Uint8Array(response.data );
                                var blob = new Blob( [ arrayBufferView ], { type: "image/png" } );
                                var urlCreator = window.URL || window.webkitURL;
                                var imageUrl = urlCreator.createObjectURL( blob );
                                var img = document.querySelector( "#"+imgID );
                                if(img){
                                    img.src = imageUrl;
                                }
                                return imageUrl;
                       },function(error){
                           
                       });
           },
           /**
            * 
            * @param {type} filename
            * @returns {undefined}
            */
           getPNG_URL:function(filename){
               var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/resource/png/"+filename;
               $http.get(url, {responseType: "arraybuffer"})
                       .then(function(response){
                                var arrayBufferView = new Uint8Array(response.data );
                                var blob = new Blob( [ arrayBufferView ], { type: "image/png" } );
                                var urlCreator = window.URL || window.webkitURL;
                                var imageUrl = urlCreator.createObjectURL( blob );
                                return  imageUrl;
                       },function(error){
                           
                       });
           }

     };
});
