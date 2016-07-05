(function() {
    'use strict';

    angular
        .module('gutenbergApp')
        .controller('LibrosDetailController', LibrosDetailController);

    LibrosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'entity', 'Libros'];

    function LibrosDetailController($scope, $rootScope, $stateParams, entity, Libros) {
        var vm = this;
        vm.libros = entity;
        
        var unsubscribe = $rootScope.$on('gutenbergApp:librosUpdate', function(event, result) {
            vm.libros = result;
        });
        $scope.$on('$destroy', unsubscribe);

    }
})();
