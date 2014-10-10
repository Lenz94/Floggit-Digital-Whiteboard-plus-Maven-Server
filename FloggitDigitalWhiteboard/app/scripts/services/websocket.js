'use strict';

/**
 * @ngdoc service
 * @name FloggitDigitalWhiteboard.websocket
 * @description
 * # websocket
 * Factory in the floggitDigitalWhiteboardApp.
 */


angular.module('floggitDigitalWhiteboardApp')
	.factory('websocket', function ($rootScope) {

		var todosarray = [];

		var ws = new WebSocket('ws://localhost:8080/whiteboard/shout');
		
		ws.onopen = function () {
			// Web Socket is connected, send data using send()
			//window.alert('Socket has been opened!');
			console.log('Welcome to our WebSocket');
		
		};

		ws.onerror = function (error) {
			window.alert('WebSocket Error ' + error);
		};

		ws.onmessage = function (message) {
			console.log('Note from Server');
			todosarray = JSON.parse(message.data);
			console.log(JSON.stringify(todosarray));
			$rootScope.$broadcast('ws-message', todosarray);
  		};
		
		ws.onclose = function () {
			window.alert('Connection is closed...');
		};


		return {
			addToServer: function () {
				var todoText = document.getElementById('todoText').value;
				var textfiled = document.getElementById('textfiled').value;
				var myColor = document.getElementById('myColor').value;

				var item = {
					'id': 0,
					'service': 'create',
					'name': todoText,
					'textarea': textfiled,
          			'color': myColor
				};

				ws.send(JSON.stringify(item));
				console.log('--------------');
				console.log('Note sent to Server');
				console.log(item);
			},

			updateToServer: function (todo) {
				var todoText = document.getElementById('formname').value;
				var textfiled = document.getElementById('formtextarea').value;
				var myColor = document.getElementById('formcolor').value;


				var itemupdate = {
					'id': todo.id,
					'service': 'update',
					'name': todoText,
					'textarea': textfiled,
          			'color': myColor
				};


				ws.send(JSON.stringify(itemupdate));
				console.log('--------------');
				console.log('Note updated to Server');
				console.log(JSON.stringify(itemupdate));
			},

			deleteFromServer: function (todo) {

				var itemdelete = {
					'id': todo.id,
					'service': 'delete',
					'name': '',
					'textarea': '',
					'color': ''
				};

				ws.send(JSON.stringify(itemdelete));
				console.log('--------------');
				console.log('JSON sent to backend');
				console.log(itemdelete);

			},

		}; 
	});