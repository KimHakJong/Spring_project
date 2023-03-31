package com.gant.myhome.service;

import java.util.List;

import com.gant.myhome.domain.ReservationItem;

public interface ReservationItemService {

	public int insert(ReservationItem rItem);

	public List<String> getTypeList();

	public List<String> getResourcesByType(String type);


}
