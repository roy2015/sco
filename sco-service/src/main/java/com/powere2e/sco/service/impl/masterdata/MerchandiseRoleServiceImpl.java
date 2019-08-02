package com.powere2e.sco.service.impl.masterdata;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.service.impl.ServiceImpl;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.jexcel.utils.SmallReaderHandler;
import com.powere2e.jexcel.utils.SmallReaderHandler.ExcelRow;
import com.powere2e.sco.common.utils.Constant;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseDao;
import com.powere2e.sco.interfaces.dao.masterdata.MerchandiseRoleDao;
import com.powere2e.sco.interfaces.service.masterdata.MerchandiseRoleService;
import com.powere2e.sco.model.masterdata.Merchandise;
import com.powere2e.security.model.Option;

/**
 * 商品角色查询Service接口实现
 * 
 * @author Gavillen.Zhou
 * @since 2015年3月18日
 * @version 1.0
 */
public class MerchandiseRoleServiceImpl extends ServiceImpl implements MerchandiseRoleService {
	/**
	 * 
	 */
	private static final long serialVersionUID = -964184845108630490L;
	private MerchandiseRoleDao merchandiseRoleDao;
	private MerchandiseDao merchandiseDao;

	// 获取Merchandise Dao实例
	public MerchandiseDao getMerchandiseDao() {
		return merchandiseDao;
	}

	// 设置Merchandise Dao实例
	public void setMerchandiseDao(MerchandiseDao merchandiseDao) {
		this.merchandiseDao = merchandiseDao;
	}

	// 获取MerchandiseRole Dao实例
	public MerchandiseRoleDao getMerchandiseRoleDao() {
		return merchandiseRoleDao;
	}

	// 设置MerchandiseRole Dao实例
	public void setMerchandiseRoleDao(MerchandiseRoleDao merchandiseRoleDao) {
		this.merchandiseRoleDao = merchandiseRoleDao;
	}

	/**
	 * 方便其他模块调用
	 * 
	 * @return Service实例
	 */
	public static MerchandiseRoleService getInstance() {
		return (MerchandiseRoleService) ConfigFactory.getInstance().getBean("merchandiseRoleService");
	}

	@Override
	public List<Option> listQualitative(Map<String, Object> map) {
		return this.merchandiseRoleDao.listQualitative(map);
	}

	@Override
	public List<Option> listQuantify(Map<String, Object> map) {
		return this.merchandiseRoleDao.listQuantify(map);
	}

	@Override
	public String completeImportMerchandise(File file) {

		// 查询主数据中所有商品
		Map<String, Object> map = new HashMap<String, Object>();
		List<String> mList = MerchandiseServiceImpl.getInstance().listMerchandiseCode(map);
		List<String> dxList = MerchandiseRoleServiceImpl.getInstance().listMerchandiseDxRoleStorageForm();
		// 用于存储消息
		String msg = "";
		if (mList.isEmpty()) {
			return "SAP中目前没有商品数据,请先同步商品主数据";
		}
		// 用于存储excel中的数据
		List<Merchandise> dataList = new ArrayList<Merchandise>();
		try {
			// 注册Double类型默认值为null
			dataList = this.parseExcel(file, mList, dxList, dataList);
		} catch (Exception e) {
			msg = e.getMessage();
		} finally {
			// 取消注册Double类型默认值为null
			// ConvertUtils.deregister();
		}
		if (!msg.equals("")) {// 有错误消息
			return msg.toString();
		} else if (dataList.isEmpty()) {// 文件中无数据
			return "请在文件中填写需上传数据";
		} else {
			this.insertMarketSurverData(dataList);
		}
		return null;
	}

	/**
	 * 进行集合判断，数据库有的修改数据库之后再list集合删除
	 * 
	 * @param list
	 * @return
	 */
	public List<Merchandise> updateMerchandiseList(List<Merchandise> list) {
		List<Merchandise> delList = new ArrayList<Merchandise>();
		for (Merchandise merchandise : list) {
			Map<String, Object> merchandiseMap = new HashMap<String, Object>();
			merchandiseMap.put("roleCode", merchandise.getMerchandiseCode());
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("merchandiseCode", merchandise.getMerchandiseCode());
			Integer i=this.merchandiseRoleDao.searchMerchandiseCount(map);
			if (i!=0) {
				merchandiseMap = merchandise.toMap();
				this.merchandiseRoleDao.updateMerchandise(merchandiseMap);
				delList.add(merchandise);
			}
		}
		list.removeAll(delList);
		return list;
	}

	/**
	 * 新增商品角色数据
	 * 
	 * @param dataList
	 */
	private void insertMarketSurverData(List<Merchandise> list) {
		// 批量插入
		Map<String, Object> map = new HashMap<String, Object>();
		// List分批插入(每批100条)
		int listSize = list.size();
		int maxCount = listSize / Constant.BATCH_SIZE;
		int i = 0;
		for (; i < maxCount; i++) {
			int toIndex = (i + 1) * Constant.BATCH_SIZE > listSize ? listSize : (i + 1) * Constant.BATCH_SIZE;
			list = updateMerchandiseList(list);
			if (list.size() != 0) {
				map.put("list", list.subList(i * Constant.BATCH_SIZE, toIndex));
				this.merchandiseRoleDao.insertMerchandise(map);
			}
		}
		// 防止遗漏数据
		int currentIndex = i * Constant.BATCH_SIZE;
		if (currentIndex < list.size()) {
			list = updateMerchandiseList(list);
			if (list.size() != 0) {
				map.put("list", list.subList(currentIndex, list.size()));
				this.merchandiseRoleDao.insertMerchandise(map);
			}
		}
	}

