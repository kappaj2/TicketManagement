(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('TenantMySuffixDialogController', TenantMySuffixDialogController);

    TenantMySuffixDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Tenant'];

    function TenantMySuffixDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Tenant) {
        var vm = this;

        vm.tenant = entity;
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
            if (vm.tenant.id !== null) {
                Tenant.update(vm.tenant, onSaveSuccess, onSaveError);
            } else {
                Tenant.save(vm.tenant, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ticketManagementApp:tenantUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.creationDate = false;
        vm.datePickerOpenStatus.dateCeased = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
