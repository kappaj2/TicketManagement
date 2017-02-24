(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('LoanEquipmentMySuffixDialogController', LoanEquipmentMySuffixDialogController);

    LoanEquipmentMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'LoanEquipment'];

    function LoanEquipmentMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, LoanEquipment) {
        var vm = this;

        vm.loanEquipment = entity;
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
            if (vm.loanEquipment.id !== null) {
                LoanEquipment.update(vm.loanEquipment, onSaveSuccess, onSaveError);
            } else {
                LoanEquipment.save(vm.loanEquipment, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ticketManagementApp:loanEquipmentUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.initialUploadDate = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
