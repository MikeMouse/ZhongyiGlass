package com.zhongyi.glass.form;

import java.util.List;
import java.util.Map;

import com.zhongyi.glass.bean.ConcaveCylinderBean;
import com.zhongyi.glass.bean.ConcaveSphereBean;
import com.zhongyi.glass.bean.EmployeeBean;
import com.zhongyi.glass.bean.GoodsTypeBean;
import com.zhongyi.glass.bean.VisualAcuityBean;

/**
 * 销售记录Form
 * 
 * @author liqianxi
 * @date 2013-06-29
 */
public class SalesRecordForm extends BasePageForm {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5867206575692430055L;

    /**
     * 销售记录ID
     */
    private String salesRecordId;

    /**
     * 顾客ID
     */
    private String customerId;

    /**
     * 顾客姓名
     */
    private String customerName;

    /**
     * 移动电话
     */
    private String mobilePhone;

    /**
     * 家庭电话
     */
    private String homePhone;

    /**
     * 销售记录查询结果列表
     */
    private List<Map<String, Object>> searchResultList;

    /**
     * 眼镜类型
     */
    private String glassType;

    /**
     * 裸眼视力（左）
     */
    private String uncorrectedVisualAcuityLeft;

    /**
     * 裸眼视力（右）
     */
    private String uncorrectedVisualAcuityRight;

    /**
     * 球镜（左）
     */
    private String concaveSphereLeft;

    /**
     * 球镜（右）
     */
    private String concaveSphereRight;

    /**
     * 柱镜（左）
     */
    private String concaveCylinderLeft;

    /**
     * 柱镜（右）
     */
    private String concaveCylinderRight;

    /**
     * 轴位（左）
     */
    private String axialDirectionLeft;

    /**
     * 轴位（右）
     */
    private String axialDirectionRight;

    /**
     * 矫正视力（左）
     */
    private String correctedVisualAcuityLeft;

    /**
     * 矫正视力（右）
     */
    private String correctedVisualAcuityRight;

    /**
     * 远用瞳距（左）
     */
    private String interpupillaryDistanceFarLeft;

    /**
     * 远用瞳距（右）
     */
    private String interpupillaryDistanceFarRight;

    /**
     * 近用瞳距（左）
     */
    private String interpupillaryDistanceCloseLeft;

    /**
     * 近用瞳距（右）
     */
    private String interpupillaryDistanceCloseRight;

    /**
     * 度数（左）
     */
    private String degreeLeft;

    /**
     * 度数（右）
     */
    private String degreeRight;

    /**
     * 取镜日期
     */
    private String glassStatus;

    /**
     * 眼镜状态
     */
    private String glassTakenDate;

    /**
     * 复查日期
     */
    private String reExamineDate;

    /**
     * 验光师
     */
    private String optometrist;

    /**
     * 配镜师
     */
    private String lensDispenser;

    /**
     * 加工师
     */
    private String processor;

    /**
     * 检验员
     */
    private String inspector;

    /**
     * 收银员
     */
    private String cashier;

    /**
     * 视力表
     */
    private List<VisualAcuityBean> visualAcuityList = null;

    /**
     * 球镜表
     */
    private List<ConcaveSphereBean> concaveSphereList = null;

    /**
     * 柱镜表
     */
    private List<ConcaveCylinderBean> concaveCylinderList = null;

    /**
     * 职员表
     */
    private List<EmployeeBean> employeeList = null;

    /**
     * 商品类型表
     */
    private List<GoodsTypeBean> goodsTypeList = null;

    /**
     * 备注
     */
    private String comment;

    /**
     * 操作类型
     */
    private String operateType;

    /**
     * 商品序号
     */
    private String[] goodsNo;

    /**
     * 商品类型
     */
    private String[] goodsType;

    /**
     * 品牌
     */
    private String[] goodsBrand;

    /**
     * 型号
     */
    private String[] goodsBrandSubType;

    /**
     * 单价
     */
    private String[] goodsPrice;

    /**
     * 数量
     */
    private String[] goodsQuantity;

    /**
     * 折扣类型
     */
    private String[] goodsDiscountType;

    /**
     * 折扣
     */
    private String[] goodsDiscount;

    /**
     * 金额
     */
    private String[] goodsAmount;

