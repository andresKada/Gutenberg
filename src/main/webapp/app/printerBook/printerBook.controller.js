/**
 * Created by ernesto.
 * Date: 7/9/16.
 * Time: 3:16 PM.
 * Project: gutenberg.
 * Package:
 */
(function() {
    'use strict';

    angular
        .module('gutenbergApp')
        .controller('PrinterBookController', PrinterBookController);

    PrinterBookController.$inject = ['$state',  'entity', '$timeout'];

    function PrinterBookController($state, entity, $timeout) {
        var vm = this;
        vm.libros = entity;

        vm.printBook = printBook;

        function printBook(area) {
            var ficha = document.getElementById(area);
            var ventimp = window.open(' ', 'popimpr');
            ventimp.document.write('<html><head><title></title><link rel="stylesheet" href="../../content/css/estilos_gu.css"><link rel="stylesheet" href="../../content/css/font-awesome.css"></head><body>');
            ventimp.document.write(ficha.innerHTML);
            ventimp.document.write('</body></html>');
            ventimp.document.close();
            ventimp.print();

            $timeout(function () {
                    ventimp.close();
                }, 1000);
            $state.go('printerBook', {}, {reload: true});
        }
    }

})();
