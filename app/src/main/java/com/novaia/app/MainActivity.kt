
package com.novaia.app

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val fundo = Color.rgb(18, 18, 24)
        val branco = Color.WHITE
        val roxo = Color.rgb(130, 80, 220)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL
        layout.setPadding(20, 30, 20, 20)
        layout.setBackgroundColor(fundo)

        val titulo = TextView(this)
        titulo.text = "NOVA IA"
        titulo.textSize = 30f
        titulo.setTextColor(branco)
        titulo.gravity = Gravity.CENTER
        layout.addView(titulo)

        val subtitulo = TextView(this)
        subtitulo.text = "Sua inteligência artificial"
        subtitulo.textSize = 15f
        subtitulo.setTextColor(Color.LTGRAY)
        subtitulo.gravity = Gravity.CENTER
        layout.addView(subtitulo)

        val conversa = TextView(this)
        conversa.text = "Olá! Eu sou a NOVA IA.\\nEm breve poderei conversar com você."
        conversa.textSize = 17f
        conversa.setTextColor(branco)
        conversa.setPadding(10, 30, 10, 20)

        val rolagem = ScrollView(this)
        rolagem.addView(conversa)

        layout.addView(
            rolagem,
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1f
            )
        )

        val entrada = EditText(this)
        entrada.hint = "Digite sua mensagem..."
        entrada.setHintTextColor(Color.LTGRAY)
        entrada.setTextColor(branco)
        entrada.setSingleLine(true)
        layout.addView(entrada)

        val enviar = Button(this)
        enviar.text = "Enviar"
        enviar.setTextColor(branco)
        enviar.setBackgroundColor(roxo)

        enviar.setOnClickListener {
            val mensagem = entrada.text.toString().trim()
            if (mensagem.isNotEmpty()) {
                conversa.append("\\n\\nVocê: $mensagem")
                conversa.append("\\nNOVA IA: A inteligência será conectada nas próximas etapas.")
                entrada.text.clear()
                rolagem.post {
                    rolagem.fullScroll(ScrollView.FOCUS_DOWN)
                }
            }
        }

        layout.addView(enviar)
        setContentView(layout)
    }
}
