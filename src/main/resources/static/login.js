angular.module('app', []).controller('indexController', function ($scope, $http) {
    const contextPath = 'http://localhost:8080';

    $scope.loadToken = function () {
        $http({
            url: contextPath + '/auth',
            method: 'POST',
            params: {
                username: $scope.user ? $scope.user.username : null,
                password: $scope.user ? $scope.user.password : null
            }
        }).then(function (response) {
            $scope.localStorage = response.data;

        });
    };

});