package br.com.overflow.intermediario.persistenciaEquipamento;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.extensions.actionbutton.Registro;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class ExcluirEquipamento implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {
		
	Registro[] linhas = contexto.getLinhas();
		
		for(Registro linha : linhas) {
			
			BigDecimal idEquipamento = (BigDecimal) linha.getCampo("IDEQUIP");
			
			excluirEquipamento(idEquipamento);
			
		}

	}
	
	public void excluirEquipamento(BigDecimal idEquipamento) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			dwfFacade.removeEntity("AD_EQUIP", new Object[]{idEquipamento});

		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
