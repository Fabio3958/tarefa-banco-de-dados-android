package com.evangelista.androidbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void salvar(View view) {

        EditText nome = findViewById(R.id.nome);
        EditText cpf = findViewById(R.id.cpf);
        EditText rg = findViewById(R.id.rg);
        EditText dataNascimento = findViewById(R.id.dataNascimento);
        EditText funcao = findViewById(R.id.funcao);


        // pegando conteúdos dos EditTexts
        String nomeDigitado = nome.getText().toString();
        String cpfDigitado = cpf.getText().toString();
        String rgDigitado = rg.getText().toString();
        String dataNascimentoDigitada = dataNascimento.getText().toString();
        String funcaoDigitada = funcao.getText().toString();

        // vamo salvar no banco
        DatabaseHelper dh = new DatabaseHelper(this);
        dh.inserir(nomeDigitado, cpfDigitado, rgDigitado, dataNascimentoDigitada, funcaoDigitada);

        Toast.makeText(this, "Funcionário inserido com sucesso", Toast.LENGTH_SHORT).show();

    }

    public void lista(View view){

        Intent i = new Intent(this, DetailsActivity.class);
        startActivity(i);
    }
    public void pesquisa(View view) {

        EditText pesquisa = findViewById(R.id.pesquisa);

        String nomeDigitado = pesquisa.getText().toString();


        Intent i = new Intent(this, SearchActivity.class);
        i.putExtra("nomePesquisa", nomeDigitado);
        startActivity(i);
    }


}