    /**
     * 一个销售记录包含的商品列表
     */
    private List<Map<String, Object>> goodsList;

    /**
     * 总金额
     */
    private String totalAmount;

    /**
     * 总金额去零头
     */
    private String totalDiscount;

    /**
     * 消费金额
     */
    private String expense;

    /**
     * 历史销售记录信息列表
     */
    private List<Map<String, Object>> salesRecordHistoryList;

    /**
     * 取得salesRecordId
     * @return salesRecordId
     */
    public String getSalesRecordId() {
        return salesRecordId;
    }

    /**
     * 设定salesRecordId
     * @param salesRecordId salesRecordId
     */
    public void setSalesRecordId(String salesRecordId) {
        this.salesRecordId = salesRecordId;
    }

    /**
     * 取得customerId
     * @return customerId
     */
    public String getCustomerId() {
        return customerId;
    }

    /**
     * 设定customerId
     * @param customerId customerId
     */
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    /**
     * 取得customerId
     * @return customerId
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * 设定customerName
     * @param customerName customerName
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * 取得mobilePhone
     * @return mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * 设定mobilePhone
     * @param mobilePhone mobilePhone
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * 取得homePhone
     * @return homePhone
     */
    public String getHomePhone() {
        return homePhone;
    }

    /**
     * 设定homePhone
     * @param homePhone homePhone
     */
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    /**
     * 取得searchResultList
     * @return searchResultList
     */
    public List<Map<String, Object>> getSearchResultList() {
        return searchResultList;
    }

    /**
     * 设定searchResultList
     * @param searchResultList searchResultList
     */
    public void setSearchResultList(List<Map<String, Object>> searchResultList) {
        this.searchResultList = searchResultList;
    }

    /**
     * 取得glassType
     * @return glassType
     */
    public String getGlassType() {
        return glassType;
    }

    /**
     * 设定glassType
     * @param glassType glassType
     */
    public void setGlassType(String glassType) {
        this.glassType = glassType;
    }

    /**
     * 取得uncorrectedVisualAcuityLeft
     * @return uncorrectedVisualAcuityLeft
     */
    public String getUncorrectedVisualAcuityLeft() {
        return uncorrectedVisualAcuityLeft;
    }

    /**
     * 设定uncorrectedVisualAcuityLeft
     * @param uncorrectedVisualAcuityLeft uncorrectedVisualAcuityLeft
     */
    public void setUncorrectedVisualAcuityLeft(String uncorrectedVisualAcuityLeft) {
        this.uncorrectedVisualAcuityLeft = uncorrectedVisualAcuityLeft;
    }

    /**
     * 取得uncorrectedVisualAcuityRight
     * @return uncorrectedVisualAcuityRight
     */
    public String getUncorrectedVisualAcuityRight() {
        return uncorrectedVisualAcuityRight;
    }

    /**
     * 设定uncorrectedVisualAcuityRight
     * @param uncorrectedVisualAcuityRight uncorrectedVisualAcuityRight
     */
    public void setUncorrectedVisualAcuityRight(String uncorrectedVisualAcuityRight) {
        this.uncorrectedVisualAcuityRight = uncorrectedVisualAcuityRight;
    }

    /**
     * 取得concaveSphereLeft
     * @return concaveSphereLeft
     */
    public String getConcaveSphereLeft() {
        return concaveSphereLeft;
    }

    /**
     * 设定concaveSphereLeft
     * @param concaveSphereLeft concaveSphereLeft
     */
    public void setConcaveSphereLeft(String concaveSphereLeft) {
        this.concaveSphereLeft = concaveSphereLeft;
    }

    /**
     * 取得concaveSphereRight
     * @return concaveSphereRight
     */
    public String getConcaveSphereRight() {
        return concaveSphereRight;
    }

    /**
     * 设定concaveSphereRight
     * @param concaveSphereRight concaveSphereRight
     */
    public void setConcaveSphereRight(String concaveSphereRight) {
        this.concaveSphereRight = concaveSphereRight;
    }

    /**
     * 取得concaveCylinderLeft
     * @return concaveCylinderLeft
     */
    public String getConcaveCylinderLeft() {
        return concaveCylinderLeft;
    }

