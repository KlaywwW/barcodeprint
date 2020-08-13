package com.starvincci.barcodeprint.read.service;

import com.starvincci.barcodeprint.pojo.BmBom;
import com.starvincci.barcodeprint.pojo.Erpsp;

import java.util.List;

public interface BmBomService {

    List<BmBom> findBmBySpNo(String spNo);

    Erpsp findSpNo(Integer spId);
}
