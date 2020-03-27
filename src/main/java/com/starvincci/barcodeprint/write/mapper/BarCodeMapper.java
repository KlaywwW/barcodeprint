package com.starvincci.barcodeprint.write.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.starvincci.barcodeprint.pojo.BarCode;
import com.starvincci.barcodeprint.pojo.Erpsp2;

@Mapper
public interface BarCodeMapper {
	
	/**
	 * 根据 品牌 款式 找到 对应规格的背景图片
	 * @return
	 */
	@Select("select * from barcode where brand=#{brand} limit 1")
	@Results({
		@Result(column="imgUrl",property="imgUrl"),
		@Result(column="brand",property="brand")
	})
	public  BarCode selectBarcodePhoto(String brand);
	
	/**
	 * 根据bomid找到相关信息
	 * @param id
	 * @return
	 */
	@Select("select * from erpsp2 where id=#{id} ")
	@Results({
		@Result(column="id",property="id")
	})
	public BarCode selectBarCodeByID(Integer id);
	
	
	
	
	//删除已有数据
	@Delete("delete from erpsp2 where id=#{id}")
	public Integer deleteBarCodeById(Integer id);
	
	//修改数据的生产地址
	@Update("Update erpsp2 set made=#{made} where no=#{no}")
	public Integer updateMade(@Param("made")Integer made,@Param("no")String no);
	
	//修改数据的PO号
	@Update("Update erpsp2 set po=#{po} where no=#{no}")
	public Integer updatePo(@Param("po")String po,@Param("no")String no);
	
	
	
	
	
	
	
	/**
	 * 
	 * @param barcode
	 * @return
	 */
	@Insert("insert into erpsp2 value(#{id},#{no},#{price},#{neihe},#{xiaosipian},#{jiaopian},#{guapai},#{maodai},#{brand},null,2,null,#{datetime})")
	public Integer insertBarCode(Erpsp2 erpsp);
	
	/**
	 * 
	 * @return
	 */
	@Select("select * from erpsp2 where id =#{id}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="no",property="no"),
		@Result(column="price",property="price"),
		@Result(column="neihe",property="neihe"),
		@Result(column="xiaosipian",property="xiaosipian"),
		@Result(column="jiaopian",property="jiaopian"),
		@Result(column="guapai",property="guapai"),
		@Result(column="maodai",property="maodai"),
		@Result(column="po",property="Po"),
		@Result(column="made",property="made"),
		@Result(column="sellDate",property="sellDate"),
		@Result(column="datetime",property="datetime"),
	})
	public Erpsp2 selectErpsp2(Integer id);
	
	
	
	
	
	@Select("<script>"
			+ "select * from erpsp2 "
			+ "<where>"
			+ "no like CONCAT('%',#{no},'%') "
			+ "</where>"
	  		+ "</script>")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="no",property="no"),
		@Result(column="price",property="price"),
		@Result(column="neihe",property="neihe"),
		@Result(column="xiaosipian",property="xiaosipian"),
		@Result(column="jiaopian",property="jiaopian"),
		@Result(column="guapai",property="guapai"),
		@Result(column="maodai",property="maodai"),
	})
	public List<Erpsp2> selectErpsp3(String no);
	
	
	
	
	
	@Select("select * from erpsp2 limit 100")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="no",property="no"),
		@Result(column="price",property="price"),
		@Result(column="neihe",property="neihe"),
		@Result(column="xiaosipian",property="xiaosipian"),
		@Result(column="jiaopian",property="jiaopian"),
		@Result(column="guapai",property="guapai"),
		@Result(column="maodai",property="maodai"),
		@Result(column="brand",property="brand"),
		@Result(column="datetime",property="datetime"),
		
	})
	public List<Erpsp2> selectAllErpsp2();
	
	@Select("select * from erpsp2 where id=#{id}")
	@Results({
		@Result(column="id",property="id"),
		@Result(column="no",property="no"),
		@Result(column="price",property="price"),
		@Result(column="neihe",property="neihe"),
		@Result(column="xiaosipian",property="xiaosipian"),
		@Result(column="jiaopian",property="jiaopian"),
		@Result(column="guapai",property="guapai"),
		@Result(column="maodai",property="maodai"),
		@Result(column="brand",property="brand"),
		@Result(column="datetime",property="datetime"),
		
	})
	public List<Erpsp2> selectAllErpsp3(Integer id);
	
	
	
	@Update("update erpsp2 set po=#{po} where id=#{id}")
	public int updateTongDaiPo(@Param("po")String po,@Param("id")Integer id);
	
	@Update("update erpsp2 set sellDate=#{sellDate} where id=#{id}")
	public int updateSellDate(@Param("sellDate")String sellDate,@Param("id")Integer id);
	
}
