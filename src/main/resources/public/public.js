var myApp = angular.module('App',[]);

myApp.controller('Report', function($scope, $http) {
    $http.get('/report').
        then(function(response) {
            $scope.reports = response.data;
        });
});
myApp.controller('Expence', function($scope, $http) {
    $http.get('/expence').
        then(function(response) {
            $scope.expences = response.data;
        });
});







