package com.starvincci.barcodeprint.pojo;

/**
 * 
 * sqlserver数据库 暂不使用此表
 * BOM表父级
 * @author admin
 *
 */
public class BmBom {

	private Integer Bom_Id;//主键id
	
	private Integer SP_ID;//bom表id(外键)

	public Integer getSP_ID() {
		return SP_ID;
	}

	public void setSP_ID(Integer sP_ID) {
		SP_ID = sP_ID;
	}

	public Integer getBom_Id() {
		return Bom_Id;
	}

	public void setBom_Id(Integer bom_Id) {
		Bom_Id = bom_Id;
	}
}
