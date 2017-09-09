var app = angular.module('NoteApp');

app.controller('NoteDashboardCtrl', NoteDashboardCtrl);
app.service('NoteDetailService', NoteDetailService);

NoteDashboardCtrl.$inject = ['$scope', '$http', '$auth', '$rootScope', '$uibModal', 'NoteDetailService', 'uiGridConstants', 'noteDetailModel', 'noteUploadAPI', 'NoteService', 'WaitingDialog'];

function NoteDashboardCtrl($scope, $http, $auth, $rootScope, $uibModal, NoteDetailService, uiGridConstants, noteDetailModel, noteUploadAPI, NoteService, WaitingDialog) {
    var vm = this;
    $scope.noteDetailModel = noteDetailModel;
    vm.getNoteDetail = NoteDetailService.getNoteDetail;
    vm.updateMarketValue = NoteDetailService.updateMarketValue;
    vm.noteAnalyzer = function() {
        NoteService.noteAnalyze(vm.inputZipCode);
    };

    $scope.uploadFile = function() {
        NoteService.uploadNoteFile($scope.myFile, noteUploadAPI);
    };

    $scope.noteSearch = function() {
        angular.forEach($scope.selectedCityList, function(value, key) {
            //console.log('selected' + $scope.selectedCityList);
            //console.log('value' + value);
            //console.log('key' + key);
        });

        angular.element(document.querySelector('.collapse')).collapse("hide");
    }


    $scope.customNumberFilter = function(searchTerm, cellValue) {
        var searchTermValue = searchTerm.replace("\\+", '');
        searchTermValue = searchTermValue.replace("\\-", '');
        if (searchTerm.includes("+")) {
            return cellValue > searchTermValue;
        } else if (searchTerm.includes("-")) {
            return cellValue < searchTermValue;
        } else {
            return cellValue == searchTermValue;
        }

    }
    vm.serviceGrid = {
        enableRowSelection: true,
        enableRowHeaderSelection: false,
        multiSelect: false,
        enableSorting: true,
        enableFiltering: true,
        enableGridMenu: false,
        enableCellEdit: false,
        enablePaginationControls: true,
        paginationPageSizes: [10, 25, 50, 100],
        paginationPageSize: 10,
        rowHeight: 40,

        rowTemplate: "<div ng-dblclick=\"grid.appScope.vm.getNoteDetail(grid, row)\" ng-repeat=\"(colRenderIndex, col) in colContainer.renderedColumns track by col.colDef.name\" class=\"ui-grid-cell\" ng-class=\"{ 'ui-grid-row-header-cell': col.isRowHeader }\" ui-grid-cell></div>"
    };

    vm.serviceGrid.columnDefs = [{
            field: 'noteAddress',
            displayName: 'Address',
            headerCellClass: 'addressHeaderClass',
            cellClass: 'uiGridCellClass',
            width: 260,
           
            filter: {
                condition: uiGridConstants.filter.STARTS_WITH,
                placeholder:'&#xf02b; Some Input Text'
            },
            cellTemplate: "<a ng-href=\"\" style=\"cursor:pointer;\" ng-click= \"grid.appScope.vm.getNoteDetail(grid, row)\">{{row.entity.noteAddress}}</a>"
        },
        {
            field: 'yield',
            displayName: 'Yield',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by Yield',
            },
            cellTemplate: "<div>{{row.entity.yield}}</div>"
        }, {
            field: 'estimatedITV',
            displayName: 'Estimated ITV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by Est ITV'
            },
            cellTemplate: "<div>{{row.entity.estimatedITV}}</div>"
        }, {
            field: 'estimatedLTV',
            displayName: 'Estimated LTV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by Est LTV'
            },
            cellTemplate: "<div>{{row.entity.estimatedLTV}}</div>"
        }, {
            field: 'currentLTV',
            displayName: 'LTV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by LTV'
            },
            cellTemplate: "<div ng-show={{row.entity.marketValueAvailable}}><p>{{row.entity.currentLTV}}<span class=\"glyphicon glyphicon-info-sign tooltip-color \"" +
                "tooltip-placement=\"bottom\" uib-tooltip=\"Last updated date {{ row.entity.marketUpdateDate | date}}\"></span> </p></div>" +
                "<a ng-show={{!row.entity.marketValueAvailable}} ng-href=\"\" style=\"cursor:pointer;\" ng-click= \"grid.appScope.vm.updateMarketValue(grid, row)\">{{row.entity.rowText}}</a>"
        },
         {
            field: 'currentITV',
            displayName: 'ITV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by ITV'
            },
            cellTemplate: "<div ng-show={{row.entity.marketValueAvailable}}><p>{{row.entity.currentITV}}<span class=\"glyphicon glyphicon-info-sign tooltip-color \"" +
                "tooltip-placement=\"bottom\" uib-tooltip=\"Last updated date {{ row.entity.marketUpdateDate | date}}\"></span> </p></div>" +
                "<a ng-show={{!row.entity.marketValueAvailable}} ng-href=\"\" style=\"cursor:pointer;\" ng-click= \"grid.appScope.vm.updateMarketValue(grid, row)\">{{row.entity.rowText}}</a>"
        },
        {
            field: 'schoolScore',
            displayName: 'School Score',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by School Score',
            },
            cellTemplate: "<div ><p>{{row.entity.schoolScore | getDefaultValueForNull}}</p></div>"
        }, {
            field: 'crimeScore',
            displayName: 'Crime Score',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by Crime Score' 
            },
            cellTemplate: "<div><p>{{row.entity.crimeScore | getDefaultValueForNull}}</p></div>"
        }, {
            field: 'marketValue',
            displayName: 'Market Value',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search by Market Value'
            },
            cellTemplate: "<div ng-show={{row.entity.marketValueAvailable}}><p>{{row.entity.marketValue}}<span class=\"glyphicon glyphicon-info-sign tooltip-color \"" +
                "tooltip-placement=\"bottom\" uib-tooltip=\"Last updated date {{ row.entity.marketUpdateDate | date}}\"></span> </p></div>" +
                "<a ng-show={{!row.entity.marketValueAvailable}} ng-href=\"\" style=\"cursor:pointer;\" ng-click= \"grid.appScope.vm.updateMarketValue(grid, row)\">{{row.entity.rowText}}</a>"
           
        }
    ];

    $scope.init = function() {
        WaitingDialog.show();
        $http.get('api/fetchAllNotes').then(function(response) {
            $scope.vm.serviceGrid.data = response.data;
        }, function(response) {
            $scope.vm.serviceGrid.data = [];
            $auth.checkLoginFromServer(response.status);
        }).then(function() {
            WaitingDialog.hide();
        });

    }


}

