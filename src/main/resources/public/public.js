angular.module('demo', [])
.controller('Report', function($scope, $http) {
    $http.get('/report').
        then(function(response) {
            $scope.reports = response.data;
        });
});

