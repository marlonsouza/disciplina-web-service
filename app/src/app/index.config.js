(function () {
    'use strict';

    angular
        .module('app')
        .config(config)
        .run(configRestangular);

    /** @ngInject */
    function config($logProvider, toastrConfig) {
        // Enable log
        $logProvider.debugEnabled(true);

        // Set options third-party lib
        toastrConfig.allowHtml = true;
        toastrConfig.timeOut = 3000;
        toastrConfig.positionClass = 'toast-top-right';
        toastrConfig.preventDuplicates = true;
        toastrConfig.progressBar = true;

    }

    configRestangular.$inject = ['Restangular'];

    function configRestangular(Restangular){

        Restangular.setBaseUrl('http://localhost:8080/webservicesenai/');

        Restangular.addResponseInterceptor(function(data, operation){
            var extractedData;
            if(operation == 'getList') {
                extractedData = data.items;
            } else {
                extractedData = data;
            }

            return extractedData;
        });

        Restangular.setDefaultHeaders({
            'Content-Type':'application/json',
            'Accept': 'application/json'
        });

    }




})();
