import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.LocalPlatformContext
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import ui.home.HomeSearchPage
import ui.navigation.NavGraph
import ui.navigation.enterTransition
import ui.navigation.exitTransition
import ui.navigation.popEnterTransition
import ui.navigation.popExitTransition
import org.jetbrains.compose.ui.tooling.preview.Preview
import ui.home.HomeLabelPage
import ui.home.HomeLinkPage
import ui.settings.SettingsPage
import ui.theme.CSMultiTheme
import ui.theme.LocalSignInDialogShowState
import ui.unions.SignInDialog
import ui.unions.TopBar
import ui.windows.WindowsAdbHelp

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    CSMultiTheme {
        val context = LocalPlatformContext.current
        setSingletonImageLoaderFactory {
            getAsyncImageLoader(context)
        }
        val navController = rememberNavController()
        Scaffold(topBar = {
            TopBar(navController)
        }) {
            NavHost(navController, startDestination = NavGraph.NavHome.route) {
                composable(
                    NavGraph.NavHome.route,
                    enterTransition = { enterTransition() },
                    exitTransition = { exitTransition() },
                    popEnterTransition = { popEnterTransition() },
                    popExitTransition = { popExitTransition() }) {
                    HomeSearchPage()
                }
                composable(
                    NavGraph.NavSettings.route,
                    enterTransition = { enterTransition() },
                    exitTransition = { exitTransition() },
                    popEnterTransition = { popEnterTransition() },
                    popExitTransition = { popExitTransition() }) {
                    SettingsPage()
                }

                composable(
                    NavGraph.WindowsAdbHelp.route,
                    enterTransition = { enterTransition() },
                    exitTransition = { exitTransition() },
                    popEnterTransition = { popEnterTransition() },
                    popExitTransition = { popExitTransition() }) {
                    WindowsAdbHelp()
                }

                composable(
                    "${NavGraph.HomeLabelPage.route}?searchContent={searchContent}",
                    arguments = listOf(
                        navArgument("searchContent") {
                            defaultValue = ""
                            type = NavType.StringType
                        }
                    ),
                    enterTransition = { enterTransition() },
                    exitTransition = { exitTransition() },
                    popEnterTransition = { popEnterTransition() },
                    popExitTransition = { popExitTransition() }) {
                    val searchContent = it.arguments?.getString("searchContent") ?: ""
                    HomeLabelPage(searchContent = searchContent)
                }

                composable(
                    NavGraph.HomeLinkPage.route,
                    enterTransition = { enterTransition() },
                    exitTransition = { exitTransition() },
                    popEnterTransition = { popEnterTransition() },
                    popExitTransition = { popExitTransition() }) {
                    HomeLinkPage()
                }
            }

            SignInDialog(modifier = Modifier.width(300.dp), showState = LocalSignInDialogShowState.current)
        }
    }
}

fun getAsyncImageLoader(context: PlatformContext) =
    ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()