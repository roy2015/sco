package com.powere2e.sco.action.accessoryintention;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;

import com.powere2e.frame.commons.config.ConfigPath;
import com.powere2e.frame.commons.power.Authority;
import com.powere2e.frame.web.action.AuthorityAction;

/**
 * 辅料意向品的WEB请求响应类
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */

/**
 * 辅料 —竞价委员会申请
 * 
 * @author gavin.xu
 * @version 1.0
 * @since 2015年3月16日
 */
public class DownLoadFileAction extends AuthorityAction {

	/**
	 * 
	 * 
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 5606430258538938011L;

	/**
	 * 设置要用到的业务类
	 */
	@Override
	protected void beforeBuild() {

	}

	/**
	 * 下载大货文件
	 * 
	 * @throws IOException
	 */
	@Authority(privilege = "com.powere2e.sco")
	public void doDownloadDhFile() throws IOException {
		String path = this.asString("path");
		File file = new File(ConfigPath.getUploadFilePath().concat(path));
		// 获得请求文件名
		String filename = file.getName();

		// 设置文件MIME类型
		response.setContentType("application/octet-stream");
		String userAgent = request.getHeader("User-Agent").toLowerCase();
		if (userAgent.indexOf("chrome") != -1 || userAgent.indexOf("firefox") != -1) {
			filename = new String(filename.getBytes("UTF-8"), "ISO8859-1");
		} else {
			filename = URLEncoder.encode(filename, "UTF-8");
		}
		// 设置Content-Disposition
		response.setHeader("Content-Disposition", "attachment;filename=" + filename);
		// 读取目标文件，通过response将目标文件写到客户端
		// 获取目标文件的绝对路径
		// System.out.println(fullFileName);
		// 读取文件
		InputStream in = new FileInputStream(ConfigPath.getUploadFilePath().concat(path));
		OutputStream out = response.getOutputStream();

		// 写文件
		int b;
		while ((b = in.read()) != -1) {
			out.write(b);
		}

		in.close();
		out.close();

	}
}
