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
            method : "POST",
            url : "pass=" + $scope.form.password
            }).then(function successCallback(response) {
        $scope.meter = response.data;
        if (response.data.nota <= 100) $scope.color = { 'background-color': "green" };
        if (response.data.nota < 80) $scope.color = { 'background-color': "yellowgreen" };
        if (response.data.nota < 60) $scope.color = { 'background-color': "gold" };
        if (response.data.nota < 40) $scope.color = { 'background-color': "orangered" };  
        if (response.data.nota < 20) $scope.color = { 'background-color': "crimson" };
        }, function errorCallback(response) {
        console.log(response.statusText);
        });
    };
    
    this.$onInit = function() {
        $http({
            method : "POST",
            url : "pass="
            }).then(function successCallback(response) {
        $scope.color = { 'background-color': "crimson" };
        $scope.meter = response.data;
        }, function errorCallback(response) {
        console.log(response.statusText);
        });
    };
    
});