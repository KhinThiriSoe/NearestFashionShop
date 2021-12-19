package com.khin.fashion.core.data.extensions

import com.khin.fashion.core.domain.entity.NetworkErrorResponse
import com.khin.fashion.core.domain.entity.RepositoryResult
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.net.UnknownHostException

@ExperimentalCoroutinesApi
internal class NetworkExtTest {

    @Test
    fun `success - wrapNetworkCall - success returned`() = runBlockingTest {
        val networkCall: suspend () -> String = { "something" }

        val result = wrapNetworkCall(networkCall)

        val expected = RepositoryResult.Success("something")
        assertEquals(expected, result)
    }

    @Test
    fun `UnknownHostException - wrapNetworkCall - ConnectionError returned`() = runBlockingTest {
        val networkCall: suspend () -> String = { throw UnknownHostException("something") }

        val result = wrapNetworkCall(networkCall)

        assertEquals(RepositoryResult.ConnectionError, result)
    }

    @Test
    fun `HttpException - wrapNetworkCall - HttpException returned`() = runBlockingTest {
        val networkErrorResponse = NetworkErrorResponse(
            code = "SomeError",
            message = "something went wrong",
            detail = "something went wrong"
        )
        val networkCall: suspend () -> Response<NetworkErrorResponse> = {
            throw HttpException(
                Response.error<NetworkErrorResponse>(
                    400,
                    "{\"code\":\"SomeError\",\"message\":\"something went wrong\",\"detail\":\"something went wrong\"}".toResponseBody()
                )
            )
        }

        val result = wrapNetworkCall(networkCall)

        assertEquals(RepositoryResult.GenericError(networkErrorResponse), result)
    }
}