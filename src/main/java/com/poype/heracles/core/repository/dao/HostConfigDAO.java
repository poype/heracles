package com.poype.heracles.core.repository.dao;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface HostConfigDAO {

    List<String> queryAllHostNames();
}
