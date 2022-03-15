package com.example.salario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Calculo extends AppCompatActivity {

    private double irrf = 0;
    private double valeTransportedou = 0;
    private double valeAlmentacaodou = 0;
    private double salarioBruto = 0;
    private int planoSaude = 0;
    private double salarioLiquido = 0;
    private double valeRefeicaodou = 0;
    private double inss = 0;
    private TextView  sLiquido,salarioBrutoT, planSaude,  valeTransporte, vIrrf,valeRefeicao,  valeAlimentacao,vInss;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        salarioBrutoT = findViewById(R.id.salarioBrutoId);
        planSaude = findViewById(R.id.planoId);
        valeAlimentacao = findViewById(R.id.valeAlimentacaoId);
        valeTransporte = findViewById(R.id.valeTransporteId);
        valeRefeicao = findViewById(R.id.valeRefeicaoId);
        vIrrf = findViewById(R.id.irrfId);
        vInss = findViewById(R.id.inssId);
        sLiquido = findViewById(R.id.salarioLiquidoId);



        salarioBruto = getIntent().getDoubleExtra("SalarioBruto", 0);
        planoSaude = getIntent().getIntExtra("PlanoSaude", 0);
        inss = getIntent().getDoubleExtra("INSS", 0);
        irrf = getIntent().getDoubleExtra("IRRF", 0);
        valeTransportedou = getIntent().getDoubleExtra("ValeTransporte",0);
        valeAlmentacaodou = getIntent().getDoubleExtra("ValeAlimentacao",0);
        valeRefeicaodou = getIntent().getDoubleExtra("ValeRefeicao",0);
        salarioLiquido = getIntent().getDoubleExtra("SalarioLiquido", 0);

        salarioBrutoT.setText("Esse é o seu Salário Bruto: ("+salarioBruto+")");
        planSaude.setText("Esse é o seu Plano de Saúde: ("+planoSaude+")");
        vIrrf.setText("Este é o seu irrf: ("+irrf+")");
        vInss.setText("Este é o seu inss: ("+inss+")");
        valeAlimentacao.setText("Vale Alimententação: ("+valeAlmentacaodou+")");
        valeTransporte.setText("Vale Transporte: ("+valeTransportedou+")");
        valeRefeicao.setText("Vale Refeição: ("+valeRefeicaodou+")");
        sLiquido.setText("Esse é o seu Salário Líquido: ("+salarioLiquido+")");


    }
}