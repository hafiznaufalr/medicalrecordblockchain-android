package my.id.medicalrecordblockchain.utils

import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
import java.net.UnknownHostException

object NetworkHandler {
    suspend fun <T> safeApiCall(apiCall: suspend () -> Response<T>
    ): ResultData<T> {
        return try {
            val response: Response<T> = apiCall()

            if (response.isSuccessful) {
                when(response.code()) {
                    200, 201 -> {
                        ResultData.Success(response.body()!!)
                    }
                    else -> {
                        var msg = ""
                        response.errorBody()?.string()?.let { err ->
                            msg = JSONObject(err).getString("message")
                        }
                        ResultData.Failure(msg)
                    }
                }
            } else {
                var msg = ""
                response.errorBody()?.string()?.let { err ->
                    msg = JSONObject(err).getString("message")
                }
                ResultData.Failure(msg)
            }
        } catch (e: IOException) {
            ResultData.Failure(e.localizedMessage)
        } catch (e: UnknownHostException) {
            ResultData.Failure(e.localizedMessage)
        }
    }
}