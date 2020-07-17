package com.starvincci.barcodeprint.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.starvincci.barcodeprint.write.service.SizeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.starvincci.barcodeprint.pojo.BarCode;
import com.starvincci.barcodeprint.pojo.Erpsp;
import com.starvincci.barcodeprint.pojo.Erpsp2;
import com.starvincci.barcodeprint.read.service.erpService;
import com.starvincci.barcodeprint.util.IOUtils;
import com.starvincci.barcodeprint.util.IPUtils;
import com.starvincci.barcodeprint.write.service.BarCodeService;

@CrossOrigin
@Controller
public class ErpspController {

    private static final Logger log = LoggerFactory.getLogger(ErpspController.class);

    @Autowired
    private erpService espSpService;

    @Autowired
    private BarCodeService barCodeService;

    @Autowired
    private SizeServiceImpl sizeService;

    /**
     * 进入打印预览首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String Getindex(Model model) {

//        System.out.println(sizeService.findAllSize().size());

        model.addAttribute("size",sizeService.findAllSize());

        return "spinfo";
    }


    /**
     * 模糊查询款号 sqlserver
     * <p>
     * (添加规格)打印预览页面 模糊查询输入的bom编号
     */
    @RequestMapping("/findErpSPInfo")
    @ResponseBody
    public String returnInfo(String spNo, HttpSession session) {
        log.info("--模糊查询款号");
        session.removeAttribute("mes");
        session.removeAttribute("mes2");
        List<Erpsp> list = espSpService.selectSpIDByNo(spNo);
        if (list.size() > 0 && list != null) {

            session.setAttribute("ERPlist", list);
            return "OK";
        } else {
            session.removeAttribute("ERPlist");
            return "NO";
        }

    }


    /**
     * mysql 数据库
     * 1模糊查询工令单
     * (添加规格)产品列表页面 模糊查询输入的bom编号
     */
    @RequestMapping("/findErpSPInfo2")
    @ResponseBody
    public String returnInfo2(String spNo, HttpSession session) {
        log.info("mysql--查询款号");
        session.removeAttribute("mes");
        session.removeAttribute("mes2");
        List<Erpsp2> list = barCodeService.selectErpsp3(spNo);
        if (list.size() > 0 && list != null) {
            session.setAttribute("ERPlist2", list);

            return "OK";
        } else {
            session.removeAttribute("ERPlist2");
            return "NO";
        }

    }


