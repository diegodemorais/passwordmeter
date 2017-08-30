var app = angular.module('PasswordMeter', []);
app.controller('PasswordMeterController', function($scope, $http) {
    $scope.change = function() {
        if ($scope.form.password.length>0) {
            $http({
                method : "POST",
                url : "/password/",// + encodeURI($scope.form.password)
                data: ($scope.form.password.length=0) ? "" : $scope.form.password
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
        } else {
            $http({
                method : "GET",
                url : "pass="
            }).then(function successCallback(response) {
                $scope.color = { 'background-color': "crimson" };
                $scope.meter = response.data;
            }, function errorCallback(response) {
                console.log(response.statusText);
            });
        }
    };
    
    this.$onInit = function() {
        $http({
            method : "GET",
            url : "pass="
            }).then(function successCallback(response) {
        $scope.color = { 'background-color': "crimson" };
        $scope.meter = response.data;
        }, function errorCallback(response) {
            console.log(response.statusText);
        });
    };
//    
});