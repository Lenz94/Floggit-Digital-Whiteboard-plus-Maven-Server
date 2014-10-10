'use strict';

/**
 * @ngdoc function
 * @name floggitDigitalWhiteboardApp.controller:TodoCtrl
 * @description
 * # TodoCtrl
 * Controller of the floggitDigitalWhiteboardApp
 */
angular.module('floggitDigitalWhiteboardApp')
  .controller('TodoCtrl', function ($scope, localStorageService, $interval, $http) {

    var ws = new WebSocket('ws://localhost:8080/whiteboard/shout');

    ws.onopen = function () {
      // Web Socket is connected, send data using send()
      //writeToScreen('CONNECTED');

      window.alert('Connected to server');

    };

    ws.onerror = function (error) {
      window.alert('WebSocket Error ' + error);
    };

    ws.onmessage = function (evt) {
      window.alert(JSON.parse(evt.data));
      console.log(JSON.parse(evt.data));
      return evt.data;
      //var message = document.getElementById('todos').value;
      //var colors = $scope.colors;
      //ws.send(JSON.stringify(colors));
    };

    ws.onclose = function () {
      // websocket is closed.
      window.alert('Connection is closed...');
    };

    //$scope.showtextarea = true;

    //$scope.colors = [
      //{name:'Red', shade:'Dark'},
      //{name:'Blue', shade:'Dark'},
      //{name:'Yellow', shade:'Light'},
      //{name:'Pink', shade:'Light'},
      //{name:'Purple', shade:'Light'}
    //];
    var todosInStore = localStorageService.get('todos');

  	$scope.todos = todosInStore && todosInStore.split('\n') || [];

  	$scope.$watch('todos', function () {
    	localStorageService.add('todos', $scope.todos.join('\n'));
  	}, true);

      $scope.addTodo = function () {
        $scope.todos.push($scope.todo);
        $scope.todo = '';
      };
      $scope.removeTodo = function (index) {
        $scope.todos.splice(index, 1);
      };

      $scope.add = function (todoText, textfiled, myColor) {
        var item = {
          name: todoText,
          textarea: textfiled,
          color: myColor

        };

        var sendobject = JSON.stringify(item);
        ws.send(sendobject);

        $http.post('http://localhost:14782/leomar/', item)
          .success(function () {
            $scope.getAll();
          });

      };

      $scope.delete = function (id) {
        $http.delete('http://localhost:14782/leomar/' + id)
          .success(function () {
            $scope.getAll();
          });

      };

      $scope.getAll = function () {
        $http.get('http://localhost:14782/leomar/')
          .success(function (response) {
            $scope.names = response;
          });
      };

      $scope.put = function (id, todoText, textfiled, myColor) {
        var item = {
          name: todoText,
          textarea: textfiled,
          color: myColor
        };

        var sendobject = JSON.stringify(item);
        ws.send(sendobject);

        $http.put('http://localhost:14782/leomar/' + id, item)
          .success(function () {
            $scope.getAll();
          });

      };

     // $interval($scope.getAll, 20000);
      $scope.getAll();

    });