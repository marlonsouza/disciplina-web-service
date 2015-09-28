(function () {
    'use strict';

    angular.module('app')
        .factory('_', _);

    _.$inject = ['$window'];

    function _($window) {
      return $window._;
    }
})();
