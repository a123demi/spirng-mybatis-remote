package com.lm.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class ClientRmiTest {
	private RmiProxyFactoryBean rmiProxyFactoryBean;

	public RmiProxyFactoryBean getRmiProxyFactoryBean() {
		return rmiProxyFactoryBean;
	}

	@Autowired
	public void setRmiProxyFactoryBean(RmiProxyFactoryBean rmiProxyFactoryBean) {
		this.rmiProxyFactoryBean = rmiProxyFactoryBean;
	}
	
}
