(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('EmployeesDialogController', EmployeesDialogController);

    EmployeesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Employees'];

    function EmployeesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Employees) {
        var vm = this;

        vm.employees = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.employees.id !== null) {
                Employees.update(vm.employees, onSaveSuccess, onSaveError);
            } else {
                Employees.save(vm.employees, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ticketManagementApp:employeesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
