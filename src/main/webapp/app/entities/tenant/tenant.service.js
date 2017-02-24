(function() {
    'use strict';
    angular
        .module('ticketManagementApp')
        .factory('Tenant', Tenant);

    Tenant.$inject = ['$resource', 'DateUtils'];

    function Tenant ($resource, DateUtils) {
        var resourceUrl =  'api/tenants/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.creationDate = DateUtils.convertDateTimeFromServer(data.creationDate);
                        data.dateCeased = DateUtils.convertDateTimeFromServer(data.dateCeased);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
