package com.zhongyi.glass.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.zhongyi.glass.bean.ConcaveCylinderBean;
import com.zhongyi.glass.bean.ConcaveSphereBean;
import com.zhongyi.glass.bean.EmployeeBean;
import com.zhongyi.glass.bean.GoodsTypeBean;
import com.zhongyi.glass.bean.VisualAcuityBean;
import com.zhongyi.glass.constant.CodeConstant;
import com.zhongyi.glass.dao.EmployeeDao;
import com.zhongyi.glass.dao.SalesRecordDao;
import com.zhongyi.glass.form.SalesRecordForm;
import com.zhongyi.glass.util.CommonUtil;
import com.zhongyi.glass.util.MessageUtil;
import com.zhongyi.glass.util.StringUtil;

/**
 * 销售记录添加/修改
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class SalesRecordEditAction extends BaseAction {

    /**
     * 销售记录DAO
     */
    private SalesRecordDao salesRecordDao;

    /**
     * 职员DAO
     */
    private EmployeeDao employeeDao;

    @Override
    public ActionForward doLogic(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        SalesRecordForm salesRecordForm = (SalesRecordForm) form;
        String salesRecordId = salesRecordForm.getSalesRecordId();
        if (CodeConstant.OPERATE_TYPE_COMMON_EDIT.equals(salesRecordForm.getOperateType())) {
            Map<String, Object> param = new HashMap<String, Object>();
            String message = null;
            // 参数设定
            setSalesRecordParam(param, salesRecordForm);
            if (StringUtil.isEmpty(salesRecordId)) {
                // 新增
                // 添加销售记录
                salesRecordDao.insertSalesRecordInfo(param);
                // 取得添加销售记录的销售记录ID
                String insertSalesRecordId = salesRecordDao.getLastestSalesRecordIdByCustomer(salesRecordForm.getCustomerId());
                // 添加商品信息
                String[] goodsAmountArray = salesRecordForm.getGoodsAmount();
                if (goodsAmountArray != null && goodsAmountArray.length > 0) {
                    int count = 0;
                    for (int i = 0; i < goodsAmountArray.length; i++) {
                        if (StringUtil.getInt(goodsAmountArray[i]) >= 0) {
                            count++;
                            Map<String, Object> salesGoodsParam = new HashMap<String, Object>();
                            setSalesGoodsParam(salesGoodsParam, salesRecordForm, i, count, insertSalesRecordId);
                            // 添加销售商品信息
                            salesRecordDao.insertSalesGoodsInfo(salesGoodsParam);
                        }
                    }
                }

                message = "添加成功！";
            } else {
                // 编辑
                salesRecordDao.updateSalesRecordInfo(param);
                message = "修改成功！";
            }
            // 清空Form
            salesRecordForm.clear();
            MessageUtil.setPopMessageAndRedirect(request, message, "salesRecordView.do");

        } else {
            if (StringUtil.isEmpty(salesRecordId)) {
                // Menu【增加销售记录】时
                salesRecordForm.clear();
                // 取得视力表数据
                salesRecordForm.setVisualAcuityList(salesRecordDao.getVisualAcuityList());
                // 取得球镜表数据
                salesRecordForm.setConcaveSphereList(salesRecordDao.getConcaveSphereList());
                // 取得柱镜表数据
                salesRecordForm.setConcaveCylinderList(salesRecordDao.getConcaveCylinderList());
                // 取得职员表数据
                salesRecordForm.setEmployeeList(employeeDao.getAllEmployeeList());
                // 取得商品类型表数据
                salesRecordForm.setGoodsTypeList(salesRecordDao.getGoodsTypeList());
            } else {
                // 查看销售记录页面【编辑】时
                // 取得销售记录详情
                Map<String, Object> salesRecordInfo = salesRecordDao.getSalesRecordInfo(salesRecordId);
                // 设定销售记录详细信息
                setSalesRecordInfo(salesRecordInfo, salesRecordForm);
                // 取得销售商品详情
                List<Map<String, Object>> salesGoodsList = salesRecordDao.getSalesGoodsList(salesRecordId);
                // 设定销售记录对应的商品详细信息
                editSalesGoodsList(salesGoodsList, salesRecordForm);

            }
        }

        salesRecordForm.setOperateType(null);
        return mapping.findForward("success");
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return SalesRecordEditAction.class;
    }

    /**
     * 取得销售记录DAO
     * 
     * @return 销售记录DAO
     */
    public SalesRecordDao getSalesRecordDao() {
        return salesRecordDao;
    }

    /**
     * 设定销售记录DAO
     * 
     * @param salesRecordDao 销售记录DAO
     */
    public void setSalesRecordDao(SalesRecordDao salesRecordDao) {
        this.salesRecordDao = salesRecordDao;
    }

    /**
     * 取得职员DAO
     * 
     * @return 职员DAO
     */
    public EmployeeDao getEmployeeDao() {
        return employeeDao;
    }

    /**
     * 设定职员DAO
     * 
     * @param employeeDao 职员DAO
     */
    public void setEmployeeDao(EmployeeDao employeeDao) {
        this.employeeDao = employeeDao;
    }

    /**
     * 设定销售记录参数
     * 
     * @param param
     * @param salesRecordForm
     */
    private void setSalesRecordParam(Map<String, Object> param, SalesRecordForm salesRecordForm) {
        String currentTime = StringUtil.valueOf(System.currentTimeMillis() / 1000);
        if (StringUtil.isEmpty(salesRecordForm.getSalesRecordId())) {
            // 顾客ID
            param.put("CUSTOMER_ID", salesRecordForm.getCustomerId());
            // 眼镜类型
            param.put("GLASS_TYPE", StringUtil.emptyStrToNull(salesRecordForm.getGlassType()));
            // 裸眼视力（左）
            param.put("UNCORRECTED_VISUAL_ACUITY_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getUncorrectedVisualAcuityLeft()));
            // 球镜（左）
            param.put("CONCAVE_SPHERE_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getConcaveSphereLeft()));
            // 柱镜（左）
            param.put("CONCAVE_CYLINDER_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getConcaveCylinderLeft()));
            // 轴位（左）
            param.put("AXIAL_DIRECTION_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getAxialDirectionLeft()));
            // 矫正视力（左）
            param.put("CORRECTED_VISUAL_ACUITY_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getCorrectedVisualAcuityLeft()));
            // 远用瞳距（左）
            param.put("INTERPUPILLARY_DISTANCE_FAR_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getInterpupillaryDistanceFarLeft()));
            // 近用瞳距（左）
            param.put("INTERPUPILLARY_DISTANCE_CLOSE_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getInterpupillaryDistanceCloseLeft()));
            // 度数（左）
            param.put("DEGREE_LEFT", StringUtil.emptyStrToNull(salesRecordForm.getDegreeLeft()));
            // 裸眼视力（右）
            param.put("UNCORRECTED_VISUAL_ACUITY_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getUncorrectedVisualAcuityRight()));
            // 球镜（右）
            param.put("CONCAVE_SPHERE_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getConcaveSphereRight()));
            // 柱镜（右）
            param.put("CONCAVE_CYLINDER_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getConcaveCylinderRight()));
            // 轴位（右）
            param.put("AXIAL_DIRECTION_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getAxialDirectionRight()));
            // 矫正视力（右）
            param.put("CORRECTED_VISUAL_ACUITY_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getCorrectedVisualAcuityRight()));
            // 远用瞳距（右）
            param.put("INTERPUPILLARY_DISTANCE_FAR_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getInterpupillaryDistanceFarRight()));
            // 近用瞳距（右）
            param.put("INTERPUPILLARY_DISTANCE_CLOSE_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getInterpupillaryDistanceCloseRight()));
            // 度数（右）
            param.put("DEGREE_RIGHT", StringUtil.emptyStrToNull(salesRecordForm.getDegreeRight()));
            // 验光师
            param.put("OPTOMETRIST", StringUtil.emptyStrToNull(salesRecordForm.getOptometrist()));
            // 配镜师
            param.put("LENS_DISPENSER", StringUtil.emptyStrToNull(salesRecordForm.getLensDispenser()));
            // 加工师
            param.put("PROCESSOR", StringUtil.emptyStrToNull(salesRecordForm.getProcessor()));
            // 检验员
            param.put("INSPECTOR", StringUtil.emptyStrToNull(salesRecordForm.getInspector()));
            // 收银员
            param.put("CASHIER", StringUtil.emptyStrToNull(salesRecordForm.getCashier()));
            // 总价
            param.put("TOTAL_AMOUNT", CommonUtil.formatStoreMoney(salesRecordForm.getTotalAmount()));
            // 优惠总额
            param.put("TOTAL_DISCOUNT", CommonUtil.formatStoreMoney(salesRecordForm.getTotalDiscount()));
            // 创建时间
            param.put("CREATE_TIME", currentTime);
        }

        // 销售记录ID
        param.put("SALES_RECORD_ID", salesRecordForm.getSalesRecordId());
        // 更新时间
        param.put("UPDATE_TIME", currentTime);
        // 备注
        param.put("COMMENT", StringUtil.emptyStrToNull(salesRecordForm.getComment()));
        // 取镜状态
        param.put("GLASS_STATUS", StringUtil.emptyStrToNull(salesRecordForm.getGlassStatus()));
        // 取镜日期
        param.put("GLASS_TAKEN_DATE", StringUtil.emptyStrToNull(salesRecordForm.getGlassTakenDate()));
        // 复查日期
        param.put("RE_EXAMINE_DATE", StringUtil.emptyStrToNull(salesRecordForm.getReExamineDate()));
    }

    /**
     * 设定销售商品参数
     * 
     * @param param
     * @param salesRecordForm
     * @param index 商品列表索引
     * @param goodsNo 商品序号
     * @param salesRecordId 销售记录ID
     */
    private void setSalesGoodsParam(Map<String, Object> param, SalesRecordForm salesRecordForm,
            int index, int goodsNo, String salesRecordId) {
        // 销售记录ID
        param.put("SALES_RECORD_ID", salesRecordId);
        // 商品序号
        param.put("GOODS_NO", goodsNo);
        // 商品类型ID
        param.put("GOODS_TYPE", salesRecordForm.getGoodsType()[index]);
        // 品牌
        param.put("GOODS_BRAND", StringUtil.emptyStrToNull(salesRecordForm.getGoodsBrand()[index]));
        // 品牌型号
        param.put("GOODS_BRAND_SUB_TYPE", StringUtil.emptyStrToNull(salesRecordForm.getGoodsBrandSubType()[index]));
        // 数量
        param.put("GOODS_QUANTITY", salesRecordForm.getGoodsQuantity()[index]);
        // 单价（RMB：分）
        param.put("GOODS_PRICE", CommonUtil.formatStoreMoney(salesRecordForm.getGoodsPrice()[index]));
        // 折扣类型
        param.put("GOODS_DISCOUNT_TYPE", StringUtil.emptyStrToNull(salesRecordForm.getGoodsDiscountType()[index]));
        // 折扣
        if (CodeConstant.DISCOUNT_TYPE_LINGTOU.equals(StringUtil.valueOf(param.get("GOODS_DISCOUNT_TYPE")))) {
            // 折扣类型为“去零头”
            param.put("GOODS_DISCOUNT", StringUtil.emptyStrToNull(CommonUtil.formatStoreMoney(salesRecordForm.getGoodsDiscount()[index])));
        } else {
            param.put("GOODS_DISCOUNT", StringUtil.emptyStrToNull(salesRecordForm.getGoodsDiscount()[index]));
        }
        // 折扣后金额
        param.put("GOODS_AMOUNT", StringUtil.emptyStrToNull(CommonUtil.formatStoreMoney(salesRecordForm.getGoodsAmount()[index])));

    }

    /**
     * 设定销售记录信息
     * 
     * @param salesRecordInfo
     * @param salesRecordForm
     */
    private void setSalesRecordInfo(Map<String, Object> salesRecordInfo, SalesRecordForm salesRecordForm) {
        // 取得视力表数据
        List<VisualAcuityBean> visualAcuityList = salesRecordDao.getVisualAcuityList();
        // 取得球镜表数据
        List<ConcaveSphereBean> concaveSphereList = salesRecordDao.getConcaveSphereList();
        // 取得柱镜表数据
        List<ConcaveCylinderBean> concaveCylinderList = salesRecordDao.getConcaveCylinderList();
        // 取得职员表数据
        List<EmployeeBean> employeeList = employeeDao.getAllEmployeeList();
        // 顾客ID
        salesRecordForm.setCustomerId(StringUtil.valueOf(salesRecordInfo.get("CUSTOMER_ID")));
        // 顾客姓名
        salesRecordForm.setCustomerName(StringUtil.valueOf(salesRecordInfo.get("CUSTOMER_NAME")));
        // 配镜类型
        String glassType = StringUtil.valueOf(salesRecordInfo.get("GLASS_TYPE"));
        if (CodeConstant.GLASS_TYPE_JIAJING.equals(glassType)) {
            salesRecordForm.setGlassType(CodeConstant.GLASS_TYPE_NAME_JIAJING);
        } else if (CodeConstant.GLASS_TYPE_YINXING.equals(glassType)) {
            salesRecordForm.setGlassType(CodeConstant.GLASS_TYPE_NAME_YINXING);
        }

        String uncorrectedVisulaAcuityLeft = StringUtil.valueOf(salesRecordInfo.get("UNCORRECTED_VISUAL_ACUITY_LEFT"));
        String uncorrectedVisulaAcuityRight = StringUtil.valueOf(salesRecordInfo.get("UNCORRECTED_VISUAL_ACUITY_RIGHT"));
        String correctedVisulaAcuityLeft = StringUtil.valueOf(salesRecordInfo.get("CORRECTED_VISUAL_ACUITY_LEFT"));
        String correctedVisulaAcuityRight = StringUtil.valueOf(salesRecordInfo.get("CORRECTED_VISUAL_ACUITY_RIGHT"));
        for (VisualAcuityBean item : visualAcuityList) {
            if (item.getId().equals(uncorrectedVisulaAcuityLeft)) {
                // 裸眼视力（左）
                salesRecordForm.setUncorrectedVisualAcuityLeft(item.getValue());
            }
            if (item.getId().equals(uncorrectedVisulaAcuityRight)) {
                // 裸眼视力（右）
                salesRecordForm.setUncorrectedVisualAcuityRight(item.getValue());
            }
            if (item.getId().equals(correctedVisulaAcuityLeft)) {
                // 矫正视力（左）
                salesRecordForm.setCorrectedVisualAcuityLeft(item.getValue());
            }
            if (item.getId().equals(correctedVisulaAcuityRight)) {
                // 矫正视力（右）
                salesRecordForm.setCorrectedVisualAcuityRight(item.getValue());
            }
        }

        String concaveSphereLeft = StringUtil.valueOf(salesRecordInfo.get("CONCAVE_SPHERE_LEFT"));
        String concaveSphereRight = StringUtil.valueOf(salesRecordInfo.get("CONCAVE_SPHERE_RIGHT"));
        for (ConcaveSphereBean item : concaveSphereList) {
            if (item.getId().equals(concaveSphereLeft)) {
                // 球镜（左）
                salesRecordForm.setConcaveSphereLeft(item.getValue());
            }
            if (item.getId().equals(concaveSphereRight)) {
                // 球镜（右）
                salesRecordForm.setConcaveSphereRight(item.getValue());
            }
        }

        String concaveCylinderLeft = StringUtil.valueOf(salesRecordInfo.get("CONCAVE_CYLINDER_LEFT"));
        String concaveCylinderRight = StringUtil.valueOf(salesRecordInfo.get("CONCAVE_CYLINDER_RIGHT"));
        for (ConcaveCylinderBean item : concaveCylinderList) {
            if (item.getId().equals(concaveCylinderLeft)) {
                // 柱镜（左）
                salesRecordForm.setConcaveCylinderLeft(item.getValue());
            }
            if (item.getId().equals(concaveCylinderRight)) {
                // 柱镜（右）
                salesRecordForm.setConcaveCylinderRight(item.getValue());
            }
        }

        // 轴位（左）
        salesRecordForm.setAxialDirectionLeft(StringUtil.valueOf(salesRecordInfo.get("AXIAL_DIRECTION_LEFT")));
        // 远用瞳距（左）
        salesRecordForm.setInterpupillaryDistanceFarLeft(StringUtil.valueOf(salesRecordInfo.get("INTERPUPILLARY_DISTANCE_FAR_LEFT")));
        // 近用瞳距（左）
        salesRecordForm.setInterpupillaryDistanceCloseLeft(StringUtil.valueOf(salesRecordInfo.get("INTERPUPILLARY_DISTANCE_CLOSE_LEFT")));
        // 度数（左）
        salesRecordForm.setDegreeLeft(StringUtil.valueOf(salesRecordInfo.get("DEGREE_LEFT")));
        // 轴位（右）
        salesRecordForm.setAxialDirectionRight(StringUtil.valueOf(salesRecordInfo.get("AXIAL_DIRECTION_RIGHT")));
        // 远用瞳距（右）
        salesRecordForm.setInterpupillaryDistanceFarRight(StringUtil.valueOf(salesRecordInfo.get("INTERPUPILLARY_DISTANCE_FAR_RIGHT")));
        // 近用瞳距（右）
        salesRecordForm.setInterpupillaryDistanceCloseRight(StringUtil.valueOf(salesRecordInfo.get("INTERPUPILLARY_DISTANCE_CLOSE_RIGHT")));
        // 度数（右）
        salesRecordForm.setDegreeRight(StringUtil.valueOf(salesRecordInfo.get("DEGREE_RIGHT")));
        // 状态
        salesRecordForm.setGlassStatus(StringUtil.valueOf(salesRecordInfo.get("GLASS_STATUS")));
        // 取镜日期
        salesRecordForm.setGlassTakenDate(StringUtil.valueOf(salesRecordInfo.get("GLASS_TAKEN_DATE")));
        // 复查日期
        salesRecordForm.setReExamineDate(StringUtil.valueOf(salesRecordInfo.get("REEXAMINE_DATE")));
        // 总金额（元）
        salesRecordForm.setTotalAmount(CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(salesRecordInfo.get("TOTAL_AMOUNT")))));
        // 总折扣金额（元）
        salesRecordForm.setTotalDiscount(CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(salesRecordInfo.get("TOTAL_DISCOUNT")))));
        // 消费金额（元）
        salesRecordForm.setExpense(StringUtil.valueOf(StringUtil.getInt(salesRecordForm.getTotalAmount()) - StringUtil.getInt(salesRecordForm.getTotalDiscount())));
        // 备注
        salesRecordForm.setComment(StringUtil.valueOf(salesRecordInfo.get("COMMENT")));

        String optometrist = StringUtil.valueOf(salesRecordInfo.get("OPTOMERTRIST"));
        String lensDispenser = StringUtil.valueOf(salesRecordInfo.get("LENS_DISPENSER"));
        String processor = StringUtil.valueOf(salesRecordInfo.get("PROCESSOR"));
        String inspector = StringUtil.valueOf(salesRecordInfo.get("INSPECTOR"));
        String cashier = StringUtil.valueOf(salesRecordInfo.get("CASHIER"));
        for (EmployeeBean item : employeeList) {
            if (item.getId().equals(optometrist)) {
                // 验光师
                salesRecordForm.setOptometrist(item.getName());
            }
            if (item.getId().equals(lensDispenser)) {
                // 配镜师
                salesRecordForm.setLensDispenser(item.getName());
            }
            if (item.getId().equals(processor)) {
                // 加工师
                salesRecordForm.setProcessor(item.getName());
            }
            if (item.getId().equals(inspector)) {
                // 检验员
                salesRecordForm.setInspector(item.getName());
            }
            if (item.getId().equals(cashier)) {
                // 收银员
                salesRecordForm.setCashier(item.getName());
            }
        }
    }

    /**
     * 编辑销售记录对应的商品详细信息
     * 
     * @param salesGoodsList
     * @param salesRecordForm
     */
    private void editSalesGoodsList(List<Map<String, Object>> salesGoodsList, SalesRecordForm salesRecordForm) {
        // 取得商品类型表数据
        List<GoodsTypeBean> goodsTypeList  = salesRecordDao.getGoodsTypeList();
        if (salesGoodsList != null && salesGoodsList.size() > 0 ) {
            for (Map<String, Object> item : salesGoodsList) {
                // 单价（元）
                item.put("PRICE", CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(item.get("PRICE")))));
                // 金额（元）
                item.put("AMOUNT", CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(item.get("AMOUNT")))));
                // 折扣
                String discountType = StringUtil.valueOf(item.get("DISCOUNT_TYPE"));
                if (CodeConstant.DISCOUNT_TYPE_DAZHE.equals(discountType)) {
                    // 打折
                    item.put("DISCOUNT_TYPE", CodeConstant.DISCOUNT_TYPE_NAME_DAZHE);
                } else if (CodeConstant.DISCOUNT_TYPE_LINGTOU.equals(discountType)) {
                    // 去零头
                    item.put("DISCOUNT_TYPE", CodeConstant.DISCOUNT_TYPE_NAME_LINGTOU);
                    item.put("DISCOUNT", CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(item.get("DISCOUNT")))));
                }
                // 商品类型
                for (GoodsTypeBean goodsType : goodsTypeList) {
                    if (goodsType.getId().equals(StringUtil.valueOf(item.get("GOODS_TYPE")))) {
                        item.put("GOODS_TYPE", goodsType.getName());
                        break;
                    }
                }
            }
        }

        salesRecordForm.setGoodsList(salesGoodsList);
    }
}
