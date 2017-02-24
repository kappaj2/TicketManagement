(function() {
    'use strict';
    angular
        .module('ticketManagementApp')
        .factory('LoanEquipment', LoanEquipment);

    LoanEquipment.$inject = ['$resource', 'DateUtils'];

    function LoanEquipment ($resource, DateUtils) {
        var resourceUrl =  'api/loan-equipments/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.initialUploadDate = DateUtils.convertDateTimeFromServer(data.initialUploadDate);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
