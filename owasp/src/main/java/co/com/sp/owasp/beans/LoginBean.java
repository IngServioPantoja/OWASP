package co.com.sp.owasp.beans;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import co.com.sp.owasp.servicios.UsuarioService;

@ManagedBean
@ViewScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = -5992119866159438146L;

	// @ManagedProperty(value="#{sessionBean}")
	// private SessionBean sessionBean;
	
	@EJB private UsuarioService usuarioService;

	private String username = "";
	private String password = "";

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LoginBean() {

	}

	@PostConstruct
	public void inicializar() {

	}

	public String validarIngresoInseguro() {
		String retorno = "/loginInseguro";
		try {

			String msg;
			if (usuarioService.validarIngresoInseguro(username, password)!=null) {
				msg = "Usuario logueado exitosamente Bienvenido@";
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Session", msg);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, facesMessage);
				retorno = "/private/index?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inicianso sessión", "El usuario o contraseña son incorrectos"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inicianso sessión: ", e.getMessage()));
		}
		return retorno;
	}

	public String validarIngresoSeguro() {
		String retorno = "/loginSeguro";
		try {

			String msg;
			if (usuarioService.validarIngresoSeguro(username, password)!=null) {
				msg = "Usuario logueado exitosamente Bienvenido@";
				FacesMessage facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "Session", msg);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				facesContext.addMessage(null, facesMessage);
				retorno = "/private/index?faces-redirect=true";
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inicianso sessión", "El usuario o contraseña son incorrectos"));

			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error inicianso sessión: ", e.getMessage()));
		}
		return retorno;
	}


}