package ui.navigation

import androidx.navigation.NavController

sealed class NavGraph(val route: String) {
    object NavHome: NavGraph("navHome")
    object NavSettings: NavGraph("navSettings")
    object WindowsAdbHelp: NavGraph("WindowsAdbHelp")
    object HomeLabelPage: NavGraph("HomeLabelPage")
    object HomeLinkPage: NavGraph("HomeLinkPage")
}


fun NavController.navigateHomeLabelPage(searchContent: String = "") {
    navigate("${NavGraph.HomeLabelPage.route}?searchContent=${searchContent}")
}