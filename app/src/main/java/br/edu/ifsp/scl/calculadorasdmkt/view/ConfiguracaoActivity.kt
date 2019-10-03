package br.edu.ifsp.scl.calculadorasdmkt.view

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.calculadorasdmkt.R
import br.edu.ifsp.scl.calculadorasdmkt.controller.ConfiguracaoController
import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.model.Separador
import kotlinx.android.synthetic.main.activity_configuracao.*
import kotlinx.android.synthetic.main.toolbar.*

class ConfiguracaoActivity: AppCompatActivity() {
    object Constantes{
        // Chave de retorno para a MainActivity
        val CONFIGURACAO = "CONFIGURACAO"
    }

    // Referencia para Controller
    lateinit var configuracaoController: ConfiguracaoController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracao)

        // Toolbar
        toolbar.title = "Configuração"
        setSupportActionBar(toolbar)


        // Chama controller e atualiza view
        configuracaoController = ConfiguracaoController(this)
        configuracaoController.buscaConfiguracao()
    }

    // Funcao chamada pelo controller depois de acessar a model
    fun atualizaView(configuracao: Configuracao){
        //ajusta leiaute conforme a configuracao
        leiauteSpn.setSelection( if(configuracao.leiauteAvancado) 1 else 0)
        separadorRg.check(if(configuracao.separador == Separador.PONTO)
            R.id.pontoRb
        else
            R.id.virgulaRb
        )

        // SETAR RESULTADO PARA MAIN ACTIVITY
        setResult(AppCompatActivity.RESULT_OK, Intent().putExtra(Constantes.CONFIGURACAO, configuracao))
    }

    fun onClickSalvaConfiguracao(v: View){
        // Pega os dados da tela: 0 - Basico  1 - Avancada
        val leiauteAvancado = leiauteSpn.selectedItemPosition == 1
        val separador = if (pontoRb.isChecked) Separador.PONTO else Separador.VIRGULA

        // Criar um objeto configuracao
        val novaConfiguracao = Configuracao(leiauteAvancado, separador)

        // Chamar o Controller para salvar]
        configuracaoController.salvaConfiguracao(novaConfiguracao)

        Toast.makeText(this, "Configuração salva!", Toast.LENGTH_SHORT).show()

        // Chamar a main para nao ficar na tela de configuracoes
    }
}