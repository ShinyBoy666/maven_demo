package com.springboot.common.service;

import org.springframework.stereotype.Service;

import com.springboot.common.pojo.LogDO;
import com.springboot.common.pojo.PageDO;
import com.springboot.common.util.Query;

@Service
public interface LogService {
	void save(LogDO logDO);
	PageDO<LogDO> queryList(Query query);
	int remove(Long id);
	int batchRemove(Long[] ids);
}