package com.mfq.admin.web.servlet;

import java.io.IOException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mfq.admin.web.utils.Config;


public class FileUploadServlet extends HttpServlet {
	private static final long serialVersionUID = -7084875719700119067L;

	private static final Logger log = LoggerFactory.getLogger(FileUploadServlet.class);
	@SuppressWarnings("unused")
	private static final Random random = new Random();

	@SuppressWarnings("unused")
	private static final String tmpDir = Config.getItem("tmpUploadFileDir", "/server/tts/uploadFile");
	@SuppressWarnings("unused")
	private static final String tomcatDir = Config.getItem("front.upload", "/server/tts/htdocs_dependent/tts_dependent/");

	@SuppressWarnings("unused")
	private static final String bannerTomcatUrl = Config.getItem("front.upload.bannerTomcatUrl", "/upload/%s/banner/");
	
	//private static final int bannerCount = (int) Config.getIntItem("front.upload.bannerCount", "5");
	//private static final long bannerSizeLimit = Config.getIntItem("front.upload.bannerFileSizeLimit", "60");

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.setCharacterEncoding("utf-8");
	/*
		if (!ServletFileUpload.isMultipartContent(req)) {
			error(resp, "请求格式错误");
			return;
		}
		String validation = req.getParameter("validation");
		if (validation == null || MemCache.INSTANCE.get("banner_upload." + validation) == null) {
			error(resp, "您的操作时间过长，请刷新页面重新提交");
			return;
		}
		DiskFileItemFactory dff = new DiskFileItemFactory();
		dff.setRepository(new File(tmpDir));
		dff.setSizeThreshold(1024 * 1024);
		ServletFileUpload sfu = new ServletFileUpload(dff);

		String type = req.getParameter("type");
		if ("banner".equals(type)) {
			if (log.isInfoEnabled()) {
				log.info("Updte banner settings");
			}
			
			sfu.setFileSizeMax(bannerSizeLimit * 1024);
			sfu.setSizeMax(bannerCount * bannerSizeLimit * 1024);
			String dbName = Context.INSTANCE.tableNameMap.get(site);
			File bannerDir = new File(tomcatDir, String.format(bannerTomcatUrl, dbName));
			if (!bannerDir.exists())
				bannerDir.mkdirs();

			@SuppressWarnings("unchecked")
			Map<String, String>[] data = new Map[bannerCount];
			try {
				for (Object i : sfu.parseRequest(req)) {
					FileItem fi = (FileItem) i;

					String[] parts = fi.getFieldName().split("\\.", 2);
					String fieldName = parts[0];
					int id = -1;
					if (parts.length > 1)
						try {
							id = Integer.parseInt(parts[1]) - 1;
						} catch (NumberFormatException e) {
						}

					if (id < 0 && id >= bannerCount)
						continue;
					if (data[id] == null)
						data[id] = new HashMap<String, String>();					
					String filenewName = fi.getName();

				    if (filenewName != null) {
				    	filenewName = FilenameUtils.getName(filenewName);
				    }
		
					
					if (fi.isFormField()) {
						String value = fi.getString("utf-8");
						if (fieldName.equals("link") && !value.isEmpty() && !value.matches("(?i)^https?://.*"))
							value = "http://" + value;
						data[id].put(fieldName, value);
					} else if (fieldName.equals("banner") && fi.getSize() > 0) {
						String ext = "";
						int idx = filenewName.indexOf('.');
						if (idx >= 0)
							ext = filenewName.substring(idx);
						String filename = String.format("banner%d.%d%s", id + 1, Math.abs(random.nextLong()), ext);
						if (log.isInfoEnabled()) {
							log.info("filename--------"+filename);
						}
						fi.write(new File(bannerDir, filename));
						data[id].put("filename", filename);
					}
				}

				List<String> sqls = new ArrayList<String>();
				for (int i = 0; i < bannerCount; i++)
					if (data[i] != null) {
						String filename = data[i].get("filename");
						filename = filename != null ? filename.replaceAll("'", "\\\\'") : "";
						String link = data[i].get("link");
						link = link != null ? link.replaceAll("'", "\\\\'") : "";

						String sql;
						if (data[i].containsKey("delete")) {
							sql = String.format("DELETE FROM %s.banners WHERE id=%d", dbName, i + 1);
						} else {
							sql = String.format("INSERT INTO %s.banners (id,filename,link) VALUES (%d,'%s','%s') "
									+ "ON DUPLICATE KEY UPDATE link='%s'", dbName, i + 1, filename, link, link);
							if (!filename.isEmpty())
								sql += ",filename='" + filename + "'";
						}
						sqls.add(sql);
						
					}
				final int size =  sqls.size();
				String[] arr = (String[])sqls.toArray(new String[size]);
				DaoContext.getCommonDao().insertBatch(arr, site);
				MemCache.INSTANCE.set("user_resource_json." + dbName, "");


				resp.getWriter().write("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><script>window.parent.window.location.href=window.parent.window.location</script></head><body></body></html>");
			} catch (SizeLimitExceededException e) {
				error(resp, "图片文件过大，每张图片应不超过" + bannerSizeLimit + "K");
			} catch (FileSizeLimitExceededException e) {
				error(resp, "图片文件过大，每张图片应不超过" + bannerSizeLimit + "K");
			} catch (Exception e) {
				log.error("file upload error: site=" + site, e);
				error(resp, "提交失败，请重试");
			}
		}
		*/
	}

	@SuppressWarnings("unused")
	private void error(HttpServletResponse resp, String msg) throws IOException {
		log.info("fileUpdate ..." );
		resp.getWriter().write("<html><head><meta http-equiv='Content-Type' content='text/html; charset=utf-8'/><script>window.parent.document.getElementById('errorMsg').innerText='" + msg + "'</script></head><body></body></html>");
	}
}
