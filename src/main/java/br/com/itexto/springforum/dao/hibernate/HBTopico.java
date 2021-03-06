package br.com.itexto.springforum.dao.hibernate;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import br.com.itexto.springforum.dao.DAOTopico;
import br.com.itexto.springforum.entidades.Assunto;
import br.com.itexto.springforum.entidades.Topico;
import br.com.itexto.springforum.entidades.Usuario;

@Repository
public class HBTopico extends HBDAO<Topico> implements DAOTopico {


	protected Class<Topico> getClazz() {
		return Topico.class;
	}

	public List<Topico> getTopicosPorAutor(Usuario usuario) {
		Query busca = getSession().createQuery("from Topico topico where topico.autor = ?");
		busca.setEntity(0, usuario);
		return busca.list();
	}
	
	public List<Topico> getTopicosPorAssunto(Assunto assunto, int offset, int max) {
		Query busca = getSession().createQuery("from Topico topico where topico.assunto = ?");
		busca.setEntity(0, assunto);
		busca.setMaxResults(max);
		busca.setFirstResult(offset);
		return busca.list();
	}

}
