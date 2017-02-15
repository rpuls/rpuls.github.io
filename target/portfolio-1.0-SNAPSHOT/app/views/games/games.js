'use strict';

angular.module('myApp.games', ['ngRoute'])

        .config(['$routeProvider', function ($routeProvider) {
                $routeProvider.when('/games', {
                    templateUrl: 'app/views/games/games.html'
                            //controller: '',
                            //controllerAs: 'ctrl'
                });
            }]);