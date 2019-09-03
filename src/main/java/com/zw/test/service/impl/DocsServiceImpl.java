package com.zw.test.service.impl;

import java.io.File;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.zw.test.service.DocsService;

@Service("DocsService")
public class DocsServiceImpl implements DocsService {

	@Override
	public String uploadFile_dzjz(HttpServletRequest request, MultipartFile[] dFile, String uuid) throws Exception {
		String returnValue = null;
		File yFile, xFile;
		String fileName, saveName, ext, fileMD5, newFileMD5, savePath = null;
		int docTag = 0;
		// IDocDocs tdd;
		if (StringUtils.isNotBlank(uuid)) {
			String filePath = request.getSession().getServletContext().getRealPath("/") + savePath;
			savePath = request.getSession().getServletContext().getRealPath("/") + savePath;
			File fileDir = new File(filePath);
			boolean mkDir;
			if (!fileDir.isDirectory()) {
				mkDir = fileDir.mkdirs();
			} else {
				mkDir = true;
			}
			if(mkDir) {
				for(MultipartFile file : dFile) {
					if(file != null) {
						fileName = file.getOriginalFilename();
						saveName = UUID.randomUUID().toString();
						ext = fileName.substring(fileName.lastIndexOf('.'));
						yFile = new File(filePath + fileName);
						xFile = new File(filePath + saveName + ext);
						try {
							file.transferTo(yFile);
							returnValue = "success";
						}catch(Exception e) {
							returnValue = "error";
							System.out.println(e.getMessage());
							throw e;
						}
					}
				}
			}
		}
		return returnValue;
	}

}
