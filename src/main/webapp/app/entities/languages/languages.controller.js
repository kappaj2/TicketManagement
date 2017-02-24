(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('LanguagesController', LanguagesController);

    LanguagesController.$inject = ['$scope', '$state', 'Languages'];

    function LanguagesController ($scope, $state, Languages) {
        var vm = this;

        vm.languages = [];

        loadAll();

        function loadAll() {
            Languages.query(function(result) {
                vm.languages = result;
                vm.searchQuery = null;
            });
        }
    }
})();
