package com.powere2e.sco.model.suppliercomprehensiveanalysis;
import java.math.BigDecimal;

import com.powere2e.frame.server.model.AppModel;

/**
 * 总成本分析实体类
 * @author gavin.xu
 * @version 1.0
 * @since 2015年4月20日
 */
public class SupplierComprehensiveAnalysisForm extends AppModel  {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1662038537649001322L;
	private String supplierCode;
	private String supplierName;
	private String sjsjfw;//数据时间范围
	private BigDecimal xsl;//销售量
	private BigDecimal xsje;//销售金额
	private BigDecimal mle;//毛利额
	private String xslzball;//销售量占比（占所有商品）
	private String xsjezball;//销售金额占比（占所有商品）
	private String mlezball;//毛利额占比（占所有商品）
	private String xslzbxfl;//销售量占比（占小分类）
	private String xsjezbxfl;//销售金额占比（占小分类）
	private String mlezbxfl;//毛利额占比（占小分类）
	private String xslzbmxl;//销售量占比（占明细类）
	private String xsjezbmxl;//销售金额占比（占明细类）
	private String mlezbmxl;//毛利额占比（占明细类）
	private BigDecimal psdxl;//PSD销量
	private BigDecimal psdxsje;//PSD销售金额
	private BigDecimal psdml;//PSD毛利
	private BigDecimal qxs;//权限数
	private BigDecimal qxdt;//权限店天
	private BigDecimal xsdt;//销售店天
	private String hyd;//活跃度
	private BigDecimal amds;//A门店数
	private BigDecimal bmds;//B门店数
	private BigDecimal cmds;//C门店数
	private BigDecimal dmds;//D门店数
	private BigDecimal jhl;//进货量
	private BigDecimal jhe;//进货额
	private String ddjsl;//订单及时率
	private String ddmzl;//订单满足率
	private BigDecimal rbjssl;//让步接收数量
	private BigDecimal rbjscs;//让步接收次数
	private BigDecimal gyssjkc;//供应商实际库存
	private BigDecimal gysaqkc;//供应商安全库存
	private BigDecimal zlxj;//质量星级
	private BigDecimal zhcs;//召回次数
	private BigDecimal zhsl;//召回数量
	private BigDecimal zhskugs;//召回SKU个数
	private BigDecimal cjbhgcs;//抽检不合格次数
	private BigDecimal cjbhgskus;//抽检不合格SKU数
	private String rchgl;//入厂合格率
	private BigDecimal gysndqwyks;//供应商年度千万元客诉
	private BigDecimal gysxcpf;//供应商巡厂评分
	private BigDecimal gyskpdf;//供应商考评得分
	private BigDecimal gyskpdfph;//供应商考评得分排行
	private BigDecimal gysyxps;//供应商意向品数
	private BigDecimal zdmj;//占地面积
	private BigDecimal cjmj;//车间面积
	private BigDecimal nzcz;//年总产值
	private BigDecimal qyzrs;//企业总人数
	public String getSupplierCode() {
		return supplierCode;
	}
	public void setSupplierCode(String supplierCode) {
		this.supplierCode = supplierCode;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	public String getSjsjfw() {
		return sjsjfw;
	}
	public void setSjsjfw(String sjsjfw) {
		this.sjsjfw = sjsjfw;
	}
	public BigDecimal getXsl() {
		return xsl;
	}
	public void setXsl(BigDecimal xsl) {
		this.xsl = xsl;
	}
	public BigDecimal getXsje() {
		return xsje;
	}
	public void setXsje(BigDecimal xsje) {
		this.xsje = xsje;
	}
	public BigDecimal getMle() {
		return mle;
	}
	public void setMle(BigDecimal mle) {
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
	public BigDecimal getPsdxl() {
		return psdxl;
	}
	public void setPsdxl(BigDecimal psdxl) {
		this.psdxl = psdxl;
	}
	public BigDecimal getPsdxsje() {
		return psdxsje;
	}
	public void setPsdxsje(BigDecimal psdxsje) {
		this.psdxsje = psdxsje;
	}
	public BigDecimal getPsdml() {
		return psdml;
	}
	public void setPsdml(BigDecimal psdml) {
		this.psdml = psdml;
	}
	public BigDecimal getQxs() {
		return qxs;
	}
	public void setQxs(BigDecimal qxs) {
		this.qxs = qxs;
	}
	public BigDecimal getQxdt() {
		return qxdt;
	}
	public void setQxdt(BigDecimal qxdt) {
		this.qxdt = qxdt;
	}
	public BigDecimal getXsdt() {
		return xsdt;
	}
	public void setXsdt(BigDecimal xsdt) {
		this.xsdt = xsdt;
	}
	
	public String getHyd() {
		return hyd;
	}
	public void setHyd(String hyd) {
		this.hyd = hyd;
	}
	public BigDecimal getAmds() {
		return amds;
	}
	public void setAmds(BigDecimal amds) {
		this.amds = amds;
	}
	public BigDecimal getBmds() {
		return bmds;
	}
	public void setBmds(BigDecimal bmds) {
		this.bmds = bmds;
	}
	public BigDecimal getCmds() {
		return cmds;
	}
	public void setCmds(BigDecimal cmds) {
		this.cmds = cmds;
	}
	public BigDecimal getDmds() {
		return dmds;
	}
	public void setDmds(BigDecimal dmds) {
		this.dmds = dmds;
	}
	public BigDecimal getJhl() {
		return jhl;
	}
	public void setJhl(BigDecimal jhl) {
		this.jhl = jhl;
	}
	public BigDecimal getJhe() {
		return jhe;
	}
	public void setJhe(BigDecimal jhe) {
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
	public BigDecimal getRbjssl() {
		return rbjssl;
	}
	public void setRbjssl(BigDecimal rbjssl) {
		this.rbjssl = rbjssl;
	}
	public BigDecimal getRbjscs() {
		return rbjscs;
	}
	public void setRbjscs(BigDecimal rbjscs) {
		this.rbjscs = rbjscs;
	}
	public BigDecimal getGyssjkc() {
		return gyssjkc;
	}
	public void setGyssjkc(BigDecimal gyssjkc) {
		this.gyssjkc = gyssjkc;
	}
	public BigDecimal getGysaqkc() {
		return gysaqkc;
	}
	public void setGysaqkc(BigDecimal gysaqkc) {
		this.gysaqkc = gysaqkc;
	}
	
	public BigDecimal getZhcs() {
		return zhcs;
	}
	public void setZhcs(BigDecimal zhcs) {
		this.zhcs = zhcs;
	}
	public BigDecimal getZhsl() {
		return zhsl;
	}
	public void setZhsl(BigDecimal zhsl) {
		this.zhsl = zhsl;
	}
	public BigDecimal getZhskugs() {
		return zhskugs;
	}
	public void setZhskugs(BigDecimal zhskugs) {
		this.zhskugs = zhskugs;
	}
	public BigDecimal getCjbhgcs() {
		return cjbhgcs;
	}
	public void setCjbhgcs(BigDecimal cjbhgcs) {
		this.cjbhgcs = cjbhgcs;
	}
	public BigDecimal getCjbhgskus() {
		return cjbhgskus;
	}
	public void setCjbhgskus(BigDecimal cjbhgskus) {
		this.cjbhgskus = cjbhgskus;
	}
	public String getRchgl() {
		return rchgl;
	}
	public void setRchgl(String rchgl) {
		this.rchgl = rchgl;
	}
	
	public BigDecimal getZlxj() {
		return zlxj;
	}
	public void setZlxj(BigDecimal zlxj) {
		this.zlxj = zlxj;
	}
	public BigDecimal getGysndqwyks() {
		return gysndqwyks;
	}
	public void setGysndqwyks(BigDecimal gysndqwyks) {
		this.gysndqwyks = gysndqwyks;
	}
	public BigDecimal getGysxcpf() {
		return gysxcpf;
	}
	public void setGysxcpf(BigDecimal gysxcpf) {
		this.gysxcpf = gysxcpf;
	}
	public BigDecimal getGyskpdf() {
		return gyskpdf;
	}
	public void setGyskpdf(BigDecimal gyskpdf) {
		this.gyskpdf = gyskpdf;
	}
	public BigDecimal getGyskpdfph() {
		return gyskpdfph;
	}
	public void setGyskpdfph(BigDecimal gyskpdfph) {
		this.gyskpdfph = gyskpdfph;
	}
	public BigDecimal getGysyxps() {
		return gysyxps;
	}
	public void setGysyxps(BigDecimal gysyxps) {
		this.gysyxps = gysyxps;
	}
	public BigDecimal getZdmj() {
		return zdmj;
	}
	public void setZdmj(BigDecimal zdmj) {
		this.zdmj = zdmj;
	}
	public BigDecimal getCjmj() {
		return cjmj;
	}
	public void setCjmj(BigDecimal cjmj) {
		this.cjmj = cjmj;
	}
	public BigDecimal getNzcz() {
		return nzcz;
	}
	public void setNzcz(BigDecimal nzcz) {
		this.nzcz = nzcz;
	}
	public BigDecimal getQyzrs() {
		return qyzrs;
	}
	public void setQyzrs(BigDecimal qyzrs) {
		this.qyzrs = qyzrs;
	}
	
	
	
	
	
}