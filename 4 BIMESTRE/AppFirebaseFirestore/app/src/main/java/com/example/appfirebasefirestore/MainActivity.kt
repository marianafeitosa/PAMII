package com.example.appfirebasefirestore

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.appfirebasefirestore.ui.theme.AppFirebaseFirestoreTheme
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.size



class MainActivity : ComponentActivity() {
    // Inicializa o Firestore
    val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppFirebaseFirestoreTheme {
                // Um contêiner de superfície usando a cor de fundo do tema
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    App(db)
                }
            }
        }
    }
}

@Composable
fun App(db: FirebaseFirestore) {
    var nome by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var clientes by remember { mutableStateOf(listOf<String>()) }

    Column(
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Text(text = "App Firebase Firestore")
        }

        // Campo de entrada para Nome
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.3f)) {
                Text(text = "Nome:")
            }
            Column {
                TextField(
                    value = nome,
                    onValueChange = { nome = it },
                    placeholder = { Text(text = "Digite seu nome") }
                )
            }
        }

        // Campo de entrada para Telefone
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(modifier = Modifier.fillMaxWidth(0.3f)) {
                Text(text = "Telefone:")
            }
            Column {
                TextField(
                    value = telefone,
                    onValueChange = { telefone = it },
                    placeholder = { Text(text = "Digite seu telefone") }
                )
            }
        }

        // Exibir nome e turma
        Text(text = "Mariana Akemi -  3° DS AMS", modifier = Modifier.padding(16.dp))

        // Adicionar imagem personalizada
        Image(
            painter = painterResource(R.drawable.form),
            contentDescription = "Imagem personalizada",
            modifier = Modifier.size(128.dp).padding(16.dp)
        )

        // Botão para enviar dados
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(onClick = {
                // Cria um mapa para a nova pessoa
                val pessoa = hashMapOf(
                    "nome" to nome,
                    "telefone" to telefone
                )

                // Adiciona um novo documento com um ID gerado
                db.collection("Clientes").add(pessoa)
                    .addOnSuccessListener { documentReference ->
                        Log.d("Firestore", "DocumentSnapshot written with ID: ${documentReference.id}")
                        // Atualiza a lista de clientes após o cadastro
                        fetchClientes(db) { updatedClientes ->
                            clientes = updatedClientes
                        }
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firestore", "Error adding document", e)
                    }
            }) {
                Text(text = "Cadastrar")
            }
        }

        // Exibir a lista de clientes cadastrados
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "Clientes cadastrados:")
            clientes.forEach { cliente ->
                Text(text = cliente, modifier = Modifier.padding(4.dp))
            }
        }
    }
}

// Função para buscar clientes
fun fetchClientes(db: FirebaseFirestore, onResult: (List<String>) -> Unit) {
    db.collection("Clientes")
        .get()
        .addOnSuccessListener { documents ->
            val listaClientes = documents.map { "${it.data["nome"]} - ${it.data["telefone"]}" }
            onResult(listaClientes)
        }
        .addOnFailureListener { exception ->
            Log.w("Firestore", "Error getting documents: ", exception)
            onResult(emptyList())
        }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppFirebaseFirestoreTheme {
        App(Firebase.firestore)
    }
}
