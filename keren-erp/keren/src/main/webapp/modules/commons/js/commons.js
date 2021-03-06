/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Module contenant tous les outils
 * @param {type} param1
 * @param {type} param2
 */
angular.module('keren.core.commons',['ngResource']);

angular.module('keren.core.commons')
        .directive('ace',['$timeout',function($timeout){
         var resizeEditor = function(editor,elem){
                  var lineHeight = editor.renderer.lineHeight;
                  var rows = editor.getSession().getLength();

                  $(elem).height(rows * lineHeight);
                  editor.resize();
                };
                return {
                  restrict:'A',
                  require:'?ngModel',
                  scope:true,
                  link:function(scope,elem,attrs,ngModel){
                    var node = elem[0];

                    var editor = ace.edit(node);

                    editor.setTheme('ace/theme/monokai');

                    ///var MarkdownMode = require('ace/mode/markdown').Mode;
                    editor.getSession().setMode('ace/mode/xml'); //new MarkdownMode()

                    // set editor options
                    editor.setShowPrintMargin(false);

                    // editor.setAutoScrollEditorIntoView(true);

                    // data binding to ngModel
                    ngModel.$render = function () {
                        editor.setValue(ngModel.$viewValue);
                        resizeEditor(editor, elem);
                    };

                    editor.on('change', function () {
                        $timeout(function () {
                            scope.$apply(function () {
                                var value = editor.getValue();
                                ngModel.$setViewValue(value);
                            });
                        });

                        resizeEditor(editor, elem);
                    });
                  }
                };
}]);
/**
 * CommonsTools Factory for Keren-ERP
 * @param {type} param1
 * @param {type} param2
 */