    /**
     * 打印预览功能
     * <p>
     * 根据传递来过来的数据查找对应的存货编码相关信息 并且资料齐全直接写入指定路径文本,打开对应的BT模板时会读取指定路径的文本
     * <p>
     * 逻辑判断
     * 1判断mysql数据库是否存在此数据(是否已经导入过规格)
     * <p>
     * 2若是皮带款 判断是否存在此LOGO
     * <p>
     * 3写入数据
     * 处理数据: 皮带(价格标,胶片),童带,钱夹
     *
     * @param id
     * @return 打印预览
     */
    @RequestMapping("/erpSp")
    public String getErpSp(Integer id, HttpSession session,Model model) {

        //贴纸规格
        model.addAttribute("size",sizeService.findAllSize());

        log.info("--打印预览，写入数据中");
        if (id != null) {            session.removeAttribute("mes");
            session.removeAttribute("mes2");
            session.removeAttribute("img");
            session.removeAttribute("missinfo");
            session.removeAttribute("erpsp2");

            //业务一 在sqlserver数据库找到bom相关资料
            Erpsp erpSp = new Erpsp();
            Erpsp erpsp = new Erpsp();
            erpsp.setSp_id(id);
            erpSp = espSpService.selectAllErpsp(erpsp);
            if (erpSp != null) {
                if (erpSp.getErpspPlusmyField09() != null && erpSp.getErpspPlusmyField09() != "") {
                    if (erpSp.getErpspPlusmyField09().contains("带") || erpSp.getErpspPlusmyField09().contains("帶")) {
                        if (erpSp.getErpspPlusmyField11() != null && erpSp.getErpspPlusmyField11() != "") {
                            if (erpSp.getErpspPlusmyField11().contains("孩") || erpSp.getErpspPlusmyField11().contains("童")) {
                                erpSp.setKk("童带");
                            } else if (erpSp.getErpspPlusmyField09().contains("背") || erpSp.getErpspPlusmyField09().contains("吊褲")) {
                                erpSp.setKk("背带");
                            } else if (erpSp.getErpspPlusmyField09().contains("狗")) {
                                erpSp.setKk("狗带");
                            } else if (erpSp.getErpspPlusmyField09().contains("帽")) {
                                erpSp.setKk("帽带");
                            } else {
                                erpSp.setKk(erpSp.getErpspPlusmyField09());
                            }

                        } else {
                            erpSp.setKk("皮带类");
                        }
                    } else {

                        erpSp.setKk(erpSp.getErpspPlusmyField09());
                    }
                } else {
                    erpSp.setKk("ErpspPlusmyField09值为空");
                }

                session.setAttribute("erpSps", erpSp);

                Erpsp2 erpsp2 = barCodeService.selectErpsp2(id);
                //业务二 在mysql数据库查询相关信息
                if (erpsp2 == null) {

                    //没有资料 判断有无图片地址
                    //若无准确的品牌和LOGO对应关系 进行模糊匹配
                    //将字符串转换为全部大写 截取后面内容进行匹配

                    log.info("查询mysql数据库....没有找到款号");
                    session.setAttribute("mes", "缺少数据，请联系研发添加贴纸数据！");
                    Boolean isPidai = false;
                    //判断是否是皮带 是皮带则需要LOGO
                    if (erpSp.getErpspPlusmyField09() != null) {
                        isPidai = erpSp.getErpspPlusmyField09().contains("带") == true || erpSp.getErpspPlusmyField09().contains("帶") == true;
                    } else {
                        isPidai = false;
                    }
                    String orlImgUrl = "";
                    if (isPidai) {
                        BarCode br = barCodeService.selectBarcodePhoto(erpSp.getErpspPlusmyField05());
                        if (br != null) {
                            orlImgUrl = br.getImgUrl();
                            int i = br.getImgUrl().lastIndexOf('\\');
                            String ImgUrl = br.getImgUrl().substring(i + 1, br.getImgUrl().length());
                            session.setAttribute("img", ImgUrl);
                        } else {
                            session.setAttribute("missinfo", 1);//标记缺少img
                        }
                    }
                    return "spinfo";
                } else {

                    log.info("查询mysql数据库....已找到款号");
                    Boolean isPidai = erpSp.getErpspPlusmyField09().contains("带") == true || erpSp.getErpspPlusmyField09().contains("帶") == true;
                    String orlImgUrl = "";

                    try {

                        String imgUrl2 = "D:\\wip\\ex2.png";
                        String imgUrl = "D:\\wip\\ex1.png";
                        String KeHu = "D:\\wip\\kehu.txt";
                        String upc = "D:\\wip\\upc.txt";
                        String price = "D:\\wip\\price.txt";
                        String kehu2 = "D:\\wip\\kehu2.txt";
                        String size = "D:\\wip\\size.txt";

                        //是否是皮带
                        if (isPidai == true) {
                            BarCode br = barCodeService.selectBarcodePhoto(erpSp.getErpspPlusmyField05());
                            System.out.println("erpSp.getErpspPlusmyField12()---------------------" + erpSp.getErpspPlusmyField12());
                            //是否有LOGO
                            if (br != null) {


                                orlImgUrl = br.getImgUrl();
                                //生成img地址
                                String ImgUrl = "";
                                int i = br.getImgUrl().lastIndexOf('\\');
                                ImgUrl = br.getImgUrl().substring(i + 1, br.getImgUrl().length());
                                session.removeAttribute("imginfo");
                                session.removeAttribute("misssinfo");
                                session.setAttribute("img", ImgUrl);
                                //客户编码是否有尺寸

                                if (erpSp.getErpspPlusmyField12().contains("-")) {


                                    String str1 = erpSp.getErpspPlusmyField12().substring(0, erpSp.getErpspPlusmyField12().indexOf("-"));
                                    String str2 = erpSp.getErpspPlusmyField12().substring(str1.length() + 1, erpSp.getErpspPlusmyField12().length());
                                    String str3 = "SIZE" + "\t" + str2;
                                    System.out.println(str1 + "===" + str3);
                                    IOUtils.WriteLink(str1, kehu2);
                                    IOUtils.WriteLink(str3, size);
                                } else {
                                    IOUtils.WriteLink(erpSp.getErpspPlusmyField12(), kehu2);
                                    IOUtils.WriteLink("SIZE", size);
                                }


                            } else {
                                session.setAttribute("mes", "系统暂无此皮带款的Logo数据，请联系开发人员添加logo资料");
                                session.setAttribute("missinfo", 1);
                                return "spinfo";
                            }

                        }

                        //图片LOGO读写
                        IOUtils.WriteLink(erpSp.getErpspPlusmyField12(), KeHu);
                        IOUtils.WriteLink(erpSp.getErpspPlusmyField03(), upc);
                        if (orlImgUrl != "") {

                            List<String> brands = new ArrayList<>();
                            brands.add("ARIAT");
                            brands.add("Aaiat");
                            brands.add("Ariat Brand");
                            brands.add("Ariat Work");
                            if (brands.contains(erpSp.getErpspPlusmyField05()) == true) {
                                //如果是ARIAT品牌的LOGO 生成第二个LOGO
                                IOUtils.WriteImage("D:\\wip\\imge\\pidai\\ariat2.png", imgUrl2);
                            } else {
                                IOUtils.WriteImage(orlImgUrl, imgUrl2);
                            }

                            IOUtils.WriteImage(orlImgUrl, imgUrl);
                        }
                        IOUtils.WriteLink(erpSp.getErpspPlusmyField06(), price);
                        String mes1 = "";
                        log.info("处理童带数据:正在截取PO号");

                        //判断数据库有无日期
                        if (erpsp2.getSellDate() != null && erpsp2.getSellDate() != "") {
                            mes1 = erpsp2.getSellDate();
                        } else {
                            Calendar calendar = Calendar.getInstance();
                            calendar.setTime(new Date());
                            Integer month = (calendar.get(calendar.MONTH) + 1);

                            if (month < 10) {
                                mes1 = 0 + "" + month + "/";
                            } else {
                                mes1 = month + "/";
                            }
                            String year = calendar.get(calendar.YEAR) + "";
                            mes1 = mes1 + year;
                        }
                        String po;
                        po = erpsp2.getPo();
                        String str1 = "";
                        if (erpSp.getErpspPlusmyField12().contains("-")) {
                            str1 = erpSp.getErpspPlusmyField12().substring(0, erpSp.getErpspPlusmyField12().indexOf("-"));
                        }


                        //判断生产地址
                        String mes2;
                        if (erpsp2.getMade() == 1) {
                            mes2 = "M&F Western Products,Inc" + "\n" + "Made In China" + "\n" + "PO#" + po + "\n" + "ItemNo." + str1 + "\n" + "Meets CPSC&CPSIA Safety Requirements" + "\n" + mes1;
                        } else {
                            mes2 = "M&F Western Products,Inc" + "\n" + "Made In Taiwan" + "\n" + "PO#" + po + "\n" + "ItemNo." + str1 + "\n" + "Meets CPSC&CPSIA Safety Requirements" + "\n" + mes1;
                        }

                        //将此文本写入
                        String url2 = "D:\\wip\\info.txt";
                        IOUtils.WriteLink(mes2, url2);


                        if (erpsp2.getMade() == 1) {

                            String mesM = "MADE" + "\t" + "IN" + "\t" + "CHINA";
                            String urlMade = "D:\\wip\\made.txt";
                            IOUtils.WriteLink(mesM, urlMade);
                        } else {
                            String mesM = "MADE" + "\t" + "IN" + "\t" + "TAIWAN";
                            String urlMade = "D:\\wip\\made.txt";
                            IOUtils.WriteLink(mesM, urlMade);
                        }
                        session.setAttribute("mes", "贴纸已生成!请更换对应的贴纸后打印");
                        session.setAttribute("erpsp2", erpsp2);
                        session.setAttribute("erpspId", id);
                        session.setAttribute("erpspNo", erpSp.getSp_No());

                        return "spinfo";
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        session.setAttribute("mes", "请检查D盘中文件是否缺少或损失或者erp资料不完整,将数据写入文本失败");
                        return "spinfo";
                    }

                }
            } else {
                session.setAttribute("mes", "erp无此款数据");
                return "spinfo";
            }
        } else {//判断传递过来的id是否为空
            return "spinfo";
        }


    }

