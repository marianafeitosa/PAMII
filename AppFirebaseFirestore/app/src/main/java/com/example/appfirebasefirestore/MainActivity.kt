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


class MainActivity : ComponentActivity() {
    // Inicializa o Firestore
    private val db: FirebaseFirestore = Firebase.firestore

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
                    }
                    .addOnFailureListener { e ->
                        Log.w("Firestore", "Error adding document", e)
                    }
            }) {
                Text(text = "Cadastrar")
            }
        }

        // Exibir dados do Firestore
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Column {
                db.collection("Clientes")
                    .get()
                    .addOnSuccessListener { documents ->
                        for (document in documents) {
                            Log.d("Firestore", "${document.id} => ${document.data}")
                        }
                    }
                    .addOnFailureListener { exception ->
                        Log.w("Firestore", "Error getting documents: ", exception)
                    }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AppPreview() {
    AppFirebaseFirestoreTheme {
        // Você pode criar uma prévia com dados mock aqui, se necessário
        App(Firebase.firestore)
    }
}
