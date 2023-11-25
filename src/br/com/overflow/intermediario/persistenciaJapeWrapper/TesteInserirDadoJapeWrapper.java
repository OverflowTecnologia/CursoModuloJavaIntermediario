package br.com.overflow.intermediario.persistenciaJapeWrapper;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.wrapper.JapeFactory;
import br.com.sankhya.jape.wrapper.JapeWrapper;

public class TesteInserirDadoJapeWrapper implements AcaoRotinaJava{

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {


		
	}
	
	public void exemploCriacaoDeRegistroJapeWrapper(BigDecimal codParc,BigDecimal vlrCustoAgrupamento,BigDecimal codprod,BigDecimal codEmp) throws Exception {
		
		SessionHandle hnd = null;
		try {
			hnd = JapeSession.open();

			JapeWrapper regitroDAO = JapeFactory.dao("AD_ATUALCUSPROD"); //instancia a tabela em questão
			regitroDAO.create()
				.set("VLRCUSTO", vlrCustoAgrupamento) // o metodo .set() insere os valores dentro da coluna informada		
				.set("CODPROD", codprod)	
				.set("CODEMP", codEmp)	
				.save(); // funcao para salvar o registro
			
		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
