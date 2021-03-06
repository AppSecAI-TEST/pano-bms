package com.focus3d.pano.usersside.dao;

import java.util.List;

import com.focus3d.pano.model.pano_mem_user;
import com.focus3d.pano.model.pano_user_receive_address;

public interface PersonalDAO {

	// ----------------------------------------------个人中心----------------------------------------------

	// 根据SN查询用户信息
	public pano_mem_user selUserbySN(Long SN);

	// ----------------------------------------------收货地址----------------------------------------------

	// 查询用户收货地址
	public List<pano_user_receive_address> selAddressbyUserSN(Long USER_SN);

	// 新增收货地址
	public void addAddress(pano_user_receive_address address);

	// 删除收货地址
	public int delAddress(Long SN);

	// 根据SN查询收货地址
	public pano_user_receive_address selAddressbySN(Long SN);

	// 修改收货地址
	public void upAddress(pano_user_receive_address address);
}
