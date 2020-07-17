package com.starvincci.barcodeprint.write.mapper;

import com.starvincci.barcodeprint.pojo.Size;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SizeMapper {

    /**
     * 查询所有的贴纸规格
     * @return
     */
    List<Size> findAllSize();
}
