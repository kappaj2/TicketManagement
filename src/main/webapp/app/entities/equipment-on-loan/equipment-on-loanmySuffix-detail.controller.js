(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('EquipmentOnLoanMySuffixDetailController', EquipmentOnLoanMySuffixDetailController);

    EquipmentOnLoanMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'EquipmentOnLoan'];

    function EquipmentOnLoanMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, EquipmentOnLoan) {
        var vm = this;

        vm.equipmentOnLoan = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ticketManagementApp:equipmentOnLoanUpdate', function(event, result) {
            vm.equipmentOnLoan = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
