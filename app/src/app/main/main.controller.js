(function() {
  'use strict';

  angular
    .module('app')
    .controller('MainController', MainController);

  MainController.$inject = ['$q', 'promiseTracker', 'ClienteService'];

  function MainController($q, promiseTracker, ClienteService) {
    var vm = this;

    vm.tracker = {};
    vm.tracker.loading = promiseTracker();

    init();

    function init(){
      vm.tracker.loading.addPromise(
        $q.all([
          ClienteService.getDefault().then(function(data){
            vm.defaultClienteCache = data;
          })
        ])
      );
    }
  }
})();
