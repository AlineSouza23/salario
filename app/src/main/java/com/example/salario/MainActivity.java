package com.example.salario;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {

    private RadioButton  valeAlimentSim, desc, refeicaoSim,valeTranSim,editPlanoSaudeBasico, editPlanoSaudeStandad, editPlanoSaudeSuper, editPlanoSaudeMaster;
    private EditText  editNumDep, editSalBruto;
    private Button calc;
    private double  salBase, liqui, valeTransporte, valeRefeicao, irrf, inss, valeAlimentacao, salarioLiquido;
    int planoSaude = 0;
    private RadioGroup  groupValeRefeicao, groupValeTransporte, groupValeAlimentacao, groupPlano;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        groupPlano = findViewById(R.id.planos);
        groupValeTransporte = findViewById(R.id.valeTransporte);
        groupValeAlimentacao = findViewById(R.id.valeAlimentacao);
        groupValeRefeicao = findViewById(R.id.valeRefeicao);
        editSalBruto = findViewById(R.id.salarioBruto);
        editPlanoSaudeStandad = findViewById(R.id.planoStandard);
        editNumDep = findViewById(R.id.numeroDependentes);
        editPlanoSaudeBasico = findViewById(R.id.planoBasico);
        editPlanoSaudeSuper = findViewById(R.id.planoSuper);
        valeTranSim = findViewById(R.id.valeTrasporteSim);
        refeicaoSim= findViewById(R.id.valeRefeicaoSim);
        valeAlimentSim= findViewById(R.id.valeAlimentacaoSim);
        calc = findViewById(R.id.calcular);

        Button calc = (Button) findViewById(R.id.calcular);
        calc.setOnClickListener(view -> {
            //Condição para não deixar os campos vazios
            if (editSalBruto.getText().toString().isEmpty()) {
                editSalBruto.setError(getString(R.string.salarioBrutoString));
                Toast.makeText(getBaseContext(), R.string.salarioBrutoString, Toast.LENGTH_SHORT).show();
            } else if (editNumDep.getText().toString().isEmpty()) {
                editNumDep.setError(getString(R.string.numeroDependentesString));
                Snackbar.make(editNumDep, R.string.numeroDependentesString, Snackbar.LENGTH_SHORT).show();
           /* }else if (editPlanoSaudeStandad.equals(null)) {
                editPlanoSaudeStandad.setError(getString(R.string.planoMaster));
                Toast.makeText(getBaseContext(), R.string.planoStandard, Toast.LENGTH_SHORT).show();
            }else if (editPlanoSaudeBasico.equals(null)){
                    editPlanoSaudeBasico.setError(getString(R.string.planoBasico));
                    Toast.makeText(getBaseContext(), R.string.planoBasico, Toast.LENGTH_SHORT).show();
            }else if (editPlanoSaudeSuper.equals(null)) {
                editPlanoSaudeSuper.setError(getString(R.string.planoSuper));
                Toast.makeText(getBaseContext(), R.string.planoSuper, Toast.LENGTH_SHORT).show();
            } else if (editPlanoSaudeMaster.equals(null)){
                    editPlanoSaudeMaster.setError(getString(R.string.planoMaster));
                    Toast.makeText(getBaseContext(), R.string.planoMaster, Toast.LENGTH_SHORT).show();           } else if (editPlanoSaudeStandad.isChecked()) {
                editPlanoSaudeStandad.setError(getString(R.string.planoStandard));
                Toast.makeText(getBaseContext(), R.string.planoStandard, Toast.LENGTH_SHORT).show();
            } else if (editPlanoSaudeBasico.isChecked()) {
                editPlanoSaudeBasico.setError(getString(R.string.planoBasico));
                Toast.makeText(getBaseContext(), R.string.planoBasico, Toast.LENGTH_SHORT).show();
            } else if (editPlanoSaudeSuper.isChecked()){
                editPlanoSaudeSuper.setError(getString(R.string.planoSuper));
                Toast.makeText(getBaseContext(), R.string.planoSuper, Toast.LENGTH_SHORT).show();
            }else if (editPlanoSaudeMaster.isChecked()){
                editPlanoSaudeMaster.setError(getString(R.string.planoMaster));
                Toast.makeText(getBaseContext(), R.string.planoMaster, Toast.LENGTH_SHORT).show();
*/
            }else{

                double salarioBruto;
                EditText edit_sal_bruto = (EditText) findViewById(R.id.salarioBruto);
                salarioBruto = Double.parseDouble(edit_sal_bruto.getText().toString());
                RadioGroup radioGroup = (RadioGroup) findViewById(R.id.planos);

                switch (groupPlano.getCheckedRadioButtonId()) {
                    case R.id.planoStandard:
                        if (salarioBruto <= 3000)
                            planoSaude = (60);
                        else
                            planoSaude = (80);
                        break;
                    case R.id.planoMaster:
                        if (salarioBruto <= 3000)
                            planoSaude = (80);
                        else
                            planoSaude = (110);
                        break;
                    case R.id.planoSuper:
                        if (salarioBruto <= 3000)
                            planoSaude = (95);
                        else
                            planoSaude = (135);
                        break;
                    case R.id.planoBasico:
                        if (salarioBruto <= 3000)
                            planoSaude = (130);
                        else
                            planoSaude = (180);
                        break;
                }

                //Vale Alimentação
                if (groupValeAlimentacao.getCheckedRadioButtonId() == R.id.valeAlimentacaoSim)
                    if (salarioBruto <= 3000)
                        valeAlimentacao = 15.00;
                    else if (salarioBruto >= 3000.01 && salarioBruto <= 5000.00)
                        valeAlimentacao = 25.00;
                    else
                        valeAlimentacao = 35.00;
                else
                    valeAlimentacao = 0;

                double dependentes = Double.parseDouble(editNumDep.getText().toString());

                if (groupValeTransporte.getCheckedRadioButtonId() == R.id.valeTrasporteSim)
                    valeTransporte = salarioBruto * 0.06;
                else
                    valeTransporte = 0;

                //INSS
                if (salarioBruto <= 1045)
                    inss = 0.075 * salarioBruto;
                else if (salarioBruto < 2089.6)
                    inss = (0.09 * salarioBruto) - 15.68;
                else if (salarioBruto < 3134.40)
                    inss = (0.12 * salarioBruto) - 78.38;
                else if (salarioBruto < 6101.06)
                    inss = (0.14 * salarioBruto) - 141.07;
                else
                    inss = 713.08;

                //IRRF
                salBase = salarioBruto - inss - (189.59 * dependentes);
                if (irrf <= 1903.98)
                    irrf = salBase * 0.00 - 0.00;

                else if (salBase < 2826.65)
                    irrf = salBase * 0.075 - 142.80;

                else if (salBase < 3751.05)
                    irrf = salBase * 0.15 - 354.80;

                else if (salBase < 4664.68)
                    irrf = salBase * 0.225 - 636.13;
                else
                    irrf = salBase * 0.275 - 869.36;

                //Vale Refeição
                if (groupValeRefeicao.getCheckedRadioButtonId() == R.id.valeRefeicaoSim)
                    if (salarioBruto <= 3000)
                        valeRefeicao = 22 * 2.60;
                    else if (salarioBruto >= 3000.01 && salarioBruto <= 5000.00)
                        valeRefeicao = 22 * 3.65;
                    else
                        valeRefeicao = 22 * 6.50;

                //Salário Líquido
                salarioLiquido = salarioBruto - inss - valeTransporte - valeRefeicao - valeAlimentacao - irrf - planoSaude;
                liqui = 1 - salarioLiquido / salarioBruto;
                        liqui = liqui * 100;

                Intent intent = new Intent(MainActivity.this, Calculo.class);
                intent.putExtra("SalarioBruto", salarioBruto);
                intent.putExtra("PlanoSaude", planoSaude);
                intent.putExtra("INSS", inss);
                intent.putExtra("IRRF", irrf);
                intent.putExtra("ValeTransporte", valeTransporte);
                intent.putExtra("ValeAlimentacao", valeAlimentacao);
                intent.putExtra("ValeRefeicao", valeRefeicao);
                intent.putExtra("SalarioLiquido", salarioLiquido);
                startActivity(intent);
                finish();

            }
        });
    };

}