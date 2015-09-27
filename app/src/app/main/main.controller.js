(function() {
  'use strict';

  angular
    .module('app')
    .controller('MainController', MainController);

  MainController.$inject = ['$q', 'promiseTracker', 'ClienteService', '$log'];

  function MainController($q, promiseTracker, ClienteService, $log) {
    var vm = this, defaultClienteCache;

    vm.tracker = {};
    vm.tracker.loading = promiseTracker();
    vm.tracker.saving = promiseTracker();
    vm.tracker.removing = promiseTracker();

    vm.clientes = [];

    vm.saveCliente = saveCliente;
    vm.removeCliente = removeCliente;

    init();

    function init(){
      vm.tracker.loading.addPromise(
        $q.all([
          ClienteService.getDefault().then(function(data){
            defaultClienteCache = data;
            vm.cliente = angular.copy(data);
          })
        ])
      );
    }

    function saveCliente(){
      vm.tracker.saving.addPromise(
        ClienteService.save(vm.cliente).then(function(data){
          vm.cliente = angular.copy(defaultClienteCache);
          vm.formCliente.$setPristine();

          vm.clientes.push(data);
        })
      );
    }

    function removeCliente(id){
      vm.tracker.removing.addPromise(
        ClienteService.remove(id).then(function(){
          $log.log('Cliente '+id+' removido com sucesso');
        })
      );
    }

  }
})();