    /**
     * 设定concaveCylinderLeft
     * @param concaveCylinderLeft concaveCylinderLeft
     */
    public void setConcaveCylinderLeft(String concaveCylinderLeft) {
        this.concaveCylinderLeft = concaveCylinderLeft;
    }

    /**
     * 取得concaveCylinderRight
     * @return concaveCylinderRight
     */
    public String getConcaveCylinderRight() {
        return concaveCylinderRight;
    }

    /**
     * 设定concaveCylinderRight
     * @param concaveCylinderRight concaveCylinderRight
     */
    public void setConcaveCylinderRight(String concaveCylinderRight) {
        this.concaveCylinderRight = concaveCylinderRight;
    }

    /**
     * 取得axialDirectionLeft
     * @return axialDirectionLeft
     */
    public String getAxialDirectionLeft() {
        return axialDirectionLeft;
    }

    /**
     * 设定axialDirectionLeft
     * @param axialDirectionLeft axialDirectionLeft
     */
    public void setAxialDirectionLeft(String axialDirectionLeft) {
        this.axialDirectionLeft = axialDirectionLeft;
    }

    /**
     * 取得axialDirectionRight
     * @return axialDirectionRight
     */
    public String getAxialDirectionRight() {
        return axialDirectionRight;
    }

    /**
     * 设定axialDirectionRight
     * @param axialDirectionRight axialDirectionRight
     */
    public void setAxialDirectionRight(String axialDirectionRight) {
        this.axialDirectionRight = axialDirectionRight;
    }

    /**
     * 取得correctedVisualAcuityLeft
     * @return correctedVisualAcuityLeft
     */
    public String getCorrectedVisualAcuityLeft() {
        return correctedVisualAcuityLeft;
    }

    /**
     * 设定correctedVisualAcuityLeft
     * @param correctedVisualAcuityLeft correctedVisualAcuityLeft
     */
    public void setCorrectedVisualAcuityLeft(String correctedVisualAcuityLeft) {
        this.correctedVisualAcuityLeft = correctedVisualAcuityLeft;
    }

    /**
     * 取得correctedVisualAcuityRight
     * @return correctedVisualAcuityRight
     */
    public String getCorrectedVisualAcuityRight() {
        return correctedVisualAcuityRight;
    }

    /**
     * 设定correctedVisualAcuityRight
     * @param correctedVisualAcuityRight correctedVisualAcuityRight
     */
    public void setCorrectedVisualAcuityRight(String correctedVisualAcuityRight) {
        this.correctedVisualAcuityRight = correctedVisualAcuityRight;
    }

    /**
     * 取得interpupillaryDistanceFarLeft
     * @return interpupillaryDistanceFarLeft
     */
    public String getInterpupillaryDistanceFarLeft() {
        return interpupillaryDistanceFarLeft;
    }

    /**
     * 设定interpupillaryDistanceFarLeft
     * @param interpupillaryDistanceFarLeft interpupillaryDistanceFarLeft
     */
    public void setInterpupillaryDistanceFarLeft(String interpupillaryDistanceFarLeft) {
        this.interpupillaryDistanceFarLeft = interpupillaryDistanceFarLeft;
    }

    /**
     * 取得interpupillaryDistanceFarRight
     * @return interpupillaryDistanceFarRight
     */
    public String getInterpupillaryDistanceFarRight() {
        return interpupillaryDistanceFarRight;
    }

    /**
     * 设定interpupillaryDistanceFarRight
     * @param interpupillaryDistanceFarRight interpupillaryDistanceFarRight
     */
    public void setInterpupillaryDistanceFarRight(String interpupillaryDistanceFarRight) {
        this.interpupillaryDistanceFarRight = interpupillaryDistanceFarRight;
    }

    /**
     * 取得interpupillaryDistanceCloseLeft
     * @return interpupillaryDistanceCloseLeft
     */
    public String getInterpupillaryDistanceCloseLeft() {
        return interpupillaryDistanceCloseLeft;
    }

    /**
     * 设定interpupillaryDistanceCloseLeft
     * @param interpupillaryDistanceCloseLeft interpupillaryDistanceCloseLeft
     */
    public void setInterpupillaryDistanceCloseLeft(String interpupillaryDistanceCloseLeft) {
        this.interpupillaryDistanceCloseLeft = interpupillaryDistanceCloseLeft;
    }

