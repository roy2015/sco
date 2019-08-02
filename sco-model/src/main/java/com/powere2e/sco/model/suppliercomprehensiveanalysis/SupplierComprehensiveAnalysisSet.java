package com.powere2e.sco.model.suppliercomprehensiveanalysis;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析设置实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class SupplierComprehensiveAnalysisSet extends AppModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8351283314042345445L;
	private String supplierCode;
	private String minDate;
	private String maxDate;
	private String minDate1;//同比1年数据
	private String maxDate1;
	private String minDate2;
	private String maxDate2;
	private String minDate3;
	private String maxDate3;
	private String smallTypeCode;
	private String detailTypeCode;
	private Integer years;
	private String xsbx;
	private String zlqk;
	private String jhqk;
	private String shbx;
	private String xpphqk;
	private Integer lastYear;
	private Integer lastYear1;
	private Integer lastYear2;
	private Integer lastYear3;
	
	//
	private String xsl;//销售量
	private String xsje;//销售金额
	private String mle;//毛利额
	private String xslzball;//销售量占比（占所有商品）
	private String xsjezball;//销售金额占比（占所有商品）
	private String mlezball;//毛利额占比（占所有商品）
	private String xslzbxfl;//销售量占比（占小分类）
	private String xsjezbxfl;//销售金额占比（占小分类）
	private String mlezbxfl;//毛利额占比（占小分类）
	private String xslzbmxl;//销售量占比（占明细类）
	private String xsjezbmxl;//销售金额占比（占明细类）
	private String mlezbmxl;//毛利额占比（占明细类）
	private String psdxl;//PSD销量
	private String psdxsje;//PSD销售金额
	private String psdml;//PSD毛利
	private String qxs;//权限数
	private String qxdt;//权限店天
	private String xsdt;//销售店天
	private String hyd;//活跃度
	private String abcdmds;//A门店数
	private String amds;//A门店数
	private String bmds;//B门店数
	private String cmds;//C门店数
	private String dmds;//D门店数
	private String jhl;//进货量
	private String jhe;//进货额
	private String ddjsl;//订单及时率
	private String ddmzl;//订单满足率
	private String rbjssl;//让步接收数量
	private String rbjscs;//让步接收次数
	private String gyssjkc;//供应商实际库存
	private String gysaqkc;//供应商安全库存
	private String zlxj;//质量星级
	private String zhcs;//召回次数
	private String zhsl;//召回数量
	private String zhskugs;//召回SKU个数
	private String cjbhgcs;//抽检不合格次数
	private String cjbhgskus;//抽检不合格SKU数
	private String rchgl;//入厂合格率
	private String gysndqwyks;//供应商年度千万元客诉
	private String gysxcpf;//供应商巡厂评分
	private String gyskpdf;//供应商考评得分
	private String gyskpdfph;//供应商考评得分排行
	private String gysyxps;//供应商意向品数
	
	
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getMinDate() {
		return minDate;
	}
	public void setMinDate(String minDate) {
		this.minDate = minDate;
	}
	public String getMaxDate() {
		return maxDate;
	}
	public void setMaxDate(String maxDate) {
		this.maxDate = maxDate;
	}
	public String getMinDate1() {
		return minDate1;
	}
	public void setMinDate1(String minDate1) {
		this.minDate1 = minDate1;
	}
	public String getMaxDate1() {
		return maxDate1;
	}
	public void setMaxDate1(String maxDate1) {
		this.maxDate1 = maxDate1;
	}
	public String getMinDate2() {
		return minDate2;
	}
	public void setMinDate2(String minDate2) {
		this.minDate2 = minDate2;
	}
	public String getMaxDate2() {
		return maxDate2;
	}
	public void setMaxDate2(String maxDate2) {
		this.maxDate2 = maxDate2;
	}
	public String getMinDate3() {
		return minDate3;
	}
	public void setMinDate3(String minDate3) {
		this.minDate3 = minDate3;
	}
	public String getMaxDate3() {
		return maxDate3;
	}
	public void setMaxDate3(String maxDate3) {
		this.maxDate3 = maxDate3;
	}
	public String getSmallTypeCode() {
		return smallTypeCode;
	}
	public void setSmallTypeCode(String smallTypeCode) {
		this.smallTypeCode = smallTypeCode;
	}
	public String getDetailTypeCode() {
		return detailTypeCode;
	}
	public void setDetailTypeCode(String detailTypeCode) {
		this.detailTypeCode = detailTypeCode;
	}
	public Integer getYears() {
		return years;
	}
	public void setYears(Integer years) {
		this.years = years;
	}
	public String getXsbx() {
		return xsbx;
	}
	public void setXsbx(String xsbx) {
		this.xsbx = xsbx;
	}
	public String getZlqk() {
		return zlqk;
	}
	public void setZlqk(String zlqk) {
		this.zlqk = zlqk;
	}
	
	public String getJhqk() {
		return jhqk;
	}
	public void setJhqk(String jhqk) {
		this.jhqk = jhqk;
	}
	public String getShbx() {
		return shbx;
	}
	public void setShbx(String shbx) {
		this.shbx = shbx;
	}
	public String getXpphqk() {
		return xpphqk;
	}
	public void setXpphqk(String xpphqk) {
		this.xpphqk = xpphqk;
	}
	public Integer getLastYear() {
		return lastYear;
	}
	public void setLastYear(Integer lastYear) {
		this.lastYear = lastYear;
	}
	public Integer getLastYear1() {
		return lastYear1;
	}
	public void setLastYear1(Integer lastYear1) {
		this.lastYear1 = lastYear1;
	}
	public Integer getLastYear2() {
		return lastYear2;
	}
	public void setLastYear2(Integer lastYear2) {
		this.lastYear2 = lastYear2;
	}
	public Integer getLastYear3() {
		return lastYear3;
	}
	public void setLastYear3(Integer lastYear3) {
		this.lastYear3 = lastYear3;
	}
	public String getXsl() {
		return xsl;
	}
	public void setXsl(String xsl) {
		this.xsl = xsl;
	}
	public String getXsje() {
		return xsje;
	}
	public void setXsje(String xsje) {
		this.xsje = xsje;
	}
	public String getMle() {
		return mle;
	}
	public void setMle(String mle) {
		this.mle = mle;
	}
	public String getXslzball() {
		return xslzball;
	}
	public void setXslzball(String xslzball) {
		this.xslzball = xslzball;
	}
	public String getXsjezball() {
		return xsjezball;
	}
	public void setXsjezball(String xsjezball) {
		this.xsjezball = xsjezball;
	}
	public String getMlezball() {
		return mlezball;
	}
	public void setMlezball(String mlezball) {
		this.mlezball = mlezball;
	}
	public String getXslzbxfl() {
		return xslzbxfl;
	}
	public void setXslzbxfl(String xslzbxfl) {
		this.xslzbxfl = xslzbxfl;
	}
	public String getXsjezbxfl() {
		return xsjezbxfl;
	}
	public void setXsjezbxfl(String xsjezbxfl) {
		this.xsjezbxfl = xsjezbxfl;
	}
	public String getMlezbxfl() {
		return mlezbxfl;
	}
	public void setMlezbxfl(String mlezbxfl) {
		this.mlezbxfl = mlezbxfl;
	}
	public String getXslzbmxl() {
		return xslzbmxl;
	}
	public void setXslzbmxl(String xslzbmxl) {
		this.xslzbmxl = xslzbmxl;
	}
	public String getXsjezbmxl() {
		return xsjezbmxl;
	}
	public void setXsjezbmxl(String xsjezbmxl) {
		this.xsjezbmxl = xsjezbmxl;
	}
	public String getMlezbmxl() {
		return mlezbmxl;
	}
	public void setMlezbmxl(String mlezbmxl) {
		this.mlezbmxl = mlezbmxl;
	}
	public String getPsdxl() {
		return psdxl;
	}
	public void setPsdxl(String psdxl) {
		this.psdxl = psdxl;
	}
	public String getPsdxsje() {
		return psdxsje;
	}
	public void setPsdxsje(String psdxsje) {
		this.psdxsje = psdxsje;
	}
	public String getPsdml() {
		return psdml;
	}
	public void setPsdml(String psdml) {
		this.psdml = psdml;
	}
	public String getQxs() {
		return qxs;
	}
	public void setQxs(String qxs) {
		this.qxs = qxs;
	}
	public String getQxdt() {
		return qxdt;
	}
	public void setQxdt(String qxdt) {
		this.qxdt = qxdt;
	}
	
	public String getXsdt() {
		return xsdt;
	}
	public void setXsdt(String xsdt) {
		this.xsdt = xsdt;
	}
	public String getHyd() {
		return hyd;
	}
	public void setHyd(String hyd) {
		this.hyd = hyd;
	}
	public String getAmds() {
		return amds;
	}
	public void setAmds(String amds) {
		this.amds = amds;
	}
	public String getBmds() {
		return bmds;
	}
	public void setBmds(String bmds) {
		this.bmds = bmds;
	}
	public String getCmds() {
		return cmds;
	}
	public void setCmds(String cmds) {
		this.cmds = cmds;
	}
	public String getDmds() {
		return dmds;
	}
	public void setDmds(String dmds) {
		this.dmds = dmds;
	}
	
	public String getJhl() {
		return jhl;
	}
	public void setJhl(String jhl) {
		this.jhl = jhl;
	}
	public String getJhe() {
		return jhe;
	}
	public void setJhe(String jhe) {
		this.jhe = jhe;
	}
	public String getDdjsl() {
		return ddjsl;
	}
	public void setDdjsl(String ddjsl) {
		this.ddjsl = ddjsl;
	}
	public String getDdmzl() {
		return ddmzl;
	}
	public void setDdmzl(String ddmzl) {
		this.ddmzl = ddmzl;
	}
	public String getRbjssl() {
		return rbjssl;
	}
	public void setRbjssl(String rbjssl) {
		this.rbjssl = rbjssl;
	}
	public String getRbjscs() {
		return rbjscs;
	}
	public void setRbjscs(String rbjscs) {
		this.rbjscs = rbjscs;
	}
	public String getGyssjkc() {
		return gyssjkc;
	}
	public void setGyssjkc(String gyssjkc) {
		this.gyssjkc = gyssjkc;
	}
	
	public String getGysaqkc() {
		return gysaqkc;
	}
	public void setGysaqkc(String gysaqkc) {
		this.gysaqkc = gysaqkc;
	}
	public String getZlxj() {
		return zlxj;
	}
	public void setZlxj(String zlxj) {
		this.zlxj = zlxj;
	}
	public String getZhcs() {
		return zhcs;
	}
	public void setZhcs(String zhcs) {
		this.zhcs = zhcs;
	}
	public String getZhsl() {
		return zhsl;
	}
	public void setZhsl(String zhsl) {
		this.zhsl = zhsl;
	}
	public String getZhskugs() {
		return zhskugs;
	}
	public void setZhskugs(String zhskugs) {
		this.zhskugs = zhskugs;
	}
	public String getCjbhgcs() {
		return cjbhgcs;
	}
	public void setCjbhgcs(String cjbhgcs) {
		this.cjbhgcs = cjbhgcs;
	}
	public String getCjbhgskus() {
		return cjbhgskus;
	}
	public void setCjbhgskus(String cjbhgskus) {
		this.cjbhgskus = cjbhgskus;
	}
	public String getRchgl() {
		return rchgl;
	}
	public void setRchgl(String rchgl) {
		this.rchgl = rchgl;
	}
	
	public String getGysndqwyks() {
		return gysndqwyks;
	}
	public void setGysndqwyks(String gysndqwyks) {
		this.gysndqwyks = gysndqwyks;
	}
	public String getGysxcpf() {
		return gysxcpf;
	}
	public void setGysxcpf(String gysxcpf) {
		this.gysxcpf = gysxcpf;
	}
	public String getGyskpdf() {
		return gyskpdf;
	}
	public void setGyskpdf(String gyskpdf) {
		this.gyskpdf = gyskpdf;
	}
	public String getGyskpdfph() {
		return gyskpdfph;
	}
	public void setGyskpdfph(String gyskpdfph) {
		this.gyskpdfph = gyskpdfph;
	}
	public String getGysyxps() {
		return gysyxps;
	}
	public void setGysyxps(String gysyxps) {
		this.gysyxps = gysyxps;
	}
	public String getAbcdmds() {
		return abcdmds;
	}
	public void setAbcdmds(String abcdmds) {
		this.abcdmds = abcdmds;
	}
	
//
	
	
}