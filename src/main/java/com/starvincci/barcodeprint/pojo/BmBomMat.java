package com.starvincci.barcodeprint.pojo;

/**
 * 
 * sqlserver数据库 暂不使用此表
 * bom子件信息 关系表
 * @author admin
 *
 */
public class BmBomMat {
	
	private Integer BmBomMat_id;//主键id
	private Integer Bom_Id;//bom父件关系表id
	private Integer Mat_ID;//bomID
	
	public Integer getMat_ID() {
		return Mat_ID;
	}
	public void setMat_ID(Integer mat_ID) {
		Mat_ID = mat_ID;
	}
	public Integer getBom_Id() {
		return Bom_Id;
	}
	public void setBom_Id(Integer bom_Id) {
		Bom_Id = bom_Id;
	}
	public Integer getBmBomMat_id() {
		return BmBomMat_id;
	}
	public void setBmBomMat_id(Integer bmBomMat_id) {
		BmBomMat_id = bmBomMat_id;
	}

}
