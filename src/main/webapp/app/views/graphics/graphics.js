'use strict';

angular.module('myApp.graphics', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/graphics', {
                    templateUrl: 'app/views/graphics/graphics.html'
                            //controller: '',
                            //controllerAs: 'ctrl'
                });
            }]);