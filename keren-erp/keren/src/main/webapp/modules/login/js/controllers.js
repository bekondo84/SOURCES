/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
angular.module('keren.core.login' , ['ngResource','ngCookies','keren.core.commons']);

angular.module('keren.core.login')
        .controller('loginCtrl' , function($rootScope,$scope,$location,$http,authenticationService,commonsTools){
            $scope.dataLoading = true ;
            $scope.username = null;
            $scope.password = null;
            $scope.remember = false;
            
            /**
             * 
             * @returns {undefined}
             */
            (function initController(){
                authenticationService.clearCredentials();
            })();
            
            /**
             * 
             * @returns {undefined}
             */
            $scope.login = function(e){
//                console.log("Authentication Login methode === "+$scope.username+" === "+$scope.password+" ====== event : "+e.key);
                if(angular.isDefined(e) && e.key!="Enter"){
                    return ;
                }//end if(angular.isDefined(e) && e.key!="Enter"){
                authenticationService.login($scope.username,$scope.password)
                        .then(function(response){
                            var urlPath = "http://"+$location.host()+":"+$location.port()+"/keren/auth/login/crypto"; 
                            $http.post(urlPath ,{username:$scope.username,password:$scope.password})
                                    .then(function(response){
//                                        console.log("$scope.login = function() remember == encrypt pwd : "+response.data);                            
                                        authenticationService.setCredentials($scope.username,response.data,$scope.remember);                                        
                                    },function(error){
                                        commonsTools.notifyWindow("Echec authentification" ,"<br/>"+"Echec de recupération des paramètres ","danger");
                                        $rootScope.$broadcast("login" , {username:$scope.username , password:$scope.password});
                                    });
                            
                            //$location.path('/authenticate');
//                            console.log("Authentication Success === "+response);
                        },function(error){
                            //commonsTools.notifyWindow();
                            //$location.path('/login');
//                            console.log("Authentication Success === "+error);
                            commonsTools.notifyWindow("Echec authentification" ,"<br/>"+"Le login ou le mot de passe est incorrect","danger");
                            $rootScope.$broadcast("login" , {username:$scope.username , password:$scope.password});
                        });
            };
           
        });

