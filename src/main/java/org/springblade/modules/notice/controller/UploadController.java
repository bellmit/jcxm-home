package org.springblade.modules.notice.controller;

import lombok.SneakyThrows;
import org.springblade.core.oss.minio.MinioTemplate;
import org.springblade.core.oss.model.BladeFile;
import org.springblade.core.tool.api.R;
import org.springblade.core.tool.utils.ObjectUtil;
import org.springblade.modules.notice.entity.Uploadfile;
import org.springblade.modules.notice.service.IUploadfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@RestController
@RequestMapping("ylkj-file/upload")
public class UploadController {

	@Autowired
	private MinioTemplate minioTemplate;

	@Autowired
	private IUploadfileService uploadfileService;

	@SneakyThrows
	@PostMapping(value = "/upload")
	public R<?> upload(@RequestParam("files") MultipartFile[] files, String bucketName) {
		String bucket = ObjectUtil.isEmpty(bucketName) || "null".equals(bucketName) ? "notice" : bucketName;
		Set data = new HashSet<>();
		if(files != null && files.length > 0){
			for(int i=0; i < files.length; i++){
				MultipartFile file = files[i];
				if(!file.isEmpty()){
					Map<String,Object> map = new HashMap<>();
					BladeFile bladeFile = this.minioTemplate.putFile(bucket, file.getOriginalFilename(), file.getInputStream());
					Uploadfile uploadfile = new Uploadfile();
					String fileName = bladeFile.getOriginalName();
					uploadfile.setName(fileName);
					uploadfile.setUrl(bladeFile.getLink());

					uploadfile.setCreateAt(new Date());
					uploadfile.setSuffix(fileName.substring(fileName.lastIndexOf(".") +1));
					uploadfile.setFileSize(file.getSize());
					uploadfileService.saveOrUpdate(uploadfile);
					map.put("id",uploadfile.getId());
					map.put("name",bladeFile.getOriginalName());
					data.add(map);
				}
			}
		}
		return R.data(data);
	}


}
