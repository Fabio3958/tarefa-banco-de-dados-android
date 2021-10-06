package com.evangelista.androidbanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        // nesta tela vamo pega dados
        DatabaseHelper dh = new DatabaseHelper(this);
        List<Funcionario> funcionarios = dh.listar();
        // vamo pegar o conteudo dos alunos vindos do banco
        // e meter na listview
        ListView lv = findViewById(R.id.xinfurinfula);
        // usando o adaptadô pra pegar o conteudo do banco
        // e meter na lixschta
        List<Map<String, Object>> funcionariosConvertido = new ArrayList<>();

        for (
                Funcionario a : funcionarios) {
            Map<String, Object> mapa = new HashMap<>();
            mapa.put("nome", a.getNome());
            mapa.put("cpf", a.getCpf());
            mapa.put("rg", a.getRg());
            mapa.put("dataNascimento", a.getDataNascimento());
            mapa.put("funcao", a.getFuncao());
            funcionariosConvertido.add(mapa);
        }

        ListAdapter adaptador = new SimpleAdapter(this,
                funcionariosConvertido,
                R.layout.item,
                new String[]{"nome", "cpf", "rg", "dataNascimento", "funcao"},
                new int[]{R.id.nomeItem, R.id.cpfItem, R.id.rgItem, R.id.dataNascimentoItem, R.id.funcaoItem});
        lv.setAdapter(adaptador);
    }

    public void voltar(View view) {
        Intent i = new Intent(this,
                br.uniso.tarefabanco.MainActivity.class);
        startActivity(i);
    }

}

