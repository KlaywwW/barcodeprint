package com.starvincci.barcodeprint.read.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.starvincci.barcodeprint.pojo.Erpsp;
import com.starvincci.barcodeprint.pojo.MpsPlan;

/**
 * 存货档案
 *
 * @author admin
 */
@Mapper
public interface ErpMapper {

    @Select("<script>"
            + "select Sp_No, Sp_Spec, Sp_Name,erpspPlusmyField03,erpspPlusmyField12,erpspPlusmyField05,erpspPlusmyField06,erpspPlusmyField09,erpspPlusmyField11,erpsp.Sp_id from erpsp,erpspPlus"
            + "<where>"
            + "erpsp.Sp_id=erpspPlus.SP_ID "
            + "<if test='Sp_id !=null'>"
            + "and erpsp.Sp_id=#{Sp_id}"
            + "</if>"
            + "<if test='Sp_No!=null'>"
            + "and Sp_No=#{Sp_No} "    //存货编码名称
            + "</if>"
            + "<if test='erpspPlusmyField12!=null'>"
            + " and erpspPlusmyField12=#{erpspPlusmyField12}" //客户编码
            + "</if>"
            + "<if test='erpspPlusmyField05!=null'>"
            + " and erpspPlusmyField05=#{erpspPlusmyField05}" //品牌名称
            + "</if>"
            + "</where>"
            + "</script>")
    @Results({
            @Result(column = "Sp_No", property = "Sp_No"),
            @Result(column = "Sp_Name", property = "Sp_Name"),
            @Result(column = "erpspPlusmyField03", property = "erpspPlusmyField03"),
            @Result(column = "erpspPlusmyField12", property = "erpspPlusmyField12"),
            @Result(column = "erpspPlusmyField05", property = "erpspPlusmyField05"),
            @Result(column = "erpspPlusmyField06", property = "erpspPlusmyField06"),
            @Result(column = "erpspPlusmyField09", property = "erpspPlusmyField09"),
            @Result(column = "erpspPlusmyField11", property = "erpspPlusmyField11"),
            @Result(column = "Sp_id", property = "Sp_id"),
            @Result(column = "Sp_Spec", property = "Sp_Spec"),
    })
    public Erpsp selectAllErpsp(Erpsp erpsp);


    /**
     * 根据工单编号找到工单id
     *
     * @param Plan_No
     * @return
     */
    @Select("select Sp_id from mpsPlan where Plan_No=#{Plan_No}")
    public Integer selectMpsPlan(String Plan_No);


    @Select("select Mat_ID from bmBomMat,bmBom where bmBomMat.Bom_Id=bmBom.Bom_Id AND SP_ID=#{id}")
    public List<Integer> findziBiaoID(Integer id);


    /***
     * 根据子表id集合查询需要的价格标签和胶片标签
     *
     *
     */
    @Select("<script>"
            + "select Sp_id,Sp_No,Sp_Name, Sp_Spec from erpsp "
            + "<where>"
            + "and Sp_id  in"
            + "<foreach  item='item' collection='list' open='(' close=')' separator=','>"
            + "#{item}"
            + "</foreach>"
            + "</where>"
            + "</script>")
    @Results({
            @Result(column = "Sp_No", property = "Sp_No"),
            @Result(column = "Sp_Name", property = "Sp_Name"),
            @Result(column = "Sp_id", property = "Sp_id"),
            @Result(column = "Sp_Spec", property = "Sp_Spec")
    })
    public List<Erpsp> selectErpSpInIDs(List<Integer> ids);


    @Select("select top 50 Sp_id,Sp_No,Sp_Name  from erpSP where Sp_No like '%'+#{sp_No}+'%' ")
    @Results({
            @Result(column = "Sp_id", property = "Sp_id"),
            @Result(column = "Sp_No", property = "Sp_No"),
            @Result(column = "Sp_Name", property = "Sp_Name")
    })
    public List<Erpsp> selectSpIDByNo(String sp_No);


    @Select("select  Sp_id,Sp_No,Sp_Name  from erpSP where Sp_No =#{sp_No} ")
    @Results({
            @Result(column = "Sp_id", property = "Sp_id"),
            @Result(column = "Sp_No", property = "Sp_No"),
            @Result(column = "Sp_Name", property = "Sp_Name")
    })
    public List<Erpsp> selectERPSPByNo(String sp_No);
}
