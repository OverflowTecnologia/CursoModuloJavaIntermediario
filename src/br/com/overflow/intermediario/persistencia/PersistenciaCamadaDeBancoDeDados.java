package br.com.overflow.intermediario.persistencia;

import java.math.BigDecimal;
import java.sql.ResultSet;

import br.com.sankhya.jape.dao.JdbcWrapper;
import br.com.sankhya.jape.sql.NativeSql;
import br.com.sankhya.modelcore.util.EntityFacadeFactory;

public class PersistenciaCamadaDeBancoDeDados {


	//READ
	
	public void exemploBuscaDeRegistrosSQL( BigDecimal nunota) throws Exception {
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);
	      
	      	/*
	       
		        sql.appendSql("SUA QUERY AQUI");
		       
		        OBS: NUNCA utilizar SELECT * nas queries desse metodo, pois ele ira carregar todas as Colunas da tabela sem necessidade.
		       
		        sql.setNamedParameter("NOME_VARIAVEL_NA_QUERY", variavel);
	        
	        
	        */
	      sql.appendSql("SELECT COUNT(*) AS QTD TGFITE WHERE NUNOTA = :NUNOTA");
	      
	      sql.setNamedParameter("NUNOTA", nunota);
	      

	      ResultSet resultSet = sql.executeQuery();
	      while (resultSet.next()) {
	    	  
	    	  /*
				
				vc pode acessar os valoraes das colunas da seguinte forma:
				
				resultSet.getBigDecimal("NOMEDACOLUNA");
	    	  	resultSet.getString("NOMEDACOLUNA");
	    	  	resultSet.getTimestamp("NOMEDACOLUNA");
		
	    	   */

	      } 
	    } finally {
	      jdbc.closeSession();
	    }
	}
	
	//CRATE - UPDATE - DELETE
	
	public void exemploUpdateInsertDeleteSQL( BigDecimal nunota) throws Exception {
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
	      sql.appendSql("UPDATE TGFCAB SET VLRNOTA = 123 WHERE NUNOTA = :NUNOTA");
	      
	      sql.setNamedParameter("NUNOTA", nunota);
	      
	      //Executa Update ou o Delete, dependendo da instrucao
	      sql.executeUpdate();

	    } finally {
	      jdbc.closeSession();
	    }
	}
	
	//READ
	
	public void exemploBuscaComQueryGrande( BigDecimal nunota) throws Exception {
		JdbcWrapper jdbc = null;
	    try {
	      jdbc = EntityFacadeFactory.getDWFFacade().getJdbcWrapper();
	      NativeSql sql = new NativeSql(jdbc);
	      
	      	/*
	       
		        sql.loadSql("Classe na raiz no projeto onde se encontra o aquivo.sql", "exemploQueryGrande.sql");
		       
		        sql.setNamedParameter("NOME_VARIAVEL_NA_QUERY", variavel);
	        
	        
	        */
	      sql.loadSql(PersistenciaCamadaDeBancoDeDados.class, "exemploQueryGrande.sql");
	      
	      sql.setNamedParameter("NUNOTA", nunota);
	      

	      ResultSet resultSet = sql.executeQuery();
	      while (resultSet.next()) {
	    	  
	    	  /*
				
				vc pode acessar os valoraes das colunas da seguinte forma:

				resultSet.getBigDecimal("NOMEDACOLUNA");
	    	  	resultSet.getString("NOMEDACOLUNA");
	    	  	resultSet.getTimestamp("NOMEDACOLUNA");
		
	    	   */

	      } 
	    } finally {
	      jdbc.closeSession();
	    }
	}
	

	//READ
	
	public BigDecimal exemploBuscaSimplificadaSQL( BigDecimal nuNota) throws Exception {
		
		return NativeSql.getBigDecimal("COUNT(*)", "TGFITE", "NUNOTA = ?", new Object[] {nuNota});
		
	}
	
	
}
