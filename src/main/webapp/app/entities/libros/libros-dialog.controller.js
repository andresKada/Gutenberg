(function() {
    'use strict';

    angular
        .module('gutenbergApp')
        .controller('LibrosDialogController', LibrosDialogController);

    LibrosDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Libros'];

    function LibrosDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Libros) {
        var vm = this;
        vm.libros = entity;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        var onSaveSuccess = function (result) {
            $scope.$emit('gutenbergApp:librosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        };

        var onSaveError = function () {
            vm.isSaving = false;
        };

        vm.save = function () {
            vm.isSaving = true;
            if (vm.libros.id !== null) {
                Libros.update(vm.libros, onSaveSuccess, onSaveError);
            } else {
                Libros.save(vm.libros, onSaveSuccess, onSaveError);
            }
        };

        vm.clear = function() {
            $uibModalInstance.dismiss('cancel');
        };
    }
})();
