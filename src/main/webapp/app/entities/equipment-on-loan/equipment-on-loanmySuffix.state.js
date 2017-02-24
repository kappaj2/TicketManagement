(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('equipment-on-loanmySuffix', {
            parent: 'entity',
            url: '/equipment-on-loanmySuffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ticketManagementApp.equipmentOnLoan.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipment-on-loan/equipment-on-loansmySuffix.html',
                    controller: 'EquipmentOnLoanMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('equipmentOnLoan');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('equipment-on-loanmySuffix-detail', {
            parent: 'entity',
            url: '/equipment-on-loanmySuffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ticketManagementApp.equipmentOnLoan.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/equipment-on-loan/equipment-on-loanmySuffix-detail.html',
                    controller: 'EquipmentOnLoanMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('equipmentOnLoan');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'EquipmentOnLoan', function($stateParams, EquipmentOnLoan) {
                    return EquipmentOnLoan.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'equipment-on-loanmySuffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('equipment-on-loanmySuffix-detail.edit', {
            parent: 'equipment-on-loanmySuffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-on-loan/equipment-on-loanmySuffix-dialog.html',
                    controller: 'EquipmentOnLoanMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EquipmentOnLoan', function(EquipmentOnLoan) {
                            return EquipmentOnLoan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipment-on-loanmySuffix.new', {
            parent: 'equipment-on-loanmySuffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-on-loan/equipment-on-loanmySuffix-dialog.html',
                    controller: 'EquipmentOnLoanMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                equipmentOnLoanEntryId: null,
                                tenantId: null,
                                equipmentId: null,
                                dateBookedOut: null,
                                dateInstalledAtTenant: null,
                                bookOutTechnitian: null,
                                dateRemovedFromTenant: null,
                                installationTechnitian: null,
                                removalTechnitian: null,
                                dateBookedBackIn: null,
                                bookInTechnitian: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('equipment-on-loanmySuffix', null, { reload: 'equipment-on-loanmySuffix' });
                }, function() {
                    $state.go('equipment-on-loanmySuffix');
                });
            }]
        })
        .state('equipment-on-loanmySuffix.edit', {
            parent: 'equipment-on-loanmySuffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-on-loan/equipment-on-loanmySuffix-dialog.html',
                    controller: 'EquipmentOnLoanMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['EquipmentOnLoan', function(EquipmentOnLoan) {
                            return EquipmentOnLoan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipment-on-loanmySuffix', null, { reload: 'equipment-on-loanmySuffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('equipment-on-loanmySuffix.delete', {
            parent: 'equipment-on-loanmySuffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/equipment-on-loan/equipment-on-loanmySuffix-delete-dialog.html',
                    controller: 'EquipmentOnLoanMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['EquipmentOnLoan', function(EquipmentOnLoan) {
                            return EquipmentOnLoan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('equipment-on-loanmySuffix', null, { reload: 'equipment-on-loanmySuffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
