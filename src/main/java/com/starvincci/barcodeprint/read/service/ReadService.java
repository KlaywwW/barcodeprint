package com.starvincci.barcodeprint.read.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starvincci.barcodeprint.read.mapper.ReadMapper;

@Service
public class ReadService {

	@Autowired
	private ReadMapper readMapper;
	 public Integer selectmps() {
		 return readMapper.selectmps();
	 }
	
}
