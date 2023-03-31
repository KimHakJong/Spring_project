package com.gant.myhome.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.gant.myhome.domain.ReservationCheck;

@Mapper
public interface ReservationCheckMapper {

	List<ReservationCheck> getTime(Map<String, String> parameter_map);

}
