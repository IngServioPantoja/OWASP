package co.com.sp.owasp.servicios.impl;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import co.com.sp.owasp.dominio.Usuario;
import co.com.sp.owasp.servicios.UsuarioService;

@Stateless
public class UsuarioServiceImpl implements UsuarioService, Serializable {

	private static final long serialVersionUID = 2807572921740573681L;
	
	@PersistenceContext(unitName = "owaspPU")
	EntityManager em;

	@Override
	public Usuario validarIngresoInseguro(String login, String password) throws Exception {
		try{
			Query namedQuery = this.em.createNativeQuery("SELECT u.* FROM usuario u WHERE u.login = '"+login+"' AND u.password = '"+password+"'");
			Object object = namedQuery.getSingleResult();	
			Object[] objectCodigo = (Object[]) object;
			Usuario usuario = new Usuario();
			usuario.setId(Long.parseLong(objectCodigo[0].toString()));
			usuario.setLogin(objectCodigo[1].toString());
			usuario.setPassword(objectCodigo[1].toString());
			return usuario;
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new Exception("CS Error validando Ingreso de la entidad, causa: "+e.getMessage());
		}
	}
	@Override
	public Usuario validarIngresoSeguro(String login, String password) throws Exception {
		try{
			//Primero se obtiene el usuario por login
			Query namedQuery = this.em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login");
			//JPA nos permite filtrar y encapsular los parametros de entrada
			namedQuery.setParameter("login", login);
			namedQuery.setMaxResults(1);
			Usuario usuario = (Usuario) namedQuery.getSingleResult();
			//Pasado el primer filtro y evitado el primer ataque nos aseguramos de evitar el segundo ataque con equals entre objetos
			if(usuario.getPassword().equals(password)){
				return usuario;
			}else{
				return null;
			}
		}catch(NoResultException e){
			return null;
		}catch(Exception e){
			throw new Exception("CS Error consultando usuario por login, causa: "+e.getMessage());
		}
	}
	

}
