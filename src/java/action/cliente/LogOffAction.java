/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.cliente;

/**
 *
 * Action para cerrar sesion
 *
 * @author Jorge
 */
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Result;

public class LogOffAction {

    public LogOffAction() {
    }

    @Action(value = "LogOff", results = {
        @Result(name = "SUCCESS", location = "/index.jsp")})
    public String execute() {

        //Limpiamos la sesion activa
        ActionContext.getContext().getSession().clear();
        return "SUCCESS";
    }
}