	/**
	 * 解析Excel
	 * 
	 * @param file
	 *            上传的文件
	 * @param mList
	 *            商品编码
	 * @param dataList
	 *            Excel中的数据
	 * @return 待添加的商品调价数据
	 * @throws Exception
	 */
	private List<Merchandise> parseExcel(File file, final List<String> mList, final List<String> dxList, final List<Merchandise> dataList) throws Exception {
		// 创建Excel文件处理器
		SmallReaderHandler handler = SmallReaderHandler.getReaderHandler(file);
		// 获取表格(开始行号)
		int idx = 2;
		StringBuilder sb = new StringBuilder();// 存储错误信息
		List<String> merchandiseCode = new ArrayList<String>();
		for (; idx <= handler.getLastRowNum(); idx++) {// 循环处理每一行数据
			ExcelRow rowData = handler.getExcelRow(idx);
			Merchandise msd = this.processRow(idx, rowData, mList, dxList, sb, merchandiseCode);
			if (sb.length() == 0 && msd != null) {
				dataList.add(msd);
				merchandiseCode.add(msd.getMerchandiseCode());
			}

		}
		// 检测是否有错误
		if (sb.length() > 0) {
			throw new EscmException(sb.toString());
		}
		return dataList;
	}

	/**
	 * 处理excel一行的数据
	 * 
	 * @param idx
	 *            当前行数
	 * @param rowData
	 *            当前行数据
	 * @param mList
	 *            商品主数据
	 * @param msg
	 *            错误信息
	 * @return
	 */
	private Merchandise processRow(int idx, ExcelRow rowData, List<String> mList, List<String> dxList, StringBuilder msg, List<String> merchandiseCode) {
		StringBuilder sb = new StringBuilder();
		String dataA = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("A"));
		String dataB = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("B"));
		if("".equals(dataA)&&"".equals(dataB)){
			return null;
		}
		if ("".equals(dataA)) {
			sb.append("第" + idx + "行A列:商品编号不可为空<br>");
		} else {
			if (!mList.contains(dataA)) {
				sb.append("第" + idx + "行A列:商品编号在SAP中不存在<br>");
			} else {
				if (merchandiseCode.contains(dataA)) {
					sb.append("第" + idx + "行A列:商品编号在上传文件中重复<br>");
				}

				if (dataA.length() > 30) {
					sb.append("第" + idx + "行A列:商品编号长度不能超过30<br>");
				}
			}
		}

		
		if ("".equals(dataB)) {
			sb.append("第" + idx + "行B列:商品定性角色不可为空<br>");
		} else {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("roleName", dataB);
			dataB=this.getMerchandiseRoleDao().searchMerchandiseDxRoleName(map);
			if (!dxList.contains(dataB)) {
				sb.append("第" + idx + "行B列:该商品定性角色在品类维护的商品角色下拉列表中不存在<br>");
			} else {
				if (dataB.length() > 30) {
					sb.append("第" + idx + "行B列:商品定性角色长度不能超过30<br>");
				}
			}
		}

		String dataC = StrUtils.replaceBlankAndNewline(rowData.getStringCellValue("C"));
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("roleName", dataC);
		dataC=this.getMerchandiseRoleDao().searchMerchandiseDlRoleName(map);
		msg.append(sb.toString());
		if (sb.length() > 0) {
			return null;
		}
		return new Merchandise(dataA, dataB, dataC);
	}

	// 修改
	@Override
	public void updateMerchandise(Merchandise merchandise) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String[] merchandiseCodes=merchandise.getMerchandiseCode().split(",");
		for(int i=0;i<merchandiseCodes.length;i++){
			map.put("merchandiseCode", merchandiseCodes[i]);
			Integer count=this.getMerchandiseRoleDao().searchMerchandiseCount(map);
			if(count==0){
				Merchandise m=new Merchandise(merchandiseCodes[i], merchandise.getDxRoleCode(), merchandise.getDlRoleCode());
				List<Merchandise> list=new ArrayList<Merchandise>();
				list.add(m);
				map.put("list", list);
				this.getMerchandiseRoleDao().insertMerchandise(map);
			}
			map.put("merchandiseCode", "");
		}
		map.put("merchandiseCodes", merchandise.getMerchandiseCode().split(","));
		map.put("dlRoleCode", merchandise.getDlRoleCode());
		map.put("dxRoleCode", merchandise.getDxRoleCode());
		this.getMerchandiseRoleDao().updateMerchandise(map);
	}

	/**
	 * 获取商品角色id列表
	 */
	@Override
	public List<String> listMerchandiseDxRoleStorageForm() {
		// TODO Auto-generated method stub
		return this.merchandiseRoleDao.listMerchandiseDxRoleStorageForm();
	}

}