angular.module('keren.core.commons')
        .factory('commonsTools',function($filter,$compile,$http,$location,$interval){
            //Liste des contraintes
            var uniqueContraints = new Array();
            var stopTimer = 0;
            
            
            /**
             * Momento for navigation
             * @param {type} action: name of the action
             * @param {type} item:current data
             * @returns {undefined}
             */
            var NavigationItem = function(action, item,label){
                this.action = action;
                this.item = item;
                this.label = label;
            };
            NavigationItem.prototype={
                hydrate:function(){
                    var memento = angular.fromJson(this);
                    return memento;
                },
                dehydrate:function(memento){
                    var nav = angular.toJson(memento);
                    this.action = nav.action;
                    this.item = nav.item;
                },
                equals : function(memento){
                    return memento.action===this.action && memento.item===this.item;                      
                }
            };
            var NavigationContainer=function(){
                this.mementos = new Array();
                /**
                 * 
                 * @param {type} action
                 * @returns {undefined}
                 */
                this.addRule = function(action,item,label){
                    var memento = new NavigationItem(action,item,label);
                    var index = this.getKey(memento);
                    if(index<0){
                        this.mementos[this.mementos.length] = memento;
                    }else{
                        this.mementos[index] = memento;
                    }//end if(index<0){
                },
                this.getRule = function(key){
                    return this.mementos[key];
                },
                this.getKey = function(memento){
                    for(var i=0;i<this.mementos.length;i++){
                        var data = this.mementos[i];
                        if(memento.equals(data)===true){
                            return i;
                        }//end if(memento.equals(data)===true){
                    }//end for(var i=0;i<this.mementos.length;i++){
                    return -1 ;
                },
                this.getMementos = function(){
                    return this.mementos;
                },
                this.reset = function(){
                    this.mementos = new Array();
                },
                /**
                 * Back to one step
                 * @returns {undefined}
                 */
                this.gotoPreview = function(){
                    this.mementos.pop();
                    return this.mementos[this.mementos.length-1];
                },
                this.gotoPreviewIFExist = function(){
                    var memento = this.mementos[this.mementos.length-1];
                    if(memento.item!==null){
                        this.gotoPreview();
                    }//end if(memento.item!==null){
                    return memento;
                },
                this.lastPage = function(){
                    if(this.mementos.length===0) return null;
                    return this.mementos[this.mementos.length-1]
                },
                this.setMementos = function(mementos){
                    if(!angular.isDefined(mementos)
                            ||!angular.isArray(mementos)){
                        this.mementos = new Array();
                    }else{
                        this.mementos = mementos;
                    }
                };
            };
            var NotifyStatutPanel=(function(){
               var instance;
                function createInstance(){
                    var object = new Object();
                    object.active=false;
                    return object;
                }
                return{
                    getInstance: function(){
                        if(!instance){
                            instance = createInstance();
                        }
                        return instance;
                    }
                };
            })();    
            var NotifyInstance =(function(){
                var instance ;
                function createInstance(){
                    var object = new Object();
                    object.instances=new Array();
                    return object;
                }
                return{
                    getInstance: function(){
                        if(!instance){
                            instance = createInstance();
                        }
                        return instance;
                    }
                };
            })();
            var WebSiteContext = (function(){
                var instance;
                function createInstance(){
                    var object = new Object();
                    object.currentuser=null;
                    object.website = null;
                    object.promise = null;
                    return object;
                }
                return{
                    getInstance: function(){
//                        console.log("Vous avez cachez ======================== "+angular.toJson(currentuser));
                        if(!instance){
                            instance = createInstance();
                        }
                        return instance;
                    }
                };
            })();
            var tchatContext =(function(){
                var instance;
                function createInstance(){
                    var object = new Object();
                    return object;
                }
                return{
                    getInstance: function(){
//                        console.log("Vous avez cachez ======================== "+angular.toJson(currentuser));
                        if(!instance){
                            instance = createInstance();
                        }
                        return instance;
                    }                    
                };
            })();
            /**
             * Construction d'interface Detail Display
             *Pattern chain of responsability
             */
            var EditPanelProvider =function(){
                //Liste des 
                this.chain = new Array();
            };//end var EditPanelProvider =function(){
            /**
             * Fenetre d'edition par defaut
             * @returns {undefined}
             */
            var DefaultEditPanel = function(){
                
            };
            /**
             * 
             * @param {type} idElement
             * @param {type} logo
             * @param {type} color
             * @param {type} opacity
             * @returns {undefined}
             */
            var showDialogLoadingFull = function(idElement, logo, color, opacity){
                    
                    $('#'+idElement).remove();
                
                    $('body').append("<div id="+idElement+" style='width:100%;height:100%;position:absolute;z-index:5000;text-align:center;background-color:black'></div>");
                    $('#'+idElement).append("<div id='dialogFullWindow' style='width:100%;margin:auto;margin-top:22%;color:white;text-align:center'>"+logo+"</div>");

                    //Changer le proprietes css
                    $('#'+idElement).css("opacity",opacity);
                    $('#dialogFullWindow').css("color",color);

                    //Afficher le dialog
                    $('#'+idElement).hide();
                    $('#'+idElement).fadeIn();
               };
             /**
              * Tchat Session
              * @param {type} currentuser
              * @returns {unresolved}
              */
                var nbreTchatBox = 0;
                var listeTchatBox = {};
            return {
                isexternemodule:function(name){
                    if(name=='discussionconf' || name=='calandar'||name=='configuration'||name=='application'){
                        return false ;
                    }else{
                        return true;
                    }//end if(name=='discussionconf' || name=='calandar'||name=='configuration'||name=='application'){
                },
                setWebContext:function(currentuser){return WebSiteContext.setInstance(currentuser);},
                getWebContext:function(){return WebSiteContext.getInstance();},
                startWebsiteWorker:function(scope){
                    var instance = WebSiteContext.getInstance();
                    if(instance.promise===null){
                        instance.promise = $interval(function(){
                            var key = $('#website_cache').attr('value');
                            if(angular.isDefined(key)){
                                var webcontext = angular.fromJson(sessionStorage.getItem(key));
                                scope.currentuser = webcontext.currentuser;
                                scope.website = webcontext.website;
//                                scope.company = scope.currentuser.company;
                                $http.defaults.headers.common['Authorization']='Basic '+scope.currentuser.authdata;
                                scope.ready(webcontext);
//                                console.log("Hello ce fichier est execute : "+key+" ====== "+angular.toJson(webcontext));
                                $interval.cancel(instance.promise);
                            }//end if(angular.isDefined(key)){
                        },1000);
                    }else{
                        $interval.cancel(instance.promise);
                        instance.promise =   $interval(function(){
                            var key = $('#website_cache').attr('value');
                            if(angular.isDefined(key)){
                                scope.currentuser = angular.fromJson(sessionStorage.getItem(key));
                                $http.defaults.headers.common['Authorization']='Basic '+scope.currentuser.authdata;
//                                console.log("Hello ce fichier est execute : "+key+" ====== "+angular.toJson(scope.currentuser));
                                $interval.cancel(instance.promise);
                            }
                        },1000);
                        
                    }
                    
                },
                /**
                 * 
                 * @param {type} meta
                 * @returns {undefined}
                 */
                getResources : function(meta,data){
                    var names = new Array();
                    if(meta){
                        for(var i=0 ; i<meta.columns.length;i++){
                            var column = meta.columns[i];
                            if(column.type==='image'||column.type==='file'){
                                names.push(data[column.fieldName]);
                            }//end if(column.type==='image'||column.type==='file'){
                        }//end for(var i=0 ; i<meta.columns.length;i++){
                    }//end if(meta){
                    return names;
                },
                /**
                 * 
                 * @param {type} scope
                 * @param {type} model
                 * @returns {Boolean}
                 */
                iseditable : function(scope , model,field){
//                    console.log("commons.iseditable : function(scope , model,field) ===== windowType : "+scope.windowType+" model : "+model+"  ==== field : "+angular.toJson(field));
                    if(field.metaData && field.metaData!=null){
                        if(field.metaData.createonfield==false){
                            return false;
                        }//end if(field.metaData.createonfield==false){
                    }//end if(field.metaData && field.metaData!=null){
                    var parts = model.split('.');
                    var metaData = scope.getParentMetaData(model); 
                    if(metaData.desableupdate==true){//element non modifiable
                        return false ;
                    }//end if(metaData.desableupdate==true){
                    if(scope.windowType=="view"){
                        return false ;
                    }else if((field.updatable==false)&& (scope.windowType=="update"||scope.windowType=="list")){
                        return false;
                    }else if((field.editable==false)&& (scope.windowType=="new"||scope.windowType=="list")){
                        return false;
                    }//end if(scope.windowType=="view")if(parts[0]=='currentObject'){                       
                    
                    return true;
                },
                /**
                 * 
                 * @param {type} scope
                 * @param {type} field
                 * @returns {Boolean}
                 */
                enableSelect : function(scope ,field){
                    if(scope.windowType=="view"){
                        return false ;
                    }else if((field.updatable==false)&& (scope.windowType=="update"||scope.windowType=="list")){
                        return false;
                    }else if(field.editable==false && (scope.windowType=="new"||scope.windowType=="list")){
                        return false;
                    }//end if(scope.windowType=="view")
                    return true;
                },
                /***
                 * 
                 * */
                printer : function(report_id){
                    var mywindow = window.open('', 'PRINT', 'height=650,width=900,top=100,left=100');
                    mywindow.document.write('<html><head><style>@media print {footer {page-break-after: always;}}</style>');
                    mywindow.document.write('</head><body >');
                    mywindow.document.write(document.getElementById(report_id).innerHTML);
                    mywindow.document.write('</body></html>');

                    mywindow.document.close(); // necessary for IE >= 10
                    mywindow.focus(); // necessary for IE >= 10*/

                    mywindow.print();
                    mywindow.close();
                    document.getElementById(report_id).innerHTML = "";
                },
                backtocore : function(scope){
                     var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/keren";
                     var key = $('#website_cache').attr('value');
                     var session = angular.fromJson(sessionStorage.getItem(key));
                     key= "kerensession";
                     var webSiteContext = new Object();
                     webSiteContext.currentuser = session.currentuser;
                     webSiteContext.website = session.website;
                     sessionStorage.setItem(key,angular.toJson(webSiteContext));
                     if(session.currentuser.username!=='website@website'){
                        window.location.replace(url);
                        location.reload();      
                      }//end if(session.currentuser.username!=='website@website'){
                },
                /**
                 * 
                 * @param {type} scope
                 * @param {type} websiteid
                 * @param {type} templateid
                 * @param {type} fragment
                 * @returns {undefined}
                 */
                goto : function(scope, websiteid,templateid , fragment){
                    var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/websitemodule/indexpage/"+websiteid;
                    if(angular.isDefined(templateid)
                            && templateid!=null){
                          url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/websitemodule/fragment/"+websiteid+"/"+templateid;
                    }//end if(angular.isDefined(args.cible)
                    this.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                    var stopmoteur = function(IdMoteur){clearInterval(IdMoteur);};
                    var hidenFn = function(){var instance = NotifyStatutPanel.getInstance(); if(instance.active===true){ instance.active = false; stopmoteur(stopTimer); var idElement = "dialogContent"; $('#'+idElement).fadeOut(function(){ $('#'+idElement).remove(); }); $('#'+idElement+"_Full").fadeOut(function(){ $('#'+idElement+"_Full").remove(); }); }};
                    $http.get(url).then(
                            function(response){
                                var template = response.data;
                                  var id = 'website_container';
                                if(angular.isDefined(fragment) && fragment!=null){
                                     id = fragment;
                                }//end if(angular.isDefined(position) && position!=null){
                                var container = document.createElement('div');
                                container.setAttribute('id',id);
             //                   container.setAttribute('ng-init','load()');
                                container.innerHTML = template.script;   
                                container = angular.element(container);                   
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
//                                        scope.javascripts.push(url);
             //                           var scriptElem = document.createElement('script');
             //                           scriptElem.setAttribute('src',url);
                                        item.remove();
                                    }//end if(type=='css'){
                                }//end for(var i=0; i<items.length;i++){                    
                                var compileFn = $compile(container);
                                compileFn(scope);           
                                var items = $(document).find("div");
                                 for(var i=0; i<items.length;i++){ 
                                      if(items.eq(i).attr("id")==id){
                                          items.eq(i).replaceWith(container);                             
                                          break;
                                     }//end if(items.eq(i).attr("id")=="detail-panel-body"){  
                                 }//end for(var i=0; i<items.length;i++){          
                                hidenFn();
                            },function(error){
                                hidenFn();
//                                this.showMessageDialog(error); 
                            });                       
                },
               
                /**
                 * 
                 * @param {type} model
                 * @param {type} fieldname
                 * @returns {undefined}
                 */
                keyupevent : function(target,model, fieldname,scope){
                    $('#'+target+'-'+fieldname+' .form-control').on('keyup',
                              function(event){
                                  var value =$('#'+target+'-'+fieldname+' input[class="form-control"]').val();
                                  var model_val = $('#select-'+fieldname).attr('ng-model');
//                                  console.log("commons.selectpickerKeyup ================  :::  === "+fieldname+" === "+value+"  == "+model_val);
                                  scope.keyupDataLoarder(model_val,value);
                            });
                    $('#'+target+'-'+fieldname+' .form-control').on('show.bs.select',
                              function(event){
                                  var value =$('#'+target+'-'+fieldname+' input[class="form-control"]').val();
                                  var model_val = $('#select-'+fieldname).attr('ng-model');
//                                  console.log("commons.selectpickerKeyup ================  :::  ===fieldname :  "+fieldname+" ===  value : "+value+"     ==  target : "+target+" =  model : "+model_val);
                                  scope.keyupDataLoarder(model_val,value);
                            });
                   
                },
                searchkeyevent:function(id,scope){
//                    console.log("commons.searchkeyevent ================  :::  === "+scope.searchCriteria+" === "+id);
                    $('#'+id).on('keyup',
                              function(event){
                                  scope.searchCriteria = $('#'+id).val();
//                                  console.log("commons.searchkeyevent ================  :::  === "+scope.searchCriteria+" === "+id);
                                  scope.loadDataSearch();
                              });
                },
                /**
                 * Attache keyup event on many-to-one and many-to-many 
                 * components
                 * @param {type} metaData
                 * @returns {undefined}
                 */
                selectpickerKeyup : function(metaData,model,scope){
                    if(!angular.isDefined(metaData)||metaData==null){
                        return ;
                    }//end if(!angular.isDefined(metaData)||metaData==null){
//                    console.log("commons.selectpickerKeyup ================  ::: "+model);
                    for(var i=0;i<metaData.columns.length;i++){
                        var field = metaData.columns[i];
                        if(field.type=='object'){
//                            console.log("0 - commons.selectpickerKeyup ================  ::: "+field.fieldName+" === ");
                            this.keyupevent('manytoone',model,field.fieldName,scope);
                        }else if(field.target=='many-to-many'){
                            this.keyupevent('manytomany',model,field.fieldName,scope);
                        }//end if(field.type=='object'
                    }//end for(var i=0;i<metaData.columns.length;i++){
                    for(var i=0;i<metaData.groups.length;i++){
                        var group = metaData.groups[i];
                        for(var j=0;j<group.columns.length;j++){
                            var field = group.columns[j];
                            if(field.type=='object'){
                                this.keyupevent('manytoone',model,field.fieldName,scope);
                            }else if(field.target=='many-to-many'){
                                this.keyupevent('manytomany',model,field.fieldName,scope);
                            }//end if(field.type=='object'
                        }//end for(var j=0;j<group.columns.length;j++){
                        //Cas des MetaArray
                    }//end for(var i=0;i<metaData.groups.length;i++){
                },
               //Tchat Session
               createBodyChatContent : function(){
                   $('body').append("<div id='tchatBodyContent' style='border:solid 1px green;position:fixed;z-index:9000; width:100%;top:100%;text-align:right;background-color:white'></div>");
               },
               /**
                * 
                * @param {type} idElement
                * @returns {listeTchatBox.isOpen}
                */
               isTchatOpen : function(idElement){ 
                   var instance = tchatContext.getInstance();
                   return angular.isDefined(instance[idElement])&&instance[idElement].active;
               },
               getNbreTChatBox : function(){return nbreTchatBox;},
               incrementeNbreTChatBox : function(){nbreTchatBox = nbreTchatBox + 1;},
               decrementeNbreTChatBox : function(){nbreTchatBox = nbreTchatBox - 1;},
               closeTchat : function(idElement){
                    this.decrementeNbreTChatBox();	
		    $('#'+idElement+'_tchatContent').remove();
                },
                addToTchatContext : function(zoneid,user,cible){
                    var instance = tchatContext.getInstance();
                    instance[zoneid] = new Object();
                    instance[zoneid].user = user;
                    instance[zoneid].cible = cible;
                    instance[zoneid].active = true;
                    instance[zoneid].messages = new Array();
                },
                getToTchatContext : function(zoneid){
                    var instance = tchatContext.getInstance();
                    return instance;
                },
               /**
                * 
                * @param {type} idElement
                * @param {type} idUser
                * @param {type} idFriend
                * @param {type} photoUser
                * @param {type} photoFriend
                * @param {type} nameFriend
                * @returns {undefined}
                */
               addChatZone : function(idElement,idUser,idFriend,photoUser,photoFriend,nameFriend,scope){
//                        this.decrementeNbreTChatBox();
                        
                        $('#'+idElement+'_tchatContent').remove();
                        $('#tchatBodyContent').append("<span id="+idElement+"_tchatContent style='width:300px;height:400px;vertical-align:top;margin-left:6px;display:inline-block;text-align:left;margin-top:-400px'></span>");
                        $('#'+idElement+'_tchatContent').append("<div id="+idElement+"_tchatTitleContent style='width:100%;height:8%;padding:5px;background-color:#276090;color:white;border-radius:5px 5px 0px 0px'></div>");
                        $('#'+idElement+'_tchatContent').append("<div id="+idElement+"_tchatTexteContent style='border-left:solid 1px #d3d3d3;border-right:solid 1px #d3d3d3;width:100%;height:82%;overflow:auto;padding:5px;background-color: white;'></div>");
                        $('#'+idElement+'_tchatContent').append("<div id="+idElement+"_tchatSaisieContent style='width:100%;height:10%;background-color: white;'></div>");
                        $('#'+idElement+'_tchatTitleContent').append("<span id="+idElement+"_tchatTitleTexte style='width:90%;display:inline-block;font-weight:bold'>"+nameFriend+"</span>");
                        $('#'+idElement+'_tchatTitleContent').append("<span id="+idElement+"_tchatRemove style='width:10%;text-align:center;display:inline-block;cursor:pointer'><i class='fa fa-remove'></i></span>");
                        $('#'+idElement+'_tchatSaisieContent').append("<input id="+idElement+"_tchatSaisie style='width:100%;height:100%;border:solid 1px #d3d3d3;padding:6px' placeholder='Ajouter un message'>");
                        //On incremente le nbreTchatBox
                        this.incrementeNbreTChatBox();
                        //On mentionne qu'on cree et deroule la tchatBox
                        listeTchatBox[idElement] = {isOpen:true};
                        gererEvenements();
                        function gererEvenements(){

                                $('#'+idElement+'_tchatSaisie').keyup(function(e) {

                                        if(e.which == 13){

                                                if($('#'+idElement+'_tchatSaisie').val().length != 0){
                                                        scope.addMessage(idElement,generateurIdAvance(), idUser, 1, photoUser, $('#'+idElement+'_tchatSaisie').val(), "A l'instant");

                                                        //On vide la zone de saisie
                                                        $('#'+idElement+'_tchatSaisie').val("");
                                                }
                                        }
                                });

                                $('#'+idElement+'_tchatRemove').click(function(e) {

                                        $('#'+idElement+'_tchatContent').fadeOut(function(){
                                                  var instance = tchatContext.getInstance();
                                                  instance[idElement].active=false;
//                                                decrementeNbreTChatBox();	
                                                $('#'+idElement+'_tchatContent').remove();
//                                                instance[idElement].active = false;
                                        });	

                                });

                                $('#'+idElement+'_tchatTitleTexte').click(function(e) {

                                        if(listeTchatBox[idElement].isOpen){

                                                $('#'+idElement+'_tchatContent').animate({'margin-top':'-30px' }, 500, function(){

                                                        listeTchatBox[idElement].isOpen = false;		
                                                });

                                        }else{

                                                $('#'+idElement+'_tchatContent').animate({'margin-top':'-400px' }, 500, function(){

                                                        listeTchatBox[idElement].isOpen = true;		
                                                });

                                        }

                                });
                        }
               },
               /**
                * 
                * @param {type} idParent
                * @param {type} idElement
                * @param {type} idUser
                * @param {type} sens
                * @param {type} photo
                * @param {type} message
                * @param {type} date
                * @returns {undefined}
                */
               addMessage : function(idParent,idElement,idUser,sens,photo,message,date,rootScope){
                   $('#'+idParent+'_tchatTexteContent').append("<div id="+idParent+idElement+"_messageTchat style='width:100%;padding:8px;'></div>");
	           if(sens == 0){
                            //Left
                            $('#'+idParent+idElement+"_messageTchat").append("<span id="+idParent+idElement+"_messageTchatPhotoContent style='margin-right:5px;width:40px;border:solid 1px #d3d3d3;display:inline-block;'></span>");
                            $('#'+idParent+idElement+"_messageTchat").append("<span id="+idParent+idElement+"_messageTchatTexteContent style='width:82%;vertical-align:top;display:inline-block;background-color:#eaeaea;padding:8px;border-radius:15px;word-wrap : break-word ;'></span>");

                            $('#'+idParent+idElement+"_messageTchatPhotoContent").append("<img id="+idParent+idElement+"_messageTchatPhoto style='width:100%;' src="+photo+">");
                            if(angular.isString(message)){
                              $('#'+idParent+idElement+"_messageTchatTexteContent").append("<div id="+idParent+idElement+"_messageTchatTexte style='font-size:89%'>"+message+"</div>");
                            }else{
                                $('#'+idParent+idElement+"_messageTchatTexteContent").append("<div id="+idParent+idElement+"_messageTchatTexte style='font-size:89%'>"+message.body+"</div>");
                            }//end if(angular.isString(message)){
                            $('#'+idParent+idElement+"_messageTchatTexteContent").append("<div id="+idParent+idElement+"_messageTchatDate style='font-style:italic;font-size:80%;text-align:right;'>"+date+"</div>");

                    }else{

                            //Right
                            $('#'+idParent+idElement+"_messageTchat").append("<span id="+idParent+idElement+"_messageTchatTexteContent style='margin-right:5px;width:82%;vertical-align:top;display:inline-block;background-color:#0484f7;color:white;padding:8px;border-radius:15px;word-wrap : break-word ;'></span>");
                            $('#'+idParent+idElement+"_messageTchat").append("<span id="+idParent+idElement+"_messageTchatPhotoContent style='width:40px;border:solid 1px #d3d3d3;display:inline-block;'></span>");

                            $('#'+idParent+idElement+"_messageTchatPhotoContent").append("<img id="+idParent+idElement+"_messageTchatPhoto style='width:100%;' src="+photo+">");
                            if(angular.isString(message)){
                                $('#'+idParent+idElement+"_messageTchatTexteContent").append("<div id="+idParent+idElement+"_messageTchatTexte style='font-size:89%'>"+message+"</div>");
                            }else{
                               $('#'+idParent+idElement+"_messageTchatTexteContent").append("<div id="+idParent+idElement+"_messageTchatTexte style='font-size:89%'>"+message.body+"</div>");
                            }
                            $('#'+idParent+idElement+"_messageTchatTexteContent").append("<div id="+idParent+idElement+"_messageTchatDate style='font-style:italic;font-size:80%;text-align:right;'>"+date+"</div>");
                    }
                    //On scroll vers le bas
                    $('#'+idParent+'_tchatTexteContent').scrollTop(1000000000,'bottom');
                    //Save en bd
                    var instance = tchatContext.getInstance();
                    var canal = null;
                    var user = null;
//                    console.log("commons.addMessage : function(idParent,idElement,idUser,sens,photo,message,date,rootScope)============= "+" ===Element : "+idElement+"========="+angular.toJson(instance));
                    if(instance[idParent].cible.editTitle=='CANAL'){
                        canal = instance[idParent].cible;
                    }else{
                        user = instance[idParent].cible;
                    }//end if(instance[idElement].user.editTitle=='CANAL'){
                    var msge = message;
                    if(angular.isString(message)){
                        msge = this.createemptyMessage(instance[idParent].user,canal,user,message);
                        msge.sender=instance[idParent].user; 
//                        console.log("commons.addMessage : function(idParent,idElement,idUser,sens,photo,message,date,rootScope) ============ ")
                        rootScope.$broadcast("new_message" , {message:msge});
                    }//end if(angular.isString(message)){                   
               }, 
               /**
                * 
                * @param {type} sender
                * @param {type} canal
                * @param {type} reciever
                * @param {type} body
                * @returns {commons_L61.commonsAnonym$6.createemptyMessage.message|Object}
                */
              createemptyMessage : function(sender , canal,reciever,body){
                var message = new Object();
                message.id = -1;message.compareid=-1;message.designation=null;
                message.editTitle=null;message.listTitle=null;message.moduleName=null;
                message.selected=false;message.createonfield=true;message.desablecreate=false;
                message.desabledelete=false;message.serial=null;message.activefilelien=false;
                message.footerScript=null;message.activatefollower=false;message.date=new Date();
                message.status=false;message.piecesjointe=new Array();message.sender=sender;
                message.canal=canal;message.reciever=reciever;message.body=body;//message.senders=[];
                return message;
            }, 
            /**
             * 
             * @param {type} message
             * @returns {undefined}
             */
            sendAction : function(message){
                var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/smessage/send/"+message.sender.id;
//                    commonsTools.showDialogLoading("Chargement ...","white","#9370db","0%","0%");
                $http.post(url,message)
                        .then(function(response){                                                        
//                                commonsTools.hideDialogLoading();
                        },function(error){
                           this.showDialogLoading(error);
                        });
                
            },
            /**
             * Parse theme to inject javascript ,css and less file content
             * @param {type} view
             * @returns {undefined}
             */
            themeParser:function(view){
                var container = angular.element(view);
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
//                            scope.javascripts.push(url);
//                           var scriptElem = document.createElement('script');
//                           scriptElem.setAttribute('src',url);
                           item.remove();
                       }//end if(type=='css'){
                }//end for(var i=0; i<items.length;i++){
                return container;
            },
            /**
             * Builder of the custom principal screen
             * @param {type} theme
             * @returns {undefined}
             */
            principalScreenBuilder:function(theme,scope){
//                    console.log("commons.principalScreenBuilder(theme,scope) ======== "+theme.script);
                if(angular.isDefined(theme) && angular.isDefined(theme.script)){
                    //Parse the HTML theme
                    var viewElem = document.createElement("div");
                    viewElem.setAttribute('id' , 'modulescontainer');
                    viewElem.setAttribute('style' , "height: 100%;width: 100%;position: absolute;");
                    viewElem.innerHTML = theme.script;                
                    var container = this.themeParser(viewElem);
                    var compileFn = $compile(container);
                     compileFn(scope);
                     var items = $(document).find("div");
                     for(var i=0; i<items.length;i++){                 
                         if(items.eq(i).attr("id")==="modulescontainer"){
                               items.eq(i).replaceWith(container);
                               scope.defaultui = false;
//                                   console.log("commons.principalScreenBuilder =============== trouve");
                         }//end if(items.eq(i).attr("id")=="datatable"){ 
                     }//end for(var i=0; i<items.length;i++){                    
                }//end if(angular.isDefined(theme) && angular.isDefined(theme.script)){                    
            },
                /**
                 * 
                 * @param {type} theme
                 * @param {type} scope
                 * @returns {undefined}
                 */
                treeTemplateBuilder:function(theme,scope){
                    if(angular.isDefined(theme) && angular.isDefined(theme.tree)){
                         var viewElem = document.createElement("div");
                         viewElem.setAttribute('id' , 'container');
//                         viewElem.setAttribute('ng-show' , 'principalscreen==false');
                         viewElem.innerHTML = theme.tree;
                         var compileFn = $compile(viewElem);
                         compileFn(scope);
                         var items = $(document).find("div");
                         for(var i=0; i<items.length;i++){                 
                             if(items.eq(i).attr("id")=="container"){
                                   items.eq(i).replaceWith(viewElem);
//                                   console.log("commons.principalScreenBuilder =============== trouve");
                             }//end if(items.eq(i).attr("id")=="datatable"){ 
                         }//end for(var i=0; i<items.length;i++){      
                    }//end if(angular.isDefined(theme) && angular.isDefined(theme.script)){
                },
                reportHTMLTemplateParser: function(scope, script){
                    
                },
                /**
                 * Return le container de navigation
                 * @returns {commons_L61.NavigationContainer}
                 */
                getNavigatorContainer:function(){
                    var container = new NavigationContainer();
                    return container;
                },
                /**
                 * 
                 * @param {type} texte
                 * @param {type} color
                 * @param {type} colorContent
                 * @param {type} topPos
                 * @param {type} leftPos
                 * @returns {undefined}
                 */
               showDialogLoading :function(texte, color, colorContent, topPos,leftPos) {
//                   console.log("commons.showDialogLoading ================== intree ")
                   var instance = NotifyStatutPanel.getInstance();
                   if(instance.active===false){
                       instance.active = true;
                        var idElement = "dialogContent";
                        $('#'+idElement).remove();

                         $('body').append("<div id="+idElement+" style='width:100%;height:100%;position:absolute;z-index:2000;text-align:center;'></div>");
                         $('#'+idElement).append("<div id='dialogWindow'></div>");
                         $('#dialogWindow').append("<span id='dialogWindowText' style='text-align:center;padding:8px;padding-right:16px;padding-left:16px;display:inline-block;color:white;border-radius:3px;font-size:80%;'>"+texte+"</span>");

                         //Changer le proprietes css
                         $('#'+idElement).css("top",topPos);
                         $('#'+idElement).css("left",leftPos);

                         $('#dialogWindowText').css("color",color);
                         $('#dialogWindowText').css("background-color",colorContent);

                         //Afficher le dialog
                         $('#'+idElement).fadeIn();

                         stopTimer = setTimeout(function(){

                                 $('#'+idElement).fadeOut(function(){
                                         showDialogLoadingFull(idElement+"_Full","<i class='fa fa-cog fa-spin fa-3x fa-fw'></i>","white","0.2");
                                         $('#'+idElement).remove();

                                         /*setTimeout(function(){
                                                 hideDialogLoading("open01");
                                         },6000);*/

                                 });

                                 //hideDialogLoading(idElement+"_Full");
                         },6000);
                     }
	
               },//end
               
               hideDialogLoading :function() {
//                   console.log("commons.hideDialogLoading ================== sortie ");
                    //On stoppe le moteur
                    var instance = NotifyStatutPanel.getInstance();
                    if(instance.active===true){
                        instance.active = false;
                        this.stopMoteur(stopTimer);
                        var idElement = "dialogContent";
                        //On cache
                        $('#'+idElement).fadeOut(function(){
                                $('#'+idElement).remove();
                        });

                        //On cache
                        $('#'+idElement+"_Full").fadeOut(function(){
                                $('#'+idElement+"_Full").remove();
                        });
                    }//end if(instance.active===true){
//                    console.log("commons.hideDialogLoading ================== sortie 2");
               },
               /**
                * 
                * @param {type} IdMoteur
                * @returns {undefined}
                */
               stopMoteur : function(IdMoteur){
                   clearInterval(IdMoteur);
               },
               /**
                * Affiche la notification en cas d'erreur
                * @param {type} title
                * @param {type} message
                * @param {type} type
                * @returns {undefined}
                */
               notifyWindow : function(title , message ,type){
                   var instance = NotifyInstance.getInstance();
                   //Desactivation des fenetre ouvert
                   for(var i=0;i<instance.instances.length;i++){
                       var notify = instance.instances[i];
                       notify.close();
                   }//end for(var i=0;i<instance.instances.length;i++){
                   var notify = $.notify(
                     {
                       title: "<strong>"+title+":</strong> ",
                       message: message

                     },{
                        type:type,
                        z_index: 5800,
                         animate: {
                           enter: 'animated fadeInRight',
                           exit: 'animated fadeOutRight'
                         }

                     }
                   );
                   instance.instances.push(notify);
               },
               /**
                * Affiche fenetre alerte
                * @param {type} title
                * @param {type} message
                * @param {type} type
                * @returns {undefined}
                */
               alertWindow : function(title , message ,type){
                   
               },
               //Fileds Validations
                 /**
                    Validate all the fields of the forms to chack constraint validation

                  **/
                 validateFields : function(metaData,currentObject){
                       var champs = new Array();
                       uniqueContraints =  new Array();
                       if(metaData && currentObject){
                           if(metaData.columns){
                              for(var i=0 ; i< metaData.columns.length;i++){
                                  if(!metaData.columns[i].optional || metaData.columns[i].min){
                                      if(!currentObject[metaData.columns[i].fieldName]){
                                          champs.push(metaData.columns[i].fieldLabel);
                                      }//end if(!currentObject[metaData.columns[i].fieldName]){
                                  }//end if(!metaData.columns[i].optional || metaData.columns[i].min){
                                  //Construction des champs pour unicite
                                  if(metaData.columns[i].unique){
                                      var pred = new Object();
                                      pred.fieldLabel = metaData.columns[i].fieldLabel;
                                      pred.fieldName =  metaData.columns[i].fieldName;
                                      pred.fieldValue = currentObject[metaData.columns[i].fieldName];
                                      uniqueContraints.push(pred);
                                  }//end if(metaData.columns[i].unique)
                              }//end for(var i=0 ; i< metaData.columns.length;i++){
                            }//end if(metaData.columns){
                            //Cas des groups
                            if(metaData.groups){
                               for(var i=0;i<metaData.groups.length;i++){
           //                        if($scope.metaData.groups[i].metaArray){
           //                            for(var j=0 ; j<$scope.metaData.groups[i].metaArray.metaData.columns.length ; j++){
           //                                if(!$scope.metaData.groups[i].metaArray.metaData.columns[j].optional || $scope.metaData.groups[i].metaArray.metaData.columns[j].min){
           //                                      champs.push($scope.metaData.groups[i].metaArray.metaData.columns[j].fieldLabel);
           //                                }
           //                            }
           //                        }
                                   //Cas des données normales
                                   if(metaData.groups[i].columns){
                                      for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                                         if(!metaData.groups[i].columns[j].optional || metaData.groups[i].columns[j].min){
                                              var pred = new Object();
                                               pred.fieldLabel = metaData.groups[i].columns[j].fieldLabel;
                                               pred.fieldName =  metaData.groups[i].columns[j].fieldName;
                                               pred.value =  currentObject[metaData.groups[i].columns[j].fieldName];
                                               uniqueContraints.push(pred);
                                               // champs.push($scope.metaData.groups[i].columns[j].fieldLabel);
                                         }//end if(!metaData.groups[i].columns[j].optional || metaData.groups[i].columns[j].
                                         //Construction des champs pour unicite
                                        if(metaData.groups[i].columns[j].unique){
                                            uniqueContraints.push(metaData.groups[i].columns[j].fieldLabel);
                                        }//end if(metaData.groups[i].columns[j].unique){
                                      }//end for(var j=0 ; j< metaData.groups[i].columns.length;j++){
                                   }//end if(metaData.groups[i].columns){
                               }//end for(var i=0;i<metaData.groups.length;i++){
                            }//end if(metaData.groups){
                       }//end if(metaData && currentObject){
                       return champs;

                  },
                  /**
                   * Contriante unicite (appeler a pres l'apel de validateFields 
                   * @returns {commons_L14.commonsAnonym$1.uniqueContraints}
                   */
                  uniqueContraints:function(){
                      return  uniqueContraints;
                  },
                  /**
                   * Construit les composants 
                   * @param {type} columns
                   * @param {type} currentObject
                   * @returns {undefined}
                   */
                 createFromFields: function(columns,currentObject){
                       for(var i=0 ; i< columns.length;i++){
                            if(columns[i].type=='object'){
                              if(!currentObject[""+columns[i].fieldName+""]){
                                currentObject[""+columns[i].fieldName+""] = null;
                              }
                            }else if(columns[i].type=='array'){
                               if(!currentObject[""+columns[i].fieldName+""]) {
                                  currentObject[columns[i].fieldName] = new Array();
                                }
                                //console.log("createFromFields =====  "+$scope.currentObject[columns[i].fieldName]);                       

                                //object["'"+metaData.columns[i].fieldName+"'"].push($scope.createEmptyObject(metaData.columns[i].metaData));
                            }else{
                                if(!currentObject[""+columns[i].fieldName+""]) {
                                  currentObject[""+columns[i].fieldName+""] = "";
                                }
                            }
                        }
                 },
                 /**
                  * 
                  * @param {type} array
                  * @param {type} value
                  * @returns {Boolean}
                  */
                 containsLiteral:function(array , value){
//                     console.log("commonTool.containsLiteral:function(array , value) =============== tab : "+angular.toJson(array)+" ===== state : "+value);
                     if(!angular.isDefined(value)||value==null
                             || !angular.isDefined(array)||array==null){
                         return false ;
                     }//end if(!angular.isDefined(value)){  
                     for(var i=0 ; i<array.length;i++){
                         if(array[i]==value){
                             return true;
                         }
                     }//end for(var i=0 ; i<array.length;i++){
                     return false;
                 },
                /**
                 Create a empty object base of the metaData
                 @metaData : the description of the object
              **/
              createEmptyObject: function(metaData){            
                    var currentObject = new Object();
                     if(metaData){
                        //Cas des colonnes
                        if(metaData.columns){
                              this.createFromFields(metaData.columns,currentObject);                     
                        }
                        //Traitement des champs groups
                        if(metaData.groups){
                             //Traitement des groups
                             for(var i=0 ; i< metaData.groups.length;i++){
                                 //Cas des colonnes
                                 if(metaData.groups[i].columns){
                                    this.createFromFields(metaData.groups[i].columns,currentObject);
                                 }
                                 //cas des metaArray
                                 if(metaData.groups[i].metaArray){
                                     currentObject[metaData.groups[i].metaArray.fieldName] = new Array();
                                 }
                             }
                        }

                     } 
                    return currentObject;
                 },
                 /**
                  * Compute compute field
                  * @param {type} obj
                  * @param {type} currentObject
                  * @param {type} currentUser
                  * @param {type} metadata
                  * @returns {undefined}
                  */
             compteField:function(obj,currentObject,currentUser , metadata){
                 if(obj && metadata){
                     //Traitement des columns
                     for(var i=0 ; i<metadata.columns.length;i++){
                         if(metadata.columns[i].compute==true&&metadata.columns[i].type=='number'){
                             obj[metadata.columns[i].fieldName] = this.expeval(obj,currentObject,currentUser,metadata.columns[i].value);
                         }//end if(metadata[i].compute==true)
                     }//end for(var i=0 ; i<metadata.length;i++)
                     //Traitement des groups
                     for(var i=0;i<metadata.groups.length;i++){
                         if(metadata.groups[i].metaArray){
                             var groupe = metadata.groups[i].metaArray;
                             this.compteField(obj[groupe.fieldName],currentObject,currentUser,groupe.metaData);
                         }//end if(metadata.groups[i].metaArray){
                         if(metadata.groups[i].columns){
                             for(var j=0;j<metadata.groups[i].columns.length;j++){
                                if(metadata.groups[i].columns[j].compute==true&&metadata.groups[i].columns[j].type=='number'){                                
                                    obj[metadata.groups[i].columns[j].fieldName]=this.expeval(obj,currentObject,currentUser,metadata.groups[i].columns[j].value);
                                }//end if(metadata.columns[i].compute==true&&metadata.columns[i].type=='number'){  
                             }//end for(var j=0;j<metadata.groups[i].columns.length;j++)
                         }//end if(metadata.groups[i].columns){
                     }//end for(var i=0;i<metadata.groups.length;i++)
                 }//end if(obj && metadata)
//                 console.log("commonsTools.expeval  ====== "+angular.toJson(obj)+" ======  "+angular.toJson(metadata));                 
             },
            
            /**
             * Calcul la date end fonction de la date et heure
             * @param {type} date
             * @param {type} hours
             * @returns {undefined}
             * @date : date de debut
             * @hours: duree du au format hh:mm:ss
             */
            computeDate:function(date , hours){
                if(angular.isDate(date)){

                    var heu = 0;
                    var min = 0;
                    var sec = 0 ;
                    if(angular.isString(hours)){
                        var parts = hours.split(":");               
                        for(var i=0 ;i<parts.length;i++){
                            if(i==0){
                                heu = Number(parts[i]);
                            }else if(i==1){
                                min = Number(parts[i]);
                            }else if(i==2){
                                sec = Number(parts[i]);
                            }
                        }
                    }//end if(angular.isString(hours))
                    var result = date.getTime()+ (heu*60*60+min*60+sec)*1000;
                    return new Date(result);
                }

            },
         /**
          * 
          * @param {type} date
          * @returns {unresolved}
          */
         convertToLocalDate:function(date){
                 //var date = new Date();  
                var d = ''+date.getDate();
                var m = ''+(date.getMonth()+1);
                var y = ''+date.getFullYear();
                if(m.length<2) m = '0'+m;
                if(d.length<2) d = '0'+d;
                var h = date.getHours()<9 ? "0"+date.getHours():date.getHours();
                var m2 = date.getMinutes()<9 ? "0"+date.getMinutes():date.getMinutes();
                var s = date.getSeconds();
               var dateString = new String();
               dateString = y+"-"+m+"-"+d+"T"+h+":"+m2;
//               console.log("commons.convertToLocalDate ========================= "+dateString)
               return dateString;
         },
         
         convertDateToString:function(date){
               var d = date.getDate();
                var m = date.getMonth();
                var y = date.getFullYear();
                var h = date.getHours()<9 ? "0"+date.getHours():date.getHours();
                var m2 = date.getMinutes()<9 ? "0"+date.getMinutes():date.getMinutes();
                var s = date.getSeconds();
               var dateString = new String();
               dateString = d+"-"+m+"-"+y+" "+h+":"+m2;
         },
         /**
             Verifie que le tableau contient un element
             @array : tableau de données
             @item : element
          **/
          contains: function(array , item){
               if(!angular.isDefined(array)){
                  return false;
               }
               for(var i= 0 ; i<array.length;i++){
                   if(array[i].id===item.id){
                      return true;
                   }
               }
               return false;
          },
          /**
           * 
           * @param {type} array
           * @param {type} item
           * @returns {undefined}
           */
          getIndex: function(array,item){
              if(!angular.isDefined(array)){
                  return false;
               }
//               console.log("commonsTool.getIndex ::::::::::::::: "+angular.toJson(array)+" === item :: "+angular.toJson(item));
               for(var i= 0 ; i<array.length;i++){
                   if(array[i].id == item.id){
                      return i;
                   }
               }
               
          },
          /**
            * 
            * @param {type} array
            * @param {type} item
            * @returns {undefined}
            */
          removeFromArray: function(array , item){
              if(angular.isDefined(array)){                  
                  for(var i=0 ; i<array.length;i++){
                     if(array[i].id == item.id){
                        array.splice(i , 1);
                     }//end  if(array[i].id == item.id){
                  }//end if(angular.isDefined(array)){       
                  //console.log(array+" ====== "+item);                             
              }
          },
          /**
           * 
           * @param {type} metaData
           * @returns {metaData.groups.metaArray|metaData.columns|Object}
           */
          metaDataMapBuilder : function(metaData){
              var map = new Object();
               if(metaData==null||!angular.isDefined(metaData)){
                   return map;
               }
               //Traitement de la metaData
               if(metaData.columns){
                 for(var i=0 ; i<metaData.columns.length;i++){
                      map[metaData.columns[i].fieldName] = metaData.columns[i];
                 }
               }//end if(metaData.columns){
               //Cas des groups
               if(metaData.groups){
                 for(var i=0 ; i<metaData.groups.length;i++){
                     //Cas des columns
                     if(metaData.groups[i].columns){
                         var columns = metaData.groups[i].columns;
                         for(var j=0 ; j<columns.length;j++){
                             map[columns[j].fieldName] = columns[j];
                         }
                     }
                     //Cas des metaArray
                     if(metaData.groups[i].metaArray){
                         map[metaData.groups[i].metaArray.fieldName] = metaData.groups[i].metaArray;
                     }
                 }
               }//end if(metaData.groups){
                return map;
            },
            /**
             * 
             * @param {type} metaData
             * @param {type} fieldName
             * @returns {undefined}
             */
            getMetaData : function(metaData , fieldName){
                //Traitement des columns
                for(var i=0 ; i<metaData.columns.length;i++){
                    var column = metaData.columns[i];
                    if(column.fieldName==fieldName){
                        return column.metaData;
                    }//end if(column.fieldName==fieldName){
                }//end  for(var i=0 ; i<metaData.columns.length;i++){
                for(var i=0;i<metaData.groups.length;i++){
                    var group = metaData.groups[i];
                    if(group.metaArray){
                        var meta = this.getMetaData(group.metaArray.metaData,fieldName);
                        if(meta){
                            return meta;
                        }//end if(meta){
                    }//end if(group.metaArray){
                    for(var j=0 ; j< group.columns.length;j++){
                        var column = group.columns[j];
                        if(column.fieldName==fieldName){
                            return column.metaData;
                        }//end if(column.fieldName==fieldName){
                    }//end for(var j=0 ; j< group.columns.length;j++){
                }//end for(var i=0;i<metaData.groups.length;i++){
            },
            /**
             * 
             * @param {type} template
             * @param {type} scope
             * @param {type} model
             * @param {type} metaData
             * @returns {undefined}
             */
            xmlListParser : function(template){
//                console.log("commons.xmlListParser ================== "+template);
                if(!angular.isDefined(template)
                        ||template==null){
                    return null;
                }//end if(angular.isDefined(template)
                var container = document.createElement('div');
                container.innerHTML = template;
                return container;
            },
           /**
            * 
            * @param {type} template
            * @param {type} model
            * @param {type} metaData
            * @param {type} windowType
            * @param {type} index
            * @param {type} modelpath
            * @returns {unresolved}
            */
            xmlViewParser : function(template,scope,model , metaData , windowType,index,modelpath){
//                 console.log("Nous avons trouve un champs field");
                if(!angular.isDefined(template)
                        ||template==null){
                    return null;
                }//end if(!angular.isDefined(template)
                 if(metaData==null || !angular.isDefined(metaData)){
                      return null;
                 }//end if(metaData==null || !angular.isDefined(metaData)){
//                 var metaData = meta;
                 var container = angular.element(template);
                 var items = container.find("field");
                 for(var i=0; i<items.length;i++){ 
                     var item = items.eq(i);
                     var field = this.getMetaField(metaData,item.attr("name"));
                     if(angular.isDefined(field)){
                         if(angular.isDefined(item.attr("target"))){
                             field.target = item.attr("target");
                         }//end if(angular.isDefined(item.attr("target")))
                         item.replaceWith(scope.getIhmComponent(model , field , metaData.entityName , metaData.metaDataName,index,modelpath));
                     }//end if(angular.isDefined(field)){
//                     console.log("commons.xmlViewParser ===== "+item.attr("name")+" ==== target : "+item.attr("target"));
//                      items.eq(i).replaceWith(titleheader);                  
                 }//end for(var i=0; i<items.length;i++){   
                 return container;
            },  /**
            * 
            * @returns {undefined}
            */
           preParser:function(script ,module){
               var container = angular.element(script);
               var items = container.find("ng-include");
               for(var i=0 ;i<items.length;i++){
                   var item = items.eq(i);
                   var attr = item.attr("src");
//                   console.log("commsTools.preParser ================== "+angular.toJson(attr));
                   if(angular.isDefined(attr)&& attr!=""){
                       this.itemPreParser(item,attr,module);
                   }//end if(angular.isDefined(attr)&& attr!=""){
               }//end for(var i=0 ;i<items.length;i++){  
//               items = container.find('?xml');
//               console.log("commsTools.preParser ================== "+items.length);
               return container;               
            },
            /**
             * 
             */
           itemPreParser:function(item , name,module){
//                 console.log("commsTools.itemPreParser ================== "+angular.toJson(module.templates));
                 for(var i=0 ;i<module.templates.length;i++){
                     var template = module.templates[i];
                     item.replaceWith(angular.element(template.script));
                 }//end for(var i=0 ;i<module.templates.length;i++){
           } ,

            /**
             * 
             * @param {type} error
             * @returns {undefined}
             */
            showMessageDialog:function(error){
//                    var errorobj = angular.fromJson(angular.toJson(error));
//                    console.log(angular.toJson(error));
                    if(error.status==412){
                        this.notifyWindow("Une erreur est servenu pendant le traitement" ,"<br/>"+error.data,"danger");
                    }else{
                        var viewElem =  document.createElement('div'); //;
                        viewElem.setAttribute('id' , 'gmodalbody');
                        var content =  document.createElement('div');
                        content.setAttribute('style','height:300px;overflow:auto;');
                        //console.log(angular.toJson(error.data));
                        if(error.data){
                            content.innerHTML = angular.toJson(error.data);
                        }else{
                            content.innerHTML = angular.toJson(error);
                        }
                        viewElem.appendChild(content);
                        //Construction du footer
                        var footerDiv = document.createElement('div');
                        footerDiv.setAttribute('class' , 'modal-footer');
                        footerDiv.setAttribute('id' , 'gmodalfooter');
                        //Button annuler
                        var buttonElem = document.createElement('button');
                        footerDiv.appendChild(buttonElem);
                        buttonElem.setAttribute('class' , 'btn btn-default');
                        buttonElem.setAttribute('data-dismiss' , "modal");
                        buttonElem.setAttribute('type' , 'button');
                        buttonElem.appendChild(document.createTextNode('Annuler'));
                        //Entete modal
                        var titleheader = document.createElement('h4');
                        titleheader.setAttribute('class','modal-title');
                        titleheader.setAttribute('id','gmodaltitle');
                        titleheader.appendChild(document.createTextNode("Erreur Serveur : "+error.status));                    
                        var items = $(document).find("div");
                        for(var i=0; i<items.length;i++){               
                            if(items.eq(i).attr("id")=="gmodalbody"){
                                  items.eq(i).replaceWith(viewElem);                              

                            } else if(items.eq(i).attr("id")=="gmodalfooter"){
                                items.eq(i).replaceWith(footerDiv);
                            } 
                        } 


                        items = $(document).find("h4");
                        for(var i=0; i<items.length;i++){               
                            if(items.eq(i).attr("id")=="gmodaltitle"){
                                  items.eq(i).replaceWith(titleheader);                                     
                            } 
                        } 
                        //Appele de la fenetre modale
                        $("#globalModal").modal("toggle");
                        $("#globalModal").modal("show");
                    }//end if(error.status==412)
                   
            },
    
            /**
             * 
             * @param {type} idFileElement
             * @param {type} idDivImageContent
             * @param {type} idApercuImageContent
             * @returns {undefined}
             */
            gererChangementImage:function(idFileElement ,idDivImageContent ,idApercuImageContent){
                //Initiallisation du tableau des images
                var files = new Array();
                document.querySelector('#'+idFileElement).onchange = function() {
//		console.log("Images Arrays ::::::::::::::::::::: ");	
		var fileInput = document.querySelector('#'+idFileElement);
		var allowedTypes = ['png', 'jpg', 'jpeg', 'gif'];
				
		files = this.files;      
		var filesLen = files.length;      
		var imgType;       

		for (var i = 0 ; i < filesLen ; i++) {              

			imgType = files[i].name.split('.');       

			imgType = imgType[imgType.length - 1].toLowerCase(); // On utilise toLowerCase() pour éviter les extensions en majuscules             

			if(allowedTypes.indexOf(imgType) != -1) { // Le fichier est bien une image supportée, il ne reste plus qu'à l'afficher                  
				
				//On affiche l'appercu				
				this.createThumbnail(files[i],idDivImageContent,idApercuImageContent);
				
			}else{
				
				//Code
			}           
		}
              };
              
              return files;
            },
            objectToNumber:function(data){
                if(data){
                    var value = new String();
                    for(var key in data){
                        value +=data[key];
                    }
                   return new Number('"'+value.trim()+'"');
                }
            },
            /**
             * 
             * @param {type} meta
             * @param {type} fieldName
             * @returns {undefined}
             */
            getMetaField:function(meta,fieldName){
//                console.log("getMetaField === "+fieldName+" == "+angular.toJson(metav));
                if(meta && fieldName){
                    for(var i=0 ; i<meta.columns.length;i++){
                        if(meta.columns[i].fieldName==fieldName){
                            return meta.columns[i];
                        }
                    }//end for(var i=0 ; i<meta.columns.length;i++)
                    for(var i=0;i<meta.groups.length;i++){
                        for(var j=0;j<meta.groups[i].columns.length;j++){
                            if(meta.groups[i].columns[j].fieldName==fieldName){
                                return meta.groups[i].columns[j];
                            }
                        }
                        if(meta.groups[i].metaArray&&meta.groups[i].metaArray.fieldName==fieldName){
                           return meta.groups[i].metaArray;
                        }//end if(meta.groups[i].columns[j].fieldName==fieldName)
                    }//end for(var i=0;i<meta.groups.length;i++)
                }
            },
            /**
             * 
             * @param {type} file
             * @param {type} idDivImageContent
             * @param {type} idApercuImageContent
             * @returns {undefined}
             */
            createThumbnail:function(file,idDivImageContent,idApercuImageContent){
                var reader = new FileReader();       
		reader.onload = function() {                
					
			var imgElement = document.querySelector('#'+idApercuImageContent);      
			 imgElement.src = this.result;          

		};        
		reader.readAsDataURL(file);
            },
            /**
             * Somme la valeur d'une columns
             * @param {type} fieldName:fields name type :number
             * @param {type} datas:
             * @returns {undefined}
             */
            sumTableField:function(fieldName,datas){
                if(datas && datas.length>0){                    
                    var data = datas[0];                    
                    if(data[fieldName]!=null && angular.isNumber(data[fieldName]) ){                        
                        var total = 0;
                        for(var i=0;i<datas.length;i++){           
                            data = datas[i];
                            total += data[fieldName];
                        }//end for(var i=0;i<datas.length;i++)
//                        console.log("$scope.tableListComponent === "+fieldName+" === "+total);
                        return $filter('number')(total,0);
                    }//end if(angular.isNumber(data[fieldName]))
                    
                }//else{return 0;}end if(datas && datas.length>0)
                
            },
            /**
             * Sum the table datas with rhe express
             * @param {type} expr:['tva','*','quantite']
             * @param {type} datas
             * @returns {undefined}
             */
            sumListExpr:function(expr, datas,object,user){
                var exprA = expr.split(','); 
                if(datas && datas.length>0){
                    /* if(exprA.length==1){
                        var data = datas[0];
                        var value=this.expeval(datas[0],null,null,exprA);
                        if(angular.isNumber(value)){
                            var total = 0;
                            for(var i=0;i<datas.length;i++){
                                var data = datas[i];
                                value = this.expeval(data,null,null,exprA);
                                 total += value;
                            }//end for(var i=0;i<datas.length;i++)   
                            return total;
                        }else{
                            return expr;
                        }
                    }else{*/
                        if(exprA.length==1){
                            exprA = expr.split(".");
                            if(exprA.length==1&&!angular.isNumber(expr)){
                                return expr;
                            }//end if(exprA.length==1&&!angular.isNumber(expr)){
                        }//end if(exprA.length==1&&!angular.isNumber(exprA))
//                        console.log(" expeval:function(obj,currentObject,currentUser  , expr)=="+expr+" ===== "+exprA+" ===== ");                
                        var total = 0;
                        for(var i=0;i<datas.length;i++){
                            var value=this.expeval(datas[i],object,user,expr);
                            if(value && angular.isNumber(value)){
                                total += value;
                            }
                        }//end for(var i=0;i<datas.length;i++)
                   // }//end if(exprA.length==1)
                    
                    
                    return total;
                }else{return 0;}//end if(datas && datas.length>0)
            }, 
            funcEval:function(obj,currentObject,currentUser  , expr){
//                console.log("funcEval:function(obj,currentObject,currentUser  , expr) ========== "+expr);
                var entity = angular.fromJson(expr);
                var data = obj;
                if(entity.source=='this'){
                    data = obj;
                }else if(entity.source=='object'){
                    data = currentObject;
                }else if(entity.source=='user'){
                    data = currentUser;
                }
                data = data[entity.data];
                if(entity.op=="sum"){
                    return this.sum(data,entity.field);
                }
                return "0";
            },
            /**
             * 
             * @param {type} datas
             * @param {type} field
             * @returns {Number}
             */
            sum:function(datas , field){
                var result = 0 ;
                for(var i=0;i<datas.length;i++){
                    var data =datas[i];
                    result +=data[field];
                }
                return result;
            },
            /**
              * Expression evaluation
              * @param {type} obj
              * @param {type} currentObject
              * @param {type} currentUser
              * @param {type} expr
              * @returns {undefined}
              */
             expeval:function(obj,currentObject,currentUser  , expr){ 
//                 console.log("commonsTools.expeval:function(obj,currentObject,currentUser  , expr) =============== "+obj+" ::::  === "+currentObject+" === "+expr);
                
                 expr = ""+expr;
                 var parts = expr.split(';');
                 var exp = new String();
                 for(var i=0;i<parts.length;i++){
                     if(parts[i]=='('||parts[i]==')'||parts[i]=='*'
                             ||parts[i]=='+'||parts[i]=='-'||parts[i]=='/'||parts[i]=="%"){
                         exp+=parts[i];
                     }else{//value
                         var ops = parts[i].split('.');
                         if(ops.length==1){
                             if(!isNaN((parts[i]))){
                                 exp+=parts[i];
                             }else{
                                 exp+=this.funcEval(obj,currentObject,currentUser,parts[i]);
                             }
                         }else if(ops[0]=='this'){
                             var value = obj;
                             for(var j=1;j<ops.length;j++){
                                 if(value[ops[j]]){
                                    value =value[ops[j]];
                                 }else{
                                    value= "0";
                                 }//end if(obj[ops[1]]){    
                             }//end for(var i=1;i<ops.length;i++)
                             exp+=value;        
                         }else if(ops[0]=='object'){
                             var value = currentObject;
                             for(var j=1;j<ops.length;j++){
                                 if(value[ops[j]]){
                                    value =value[ops[j]];
                                 }else{
                                    value= "0";
                                 }//end if(obj[ops[1]]){    
                             }//end for(var i=1;i<ops.length;i++)
                             exp+=value;        
                         }else if(ops[0]=='user'){
                             var value = currentUser;
                             for(var j=1;j<ops.length;j++){
                                 if(value[ops[j]]){
                                    value =value[ops[j]];
                                 }else{
                                    value= "0";
                                 }//end if(obj[ops[1]]){    
                             }//end for(var i=1;i<ops.length;i++)
                             exp+=value;         
                         }                         
                     }//end if(parts[i]=='('||parts[i]==')'||parts[i]=='*'
                 }//end for(var i=0;i<parts.length;i++)
//                 console.log("commonsTools.expeval  ====== "+expr+" ************ "+exp+" == "+ops[1]);
               return eval(exp);               
             },
             /**
              * 
              * @param {type} expr
              * @param {type} data
              * @param {type} currentObject
              * @param {type} currentUser
              * @returns {commons_L61.commonsAnonym$3.getValue.data|String}
              */
            getValue:function(expr,data,currentObject,currentUser){
               if(expr){
                   if(angular.isNumber(expr)){
                       return expr;
                   }//end if(angular.isNumber(expr))
//                   console.log("getValue:function(expr,data) === "+expr);
                   var part = expr.split(".");
                   if(part.length==1){
                       if(angular.isNumber(data[expr])){
                        return data[expr];
                       }
                   }else if(part.length>1){
                       var obj = data[part[0]];
                       for(var i=1;i<part.length;i++){
                           obj = obj[part[i]];
                       }//end for(var i=1;i<part.length;i++){
                       if(angular.isNumber(obj)){
                        return obj;
                       }//end if(angular.isNumber(obj))
                   }//end if(part.length==1)                   
               }//end if(exp) 
               return "0" ;
            },
            sumExpr:function(expr,data){
                if(data && expr.length>0){
                    var expEval = new String();
                    for(var i=0;i<expr.length;i++){
                        if(expr[i]=='*'||expr[i]=='-'||expr[i]=='+'||expr[i]=='/'||expr[i]=='%'||expr[i]=='('||expr[i]==')'){
                            expEval += expr[i];
                        }else{
                            expEval +=this.getValue(expr[i],data);
                        }
                    }//end for(var i+0;i<expr.length;i++){
//                    console.log("sumListExpr:function(expr, datas)) === "+expEval+" === "+result);
                    var result = eval(expEval.toString());
                    return result;
                }//end if(data && expr.length>0)
            },
            /**
             * Construit le pied de table
             * @param {type} script
             * @param {type} datas
             * @returns {undefined}
             */
            tableFooterBuilder:function(script , datas,id,object,user){
                var container = document.createElement('tfoot');
                container.setAttribute("id",id);
                container.innerHTML = script;
               //parcours des tr lignes
//               console.log("tableFooterBuilder ===  === "+container.childNodes.length+" ********* script : "+script);
                for(var i=0;i<container.childNodes.length;i++){
                    //traitement des colonnes
                    var rowNode = container.childNodes[i];
                     if(rowNode.tagName=='TR'){
                        for(var j=0;j<rowNode.childNodes.length;j++){
                            var colNode = rowNode.childNodes[j];
                            if(colNode.tagName=='TD'){
                                var value = colNode.textContent;                        
                                if(value!=null&&value!=""){
//                                    console.log("tableFooterBuilder ===  ===  ********* "+colNode.tagName+" === "+colNode.textContent+" ===== Value : "+value);
                                    var data = this.sumListExpr(value,datas,object,user);
                                    if(!isNaN(data)){
                                        colNode.textContent = $filter('number')(data,0);
                                    }else{ 
                                       colNode.textContent = data; 
                                    }//end if(!isNaN(data))
                                }//end if(value!=null&&value!="")
                            }//end if(colNode.tagName=='TD')
                        }//end for(var j=0;j<rowNode.childNodes.length;j++){
                      }//end if(rowNode.tagName=='TR'){
               }//end for(var i=0;i<container.childNodes.length;i++)
               var thelem = document.createElement('th');
               container.appendChild(thelem);
               return container;
            },
            /**
             * 
             * @param {type} metaData
             * @param {type} data
             * @param {type} model
             * @returns {undefined}
             */
            sumFooterTableBuilder:function(metaData , data,model,id){
//                console.log("commonsTool. sumFooterTableBuilder === "+model+"========"+angular.toJson(data));
                    var fieldnames = new Array();
                    for(var i = 0 ; i < metaData.columns.length;i++){
                        if(angular.isDefined(metaData.columns[i].search) && metaData.columns[i].search){
                          fieldnames.push(metaData.columns[i].fieldName);                          
                        }
                    }
                    //Traitement  des champs des groupes
                    if(metaData.groups&&metaData.groups.length>0){
                        for(var i=0;i<metaData.groups.length;i++){
                            //Cas des columns
                            if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0){
                                 for(var j = 0 ; j < metaData.groups[i].columns.length;j++){
                                   if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search){
                                     fieldnames.push(metaData.groups[i].columns[j].fieldName);                                     
                                   }//end  if(angular.isDefined(metaData.groups[i].columns[j].search) && metaData.groups[i].columns[j].search)
                               }//end for(var j = 0 ; j < metaData.groups[i].columns.length;j++)
                            }//end if(metaData.groups[i].columns&&metaData.groups[i].columns.length>0)                     
                        }//end for(var i=0;i<metaData.groups.length;i++)
                    }
                    //Construction du pied
                    if(fieldnames.length>0){
                         var sources = model.split(".");                        
                        var footerElem = document.createElement('tfoot');
                        footerElem.setAttribute("id",id);
                        if(fieldnames.length>0){
                        for(var i=0 ;  i<fieldnames.length ; i++){
                                var thelem = document.createElement('th');
                                footerElem.appendChild(thelem);                            
//                                console.log("commonsTool. sumFooterTableBuilder === "+model+"===="+sources[0]+"===="+angular.toJson(data));
                                var total = null;
                                if(data){
                                    total = this.sumTableField(fieldnames[i],data[sources[sources.length-1]]);
                                }//end if(data){
                                if(angular.isDefined(total)&&angular.isNumber(total)){
                                    thelem.appendChild(document.createTextNode(total));
                                    thelem.setAttribute('class','text-right');
                                }//end if(angular.isNumber(total))
                        }//end for(var i=0 ;  i<fieldnames.length ; i++)
//                        tableElem.appendChild(footerElem);
                    }//end if(fieldnames.length>0)
               }//end if(fieldnames.length>0)
               var thelem = document.createElement('th');
               footerElem.appendChild(thelem);  
               return footerElem;
            },
            
            /**
             * Build for from dashboard entry data
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryFormBuilder:function(parentID,data){
                var formElem = document.createElement('form');
//                container.appendChild(formElem);
                formElem.setAttribute('class','form-inline');
                for(var i=0 ; i<data.fields.length;i++){
                    var field = data.fields[i];
                    var divElem = document.createElement("div");
                    divElem.setAttribute("class","form-group  col-sm-12  col-md-12");
                    divElem.setAttribute("style","margin-top: 10px;");
                    formElem.appendChild(divElem);
                    var labelElem_1 = document.createElement("label");
                    divElem.appendChild(labelElem_1);
                    labelElem_1.setAttribute("for",field.fieldName);
                    labelElem_1.setAttribute("class","col-sm-6  col-md-6");
                    labelElem_1.appendChild(document.createTextNode(field.fieldLabel));
                    var labelElem_2 = document.createElement("label");
                    divElem.appendChild(labelElem_2);
                    labelElem_2.setAttribute("for",field.fieldName);
                    labelElem_2.setAttribute("class","col-sm-4  col-md-4");
                    labelElem_2.appendChild(document.createTextNode(field.fieldValue));
                    var butElem = document.createElement("button");
                    divElem.appendChild(butElem);
                    butElem.setAttribute("class","col-md-2 col-sm-2 btn btn-default btn-sm");
                    butElem.appendChild(document.createTextNode("Détail"));
                    butElem.setAttribute("ng-show",field.activalink);
                    butElem.setAttribute("ng-click" , "dashboardEntryBtn('"+field.model+"','"+field.entity+"' , '"+field.method+"')");
                }//end for(var i=0 ; i<data.length;i++)
                $("#"+parentID).html("");
                $("#"+parentID).append(formElem);
//                return formElem;
            },
            /**
             * 
             * @param {type} parentID
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryCustomBuilder:function(parentID,data,scope){
                var url = 'http://'+$location.host()+':'+$location.port()+'/'+angular.lowercase(data.model)+'/'+angular.lowercase(data.entity)+'/'+angular.lowercase(data.method);
                $http.get(url).then(
                    /**
                     * 
                     * @param {type} datas
                     * @returns {undefined}
                     */
                        function(response){
                             //Notification du changement du module
                                scope.temporalData = response.data;
                                var container = document.createElement("div");
                //              var obj = angular.fromJson(data);
//                                console.log("commons.dashboardEntryCustomBuilder ====== "+angular.toJson(scope.temporalData)+" ==== template : ");                
                                container.innerHTML = data.tempate;
                                var compileFn = $compile(container);
                                 compileFn(scope);    
                                $("#"+parentID).html("");
                                $("#"+parentID).append(container);
                        },
                        function(error){
//                             this.hideDialogLoading();
//                             this.showMessageDialog(error);
                        }
                     );
                
