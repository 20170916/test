package com.lo.springbootdemo.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController()
@RequestMapping(value = "file",produces = "application/json; charset=UTF-8")
public class UserFileController {

	private static final Logger logger = LoggerFactory.getLogger(UserFileController.class.getName());




	@RequestMapping("/uploadDir")
	public String upload(Integer pid, HttpServletRequest request) throws IOException {
    	//String userId=loginUser().getId();
		pid=6;
    	String userId="lo";
    	try{
			MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
			List<MultipartFile> multipartFiles = params.getFiles("fileFolder");
			//构建所有目录；文件增加
			Map<String,byte[]> userfileMap=new HashMap<>(multipartFiles.size());
			for(MultipartFile multipartFile:multipartFiles){
				System.out.println(multipartFile.getOriginalFilename());//原始文件名（全路径）
				System.out.println(multipartFile.getBytes());//文件字节流
				userfileMap.put(multipartFile.getOriginalFilename(),multipartFile.getBytes());

			}
			//userFileService.uploadDir(userfileMap,pid,userId);
			return "ok";
		}catch (Throwable throwable){
			String errorMsg = "用户："+"，下载失败";
			logger.error(errorMsg, throwable.toString());
			return errorMsg;
		}
	}

}
