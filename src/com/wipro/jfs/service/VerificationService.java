package com.wipro.jfs.service;

import java.util.ArrayList;
import java.util.function.BiFunction;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.wipro.jfs.dao.PanDao;
import com.wipro.jfs.entity.PanData;

@Service
@Transactional
public class VerificationService {

	@Autowired
	PanDao panDao;

	private ArrayList<PanData> panList = new ArrayList<PanData>();

	private BiFunction<PanData, String, Boolean> isPanNo = (o, p) -> o.getPanNo().toUpperCase().equals(p.toUpperCase());
	private Predicate<PanData> isCreditScore = p -> ((Float.compare(p.getCreditScore(), Float.valueOf("5.0"))) > 0)
			? true
			: false;

	/**
	 * 
	 */
	private void setPanList() {
		try {
			if (this.panList.isEmpty()) {
				if (panDao.countPanList() == 0)
					panDao.insertPanList();
				this.panList = panDao.retreivePanList();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 * @param panNo
	 * @return
	 */
	public boolean verifyPan(String panNo) {
		try {
			if (StringUtils.isEmpty(panNo)) {
				return false;
			}
			setPanList();
			return panList.stream().filter(e -> isPanNo.apply(e, panNo)).findFirst().isPresent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	/**
	 * 
	 * @param panNo
	 * @return
	 */
	public boolean verifyCreditScore(String panNo) {
		try {
			if (StringUtils.isEmpty(panNo)) {
				return false;
			}
			setPanList();
			return panList.stream().filter(e -> isPanNo.apply(e, panNo)).filter(isCreditScore).findFirst().isPresent();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

}
