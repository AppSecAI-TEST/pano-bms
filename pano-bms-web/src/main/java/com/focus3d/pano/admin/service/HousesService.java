package com.focus3d.pano.admin.service;

import java.util.List;

import com.focus3d.pano.admin.utils.Page;
import com.focus3d.pano.model.pano_ad;
import com.focus3d.pano.model.pano_project;
import com.focus3d.pano.model.pano_project_house;
import com.focus3d.pano.model.pano_project_space;
import com.focus3d.pano.model.pano_project_style;

public interface HousesService {

	// ----------------------------------------------楼盘管理----------------------------------------------

	// 查询记录数
	public int selHousesCount();

	// 楼盘管理查询
	public List<pano_project> getHouses(Page page);

	// 楼盘管理删除
	public int delHousesbySN(Long SN);

	// 楼盘管理增加
	public void addHouses(pano_project houses);

	// 楼盘管理搜索
	public List<pano_project> selHouses(pano_project houses);

	// 按照SN查询信息
	public List<pano_project> selHousesbySN(Long SN);

	// ----------------------------------------------楼盘-户型----------------------------------------------

	// 户型查询
	public List<pano_project_house> getHousetype(Long PROJECT_SN);

	// 户型删除
	public int delHousetypebySN(Long SN);

	// ----------------------------------------------楼盘-风格----------------------------------------------

	// 风格查询
	public List<pano_project_style> getHousestyle();

	// 风格删除
	public int delHousestyle(Long SN);

	// ----------------------------------------------楼盘-广告----------------------------------------------

	// 广告查询
	public List<pano_ad> getHousead();

	// 广告删除
	public int delHousead(Long SN);

	// ----------------------------------------------楼盘-户型-空间设置----------------------------------------------

	// 根据SN查户型
	public List<pano_project_house> selHousetypebySN(Long SN);

	// 查询空间信息
	public List<pano_project_space> getspace(Long HOUSE_SN);

	// 空间删除
	public int delroomSet(Long SN);

	// 空间添加
	public void addroomSet(pano_project_space space);

}
