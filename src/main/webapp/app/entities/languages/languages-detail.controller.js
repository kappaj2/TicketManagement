(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('LanguagesDetailController', LanguagesDetailController);

    LanguagesDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Languages'];

    function LanguagesDetailController($scope, $rootScope, $stateParams, previousState, entity, Languages) {
        var vm = this;

        vm.languages = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('ticketManagementApp:languagesUpdate', function(event, result) {
            vm.languages = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
