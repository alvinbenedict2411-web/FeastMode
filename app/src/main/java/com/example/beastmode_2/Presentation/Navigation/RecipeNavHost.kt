package com.example.beastmode_2.Presentation.Navigation

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.beastmode_2.Presentation.Screens.AddRecipeScreen.AddRecipeScreen
import com.example.beastmode_2.Presentation.Screens.CustomRecipeDetailsScreen.RecipeDetailsCustom
import com.example.beastmode_2.Presentation.Screens.HomeScreen.HomeScreen
import com.example.beastmode_2.Presentation.Screens.MyRecipeScreen.MyRecipeScreen
import com.example.beastmode_2.Presentation.Screens.RecipeDetailsScreen.RecipeDetailsScreen
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.AddRecipeViewModel
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.CustomRecipeDetailsViewModel
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.CustomRecipeViewModel
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.HomeViewModel
import com.example.beastmode_2.Presentation.Viewmodels.ViewModels.RecipeDetailsViewModel

@Composable
fun RecipeNavHost(application: Application){
    val navController = rememberNavController()
    val viewmodelHome: HomeViewModel = viewModel{
        HomeViewModel(application)
    }
    val viewmodelRecipeDetails: RecipeDetailsViewModel = viewModel{
        RecipeDetailsViewModel(application)
    }
    val viewmodelCustomRecipeDetails: CustomRecipeDetailsViewModel= viewModel{
        CustomRecipeDetailsViewModel(application)
    }
    val viewmodelcustomRecipe: CustomRecipeViewModel= viewModel{
        CustomRecipeViewModel(application)
    }
    val AddRecipeViewmodel: AddRecipeViewModel= viewModel{
        AddRecipeViewModel(application)
    }
    NavHost(
        navController = navController,
        startDestination = Routes.Home
    ){
        composable<Routes.Home>{
            HomeScreen(onRecipeClick = {id->
                navController.navigate(Routes.RecipeDetails(id))
            },viewmodel=viewmodelHome,navController=navController)
        }
        composable <Routes.RecipeDetails>{backstackentry->
            val data=backstackentry.toRoute<Routes.RecipeDetails>()
            RecipeDetailsScreen(id=data.id,
                onBackClick = {
                navController.popBackStack(Routes.Home,false)
            },viewmodel=viewmodelRecipeDetails)
        }
        composable<Routes.MyRecipes>{
            MyRecipeScreen(viewmodelcustomRecipe, onRecipeClick = {id->
                navController.navigate(Routes.CustomRecipeDetails(id))
            },navController)
        }
        composable<Routes.CustomRecipeDetails> {backstackentry->
            val data=backstackentry.toRoute<Routes.CustomRecipeDetails>()
            RecipeDetailsCustom(id=data.id,viewmodelCustomRecipeDetails, onbackclick = {navController.popBackStack(Routes.MyRecipes,false)},
                onEditClick = {navController.navigate(Routes.AddRecipe(data.id))},navController)
        }
        composable<Routes.AddRecipe>{backstackentry->
            val data=backstackentry.toRoute<Routes.AddRecipe>()
            AddRecipeScreen(id=data.id,viewmodel=AddRecipeViewmodel, onBackClick = {navController.popBackStack()},
                onSaveClick = {navController.navigate(Routes.MyRecipes){
                    popUpTo<Routes.AddRecipe>{inclusive=true}
                } }, onClickRetry = {navController.popBackStack()
                    navController.navigate(Routes.AddRecipe)})
        }
    }
}