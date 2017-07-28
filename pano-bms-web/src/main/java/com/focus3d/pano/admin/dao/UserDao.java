package com.focus3d.pano.admin.dao;

import java.util.List;

import com.focus3d.pano.model.Login;
import com.focus3d.pano.model.User;
import com.focus3d.pano.model.User_Role;

public interface UserDao{

	public void saveUser(User user);
	public List<User> getUserList();
	public long selectUserSnById(String cert_no);
	public long selectSnByRole_Name(String role_name);
	public void saveUSn_RSnToU_R(User_Role user_role);
	public User selectUserByCert_no(String cert_no);
	public void updateUserByCert_no(User user);
	public void updateRole_snByUser_sn(User_Role user_role);
	public void saveLogin(Login login);
	public void updateStatus(Login login);
}
