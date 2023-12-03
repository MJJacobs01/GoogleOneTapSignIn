package za.co.jacobs.mj.googleonetapsignin.presentation.screen.login

import androidx.compose.runtime.*
import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.*
import kotlinx.coroutines.*
import za.co.jacobs.mj.googleonetapsignin.domain.model.*
import za.co.jacobs.mj.googleonetapsignin.domain.repository.*
import za.co.jacobs.mj.googleonetapsignin.util.*
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

    private val _apiResponse: MutableState<RequestState<ApiResponse>> =
        mutableStateOf(RequestState.Idle)
    val apiResponse: MutableState<RequestState<ApiResponse>> = _apiResponse

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

    fun verifyTokenOnBackend(request: ApiRequest) {
        _apiResponse.value = RequestState.Loading
        try {
            viewModelScope.launch(Dispatchers.IO) {
                val response = repository.verifyTokenOnBackend(request = request)
                _apiResponse.value = RequestState.Success(response)
                _messageBatState.value = MessageBarState(
                    message = response.message,
                    error = response.error
                )
            }
        } catch (e: Exception) {
            _apiResponse.value = RequestState.Error(t = e)
            _messageBatState.value = MessageBarState(
                error = e
            )
        }
    }
}

class GoogleAccountNotFoundException(
    override val message: String? = "Google account not found"
) : Exception()