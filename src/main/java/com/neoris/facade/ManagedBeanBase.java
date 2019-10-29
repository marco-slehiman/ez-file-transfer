package com.neoris.facade;

import java.io.File;
import java.io.Serializable;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import javax.el.ELResolver;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewRoot;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// TODO: Auto-generated Javadoc
/**
 * The Class ManagedBeanBase.
 */
public class ManagedBeanBase implements Serializable {

    /** The error msg. */
    public static FacesMessage.Severity ERROR_MSG = FacesMessage.SEVERITY_ERROR;
    
    /** The fatal msg. */
    public static FacesMessage.Severity FATAL_MSG = FacesMessage.SEVERITY_FATAL;
    
    /** The info msg. */
    public static FacesMessage.Severity INFO_MSG = FacesMessage.SEVERITY_INFO;
    
    /** The Constant RESOURCE_BUNDLE_NAME. */
    //private static Properties propiedadesSistema = null;
    private static final String RESOURCE_BUNDLE_NAME = "AppResources";
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The warninin msg. */
    public static FacesMessage.Severity WARNININ_MSG = FacesMessage.SEVERITY_WARN;
    
    /** The locale. */
    protected Locale locale;
    
    /** The tipo imagenes validas. */
    protected ArrayList<String> tipoImagenesValidas;

    /**
     * Instantiates a new managed bean base.
     */
    public ManagedBeanBase() {
        this.tipoImagenesValidas = new ArrayList<String>();
        this.tipoImagenesValidas.add("image/gif");
        this.tipoImagenesValidas.add("image/jpg");
        this.tipoImagenesValidas.add("image/png");
    }

    /**
     * Adds the message.
     *
     * @param componentId the component id
     * @param errorKey the error key
     * @param severity the severity
     */
    protected void addMessage(String componentId, String errorKey,
            FacesMessage.Severity severity) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setDetail(getMessageText(facesContext, errorKey));
        message.setSummary(getMessageText(facesContext, errorKey));
        message.setSeverity(severity);
        facesContext.addMessage(componentId, message);
    }

    /**
     * Adds the text message.
     *
     * @param componentId the component id
     * @param error the error
     * @param severity the severity
     */
    protected void addTextMessage(String componentId, String error,
            FacesMessage.Severity severity) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        FacesMessage message = new FacesMessage();
        message.setDetail(error);
        message.setSummary(error);
        message.setSeverity(severity);
        facesContext.addMessage(componentId, message);
    }

    /**
     * Eliminar archivo.
     *
     * @param directorio the directorio
     * @param nombreArchivo the nombre archivo
     */
    public void eliminarArchivo(String directorio, String nombreArchivo) {
        File archivo = new File(directorio, nombreArchivo);
        if (archivo.exists()) {
            archivo.delete();
        }
    }

    /**
     * Gets the external context.
     *
     * @return the external context
     */
    protected ExternalContext getExternalContext() {
        return getFacesContext().getExternalContext();
    }

    /**
     * Gets the faces context.
     *
     * @return the faces context
     */
    protected FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    /**
     * Gets the http response.
     *
     * @return the http response
     */
    protected HttpServletResponse getHttpResponse() {
        return (HttpServletResponse) getExternalContext()
                .getResponse();
    }

    /**
     * Gets the http request.
     *
     * @return the http request
     */
    protected HttpServletRequest getHttpRequest() {
        return (HttpServletRequest) getExternalContext()
                .getRequest();
    }

    /**
     * Gets the locale.
     *
     * @return the locale
     */
    protected Locale getLocale() {
        if (this.locale == null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            UIViewRoot viewRoot = facesContext.getViewRoot();
            this.locale = viewRoot.getLocale();
        }

        return this.locale;
    }

    /**
     * Gets the message text.
     *
     * @param facesContext the faces context
     * @param key the key
     * @return the message text
     */
    private String getMessageText(FacesContext facesContext, String key) {
        ResourceBundle bundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME,
                getLocale());
        return bundle.getString(key);
    }

    /**
     * Gets the real context path.
     *
     * @return the real context path
     */
    protected String getRealContextPath() {
        ServletContext servletContext = getServeletContext();
        return servletContext.getRealPath("/");
    }

    /**
     * Gets the request attribute.
     *
     * @param name the name
     * @return the request attribute
     */
    protected Object getRequestAttribute(String name) {
        return getHttpRequest().getAttribute(name);
    }

    /**
     * Gets the request parameter.
     *
     * @param name the name
     * @return the request parameter
     */
    protected String getRequestParameter(String name) {
        Map<String, String> parameters = getExternalContext()
                .getRequestParameterMap();
        return (String) parameters.get(name);
    }

    /**
     * Gets the request path.
     *
     * @return the request path
     */
    public String getRequestPath() {
        return getExternalContext().getRequestContextPath();
    }

    /**
     * Gets the request path info.
     *
     * @return the request path info
     */
    public String getRequestPathInfo() {
        return getHttpRequest().getPathInfo();
    }

    /**
     * Gets the servelet context.
     *
     * @return the servelet context
     */
    protected ServletContext getServeletContext() {
        return (ServletContext) getExternalContext().getContext();
    }

    /**
     * Gets the system path.
     *
     * @param resource the resource
     * @return the system path
     */
    public String getSystemPath(String resource) {
        return getExternalContext().getRealPath(resource);
    }

    /**
     * Gets the usuario.
     *
     * @return the usuario
     */
    public String getUserName() {
        String userName = null;
        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
            ExternalContext externalContext = facesContext.getExternalContext();
            Principal principal = externalContext.getUserPrincipal();
            userName = principal.getName();
        }

        return userName;
    }

    /**
     * Checks if is messages.
     *
     * @return true, if is messages
     */
    public boolean isMessages() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Iterator<FacesMessage> messages = facesContext.getMessages();
        return messages.hasNext();
    }

    /**
     * Checks if is user in role.
     *
     * @param role the role
     * @return true, if is user in role
     */
    protected boolean isUserInRole(String role) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        return facesContext.getExternalContext().isUserInRole(role);
    }

    /**
     * Retrieve application bean.
     *
     * @param nameBean the name bean
     * @return the object
     */
    public static Object retrieveApplicationBean(String nameBean) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Map<String, Object> appMap = facesContext.getExternalContext().getApplicationMap();
        Object value = appMap.get(nameBean);
        if (value == null) {
            Application application = facesContext.getApplication();
            ELResolver elResolver = application.getELResolver();
            value = elResolver.getValue(facesContext.getELContext(), null,
                    nameBean);
        }
        return value;
    }

    /**
     * Retrieve session bean.
     *
     * @param nameBean the name bean
     * @return the object
     */
    protected Object retrieveSessionBean(String nameBean) {
        Map<String, Object> sessionMap = getExternalContext().getSessionMap();
        return sessionMap.get(nameBean);
    }

    /**
     * Sets the application bean.
     *
     * @param nameBean the name bean
     * @param objetc the objetc
     */
    protected void setApplicationBean(String nameBean, Object objetc) {
        Map<String, Object> application = getExternalContext().getApplicationMap();
        application.put(nameBean, objetc);
    }

    /**
     * Sets the session bean.
     *
     * @param nameBean the name bean
     * @param objetc the objetc
     */
    protected void setSessionBean(String nameBean, Object objetc) {
        Map<String, Object> sessionMap = getExternalContext().getSessionMap();
        sessionMap.put(nameBean, objetc);
    }

}
