package com.powere2e.sco.peripheral.hbxt;

import com.powere2e.frame.commons.config.ConfigFactory;
import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.dao.PageInfo;
import com.powere2e.frame.web.exception.EscmException;
import com.powere2e.sco.common.service.BusinessConstants;
import com.powere2e.sco.common.service.merchandiseoaapplication.MerchandiseOaApplicationUtil;
import com.powere2e.sco.common.utils.PathUtils;
import com.powere2e.sco.common.utils.StrUtils;
import com.powere2e.sco.interfaces.service.common.MasterDataTypeService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.MerchandiseOaApplicationService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.fastadjustpriceoaapplication.ApplicationFastadjustpriceService;
import com.powere2e.sco.interfaces.service.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentMService;
import com.powere2e.sco.interfaces.service.peripheral.hbxt.ApplyMerchandiseAdjustPriceHbxtService;
import com.powere2e.sco.interfaces.service.peripheral.hbxt.ApplyNormalMerchandiseAdjustPriceHbxtService;
import com.powere2e.sco.model.merchandiseintention.MerchandiseIntention;
import com.powere2e.sco.model.merchandiseoaapplication.ApplicationMerchandise;
import com.powere2e.sco.model.merchandiseoaapplication.reportnewoaapplication.supplierattachment.SupplierAttachmentM;
import com.powere2e.sco.model.peripheral.hbxt.AdjustPriceApplyReturnVO;
import com.powere2e.sco.model.peripheral.hbxt.CxfFileWrapper;
import com.powere2e.sco.model.peripheral.hbxt.MerchandiseAdjustPriceApplyResponseResult;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


public class ApplyNormalMerchandiseAdjustPriceHbxtServiceImpl implements ApplyNormalMerchandiseAdjustPriceHbxtService {
    public static Logger logger = Logger.getLogger("cn.vesung");
    private static AtomicInteger FILE_NO = new AtomicInteger(0);

    public static Map<Integer, String> ATTACHMENT_TYPE_MAP = new HashMap<>(3);
    static {
        ATTACHMENT_TYPE_MAP.put(0,"供应商调价申请函");
        ATTACHMENT_TYPE_MAP.put(1,"感官标准");
        ATTACHMENT_TYPE_MAP.put(2,"其它");
    }

    @Override
    public MerchandiseAdjustPriceApplyResponseResult createNormalMerchandiseAdjustPriceApplication(String merchandiseCode,
                                                                                                   CxfFileWrapper file1, CxfFileWrapper file2, CxfFileWrapper file3) {
        MerchandiseAdjustPriceApplyResponseResult responseResult = new MerchandiseAdjustPriceApplyResponseResult();
        responseResult.setSucess(true);
        CxfFileWrapper[] files = new CxfFileWrapper[3];
        files[0] = file1;
        files[1] = file2;
        files[2] = file3;
        if (file1 == null && file2 == null) {
            responseResult.setSucess(false);
            responseResult.setMessage(String.format("file1: %s, file2: %s, file3: %s, 其中%s, %s不能都为空",
                    ATTACHMENT_TYPE_MAP.get(0), ATTACHMENT_TYPE_MAP.get(1), ATTACHMENT_TYPE_MAP.get(2),
                    ATTACHMENT_TYPE_MAP.get(0), ATTACHMENT_TYPE_MAP.get(1)
                    ));
            return responseResult;
        }
        try {
            StringBuilder sb = new StringBuilder();
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    if (i > 0) sb.append(",");
                    if (files[i] != null) {
                        sb.append(String.format("[%s] - fileName：%s, fileExtension[%s]     ", i , files[i].getFileName(), files[i].getFileExtension()));
                    }
                }
            }
            populateFileNameAndExtinsion(files);
            logger.info(String.format("商品正常调价createNormalMerchandiseAdjustPriceApplication接口入参: " +
                    "merchandiseCode[%s],files[%s]", merchandiseCode, sb.toString()));

            MerchandiseOaApplicationService merchandiseOaApplicationService = (MerchandiseOaApplicationService) ConfigFactory.getInstance().getBean("merchandiseOaApplicationService");
            MasterDataTypeService masterDataTypeService = (MasterDataTypeService) ConfigFactory.getInstance().getBean("masterDataTypeService");
            SupplierAttachmentMService supplierAttachmentMService = (SupplierAttachmentMService) ConfigFactory.getInstance().getBean("supplierAttachmentMService");

