angular.module('examTest',[])

        .controller("memberCtrl", function ($scope, MemberService) {

            $scope.members = [];

            MemberService.getMembers().then(
                    function (response) {
                        $scope.members = response.data;
                    });
        })
        .service('MemberService', function ($http) {
            var func = {};
            func.getMembers = function () {
                return $http.get('api/members/all'); //<--<-- rest API
            };

            return func;

        })
;

