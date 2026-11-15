
package com.novaia.app

import android.app.Activity
import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast

class MainActivity : Activity() {

    private lateinit var root: LinearLayout
    private lateinit var scroll: ScrollView
    private lateinit var messages: LinearLayout
    private lateinit var input: EditText

    private val bg = Color.rgb(10, 12, 19)
    private val panel = Color.rgb(21, 25, 37)
    private val purple = Color.rgb(139, 92, 246)
    private val white = Color.WHITE
    private val gray = Color.rgb(165, 171, 190)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.statusBarColor = bg
        window.navigationBarColor = bg

        root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setBackgroundColor(bg)
        }

        root.addView(
            buildHeader(),
            LinearLayout.LayoutParams(-1, dp(72))
        )

        scroll = ScrollView(this).apply {
            isFillViewport = false
            clipToPadding = false
        }

        val content = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(12), dp(18), dp(20))
        }

        content.addView(buildWelcomeCard())
        addSpace(content, 25)

        content.addView(sectionTitle("Ferramentas Minecraft"))
        addSpace(content, 12)

        content.addView(buildFeatureCard(
            "⚔️  Criar addons",
            "Crie poderes, armas, itens e habilidades.",
            "Crie um addon de poderes para Minecraft Bedrock"
        ))

        addSpace(content, 10)

        content.addView(buildFeatureCard(
            "🐉  Criar entidades",
            "Crie monstros, chefes e criaturas.",
            "Crie um dragão chefe para Minecraft Bedrock"
        ))

        addSpace(content, 10)

        content.addView(buildFeatureCard(
            "🛠️  Corrigir addons",
            "Encontre e corrija erros nos seus projetos.",
            "Quero corrigir um addon de Minecraft"
        ))

        addSpace(content, 25)

        content.addView(sectionTitle("Conversa"))
        addSpace(content, 12)

        messages = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
        }

        content.addView(messages)

        addMessage(
            "Olá! Eu sou a NOVA IA. 🤖\n\n" +
            "Estou sendo preparada para ajudar você " +
            "a criar addons incríveis para Minecraft Bedrock.",
            false
        )

        scroll.addView(content)

        root.addView(
            scroll,
            LinearLayout.LayoutParams(
                -1, 0, 1f
            )
        )

        root.addView(buildComposer())

        setContentView(root)
    }

    private fun buildHeader(): View {
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(16), dp(8), dp(16), dp(8))
        }

        val menu = makeButton("☰", panel, white)
        menu.textSize = 22f
        header.addView(menu, LinearLayout.LayoutParams(dp(46), dp(46)))

        menu.setOnClickListener {
            showMenu()
        }

        val titleBox = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(12), 0, 0, 0)
        }

        titleBox.addView(makeText(
            "NOVA IA", 19f, white, true
        ))

        titleBox.addView(makeText(
            "INTELIGÊNCIA PARA MINECRAFT",
            9f, gray, true
        ))

        header.addView(
            titleBox,
            LinearLayout.LayoutParams(0, -2, 1f)
        )

        val newChat = makeButton("+", purple, white)
        newChat.textSize = 25f

        header.addView(
            newChat,
            LinearLayout.LayoutParams(dp(46), dp(46))
        )

        newChat.setOnClickListener {
            newConversation()
        }

        return header
    }

    private fun buildWelcomeCard(): View {
        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(20), dp(22), dp(20), dp(22))
            background = rounded(panel, 22)
        }

        val badge = makeText(
            "✦  SUA IA ESPECIALIZADA",
            11f,
            Color.rgb(190, 160, 255),
            true
        )

        card.addView(badge)
        addSpace(card, 15)

        card.addView(makeText(
            "O que vamos criar hoje?",
            25f, white, true
        ))

        addSpace(card, 10)

        card.addView(makeText(
            "Sua criatividade não tem limites. " +
            "Crie, edite e desenvolva addons " +
            "para Minecraft Bedrock.",
            14f, gray, false
        ))

        addSpace(card, 18)

        val status = makeText(
            "●  NOVA IA EM DESENVOLVIMENTO",
            10f,
            Color.rgb(110, 220, 160),
            true
        )

        card.addView(status)

        return card
    }

    private fun sectionTitle(value: String): View {
        return makeText(value, 17f, white, true)
    }

    private fun buildFeatureCard(
        title: String,
        description: String,
        prompt: String
    ): View {

        val card = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(16), dp(15), dp(16), dp(15))
            background = rounded(panel, 17)
            isClickable = true
            isFocusable = true
        }

        card.addView(makeText(
            title, 16f, white, true
        ))

        addSpace(card, 7)

        card.addView(makeText(
            description, 13f, gray, false
        ))

        card.setOnClickListener {
            input.setText(prompt)
            input.setSelection(input.text.length)
            input.requestFocus()
        }

        return card
    }

    private fun buildComposer(): View {
        val container = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(12), dp(10), dp(12), dp(12))
            setBackgroundColor(bg)
        }

        input = EditText(this).apply {
            hint = "Pergunte algo à Nova IA..."
            setHintTextColor(gray)
            setTextColor(white)
            textSize = 14f
            singleLine = true
            imeOptions = EditorInfo.IME_ACTION_SEND
            setPadding(dp(14), 0, dp(14), 0)
            background = rounded(panel, 18)
        }

        container.addView(
            input,
            LinearLayout.LayoutParams(0, dp(52), 1f)
        )

        val send = makeButton("➤", purple, white)
        send.textSize = 21f

        val sendParams = LinearLayout.LayoutParams(
            dp(52), dp(52)
        )

        sendParams.leftMargin = dp(9)
        container.addView(send, sendParams)

        send.setOnClickListener {
            sendMessage()
        }

        input.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEND) {
                sendMessage()
                true
            } else {
                false
            }
        }

        return container
    }

    private fun sendMessage() {
        val message = input.text.toString().trim()

        if (message.isEmpty()) return

        addMessage(message, true)
        input.text.clear()

        addMessage(
            "Mensagem adicionada à conversa. " +
            "A conexão com o modelo de inteligência " +
            "artificial será implementada na próxima etapa.",
            false
        )
    }

    private fun addMessage(
        value: String,
        isUser: Boolean
    ) {
        val bubble = TextView(this).apply {
            text = value
            textSize = 14f
            setTextColor(white)
            setPadding(dp(15), dp(13), dp(15), dp(13))
            background = rounded(
                if (isUser) Color.rgb(91, 62, 165)
                else panel,
                17
            )
        }

        val params = LinearLayout.LayoutParams(
            if (isUser) dp(290) else -1,
            -2
        )

        params.bottomMargin = dp(10)

        if (isUser) {
            params.gravity = Gravity.END
        }

        messages.addView(bubble, params)

        scroll.post {
            scroll.fullScroll(View.FOCUS_DOWN)
        }
    }

    private fun newConversation() {
        messages.removeAllViews()

        addMessage(
            "Nova conversa iniciada! " +
            "O que vamos criar hoje? 🤖",
            false
        )

        input.setText("")
        Toast.makeText(
            this,
            "Nova conversa iniciada",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showMenu() {
        val options = arrayOf(
            "Nova conversa",
            "Ferramentas Minecraft",
            "Sobre a NOVA IA"
        )

        AlertDialog.Builder(this)
            .setTitle("NOVA IA")
            .setItems(options) { _, which ->
                when (which) {
                    0 -> newConversation()
                    1 -> {
                        scroll.post {
                            scroll.smoothScrollTo(0, dp(400))
                        }
                    }
                    2 -> AlertDialog.Builder(this)
                        .setTitle("Sobre a NOVA IA")
                        .setMessage(
                            "Uma inteligência artificial " +
                            "especializada na criação de " +
                            "addons para Minecraft Bedrock."
                        )
                        .setPositiveButton("Fechar", null)
                        .show()
                }
            }
            .show()
    }

    private fun makeText(
        value: String,
        size: Float,
        color: Int,
        bold: Boolean
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = size
            setTextColor(color)

            if (bold) {
                typeface = Typeface.DEFAULT_BOLD
            }
        }
    }

    private fun makeButton(
        value: String,
        color: Int,
        textColor: Int
    ): TextView {
        return TextView(this).apply {
            text = value
            textSize = 15f
            gravity = Gravity.CENTER
            setTextColor(textColor)
            background = rounded(color, 15)
            isClickable = true
            isFocusable = true
        }
    }

    private fun rounded(
        color: Int,
        radius: Int
    ): GradientDrawable {
        return GradientDrawable().apply {
            setColor(color)
            cornerRadius = dp(radius).toFloat()
        }
    }

    private fun addSpace(
        layout: LinearLayout,
        height: Int
    ) {
        layout.addView(
            View(this),
            LinearLayout.LayoutParams(1, dp(height))
        )
    }

    private fun dp(value: Int): Int {
        return (
            value * resources.displayMetrics.density
        ).toInt()
    }
}

