(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .controller('EmployeesController', EmployeesController);

    EmployeesController.$inject = ['$scope', '$state', 'Employees'];

    function EmployeesController ($scope, $state, Employees) {
        var vm = this;

        vm.employees = [];

        loadAll();

        function loadAll() {
            Employees.query(function(result) {
                vm.employees = result;
                vm.searchQuery = null;
            });
        }
    }
})();
