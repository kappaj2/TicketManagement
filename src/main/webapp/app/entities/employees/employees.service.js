(function() {
    'use strict';
    angular
        .module('ticketManagementApp')
        .factory('Employees', Employees);

    Employees.$inject = ['$resource'];

    function Employees ($resource) {
        var resourceUrl =  'api/employees/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
