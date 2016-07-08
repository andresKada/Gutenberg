(function() {
    'use strict';

    angular
        .module('gutenbergApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('libros', {
            parent: 'entity',
            url: '/libros?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'gutenbergApp.libros.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/libros/libros.html',
                    controller: 'LibrosController',
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
                    $translatePartialLoader.addPart('libros');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('libros-detail', {
            parent: 'entity',
            url: '/libros/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'gutenbergApp.libros.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/libros/libros-detail.html',
                    controller: 'LibrosDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('libros');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Libros', function($stateParams, Libros) {
                    return Libros.get({id : $stateParams.id});
                }]
            }
        })
        .state('libros.new', {
            parent: 'libros',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/libros/libros-dialog.html',
                    controller: 'LibrosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre_libro: null,
                                nombre_autor: null,
                                editorial: null,
                                fracmento_libro: null,
                                stan_ubicacion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('libros', null, { reload: true });
                }, function() {
                    $state.go('libros');
                });
            }]
        })
        .state('libros.edit', {
            parent: 'libros',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/libros/libros-dialog.html',
                    controller: 'LibrosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Libros', function(Libros) {
                            return Libros.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('libros', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('libros.delete', {
            parent: 'libros',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/libros/libros-delete-dialog.html',
                    controller: 'LibrosDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Libros', function(Libros) {
                            return Libros.get({id : $stateParams.id});
                        }]
                    }
                }).result.then(function() {
                    $state.go('libros', null, { reload: true });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('libros-vista', {
          parent: 'entity',
          url: '/libros-vista',
          data: {
              authorities: ['ROLE_USER'],
              pageTitle: 'gutenbergApp.libros.home.title'
          },
          views: {
              'content@': {
                  templateUrl: 'app/entities/libros/libros-vista.html',
                  controller: 'LibrosController',
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
                  $translatePartialLoader.addPart('libros');
                  $translatePartialLoader.addPart('global');
                  return $translate.refresh();
              }]
          }
        })
        .state('libros-vistaI', {
          parent: 'entity',
          url: '/libros-vistaI',
          data: {
              authorities: ['ROLE_USER'],
              pageTitle: 'gutenbergApp.libros.home.title'
          },
          views: {
              'content@': {
                  templateUrl: 'app/entities/libros/libros-vistaI.html',
                  controller: 'LibrosController',
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
                  $translatePartialLoader.addPart('libros');
                  $translatePartialLoader.addPart('global');
                  return $translate.refresh();
              }]
          }
        })
        .state('libros-vistaII', {
          parent: 'entity',
          url: '/libros-vistaII',
          data: {
              authorities: ['ROLE_USER'],
              pageTitle: 'gutenbergApp.libros.home.title'
          },
          views: {
              'content@': {
                  templateUrl: 'app/entities/libros/libros-vistaII.html',
                  controller: 'LibrosController',
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
                  $translatePartialLoader.addPart('libros');
                  $translatePartialLoader.addPart('global');
                  return $translate.refresh();
              }]
          }
        });
    }

})();