            Map<String, Object> map = new HashMap<>();
            map.put("merchandiseCode", merchandiseCode);
            map.put("applicationType", BusinessConstants.ApplicationType.MERCHANDISE_ADJUSTPRICE.toString());
            PageInfo pageInfo = new PageInfo();
            pageInfo.setPage(1);
            pageInfo.setSize(1);
            List<MerchandiseIntention> merchandiseIntentionList = merchandiseOaApplicationService.queryMerchandiseApplicationList(map, pageInfo);
            if (merchandiseIntentionList == null || merchandiseIntentionList.isEmpty()
                    || merchandiseIntentionList.get(0).getMerchandiseCode().equals(merchandiseCode) == false) {
                responseResult.setSucess(false);
                responseResult.setMessage("商品不存在");
                return responseResult;
            }
            MerchandiseIntention merchandiseIntention = merchandiseIntentionList.get(0);
            String applicationStatus = merchandiseIntention.getApplicationStatus();
            if ( !BusinessConstants.MerchandiseApplicationStatus.W.toString().equals(applicationStatus) &&
                    !BusinessConstants.MerchandiseApplicationStatus.SPTG.toString().equals(applicationStatus) ) {
                responseResult.setSucess(false);
                responseResult.setMessage("请选择正常调价申请单状态是'无'或'审批通过'的商品");
                return responseResult;

            }
            String applicationCode = "OA".concat(masterDataTypeService.nextID("S_OA_APPLICATION"));
            String supplierCode = merchandiseIntention.getSupplierCode();
            String intentionSupplierCode = supplierCode;
            String intentionAndSupplierCodes = merchandiseCode + ":" + supplierCode;
            String supAttFilePath = PathUtils.getSupplierAttachmentFileUploadPath();
            supAttFilePath = supAttFilePath.concat("/");
            if (!supAttFilePath.endsWith("/") && !supAttFilePath.endsWith("\\")){
                supAttFilePath += "/";
            }
            Map<Integer, File> fileMap = this.uploadFilesToDest(new File(supAttFilePath), files);

            if (!fileMap.isEmpty()) {
                for ( Map.Entry<Integer, File> fileEntry : fileMap.entrySet()) {
                    Integer typeIdx = fileEntry.getKey();
                    File file = fileEntry.getValue();
                    SupplierAttachmentM supplierAttachmentM = new SupplierAttachmentM();
                    supplierAttachmentM.setApplicationCode(applicationCode);
                    supplierAttachmentM.setMerchandiseCode(merchandiseCode);
                    supplierAttachmentM.setSupplierCode(supplierCode);
                    supplierAttachmentM.setAttachmentType(ATTACHMENT_TYPE_MAP.get(typeIdx));
                    supplierAttachmentM.setElseAttachmentType(supplierAttachmentM.getAttachmentType());
                    supplierAttachmentM.setAttachmentName(StrUtils.getFileResourceName(file.getName()));
                    supplierAttachmentM.setPath(supAttFilePath.replaceAll(ConfigPath.getUploadFilePath(), "").concat(file.getName()));// 文件路径
                    map = supplierAttachmentM.toMap();
                    map.put("intentionAndSupplierCodes", intentionAndSupplierCodes);
                    map.put("applicationType", "ma");
                    supplierAttachmentMService.validateOaStatus(map);

                    List<SupplierAttachmentM> list = supplierAttachmentMService.loadSupplierAttachmentM(supplierAttachmentM);
                    if (list.size() != 0) {
                        map.put("paths", list.get(0).getPath());
                        supplierAttachmentMService.deleteSupplierAttachmentM(map);
                    }
                    supplierAttachmentMService.insertSupplierAttachmentM(map);
                }
                responseResult.setData(new AdjustPriceApplyReturnVO(merchandiseCode, applicationCode));
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            responseResult.setSucess(false);
            responseResult.setMessage(e.getMessage());
            return responseResult;
        }
        return responseResult;
    }

    /**
     *
     * @param baseDir  指定存放文件目录
     * @param cxfFileWrappers
     * @return  文件集合
     */
    private Map<Integer, File> uploadFilesToDest(File baseDir, CxfFileWrapper[] cxfFileWrappers) {
        // 判断文件的存在性
        if (cxfFileWrappers == null || cxfFileWrappers.length ==0) {
            return null;
        }

        // 判断目录的存在性
        if (!baseDir.exists()) {
            baseDir.mkdirs();
        }

        Map<Integer, File> files = new HashMap<Integer, File>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < cxfFileWrappers.length; i++) {
            if (cxfFileWrappers[i] == null) continue;
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
            DecimalFormat df = new DecimalFormat("00");
            String str = df.format(FILE_NO.getAndIncrement());
            String newFileName = StringUtils.substringBeforeLast(cxfFileWrappers[i].getFileName(), ".") + "_" + sdf.format(new Date()) + str
                    + "."
                    + StringUtils.substringAfterLast(cxfFileWrappers[i].getFileName(), ".");
            try {
                File dest = new File(baseDir, newFileName);
                logger.error("上传文件日志[正常日志]:[保存路径：" + dest.getPath() + "]");

                FileOutputStream fos = new FileOutputStream(dest);
                cxfFileWrappers[i].getFile().writeTo(fos);
                fos.flush();
                fos.close();
                files.put(i, dest);
                logger.error("文件日志[正常日志]:[文件上传成功?：" + dest.exists() + "]");
            } catch (IOException e) {
                logger.error("上传文件时异常:<br>" + StrUtils.getStackMsgToStr(e));
                throw new EscmException("IO错误", new String[] { "上传文件失败！" });
            }
        }
        if (sb.length() > 0) {
            logger.error("上传文件时异常:<br>" + sb.toString());
            throw new EscmException(sb.toString());
        }
        return files;
    }

    /**
     * 统一对文件名定义  文件名.扩展名
     * @param files
     */
    private void populateFileNameAndExtinsion(CxfFileWrapper[] files) {
        if (files == null && files.length ==0) {
            return;
        }
        for (CxfFileWrapper file : files) {
            if (file == null) continue;
            String fileName = file.getFileName();
            String fileExtension = file.getFileExtension();
            if (fileName.indexOf(".") != -1) {
                //重置扩展名
                file.setFileExtension(StringUtils.substringAfterLast(fileName, "."));
            } else {
                file.setFileName(fileName + "." + fileExtension);
            }
        }
    }


}
