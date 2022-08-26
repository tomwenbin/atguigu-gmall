package com.atguigu.gmall.product.service.impl;

import com.atguigu.gmall.common.util.DateUtil;
import com.atguigu.gmall.product.config.minio.MinioProperties;
import com.atguigu.gmall.product.service.FileUploadService;

import io.minio.MinioClient;
import io.minio.PutObjectOptions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

@Service
public class FileUploadServiceImpl implements FileUploadService {


    @Autowired
    MinioClient minioClient;
    @Autowired
    MinioProperties minioProperties;

    @Override
    public String upload(MultipartFile file) throws Exception {
        //Minio是一个时间敏感的中间件，
        //The difference between the request time and the server's time is too large
        // 1、使用MinIO服务的URL，端口，Access key和Secret key创建一个MinioClient对象

        //2、检查存储桶是否已经存在
            //3、如果桶不存在需要先创建一个桶
        boolean gmall = minioClient.bucketExists(minioProperties.getBucketName());
        if(!gmall){
            //桶不存在
            minioClient.makeBucket(minioProperties.getBucketName());
        }
        //4、使用putObject上传一个文件到存储桶中。
        /**
         * String bucketName, 桶名
         * String objectName, 对象名，也就是文件名
         * InputStream stream, 文件流  D:\0310\尚品汇\资料\03 商品图片\品牌\pingguo.png
         * PutObjectOptions options, 上传的参数设置
         */
        //文件流
        InputStream inputStream = file.getInputStream();
        String dateStr = DateUtil.formatDate(new Date());
        String fileName = UUID.randomUUID().toString().replace("-","") + "_" + file.getOriginalFilename();
        //原始文件名
        String contentType = file.getContentType();
        //文件上传参数：long objectSize, long partSize
        PutObjectOptions options = new PutObjectOptions(inputStream.available(), -1L);
        options.setContentType(contentType);

        //告诉Minio上传的这个文件的内容类型
        minioClient.putObject(
                minioProperties.getBucketName(),
                dateStr+"/"+ fileName,
                inputStream,
                options
        );


        //5、http://192.168.200.100:9000/gmall/filename
        String url = minioProperties.getEndpoint()+"/"+minioProperties.getBucketName()+"/"+dateStr+"/"+fileName;
//        String url1 = "http://192.168.200.100:9000/gmall/"+ fileName;
        return url;
    }
}
