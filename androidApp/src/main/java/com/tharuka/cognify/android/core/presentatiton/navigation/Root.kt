package com.tharuka.cognify.android.core.presentatiton.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.tharuka.cognify.android.chat.presentation.AndroidChatViewModel
import com.tharuka.cognify.android.chat.presentation.PromptScreen
import com.tharuka.cognify.android.history.presentation.AndroidHistoryViewModel
import com.tharuka.cognify.android.history.presentation.HistoryScreen
import com.tharuka.cognify.android.result.presentation.AndroidResultViewModel
import com.tharuka.cognify.android.result.presentation.ResultScreen
import com.tharuka.cognify.chat.presentation.ChatEvent
import com.tharuka.cognify.history.presentation.HistoryEvent
import com.tharuka.cognify.result.presentation.ResultEvent

@Composable
fun Root() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Routes.PROMPT + "/{${Args.chatId}}") {
        //prompt
        composable(
            route = Routes.PROMPT + "/{${Args.chatId}}",
            arguments = listOf(
                navArgument(name = Args.chatId){
                    nullable = true
                    defaultValue = null
                }
            )
        ) {

            val viewModel = hiltViewModel<AndroidChatViewModel>()
            val state by viewModel.state.collectAsState()

            PromptScreen(state = state) { event ->
                when (event) {
                    is ChatEvent.GotoHistory -> {
                        navController.navigate(Routes.HISTORY)
                    }
                    else -> {
                        viewModel.onEvent(event)
                    }
                }
            }
        }
        //conversation
        composable(
            route = Routes.CONVERSATION + "/{${Args.chatId}}/{${Args.continuable}}",
            arguments = listOf(
                navArgument(name = Args.chatId) {
                    type = NavType.StringType
                },
                navArgument(
                    name = Args.continuable
                ){
                    type = NavType.BoolType
                    defaultValue = false
                }
            )
        ) {
            val viewModel = hiltViewModel<AndroidResultViewModel>()
            val state by viewModel.state.collectAsState()

            ResultScreen(state = state){event->
                when(event){
                    is ResultEvent.OnClose -> {
                        navController.popBackStack()
                    }
                    is ResultEvent.OnContinue -> {
                        navController.navigate(route = Routes.PROMPT + "/${state.chatId}")
                    }
                }
            }
        }
        //history
        composable(route = Routes.HISTORY) {

            val viewModel = hiltViewModel<AndroidHistoryViewModel>()
            val state by viewModel.state.collectAsState()

            HistoryScreen(state = state) { event ->
                when (event) {
                    is HistoryEvent.SelectItem -> {
                        navController.navigate(route = Routes.CONVERSATION + "/${event.chatId}/${true}")
                    }
                    is HistoryEvent.NewChat ->{
                        navController.navigate(route = Routes.PROMPT + "/${Args.newChat}")
                    }
                    is HistoryEvent.GotoChat -> navController.popBackStack()
                }
            }
        }
    }
}