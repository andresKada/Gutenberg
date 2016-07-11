/**
 * Created by ernesto.
 * Date: 7/9/16.
 * Time: 5:21 PM.
 * Project: gutenberg.
 * Package:
 */
(function () {
    'use strict';
    angular
        .module('gutenbergApp')
        .factory('PrinterBookService', PrinterBookService);

    PrinterBookService.$inject = ['$resource'];

    function PrinterBookService($resource) {
        var resourceUrl = 'api/printerbook/';

        return $resource(resourceUrl, {}, {
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            }
        });
    }
})();