NoteDetailService.$inject = ['$http', '$rootScope', 'NoteService', 'toastr', '$state','$auth','$window','UserService'];

function NoteDetailService($http, $rootScope, NoteService, toastr, $state, $auth,$window,UserService) {
    var service = {};
    service.getNoteDetail = getNoteDetail;
    service.updateMarketValue = updateMarketValue;
    
    function getNoteDetail(grid, row) {
        NoteService.getNoteDetail(row.entity.noteId).then(function(response) {
            NoteService.setNoteDetailModel(response);
            $state.go('noteDetail');
        }, function(response) {
            $auth.checkLoginFromServer(response.status);
            toastr.error("We are unable to find details for this note. Please try after sometime.")
        });
    };
    
    function updateMarketValue(grid, row) {
    	var noteDetailModel ={
    			noteId : row.entity.noteId,
    			selPropType:row.entity.propertyType,
    			propertyDetailModel:{city:row.entity.city,
    							     state:row.entity.state,
    							     streetAddress:row.entity.streetAddress,
    							     zip:row.entity.zipCode
    							     }
    	}
    	UserService.updateSubscription().then(function(response) {
			$auth.setUser(response);
		    	
    	NoteService.subscribeNote(noteDetailModel).then(function(response) {
        	$window.location.reload();
        }, function(response) {
            $auth.checkLoginFromServer(response.status);
            toastr.error("We are unable to find details for this user. Please try after sometime.")
        })}, function(response) {
            $auth.checkLoginFromServer(response.status);
            toastr.error("We are update the user subscription. Please try after sometime.")
        });
    };

    return service;
}