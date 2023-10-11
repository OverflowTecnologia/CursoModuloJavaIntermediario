package br.com.overflow.intermediario.persistenciaEquipamento;

import java.math.BigDecimal;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.EntityFacade;
import br.com.sankhya.jape.bmp.PersistentLocalEntity;
import br.com.sankhya.jape.core.JapeSession;
import br.com.sankhya.jape.core.JapeSession.SessionHandle;
import br.com.sankhya.jape.vo.DynamicVO;
import br.com.sankhya.jape.vo.EntityVO;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class BuscaEquipamento implements AcaoRotinaJava {

	@Override
	public void doAction(ContextoAcao contexto) throws Exception {

		Equipamento equipamentoPopulado = buscarEquipamento(new BigDecimal("5"));
		
		contexto.setMensagemRetorno(" Nome Equipamento = " + equipamentoPopulado.getNome() + ", Valor = " + equipamentoPopulado.getValor());	
		
	}

	public Equipamento buscarEquipamento(BigDecimal idEquipamento) throws Exception {
		SessionHandle hnd = null;

		try {
			hnd = JapeSession.open();
			EntityFacade dwfFacade = EntityFacadeFactory.getDWFFacade();
			PersistentLocalEntity persistentLocalEntity = dwfFacade.findEntityByPrimaryKey("AD_EQUIP", new Object[] {idEquipamento});
			EntityVO entityVO = persistentLocalEntity.getValueObject();
			DynamicVO dynamicVO = (DynamicVO) entityVO;
			
			/*
				Dentro da variavel dynamicVO esta todas as colunas do registro que voce consultou.
				vc pode acessar da seguinte forma :
				
				dynamicVO.asBigDecimal("NOMEDACOLUNA");
				dynamicVO.asString("NOMEDACOLUNA");
				dynamicVO.asTimestamp("NOMEDACOLUNA");
			
			*/
			
			BigDecimal valor = dynamicVO.asBigDecimal("VALOR");
			String nome = dynamicVO.asString("NOME");
			
			Equipamento equip = new Equipamento();
			
			equip.setNome(nome);
			equip.setValor(valor);
			
			return equip;
			
		
		} finally {
			JapeSession.close(hnd);
		}
		
	}

}
