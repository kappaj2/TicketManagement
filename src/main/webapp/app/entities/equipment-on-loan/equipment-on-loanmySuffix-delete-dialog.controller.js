(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('EquipmentOnLoanMySuffixDeleteController',EquipmentOnLoanMySuffixDeleteController);

    EquipmentOnLoanMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'EquipmentOnLoan'];

    function EquipmentOnLoanMySuffixDeleteController($uibModalInstance, entity, EquipmentOnLoan) {
        var vm = this;

        vm.equipmentOnLoan = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            EquipmentOnLoan.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
