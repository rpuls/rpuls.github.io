'use strict';

angular.module('myApp.coding', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/coding', {
                    templateUrl: 'app/views/coding/coding.html'
                            //controller: '',
                            //controllerAs: 'ctrl'
                });
            }]);