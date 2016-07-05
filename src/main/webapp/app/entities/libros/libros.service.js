(function() {
    'use strict';
    angular
        .module('gutenbergApp')
        .factory('Libros', Libros);

    Libros.$inject = ['$resource'];

    function Libros ($resource) {
        var resourceUrl =  'api/libros/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