//                return formElem;
            },
            /**
             * 
             * @param {type} parentID
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryBarBuilder:function(parentID , data){
                var bardata = new Object();
                bardata.type = "column";
                bardata.dataPoints = new Array();                
                for(var i=0 ; i<data.fields.length;i++){
                    bardata.dataPoints.push({label:data.fields[i].fieldLabel,y:data.fields[i].fieldValue});
                }//end for(var i=0 ; i<data.fields.length;i++)
                var bararray = new Array();
                bararray.push(bardata);
                // Construct options first and then pass it as a parameter
                var options = {
                        animationEnabled: true,
                        title: {
                                text: ""
                        },
                        data:bararray
                };
                $("#"+parentID).html("");
                $("#"+parentID).CanvasJSChart(options);
//                return divElem;
            },
            /**
             * 
             * @param {type} parentID
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryPieBuilder:function(parentID , data){
                var bardata = new Object();
                bardata.type = "pie";
                bardata.startAngle= 45;
                bardata.indexLabel = "{label} ({y})";
                bardata.yValueFormatString = "#,##0.#"%"";
                bardata.dataPoints = new Array();                
                for(var i=0 ; i<data.fields.length;i++){
                    bardata.dataPoints.push({label:data.fields[i].fieldLabel,y:data.fields[i].fieldValue});
                }//end for(var i=0 ; i<data.fields.length;i++)
                var bararray = new Array();
                bararray.push(bardata);
                // Construct options first and then pass it as a parameter
                var options = {
                        animationEnabled: true,
                        title: {
                                text: ""
                        },
                        data:bararray
                };
                $("#"+parentID).html("");
                $("#"+parentID).CanvasJSChart(options);
            },
            /**
             * 
             * @param {type} parentID
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryLineBuilder:function(parentID , data){
                var bardata = new Object();
                bardata.type = "spline";
                bardata.dataPoints = new Array();                
                for(var i=0 ; i<data.fields.length;i++){
                    bardata.dataPoints.push({label:data.fields[i].fieldLabel,y:data.fields[i].fieldValue});
                }//end for(var i=0 ; i<data.fields.length;i++)
                var bararray = new Array();
                bararray.push(bardata);
                // Construct options first and then pass it as a parameter
                var options = {
                        animationEnabled: true,
                        title: {
                                text: ""
                        },
                        data:bararray
                };
                $("#"+parentID).html("");
                $("#"+parentID).CanvasJSChart(options);
            },
            /**
             * 
             * @param {type} parentID
             * @param {type} data
             * @returns {unresolved}
             */
            dashboardEntryUnkownBuilder:function(parentID , data){
                var divElem = document.createElement("div");
                divElem.appendChild(document.createTextNode("Unkown options ..."));
                return divElem;
            },
            /**
             * 
             * @param {type} data
             * @returns {undefined}
             */
            dashboardEntryBuilder:function(parentID,data,scope){
                if(data.type=='data'){
                    return this.dashboardEntryFormBuilder(parentID,data);
                }else if(data.type=='bar'){
                    return this.dashboardEntryBarBuilder(parentID,data);
                }else if(data.type=='pie'){
                    return this.dashboardEntryPieBuilder(parentID,data);
                }else if(data.type=='line'){
                    return this.dashboardEntryLineBuilder(parentID,data);
                }else if(data.type=='template'){
                    return this.dashboardEntryCustomBuilder(parentID,data,scope);
                }else{
                    return this.dashboardEntryUnkownBuilder(parentID,data);
                }
            },
            /**
             * 
             * @param {type} data:dash bord datat
             * @returns {undefined}
             */
        dashboardBuilder:function(data){
                if(data){                                                            
                    var divElem = document.createElement('div');
                    divElem.setAttribute("class","panel kanban-col");
                    divElem.setAttribute("style","margin-bottom:7px;padding-left: 0px;margin-right: 10px;width: 47%;");
                    var headElem = document.createElement("div");
                    divElem.appendChild(headElem);
                    headElem.setAttribute("class","panel-heading col-sm-12 col-md-12 dashboard-header");
                    headElem.appendChild(document.createTextNode("{{'"+data.label+"' | translate}}"));
                    var actionElem = document.createElement("div");
                    headElem.appendChild(actionElem);
                    actionElem.setAttribute("class","btn-group  dropdown pull-right");
                    actionElem.setAttribute("role","group");
                    actionElem.setAttribute("aria-label","group 2");
                    var buttonElem = document.createElement("button");
                    actionElem.appendChild(buttonElem);
                    buttonElem.setAttribute("class","btn  dropdown dropdown-toggle btn-sm  dashboard-header");
                    buttonElem.setAttribute("type","button");
                    buttonElem.setAttribute("data-toggle","dropdown");
                    buttonElem.setAttribute("aria-haspopup","false");
                    buttonElem.setAttribute("aria-aria-expanded","true");
                    buttonElem.appendChild(document.createElement("Plus"));
                    var spanElem = document.createElement("span");
                    buttonElem.appendChild(spanElem);
                    spanElem.setAttribute("class","caret");
                    var ulElem = document.createElement("ul");
                    actionElem.appendChild(ulElem);
                    ulElem.setAttribute("class","dropdown-menu");
                    ulElem.setAttribute("role","menu");
                    ulElem.setAttribute("aria-labelledby","dashboardbtn");
                    //Liste des menus
                    for(var i=0 ;i<data.entries.length;i++){
                        var entry = data.entries[i];
                        var liElem = document.createElement("li");
                        ulElem.appendChild(liElem);
                        liElem.setAttribute("role","presentation");
                        var aElem = document.createElement("a");
                        liElem.appendChild(aElem);
                        aElem.setAttribute("role","menuitem");
                        aElem.setAttribute("tabindex","1");
                        aElem.setAttribute("href","#");
                        aElem.setAttribute("ng-click","showEntrypanel('"+data.code+"','"+entry.code+"')");
                        aElem.appendChild(document.createTextNode("{{'"+entry.label+"' | translate}}"));
                    }//end entries
                    //Body of the dashboard
                    var bodyElem = document.createElement("div");
                    divElem.appendChild(bodyElem);
                    bodyElem.setAttribute("class","panel-body col-sm-12 col-md-12");
                    bodyElem.setAttribute("style","padding: 0px;");
                    var divElem2 = document.createElement("div");
                    bodyElem.appendChild(divElem2);
                    divElem2.setAttribute("class","kanban-centered");
                    divElem2.setAttribute("style","padding:0px;");
                    var artElem = document.createElement("article");
                    divElem2.appendChild(artElem);
                    artElem.setAttribute("class","dashboard-body");
                    artElem.setAttribute("draggable","true");
                    artElem.setAttribute("style","height: 150px;");
                    var container = document.createElement("div");                    
                    container.setAttribute("id",data.code);
                    container.setAttribute("style","height: 100%; width: 100%;");                    
//                    var dashentry =this.dashboardEntryBuilder(data.code,data.entries[0]);
//                    if(dashentry){
//                        container.appendChild(dashentry);
//                    }//end if(dashentry){
                    artElem.appendChild(container);
//                    console.log(" $scope.initAction ===== "+divElem.innerHTML);
//                    this.dashboardEntryBuilder(data.code,data.entries[0]);
                    return divElem;
                }//end if(data)
            },
            /**
             * 
             * @param {type} scope
             * @param {type} container
             * @returns {undefined}
             */
            menusActions :function(scope,container,sens,terme){
                    var span = document.createElement("span");
                    span.setAttribute("class","tc-dropdown");
                    span.setAttribute("style","float:right;");
                    container.appendChild(span); 
                    var spanElem = document.createElement("span");
                    if(sens===0){
                      spanElem.setAttribute("class" ,"glyphicon glyphicon-option-horizontal pull-right tc-dropbtn"); 
                    }else{
                        spanElem.setAttribute("class" ,"glyphicon glyphicon-option-vertical pull-right tc-dropbtn"); 
                    }//end if(sens===0){
                    span.appendChild(spanElem); 
                    var ulelem = document.createElement("ul");
                    ulelem.setAttribute('class','dropdown-menu');
                    ulelem.setAttribute('role','menu');
                    ulelem.setAttribute('aria-labelledby','aria-labelledby');
                    span.appendChild(ulelem);
                    ulelem.setAttribute("class","tc-dropdown-content");
                    if(scope.currentModule.name==="application"){
                        var liElem = document.createElement('li');
                        var aElem = document.createElement('a');
                        liElem.setAttribute('role','presentation');
                        liElem.setAttribute('ng-hide','desableupdate');
                        ulelem.appendChild(liElem);
                        aElem = document.createElement('a');
                        aElem.setAttribute('role','menuitem');
                        aElem.setAttribute('tabindex','-1');
                        aElem.setAttribute('href','#');
                        aElem.setAttribute('ng-click',"installAction("+terme+")");
                        aElem.appendChild(document.createTextNode('{{exportbtnlabel | translate}}')) ;
                        liElem.appendChild(aElem);
                        //Bloc 3
                        liElem = document.createElement('li');
                        liElem.setAttribute('role','presentation');
                        liElem.setAttribute('ng-hide','desableupdate');
                        ulelem.appendChild(liElem);
                        aElem = document.createElement('a');
                        aElem.setAttribute('role','menuitem');
                        aElem.setAttribute('tabindex','-1');
                        aElem.setAttribute('href','#');
                        aElem.setAttribute('ng-click',"refreshApplication("+terme+")");
                        aElem.appendChild(document.createTextNode('{{updatebtnlabel | translate}}')) ;
                        liElem.appendChild(aElem);
                    }else{
                        var liElem = document.createElement('li');
                        var aElem = document.createElement('a');                        
                        liElem.setAttribute('role','presentation');
                        liElem.setAttribute('ng-hide',"!canUpdate("+terme+")");
                        ulelem.appendChild(liElem);
                        aElem = document.createElement('a');
                        aElem.setAttribute('role','menuitem');
                        aElem.setAttribute('tabindex','-1');
                        aElem.setAttribute('href','#');
                        aElem.setAttribute('ng-click',"updateAction("+terme+")");
                        aElem.appendChild(document.createTextNode('{{updatebtnlabel | translate}}')) ;
                        liElem.appendChild(aElem);
                        //Bloc dupliquer
                        liElem = document.createElement('li');
                        liElem.setAttribute('role','presentation');
                        liElem.setAttribute('ng-hide',"!canCreate("+terme+")");
                        ulelem.appendChild(liElem);
                        aElem = document.createElement('a');
                        aElem.setAttribute('role','menuitem');
                        aElem.setAttribute('tabindex','-1');
                        aElem.setAttribute('href','#');
                        aElem.setAttribute('ng-click',"dupliquerAction("+terme+")");
                        aElem.appendChild(document.createTextNode("{{'Dupliquer' | translate}}")) ;
                        liElem.appendChild(aElem);
                        //Bloc 3
                        liElem = document.createElement('li');
                        liElem.setAttribute('role','presentation');
                        liElem.setAttribute('ng-hide',"!canDelete("+terme+")");
                        ulelem.appendChild(liElem);
                        aElem = document.createElement('a');
                        aElem.setAttribute('role','menuitem');
                        aElem.setAttribute('tabindex','-1');
                        aElem.setAttribute('href','#');
                        aElem.setAttribute('ng-click',"deleteAction("+terme+")");
                        aElem.appendChild(document.createTextNode('{{deletebtnlabel | translate}}')) ;
                        liElem.appendChild(aElem);
                    }//end if(scope.currentModule.name=="application"){     
            },        
            kabanBuilder:function(scope){
                    var divElem = document.createElement('div');
                    divElem.setAttribute("class","panel kanban-col");
                    divElem.setAttribute("style","margin-bottom:7px;padding-left: 0px;margin-right: 10px;width: 24%;");
                    var headElem = document.createElement("div");
                    divElem.appendChild(headElem);
                    headElem.setAttribute("class","panel-heading col-sm-4 col-md-3 col-lg-2 dashboard-header");
                    headElem.setAttribute("style","width: 100%;height:5px;");
//                    var span = document.createElement("span");
                    this.menusActions(scope,headElem,0,'item');              
                    //Body of the dashboard
                    var bodyElem = document.createElement("div");
                    divElem.appendChild(bodyElem);
                    bodyElem.setAttribute("class","panel-body col-sm-4 col-md-3  col-lg-2");
                    bodyElem.setAttribute("style","padding: 0px;width: 100%;");
                    var divElem2 = document.createElement("div");
                    bodyElem.appendChild(divElem2);
                    divElem2.setAttribute("class","kanban-centered");
                    divElem2.setAttribute("style","padding:0px;");
                    var artElem = document.createElement("article");
                    divElem2.appendChild(artElem);
                    artElem.setAttribute("class","kaban-body");
                    artElem.setAttribute("draggable","true");
                    artElem.setAttribute("style","height: 100px;");
                    artElem.setAttribute("ng-click","viewAction(item)");
                    var container = document.createElement("div");                    
                    container.setAttribute("id","kaban_{{item.id}}");
                    container.setAttribute("style","height: 100%; width: 100%;");                    
                    if(angular.isDefined(scope.currentAction.kaban) 
                            &&angular.isDefined(scope.currentAction.kaban.script)){
                        container.innerHTML = scope.currentAction.kaban.script;                        
                    }//end dashentry = if(angular.isDefined(scope.currentAction.kaban)
                    artElem.appendChild(container);
                    var compileFn = $compile(divElem);
                    compileFn(scope);                    
//                    console.log(" $scope.initAction ===== "+divElem.innerHTML);
//                    this.dashboardEntryBuilder(data.code,data.entries[0]);
                    return divElem;               
            },
            /**
             * 
             * @param {type} scope
             * @param {type} meta
             * @returns {undefined}
             */
            dispatchDatas:function(scope){
                var meta = scope.metaData;
                var blocks=new Object();                
                for(var i=0;i<meta.states.length;i++){
                    blocks[meta.states[i].code] = new Array();
                }//end for(var i=0;i<meta.states.length;i++){
//                console.log("commons.dispatchDatas:function(scope) =============== "+angular.toJson(blocks));
                //Aiguillage des données
                for(var i=0 ; i<scope.datas.length;i++){
                    var data = scope.datas[i];
//                    console.log("commons.dispatchDatas:function(scope) =============== "+angular.toJson(blocks)+" ====== state : "+data.state);
                   if(blocks[data.state]){
                       blocks[data.state].push(data);
                   }//end if(blocks[data.state]){
                }//end for(var i=0 ; i<scope.datas.length;i++){
                scope.dataCache["blocks"] = blocks;
            }
            ,
            urlRootBuilder : function(model,entity){
                var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/"+model+"/"+entity;
                return url;
            }
            ,
           /**
            * 
            * @param {type} scope
            * @param {type} metaData
            * @returns {unresolved}
            */
            kabanContainerBuilder:function(scope,metaData){
                if(metaData.states.length<=0){
                        var worflowcontent = document.createElement("div");
//                        worflowcontent.setAttribute("id","datawidget");
                        worflowcontent.setAttribute("class","workflow-content");
                        var worklowitemcontent = document.createElement("div");
                        worflowcontent.appendChild(worklowitemcontent);
                        worklowitemcontent.setAttribute("class","workflow-item-content");
                        worklowitemcontent.setAttribute("style","width:100%;display: block;");
                        var itemcontent = document.createElement("div");
                        itemcontent.setAttribute("class","item-content");
                        worklowitemcontent.appendChild(itemcontent);
                        var kabanitemcontent = document.createElement("div");
                        itemcontent.appendChild(kabanitemcontent);
                        kabanitemcontent.setAttribute("class","kaban-item-content-2");
                        kabanitemcontent.setAttribute("ng-repeat","item in datas");
                        kabanitemcontent.setAttribute("id","kaban_{{item.id}}");
                        var kabanitemcontentpart1 = document.createElement("span");
                        kabanitemcontent.appendChild(kabanitemcontentpart1);
                        kabanitemcontentpart1.setAttribute("class","kaban-item-content-part-1 background-color-11");
                        var kabanitemcontentpart2 = document.createElement("span");
                        kabanitemcontent.appendChild(kabanitemcontentpart2);  
//                        var kabanitemtitre = document.createElement("div");
//                        kabanitemtitre.setAttribute("class","kaban-item-titre");
//                        kabanitemcontentpart2.appendChild(kabanitemtitre);
//                        var kabanitemtitre1 = document.createElement("span");
//                        kabanitemtitre.appendChild(kabanitemtitre1);
//                        kabanitemtitre1.setAttribute("class","kaban-item-titre-1");
//                        kabanitemtitre1.appendChild(document.createTextNode("  "));
//                        var kabanitemtitre2 = document.createElement("span");
//                        kabanitemtitre.appendChild(kabanitemtitre2);
//                        kabanitemtitre2.setAttribute("class","kaban-item-titre-2");
//                        i = document.createElement("i");
//                        kabanitemtitre2.appendChild(i);
//                        i.setAttribute("class","fa fa-ellipsis-v");
                        var bodycontainer = document.createElement("div");
                        bodycontainer.setAttribute("ng-click","viewAction(item)");
                        kabanitemcontentpart2.appendChild(bodycontainer);
                        kabanitemcontentpart2.setAttribute("class","kaban-item-content-part-2");
                        bodycontainer.innerHTML = scope.currentAction.kaban.script;  
                        var kabanitemimage = document.createElement("div");
                        kabanitemcontentpart2.appendChild(kabanitemimage);
                        kabanitemimage.setAttribute("class","kaban-item-image");
                        i = document.createElement("i");
                        kabanitemimage.appendChild(i);
                        i.setAttribute("ng-show","currentModule.name==='application' && item.active==true");
                        i.setAttribute("class","fa fa-check-circle");
                        i.setAttribute("style","color: #00A09D;font-size: 17px;");
                        i.setAttribute("ng-click","installAction(item)");
                        i = document.createElement("i");
                        kabanitemimage.appendChild(i);
                        i.setAttribute("ng-show","currentModule.name==='application' && item.active==false");
                        i.setAttribute("class","fa fa-circle-thin");
                        i.setAttribute("style","font-size: 17px;");
                        i.setAttribute("ng-click","installAction(item)");
                        i = document.createElement("i");
                        kabanitemimage.appendChild(i);
                        i.setAttribute("ng-hide","currentModule.name==='application'||!canUpdate(item)");
                        i.setAttribute("class","fa fa-edit");
                        i.setAttribute("ng-click","updateAction(item)");
                        i = document.createElement("i");
                        kabanitemimage.appendChild(i);
                        i.setAttribute("class","fa fa-trash");
                        i.setAttribute("ng-hide","currentModule.name==='application'||!canDelete(item)");
                        i.setAttribute("ng-click","deleteAction(item)");
                        return worflowcontent;
//                    var divElem = document.createElement("div");
//                    divElem.setAttribute("id","datawidget");
//                    divElem.setAttribute("style","padding-left:  10px;margin:10px;");
//                    divElem.setAttribute("ng-repeat","item in datas");
//                    divElem.appendChild(this.kabanBuilder(scope,metaData));  
//                    return divElem;
               }else{
                   this.dispatchDatas(scope);
                   var heigth = 275*scope.metaData.states.length;
                  var worflowcontent = document.createElement("div");
//                  worflowcontent.setAttribute("id","datawidget");
                  worflowcontent.setAttribute("class","workflow-content");
                  worflowcontent.setAttribute("style","width:"+heigth+"px;");
                  var worklowitemcontent = document.createElement("span");
                  worklowitemcontent.setAttribute("class","workflow-item-content");
                  worklowitemcontent.setAttribute("ng-repeat","state in metaData.states");  
                  worflowcontent.appendChild(worklowitemcontent);
                  var itemtitre = document.createElement("div");
                  worklowitemcontent.appendChild(itemtitre);
                  itemtitre.setAttribute("class","item-titre");
                  var titre = document.createElement("span");
                  itemtitre.appendChild(titre);
                  titre.setAttribute("class","titre");
                  titre.appendChild(document.createTextNode("{{state.intitule | translate}}"));
                 worklowitemcontent.appendChild(itemtitre);
                 var titreoptions = document.createElement("span");
                 itemtitre.appendChild(titreoptions);
                 titreoptions.setAttribute("class","titre-options");
                 var titreoption1 = document.createElement("span");
                 titreoptions.appendChild(titreoption1);
                 titreoption1.setAttribute("class","workflow-hover titre-option");
                 var i = document.createElement("i");
                 titreoption1.appendChild(i);
                 i.setAttribute("class","fa fa-cog");
                 //option 2
                 titreoption1 = document.createElement("span");
                 titreoptions.appendChild(titreoption1);
                 titreoption1.setAttribute("class","workflow-hover titre-option");
                 var i = document.createElement("i");
                 titreoption1.appendChild(i);
                 i.setAttribute("class","fa fa-plus");
                 //Option items
                 var itemoptions = document.createElement("div");
                 itemoptions.setAttribute("class","item-options");
                 worklowitemcontent.appendChild(itemoptions);
                 var optionsbarre = document.createElement("span");
                 itemoptions.appendChild(optionsbarre);
                 optionsbarre.setAttribute("class","options-barre");
                 var optionbarreinput = document.createElement("div");
                 optionsbarre.appendChild(optionbarreinput);
                 optionbarreinput.setAttribute("class","options-barre-input");
                 optionbarreinput.setAttribute("style","background-color:{{state.couleur}}");
                 var optionsnumber = document.createElement("span");
                 itemoptions.appendChild(optionsnumber);
                 optionsnumber.setAttribute("class","options-number");
                 optionsnumber.appendChild(document.createTextNode("0"));
                 //Creation des zones
                 var itemcontent = document.createElement("div");
                 itemcontent.setAttribute("id","{{state.code}}");
                 itemcontent.setAttribute("class","item-content");
                 itemcontent.setAttribute("ondrop","drop(event)");
                 itemcontent.setAttribute("ondragover","allowDrop(event)");
                 worklowitemcontent.appendChild(itemcontent);
                 var kabanitemcontent = document.createElement("div");
                 itemcontent.appendChild(kabanitemcontent);
                 kabanitemcontent.setAttribute("class","kaban-item-content");
                 kabanitemcontent.setAttribute("ng-repeat","item in dataCache.blocks[state.code]");
                 kabanitemcontent.setAttribute("id","draggable{{item.id}}");
                 kabanitemcontent.setAttribute("draggable","true");
                 kabanitemcontent.setAttribute("ondragstart","drag(event)");
                 var kabanitemcontentpart1 = document.createElement("span");
                 kabanitemcontent.appendChild(kabanitemcontentpart1);
                 kabanitemcontentpart1.setAttribute("class","kaban-item-content-part-1 background-color-11");
                 var kabanitemcontentpart2 = document.createElement("span");
                 kabanitemcontent.appendChild(kabanitemcontentpart2);  
//                 var kabanitemtitre = document.createElement("div");
//                 kabanitemtitre.setAttribute("class","kaban-item-titre");
//                 kabanitemcontentpart2.appendChild(kabanitemtitre);
//                 var kabanitemtitre1 = document.createElement("span");
//                 kabanitemtitre.appendChild(kabanitemtitre1);
//                 kabanitemtitre1.setAttribute("class","kaban-item-titre-1");
//                 kabanitemtitre1.appendChild(document.createTextNode("  "));
//                 var kabanitemtitre2 = document.createElement("span");
//                 kabanitemtitre.appendChild(kabanitemtitre2);
//                 kabanitemtitre2.setAttribute("class","kaban-item-titre-2");
//                 i = document.createElement("i");
//                 kabanitemtitre2.appendChild(i);
//                 i.setAttribute("class","fa fa-ellipsis-v");
                 var bodycontainer = document.createElement("div");
                 kabanitemcontentpart2.appendChild(bodycontainer);
                 bodycontainer.setAttribute("ng-dblclick","viewAction(item)");
                 bodycontainer.setAttribute("ng-mouseenter","selectedItem(item)");
                 kabanitemcontentpart2.setAttribute("class","kaban-item-content-part-2");
                 bodycontainer.innerHTML = scope.currentAction.kaban.script;  
                 var kabanitemimage = document.createElement("div");
                 kabanitemcontentpart2.appendChild(kabanitemimage);
                 kabanitemimage.setAttribute("class","kaban-item-image");
                 i = document.createElement("i");
                 kabanitemimage.appendChild(i);
                 i.setAttribute("class","fa fa-edit");
                 i.setAttribute("ng-click","updateAction(item)");
                 i = document.createElement("i");
                 kabanitemimage.appendChild(i);
                 i.setAttribute("class","fa fa-trash");
                 i.setAttribute("ng-click","deleteAction(item)");
                 return worflowcontent;
               }//end if(metaData.states.length<=0)
            }
            ,
            /**container.innerHTML = scope.currentAction.kaban.script;    
             * Construction du panel tableau de bord
             * @param {type} data
             * @returns {undefined}
             */
            dashboardContainerBuilder:function(data,scope){
                var divElem = document.createElement("div");
                divElem.setAttribute("class","row");
                divElem.setAttribute("style","padding-left:  10px;margin:10px;");
                for(var i=0;i<data.dashboards.length;i++){
                    if(data.dashboards[i]){                        
                        divElem.appendChild(this.dashboardBuilder(data.dashboards[i]));                        
                    }//end if(data.dashboards[i])
                }//end for(var i=0;i<data.dashboards.length;i++)
                return divElem;
            },
          
            /**
             * 
             * @param {type} date
             * @returns {undefined}
             */    
             formatDat:function(date){
                if(date){
                    var today = new Date();
                    if(today.getDate()==date.getDate()&&today.getMonth()==date.getMonth()&&today.getYear()==date.getYear()){
                        return date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
                    }else{
                        return date.toDateString();
                    }
                }
            },
            
            /**
             * 
             * @param {type} module
             * @param {type} action
             * @param {type} entity
             * @param {type} user
             * @returns {commons_L61.commonsAnonym$3.createsession.session|Object}
             */
            createsession: function(module,memento,user){
                 var session = new Object();
                 session.module = module ;
                 session.navigator = memento ;
//                 session.entity = entity;
                 session.user = user;
                 this.createCookie("session_"+session.user,angular.toJson(session),30);
//           console.log("principal.createsession ===== cookie read : "+commonsTools.readCookie("session_"+session.user));
               return session ;
            },
            /**
             * 
             * @param {type} name
             * @param {type} value
             * @param {type} minute
             * @returns {undefined}
             */
            createCookie: function(name,value,days){
                if(days){
                    var date = new Date();
                    date.setTime(date.getTime()+(days*60*1000));
                    var expires ="; expires="+date.toGMTString();
                }else{
                    var expires ="";
                }//end if(days){
                document.cookie = name+"="+value+expires+"; path=/";
            },
            /**
             * 
             * @param {type} name
             * @returns {undefined}
             */
            readCookie: function(name){
                var nameEQ = name + "=";
                var ca = document.cookie.split(';');
                for(var i=0;i < ca.length;i++) {
                        var c = ca[i];
                        while (c.charAt(0)==' ') c = c.substring(1,c.length);
                        if (c.indexOf(nameEQ) == 0) return c.substring(nameEQ.length,c.length);
                }
                return null;
            },
            /**
             * 
             * @param {type} id
             * @returns {undefined}
             */
            getDashoardEntryDataFromID:function(data , dashid,entryId){
//                console.log("getDashoardEntryDataFromID ::: "+angular.toJson(data));
                if(data){
                    if(data.dashboards && data.dashboards.length>0){
                        for(var i=0 ; i<data.dashboards.length;i++){
                            var dash = data.dashboards[i];
                            if(dash.code && dash.code==dashid){
                                for(var j=0 ;j<dash.entries.length;j++){
                                    var entry = dash.entries[j];
                                    if(entry.code==entryId){
                                        return entry;
                                    }
                                }
                            }//end if(dash.code && dash.code==dashid){
                        }//end for(var i=0 ; i<data.dashboards.length;i++)
                    }//end if(data.dashboards && data.dashboards.length>0)
                    return null;
                }//end if(data)
                return null ;
            }  ,
            /**
             * 
             * @param {type} model
             * @returns {undefined}
             */
            keygenerator:function(model){
                var parts= model.split(".");
                var key = "";
                for(var i=1 ; i<parts.length;i++){
                    var part = parts[i].split("[");
                    if(i==1){                 
                        key = part[0];                        
                    }else{
                        key+=part[0];
                    }//end if(i==1){     
//                    if(part.length>1){
//                        var left = part[1].split(']');
//                        key+=left[0];
//                    }//end if(part.length>1){
                }
                return key;
            },
            /**
             * 
             * @param {type} model
             * @returns {String}
             */
             keyparentgenerator:function(model){
                var parts= model.split(".");
                var key = "";
                var endIndex = parts.length-1;
                for(var i=1 ; i<endIndex;i++){
                    if(i==1){
                        key = parts[i];
                    }else{
                        key+=parts[i];
                    }
                }
                return key;
            },
            /**
             * 
             * @param {type} errors
             * @returns {Array}
             */
            converErrorsMap : function(errors){
                var array = new Array();
                for(var key in errors){
                    var data = errors[key];
                    for(var key2 in data){
                        var error = data[key2];
                        var obj = new Object();
                        obj['line'] = key;
                        obj['field'] = key2;
                        for(var key3 in error){
//                            console.log("commons.converErrorsMap ========== key : "+key3+" ======= value : "+angular.toJson(error[key3]));
                            var detail = error[key3];
                            obj['value'] = detail['value'];
                            obj['message'] = detail['message'];
                        }//end for(var key3 in error){
                        array.push(obj);
                    }//end for(var key2 in data){
                }//end for(var key in errors){
                return array;
            },
            /**
             * 
             * @param {type} data
             * @param {type} expressions
             * @returns {undefined}
             */
            evaluateExpression:function(data,expressions){
                var result = true;
                for(var i=0 ; i<expressions.length;i++){
                    var expr = expressions[i];
                    if(expr.function=='=='){
                        result &=(data[expr.fieldname]==expr.value);
                    }//end if(expr.function=='==')
//                    console.log("commons.evaluateExpression ============ expr = "+expr+" === field : "+data[expr.fieldname]+" == oper : "+expr.function+" === value : "+expr.value);
                }//end for(var i=0 ; i<expressions.length;i++){
                return result;
            },
            /**
            * 
            * @param {type} metaData
            * @returns {undefined}
            */
           createImportEntity:function(metaData){
               var entity = new Object();
               entity.fichier = null;
               entity.fields = new Array();
               entity.className = metaData.className;
               entity.format ='cvs';
               entity.separator = ',';
               entity.typeexport = '0';
               /**
                * Traitement des champs columns
                */
               for(var i=0 ; i<metaData.columns.length;i++){
                   var ele = metaData.columns[i];
                   /**if(ele.search==true)**/{
                        var field = new Object();
                        field.id = -1 ;
                        field.selected = false ;
                        field.code = ele.fieldName;                        
                        if(ele.type=='object'){
                            if(angular.isDefined(ele.importfield) && 
                                    ele.importfield!=""){
                                field.code = field.code+"."+ele.importfield; 
                                field.className = ele.metaData.className;
//                                console.log("commonsTools.createImportEntity ========================= type : "+angular.toJson(ele));                        
                            }//end if(angular.isDefined(field.importfield) &&
                        }//end if(field.type=='object'){
                        field.description = ele.fieldLabel;
                        field.optional = !ele.optional;
                        field.selected = field.optional;
                        field.pattern = ele.pattern;
                        field.length = ele.length;
                        field.min = ele.min;
                        field.max = ele.max ;
                        field.unique = ele.unique;
                        field.nullable = ele.nullable;
                        entity.fields.push(field);
                   }//end if(ele.search==true){
               }//end for(var i=0 ; i<metaData.columns.length;i++){
               /**
                * Traitement des groups
                */
               for(var i=0 ; i<metaData.groups.length;i++){
                   for(var j=0 ;j<metaData.groups[i].columns.length;j++){
                       var ele = metaData.groups[i].columns[j];
                       /**if(ele.search==true)**/{
                            var field = new Object();
                            field.id = -1 ;
                            field.selected = false ;
                            field.code = ele.fieldName;
//                             console.log("commonsTools.createImportEntity ========================= type : "+ele.type+" ===== importfield : "+ele.importfield+" filed code : "+field.code);
                            if(ele.type=='object'){
                                if(angular.isDefined(ele.importfield) && 
                                        ele.importfield!=""){
                                    field.code = field.code+"."+ele.importfield;  
                                    field.className = ele.metaData.className;
//                                    console.log("commonsTools.createImportEntity ========================= type : "+angular.toJson(ele));
                                }//end if(angular.isDefined(field.importfield) &&
                            }//end if(field.type=='object'){
                            field.description = ele.fieldLabel;
                            field.optional = !ele.optional;
                            field.selected = field.optional;
                            field.pattern = ele.pattern;
                            field.length = ele.length;
                            field.min = ele.min;
                            field.max = ele.max ;
                            field.unique = ele.unique;
                            field.nullable = ele.nullable;
                            entity.fields.push(field);
                       }//end if(ele.search==true){
                   }//end for(var j=0 ;j<metaData.groups[i].columns.length;j++){
               }//end for(var i=0 ; i<metaData.groups.length;i++){
               return entity;
           },
         
       };
            
 });
