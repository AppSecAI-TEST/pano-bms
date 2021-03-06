package com.focus3d.pano.filter;

import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.focus3d.pano.auth.service.PanoResourceService;
import com.focus3d.pano.auth.service.PanoRoleResourceService;
import com.focus3d.pano.auth.service.PanoUserRoleService;
import com.focus3d.pano.model.PanoLoginModel;
import com.focus3d.pano.model.PanoResourceModel;
import com.focus3d.pano.model.PanoRoleResourceModel;
import com.focus3d.pano.model.PanoUserRoleModel;
import com.focustech.cief.cop.ws.auth.Auth;
import com.focustech.cief.cop.ws.auth.AuthHolder;
import com.focustech.common.utils.EncryptUtil;
import com.focustech.common.utils.ListUtils;
import com.focustech.common.utils.TCUtil;
/**
 *
 * *
 * @author lihaijun
 *
 */
public class LoginFilter extends AbstractFilter {
	public static final String SESSION_KEY = "login";
	public static final String LOGIN_PAGE_NAME = "user/login";
	//动态链接
	protected static final String[] DYNAMIC_RESOURCES = {
		"/index.html"
		,"/monitor.html"
		,"/crossdomain.xml"
		,"/favicon.ico"
		,"/cross-domain-policy.xml"
		,"/cross-domain-policy.dtd"
		, "/common/*"
		,"/index"
		, "/qrcode/*"
		,"/sms/send"
		, "/captchas/*"
		, "/" + LOGIN_PAGE_NAME
		, "/user/logout"
		, "/user/register*"
		, "/user/login*"
		, "/user/password-find"
		, "/f/*"
		, "/fp/*"
		, "/out/*"
	};
	public static Auth auth = new Auth();
	
	@Value("${rpc.fs.domain}")
	private String fileServerDomain;
	@Autowired
	private PanoUserRoleService<PanoUserRoleModel> userRoleService;
	@Autowired
	private PanoRoleResourceService<PanoRoleResourceModel> roleResourceService;
	@Autowired
	private PanoResourceService<PanoResourceModel> resourceService;
	@Override
	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain fc) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		HttpSession session = request.getSession();
		Object sessinObj = session.getAttribute(SESSION_KEY);
		String servletPath = request.getServletPath();
		boolean isPass = false;
		if(isNotNeedAuthCheckUrl(servletPath, request)){
			if("/home/index".equals(servletPath)){
				//首页会话设置用户信息
				RequestThreadLocal.setLoginInfo(sessinObj);
				PanoLoginModel loginInfo = RequestThreadLocal.getLoginInfo();
				if(loginInfo != null){
					req.setAttribute(SESSION_KEY, loginInfo);
				}
			}
			isPass = true;
		} else {
			if(sessinObj == null) {
				response.sendRedirect("/" + LOGIN_PAGE_NAME);
			} else {
				RequestThreadLocal.setLoginInfo(sessinObj);
				req.setAttribute("usn", EncryptUtil.encode(((PanoLoginModel)sessinObj).getUserSn()));
				req.setAttribute("fserver", fileServerDomain);
				isPass = isAuthedUrl(servletPath, sessinObj);
			}
		}
		if(isPass){
			AuthHolder.setAuth(auth);
			fc.doFilter(req, resp);
		} else {
			response.setStatus(403);
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			response.getOutputStream().print("无权限访问");
		}
	}
	
	/**
	 * 是否是不需要登录的url
	 * *
	 * @param servletPath
	 * @return
	 */
	public boolean isNotNeedAuthCheckUrl(String servletPath, HttpServletRequest request){
		int resourceType = TCUtil.iv(request.getAttribute("resourceType"));
		boolean flag = (resourceType == 1);
		if(!flag){
			for(String resource : DYNAMIC_RESOURCES){
				if(resource.contains("*")){
					Pattern pattern = Pattern.compile("^" + resource.replace("*", ".*"));
					Matcher matcher = pattern.matcher(servletPath);
					if(matcher.matches()){
						flag = true;
						break;
					}
				} else if(servletPath.equalsIgnoreCase(resource)){
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	/**
	 * 是否已经授权了的url
	 * *
	 * @param servletPath
	 * @return
	 */
	public boolean isAuthedUrl(String servletPath, Object sessinObj){
		boolean isPass = false;
		if(sessinObj != null) {
			//登录用户验证功能菜单权限
			if(sessinObj instanceof PanoLoginModel){
				if(servletPath.equals("/index")){
					isPass = true;
				} else {
					PanoLoginModel loginInfo = (PanoLoginModel)sessinObj;
					Long userId = loginInfo.getUserSn();
					if(userId != null){
						List<PanoUserRoleModel> roles = userRoleService.listByUserId(userId);
						if(ListUtils.isNotEmpty(roles)){
							//后续添加到缓存
							List<PanoRoleResourceModel> resources = roleResourceService.listByRoleId(roles.get(0).getRoleSn());
							for (PanoRoleResourceModel agentRoleResource : resources) {
								PanoResourceModel resource = resourceService.getBySn(agentRoleResource.getResourceSn());
								if(servletPath.startsWith(resource.getResourceInterface())){
									isPass = true;
									break;
								}
							}
						}
					}
				
				}
			}
		}
		return isPass;
	}

	@Override
	public void init(FilterConfig fcg) throws ServletException {
		auth.setUsername("system");
		auth.setUserSn(-1L);
		auth.setFromSubSystem("focus3d_agent");
	}
}
