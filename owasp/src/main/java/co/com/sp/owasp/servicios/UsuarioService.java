package co.com.sp.owasp.servicios;

import javax.ejb.Local;

import co.com.sp.owasp.dominio.Usuario;

@Local
public interface UsuarioService {

	public Usuario validarIngresoInseguro(String login, String password) throws Exception ;

	Usuario validarIngresoSeguro(String login, String password) throws Exception;
}
