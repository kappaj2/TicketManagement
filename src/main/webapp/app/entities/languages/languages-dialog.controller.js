(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('LanguagesDialogController', LanguagesDialogController);

    LanguagesDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Languages'];

    function LanguagesDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Languages) {
        var vm = this;

        vm.languages = entity;
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
            if (vm.languages.id !== null) {
                Languages.update(vm.languages, onSaveSuccess, onSaveError);
            } else {
                Languages.save(vm.languages, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('ticketManagementApp:languagesUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
