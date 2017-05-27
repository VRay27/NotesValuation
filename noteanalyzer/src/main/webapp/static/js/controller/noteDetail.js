var app = angular.module('NoteApp');

app.controller('NoteDetailCtrl', NoteDetailCtrl);
app.controller('RowEditCtrl', RowEditCtrl);
app.service('RowEditor', RowEditor);

NoteDetailCtrl.$inject = ['$scope', '$http','$auth', '$rootScope', '$uibModal', 'RowEditor', 'uiGridConstants', 'noteDetailModel','noteUploadAPI','NoteService'];
function NoteDetailCtrl($scope, $http,$auth, $rootScope, $uibModal, RowEditor, uiGridConstants, noteDetailModel,noteUploadAPI,NoteService) {
  var vm = this;
  $scope.noteDetailModel = noteDetailModel;
  vm.editRow = RowEditor.editRow;
  vm.noteAnalyzer = function() {
		NoteService.noteAnalyze(vm.inputZipCode);
	};
	  
  $scope.uploadFile = function() {
	  NoteService.uploadNoteFile($scope.myFile, noteUploadAPI);
  };

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
    enableFiltering: false,
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

  $http.get('api/fetchAllNotes').then(function(response) {
	  $scope.vm.serviceGrid.data = response.data;
  }, function(response) {
	  $scope.vm.serviceGrid.data = [];
	  $auth.checkLoginFromServer(response.status);
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
      controller: ['$http','$scope', '$uibModalInstance', 'grid','noteDetailModel', 'row','NoteService','toastr', RowEditCtrl],
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

function RowEditCtrl($http,$scope, $uibModalInstance, grid,noteDetailModel, row,NoteService,toastr) {
	var editCtrl = this;
	NoteService.getNoteDetail(row.entity.noteId).then(function(response){
		$scope.noteDetailModel = response.data;
	},function(response){
		toastr.error("Unable to fetch the note detail. Please try after sometime");
		$uibModalInstance.close(row.entity);
	});
	
	editCtrl.removeNote= function(){
		console.log('delete Notes..');
		NoteService.deleteNote(row.entity.noteId).then(function(response){
			toastr.success("Note is deleted successfully");
			$uibModalInstance.close(row.entity);
		},function(response){
			toastr.error("Unable to delete the note. Please try after sometime");
		});
	}
	editCtrl.saveNote= function(data){
		console.log('save Notes.. '+data+'  note'+noteDetailModel.rate);
		NoteService.editNote($scope.noteDetailModel).then(function(response){
			$scope.noteDetailModel = noteDetailModel;
			toastr.success("Note is updated successfully");
			$uibModalInstance.close(row.entity);
		},function(response){
			toastr.error("Unable to save the note. Please try after sometime");
		});
	}

	$scope.checkName = function(data){
		console.log('name  check Nname.'+data);
		
	}
	
	
/*	
	 vm.entity = angular.copy(row.entity);
  vm.save = save;
  function save() {
    if (row.entity.id == '0') {
      
       * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $uibModalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
       
      row.entity = angular.extend(row.entity, vm.entity);
      //real ID come back from response after the save in DB
      row.entity.id = Math.floor(100 + Math.random() * 1000);

      grid.data.push(row.entity);

    } else {
      row.entity = angular.extend(row.entity, vm.entity);
    
     * $http.post('http://localhost:8080/service/save', row.entity).success(function(response) { $uibModalInstance.close(row.entity); }).error(function(response) { alert('Cannot edit row (error in console)'); console.dir(response); });
     
    }
    $uibModalInstance.close(row.entity);
  }

 // vm.remove = remove;
  function remove() {
    console.dir(row)
    if (row.entity.id != '0') {
      row.entity = angular.extend(row.entity, vm.entity);
      var index = grid.appScope.vm.serviceGrid.data.indexOf(row.entity);
      grid.appScope.vm.serviceGrid.data.splice(index, 1);
    
     * $http.delete('http://localhost:8080/service/delete/'+row.entity.id).success(function(response) { $uibModalInstance.close(row.entity); }).error(function(response) { alert('Cannot delete row (error in console)'); console.dir(response); });
     
    }
    $uibModalInstance.close(row.entity);
  }*/

}