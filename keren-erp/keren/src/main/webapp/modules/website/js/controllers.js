/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use strict';
angular.module('keren.core.website' ,['pascalprecht.translate','keren.core.commons','keren.core.login']);
//        .config(function($translateProvider){
//            //$locationProvider.html5Mode(true);
//        $translateProvider.translations('en',{
//                Creer:'Create',
//                Importer:'Import',
//                Imprimer:'Print',
//                Filtres:'Filter',
//                Quitter:'Exit',
//                PJ:'Attachement(s)',
//                Actions:'Actions',
//                Enregistrer:'Save',
//                Ajouter:'Add',
//                Exporter:'Export',
//                Modifier:'Update',
//                Supprimer:'Delete',
//                Administration:'Administration',
//                Discussions:'Discussions',
//                Calendrier:'Calendar',
//                Applications:'Applications',
//                Configuration:'Configuration',
//                Documentation:'Documentation',
//                Assistance:'Assistance',
//                Préference:'Preference',
//                UPPWD:'Change the Password',
//                Déconnexion:'Log out',
//                MAIL:'Mailbox',
//                Conversation:'Tchat',
//                Administrateur:'Administrator',
//                EVENTTITLE:'Event Title',
//                Participants:'Participants',
//                EVENTDATE:'Event date',
//                Options:'Options',
//                Nom:'Name',
//                EMAIL:'Mail adrdress',
//                Socièté:'Partner',
//                Description:'Description',
//                BEGINDATETIME:'Start date and time',
//                Rappels:'Reminders',
//                Confidentialite:'Privacy',
//                ALLPEOPLE:'Everybody',
//                ONLYME:'Only me',
//                INNERONLY:'Internal user only',
//                SHOWHOURS:'Show time as',
//                Libre:'Free',
//                Occupée:'Occupied',
//                Duree:'Duration',
//                CALENDAROPTIONS:'Calendar options',
//                ADDELEMENT:'Add an element',
//                Lieu:'Place',
//                ALLDAY:'All day'
//
//            });
//            $translateProvider.translations('fr',{
//                PJ:'Pièce(s) Jointe(s)',
//                UPPWD:'Modifier le Mot de passe',
//                MAIL:'Boîte de réception',
//                EVENTTITLE:'Titre evenement',
//                EVENTDATE:'Date evenement',
//                EMAIL:'Adresse mail',
//                BEGINDATETIME:'Date et heure de debut',
//                ALLPEOPLE:'Tout le monde',
//                ONLYME:'Moi seulement',
//                INNERONLY:'Utilisateur interne seulement',
//                SHOWHOURS:'Afficher heure comme',
//                CALENDAROPTIONS:'Options du calendrier',
//                ADDELEMENT:'Ajouter un element',
//                ALLDAY:'Toute la journee'
//            });
//            $translateProvider.preferredLanguage('fr');
//           
//        });
angular.module('keren.core.website')
        .controller('WebSiteCtrl',['$rootScope','$scope','$translate','$timeout','$location','$http','$compile','authenticationService','commonsTools','backendService'
        , function($rootScope,$scope,$translate,$timeout,$location,$http, $compile, authenticationService,commonsTools,backendService){
             /**
              * Section des variables
              * @returns {undefined}
              */
             $scope.hostname = $location.host();    
             $scope.portvalue = $location.port();
             $scope.protocol = $location.protocol(); 
             $scope.username = null;
             //Cache des données du site web
             $scope.website = new Object();
             $scope.sessioncontext = null;
             //Web Element cache
             $scope.javascripts = new Array();
             $scope.login = function(){
                     var key= "kerensession";
                     var sessionobject = sessionStorage.getItem(key);
                     var webContext = null;
                     if(sessionobject!=null){
                         webContext = angular.fromJson(sessionStorage.getItem(key));
                         $scope.sessioncontext = webContext;
                         $rootScope.globals = new Object();
                         $rootScope.globals.currentUser = webContext.currentuser;
                     }//end if(sessionobject!=null){
//                    console.log("Authentication Login methode === "+$scope.username+" === "+sessionStorage.getItem(key));
                     if(angular.isDefined($rootScope.globals)
                             &&angular.isDefined($rootScope.globals.currentUser)
                             && $rootScope.globals.currentUser!=null){
//                             authenticationService.setCredentials($rootScope.globals.currentUser.username
//                                                            ,$rootScope.globals.currentUser.authdata,false);
                             $http.defaults.headers.common['Authorization']='Basic '+$rootScope.globals.currentUser.authdata; 
                             $rootScope.$broadcast("gotowebsite" , {username:$rootScope.globals.currentUser.username});
                      }else{
                         var urlPath = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/keren/auth/login/crypto"; 
                                $http.post(urlPath ,{username:"website@website",password:"websiteteratech2013"})
                                        .then(function(response){
    //                                        console.log("$scope.login = function() remember == encrypt pwd : "+response.data);                                           
                                            authenticationService.setCredentialWithoutBroatcast("website@website",response.data);
                                              //Chargement du module website pour besion de securité
                                            var url2 = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/websitemodule/categorie/"+$rootScope.website;
                                            $http.get(url2)
                                                    .then(function(response){
//                                                        console.log("websitemode.controller.===================== "+response.data);
                                                        if(response.data!='web_site'){
                                                            $rootScope.$broadcast("login" , {  });  
                                                            commonsTools.hideDialogLoading();
                                                        }//end if(response.data=='web_site'){
                                                    },function(error){
                                                        commonsTools.showMessageDialog(error);     
                                                    });
                                            $rootScope.$broadcast("gotowebsite" , {username:$rootScope.globals.currentUser.username});
                                        },function(error){
                                            commonsTools.showMessageDialog(error);                                            
                                        });    
                       }//end if(angular.isDefined($rootScope.globals.currentUser)
                           
               };
              /**
               * HTML parser parse the HTML 
               * treatment of javascript file css, less file
               * @param {type} template
               * @param {type} cible
               * @param {type} position
               * @returns {undefined}
               */
               $scope.parseHTML = function(template,cible,position){
                   var id = 'website_container';
                   if(angular.isDefined(position) && position!=null){
                        id = position;
                   }//end if(angular.isDefined(position) && position!=null){
                   var websitediv = document.createElement('div');
                   websitediv.setAttribute('ng-controller','portailwebCtrl');
                   var container = document.createElement('div');
                   websitediv.appendChild(container);
                   container.setAttribute('id',id);
//                   container.setAttribute('ng-init','load()');
                   container.innerHTML = template.script;   
                   container = angular.element(container);
                   var inputeleme = document.createElement('input');
                   inputeleme.setAttribute('id','website_cache');
                   inputeleme.setAttribute('type','hidden');
//                   inputeleme.setAttribute('ng-init','load()');
                   var key = generateurId();
                   var webSiteContext = new Object();
                   webSiteContext.currentuser = $rootScope.globals.currentUser;
                   webSiteContext.website = $rootScope.website;
                   if($scope.sessioncontext!=null&&angular.isDefined($scope.sessioncontext.data)){
                       webSiteContext.data = $scope.sessioncontext.data;
                   }//end if($scope.sessioncontext!=null&&angular.isDefined($scope.sessioncontext.data)){
                   sessionStorage.setItem(key,angular.toJson(webSiteContext));
                   sessionStorage.removeItem("kerensession");
                   inputeleme.setAttribute('value',key);
                   var items = container.find("input");
                   var trouve = false;
                   for(var i=0;i<items.length;i++){
                       if(items.eq(i).id=='website_cache'){
                           items.eq(i).replaceWith(inputeleme);
                           trouve = true;
                       }//end if(items.eq(i).id=='website_cache'){
                   }//end for(var i=0;i<items.length;i++){
                   if(trouve==false){
                       websitediv.prepend(inputeleme);
                   }//end if(trouve==false){
                   //raitement des inclusion
                   var items = container.find("include");
                   for(var i=0; i<items.length;i++){ 
                       var item = items.eq(i);
                       var type = item.attr('type');
                       var src = item.attr("src");
                       if(type=='css'){
                           var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/keren/auth/resource/text/"+src;
                           var linkElem = document.createElement('style');
                           linkElem.setAttribute('type','text/css');
                           linkElem.innerHTML='@import url("'+url+'");';
                           item.replaceWith(linkElem);
                       }else if(type=='less'){
                           var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/keren/auth/resource/text/"+src;
                           var linkElem = document.createElement('style');
                           linkElem.setAttribute('type','text/less');
                           linkElem.innerHTML='@import url("'+url+'");';
                           item.replaceWith(linkElem);
                       }else if(type=='javascript'){
                           var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/keren/auth/resource/text/"+src;
                           $('<script />', { type : 'text/javascript', src : url}).appendTo('body');
                           $scope.javascripts.push(url);
//                           var scriptElem = document.createElement('script');
//                           scriptElem.setAttribute('src',url);
                           item.remove();
                       }//end if(type=='css'){
                   }//end for(var i=0; i<items.length;i++){
                   angular.bootstrap(websitediv, ['keren.core.website']);
//                    var compileFn = $compile(container);
//                    compileFn($scope);           
                   var items = $(document).find("div");
                    for(var i=0; i<items.length;i++){ 
                         if(items.eq(i).attr("id")==id){
                             items.eq(i).replaceWith(websitediv);                             
                             break;
                        }//end if(items.eq(i).attr("id")=="detail-panel-body"){  
                    }//end for(var i=0; i<items.length;i++){                     
               };//end $scope.parseHTML = function(template){
               
               /**
                * 
                * @param {type} template
                * @param {type} cible
                * @param {type} fragment
                * @returns {undefined}
                */
               $scope.initialiseContext = function(template,cible , fragment){
                   //Traitement de la variable
                   if(template.var!=null){
                       $scope.website[template.var] = null;
                   }//end if(template.var!=null){
                   //Chargement des données
                   if(template.modele!=null && template.method!=null
                           && template.entity!=null &&template.var!=null){
                        var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/"+template.modele+"/"+template.entity+"/"+template.method;
                        $http.get(url).then(
                                function(response){
                                    $scope.website[template.var] = response.data;
                                    $rootScope.$broadcast("contextready" , {template:template,cible:cible,position:fragment});
                                },function(error){
                                    commonsTools.hideDialogLoading();
                                    commonsTools.showMessageDialog(error); 
                                });
                   }else{
                        $rootScope.$broadcast("contextready" , {template:template,cible:cible,position:fragment});
                   }//end if(template.modele!=null && template.method!=null&& template.entity!=null){
               } ;
               /**
                * Navigation from source page to templateid page
                * @param {type} templateid
                * @param {type} position
                * @returns {undefined}
                */
               $scope.gotodepreciate = function(templateid , fragment){
                   $rootScope.$broadcast("gotowebsite" , {username:$rootScope.globals.currentUser.username,cible:templateid,position:fragment});
               };//end $scope.goto = function(templateid , position){
               /**
                * 
                * @param {type} entity
                * @returns {undefined}
                */
               $scope.saveOrUpdate = function(entity){
                   
               };
                $scope.$on("gotowebsite",function(event,args){
                       $scope.username = args.username;
                       //Chargement du module web
                       var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/websitemodule/indexpage/"+$rootScope.website;
                       if(angular.isDefined(args.cible)
                               && args.cible!=null){
                             url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/websitemodule/fragment/"+$rootScope.website+"/"+args.cible;
                       }//end if(angular.isDefined(args.cible)
                       commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                       $http.get(url).then(
                               function(response){
                                   $scope.initialiseContext(response.data,args.cible,args.position);
                               },function(error){
                                   commonsTools.hideDialogLoading();
                                   commonsTools.showMessageDialog(error); 
                               });                       
                });//end $scope.$on("refreshList",function(event,args){
                $scope.$on("contextready" ,function(event ,  args){
                    $scope.parseHTML(args.template,args.cible,args.position);
                    commonsTools.hideDialogLoading();               
                });
                //Demarrage du site web
                $scope.login();
        }]);