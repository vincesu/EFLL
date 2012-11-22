package com.vincesu.framework.model;

import com.vincesu.framework.entity.SysUser;

public class UserModel extends AbstractModel<SysUser> {

	public boolean isExist(Long id)
	{
		return false;
	}
	
	public boolean isExist(String username)
	{
		return false;
	}

}
