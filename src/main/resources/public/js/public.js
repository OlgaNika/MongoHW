var myApp = angular.module('App',[]);


myApp.controller('Expence', ['$scope','$http', function($scope,$http) {
    console.log('Expence started');
    $http.get('http://localhost:8080/expence').
    then(function(response) {
        $scope.expences = response.data;
    });


    $scope.showForThisMonth = function() {
        console.log('show for this month - but show all againn');
        $http.get('http://localhost:8080/expence').
        then(function(response) {
        $scope.expences = response.data;
        console.log('expencies reloaded');
    });
    };

    $scope.showByType = function(index) {
        console.log('showByType for index='+index+';type='+$scope.expences[index].type);
        $http.get('http://localhost:8080/expenceByType/'+$scope.expences[index].type).
            then(function(response) {
                $scope.expences = response.data;
                console.log('expencies reloaded by type');
            }, function (response) {
                console.log('error!');
                console.log(response);
            });
    };

    $scope.removeItem = function(index) {
        console.log('delete index='+index+';id='+$scope.expences[index].id);
        $http.delete('http://localhost:8080/expence/'+$scope.expences[index].id).
            then(function(response) {
                console.log(response);
                $scope.expences.splice(index, 1);
            }, function (response) {
                console.log('error!');
                console.log(response);
            });
    };
    $scope.submit = function() {
        if (typeof $scope.expence.amount !== "undefined") {
            $http.post('http://localhost:8080/expence',$scope.expence,{
            headers: {'Content-Type': 'application/json'}}).
            then(function(response) {
                console.log('success');
                console.log(response);
                $scope.expences.push(response.data);

            }, function (response) {
                console.log('error');
                console.log(response);
                console.log($scope.expence);

            });
        };
    };

}]);



myApp.controller('Report', ['$scope','$http', function($scope,$http) {

        console.log('Report started');

        $scope.removeItem = function(index) {
            console.log('delete index='+index);
            console.log('delete id='+$scope.reports[index].id);
            $http.delete('http://localhost:8080/report/'+$scope.reports[index].id).
                then(function(response) {
                    console.log('deleted!');
                    console.log(response);
                    $scope.reports.splice(index, 1);
                }, function (response) {
                    console.log('error!');
                    console.log(response);
                });
        };

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








