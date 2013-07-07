package com.zhongyi.glass.action;

import java.util.ArrayList;
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
import com.zhongyi.glass.util.DateUtil;
import com.zhongyi.glass.util.StringUtil;

/**
 * 历史销售记录查看
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class SalesRecordDetailAction extends BaseAction {

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
        // 取得该顾客的历史消费记录
        List<Map<String, Object>> salesRecordList = salesRecordDao.getSalesRecordListByCustomer(salesRecordForm.getCustomerId());
        List<Map<String, Object>> salesRecordHistoryList = new ArrayList<Map<String, Object>>();
        setDetailList(salesRecordHistoryList, salesRecordList);

        salesRecordForm.setCustomerName(StringUtil.valueOf(salesRecordList.get(0).get("CUSTOMER_NAME")));
        salesRecordForm.setSalesRecordHistoryList(salesRecordHistoryList);
        salesRecordForm.setOperateType(null);
        return mapping.findForward("success");
    }

    @Override
    public Class<? extends BaseAction> getClazz() {
        return SalesRecordDetailAction.class;
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
     * @param SalesRecordDao 销售记录DAO
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
     * 编辑详细列表记录信息
     * 
     * @param detailList 详细列表记录信息
     * @param salesRecordList 历史消费记录
     */
    @SuppressWarnings("unchecked")
    private void setDetailList(List<Map<String, Object>> detailList, List<Map<String, Object>> salesRecordList) {
        if (salesRecordList != null && salesRecordList.size() > 0) {
            String preSalesRecordId = StringUtil.valueOf(salesRecordList.get(0).get("SALES_RECORD_ID"));
            Map<String, Object> firstSalesRecordIdInfo = new HashMap<String, Object>();
            // 上条数据库记录的销售记录ID
            firstSalesRecordIdInfo.put("SALES_RECORD_ID", preSalesRecordId);
            // 设定销售记录基本信息
            setSalesRecordInfo(firstSalesRecordIdInfo, salesRecordList.get(0));
            // 初始化该销售记录的商品列表
            firstSalesRecordIdInfo.put("SALES_GOODS_LIST", new ArrayList<Map<String, Object>>());
            // 存入第一条销售记录
            detailList.add(firstSalesRecordIdInfo);

            // 当前数据库记录销售记录ID
            String currentSalesRecordId = null;
            for (int i = 0; i < salesRecordList.size(); i++) {
                currentSalesRecordId = StringUtil.valueOf(salesRecordList.get(i).get("SALES_RECORD_ID"));
                if (StringUtil.equals(preSalesRecordId, currentSalesRecordId)) {
                    // 前一销售记录ID与当前销售记录ID一致
                    List<Map<String, Object>> salesGoodsList =
                            (List<Map<String, Object>>) detailList.get(detailList.size() - 1).get("SALES_GOODS_LIST");
                    // 设定该销售记录包含的商品信息
                    setSalesGoodsInfo(salesGoodsList, salesRecordList.get(i));

                } else {
                    // 前一销售记录ID与当前销售记录ID不一致
                    Map<String, Object> salesRecordInfo = new HashMap<String, Object>();
                    // 设定销售记录基本信息
                    setSalesRecordInfo(salesRecordInfo, salesRecordList.get(i));
                    // 初始化该销售记录的商品列表
                    salesRecordInfo.put("SALES_GOODS_LIST", new ArrayList<Map<String, Object>>());
                    setSalesGoodsInfo((List<Map<String, Object>>) salesRecordInfo.get("SALES_GOODS_LIST"), salesRecordList.get(i));
                    // 存入该条销售记录
                    detailList.add(salesRecordInfo);

                    // 修改上条数据库记录销售记录ID
                    preSalesRecordId = currentSalesRecordId;
                }
            }
        }
    }

    /**
     * 设定销售记录信息
     * 
     * @param salesRecordIdInfo 每个销售记录ID的信息
     * @param salesRecordListItemInfo 数据库查询出的每条记录
     */
    private void setSalesRecordInfo(Map<String, Object> salesRecordIdInfo, Map<String, Object> salesRecordListItemInfo) {
        // 取得视力表数据
        List<VisualAcuityBean> visualAcuityList = salesRecordDao.getVisualAcuityList();
        // 取得球镜表数据
        List<ConcaveSphereBean> concaveSphereList = salesRecordDao.getConcaveSphereList();
        // 取得柱镜表数据
        List<ConcaveCylinderBean> concaveCylinderList = salesRecordDao.getConcaveCylinderList();
        // 取得职员表数据
        List<EmployeeBean> employeeList = employeeDao.getAllEmployeeList();

        // 配镜日期
        salesRecordIdInfo.put("UPDATE_TIME",
                DateUtil.convertTimestampToDateStr(StringUtil.getLong(StringUtil.valueOf(salesRecordListItemInfo.get("UPDATE_TIME"))), DateUtil.DATE_FORMATE_YYYYMMDD_WITH_HYPHEN));

        // 配镜类型
        String glassType = StringUtil.valueOf(salesRecordListItemInfo.get("GLASS_TYPE"));
        if (CodeConstant.GLASS_TYPE_JIAJING.equals(glassType)) {
            salesRecordIdInfo.put("GLASS_TYPE", CodeConstant.GLASS_TYPE_NAME_JIAJING);
        } else if (CodeConstant.GLASS_TYPE_YINXING.equals(glassType)) {
            salesRecordIdInfo.put("GLASS_TYPE", CodeConstant.GLASS_TYPE_NAME_YINXING);
        }

        String uncorrectedVisulaAcuityLeft = StringUtil.valueOf(salesRecordListItemInfo.get("UNCORRECTED_VISUAL_ACUITY_LEFT"));
        String uncorrectedVisulaAcuityRight = StringUtil.valueOf(salesRecordListItemInfo.get("UNCORRECTED_VISUAL_ACUITY_RIGHT"));
        String correctedVisulaAcuityLeft = StringUtil.valueOf(salesRecordListItemInfo.get("CORRECTED_VISUAL_ACUITY_LEFT"));
        String correctedVisulaAcuityRight = StringUtil.valueOf(salesRecordListItemInfo.get("CORRECTED_VISUAL_ACUITY_RIGHT"));
        for (VisualAcuityBean item : visualAcuityList) {
            if (item.getId().equals(uncorrectedVisulaAcuityLeft)) {
                // 裸眼视力（左）
                salesRecordIdInfo.put("UNCORRECTED_VISUAL_ACUITY_LEFT", item.getValue());
            }
            if (item.getId().equals(uncorrectedVisulaAcuityRight)) {
                // 裸眼视力（右）
                salesRecordIdInfo.put("UNCORRECTED_VISUAL_ACUITY_RIGHT", item.getValue());
            }
            if (item.getId().equals(correctedVisulaAcuityLeft)) {
                // 矫正视力（左）
                salesRecordIdInfo.put("CORRECTED_VISUAL_ACUITY_LEFT", item.getValue());
            }
            if (item.getId().equals(correctedVisulaAcuityRight)) {
                // 矫正视力（右）
                salesRecordIdInfo.put("CORRECTED_VISUAL_ACUITY_RIGHT", item.getValue());
            }
        }

        String concaveSphereLeft = StringUtil.valueOf(salesRecordListItemInfo.get("CONCAVE_SPHERE_LEFT"));
        String concaveSphereRight = StringUtil.valueOf(salesRecordListItemInfo.get("CONCAVE_SPHERE_RIGHT"));
        for (ConcaveSphereBean item : concaveSphereList) {
            if (item.getId().equals(concaveSphereLeft)) {
                // 球镜（左）
                salesRecordIdInfo.put("CONCAVE_SPHERE_LEFT", item.getValue());
            }
            if (item.getId().equals(concaveSphereRight)) {
                // 球镜（右）
                salesRecordIdInfo.put("CONCAVE_SPHERE_RIGHT", item.getValue());
            }
        }

        String concaveCylinderLeft = StringUtil.valueOf(salesRecordListItemInfo.get("CONCAVE_CYLINDER_LEFT"));
        String concaveCylinderRight = StringUtil.valueOf(salesRecordListItemInfo.get("CONCAVE_CYLINDER_RIGHT"));
        for (ConcaveCylinderBean item : concaveCylinderList) {
            if (item.getId().equals(concaveCylinderLeft)) {
                // 柱镜（左）
                salesRecordIdInfo.put("CONCAVE_CYLINDER_LEFT", item.getValue());
            }
            if (item.getId().equals(concaveCylinderRight)) {
                // 柱镜（右）
                salesRecordIdInfo.put("CONCAVE_CYLINDER_RIGHT", item.getValue());
            }
        }

        // 轴位（左）
        salesRecordIdInfo.put("AXIAL_DIRECTION_LEFT", StringUtil.valueOf(salesRecordListItemInfo.get("AXIAL_DIRECTION_LEFT")));
        // 远用瞳距（左）
        salesRecordIdInfo.put("INTERPUPILLARY_DISTANCE_FAR_LEFT", StringUtil.valueOf(salesRecordListItemInfo.get("INTERPUPILLARY_DISTANCE_FAR_LEFT")));
        // 近用瞳距（左）
        salesRecordIdInfo.put("INTERPUPILLARY_DISTANCE_CLOSE_LEFT", StringUtil.valueOf(salesRecordListItemInfo.get("INTERPUPILLARY_DISTANCE_CLOSE_LEFT")));
        // 度数（左）
        salesRecordIdInfo.put("DEGREE_LEFT", StringUtil.valueOf(salesRecordListItemInfo.get("DEGREE_LEFT")));
        // 轴位（右）
        salesRecordIdInfo.put("AXIAL_DIRECTION_RIGHT", StringUtil.valueOf(salesRecordListItemInfo.get("AXIAL_DIRECTION_RIGHT")));
        // 远用瞳距（右）
        salesRecordIdInfo.put("INTERPUPILLARY_DISTANCE_FAR_RIGHT", StringUtil.valueOf(salesRecordListItemInfo.get("INTERPUPILLARY_DISTANCE_FAR_RIGHT")));
        // 近用瞳距（右）
        salesRecordIdInfo.put("INTERPUPILLARY_DISTANCE_CLOSE_RIGHT", StringUtil.valueOf(salesRecordListItemInfo.get("INTERPUPILLARY_DISTANCE_CLOSE_RIGHT")));
        // 度数（右）
        salesRecordIdInfo.put("DEGREE_RIGHT", StringUtil.valueOf(salesRecordListItemInfo.get("DEGREE_RIGHT")));
        // 状态
        String glassStatus = StringUtil.valueOf(salesRecordListItemInfo.get("GLASS_STATUS"));
        if (CodeConstant.GLASS_STATUS_PROCESS.equals(glassStatus)) {
            salesRecordIdInfo.put("GLASS_STATUS", CodeConstant.GLASS_STATUS_NAME_PROCESS);
        } else if (CodeConstant.GLASS_STATUS_WAIT.equals(glassStatus)) {
            salesRecordIdInfo.put("GLASS_STATUS", CodeConstant.GLASS_STATUS_NAME_WAIT);
        } else if (CodeConstant.GLASS_STATUS_TAKEN.equals(glassStatus)) {
            salesRecordIdInfo.put("GLASS_STATUS", CodeConstant.GLASS_STATUS_NAME_TAKEN);
        }
        // 取镜日期
        salesRecordIdInfo.put("GLASS_TAKEN_DATE", StringUtil.valueOf(salesRecordListItemInfo.get("GLASS_TAKEN_DATE")));
        // 复查日期
        salesRecordIdInfo.put("REEXAMINE_DATE", StringUtil.valueOf(salesRecordListItemInfo.get("REEXAMINE_DATE")));
        // 总金额（元）
        long totalAmount = StringUtil.getLong(StringUtil.valueOf(salesRecordListItemInfo.get("TOTAL_AMOUNT")));
        salesRecordIdInfo.put("TOTAL_AMOUNT", CommonUtil.formatDisplayMoney(totalAmount));
        // 总折扣金额（元）
        long totalDiscount = StringUtil.getLong(StringUtil.valueOf(salesRecordListItemInfo.get("TOTAL_DISCOUNT")));
        salesRecordIdInfo.put("TOTAL_DISCOUNT", CommonUtil.formatDisplayMoney(totalDiscount));
        // 消费金额（元）
        salesRecordIdInfo.put("EXPENSE", CommonUtil.formatDisplayMoney(totalAmount - totalDiscount));
        // 备注
        salesRecordIdInfo.put("COMMENT", StringUtil.valueOf(salesRecordListItemInfo.get("COMMENT")));

        String optometrist = StringUtil.valueOf(salesRecordListItemInfo.get("OPTOMERTRIST"));
        String lensDispenser = StringUtil.valueOf(salesRecordListItemInfo.get("LENS_DISPENSER"));
        String processor = StringUtil.valueOf(salesRecordListItemInfo.get("PROCESSOR"));
        String inspector = StringUtil.valueOf(salesRecordListItemInfo.get("INSPECTOR"));
        String cashier = StringUtil.valueOf(salesRecordListItemInfo.get("CASHIER"));
        for (EmployeeBean item : employeeList) {
            if (item.getId().equals(optometrist)) {
                // 验光师
                salesRecordIdInfo.put("OPTOMERTRIST", item.getName());
            }
            if (item.getId().equals(lensDispenser)) {
                // 配镜师
                salesRecordIdInfo.put("LENS_DISPENSER", item.getName());
            }
            if (item.getId().equals(processor)) {
                // 加工师
                salesRecordIdInfo.put("PROCESSOR", item.getName());
            }
            if (item.getId().equals(inspector)) {
                // 检验员
                salesRecordIdInfo.put("INSPECTOR", item.getName());
            }
            if (item.getId().equals(cashier)) {
                // 收银员
                salesRecordIdInfo.put("CASHIER", item.getName());
            }
        }
    }

    /**
     * 设定销售记录信息
     * 
     * @param salesGoodsList 每个销售记录包含的商品列表
     * @param salesRecordListItemInfo 数据库查询出的每条记录
     */
    private void setSalesGoodsInfo(List<Map<String, Object>> salesGoodsList, Map<String, Object> salesRecordListItemInfo) {
        // 取得商品类型表数据
        List<GoodsTypeBean> goodsTypeList  = salesRecordDao.getGoodsTypeList();
        Map<String, Object> salesGoodsInfo = new HashMap<String, Object>();
        // 商品序号
        salesGoodsInfo.put("SALES_GOODS_NO", StringUtil.valueOf(salesRecordListItemInfo.get("SALES_GOODS_NO")));
        // 品牌
        salesGoodsInfo.put("BRAND", StringUtil.valueOf(salesRecordListItemInfo.get("BRAND")));
        // 型号
        salesGoodsInfo.put("BRAND_SUB_TYPE", StringUtil.valueOf(salesRecordListItemInfo.get("BRAND_SUB_TYPE")));
        // 数量
        salesGoodsInfo.put("QUANTITY", StringUtil.valueOf(salesRecordListItemInfo.get("QUANTITY")));
        // 折扣类型
        salesGoodsInfo.put("DISCOUNT_TYPE", StringUtil.valueOf(salesRecordListItemInfo.get("DISCOUNT_TYPE")));
        // 单价（元）
        salesGoodsInfo.put("PRICE", CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(salesRecordListItemInfo.get("PRICE")))));
        // 金额（元）
        salesGoodsInfo.put("AMOUNT", CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(salesRecordListItemInfo.get("AMOUNT")))));
        // 折扣
        String discountType = StringUtil.valueOf(salesRecordListItemInfo.get("DISCOUNT_TYPE"));
        if (CodeConstant.DISCOUNT_TYPE_DAZHE.equals(discountType)) {
            // 打折
            salesGoodsInfo.put("DISCOUNT_TYPE", CodeConstant.DISCOUNT_TYPE_NAME_DAZHE);
            salesGoodsInfo.put("DISCOUNT", StringUtil.valueOf(salesRecordListItemInfo.get("DISCOUNT")));
        } else if (CodeConstant.DISCOUNT_TYPE_LINGTOU.equals(discountType)) {
            // 去零头
            salesGoodsInfo.put("DISCOUNT_TYPE", CodeConstant.DISCOUNT_TYPE_NAME_LINGTOU);
            salesGoodsInfo.put("DISCOUNT", CommonUtil.formatDisplayMoney(StringUtil.getLong(StringUtil.valueOf(salesRecordListItemInfo.get("DISCOUNT")))));
        }
        // 商品类型
        for (GoodsTypeBean goodsType : goodsTypeList) {
            if (goodsType.getId().equals(StringUtil.valueOf(salesRecordListItemInfo.get("GOODS_TYPE")))) {
                salesGoodsInfo.put("GOODS_TYPE", goodsType.getName());
                break;
            }
        }

        salesGoodsList.add(salesGoodsInfo);
    }

}
