//angular.module('PasswordMeter', [])
//.controller('PasswordMeterController', function($scope, $http) {
//    $http.post('http://localhost:8081/pass='+$scope.password).
//    //{params: {pass: "Diego123"}}
//        then(function submit(response) {
//            $scope.meter = response.data;
//            console.log(response.data);
//        });
//});
var app = angular.module('PasswordMeter', []);
app.controller('PasswordMeterController', function($scope, $http) {
    $scope.change = function() {
        $http({
            method : "GET",
            url : "pass=" + $scope.form.password
            }).then(function successCallback(response) {
        $scope.meter = response.data;
        }, function errorCallback(response) {
        console.log(response.statusText);
        });
    };
});