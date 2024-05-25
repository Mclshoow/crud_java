package br.com.agenda.aplicacao;

import java.util.Date;

import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main 
{
	public static void main(String[] args) 
	{
		ContatoDAO contatoDao = new ContatoDAO();
		
		//Salvar novo contato ---------
		
		/*Contato contato = new Contato();
		contato.setNome("João Terezo");
		contato.setIdade(21);
		contato.setDataCadastro(new Date());
		contatoDao.save(contato);*/
		
		//Atualizar contato -----------
		
		/*Contato contatoEditado = new Contato();
		contatoEditado.setNome("Marcelo Augusto");
		contatoEditado.setIdade(25);
		contatoEditado.setDataCadastro(new Date());
		contatoEditado.setId(1);
		contatoDao.update(contatoEditado); */
		
		//Deletar contato -------------
		
		//contatoDao.deleteById(2);
		
		//Todos os dados do banco de dados agenda/contatos
		
		for(Contato listaContatos : contatoDao.getContatos()) 
		{
			System.out.println("Contato: " + listaContatos.getNome());
		}
	}
}
