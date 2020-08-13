package com.starvincci.barcodeprint.write.mapper;

import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.pojo.Size;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SizeMapper {

    /**
     * 查询所有的贴纸规格
     * @return
     */
    List<Size> findAllSize();

    /**
     * 查询某款号贴纸是否存在
     */
    List<Erpsp2> findTagsBySpNo(String spNo);
}
