package com.starvincci.barcodeprint.write.service;

import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.pojo.Size;

import java.util.List;

public interface SizeService {
    /**
     * 查询所有的贴纸规格
     * @return
     */
    List<Size> findAllSize();

    List<Erpsp2> findTagsBySpNo(String spNo);
}
