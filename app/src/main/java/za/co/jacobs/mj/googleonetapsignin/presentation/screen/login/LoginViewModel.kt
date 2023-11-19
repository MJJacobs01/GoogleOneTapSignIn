package za.co.jacobs.mj.googleonetapsignin.presentation.screen.login

import androidx.compose.runtime.*
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*
import za.co.jacobs.mj.googleonetapsignin.domain.repository.*
import javax.inject.*

/**
 * Created by MJ Jacobs on 2023/11/19 at 15:10
 */

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _signedInState = mutableStateOf(false)
    val signedInState: State<Boolean> = _signedInState

    private val _messageBatState = mutableStateOf(MessageBarState())
    val messageBarState: State<MessageBarState> = _messageBatState

    init {
        viewModelScope.launch {
            repository.readSignedInState().collect { completed ->
                _signedInState.value = completed
            }
        }
    }

    fun saveSignedInState(signedIn: Boolean) {
        viewModelScope.launch {
            repository.saveSignedInState(signedIn)
        }
    }

    fun updateMessageBarState() {
        _messageBatState.value = MessageBarState(error = GoogleAccountNotFoundException())
    }
}

class GoogleAccountNotFoundException(
    override val message: String? = "Google account not found"
) : Exception()