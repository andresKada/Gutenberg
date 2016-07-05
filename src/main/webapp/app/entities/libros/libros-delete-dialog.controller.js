(function() {
    'use strict';

    angular
        .module('gutenbergApp')
        .controller('LibrosDeleteController',LibrosDeleteController);

    LibrosDeleteController.$inject = ['$uibModalInstance', 'entity', 'Libros'];

    function LibrosDeleteController($uibModalInstance, entity, Libros) {
        var vm = this;
        vm.libros = entity;
        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
        vm.confirmDelete = function (id) {
            Libros.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        };
    }
})();
