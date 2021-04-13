package org.springblade.modules.group.controller;

import lombok.SneakyThrows;
import org.springblade.core.oss.minio.MinioTemplate;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.ObjectUtil;


import org.springblade.modules.group.entity.Uploadimg;
import org.springblade.modules.group.service.IUploadimgService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.util.*;

@RestController
@RequestMapping("file/upload")
public class UploadImgController {

	@Autowired
	private MinioTemplate minioTemplate;

	@Autowired
	private IUploadimgService uploadimgService;

	/**
	 * 上传图片的接口
	 * @param files
	 * @param bucketName
	 * @param gbgid
	 * @param gimgid
	 * @param abgid
	 * @param aimgid
	 * @param clogid
	 * @param eimgid
	 * @return
	 */
	@SneakyThrows
	@PostMapping(value = "/upload")
	public R<?> upload(@RequestParam("files")MultipartFile[] files, String bucketName,
					   @RequestParam(value="gbgid",defaultValue ="0") Integer gbgid,
					   @RequestParam(value="gimgid",defaultValue ="0") Integer gimgid,
					   @RequestParam(value="abgid",defaultValue ="0") Integer abgid,
					   @RequestParam(value="aimgid",defaultValue ="0") Integer aimgid,
					   @RequestParam(value="clogid",defaultValue ="0") Integer clogid,
					   @RequestParam(value="eimgid",defaultValue ="0") Integer eimgid,
					   @RequestParam(value="cimgid",defaultValue ="0") Integer cimgid) {
		String bucket = ObjectUtil.isEmpty(bucketName) || "null".equals(bucketName) ? "notice" : bucketName;
		Set data = new HashSet<>();
		if(files != null && files.length > 0){
			for(int i=0; i < files.length; i++){
				MultipartFile file = files[i];
				if(!file.isEmpty()){
					Map<String,Object> map = new HashMap<>();
					BladeFile bladeFile = this.minioTemplate.putFile(bucket, file.getOriginalFilename(), file.getInputStream());
					Uploadimg uploadimg = new Uploadimg();
					String fileName = bladeFile.getOriginalName();
					uploadimg.setName(fileName);
					uploadimg.setUrl(bladeFile.getLink());
					uploadimg.setCreateAt(new Date());
					uploadimg.setSuffix(fileName.substring(fileName.lastIndexOf(".") +1));
					uploadimg.setFileSize(file.getSize());
					if(gbgid!=0){
						uploadimg.setGbgid(gbgid);
					}
					if(gimgid!=0){
						uploadimg.setGimgid(gimgid);
					}
					if(abgid!=0){
						uploadimg.setAbgid(abgid);
					}
					if(aimgid!=0){
						uploadimg.setAimgid(aimgid);
					}
					if(clogid!=0){
						uploadimg.setClogid(clogid);
					}
					if(eimgid!=0){
						uploadimg.setEimgid(eimgid);
					}
					if(cimgid!=0){
						uploadimg.setCimgid(cimgid);
					}
					uploadimgService.saveOrUpdate(uploadimg);
					map.put("id",uploadimg.getId());
					map.put("name",bladeFile.getOriginalName());
					data.add(map);
				}
			}
		}
		return R.data(data);
	}

	/**
	 * 获取图片接口
	 */
	@RequestMapping("/getUploadimg")
	public R<Uploadimg> getUploadimg(@RequestParam(value="gbgid",defaultValue ="0") Integer gbgid,
									 @RequestParam(value="gimgid",defaultValue ="0") Integer gimgid,
									 @RequestParam(value="clogid",defaultValue ="0") Integer clogid,
									 @RequestParam(value="cimgid",defaultValue ="0") Integer cimgid){
		Uploadimg uploadimg = new Uploadimg();
		if(gbgid!=0){
			uploadimg=uploadimgService.findBygbgid(gbgid);
		}
		if(gimgid!=0){
			uploadimg=uploadimgService.findBygimgid(gimgid);
		}
		if(clogid!=0){
			uploadimg=uploadimgService.findByclogid(clogid);
		}
		if(cimgid!=0){
			uploadimg=uploadimgService.findBycimgid(cimgid);
		}
		return R.data(uploadimg);
	}

}
