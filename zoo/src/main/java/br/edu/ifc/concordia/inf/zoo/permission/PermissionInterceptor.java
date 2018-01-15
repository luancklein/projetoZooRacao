package br.edu.ifc.concordia.inf.zoo.permission;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.com.caelum.vraptor.AroundCall;
import br.com.caelum.vraptor.Intercepts;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.boilerplate.interceptor.HeadersInterceptor;
import br.com.caelum.vraptor.controller.ControllerMethod;
import br.com.caelum.vraptor.interceptor.AcceptsWithAnnotations;
import br.com.caelum.vraptor.interceptor.SimpleInterceptorStack;
import br.edu.ifc.concordia.inf.zoo.UserSession;
import br.edu.ifc.concordia.inf.zoo.controller.UserController;

@Intercepts(after=HeadersInterceptor.class)
@RequestScoped
@AcceptsWithAnnotations(Permission.class)
public class PermissionInterceptor {
	@Inject private UserSession userSession;
	@Inject private HttpServletResponse httpResponse;
	@Inject private ControllerMethod method;
	@Inject private Result result;
	
	@AroundCall
	public void intercept(SimpleInterceptorStack Stack) {
		if(!this.userSession.isLogged()) {
			this.result.redirectTo(UserController.class).login("Você precisa estar logado para acessar esta pagina!");
		}else if (this.userSession.getUser().getAcesso() >= UserRoles.SYS_ADMIN.getAccessLevel()){
			Stack.next();
		}else {
			Permission perm = this.method.getMethod().getAnnotation(Permission.class);
			if (this.userSession.getUser().getAcesso() >= perm.value().getAccessLevel()) {
				Stack.next();
			} else {
				this.httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				this.result.nothing();
			}
		}
	}
}

