(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('EquipmentOnLoanMySuffixDialogController', EquipmentOnLoanMySuffixDialogController);

    EquipmentOnLoanMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'EquipmentOnLoan'];

    function EquipmentOnLoanMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, EquipmentOnLoan) {
        var vm = this;

        vm.equipmentOnLoan = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.equipmentOnLoan.id !== null) {
                EquipmentOnLoan.update(vm.equipmentOnLoan, onSaveSuccess, onSaveError);
            } else {
                EquipmentOnLoan.save(vm.equipmentOnLoan, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ticketManagementApp:equipmentOnLoanUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.dateBookedOut = false;
        vm.datePickerOpenStatus.dateInstalledAtTenant = false;
        vm.datePickerOpenStatus.dateRemovedFromTenant = false;
        vm.datePickerOpenStatus.dateBookedBackIn = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
