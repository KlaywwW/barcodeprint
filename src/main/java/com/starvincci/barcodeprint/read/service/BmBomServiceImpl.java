package com.starvincci.barcodeprint.read.service;

import com.starvincci.barcodeprint.pojo.BmBom;
import com.starvincci.barcodeprint.pojo.Erpsp;
import com.starvincci.barcodeprint.read.mapper.BmBomMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class BmBomServiceImpl implements BmBomService {

    @Resource
    private BmBomMapper bmBomMapper;

    @Override
    public List<BmBom> findBmBySpNo(String spNo) {
        return bmBomMapper.findBmBySpNo(spNo);
    }

    @Override
    public Erpsp findSpNo(Integer spId) {
        return bmBomMapper.findSpNo(spId);
    }
}