    /**
     * 取得interpupillaryDistanceCloseRight
     * @return interpupillaryDistanceCloseRight
     */
    public String getInterpupillaryDistanceCloseRight() {
        return interpupillaryDistanceCloseRight;
    }

    /**
     * 设定interpupillaryDistanceCloseRight
     * @param interpupillaryDistanceCloseRight interpupillaryDistanceCloseRight
     */
    public void setInterpupillaryDistanceCloseRight(String interpupillaryDistanceCloseRight) {
        this.interpupillaryDistanceCloseRight = interpupillaryDistanceCloseRight;
    }

    /**
     * 取得degreeLeft
     * @return degreeLeft
     */
    public String getDegreeLeft() {
        return degreeLeft;
    }

    /**
     * 设定degreeLeft
     * @param degreeLeft degreeLeft
     */
    public void setDegreeLeft(String degreeLeft) {
        this.degreeLeft = degreeLeft;
    }

    /**
     * 取得degreeRight
     * @return degreeRight
     */
    public String getDegreeRight() {
        return degreeRight;
    }

    /**
     * 设定degreeRight
     * @param degreeRight degreeRight
     */
    public void setDegreeRight(String degreeRight) {
        this.degreeRight = degreeRight;
    }

    /**
     * 取得glassStatus
     * @return glassStatus
     */
    public String getGlassStatus() {
        return glassStatus;
    }

    /**
     * 设定glassStatus
     * @param glassStatus glassStatus
     */
    public void setGlassStatus(String glassStatus) {
        this.glassStatus = glassStatus;
    }

    /**
     * 取得glassTakenDate
     * @return glassTakenDate
     */
    public String getGlassTakenDate() {
        return glassTakenDate;
    }

    /**
     * 设定glassTakenDate
     * @param glassTakenDate glassTakenDate
     */
    public void setGlassTakenDate(String glassTakenDate) {
        this.glassTakenDate = glassTakenDate;
    }

    /**
     * 取得reExamineDate
     * @return reExamineDate
     */
    public String getReExamineDate() {
        return reExamineDate;
    }

    /**
     * 设定reExamineDate
     * @param reExamineDate reExamineDate
     */
    public void setReExamineDate(String reExamineDate) {
        this.reExamineDate = reExamineDate;
    }

    /**
     * 取得optometrist
     * @return optometrist
     */
    public String getOptometrist() {
        return optometrist;
    }

    /**
     * 设定optometrist
     * @param optometrist optometrist
     */
    public void setOptometrist(String optometrist) {
        this.optometrist = optometrist;
    }

    /**
     * 取得lensDispenser
     * @return lensDispenser
     */
    public String getLensDispenser() {
        return lensDispenser;
    }

    /**
     * 设定lensDispenser
     * @param lensDispenser lensDispenser
     */
    public void setLensDispenser(String lensDispenser) {
        this.lensDispenser = lensDispenser;
    }

    /**
     * 取得processor
     * @return processor
     */
    public String getProcessor() {
        return processor;
    }

    /**
     * 设定processor
     * @param processor processor
     */
    public void setProcessor(String processor) {
        this.processor = processor;
    }

    /**
     * 取得inspector
     * @return inspector
     */
    public String getInspector() {
        return inspector;
    }

    /**
     * 设定inspector
     * @param inspector inspector
     */
    public void setInspector(String inspector) {
        this.inspector = inspector;
    }

    /**
     * 取得cashier
     * @return cashier
     */
    public String getCashier() {
        return cashier;
    }

    /**
     * 设定cashier
     * @param cashier cashier
     */
    public void setCashier(String cashier) {
        this.cashier = cashier;
    }

    /**
     * 取得visualAcuityList
     * @return visualAcuityList
     */
    public List<VisualAcuityBean> getVisualAcuityList() {
        return visualAcuityList;
    }

    /**
     * 设定visualAcuityList
     * @param visualAcuityList visualAcuityList
     */
    public void setVisualAcuityList(List<VisualAcuityBean> visualAcuityList) {
        this.visualAcuityList = visualAcuityList;
    }

    /**
     * 取得concaveSphereList
     * @return concaveSphereList
     */
    public List<ConcaveSphereBean> getConcaveSphereList() {
        return concaveSphereList;
    }

