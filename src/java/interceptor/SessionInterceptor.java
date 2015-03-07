/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package interceptor;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import java.util.Map;

/**
 * Interceptor que borra la sesión actual cuando se ha excedido el tiempo límite
 * de sesión
 *
 * @author Jorge
 */
public class SessionInterceptor extends AbstractInterceptor {

    @Override
    public String intercept(ActionInvocation invocation) throws Exception {
        Map<String, Object> session = invocation.getInvocationContext().getSession();
        if (session.isEmpty()) {
            return "session"; // session is empty/expired
        }
        return invocation.invoke();
    }
}
