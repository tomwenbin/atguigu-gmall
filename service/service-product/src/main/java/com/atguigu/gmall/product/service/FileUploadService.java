package com.atguigu.gmall.product.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface FileUploadService {

    String upload(MultipartFile file) throws  Exception;
}
