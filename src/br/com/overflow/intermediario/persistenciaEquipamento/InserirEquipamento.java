package br.com.overflow.intermediario.persistenciaEquipamento;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class InserirEquipamento implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {

		String nomeEquipamento = (String) contexto.getParam("NOMEEQUIP");
		
		BigDecimal valor = new BigDecimal( (Double)contexto.getParam("VALOR") );
		
		insereRegistro(nomeEquipamento, valor);
		
		insereRegistro("EquipamentoFantasma", new BigDecimal("15000"));
		
	}

	private void insereRegistro(String nomeEquipamento, BigDecimal valor) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();

			DynamicVO dynamicVO = (DynamicVO) dwfFacade.getDefaultValueObjectInstance("AD_EQUIP");
			
			dynamicVO.setProperty("NOME", nomeEquipamento);
			dynamicVO.setProperty("VALOR", valor);
			dynamicVO.setProperty("SETOR", "E");

			dwfFacade.createEntity("AD_EQUIP", (EntityVO) dynamicVO);

		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
