package cn.mailu.LushX.service.impl;

import cn.mailu.LushX.service.FileService;
import cn.mailu.LushX.util.FTPUtils;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.Gson;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.*;
import java.util.Map;
import java.util.UUID;

/**
 * @Author: NULL
 * @Description:
 * @Date: Create in 2017/11/9 15:01
 */
@Service
public class FileServiceImpl implements FileService {

    private Logger logger= LoggerFactory.getLogger(FileServiceImpl.class);
    @Override
    public String upload(MultipartFile file, String path) {
        String fileName=file.getOriginalFilename();
        //获取扩展名
        String  fileExtensionName=fileName.substring(fileName.lastIndexOf(".")+1);
        String uploadFileName= UUID.randomUUID().toString()+"."+fileExtensionName;
        logger.info("开始上传文件，上传的文件名：{}，上传的路径：{}，新文件名：{}",fileName,path,uploadFileName);
        File fileDir=new File(path);
        if(!fileDir.exists()){
            fileDir.setWritable(true);
            fileDir.mkdirs();
        }
        File targetFile=new File(path,uploadFileName);
        try {
            file.transferTo(targetFile);
            //将tagetFile上传到FTP服务器
            FTPUtils.uploadFile(Lists.newArrayList(targetFile));
            //上传完之后，删除upload下面的文件
            targetFile.delete();
        } catch (IOException e) {
            logger.error("上传文件异常",e);
            return null;
        }
        return targetFile.getName();
    }

    public Map uploadImage(String imgStr) {
        Map map = Maps.newHashMap();

        //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null) { //图像数据为空
            map.put("status", 1);
            map.put("message", "上传失败");
            return map;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {//调整异常数据
                    b[i] += 256;
                }
            }
            //默认不指定key的情况下，以文件内容的hash值作为文件名
            String key = null;
            //构造一个带指定Zone对象的配置类
            Configuration cfg = new Configuration(Zone.zone0());
            //...其他参数参考类注释
            UploadManager uploadManager = new UploadManager(cfg);
            //...生成上传凭证，然后准备上传
            String accessKey = "Wv7QvRU7f9CsqhjrgvUfQpdc7jlt4hi1oa7_nx3b";
            String secretKey = "D1R7xuNRwU5ICl6OI77KU0tjwPUsxezIU0qf2L7e";
            String bucket = "lushx";
            //ByteArrayInputStream byteInputStream = new ByteArrayInputStream(b);
            Auth auth = Auth.create(accessKey, secretKey);
            String upToken = auth.uploadToken(bucket);

            Response response = uploadManager.put(b,key,upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            key=putRet.key;
            map.put("status", 0);
            map.put("message", "http://oz6lquw8v.bkt.clouddn.com/" + key);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("status", 1);
            map.put("message", "上传失败");
            return map;
        }
        return map;

    }
}
