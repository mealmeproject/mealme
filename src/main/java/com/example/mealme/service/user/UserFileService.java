package com.example.mealme.service.user;

import com.example.mealme.dto.CompanyRegistrationFileDto;
import com.example.mealme.dto.UserFileDto;
import com.example.mealme.mapper.UserFileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class UserFileService {
    private final UserFileMapper userFileMapper;

    @Value("${file.dir}")
    private String fileDir;

    // 기업 회원가입의 사업자 등록증 파일처리
    public void companyRegistrationFileRegister(CompanyRegistrationFileDto companyRegistrationFileDto){
        if(companyRegistrationFileDto == null) { throw new IllegalArgumentException("파일 정보 누락"); }
        userFileMapper.registrationInsert(companyRegistrationFileDto);
    }

    public CompanyRegistrationFileDto companyRegistrationFindFile(Long companyNumber){
        return userFileMapper.registrationSelect(companyNumber);
    }

    public void companyRegistrationRemove(Long companyNumber){
        if (companyNumber == null) {
            throw new IllegalArgumentException("게시물 번호 누락(file)");
        }
        CompanyRegistrationFileDto file = companyRegistrationFindFile(companyNumber);

            File target = new File(fileDir, file.getFileUploadPath() + "/" + file.getFileUuid() + "_" + file.getFileName());
            File thumbnail = new File(fileDir, file.getFileUploadPath() + "/th_" + file.getFileUuid() + "_" + file.getFileName());

            if (target.exists()){
                target.delete();
            }
            if (thumbnail.exists()){
                thumbnail.delete();
            }

        userFileMapper.registrationDelete(companyNumber);
    }

    //    파일 저장 처리
    public CompanyRegistrationFileDto companyRegistrationSaveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = file.getOriginalFilename();
//        파일이름에 공백이 들어오면 처리해준다.(공백 제거)
        originName = originName.replace("\\s+", "");

//        파일 이름에 붙여줄 uuid 생성(파일이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(fileDir, getUploadPath());

//        경로가 존재하지 않는다면(폴더가 없다면)
        if(!uploadPath.exists()){
//            경로에 필요한 폴더를 생성한다.
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath, sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
//        transferTo(경로)
//        MultipartFile객체를 실제로 저장시킨다.
//        저장시킬 경로와 이름을 매개변수로 넘겨주면 된다.
        file.transferTo(uploadFile);

//        boardNumber를 제외한 모든 정보를 가진 FileDto를 반환한다.
        CompanyRegistrationFileDto companyRegistrationFileDto = new CompanyRegistrationFileDto();
        companyRegistrationFileDto.setFileUuid(uuid.toString());
        companyRegistrationFileDto.setFileName(originName);
        companyRegistrationFileDto.setFileUploadPath(getUploadPath());

        return companyRegistrationFileDto;
    }

    /**
     * 파일 리스트를 DB등록 및 저장 처리
     *
     * @param companyNumber 파일이 속하는 게시글 번호
     * @throws IOException
     */
    public void companyRegistrationRegisterAndSaveFiles(MultipartFile file, Long companyNumber) throws IOException{
            CompanyRegistrationFileDto companyRegistrationFileDto = companyRegistrationSaveFile(file);
            companyRegistrationFileDto.setCompanyNumber(companyNumber);
            companyRegistrationFileRegister(companyRegistrationFileDto);

    }

    //    파일이 저장되는 하위 경로를 현재 날짜로 설정할 것이기 때문에 현재날짜를 구한다.
    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());
    }




    // 유저 프로필 사진 처리 *******************************************************************************
    public void userFileRegister(UserFileDto userFileDto){
        if(userFileDto == null) { throw new IllegalArgumentException("파일 정보 누락"); }
        userFileMapper.userInsert(userFileDto);
    }

    public UserFileDto userProfileFindFile(Long userNumber){
        return userFileMapper.userSelect(userNumber);
    }

    public void userRemove(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("유저 번호 누락(file)");
        }
        UserFileDto file = userProfileFindFile(userNumber);

        File target = new File(fileDir, file.getFileUploadPath() + "/" + file.getFileUuid() + "_" + file.getFileName());
        File thumbnail = new File(fileDir, file.getFileUploadPath() + "/th_" + file.getFileUuid() + "_" + file.getFileName());

        if (target.exists()){
            target.delete();
        }
        if (thumbnail.exists()){
            thumbnail.delete();
        }

        userFileMapper.userDelete(userNumber);
    }

    //    파일 저장 처리
    public UserFileDto userSaveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = file.getOriginalFilename();
//        파일이름에 공백이 들어오면 처리해준다.(공백 제거)
        originName = originName.replace("\\s+", "");

//        파일 이름에 붙여줄 uuid 생성(파일이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();

//        uuid와 파일이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(fileDir, getUploadPath());

//        경로가 존재하지 않는다면(폴더가 없다면)
        if(!uploadPath.exists()){
//            경로에 필요한 폴더를 생성한다.
            uploadPath.mkdirs();
        }

//        전체 경로와 파일이름을 연결한다.
        File uploadFile = new File(uploadPath, sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
//        transferTo(경로)
//        MultipartFile객체를 실제로 저장시킨다.
//        저장시킬 경로와 이름을 매개변수로 넘겨주면 된다.
        file.transferTo(uploadFile);

//        boardNumber를 제외한 모든 정보를 가진 FileDto를 반환한다.
        UserFileDto userFileDto = new UserFileDto();
        userFileDto.setFileUuid(uuid.toString());
        userFileDto.setFileName(originName);
        userFileDto.setFileUploadPath(getUploadPath());

        return userFileDto;
    }

    /**
     * 파일 리스트를 DB등록 및 저장 처리
     *
     * @param userNumber 파일이 속하는 게시글 번호
     * @throws IOException
     */
    public void userRegisterAndSaveFiles(MultipartFile file, Long userNumber) throws IOException{
        UserFileDto userFileDto = userSaveFile(file);
        userFileDto.setUserNumber(userNumber);
        userFileRegister(userFileDto);

    }

    // 파일 체크
    public boolean userProfileFileCheck(Long userNumber){
        if (userNumber == null) {
            throw new IllegalArgumentException("유저 번호 누락(file)");
        }
        return userFileMapper.userCheck(userNumber) > 0;
    }


}






