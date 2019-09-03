package com.zw.test.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

public interface DocsService {
	public String uploadFile_dzjz(HttpServletRequest request, MultipartFile[] dFile, String uuid) throws Exception;
}
