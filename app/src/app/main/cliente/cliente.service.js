(function () {
    'use strict';

    angular.module('app')
        .factory('ClienteService', ClienteService);

    ClienteService.$inject = ['Restangular'];

    function ClienteService(Restangular) {

      var self = {};

      self.getDefault = getDefault;
      self.save = save;
      self.remove = remove;
      self.saveMessage = saveMessage;

      function getDefault(){
        return Restangular.one('cliente').options();
      }

      function save(cliente){
        return Restangular.all('cliente').post(cliente);
      }

      function remove(idCliente){
        return Restangular.one('cliente', idCliente).customDELETE();
      }

      function saveMessage(message){
        return Restangular.all('mensagem').post(message);
      }

      return self;
    }
})();
