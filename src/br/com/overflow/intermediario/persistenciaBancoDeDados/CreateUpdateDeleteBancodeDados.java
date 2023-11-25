package br.com.overflow.intermediario.persistenciaBancoDeDados;

import br.com.sankhya.extensions.actionbutton.AcaoRotinaJava;
import br.com.sankhya.extensions.actionbutton.ContextoAcao;
import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class CreateUpdateDeleteBancodeDados implements AcaoRotinaJava {

	@Override
	
	public void doAction(ContextoAcao contexto) throws Exception {
		
		exemploUpdateInsertDeleteSQL();

	}
	
	public void exemploUpdateInsertDeleteSQL( ) throws Exception {
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);
	      
	      	/*
	       
		        sql.appendSql("SEU UPDATE, INSERT ou DELETE AQUI");
		        
		        sql.appendSql(INSERT INTO table_name (column_a, column_b) VALUES ("value_a", "value_b"));
		        
		        sql.appendSql( DELETE FROM table_name WHERE condition);
		       
		        sql.setNamedParameter("NOME_VARIAVEL_NA_QUERY", variavel);
	        
	        
	        */
	      //sql.appendSql("UPDATE AD_EQUIP SET NOME = 'EQUIPAMENTO ALTERADO' WHERE IDEQUIP = :IDEQUIP");
	      
	     // sql.appendSql("INSERT INTO AD_EQUIP (IDEQUIP, NOME, VALOR) VALUES (:IDEQUIP, :NOME, :VALOR)");
	      
	      sql.appendSql("DELETE FROM AD_EQUIP WHERE valor >= 50000");
	      
	     // sql.setNamedParameter("IDEQUIP", new BigDecimal(13));
	     // sql.setNamedParameter("NOME", "EQUIPAMENTO GERADO PELO BANCO");
	     // sql.setNamedParameter("VALOR", new BigDecimal(150000));
	      
	      //Executa Update ou o Delete, dependendo da instrucao
	      sql.executeUpdate();

	    } finally {
	      jdbc.closeSession();
	    }
	}

}