    /**
     * 清空出货时间
     */
    @RequestMapping("/changeToNull")
    @ResponseBody
    public String changeToNull(String No) {
        if (No != null) {
            //将date日期转换为String类型
            int size = No.length();
            String commonNo = No.substring(0, size - 2);
            //  System.out.println("通用的NOs");
            for (int i = 10; i <= 80; ) {
                String bomNo = commonNo + i;
                List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);
                if (!erpspList.isEmpty()) {//不为空
                    //2019-04-17---->10/2019

                    barCodeService.updateSellDate(null, erpspList.get(0).getSp_id());

                }
                i = i + 2;
            }
        }
        return null;
    }

    /**
     * 修改出货时间
     *
     * @param date
     * @return
     */
    @RequestMapping("/updateSellDate")
    @ResponseBody
    public String updateSellDate(String date, String No) {

        if (No != null && date != null) {
            //将date日期转换为String类型
            int size = No.length();
            String commonNo = No.substring(0, size - 2);
            //  System.out.println("通用的NOs");
            for (int i = 10; i <= 80; ) {
                String bomNo = commonNo + i;
                List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);
                if (!erpspList.isEmpty()) {//不为空
                    //2019-04-17---->10/2019
                    String month = date.substring(5, 7);
                    String year = date.substring(0, 4);
                    String strDate = month + "/" + year;
                    System.out.println(strDate);
                    barCodeService.updateSellDate(strDate, erpspList.get(0).getSp_id());

                }
                i = i + 2;
            }
        }

        return null;
    }


    /**
     * 打印预览首页  添加款号规格
     *
     * @param erpsp2
     * @param session
     * @return
     */
    @RequestMapping("/insert")
    @ResponseBody
    public String insert(Erpsp2 erpsp2, HttpSession session) {
        Erpsp erpsp = (Erpsp) session.getAttribute("erpSps");
        if (erpsp2.getPrice() == null) {
            return "添加贴纸规格失败,请选择规格";
        } else {

            Timestamp time = new Timestamp(new Date().getTime());
            Integer id = erpsp.getSp_id();
            String No = erpsp.getSp_No();
            String brand = erpsp.getErpspPlusmyField05();
            if (id == null || No == null || brand == null) {
                return "数据失效，请刷新页面";
            } else {
                erpsp2.setId(id);
                erpsp2.setNo(No);
                erpsp2.setBrand(brand);
                erpsp2.setDatetime(time);
                //根据id no brand 找到数据库有无此款式
                BarCode barcode = barCodeService.selectBarCodeByID(id);

                if (barcode == null) {
                    int size = No.length();
                    String commonNo = No.substring(0, size - 2);
                    //一般性规则 xxxxNO 以尺寸结尾的款号

                    barCodeService.insertBarCode(erpsp2);

                    for (int i = 10; i <= 80; ) {
                        String bomNo = commonNo + i;
                        List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);
                        if (!bomNo.equals(No)) {
                            if (!erpspList.isEmpty()) {//不为空
                                erpsp2.setNo(bomNo);
                                erpsp2.setId(erpspList.get(0).getSp_id());
                                //barCodeService.deleteBarCodeById(erpspList.get(0).getSp_id());
                                barCodeService.insertBarCode(erpsp2);
                            }
                        }

                        i = i + 2;
                    }
                    //特殊规则 xxxNOm  以 L，M，S,X,XL,XXL结尾的款号

                    String Nos[] = {"L", "M", "S", "X", "XL", "XXL"};
                    for (int j = 0; j < 6; j++) {
                        String cutNo = No.substring(0, size - 1);
                        String commonNo2 = cutNo + Nos[j];
                        //在sqlserver查询款号是否存在
                        List<Erpsp> erpspList = espSpService.selectERPSPByNo(commonNo2);
                        if (!commonNo2.equals(No)) {
                            //
                            if (!erpspList.isEmpty()) {//不为空
                                erpsp2.setNo(commonNo2);
                                erpsp2.setId(erpspList.get(0).getSp_id());
                                barCodeService.deleteBarCodeById(erpspList.get(0).getSp_id());
                                barCodeService.insertBarCode(erpsp2);
                            }
                        }
                    }
                    session.setAttribute("bomID", id);

                    if (erpsp.getErpspPlusmyField11() != null) {
                        if (erpsp.getErpspPlusmyField11().contains("孩") || erpsp.getErpspPlusmyField11().contains("童")) {
                            return "no";
                        } else {
                            return "添加贴纸规格成功！";
                        }
                    } else {
                        return "添加贴纸规格成功！";
                    }
                } else {
                    //数据库已有此款式，进行修改
                    int size = No.length();
                    String commonNo = No.substring(0, size - 2);
                    barCodeService.deleteBarCodeById(id);
                    barCodeService.insertBarCode(erpsp2);

                    for (int i = 10; i <= 80; ) {
                        String bomNo = commonNo + i;
                        List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);
                        if (!bomNo.equals(No)) {
                            if (!erpspList.isEmpty()) {//不为空
                                erpsp2.setNo(bomNo);
                                erpsp2.setId(erpspList.get(0).getSp_id());
                                barCodeService.deleteBarCodeById(erpspList.get(0).getSp_id());
                                barCodeService.insertBarCode(erpsp2);
                            }
                        }
                        i = i + 2;
                    }

                    if (erpsp2.getJiaopian().contains("童")) {
                        return "no";
                    } else {
                        return "添加贴纸规格成功！";
                    }

                }
            }


        }

    }

    /**
     * 进入打印列表页面
     *
     * @param session
     * @param id
     * @return
     */
    @RequestMapping("/updateInfo")
    public String updateInfo(HttpSession session, Integer id, HttpServletRequest request) {
        //获取
        log.info("" + IPUtils.getIpAddr(request));
        List<Erpsp2> list = new ArrayList<>();
        if (id == null) {
            list = barCodeService.selectAllErpsp2();
        } else {
            list = barCodeService.selectAllErpsp3(id);
        }

        session.setAttribute("alllist", list);
        return "update";
    }


    //新增童带尺寸时 需要填写PO号

    @RequestMapping("/updatePo")
    @ResponseBody
    public String updatePo(String word, HttpSession session) {
        Erpsp erpsp = (Erpsp) session.getAttribute("erpSps");
        Integer id = erpsp.getSp_id();
        try {

            String No = erpsp.getSp_No();
            //遍历循环
            int size = No.length();
            String commonNo = No.substring(0, size - 2);
            barCodeService.updateTongDaiPo(word, id);

            for (int i = 10; i <= 80; ) {
                String bomNo = commonNo + i;
                List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);
                if (No != bomNo) {
                    if (!erpspList.isEmpty()) {//不为空
                        barCodeService.updateTongDaiPo(word, erpspList.get(0).getSp_id());
                    }
                }
                i = i + 2;
            }

        } catch (Exception e) {
            // TODO: handle exception
        }

        return "订单号信息已添加";
    }

    /**
     * 修改生产地址
     */
    @RequestMapping("/updateMfrs")
    @ResponseBody
    public String updateMfrs(String Str, String No) {
        if (No != null) {
            if (Str == null) {

                return "error";
            } else if (Str.equals("China")) {
                //修改made为台湾
                //根据No知道类似的
                int size = No.length();
                String commonNo = No.substring(0, size - 2);
                //当前款通用的款号前缀;
                barCodeService.updateMade(2, No);
                for (int i = 10; i <= 80; ) {
                    String bomNo = commonNo + i;
                    List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);

                    if (!erpspList.isEmpty()) {//不为空
                        barCodeService.updateMade(2, bomNo);
                        //将大陆改为台湾;
                    }
                    i = i + 2;
                }
                return "TaiWan";
            } else if (Str.equals("TaiWan")) {
                int size = No.length();
                String commonNo = No.substring(0, size - 2);
                //当前款通用的款号前缀;
                barCodeService.updateMade(1, No);
                for (int i = 10; i <= 80; ) {
                    String bomNo = commonNo + i;
                    List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);
                    if (!erpspList.isEmpty()) {//不为空
                        barCodeService.updateMade(1, bomNo);
                        //将大陆改为台湾;
                    }
                    i = i + 2;
                }
                System.out.println("台湾改为大陆");
                return "China";
            } else {

                return "error";
            }
        } else {
            return "error";
        }


    }

    /**
     * 修改PO号
     *
     * @param No
     * @param linkQuee
     * @return
     */
    @RequestMapping("/updatePo2")
    @ResponseBody
    public String update2222(String No, String linkQuee) {

        if (No != null && No != "") {
            try {
                int size = No.length();
                String commonNo = No.substring(0, size - 2);
                barCodeService.updatePo(linkQuee, No);
                //当前款通用的款号前缀;
                for (int i = 10; i <= 80; ) {
                    String bomNo = commonNo + i;
                    List<Erpsp> erpspList = espSpService.selectERPSPByNo(bomNo);
                    if (!erpspList.isEmpty()) {//不为空
                        barCodeService.updatePo(linkQuee, bomNo);
                        //将大陆改为台湾;
                    }
                    i = i + 2;
                }
                return linkQuee;
            } catch (Exception e) {
                // TODO: handle exception
                return "ERROR";
            }

        } else {
            return "ERROR";
        }
    }


}
