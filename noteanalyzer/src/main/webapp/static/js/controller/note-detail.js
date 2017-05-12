var app = angular.module('NoteApp');

app.controller('NoteDetailCtrl', NoteDetailCtrl);
app.controller('RowEditCtrl', RowEditCtrl);
app.service('RowEditor', RowEditor);

NoteDetailCtrl.$inject = ['$scope', '$http', '$rootScope', '$uibModal', 'RowEditor', 'uiGridConstants', 'noteDetailModel', 'fileUpload', 'noteUploadAPI'];
function NoteDetailCtrl($scope, $http, $rootScope, $uibModal, RowEditor, uiGridConstants, noteDetailModel, fileUpload, noteUploadAPI) {
  var vm = this;
  $scope.noteDetailModel = noteDetailModel;
  vm.editRow = RowEditor.editRow;
  vm.noteUpload = function(modalName) {
    var modalInstance = $uibModal.open({
      templateUrl: 'static/template/note-form.html',
      controller: 'noteInputFormController' /*,
      resolve: {
          'items': function() { return $scope.items; }
      }*/
    })
  };

  $scope.uploadFile = function() {
    fileUpload.uploadFileToUrl($scope.myFile, noteUploadAPI);
  };

  $scope.inputAddress = '';
  vm.serviceGrid = {
    enableRowSelection: true,
    enableRowHeaderSelection: false,
    multiSelect: false,
    enableSorting: true,
    enableFiltering: true,
    enableGridMenu: false,
    rowHeight: 100,
  /*        enablePaginationControls:true,
          paginationPageSizes: [10,25, 50, 75],
          paginationPageSize: 10,
          
  */ /*rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.editRow(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"*/
  };

  vm.serviceGrid.columnDefs = [{
    field: 'assetImageURL',
    displayName: 'Asset Image',
    enableSorting: false,
    enableCellEdit: false,
    cellTemplate: "<div ng-click='grid.appScope.vm.editRow(grid, row)'><img width=\"100px\" ng-src=\"{{row.entity.assetImgSrc}}\" lazy-src  class=\"img-responsive\"/></div>"
  }, {
    field: 'yield',
    displayName: 'Yield',
    enableSorting: true,
    enableCellEdit: false,
    cellTemplate: "<div>{{row.entity.yield}}</div>"
  }, {
    field: 'itv',
    displayName: 'ITV',
    enableSorting: true,
    enableCellEdit: false,
    cellTemplate: "<div>{{row.entity.itv}}</div>"
  }, {
    field: 'ltv',
    displayName: 'LTV',
    enableSorting: true,
    enableCellEdit: false,
    cellTemplate: "<div>{{row.entity.ltv}}</div>"
  }, {
    field: 'marketValue',
    displayName: 'Market Value',
    enableSorting: true,
    enableCellEdit: false,
    cellTemplate: "<div>{{row.entity.marketValue}}</div>"
  }, {
    field: 'crime',
    displayName: 'Crime',
    enableSorting: true,
    enableCellEdit: false,
    cellTemplate: "<div>{{row.entity.crime}}</div>"
  }, {
    field: 'overAllScore',
    displayName: 'OverAll Score',
    enableSorting: true,
    enableCellEdit: false,
    sort: {
      direction: uiGridConstants.ASC,
      priority: 1,
    },
    cellTemplate: "<div>{{row.entity.overAllScore}}</div>"
  }];

  $http.get('fetchAllNotes').then(function(response) {
	  $scope.vm.serviceGrid.data = response.data;
  }, function(response) {
	  $scope.vm.serviceGrid.data = [];
  });

  $scope.addRow = function() {
    var newService = {
      "assetImageURL": "0",
      "yield": "public",
      "itv": "http://bced.wallonie.be/services/",
      "ltv": "-",
      "marketValue": "-",
      "crime": "//*[local-name()='-']/text()",
      "overAllScore": "BCED"
    };
    var rowTmp = {};
    rowTmp.entity = newService;
    vm.editRow($scope.vm.serviceGrid, rowTmp);
  };

}

RowEditor.$inject = ['$http', '$rootScope', '$uibModal'];
function RowEditor($http, $rootScope, $uibModal) {
  var service = {};
  service.editRow = editRow;

  function editRow(grid, row) {
    $uibModal.open({
      templateUrl: 'static/template/note-detail.html',
      controller: ['$http','$scope', '$uibModalInstance', 'grid','noteDetailModel', 'row', RowEditCtrl],
      controllerAs: 'editCtrl',
      resolve: {
        grid: function() {
          return grid;
        },
        row: function() {
          return row;
        }
      }
    });
  }

  return service;
}

function RowEditCtrl($http,$scope, $uibModalInstance, grid,noteDetailModel, row) {
	var editCtrl = this;
	$scope.noteDetailModel = noteDetailModel;
	editCtrl.removeNote= function(){
		
		console.log('remove Notes..');
	}
	editCtrl.saveNote= function(data){
		console.log('save Notes.. '+data+'  note'+noteDetailModel.rate);
		$uibModalInstance.close(row.entity);
	}
	
	$scope.checkName = function(data){
		console.log('name  check Nname.'+data);
		
	}
	
	/* vm.entity = angular.copy(row.entity);
  vm.save = save;
 */ function save() {
    if (row.entity.id == '0') {
      /*
       * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $uibModalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
       */
      row.entity = angular.extend(row.entity, vm.entity);
      //real ID come back from response after the save in DB
      row.entity.id = Math.floor(100 + Math.random() * 1000);

      grid.data.push(row.entity);

    } else {
      row.entity = angular.extend(row.entity, vm.entity);
    /*
     * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $uibModalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
     */
    }
    $uibModalInstance.close(row.entity);
  }

 // vm.remove = remove;
  function remove() {
    console.dir(row)
   /* if (row.entity.id != '0') {
      row.entity = angular.extend(row.entity, vm.entity);
      var index = grid.appScope.vm.serviceGrid.data.indexOf(row.entity);
      grid.appScope.vm.serviceGrid.data.splice(index, 1);
    
     * $http.delete('http://localhost:8080/service/delete/'+row.entity.id).success(function(response) { $uibModalInstance.close(row.entity); }).error(function(response) { alert('Cannot delete row (error in console)'); console.dir(response); });
     
    }*/
    $uibModalInstance.close(row.entity);
  }

}