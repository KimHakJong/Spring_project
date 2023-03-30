package com.gant.myhome.hakjong.service;

import java.util.List;

import com.gant.myhome.hakjong.domain.Project;

public interface ProjectService {

	public int create(Project project);

	public List<Project> getProjectList(String id, String p_name,String admin, String position);

	public Project getProjectOne(int p_no);

	public int update(Project project);

	public int delete(int p_no);

}
