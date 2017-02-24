(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('LoanEquipmentMySuffixDetailController', LoanEquipmentMySuffixDetailController);

    LoanEquipmentMySuffixDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'LoanEquipment'];

    function LoanEquipmentMySuffixDetailController($scope, $rootScope, $stateParams, previousState, entity, LoanEquipment) {
        var vm = this;

        vm.loanEquipment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ticketManagementApp:loanEquipmentUpdate', function(event, result) {
            vm.loanEquipment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
