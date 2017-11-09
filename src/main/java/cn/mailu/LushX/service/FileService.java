package cn.mailu.LushX.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/9 14:58
 */
public interface FileService {

    String upload(MultipartFile file, String path);
}
