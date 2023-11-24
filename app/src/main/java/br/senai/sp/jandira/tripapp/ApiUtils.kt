package br.senai.sp.jandira.tripapp

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

object RetrofitHelper {

    private const val baseUrl =  "http://10.107.144.20:3000/"

    fun getInstance(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}

data class BaseResponse(
    val erroStatus : String,
    val mensagemStatus: String
)

interface ApiService {

    @POST("usuario/cadastrarUsuario")
    fun cadastrarUsuario(
        @Body userRequest: UserRequest
    ) : Call<BaseResponse>
}

fun cadastrarUsuarioNaApi(
    userRequest: UserRequest,
    context: Context
) {
    val retrofit = RetrofitHelper.getInstance()
    val api = retrofit.create(ApiService::class.java)

    val call = api.cadastrarUsuario(
        userRequest = userRequest
    )
    call.enqueue( object : Callback<BaseResponse>{
        override fun onResponse(call: Call<BaseResponse>, response: Response<BaseResponse>) {
            Toast.makeText(
                context,
                "Deu certo! Cadastrado com sucesso!",
                Toast.LENGTH_LONG
            ).show()
        }

        override fun onFailure(call: Call<BaseResponse>, t: Throwable) {
            Toast.makeText(
                context,
                "Algo deu errado! ${t.message}",
                Toast.LENGTH_LONG
            ).show()
        }
    }
    )

}