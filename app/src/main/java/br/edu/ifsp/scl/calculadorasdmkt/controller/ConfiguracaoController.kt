package br.edu.ifsp.scl.calculadorasdmkt.controller

import br.edu.ifsp.scl.calculadorasdmkt.model.Configuracao
import br.edu.ifsp.scl.calculadorasdmkt.model.ConfiguracaoService
import br.edu.ifsp.scl.calculadorasdmkt.view.ConfiguracaoActivity

class ConfiguracaoController(val view: ConfiguracaoActivity) {
    // Model
    val model: ConfiguracaoService
    init {
        model = ConfiguracaoService(view.applicationContext)// contexto eh um objeto que leva todos os dados do aplicativo: permisoes de arquivo etc
    }

    fun salvaConfiguracao(configuracao: Configuracao){
        model.setConfiguracao(configuracao)
        view.atualizaView(configuracao)
    }

    fun buscaConfiguracao(){
        val configuracao = model.getConfiguracao()
        view.atualizaView(configuracao)

    }
}