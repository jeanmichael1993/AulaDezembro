package com.example.auladezembro.activities

import android.app.PendingIntent.getService
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.auladezembro.R
import com.example.auladezembro.dto.DtoUser
import com.example.auladezembro.services.RetrofitService
import kotlinx.android.synthetic.main.activity_cadastro_de_usuario.*
import kotlinx.android.synthetic.main.content_main.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CadastroDeUsuarioActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastro_de_usuario)
    }

    fun cadastrar(view: View) {
        var TAG = "CadastroDeUsuarioActivi"

        var email = et_cadastro_usuario_email.text.toString()
        var nome = et_cadastro_usuario_nome.text.toString()
        var telefone = et_cadastro_usuario_phone.text.toString()
        var senha = et_cadastro_usuario_password.text.toString()

        val dtoUser: DtoUser = DtoUser()
        dtoUser.email = email
        dtoUser.name = nome
        dtoUser.phone = telefone
        dtoUser.password = senha

        val serviceRetrofit = RetrofitService()
        serviceRetrofit.api?.cadastrarUsuario(dtoUser)?.enqueue(object : Callback<DtoUser> {
            override fun onResponse(call: Call<DtoUser>, response: Response<DtoUser>) {
                Log.d("Responsta","Usuario cadastrado com ID: " + response.body()?.id.toString())
            }

            override fun onFailure(call: Call<DtoUser>, t: Throwable) {
                Log.e("Erro", "************** erro **********\n" + t?.message.toString())
            }
        })
    }

    private fun buscaDados() {
        val serviceRetrofit = RetrofitService()
        serviceRetrofit.api?.obterUsuarios()?.enqueue(object : Callback<List<DtoUser>> {
            override fun onResponse(
                call: Call<List<DtoUser>?>?,
                response: Response<List<DtoUser>?>?
            ) {
                val lista = response?.body();
                if (lista != null) {
                    for (user in lista) {
                        Log.d("Resposta", user.name.toString())
                    }
                }
            }

            override fun onFailure(call: Call<List<DtoUser>?>?, t: Throwable?) {
                Log.e("Erro", "************** erro **********\n" + t?.message.toString())
            }
        })
    }
}