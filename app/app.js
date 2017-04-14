angular.module('myApp', [
    'ngRoute',
    'myApp.coding',
    'myApp.games',
    'myApp.graphics'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/home'}),
                        $routeProvider.when('/home', {
                            templateUrl: 'app/views/home/home.html'
                                    //controller: '',
                                    //controllerAs: 'ctrl'
                        });
            }])
        
       ;