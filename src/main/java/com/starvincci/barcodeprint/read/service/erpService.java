package com.starvincci.barcodeprint.read.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.starvincci.barcodeprint.pojo.BarCodeGuiGe;
import com.starvincci.barcodeprint.pojo.Erpsp;
import com.starvincci.barcodeprint.read.mapper.erpMapper;

@Service
public class erpService {


    @Autowired
    private erpMapper erpmapper;

    public Erpsp selectAllErpsp(Erpsp erpsp) {
        return erpmapper.selectAllErpsp(erpsp);
    }

    public Integer selectMpsPlan(String Plan_No) {
        return erpmapper.selectMpsPlan(Plan_No);
    }

    public List<Erpsp> selectSpIDByNo(String sp_No) {
        return erpmapper.selectSpIDByNo(sp_No);
    }

    /**
     * 找到父件对应的子表id集合
     *
     * @param id
     * @return
     */
    public List<Integer> findziBiaoID(Integer id) {
        return erpmapper.findziBiaoID(id);
    }


    /**
     * 通过id集合 遭到bom信息
     *
     * @param ids
     * @return
     */
    public List<Erpsp> selectErpSpInIDs(List<Integer> ids) {
        return erpmapper.selectErpSpInIDs(ids);
    }

    public List<Erpsp> selectERPSPByNo(String sp_No) {
        return erpmapper.selectERPSPByNo(sp_No);
    }


    /**
     * //业务二:根据附件id找到子件的中某个字段
     *
     * @param sp_No
     * @return
     */
    public String findSuGua(String sp_No) {
        Erpsp erpsp = new Erpsp();
        erpsp.setSp_No(sp_No);
        Erpsp erpsp2 = selectAllErpsp(erpsp);
        if (erpsp2 != null && erpsp2.getSp_id() != null) {
            List<Integer> IDlist = findziBiaoID(erpsp2.getSp_id());
            List<Erpsp> erpspList = selectErpSpInIDs(IDlist);
            //遍历循环--找到子表中的塑挂
            String mes = "";
            for (Erpsp es : erpspList) {
                if (es.getSp_Name().contains("塑挂")) {
                    mes = es.getSp_Name();

                } else {
                    return null;//缺少塑挂的唯一标识
                }
            }
            return mes;
        } else {
            return "error";//款号信息错误
        }

    }


    //业务三:皮夹款一一查出需要的内容


}
	


