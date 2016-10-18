angular.module('app', [])
    .controller('ctrl', function($http) {
        var self = this;
        $http.get('/api/users/').then(function(response) {

        })
    });