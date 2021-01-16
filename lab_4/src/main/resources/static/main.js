var app = angular.module("LabTestResultManagement", []);

// Controller Part
app.controller("LabTestResultController", function($scope, $http) {


    $scope.results = [];
    $scope.resultForm = {
        id: null,
        patient: "",
        date: "2020-02-13",
        hemoglobin: 0,
        glucose: 0.0,
        alt: 0,
        ast: 0
    };

    // Now load the data from server
    _refreshResultsData();

    // Call: http://localhost:8080/save
    $scope.addResult = function() {
        $http({
            method: "POST",
            url: "/save",
            data: angular.toJson($scope.resultForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    $scope.createResults = function() {
        _clearFormData();
    }

    // Call: http://localhost:8080/delete
    $scope.deleteResult = function() {
        $http({
            method: 'DELETE',
            url: '/delete',
            data: angular.toJson($scope.resultForm),
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(_success, _error);
    };

    // Call: http://localhost:8080/all_results
    function _refreshResultsData() {
        $http({
            method: 'GET',
            url: '/all_results'
        }).then(
            function(res) { // success
                $scope.results = res.data;
            },
            function(res) { // error
                console.log("Error: " + res.status + " : " + res.data);
            }
        );
    }

    $scope.refreshResultsData = function() {
            $http({
                method: 'GET',
                url: '/all_results',
                 params: {id: $scope.resultForm.id, patient: $scope.resultForm.patient, date: $scope.resultForm.date}
            }).then(
                function(res) { // success
                    $scope.results = res.data;
                },
                function(res) { // error
                    console.log("Error: " + res.status + " : " + res.data);
                }
            );
        }

    function _success(res) {
        _refreshResultsData();
        _clearFormData();
    }

    function _error(res) {
        var data = res.data;
        var status = res.status;
        var header = res.header;
        var config = res.config;
        alert("Error: " + status + ":" + data);
    }

    // Clear the form
    function _clearFormData() {
        $scope.resultForm.id = 0;
        $scope.resultForm.patient = "";
        $scope.resultForm.date = "2021-01-20";
        $scope.resultForm.hemoglobin = 0;
        $scope.resultForm.glucose = 0.0;
        $scope.resultForm.alt = 0;
        $scope.resultForm.ast = 0
    };
});