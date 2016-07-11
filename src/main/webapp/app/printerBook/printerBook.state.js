/**
 * Created by ernesto.
 * Date: 7/9/16.
 * Time: 5:26 PM.
 * Project: gutenberg.
 * Package:
 */
(function() {
    'use strict';

    angular
        .module('gutenbergApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider.state('printerBook', {
            parent: 'app',
            url: '/printerbook',
            data: {
                authorities: [],
                pageTitle: 'printerBook.printerBook.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/printerBook/printerBook.html',
                    controller: 'PrinterBookController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                mainTranslatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate,$translatePartialLoader) {
                    $translatePartialLoader.addPart('printerBook');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'PrinterBookService', function ($stateParams, PrinterBookService) {
                    return PrinterBookService.get();
                }]
            }
        });
    }
})();
