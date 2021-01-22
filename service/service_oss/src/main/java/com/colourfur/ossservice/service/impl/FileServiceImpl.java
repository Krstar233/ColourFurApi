package com.colourfur.ossservice.service.impl;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.colourfur.ossservice.service.FileService;
import com.colourfur.ossservice.utils.ConstantPropertiesUtils;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {
    @Override
    public String upload(MultipartFile file) {
        try {
            // Endpoint以杭州为例，其它Region请按实际情况填写。
            String endpoint = ConstantPropertiesUtils.END_POINT;
            // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
            String accessKeyId = ConstantPropertiesUtils.ACCESS_KEY_ID;
            String accessKeySecret = ConstantPropertiesUtils.ACCESS_KEY_SECRET;

            // 创建OSSClient实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            // 上传文件流。
            InputStream inputStream = file.getInputStream();

            String datePath = new DateTime().toString("yyyy/MM/dd");
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            String filename = datePath + "/" + uuid + file.getOriginalFilename();
            String bucketname = ConstantPropertiesUtils.BUCKET_NAME;
            ossClient.putObject(bucketname, filename, inputStream);

            // 关闭OSSClient。
            ossClient.shutdown();
            // 文件地址
            String url = "https://" + bucketname + "." + endpoint + "/" + filename;
            return url;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
