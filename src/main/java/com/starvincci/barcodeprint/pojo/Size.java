package com.starvincci.barcodeprint.pojo;

/**
 * 贴纸规格实体类
 * ------------------------
 * 之前是将贴纸规格直接写死在HTML页面上
 * 现通过连接MySQL数据库来读取贴纸规格的名称，
 *
 * 减轻每次系统中无规格是的重复修改动作
 * 只需要在数据库中添加规格即可。
 *
 */
public class Size {

    private Integer id;//编号
    private String sizename;//贴纸规格名称

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSizename() {
        return sizename;
    }

    public void setSizename(String sizename) {
        this.sizename = sizename;
    }
}
