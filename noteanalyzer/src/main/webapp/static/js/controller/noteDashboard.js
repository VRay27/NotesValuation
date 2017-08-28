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
                condition: uiGridConstants.filter.STARTS_WITH
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
                }
            },
            cellTemplate: "<div>{{row.entity.yield}}</div>"
        }, {
            field: 'originalLTV',
            displayName: 'Original LTV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                }
            },
            cellTemplate: "<div>{{row.entity.originalLTV}}</div>"
        }, {
            field: 'effectiveLTV',
            displayName: 'Effective LTV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                }
            },
            cellTemplate: "<div>{{row.entity.effectiveLTV}}</div>"
        }, {
            field: 'currentEffectiveLTV',
            displayName: 'Current Effective LTV',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                }
            },
            cellTemplate: "<div ng-show={{row.entity.marketValueAvailable}}><p>{{row.entity.currentEffectiveLTV}}<span class=\"glyphicon glyphicon-info-sign tooltip-color \"" +
                "tooltip-placement=\"bottom\" uib-tooltip=\"Last updated date {{ row.entity.marketUpdateDate | date}}\"></span> </p></div>" +
                "<div ng-show={{!row.entity.marketValueAvailable}}><p>No data available</p></div>"
        },
        {
            field: 'schoolScore',
            displayName: 'School Score',
            cellClass: 'uiGridCellClass',
            headerCellClass: 'addressHeaderClass',
            filter: {
                condition: function(searchTerm, cellValue) {
                    return $scope.customNumberFilter(searchTerm, cellValue);
                }
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
                }
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
                }
            },
            /* sort: {
               direction: uiGridConstants.ASC,
               priority: 1,
             },*/
            cellTemplate: "<div ng-show={{row.entity.marketValueAvailable}}><p>{{row.entity.marketValue}}<span class=\"glyphicon glyphicon-info-sign tooltip-color \"" +
                "tooltip-placement=\"bottom\" uib-tooltip=\"Last updated date {{ row.entity.marketUpdateDate | date}}\"></span> </p></div>" +
                "<a ng-show={{!row.entity.marketValueAvailable}} ng-href=\"\" style=\"cursor:pointer;\" ng-click= \"grid.appScope.vm.updateMarketValue(grid, row)\">Get Current Market Value</a>"
           
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

NoteDetailService.$inject = ['$http', '$rootScope', 'NoteService', 'toastr', '$state','$auth'];

function NoteDetailService($http, $rootScope, NoteService, toastr, $state, $auth) {
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
        NoteService.getNoteDetail(row.entity.noteId).then(function(response) {
            NoteService.setNoteDetailModel(response);
            $state.go('noteDashboard');
        }, function(response) {
            $auth.checkLoginFromServer(response.status);
            toastr.error("We are unable to find details for this note ID. Please try after sometime.")
        });
    };

    return service;
}