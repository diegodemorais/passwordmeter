//angular.module('PasswordMeter', [])
//.controller('PasswordMeterController', function($scope, $http) {
//    $http.get('http://localhost:8081/pass=Diego123',{params: {pass: "Diego123"}}).
//        then(function(response) {
//            $scope.meter = response.data;
//            console.log(response.data);
//        });
//});
angular.module('PasswordMeter', [])
    .controller('PasswordMeterController', ['$scope', function ($scope) {
    var inputMin = 3;
    $scope.someVal = '';
    $scope.result = '';
    $scope.textChanged = function() {
        if ($scope.someVal.length >= inputMin) executeSomething()
        else $scope.result = '';
    };

    function executeSomething() {
        $scope.result = $scope.someVal;
    };
}]);