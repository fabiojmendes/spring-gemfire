/**
 * Navigation Controller
 */
function Navigation($scope, $location) {

	$scope.tab = function(route) {
		return $location.path().startsWith('/' + route);
	};
}