    /**
     * 设定concaveSphereList
     * @param concaveSphereList concaveSphereList
     */
    public void setConcaveSphereList(List<ConcaveSphereBean> concaveSphereList) {
        this.concaveSphereList = concaveSphereList;
    }

    /**
     * 取得concaveCylinderList
     * @return concaveCylinderList
     */
    public List<ConcaveCylinderBean> getConcaveCylinderList() {
        return concaveCylinderList;
    }

    /**
     * 设定concaveCylinderList
     * @param concaveCylinderList concaveCylinderList
     */
    public void setConcaveCylinderList(List<ConcaveCylinderBean> concaveCylinderList) {
        this.concaveCylinderList = concaveCylinderList;
    }

    /**
     * 取得employeeList
     * @return employeeList
     */
    public List<EmployeeBean> getEmployeeList() {
        return employeeList;
    }

    /**
     * 设定employeeList
     * @param employeeList employeeList
     */
    public void setEmployeeList(List<EmployeeBean> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * 取得goodsTypeList
     * @return goodsTypeList
     */
    public List<GoodsTypeBean> getGoodsTypeList() {
        return goodsTypeList;
    }

    /**
     * 设定goodsTypeList
     * @param goodsTypeList goodsTypeList
     */
    public void setGoodsTypeList(List<GoodsTypeBean> goodsTypeList) {
        this.goodsTypeList = goodsTypeList;
    }

    /**
     * 取得comment
     * 
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设定comment
     * 
     * @param comment comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 取得操作类型
     * 
     * @return 操作类型
     */
    public String getOperateType() {
        return operateType;
    }

    /**
     * 设定操作类型
     * 
     * @param operateType 操作类型
     */
    public void setOperateType(String operateType) {
        this.operateType = operateType;
    }

    /**
     * 取得goodsNo
     * @return goodsNo
     */
    public String[] getGoodsNo() {
        return goodsNo;
    }

    /**
     * 设定goodsNo
     * @param goodsNo goodsNo
     */
    public void setGoodsNo(String[] goodsNo) {
        this.goodsNo = goodsNo;
    }

    /**
     * 取得goodsType
     * @return goodsType
     */
    public String[] getGoodsType() {
        return goodsType;
    }

    /**
     * 设定goodsType
     * @param goodsType goodsType
     */
    public void setGoodsType(String[] goodsType) {
        this.goodsType = goodsType;
    }

    /**
     * 取得goodsBrand
     * @return goodsBrand
     */
    public String[] getGoodsBrand() {
        return goodsBrand;
    }

    /**
     * 设定goodsBrand
     * @param goodsBrand goodsBrand
     */
    public void setGoodsBrand(String[] goodsBrand) {
        this.goodsBrand = goodsBrand;
    }

    /**
     * 取得goodsBrandSubType
     * @return goodsBrandSubType
     */
    public String[] getGoodsBrandSubType() {
        return goodsBrandSubType;
    }

    /**
     * 设定goodsBrandSubType
     * @param goodsBrandSubType goodsBrandSubType
     */
    public void setGoodsBrandSubType(String[] goodsBrandSubType) {
        this.goodsBrandSubType = goodsBrandSubType;
    }

    /**
     * 取得goodsPrice
     * @return goodsPrice
     */
    public String[] getGoodsPrice() {
        return goodsPrice;
    }

    /**
     * 设定goodsPrice
     * @param goodsPrice goodsPrice
     */
    public void setGoodsPrice(String[] goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    /**
     * 取得goodsQuantity
     * @return goodsQuantity
     */
    public String[] getGoodsQuantity() {
        return goodsQuantity;
    }

    /**
     * 设定goodsQuantity
     * @param goodsQuantity goodsQuantity
     */
    public void setGoodsQuantity(String[] goodsQuantity) {
        this.goodsQuantity = goodsQuantity;
    }

    /**
     * 取得goodsDiscountType
     * @return goodsDiscountType
     */
    public String[] getGoodsDiscountType() {
        return goodsDiscountType;
    }

    /**
     * 设定goodsDiscountType
     * @param goodsDiscountType goodsDiscountType
     */
    public void setGoodsDiscountType(String[] goodsDiscountType) {
        this.goodsDiscountType = goodsDiscountType;
    }

    /**
     * 取得goodsDiscount
     * @return goodsDiscount
     */
    public String[] getGoodsDiscount() {
        return goodsDiscount;
    }

    /**
     * 设定goodsDiscount
     * @param goodsDiscount goodsDiscount
     */
    public void setGoodsDiscount(String[] goodsDiscount) {
        this.goodsDiscount = goodsDiscount;
    }

    /**
     * 取得goodsAmount
     * @return goodsAmount
     */
    public String[] getGoodsAmount() {
        return goodsAmount;
    }

    /**
     * 设定goodsAmount
     * @param goodsAmount goodsAmount
     */
    public void setGoodsAmount(String[] goodsAmount) {
        this.goodsAmount = goodsAmount;
    }

    /**
     * 取得goodsList
     * @return goodsList
     */
    public List<Map<String, Object>> getGoodsList() {
        return goodsList;
    }

    /**
     * 设定goodsList
     * @param goodsList goodsList
     */
    public void setGoodsList(List<Map<String, Object>> goodsList) {
        this.goodsList = goodsList;
    }

    /**
     * 取得totalAmount
     * @return totalAmount
     */
    public String getTotalAmount() {
        return totalAmount;
    }

    /**
     * 设定totalAmount
     * @param totalAmount totalAmount
     */
    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    /**
     * 取得totalDiscount
     * @return totalDiscount
     */
    public String getTotalDiscount() {
        return totalDiscount;
    }

    /**
     * 设定totalDiscount
     * @param totalDiscount totalDiscount
     */
    public void setTotalDiscount(String totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    /**
     * 取得expense
     * @return expense
     */
    public String getExpense() {
        return expense;
    }

    /**
     * 设定expense
     * @param expense expense
     */
    public void setExpense(String expense) {
        this.expense = expense;
    }

    /**
     * 取得salesRecordHistoryList
     * @return salesRecordHistoryList
     */
    public List<Map<String, Object>> getSalesRecordHistoryList() {
        return salesRecordHistoryList;
    }

    /**
     * 设定salesRecordHistoryList
     * @param salesRecordHistoryList salesRecordHistoryList
     */
    public void setSalesRecordHistoryList(List<Map<String, Object>> salesRecordHistoryList) {
        this.salesRecordHistoryList = salesRecordHistoryList;
    }

    /**
     * 清空Form
     */
    @Override
    public void clear() {
        super.clear();
        this.axialDirectionLeft = null;
        this.axialDirectionRight = null;
        this.cashier = null;
        this.comment = null;
        this.concaveCylinderLeft = null;
        this.concaveCylinderRight = null;
        this.concaveCylinderList = null;
        this.concaveSphereLeft = null;
        this.concaveSphereRight = null;
        this.concaveSphereList = null;
        this.correctedVisualAcuityLeft = null;
        this.correctedVisualAcuityRight = null;
        this.customerId = null;
        this.customerName = null;
        this.degreeLeft = null;
        this.degreeRight = null;
        this.employeeList = null;
        this.expense = null;
        this.glassStatus = null;
        this.glassTakenDate = null;
        this.glassType = null;
        this.goodsAmount = null;
        this.goodsBrand = null;
        this.goodsBrandSubType = null;
        this.goodsDiscount = null;
        this.goodsDiscountType = null;
        this.goodsList = null;
        this.goodsNo = null;
        this.goodsPrice = null;
        this.goodsQuantity = null;
        this.goodsType = null;
        this.goodsTypeList = null;
        this.homePhone = null;
        this.inspector = null;
        this.interpupillaryDistanceCloseLeft = null;
        this.interpupillaryDistanceCloseRight = null;
        this.interpupillaryDistanceFarLeft = null;
        this.interpupillaryDistanceFarRight = null;
        this.lensDispenser = null;
        this.mobilePhone = null;
        this.operateType = null;
        this.optometrist = null;
        this.processor = null;
        this.reExamineDate = null;
        this.salesRecordId = null;
        this.salesRecordHistoryList = null;
        this.searchResultList = null;
        this.totalAmount = null;
        this.totalDiscount = null;
        this.uncorrectedVisualAcuityLeft = null;
        this.uncorrectedVisualAcuityRight = null;
        this.visualAcuityList = null;
    }
}
