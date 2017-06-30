var app = angular.module('NoteApp');

app.controller('NoteDetailCtrl', NoteDetailCtrl);
app.controller('RowEditCtrl', RowEditCtrl);
app.service('RowEditor', RowEditor);

NoteDetailCtrl.$inject = ['$scope', '$http','$auth', '$rootScope', '$uibModal', 'RowEditor', 'uiGridConstants', 'noteDetailModel','noteUploadAPI','NoteService'];
function NoteDetailCtrl($scope, $http,$auth, $rootScope, $uibModal, RowEditor, uiGridConstants, noteDetailModel,noteUploadAPI,NoteService) {
  var vm = this;
  $scope.noteDetailModel = noteDetailModel;
  vm.getNoteDetail = RowEditor.getNoteDetail;
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
          
  */ /*rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.getNoteDetail(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"*/
  };

  vm.serviceGrid.columnDefs = [{
    field: 'assetImageURL',
    displayName: 'Asset Image',
    enableSorting: false,
    enableCellEdit: false,
    enableFiltering: false,
    cellTemplate: "<div ng-click='grid.appScope.vm.getNoteDetail(grid, row)'><img width=\"100px\" ng-src=\"{{row.entity.assetImgSrc}}\" lazy-src  class=\"img-responsive\"/></div>"
  },{
	    field: 'creditScore',
	    displayName: 'Credit Score',
	    enableSorting: true,
	    enableCellEdit: false,
	    cellTemplate: "<div>{{row.entity.yield}}</div>"
	  }, {
		    field: 'paymentHistory',
		    displayName: 'Payment History',
		    enableSorting: true,
		    enableCellEdit: false,
		    cellTemplate: "<div>{{row.entity.yield}}</div>"
		  }, {
			    field: 'employmentHistory',
			    displayName: 'Employment History',
			    enableSorting: true,
			    enableCellEdit: false,
			    cellTemplate: "<div>{{row.entity.yield}}</div>"
			  },{
				    field: 'marketValue',
				    displayName: 'Market Value',
				    enableSorting: true,
				    enableCellEdit: false,
				    cellTemplate: "<div>{{row.entity.yield}}</div>"
				  },{
					    field: 'school',
					    displayName: 'school',
					    enableSorting: true,
					    enableCellEdit: false,
					    cellTemplate: "<div>{{row.entity.yield}}</div>"
					  },{
						    field: 'rent',
						    displayName: 'Employment History',
						    enableSorting: true,
						    enableCellEdit: false,
						    cellTemplate: "<div>{{row.entity.yield}}</div>"
						  },{
							    field: 'crime',
							    displayName: 'crime',
							    enableSorting: true,
							    enableCellEdit: false,
							    cellTemplate: "<div>{{row.entity.yield}}</div>"
							  },
	  {
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
	    field: 'jurisdical',
	    displayName: 'Jurisdical',
	    enableSorting: true,
	    enableCellEdit: false,
	    cellTemplate: "<div>{{row.entity.crime}}</div>"
	  },  {
    field: 'riskFactor',
    displayName: 'Risk Factor',
    enableSorting: true,
    enableCellEdit: false,
    cellTemplate: "<div>{{row.entity.marketValue}}</div>"
  },{
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

}

RowEditor.$inject = ['$http', '$rootScope', '$uibModal','NoteService','toastr'];
function RowEditor($http, $rootScope, $uibModal,NoteService,toastr) {
  var service = {};
  service.getNoteDetail = getNoteDetail;

 function getNoteDetail(grid, row) {
	  NoteService.getNoteDetail(row.entity.noteId).then(function(response) {
		  $uibModal.open({
		      templateUrl: 'static/template/note-detail.html',
		      controller: ['$http','$scope', '$uibModalInstance', 'grid','noteDetailModel', 'row','NoteService','toastr', RowEditCtrl],
		      controllerAs: 'editCtrl',
		      resolve: {
		        grid: function() {
		          return grid;
		        },
		        noteDetail: function() {
		          return response.data;
		        }
		      }
		    });
	  },function(response) {
		  toastr.error("We are unable to find details for this note. Please try after sometime.")

	  });
 };
	  
	 /* function editRow(grid, row) {
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
  }*/
	  

  return service;
}

function RowEditCtrl($http,$scope, $uibModalInstance, grid, noteDetail,NoteService,toastr) {
	var editCtrl = this;
	
	$scope.noteDetailModel = noteDetail;
	
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
		console.log('save Notes.. '+data+'  note'+$scope.noteDetailModel.rate);
		NoteService.editNote($scope.noteDetailModel).then(function(response){
			$scope.noteDetailModel = response.data;
			toastr.success("Note is updated successfully");
			$uibModalInstance.close(row.entity);
		},function(response){
			toastr.error("Unable to save the note. Please try after sometime");
		});
	}

	$scope.checkName = function(data){
		console.log('name  check Nname.'+data);
		
	}
	

}