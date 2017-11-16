var app = angular.module('NoteApp');

app.controller('NoteDashboardCtrl', NoteDashboardCtrl);
app.service('NoteDetailService', NoteDetailService);

NoteDashboardCtrl.$inject = ['$scope', '$http', '$auth', '$rootScope', '$uibModal', 'NoteDetailService', 'uiGridConstants', 'noteDetailModel', 'noteUploadAPI', 'NoteService', 'WaitingDialog','UtilityService','UserService','$window','toastr'];

function NoteDashboardCtrl($scope, $http, $auth, $rootScope, $uibModal, NoteDetailService, uiGridConstants, noteDetailModel, noteUploadAPI, NoteService, WaitingDialog,UtilityService,UserService,$window,toastr) {
	UtilityService.setActiveHeader('noteDashboard');
	var vm = this;
    $scope.noteDetailModel = noteDetailModel;
    vm.getNoteDetail = NoteDetailService.getNoteDetail;
    vm.updateSubscription = NoteDetailService.updateSubscription;
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
            sortingAlgorithm:  function(a, b, rowA, rowB, direction) {
            	if(a===b){
            		return 0;
            	}else{
            		var aArray = a.split(",");
            		var bArray = b.split(",");
            		var aState =  aArray[aArray.length-2];
            		var bState =  bArray[bArray.length-2];
            		var aCity =  aArray[aArray.length-3];
            		var bCity =  bArray[bArray.length-3];
            		var aStreetAdd =  aArray[aArray.length-4];
            		var bStreetAdd =  bArray[bArray.length-4];
            		
            		if(aState == bState){
            			if(aCity == bCity){
            				if(aStreetAdd == bStreetAdd){	
            					return 0;
            				}else{
            					return aStreetAdd.localeCompare(bStreetAdd);
            				}
            			}else{
            				return aCity.localeCompare(bCity);
            			}
            		}else{
            			return aState.localeCompare(bState);
            		}
            		
            		
            	}
            },
            filter: {
                condition: uiGridConstants.filter.STARTS_WITH,
                placeholder:'Search by Address'
            },
            cellTemplate: "<a ng-href=\"\" style=\"cursor:pointer;float:left\" ng-click= \"grid.appScope.vm.getNoteDetail(grid, row)\">{{row.entity.noteAddress}}</a>"
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
                placeholder:'Search',
            },
            cellTemplate: "<div>{{row.entity.yield | number}}%</div>"
        }, {
            field: 'estimatedITV',
            displayName: 'Estimated ITV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search'
            },
            cellTemplate: "<div>{{row.entity.estimatedITV | number}}%</div>"
        }, {
            field: 'estimatedLTV',
            displayName: 'Estimated LTV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search'
            },
            cellTemplate: "<div>{{row.entity.estimatedLTV | number}}%</div>"
        }, {
            field: 'currentLTV',
            displayName: 'LTV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search'
            },
            cellTemplate: "<div>{{row.entity.currentLTV | number | getDefaultValueForPercentage}}</div>"
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
                placeholder:'Search'
            },
            cellTemplate: "<div>{{row.entity.currentITV | number | getDefaultValueForPercentage}}</div>"
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
                placeholder:'Search',
            },
            cellTemplate: "<div ><p>{{row.entity.schoolScore  | number | number:0 | getDefaultValueForNull }}</p></div>"
        }, {
            field: 'crimeScore',
            displayName: 'Crime Score',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search' 
            },
            cellTemplate: "<div><p>{{row.entity.crimeScore  | number | number:0 | getDefaultValueForNull }}</p></div>"
        }, {
            field: 'marketValue',
            displayName:  ' Market Value ',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                },
                placeholder:'Search'
            },
            cellTemplate: "<div style=\"float:left\">{{row.entity.marketValue | number | addCurrencySymbol | getDefaultValueForNull }}</div><a ng-href=\"\"  style=\"cursor:pointer; text-align:right\"  ng-click= \"grid.appScope.vm.updateMarketValue(grid, row)\"><span class=\"glyphicon glyphicon-refresh\"></span></a>"
           
        }
    ];
   
	$scope.isSubscribed = function() {
		var user = $auth.getUser();
		if (user && "P1" == user.subscriptionName) {
			return true;
		}
		return false;
	}
	
	$scope.subscribeNote = function() {
		UserService.updateSubscription().then(function(response) {
			$auth.setUser(response);
			$window.location.reload();
			toastr.error("You have been subscribed successfully.")
		}, function(response) {
			$auth.checkLoginFromServer(response.status);
			toastr.error("We are unable to update user subscripton. Please try after sometime.")
		});

	}
    
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
    service.updateSubscription = updateSubscription;
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
    	NoteService.updateMarketValue(noteDetailModel).then(function(response) {
        	$window.location.reload();
        }, function(response) {
            $auth.checkLoginFromServer(response.status);
            toastr.error("We are unable to find details for this user. Please try after sometime.")
        })
    }
    
    
    function updateSubscription(grid, row) {
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
			$window.location.reload();
			toastr.success("You have been subscribed successfully.")
    	}, function(response) {
            $auth.checkLoginFromServer(response.status);
            toastr.error("We are update the user subscription. Please try after sometime.")
        });
    };

    return service;
}