package com.dat.parking.dao;

import java.util.List;

import com.dat.parking.model.SystemAdminAccount;
import com.dat.parking.model.UserAdminAccount;

public interface SystemAdminAccountDao {
	void persistInformation(SystemAdminAccount systemAdminAccount);
	public List checkAccount(String name,String password);
	
	
	public List adminList(String name);
	public List<SystemAdminAccount> getAdminProfileInformation(String name);

}


