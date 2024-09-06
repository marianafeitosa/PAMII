package com.example.appsqlite

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.ComponentActivity

class RespostaActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resposta)

        val edtNome: EditText = findViewById(R.id.edtNome)
        val edtEndereco: EditText = findViewById(R.id.edtEndereco)
        val edtBairro: EditText = findViewById(R.id.edtBairro)
        val edtCep: EditText = findViewById(R.id.edtCep)
        val edtTelefone: EditText = findViewById(R.id.edtTelefone)
        val btnConfirmar: Button = findViewById(R.id.btnConfirmar)

        edtNome.setText(intent.getStringExtra("Nome"))
        edtEndereco.setText(intent.getStringExtra("Endereco"))
        edtBairro.setText(intent.getStringExtra("Bairro"))
        edtCep.setText(intent.getStringExtra("CEP"))
        edtTelefone.setText(intent.getStringExtra("Telefone"))

        val db = DBHelper(this, null)
        btnConfirmar.setOnClickListener {
            db.addPessoa(
                edtNome.text.toString(),
                edtEndereco.text.toString(),
                edtBairro.text.toString(),
                edtCep.text.toString(),
                edtTelefone.text.toString()
            )
            finish()
        }
    }
}
