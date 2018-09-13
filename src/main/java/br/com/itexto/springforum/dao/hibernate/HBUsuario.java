package br.com.itexto.springforum.dao.hibernate;

import org.apache.commons.codec.digest.DigestUtils;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import br.com.itexto.springforum.dao.DAOUsuario;
import br.com.itexto.springforum.entidades.Usuario;

@Repository("daoUsuario")
@Transactional(propagation=Propagation.SUPPORTS)
public class HBUsuario extends HBDAO<Usuario> implements DAOUsuario{

	public Usuario getUsuario(String login, String senha) {
		Query query = getSession().createQuery("from Usuario u where u.login = ? and u.hashSenha = ?");
		query.setString(0, login);
		query.setString(1, DigestUtils.sha256Hex(senha));
		return (Usuario) query.uniqueResult();				   
	}

	public Usuario getUsuario(String login) {
		Query query = getSession().createQuery("from Usuario u where u.login = ?");
		query.setString(0, login);
		return (Usuario) query.uniqueResult();
	}

	@Override
	protected Class<Usuario> getClazz() {
		return Usuario.class;
	}

}
