package com.noteanalyzer.security.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.web.savedrequest.SavedRequest;

/**
 * Util methods for Web security.
 */
public class WebUtil {
    private static final String XML_HTTP_REQUEST = "XMLHttpRequest";
    private static final String X_REQUESTED_WITH = "X-Requested-With";

    private static final String CONTENT_TYPE = "Content-type";
    private static final String CONTENT_TYPE_JSON = "application/json";

    public static boolean isAjax(HttpServletRequest request) {
        return XML_HTTP_REQUEST.equals(request.getHeader(X_REQUESTED_WITH));
    }

    public static boolean isAjax(SavedRequest request) {
        return request.getHeaderValues(X_REQUESTED_WITH).contains(XML_HTTP_REQUEST);
    }

    public static boolean isContentTypeJson(SavedRequest request) {
        return request.getHeaderValues(CONTENT_TYPE).contains(CONTENT_TYPE_JSON);
    }
    
   /* public static JwtAuthenticationToken createJWTToken(@NonNull User user){
    	List<GrantedAuthority> authorities = null;
    	if(Collections.isEmpty(user.getRoles())){
    		  authorities = new ArrayList<>();
    	    authorities.add(new SimpleGrantedAuthority("USER"));
    	}else{
    	 authorities = user.getRoles().stream()
                 .map(authority -> new SimpleGrantedAuthority(authority.getRoleName()))
                 .collect(Collectors.toList());
    	}
        UserContext context = UserContext.create(user.getUsername(), authorities);
        
        return new JwtAuthenticationToken(context, context.getAuthorities());
    }
*/
}
