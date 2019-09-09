package com.wipro.jfs.dao;

import java.util.ArrayList;
import java.util.Arrays;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.springframework.stereotype.Repository;

import com.wipro.jfs.entity.PanData;

@Repository
public class PanDao {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	private ArrayList<PanData> initialPanList = new ArrayList<>(Arrays.asList(
			new PanData("AXSSP1122H", Float.valueOf("3.10")), new PanData("APPSA3355P", Float.valueOf("8.50")),
			new PanData("APPXA2244X", Float.valueOf("9.50")), new PanData("AXVPS7766V", Float.valueOf("1.50")),
			new PanData("ASXPS2121S", Float.valueOf("5.10"))));

	private ArrayList<PanData> panList = new ArrayList<>();

	public ArrayList<PanData> getPanList() {
		return panList;
	}

	public void setPanList(ArrayList<PanData> panList) {
		this.panList = panList;
	}

	/**
	 * 
	 * @param panList
	 */
	public void insertPanList() {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			this.initialPanList.stream().forEach(p -> session.persist(p));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public ArrayList<PanData> retreivePanList() {
		Session session = this.sessionFactory.getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(PanData.class);
			setPanList((ArrayList<PanData>) criteria.list());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return getPanList();
	}

	/**
	 * 
	 * @return
	 */
	public int countPanList() {
		Session session = this.sessionFactory.getCurrentSession();
		Criteria cr = session.createCriteria(PanData.class);
		try {
			cr.setProjection(Projections.rowCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Integer.parseInt(cr.list().get(0).toString());
	}

}
