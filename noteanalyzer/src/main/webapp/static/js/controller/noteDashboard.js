var app = angular.module('NoteApp');

app.controller('NoteDashboardCtrl', NoteDashboardCtrl);
app.service('NoteDetailService', NoteDetailService);

NoteDashboardCtrl.$inject = ['$scope', '$http','$auth', '$rootScope', '$uibModal', 'NoteDetailService', 'uiGridConstants', 'noteDetailModel','noteUploadAPI','NoteService','WaitingDialog'];
function NoteDashboardCtrl($scope, $http,$auth, $rootScope, $uibModal, NoteDetailService, uiGridConstants, noteDetailModel,noteUploadAPI,NoteService,WaitingDialog) {
  var vm = this;
  $scope.noteDetailModel = noteDetailModel;
  vm.getNoteDetail = NoteDetailService.getNoteDetail;
  vm.noteAnalyzer = function() {
		NoteService.noteAnalyze(vm.inputZipCode);
	};
	  
  $scope.uploadFile = function() {
	  NoteService.uploadNoteFile($scope.myFile, noteUploadAPI);
  };

  $scope.noteSearch = function(){
	  angular.forEach( $scope.selectedCityList, function( value, key ) {    
		   	console.log('selected'+$scope.selectedCityList);
		   	console.log('value'+value);
		   	console.log('key'+key);
		});
	  
	  angular.element(document.querySelector('.collapse')).collapse("hide");
  }
  

  
  vm.serviceGrid = {
    enableRowSelection: true,
    enableRowHeaderSelection: false,
    multiSelect: false,
    enableSorting: false,
    enableFiltering: true,
    enableGridMenu: false,
    enableCellEdit: false,
    enablePaginationControls:true,
    paginationPageSizes: [10,25, 50, 75],
    paginationPageSize: 10,
    rowHeight:40,
          
    rowTemplate : "<div ng-dblclick=\"grid.appScope.vm.getNoteDetail(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
  };

  vm.serviceGrid.columnDefs = [
	  {
		    field: 'noteAddress',
		    displayName: 'Address',
		    enableSorting: true,
		    enableFiltering: true,
		    headerCellClass:'addressHeaderClass',
		    cellClass:'uiGridCellClass',
		    width:260,
		    cellTemplate: "<a href =\"#\"><div ng-click=\"grid.appScope.vm.getNoteDetail(grid, row)\">{{row.entity.noteAddress}}</div></a>"
		  },
{    field: 'yield',
    displayName: 'Yield',
    enableSorting: true,
    enableCellEdit: false,
    enableFiltering: false,
    cellClass:'uiGridCellClass',
    cellTemplate: "<div>{{row.entity.yield}}</div>"
  }, {
    field: 'itv',
    displayName: 'ITV',
    enableSorting: true,
    enableCellEdit: false,
    enableFiltering: false,
    cellClass:'uiGridCellClass',
    cellTemplate: "<div>{{row.entity.itv}}</div>"
  }, {
    field: 'ltv',
    displayName: 'LTV',
    enableSorting: true,
    enableCellEdit: false,
    enableFiltering: false,
    cellClass:'uiGridCellClass',
    cellTemplate: "<div>{{row.entity.ltv}}</div>"
  },{
	    field: 'crime',
	    displayName: 'Crime',
	    enableSorting: true,
	    enableCellEdit: false,
	    enableFiltering: false,
	    cellClass:'uiGridCellClass',
	    cellTemplate: "<div>{{row.entity.crime}}</div>"
	  },{
    field: 'marketPrice',
    displayName: 'Market Price',
    enableSorting: true,
    enableCellEdit: false,
    enableFiltering: false,
    cellClass:'uiGridCellClass',
    sort: {
      direction: uiGridConstants.ASC,
      priority: 1,
    },
    cellTemplate: "<div>{{row.entity.marketPrice}}</div>"
  }];

  $scope.init = function(){
    WaitingDialog.show();
	  $http.get('api/fetchAllNotes').then(function(response) {
		  $scope.vm.serviceGrid.data = response.data;
	  }, function(response) {
		  $scope.vm.serviceGrid.data = [];
		  $auth.checkLoginFromServer(response.status);
	  }).then(function(){
		  WaitingDialog.hide();
	  });
	  
  }


}

NoteDetailService.$inject = ['$http', '$rootScope','NoteService','toastr','$state'];
function NoteDetailService($http, $rootScope,NoteService,toastr,$state,$scope) {
  var service = {};
  service.getNoteDetail = getNoteDetail;

 function getNoteDetail(grid, row) {
	  NoteService.getNoteDetail(row.entity.noteId).then(function(response) {
		  NoteService.setNoteDetailModel(response);
		  $state.go('noteDetail');
	  },function(response) {
		  toastr.error("We are unable to find details for this note. Please try after sometime.")

	  });
 };
	  
  return service;
}
	

