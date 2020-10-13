package com.kotlin.loteriaDosSonhos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val TAG = "MainActivity"
    var numero : Int = 0
    var tentativas: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        idEnviar.setOnClickListener{
            if (idTentativa.text.toString().toInt() > 0 && idTentativa.text.toString().toInt() <= 10)
                testarValor(idTentativa.text.toString().toInt())
            else
                mostrarToast("Número inválido")

            idTentativa.text?.clear()
        }
    }

    override fun onStart(){
        super.onStart()
        numero = kotlin.random.Random.nextInt(1,10)
        onResume()
    }

    override fun onResume() {
        super.onResume()
        mostrarToast("O jogo está começando, boa sorte!!!")
        Log.i(TAG,numero.toString())
    }

    fun testarValor(valor: Int){
        tentativas++
        if(tentativas <= 3){
            if (valor == numero){
                when(tentativas){
                    1 -> mostrarToast("Parabéns, nossa você tem muita sorte :D")
                    2 -> mostrarToast("Parabéns, você tem sorte :)")
                    3 -> mostrarToast("Parabéns, essa foi por pouco :|")
                }
                reiniciar()
            }
            else{
                if(tentativas == 3){
                    mostrarToast("Não foi dessa vez, mas não desista. O número era ${numero}")
                    tentativas = 0
                }
                else
                    mostrarToast("Errou, tente novamente")
            }
        }
        else{
            mostrarToast("Não foi dessa vez, mas não desista. O número era ${numero}")
            reiniciar()
        }

    }

    fun mostrarToast(msg: String){
        Toast.makeText(this,msg,Toast.LENGTH_SHORT).show()
    }
    fun reiniciar(){
        tentativas = 0
    }
}