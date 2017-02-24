(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('LoanEquipmentMySuffixDeleteController',LoanEquipmentMySuffixDeleteController);

    LoanEquipmentMySuffixDeleteController.$inject = ['$uibModalInstance', 'entity', 'LoanEquipment'];

    function LoanEquipmentMySuffixDeleteController($uibModalInstance, entity, LoanEquipment) {
        var vm = this;

        vm.loanEquipment = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            LoanEquipment.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
