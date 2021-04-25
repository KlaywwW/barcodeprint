package com.starvincci.barcodeprint.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.starvincci.barcodeprint.pojo.Erpsp;
import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.read.service.ErpService;
import com.starvincci.barcodeprint.write.service.BarCodeService;


@Controller
public class BarCodeController {

    @Autowired
    private ErpService eservice;
    @Autowired
    private BarCodeService barCodeService;

    private String path="http://localhost:8082";


    /**
     * 用户点击打印预览，使用命令行打开模板
     *
     * @param url 需要打开的模板路径
     * @param id  bom id
     * @return
     */
    @RequestMapping("/point")
    public String point(String url, Integer id, HttpSession session) {

        Erpsp erpsp = new Erpsp();
        erpsp.setSp_id(id);
        Erpsp erpspBarCode = eservice.selectAllErpsp(erpsp);
        System.out.println(url);
        if (erpspBarCode == null) {
            session.setAttribute("mes", "顺景ERP数据库暂无此款号的数据，请联系开发人员添加存货档案");
            return "redirect:"+path+"/index";
        } else {


            String f = "D:\\bartender\\皮带\\" + url + ".btw";  //要打开的文件路径
            System.out.println(f);
            Runtime r = Runtime.getRuntime();
            try {
                r.exec("cmd /c start " + f);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                System.out.println("打印皮带错误");
            }

            session.setAttribute("mes", "改变出货时间 po 地址 需要 重新点击打印预览");
            //http://192.168.123.118:830/erpSp?id=82257
            String url2 = "redirect:"+path+"/erpSp?id=" + id;
            return url2;

        }
    }

    //自动更新
    @RequestMapping("/autoupdate")
    @ResponseBody

    public String AutoUpdate() {
        //查询mysql数据库
        List<Erpsp2> list = barCodeService.selectAllErpsp2();
        List<String> Nos = new ArrayList<String>();
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            //获取当前对象

            Erpsp2 erpsp = list.get(i);
            String StrNo = erpsp.getNo();
            int size1 = StrNo.length();
            String commonNo = StrNo.substring(0, size1 - 2);
            if (!Nos.contains(commonNo)) {
                count++;
                Nos.add(commonNo);
                System.out.println("开始更新第" + count + "个款" + "款号为:" + commonNo);

                for (int j = 10; j <= 80; ) {
                    String bomNo = commonNo + j;
                    List<Erpsp> erpspList = eservice.selectERPSPByNo(bomNo);
                    if (!erpspList.isEmpty()) {//不为空
                        barCodeService.deleteBarCodeById(erpspList.get(0).getSp_id());
                        erpsp.setNo(bomNo);
                        erpsp.setId(erpspList.get(0).getSp_id());
                        barCodeService.insertBarCode(erpsp);

                    }
                    j = j + 2;
                }
            }

        }


        return "已更新完毕";
    }

}
