(function() {
    'use strict';
    angular
        .module('ticketManagementApp')
        .factory('EquipmentOnLoan', EquipmentOnLoan);

    EquipmentOnLoan.$inject = ['$resource', 'DateUtils'];

    function EquipmentOnLoan ($resource, DateUtils) {
        var resourceUrl =  'api/equipment-on-loans/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.dateBookedOut = DateUtils.convertDateTimeFromServer(data.dateBookedOut);
                        data.dateInstalledAtTenant = DateUtils.convertDateTimeFromServer(data.dateInstalledAtTenant);
                        data.dateRemovedFromTenant = DateUtils.convertDateTimeFromServer(data.dateRemovedFromTenant);
                        data.dateBookedBackIn = DateUtils.convertDateTimeFromServer(data.dateBookedBackIn);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
