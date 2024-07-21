package com.example.cat_alogue

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.cat_alogue.model.Breed
import com.example.cat_alogue.screens.catDetail.CatDetailScreen
import com.example.cat_alogue.screens.catList.CatListScreen
import com.example.cat_alogue.screens.catList.CatListViewModel
import com.example.cat_alogue.ui.theme.CatalogueTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CatalogueTheme {
                val navController = rememberNavController()
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    NavHost(
                        navController = navController,
                        startDestination = Route.LIST.name,
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable(Route.LIST.name) {
                            val viewModel = hiltViewModel<CatListViewModel>()
                            CatListScreen(
                                viewModel = viewModel,
                                onBreedSelected = { breedId ->
                                    navController.navigate("${Route.DETAILS.name}/$breedId")
                                })
                        }
                        composable(
                            route = "${Route.DETAILS.name}/{breedId}",
                            arguments = listOf(navArgument("breedId") {
                                type = NavType.StringType
                            })
                        ) {
                            CatDetailScreen(
                                breed = Breed(name = "Test")
                            )
                        }
                    }
                }
            }
        }
    }
}

enum class Route {
    LIST,
    DETAILS,
}
