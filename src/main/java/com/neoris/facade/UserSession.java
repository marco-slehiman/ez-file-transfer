package com.neoris.facade;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.HttpSession;

@ManagedBean(name = "userSession")
@SessionScoped
public class UserSession extends ManagedBeanBase {
   
   public String closeSession() { 
      HttpSession httpSession = getHttpRequest().getSession();
      httpSession.invalidate();
      return "sessionClosed";
   }

}
