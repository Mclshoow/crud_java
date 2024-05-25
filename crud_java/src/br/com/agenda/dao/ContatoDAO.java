package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

public class ContatoDAO 
{
	public void save(Contato contato)
	{
		String sql = "INSERT INTO contatos(nome, idade, datacadastro) VALUES (?, ?, ?)";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try 
		{
			//Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();
			
			//Criar uma PreparedStatement para executar uma query
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			//Adicionar valores que são esperados
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			
			//Executar a query
			pstm.execute();
			
			System.out.println("Contato salvo com sucesso!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
		//Fechar as conexões
			try 
			{
				if(pstm != null) 
				{
					pstm.close();
				}
				
				if(conn != null) 
				{
					conn.close();
				}
			} 
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	public List<Contato> getContatos()
	{
		String sql = "SELECT * FROM contatos";
		
		List<Contato> listaContatos = new ArrayList<Contato>();
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		//Classe que vai recuparar os dados do banco ***SELECT***
		ResultSet rset = null;
		
		try 
		{
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			rset = pstm.executeQuery();
			
			while (rset.next()) 
			{
				Contato contato = new Contato();
				
				//Recuperar id, nome, idade e data
				contato.setId(rset.getInt("id"));
				contato.setNome(rset.getString("nome"));
				contato.setIdade(rset.getInt("idade"));
				contato.setDataCadastro(rset.getDate("datacadastro"));
				
				listaContatos.add(contato);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(rset != null) 
				{
					rset.close();
				}
				if(pstm != null)
				{
					pstm.close();
				}
				if(conn != null) 
				{
					conn.close();
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
		
		return listaContatos;
	}
	
	public void update(Contato contato) 
	{
		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? "+
		"WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try 
		{ 
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));
			pstm.setInt(4, contato.getId());
			
			//Executar query
			pstm.execute();
			
			System.out.println("Contato atualizado com sucesso!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try 
			{
				if(pstm != null)
				{
					pstm.close();
				}
				if(conn != null) 
				{
					conn.close();
				}
			}
			catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	}

	public void deleteById(int id)
	{
		String sql = "DELETE FROM contatos WHERE id = ?";
		
		Connection conn = null;
		PreparedStatement pstm = null;
		
		try 
		{
			conn = ConnectionFactory.createConnectionToMySQL();
			
			pstm = (PreparedStatement) conn.prepareStatement(sql);
			
			pstm.setInt(1, id);
			
			pstm.execute();
			
			System.out.println("Contato deletado com sucesso!");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(pstm != null)
				{
					pstm.close();
				}
				
				if(conn != null)
				{
					conn.close();
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}