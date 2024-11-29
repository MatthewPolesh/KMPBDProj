package org.example.project

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.example.project.data.database.initDatabase
import org.example.project.di.databaseModule
import org.example.project.di.repositoryModule
import org.example.project.di.viewModelModule
import org.example.project.presentation.gui.screens.AuthScreen
import org.example.project.presentation.gui.screens.MainScreen
import org.example.project.presentation.gui.screens.RegScreen
import org.example.project.presentation.navigation.Screens
import org.jetbrains.exposed.sql.Database
import org.koin.core.context.startKoin


fun main() = application {

    startKoin {
        modules(
            repositoryModule,
            viewModelModule,
            databaseModule
        )
    }

    val database:Database = org.koin.core.context.GlobalContext.get().get()
    initDatabase(database)

    val navController: NavHostController = rememberNavController()

    Window(
        onCloseRequest = ::exitApplication,
        title = "KotlinProject",
    ) {
        NavHost(
            navController = navController,
            startDestination = Screens.Auth.route
        ){
            composable(route = Screens.Main.route) {
                MainScreen(onLogOut = { navController.navigate(Screens.Auth.route)})
            }
            composable(route = Screens.Auth.route) {
                AuthScreen(
                    onAuth = {navController.navigate(Screens.Main.route)},
                    onReg = {navController.navigate(Screens.Reg.route)}
                )
            }
            composable(route = Screens.Reg.route) {
                RegScreen(
                    onReg = {navController.navigate(Screens.Main.route)}
                )
            }
        }

    }
}
