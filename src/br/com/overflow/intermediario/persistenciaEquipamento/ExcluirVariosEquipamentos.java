package br.com.overflow.intermediario.persistenciaEquipamento;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.util.FinderWrapper;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class ExcluirVariosEquipamentos implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {
		
		String setor = (String) contexto.getParam("SETOR");

		BigDecimal valor = new BigDecimal((Double) contexto.getParam("VALOR"));

		exemploExclusaoDeVariosRegistro(setor, valor);

	}
	
	
	public void exemploExclusaoDeVariosRegistro(String setor, BigDecimal valor) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			FinderWrapper finder = new FinderWrapper("AD_EQUIP", "SETOR = ? and VALOR > ?", new Object[]{setor, valor});
			dwfFacade. removeByCriteria(finder);

		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
