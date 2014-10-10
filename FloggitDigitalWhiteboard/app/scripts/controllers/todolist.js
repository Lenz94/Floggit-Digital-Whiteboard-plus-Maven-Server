'use strict';

/**
 * @ngdoc function
 * @name floggitDigitalWhiteboardApp.controller:TodoCtrl
 * @description
 * # TodoCtrl
 * Controller of the floggitDigitalWhiteboardApp
 */
angular.module('floggitDigitalWhiteboardApp')
  .controller('TodoCtrl', function ($scope, localStorageService, $interval, $http, websocket) {

    $scope.$on('ws-message', function (event, todoarray) {
      $scope.todos = todoarray;
      $scope.$apply();
    });

    $scope.add = function () {
      websocket.addToServer();

    };

    $scope.update = function (todo) {
      websocket.updateToServer(todo);
      //$scope.getAll();
    };

    $scope.delete = function (todo) {
      websocket.deleteFromServer(todo);
      //$scope.getAll();
    };

   

  });
 
