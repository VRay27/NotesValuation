var app = angular.module('NoteApp')

app.controller('NoteDetailCtrl', NoteDetailCtrl);
app.controller('RowEditCtrl', RowEditCtrl);
app.service('RowEditor', RowEditor);

NoteDetailCtrl.$inject = [ '$scope', '$http', '$modal', 'RowEditor', 'uiGridConstants','noteDetailModel' ];
function NoteDetailCtrl($scope, $http, $modal, RowEditor, uiGridConstants,noteDetailModel) {
    
	var vm = this;
	$scope.noteDetailModel = noteDetailModel;
	vm.editRow = RowEditor.editRow;
  $scope.inputAddress = '';
	vm.serviceGrid = {
		enableRowSelection : true,
		enableRowHeaderSelection : false,
		multiSelect : false,
		enableSorting : true,
		enableFiltering : true,
		enableGridMenu : false,
		rowHeight:100,
/*        enablePaginationControls:true,
        paginationPageSizes: [10,25, 50, 75],
        paginationPageSize: 10,
        
*/	/*rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"*/
	};

	vm.serviceGrid.columnDefs = [ {
		field : 'assetImageURL',
		displayName : 'Asset Image',
		enableSorting : false,
		enableCellEdit : false,
		cellTemplate:"<div ng-click='grid.appScope.vm.editRow(grid, row)'><img width=\"100px\" ng-src=\"http://cdn.flaticon.com/png/256/70689.png\" lazy-src/></div>"
	}, {
		field : 'yield',
        displayName : 'Yield',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'itv',
        displayName : 'ITV',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'ltv',
        displayName : 'LTV',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'marketValue',
        displayName : 'Market Value',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'crime',
        displayName : 'Crime',
		enableSorting : true,
		enableCellEdit : false
	}, {
		field : 'overAllScore',
        displayName : 'OverAll Score',
		enableSorting : true,
		enableCellEdit : false,
        sort : {
			direction : uiGridConstants.ASC,
			priority : 1,
		},
	}];

	$http.get('static/data.json').success(function(response) {
		vm.serviceGrid.data = response;
	}).error(function(response){
	  vm.serviceGrid.data = [];
	});

	$scope.addRow = function() {
		var newService = {
			"id" : "0",
			"category" : "public",
			"exposednamespace" : "http://bced.wallonie.be/services/",
			"exposedoperation" : "-",
			"exposedws" : "-",
			"path" : "//*[local-name()='-']/text()",
			"provider" : "BCED",
			"version" : "1.0"
		};
		var rowTmp = {};
		rowTmp.entity = newService;
		vm.editRow($scope.vm.serviceGrid, rowTmp);
	};

}

RowEditor.$inject = [ '$http', '$rootScope', '$modal' ];
function RowEditor($http, $rootScope, $modal) {
	var service = {};
	service.editRow = editRow;

	function editRow(grid, row) {
		$modal.open({
			templateUrl : 'static/template/note-detail.html',
			controller : [ '$http', '$modalInstance', 'grid', 'row', RowEditCtrl ],
			controllerAs : 'vm',
			resolve : {
				grid : function() {
					return grid;
				},
				row : function() {
					return row;
				}
			}
		});
	}

	return service;
}

function RowEditCtrl($http, $modalInstance, grid, row) {
	var vm = this;
	vm.entity = angular.copy(row.entity);
	vm.save = save;
	function save() {
		if (row.entity.id == '0') {
			/*
			 * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
			 */
			row.entity = angular.extend(row.entity, vm.entity);
			//real ID come back from response after the save in DB
			row.entity.id = Math.floor(100 + Math.random() * 1000);
			
			grid.data.push(row.entity);

		} else {
			row.entity = angular.extend(row.entity, vm.entity);
			/*
			 * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
			 */
		}
		$modalInstance.close(row.entity);
	}

	vm.remove = remove;
	function remove() {
		console.dir(row)
		if (row.entity.id != '0') {
			row.entity = angular.extend(row.entity, vm.entity);
			var index = grid.appScope.vm.serviceGrid.data.indexOf(row.entity);
			grid.appScope.vm.serviceGrid.data.splice(index, 1);
			/*
			 * $http.delete('http://localhost:8080/service/delete/'+row.entity.id).success(function(response) { $modalInstance.close(row.entity); }).error(function(response) { alert('Cannot delete row (error in console)'); console.dir(response); });
			 */
		}
		$modalInstance.close(row.entity);
	}

}
