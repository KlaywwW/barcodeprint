package com.starvincci.barcodeprint.read.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.starvincci.barcodeprint.pojo.Erpsp;

@Mapper
public interface ReadMapper {
	
  @Select("select count(*) from mpsPlan")
  public Integer selectmps();
  
}
