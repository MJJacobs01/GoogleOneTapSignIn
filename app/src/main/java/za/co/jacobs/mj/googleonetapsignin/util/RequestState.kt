package za.co.jacobs.mj.googleonetapsignin.util

/**
 * Created by MJ Jacobs on 2023/12/03 at 14:24
 */

sealed class RequestState<out T> {
    data object Idle : RequestState<Nothing>()

    data object Loading : RequestState<Nothing>()

    data class Success<T>(val data: T) : RequestState<T>()

    data class Error(val t: Throwable) : RequestState<Nothing>()
}