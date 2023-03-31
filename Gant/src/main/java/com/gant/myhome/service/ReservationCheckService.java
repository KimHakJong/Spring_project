package com.gant.myhome.service;

import java.util.List;
import java.util.Map;

import com.gant.myhome.domain.ReservationCheck;

public interface ReservationCheckService {

	List<ReservationCheck> getTime(Map<String, String> parameter_map);

}
