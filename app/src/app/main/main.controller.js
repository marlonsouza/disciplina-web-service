(function () {
  'use strict';

  angular
    .module('app')
    .controller('MainController', MainController);

  MainController.$inject = ['$q', 'promiseTracker', 'ClienteService', '_', '$log'];

  function MainController($q, promiseTracker, ClienteService, _, $log) {
    var vm = this, defaultClienteCache;

    var SIMULATE = {
      NOME: 'cliente',
      PALAVRAS: ['insert', 'update', 'select', 'delete']
    };

    vm.tracker = {};
    vm.tracker.loading = promiseTracker();
    vm.tracker.saving = promiseTracker();
    vm.tracker.removing = promiseTracker();
    vm.tracker.savingMessage = promiseTracker();

    vm.clientes = [];
    vm.mensagens = [];
    vm.mensagem = {};

    vm.saveCliente = saveCliente;
    vm.removeCliente = removeCliente;
    vm.simular = simular;
    vm.addMessage = addMessage;

    vm.enabledSimular = false;

    init();

    function init() {
      vm.tracker.loading.addPromise(
        $q.all([
          ClienteService.getDefault().then(function (data) {
            defaultClienteCache = data;
            vm.cliente = angular.copy(data);
          })
        ])
      );
    }

    function saveCliente(cliente) {
      vm.tracker.saving.addPromise(
        ClienteService.save(cliente).then(function (data) {
          cliente = angular.copy(defaultClienteCache);
          vm.formCliente.$setPristine();

          vm.clientes.push(data.cliente);
          vm.mensagens.push(data);
        })
      );
    }

    function removeCliente(id) {
      vm.tracker.removing.addPromise(
        ClienteService.remove(id).then(function (data) {

          vm.clientes = vm.clientes.filter(function (cliente) {
            if (cliente.id !== id) {
              return cliente;
            }
          });

          vm.mensagens.push(data);
        })
      );
    }

    function simular(numeroClientes) {
      var listClients = _.range(numeroClientes),
        listPromiseCliente = [],
        listPromiseActions = [],
        listPromiseRemove = [];

      vm.clientes.length = 0;
      vm.mensagens.length = 0;

      listClients = listClients.map(function (item) {
        var cliente = {};

        cliente.id = item;
        cliente.nome = SIMULATE.NOME + ' (' + item + ')';

        return cliente;
      });

      listClients.forEach(function (cliente) {
        listPromiseCliente.push(
          ClienteService.save(cliente).then(function (data) {
            vm.clientes.push(data.cliente);
            vm.mensagens.push(data);
          })
        );
      });

      $q.all(listPromiseCliente)
        .then(function () {
          listClients.forEach(function (cliente) {
            SIMULATE.PALAVRAS.forEach(function (palavra) {
              var msg = {};

              msg.mensagem = palavra;
              msg.cliente = cliente;

              listPromiseActions.push(
                ClienteService.saveMessage(msg).then(function (data) {
                  vm.mensagens.push(data);
                })
              );
            });

            $q.all(listPromiseActions)
              .then(function(){

                listPromiseRemove.push(
                  ClienteService.remove(cliente.id).then(function (data) {
                    vm.clientes = vm.clientes.filter(function (cliente) {
                      if (cliente.id !== cliente.id) {
                        return cliente;
                      }
                    });

                    vm.mensagens.push(data);
                  })
                );

                $q.all(listPromiseRemove)
                  .then(function(){
                    $log.log('Simulação finalizada!');
                  });

              });
          });
        });
    }

    function addMessage(message) {

      message.cliente = _.find(vm.clientes, {id: Number(message.cliente)});

      vm.tracker.savingMessage.addPromise(
        ClienteService.saveMessage(message).then(function (data) {
          vm.mensagens.push(data);

          angular.forEach(message, function (value, key) {
            message[key] = null;
          });

          vm.formMessage.$setPristine();
        })
      );
    }
  }
})();
