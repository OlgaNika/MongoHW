var myApp = angular.module('App',[]);

myApp.controller('Report', ['$scope','$http', function($scope,$http) {
            console.log('addReport started');
             $http.get('http://localhost:8080/report').
                then(function(response) {
                  $scope.reports = response.data;
                 });
        $scope.submit = function() {
            $http.post('http://localhost:8080/report',$scope.report,{
                headers: {'Content-Type': 'application/json'}}).
                then(function(response) {
                    console.log('first');
                    console.log(response);
                   $scope.reports.push(response.data);
                  // console.log($scope.reports);

                }, function (response) {
                    console.log('second');
                    console.log(response);
                    console.log($scope.report);

                });
        };
    }]);

    myApp.controller('Expence', ['$scope','$http', function($scope,$http) {
        $http.get('http://localhost:8080/expence').
        then(function(response) {
            $scope.expences = response.data;
        });
        $scope.submit = function() {
            $http.post('http://localhost:8080/expence',$scope.expence,{
                headers: {'Content-Type': 'application/json'}}).
                then(function(response) {
                    console.log('first');
                    console.log(response);
                    $scope.expences.push(response.data);

                }, function (response) {
                    console.log('second');
                    console.log(response);
                    console.log($scope.expence);

                });
        };
    }]);





