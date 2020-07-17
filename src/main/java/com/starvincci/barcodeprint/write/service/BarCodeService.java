package com.starvincci.barcodeprint.write.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starvincci.barcodeprint.pojo.BarCode;
import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.write.mapper.BarCodeMapper;

@Service
public class BarCodeService {

    @Autowired
    private BarCodeMapper barmapper;

    public Integer updateMade(Integer made, String id) {
        return barmapper.updateMade(made, id);
    }

    public Integer updatePo(String po, String id) {
        return barmapper.updatePo(po, id);
    }

    public Integer insertBarCode(Erpsp2 erpsp) {
        return barmapper.insertBarCode(erpsp);
    }

    public Erpsp2 selectErpsp2(Integer id) {
        return barmapper.selectErpsp2(id);
    }

    public BarCode selectBarcodePhoto(String brand) {
        return barmapper.selectBarcodePhoto(brand);
    }

    public List<Erpsp2> selectAllErpsp2() {
        return barmapper.selectAllErpsp2();
    }

    public List<Erpsp2> selectAllErpsp3(Integer id) {
        return barmapper.selectAllErpsp3(id);
    }


    public List<Erpsp2> selectErpsp3(String no) {
        return barmapper.selectErpsp3(no);
    }

    public int updateTongDaiPo(String po, Integer id) {
        return barmapper.updateTongDaiPo(po, id);
    }

    public BarCode selectBarCodeByID(Integer id) {
        return barmapper.selectBarCodeByID(id);
    }

    public Integer deleteBarCodeById(Integer id) {
        return barmapper.deleteBarCodeById(id);
    }

    public int updateSellDate(String sellDate, Integer id) {
        return barmapper.updateSellDate(sellDate, id);
    }
}