/**
 * Tools for Rest
 */
angular.module('keren.core.commons')
.factory("backendService" , function($http ,$resource , $location){
    var urlPath = "";
    //Resource rest pour l'interaction avec le back end
    var restResource = null;
     return{
            /**
             Build the restName base of the entityName
            **/
            url:function(entityName,moduleName){
                 urlPath = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/"+moduleName+"/"+entityName+"/";
                 restResource = $resource(urlPath+":path/:first/:max/:propertyname/:id"
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
                          }
                   });
                 //console.log("restService == "+urlPath);
                 return urlPath;
            },
            /**
               Return the metaData of the entity
            **/
            getMetaData:function(){
                  if(angular.isDefined(restResource)){
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
               //console.log(" uniqueProperties == "+angular.toJson(properties));
                if(angular.isDefined(restResource)){
                    return  restResource.query({path:'unique'});
                 }
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
           findByUniqueProperty:function(propertyName , value){

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
               //URL de la resource responsable de transfert du fichier
               var url = $location.protocol()+"://"+$location.host()+":"+$location.port()+"/kerencore/resource/upload";
               var fd = new FormData();
               //Take the first select 
               for(var i=0;i<files.length;i++){
                   fd.append("file_"+(i+1),files[i]);               
               }
               return $http.post(url,fd
                    ,{withCredentials:true,headers:{'Content-Type':undefined},
                       transformRequest: angular.identity});
               
           }
     };
});
//Javascript observer design pattern implementations
/**
 * Observable class
 * declaration de l'observable
 */
