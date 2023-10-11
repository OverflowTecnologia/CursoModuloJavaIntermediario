package br.com.overflow.intermediario.persistenciaEquipamento;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.util.FinderWrapper;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class BuscaDeVariosEquipamentos implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {
		
		List<Equipamento> equipList = ExemploBuscaDeVariosRegistros();
		
		String mensagem = "";
		
		for(Equipamento equip : equipList) {
			
			mensagem = mensagem + equip.getIdEquipamento()  + " - " + equip.getNome();

		}
		
		contexto.setMensagemRetorno(mensagem);

	}
	
	
	public List<Equipamento> ExemploBuscaDeVariosRegistros() throws Exception {
		
		List<Equipamento> equipamentoList = new ArrayList<Equipamento>();
		
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			FinderWrapper finderWrapper = new FinderWrapper("AD_EQUIP", "IDEQUIP < 10 ");
			Collection<DynamicVO> colletion = dwfFacade.findByDynamicFinderAsVO(finderWrapper);
			
			for(DynamicVO dynamicVO : colletion) {

				BigDecimal idEquip = dynamicVO.asBigDecimal("IDEQUIP");
				String nome = dynamicVO.asString("NOME");
				
				Equipamento equip = new Equipamento();
				
				equip.setNome(nome);
				equip.setIdEquipamento(idEquip);
				
				equipamentoList.add(equip);

			}
			
			return equipamentoList;
			
		
		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
