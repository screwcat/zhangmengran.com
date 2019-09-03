package com.zw.test.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.zw.test.service.DocsService;

@Controller
public class FileUpload {
	@Autowired
	DocsService docsService;

	// @Value("${spring.file.uploadpath}")
	public String realPath; // 文件保存路径的根路径

	private static List<String> uploadSuffix = new ArrayList<>();
	static {
		if (uploadSuffix.isEmpty()) {
			uploadSuffix.add("jpg");
			uploadSuffix.add("png");
			uploadSuffix.add("bmp");
			uploadSuffix.add("jpeg");
			uploadSuffix.add("pdf");
		}
	}

	// @ResponseBody
	// @RequestMapping(value = "/fileupload", method = RequestMethod.POST, produces
	// = "application/json;charset=UTF-8")
	//
	@CrossOrigin
	@RequestMapping("/fileupload")
	@ResponseBody
	public Object ajaxUploadFile(HttpServletRequest request) {
		// 将request请求的上下文转换为MultipartHttpServletRequest
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;

		String errorMsg = null;
		// List<String> errorList = new ArrayList<>();
		List<String> msgList = new ArrayList<>();
		try {
			// 获取文件
			Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
			// multipartFile = null;//获取文件名
			boolean uploadFlag = true;
			for (Map.Entry<String, MultipartFile> set : fileMap.entrySet()) {
				MultipartFile multipartFile = set.getValue();// 文件名
				String uploadfileName = multipartFile.getOriginalFilename();
				if (StringUtils.isEmpty(uploadfileName)) {
					continue;
				}
				String suffix = uploadfileName.substring(uploadfileName.lastIndexOf(".") + 1).toLowerCase();
				if (!uploadSuffix.contains(suffix)) {
					errorMsg = "文件格式仅限于jpg、png、jpeg、bmp、pdf";
					uploadFlag = false;
					break;
				}
				// System.out.println(1);
				String fileName = this.storeIOcFile(multipartRequest, multipartFile);
				// System.out.println(5+"=="+fileName);
				if (StringUtils.isEmpty(fileName)) {
					errorMsg = "文件上传失败";
					uploadFlag = false;
					break;
				}
				msgList.add(fileName.replace("\\", "/"));
			}
			if (!uploadFlag) {
				return addResultMapMsg(false, errorMsg);
			} else {
				if (!msgList.isEmpty()) {
					return addResultMapMsg(true, msgList);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return addResultMapMsg(false, "文件上传失败");
	}

	private String addResultMapMsg(boolean success, Object msg) {
		JSONObject json = new JSONObject();
		json.put("msg", msg);
		return json.toJSONString();
	}

	private String storeIOcFile(HttpServletRequest request, MultipartFile file) {
		String result = "";
		String _fileName = file.getOriginalFilename();
		// /**使用UUID生成文件名称**/
		String logImageName = UUID.randomUUID().toString() + "_" + _fileName;
		String todayString = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		String uploadDir = request.getParameter("uploadDir");
		String resultStr = File.separator + uploadDir + File.separator + todayString + File.separator + logImageName;
		String fileName = realPath + resultStr;
		File restore = new File(fileName);
		if (!restore.getParentFile().exists()) {
			restore.getParentFile().mkdirs();
		}
		try {
			// System.out.println(3+"=="+fileName);
			file.transferTo(restore);
			// System.out.println(4+"=="+restore);
			result = resultStr;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		return result;
	}

	@RequestMapping("uploadFileOther")
	public void uploadFile(@RequestParam(value = "file", required = false) MultipartFile[] file,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String returnString = docsService.uploadFile_dzjz(request, file, request.getParameter("uuid"));
		switch (returnString) {
		case "success":
			// super.PostResultMessage("0",response);
			break;
		case "error":
			response.sendError(500);
			break;
		default:
			// super.PostResultMessage("0",response);
			break;
		}
	}
}
