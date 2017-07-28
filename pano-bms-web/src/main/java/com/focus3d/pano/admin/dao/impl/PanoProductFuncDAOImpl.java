package com.focus3d.pano.admin.dao.impl;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.focus3d.pano.admin.dao.PanoProductFuncDAO;
import com.focus3d.pano.model.PanoProductFunc;
import com.focus3d.pano.model.PanoProductType;
import com.focus3d.pano.model.PanoProjectPackage;
import com.focus3d.pano.model.PanoVender;
import com.focus3d.pano.model.pano_project_style;

@Repository
public class PanoProductFuncDAOImpl  extends BaseDao implements PanoProductFuncDAO{

	/**
	 * 分类查询
	 */
	public List<PanoProductType> getBasics() {
		List<PanoProductType> productList = null;
		try{
			productList=(List<PanoProductType>)getSqlMapClientTemplate().queryForList("pano_bm_basic.getBasics");
			for(PanoProductType t:productList){
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}


	public Long getInsert(String name) {
		Long sn = null;
		try{
		 sn =(Long) getSqlMapClientTemplate().insert("pano_bm_basic.getInsert",name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sn;
	}

	@Override
	public int getDelete(int sn) {
		int sm = 0;
		sm =  getSqlMapClientTemplate().delete("pano_bm_basic.getDelete",sn);
		return sm;
	}

	public int getUpdate(PanoProductType p){
		
		int sm = 0;
		try{
		sm =  getSqlMapClientTemplate().update("pano_bm_basic.getUpdate",p);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sm;
		
	}
	
	
	
	/**
	 * 套餐
	 */

	@Override
	public List<PanoProjectPackage> getBasics1() {
		List<PanoProjectPackage> productList = null;
		try{
			productList=(List<PanoProjectPackage>)getSqlMapClientTemplate().queryForList("pano_bm_basic.getBasics1");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Long getInsert1(String name) {
		Long sn = null;
		try{
		 sn =(Long) getSqlMapClientTemplate().insert("pano_bm_basic.getInsert1",name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sn;
	}

	@Override
	public int getDelete1(int sn) {
		int sm = 0;
		sm =  getSqlMapClientTemplate().delete("pano_bm_basic.getDelete1",sn);
		return sm;
	}
	
	
	public int getUpdate1(PanoProjectPackage p){
		
		int sm = 0;
		try{
		sm =  getSqlMapClientTemplate().update("pano_bm_basic.getUpdate1",p);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sm;
		
	}
	
	/**
	 * 厂家
	 */

	@Override
	public List<PanoVender> getBasics2() {
		List<PanoVender> productList = null;                                                   
		try{
			productList=(List<PanoVender>)getSqlMapClientTemplate().queryForList("pano_bm_basic.getBasics2");
			
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Long getInsert2(String name) {
		Long sn = null;
		try{
		 sn =(Long) getSqlMapClientTemplate().insert("pano_bm_basic.getInsert2",name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sn;
	}

	@Override
	public int getDelete2(int sn) {
		int sm = 0;
		sm =  getSqlMapClientTemplate().delete("pano_bm_basic.getDelete2",sn);
		return sm;
	}
	
	
	public int getUpdate2(PanoVender p){
		
		int sm = 0;
		try{
		sm =  getSqlMapClientTemplate().update("pano_bm_basic.getUpdate2",p);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sm;
		
	}
	
	/**
	 * 风格
	 */

	@Override
	public List<pano_project_style> getBasics3() {
		List<pano_project_style> productList = null;
		try{
			productList=(List<pano_project_style>)getSqlMapClientTemplate().queryForList("pano_bm_basic.getBasics3");
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Long getInsert3(String name) {
		Long sn = null;
		try{
		 sn =(Long) getSqlMapClientTemplate().insert("pano_bm_basic.getInsert3",name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sn;
	}

	@Override
	public int getDelete3(int sn) {
		int sm = 0;
		sm =  getSqlMapClientTemplate().delete("pano_bm_basic.getDelete3",sn);
		return sm;
	}
	
	
		public int getUpdate3(pano_project_style p){
		
		int sm = 0;
		try{
		sm =  getSqlMapClientTemplate().update("pano_bm_basic.getUpdate3",p);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sm;
		
	}
	
	/**
	 * 功能
	 */

	@Override
	public List<PanoProductFunc> getBasics4() {
		List<PanoProductFunc> productList = null;
		try{
			productList=(List<PanoProductFunc>)getSqlMapClientTemplate().queryForList("pano_bm_basic.getBasics4");
		}catch(Exception e){
			e.printStackTrace();
		}
		return productList;
	}

	@Override
	public Long getInsert4(String name) {
		Long sn = null;
		try{
		 sn =(Long) getSqlMapClientTemplate().insert("pano_bm_basic.getInsert4",name);
		}catch(Exception e){
			e.printStackTrace();
		}
		return sn;
	}

	@Override
	public int getDelete4(int sn) {
		int sm = 0;
		sm =  getSqlMapClientTemplate().delete("pano_bm_basic.getDelete4",sn);
		return sm;
	}
	
	public int getUpdate4(PanoProductFunc p){
		
		int sm = 0;
		try{
			System.out.println("4DAO");
		sm =  getSqlMapClientTemplate().update("pano_bm_basic.getUpdate4",p);
		System.out.println("4DAO1");
		}catch(Exception e){
			e.printStackTrace();
		}
		return sm;
		
	}
}
