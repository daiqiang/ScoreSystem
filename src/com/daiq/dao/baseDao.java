package com.daiq.dao;

import javax.annotation.Resource;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Component;

@Component("baseDao")
public class baseDao  implements InitializingBean{

	@Resource(name="sqlMapClient_2")
	org.springframework.orm.ibatis.SqlMapClientTemplate sqlMapClient2;
	
	org.springframework.orm.ibatis.SqlMapClientTemplate sqlMapClient1;

	public SqlMapClientTemplate getClient() {
		return this.sqlMapClient1;
	}

	public void afterPropertiesSet() throws Exception {
		sqlMapClient1  = sqlMapClient2;
	}
}
