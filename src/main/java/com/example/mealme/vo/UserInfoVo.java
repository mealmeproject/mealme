package com.example.mealme.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@NoArgsConstructor
public class UserInfoVo {
    private Long userNumber;
    private String userName;
    private String userNickname;
    private String userComment;
    private String fileNumber;
    private String fileName;
    private String fileUploadPath;
    private String fileUuid;
    private Long consultingRequestCount;
    private Long orderCount;
    private Long boardCount;
    private String getFile;


    public UserInfoVo userInfoSetUp(){
        String settingFile = "";
        if (fileName == null){
            settingFile = "/img/sampleProfile.png";
        }else {
            settingFile = "/upload/" + fileUploadPath + "/" + fileUuid + "_" + fileName;
        }

        UserInfoVo userInfoVo  = new UserInfoVo();
        userInfoVo.setUserNumber(userNumber);
        userInfoVo.setUserName(userName);
        userInfoVo.setUserNickname(userNickname);
        userInfoVo.setUserComment(userComment);
        userInfoVo.setFileNumber(fileNumber);
        userInfoVo.setFileName(fileName);
        userInfoVo.setFileUploadPath(fileUploadPath);
        userInfoVo.setFileUuid(fileUuid);
        userInfoVo.setConsultingRequestCount(consultingRequestCount);
        userInfoVo.setOrderCount(orderCount);
        userInfoVo.setBoardCount(boardCount);
        userInfoVo.setGetFile(settingFile);

        return userInfoVo;
    }
}
