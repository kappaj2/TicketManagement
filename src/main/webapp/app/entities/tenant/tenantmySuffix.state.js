(function() {
    'use strict';

    angular
        .module('ticketManagementApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('tenantmySuffix', {
            parent: 'entity',
            url: '/tenantmySuffix',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ticketManagementApp.tenant.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tenant/tenantsmySuffix.html',
                    controller: 'TenantMySuffixController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tenant');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('tenantmySuffix-detail', {
            parent: 'entity',
            url: '/tenantmySuffix/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'ticketManagementApp.tenant.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/tenant/tenantmySuffix-detail.html',
                    controller: 'TenantMySuffixDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('tenant');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Tenant', function($stateParams, Tenant) {
                    return Tenant.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'tenantmySuffix',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('tenantmySuffix-detail.edit', {
            parent: 'tenantmySuffix-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tenant/tenantmySuffix-dialog.html',
                    controller: 'TenantMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tenant', function(Tenant) {
                            return Tenant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tenantmySuffix.new', {
            parent: 'tenantmySuffix',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tenant/tenantmySuffix-dialog.html',
                    controller: 'TenantMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                tenantId: null,
                                tenantName: null,
                                creationDate: null,
                                dateCeased: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('tenantmySuffix', null, { reload: 'tenantmySuffix' });
                }, function() {
                    $state.go('tenantmySuffix');
                });
            }]
        })
        .state('tenantmySuffix.edit', {
            parent: 'tenantmySuffix',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tenant/tenantmySuffix-dialog.html',
                    controller: 'TenantMySuffixDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Tenant', function(Tenant) {
                            return Tenant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tenantmySuffix', null, { reload: 'tenantmySuffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('tenantmySuffix.delete', {
            parent: 'tenantmySuffix',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/tenant/tenantmySuffix-delete-dialog.html',
                    controller: 'TenantMySuffixDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Tenant', function(Tenant) {
                            return Tenant.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('tenantmySuffix', null, { reload: 'tenantmySuffix' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
