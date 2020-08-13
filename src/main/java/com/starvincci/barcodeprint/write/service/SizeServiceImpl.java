package com.starvincci.barcodeprint.write.service;

import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.pojo.Size;
import com.starvincci.barcodeprint.write.mapper.SizeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SizeServiceImpl implements SizeService {

    @Resource
    private SizeMapper sizeMapper;

    @Override
    public List<Size> findAllSize() {
        return sizeMapper.findAllSize();

    }

    @Override
    public List<Erpsp2> findTagsBySpNo(String spNo) {
        return sizeMapper.findTagsBySpNo(spNo);
    }
}
