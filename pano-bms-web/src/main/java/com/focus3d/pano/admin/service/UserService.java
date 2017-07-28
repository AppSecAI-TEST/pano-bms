package com.focus3d.pano.admin.service;

import java.util.List;

import com.focus3d.pano.model.Login;
import com.focus3d.pano.model.User;
import com.focus3d.pano.model.User_Role;


public interface UserService {
	public void saveUser(String nick_name,String name,
			String city,String mobile,String email,String cert_no,int sex,long adder_sn);
	public List<User> getUserList();
	public long selectUserSnById(String cert_no);
	public long selectSnByRole_Name(String role_name);
	public void saveUSn_RSnToU_R(long user_sn,long role_sn);
	public User selectUserByCert_no(String cert_no);
	public void updateUserByCert_no(String nick_name,String city,String mobile,String email,String cert_no);
	public void updateRole_snByUser_sn(long role_sn,long user_sn);
	public void saveLogin(
String login_name,String password,int status,
long user_sn,long adder_sn,String adder_name,String add_time,String cert_no);
	public void updateStatus(String cert_no,int status);
}