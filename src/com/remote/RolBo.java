package com.remote;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.service.RolBeanRemote;

public class RolBo {
	
	private String context;
	private RolBeanRemote rolBean;
	
	public RolBo() throws NamingException {
		super();
		context = "ejb:/IAgro-Server/RolBean!com.service.RolBeanRemote";
		rolBean = (RolBeanRemote)InitialContext.doLookup(context);
	}
	
	

}
