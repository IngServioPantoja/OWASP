package co.com.sp.owasp.test;

import javax.ejb.EJB;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import co.com.sp.owasp.servicios.impl.UsuarioServiceImpl;

@RunWith(Arquillian.class)
public class PersonServiceTest {

	@Deployment

	public static JavaArchive createTestArchive() {

		return ShrinkWrap.create(JavaArchive.class,"test.jar")

				.addClasses(UsuarioServiceImpl.class);

	}

	@EJB
	private UsuarioServiceImpl usuarioServiceImpl;

	@Test
	public void testAsManager() throws Exception {
		try {
			usuarioServiceImpl.validarIngresoInseguro("", "sa");
			System.out.println("lol");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
