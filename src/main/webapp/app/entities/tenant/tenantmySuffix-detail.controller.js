(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('TenantMySuffixDetailController', TenantMySuffixDetailController);

    TenantMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Tenant'];

    function TenantMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, Tenant) {
        var vm = this;

        vm.tenant = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ticketManagementApp:tenantUpdate', function(event, result) {
            vm.tenant = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
