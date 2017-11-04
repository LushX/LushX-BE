package cn.mailu.LushX.util;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @Author: NULL
 * @Description:ftp工具类
 * @Date: Create in 2017/11/4 16:46
 */
public class FTPUtil {

    private static Logger logger= LoggerFactory.getLogger(FTPUtil.class);
    private static String ftpIp=PropertiesUtil.getProperty("ftp.server.ip");
    private static String ftpUser=PropertiesUtil.getProperty("ftp.user");
    private static String ftpPass=PropertiesUtil.getProperty("ftp.pass");

    private String ip;
    private int port;
    private String user;
    private String pwd;
    private FTPClient ftpClient;

    public FTPUtil(String ip,int port,String user,String pwd){
        this.ip=ip;
        this.port=port;
        this.user=user;
        this.pwd=pwd;
    }

    /**
     * @Title: 对外公开上传文件方法
     * @Description:
     * @param [fileList]
     * @return boolean    返回类型
     * @throws
     */
    public static boolean uploadFile(List<File> fileList) throws IOException {

        FTPUtil ftpUtil=new FTPUtil(ftpIp,21,ftpUser,ftpPass);
        logger.info("开始连接ftp服务器");
        boolean result=ftpUtil.uploadFile("img",fileList);
        logger.info("开始连接ftp服务器，结束上传，上传结果：{}",result);
        return result;
    }

    private boolean connectServer(String ip,int port,String user,String pass){
        boolean isSuccess=false;
        ftpClient=new FTPClient();
        try {
            ftpClient.connect(ip);
            isSuccess=ftpClient.login(user,pass);
        } catch (IOException e) {
            logger.error("连接ftp服务器异常",e);
        }
        return isSuccess;
    }

    private boolean uploadFile(String remotePath,List<File> fileList) throws IOException {
        FileInputStream fis=null;
        boolean uploaded=true;
        if(connectServer(this.getIp(),this.getPort(),this.getUser(),this.getPwd())){
            try {
                ftpClient.changeWorkingDirectory(remotePath);
                ftpClient.setBufferSize(1024);
                ftpClient.setControlEncoding("UTF-8");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                ftpClient.enterLocalPassiveMode();
                for(File fileItem : fileList){
                  fis= new FileInputStream(fileItem);
                  ftpClient.storeFile(fileItem.getName(),fis);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                fis.close();
                ftpClient.disconnect();
            }
        }
        return uploaded;
    }


    public static String getFtpIp() {
        return ftpIp;
    }

    public static void setFtpIp(String ftpIp) {
        FTPUtil.ftpIp = ftpIp;
    }

    public static String getFtpUser() {
        return ftpUser;
    }

    public static void setFtpUser(String ftpUser) {
        FTPUtil.ftpUser = ftpUser;
    }

    public static String getFtpPass() {
        return ftpPass;
    }

    public static void setFtpPass(String ftpPass) {
        FTPUtil.ftpPass = ftpPass;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public FTPClient getFtpClient() {
        return ftpClient;
    }

    public void setFtpClient(FTPClient ftpClient) {
        this.ftpClient = ftpClient;
    }
}
