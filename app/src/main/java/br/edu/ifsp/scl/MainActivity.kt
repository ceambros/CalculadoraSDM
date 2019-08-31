package br.edu.ifsp.scl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import br.edu.ifsp.scl.calculadorasdmkt.Calculadora
import br.edu.ifsp.scl.calculadorasdmkt.Operador
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    var concatenaLcd: Boolean = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnSeven.setOnClickListener(this)
        btnEight.setOnClickListener(this)
        btnNine.setOnClickListener(this)
        btnAdicao.setOnClickListener(this)

        // 3 maneira de invocar o método de um botao
        btnThree.setOnClickListener {
            if (!concatenaLcd) {
                lcdtv.text = "";
            }
            lcdtv.append((it as Button).text.toString())
            concatenaLcd = true
        }

        // 4 maneira de invocar um metodo do botao
        btnZero.setOnClickListener(::onClickZeroPontoResultadoDivisao)

    }

    fun onClickZeroPontoResultadoDivisao(view: View?) {
        when (view){
            btnZero -> {
                // Limpa LCD se último clicado foi um operador
                if (!concatenaLcd) {
                    lcdtv.text = ""
                }
                lcdtv.append((view as Button).text.toString())
                concatenaLcd = true
            }
            btnDot -> {
                if (!lcdtv.text.toString().contains(".")){
                    if (!concatenaLcd) {
                        lcdtv.text = "0"
                    }
                    lcdtv.append(".")
                    concatenaLcd = true
                }
            }
            btnEqu -> {
                lcdtv.text = Calculadora.calcula(
                    lcdtv.text.toString().toFloat(),
                    Operador.RESULTADO
                ).toString()
                concatenaLcd = false
            }
            btnDiv -> {
                lcdtv.text = Calculadora.calcula(
                    lcdtv.text.toString().toFloat(),
                    Operador.DIVISAO
                ).toString()
                concatenaLcd = false
            }
        }
    }

    // Primeira maneira de chamar o click do Botao
    override fun onClick(p0: View?) {
        if (p0 == btnSeven || p0 == btnEight || p0 == btnNine) {
            if (!concatenaLcd) {
                lcdtv.text = "";
            }
            lcdtv.append((p0 as Button).text.toString())
            concatenaLcd = true
        }
        else {
            if (p0 == btnAdicao) {
                lcdtv.text = Calculadora.calcula(lcdtv.text.toString().toFloat(), Operador.ADICAO).toString()
                concatenaLcd = false
            }
        }
    }

    fun onClickBtnNum(p0: View?) {
        if (!concatenaLcd) {
            lcdtv.text = "";
        }
        lcdtv.append((p0 as Button).text.toString())
        concatenaLcd = true
    }

    fun onClickBtnSub(p0: View?) {
        if (p0 == btnAdicao) {
            lcdtv.text = Calculadora.calcula(lcdtv.text.toString().toFloat(), Operador.SUBTRACAO).toString()
            concatenaLcd = false
        }
    }
}
