angular.module('myApp', [
    'ngRoute',
    'myApp.coding',
    'myApp.games',
    'myApp.graphics'
]).
        config(['$routeProvider', function ($routeProvider) {
                $routeProvider.otherwise({redirectTo: '/home'}),
                        $routeProvider.when('/example', {
                            templateUrl: 'app/views/example.html'
                                    //controller: '',
                                    //controllerAs: 'ctrl'
                        });
            }])
        
       ;