package com.powere2e.sco.peripheral.tbpm;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.jws.WebService;

import org.apache.commons.lang.StringUtils;

import com.powere2e.sco.interfaces.service.peripheral.tbpm.GetAccessoryApplicationVisitFactoryTbmpService;
import com.powere2e.sco.model.masterdata.Supplier;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseintention.MerchandiseQuoted;
import com.powere2e.sco.model.peripheral.tbmp.ApplicationVisitFactoryMx;
import com.powere2e.sco.model.peripheral.webservice.ApplicationVisitFactoryM;
import com.powere2e.sco.peripheral.utils.PeripheralFileUtils;
import com.powere2e.sco.service.impl.masterdata.SupplierServiceImpl;
import com.powere2e.sco.service.impl.merchandiseintention.MerchandiseIntentionServiceImpl;
import com.powere2e.sco.service.impl.merchandiseintention.QuotedCompareServiceImpl;
import com.powere2e.sco.service.impl.peripheral.webservice.ApplicationVisitFactoryMServiceImpl;

@WebService(endpointInterface = "com.powere2e.sco.interfaces.service.peripheral.tbpm.GetAccessoryApplicationVisitFactoryTbmpService")
public class GetAccessoryApplicationVisitFactoryTbmpServiceImpl implements GetAccessoryApplicationVisitFactoryTbmpService {

	@Override
	public List<ApplicationVisitFactoryMx> getAccessoryApplicationVisitFactoryTbmp(String applicationVfCode) {
		if (StringUtils.isBlank(applicationVfCode)) {
			PeripheralFileUtils.logger.error("TBPM-巡厂申请明细信息获取失败-巡厂申请单号: " + applicationVfCode);
			return null;
		} else {
			List<ApplicationVisitFactoryMx> applicationVisitFactoryMxList;
			try {
				applicationVfCode = applicationVfCode.trim();
				applicationVisitFactoryMxList = new ArrayList<ApplicationVisitFactoryMx>();
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("applicationVfCode", applicationVfCode);
				List<ApplicationVisitFactoryM> applicationVisitFactoryMList = ApplicationVisitFactoryMServiceImpl.getInstance().listApplicationVisitFactoryM(map, null);
				if (applicationVisitFactoryMList != null && applicationVisitFactoryMList.size() > 0) {
					for (ApplicationVisitFactoryM applicationVisitFactoryM : applicationVisitFactoryMList) {
						ApplicationVisitFactoryMx applicationVisitFactoryMx = new ApplicationVisitFactoryMx();
						map.clear();
						map.put("supplierCode", applicationVisitFactoryM.getSupplierCode());
						applicationVisitFactoryMx.setSupplierCode(applicationVisitFactoryM.getSupplierCode());
						applicationVisitFactoryMx.setApplicationCode(applicationVisitFactoryM.getApplicationCode());
						Supplier suppliers = SupplierServiceImpl.getInstance().loadSupplier(applicationVisitFactoryM.getSupplierCode());
						if (suppliers!=null) {
							applicationVisitFactoryMx.setSupplierName(suppliers.getSupplierName());
							applicationVisitFactoryMx.setContacts(suppliers.getContacts());
							applicationVisitFactoryMx.setCompanySite(suppliers.getCompanySite());
							applicationVisitFactoryMx.setCompanyPhone(suppliers.getCompanyPhone());
						}else{
							suppliers = SupplierServiceImpl.getInstance().loadIntentionSupplier(applicationVisitFactoryM.getSupplierCode());
							applicationVisitFactoryMx.setSupplierName(suppliers.getSupplierName());
							map.clear();
							map.put("excludeIntention", "excludeIntention");
							map.put("intentionSupplierCode", applicationVisitFactoryM.getSupplierCode());
							MerchandiseQuoted merchandiseQuoted=QuotedCompareServiceImpl.getQuotedCompareServiceInstance().queryLastQuoted(map);
							if(merchandiseQuoted!=null){
								applicationVisitFactoryMx.setContacts(merchandiseQuoted.getContactsName());
								applicationVisitFactoryMx.setCompanySite(merchandiseQuoted.getCompanySite());
								applicationVisitFactoryMx.setCompanyPhone(merchandiseQuoted.getContactsPhone());	
							}
						}
						
					/*	map.clear();
						map.put("merchandiseCode", applicationVisitFactoryM.getMerchandiseCode());
						List<Merchandise> merchandises = MerchandiseServiceImpl.getInstance().listMerchandise(map, null);
						if (merchandises.size() > 0) {
							applicationVisitFactoryMx.setMerchandiseCode(applicationVisitFactoryM.getMerchandiseCode());
							applicationVisitFactoryMx.setMerchandiseName(merchandises.get(0).getMerchandiseName());
							applicationVisitFactoryMx.setSupplierName(merchandises.get(0).getSupplierName());
						}else{*/
						
							MerchandiseIntention merchandiseIntentions = MerchandiseIntentionServiceImpl.getInstance().loadMerchandiseIntention(applicationVisitFactoryM.getMerchandiseCode());
							if(merchandiseIntentions!=null){
								applicationVisitFactoryMx.setMerchandiseCode(applicationVisitFactoryM.getMerchandiseCode());
								applicationVisitFactoryMx.setMerchandiseName(merchandiseIntentions.getIntentionName());
							}
						applicationVisitFactoryMxList.add(applicationVisitFactoryMx);
					}
					PeripheralFileUtils.logger.info("TBPM-巡厂申请明细信息获取成功-巡厂申请单号: " + applicationVfCode);
					
				}
			} catch (Exception e) {
				PeripheralFileUtils.logger.error("TBPM-巡厂申请明细信息获取失败-巡厂申请单号: " + applicationVfCode);
				applicationVisitFactoryMxList=null;
				PeripheralFileUtils.logger.error(e);
				e.printStackTrace();
			}
			return applicationVisitFactoryMxList;
		}
		
	}
}
