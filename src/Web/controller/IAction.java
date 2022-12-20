package Web.controller;

import javax.servlet.http.HttpServletRequest;
import java.sql.Connection;

public interface IAction {
	public String execute(HttpServletRequest request, Connection dbConnection);
}