var Observable = function(){
    this.observers = new Array();
};

/**
 * Fonction de l'object observable
 */
Observable.prototype = {
    
    //enregistrement un observer a recevoir des notifications
    register:function(observer){
        if(angular.isDefined(observer)){
            this.observers.push(observer);           
        }//end if(angular.isDefined(observer))
        return this;
    },
    //envoie une notification a tous les observers enregistres
    notifyObservers:function(event , parameters){
        for(var i=0;i<this.observers.length;i++){
            var observer = this.observers[i];
            observer.notify(event,parameters);
        }//end for(var i=0;i<this.observers.length;i++)
    }
};
// gestion de l'héritage
function extend(C, P) {
  var F = function () {};
  F = P;
  F.prototype = $.extend(P.prototype, C.prototype);
  C.prototype = new F();
  C.uber = P.prototype;
  C.prototype.constructor = C;
}
/**
 * Declaration de l'observable
 */
var Observer = function(){
   this.observers = new Array(); 
} ;

/**
 * fonctions de l'object observable
 */
Observer.prototype = {
    register:function(observable){
       this.observable = observable;
       this.notifyMe();
       return this;
    },
    notifyMe:function(){
        this.observable.register(this);
    },
    /**
     * 
     * @param {type} event
     * @param {type} parameters
     * @returns {undefined}
     */
    notifyObservers:function(event , parameters){
        this.observable.notifyObservers(event,parameters);
    },
    /**
     * 
     * @param {type} event
     * @param {type} parameters
     * @returns {Observer.prototype}
     */
    notify:function(event , parameters){
        return this;
    }   
};