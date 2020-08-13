package com.starvincci.barcodeprint.read.mapper;

import com.starvincci.barcodeprint.pojo.BmBom;
import com.starvincci.barcodeprint.pojo.Erpsp;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BmBomMapper {

    /**
     * 查询bom数据根据导入的Excel中的存货编码
     *
     * 在bom的组成由多个材料组成
     * 贴纸就是其中的之一，我们需要在Excel中找到该贴纸的存货编码，再找哪个bom中含有该存货编码,存在一个贴纸的存货编码在多个bom中的情况。
     * 这个步骤需要在erp中找到思路。
     *
     *
     --1.根据Excel中的贴纸存货编码查找erpsp表中的sp_id 因为bom子表bmBomMat中的mat_id 与 Sp_id 对应
     --2.根据找到的mat_id(sp_id) 查找bmBomMat 中的bom_id
     --3.根据找到的bom_id查询该bom的sp_id bom里面有每款都有两条数据一个是01版本一个是tw版本
     --4.根据找到的sp_id找到存货编码名称

     ---------------------------------------------------------
        一个一个语句的测试
     --select Sp_id from  erpsp where Sp_No='JGK-N770004502'--27605689
     --select * from bmBomMat where Mat_ID='27605689'
     --Select * from bmBom where Bom_id='27604723' --27604723 32000753
     --Select Sp_No from erpsp where Sp_id='27464017'

        整合成两句sql
     --Select * from bmBom where Bom_id in (select Bom_Id from bmBomMat where Mat_ID=(select Sp_id from  erpsp where Sp_No='JGK-N770004502')) and BomRev='01'--
     --select Sp_No from erpsp where SP_ID='30104252'

     * @param spNo Excel中的贴纸存货编码
     * @return
     */
    List<BmBom> findBmBySpNo(String spNo);

    /**
     * 根据找到的sp_id找到该bom名称
     * @return
     */
    Erpsp findSpNo(Integer spId);
}
