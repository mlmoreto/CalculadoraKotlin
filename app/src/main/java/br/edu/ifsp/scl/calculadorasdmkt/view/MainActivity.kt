package br.edu.ifsp.scl.calculadorasdmkt.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import br.edu.ifsp.scl.calculadorasdmkt.R
import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import kotlinx.android.synthetic.main.toolbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Toolbar
        toolbar.title = getString(R.string.app_name)
        setSupportActionBar(toolbar)

        //Fragment padrão
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.calculadoraFl, CalculadoraBasicaFragment()) //elimina o ciclo de vida dele
            .commit()
    }

    // Cria o menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    object Constantes {
        val CONFIGURACOES_REQUEST_CODE = 0
    }

    // Redireciona ao clicar no item de menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var retorno = false

        when(item.itemId){
            R.id.SairMenuItem ->{
                retorno = true
                finish() // chama as funcoes on pause on destroy
            }
            R.id.configuracoesMenuItem -> {
                retorno = true
                val configuracaoIntent = Intent(this, ConfiguracaoActivity::class.java)

                // após utilização da outra tela, essa será exibida novamente
                startActivityForResult(configuracaoIntent, Constantes.CONFIGURACOES_REQUEST_CODE) // on pause on stop e executa todos os metodos para a criacao da outra: on create on start on resume
            }
        }
        return retorno
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == Constantes.CONFIGURACOES_REQUEST_CODE && resultCode == AppCompatActivity.RESULT_OK){
            // Pegar a configuracao retornada
            val configuracao = data?.getParcelableExtra<Configuracao>(ConfiguracaoActivity.Constantes.CONFIGURACAO)

            if (configuracao!!.leiauteAvancado){
                //Fragment padrão
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.calculadoraFl, CalculadoraAvancadaFragment()) //elimina o ciclo de vida dele
                    .commit()
            }else{
                //Fragment padrão
                supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.calculadoraFl, CalculadoraBasicaFragment()) //elimina o ciclo de vida dele
                    .commit()
            }
        }
    }
}
