package com.gant.myhome.hakjong.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.gant.myhome.hakjong.domain.Memo;

@Mapper
public interface MemoMapper {

	public List<Memo> getMemoList(String id);

	public int add(Memo memo);

	public Memo getMemoOne(int num);

	public int update(Memo memo);

	public int getMemoNum(String id);

}
