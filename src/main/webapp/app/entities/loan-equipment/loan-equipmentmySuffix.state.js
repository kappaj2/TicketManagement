(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('loan-equipmentmySuffix', {
            parent: 'entity',
            url: '/loan-equipmentmySuffix?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ticketManagementApp.loanEquipment.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan-equipment/loan-equipmentsmySuffix.html',
                    controller: 'LoanEquipmentMySuffixController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loanEquipment');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('loan-equipmentmySuffix-detail', {
            parent: 'entity',
            url: '/loan-equipmentmySuffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ticketManagementApp.loanEquipment.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan-equipment/loan-equipmentmySuffix-detail.html',
                    controller: 'LoanEquipmentMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loanEquipment');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'LoanEquipment', function($stateParams, LoanEquipment) {
                    return LoanEquipment.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'loan-equipmentmySuffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('loan-equipmentmySuffix-detail.edit', {
            parent: 'loan-equipmentmySuffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-equipment/loan-equipmentmySuffix-dialog.html',
                    controller: 'LoanEquipmentMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanEquipment', function(LoanEquipment) {
                            return LoanEquipment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-equipmentmySuffix.new', {
            parent: 'loan-equipmentmySuffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-equipment/loan-equipmentmySuffix-dialog.html',
                    controller: 'LoanEquipmentMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                equipmentId: null,
                                equipmentName: null,
                                equipmentDescription: null,
                                initialUploadDate: null,
                                uploadedBy: null,
                                equipmentActive: false,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('loan-equipmentmySuffix', null, { reload: 'loan-equipmentmySuffix' });
                }, function() {
                    $state.go('loan-equipmentmySuffix');
                });
            }]
        })
        .state('loan-equipmentmySuffix.edit', {
            parent: 'loan-equipmentmySuffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-equipment/loan-equipmentmySuffix-dialog.html',
                    controller: 'LoanEquipmentMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['LoanEquipment', function(LoanEquipment) {
                            return LoanEquipment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-equipmentmySuffix', null, { reload: 'loan-equipmentmySuffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan-equipmentmySuffix.delete', {
            parent: 'loan-equipmentmySuffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan-equipment/loan-equipmentmySuffix-delete-dialog.html',
                    controller: 'LoanEquipmentMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['LoanEquipment', function(LoanEquipment) {
                            return LoanEquipment.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan-equipmentmySuffix', null, { reload: 'loan-equipmentmySuffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
