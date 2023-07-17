package com.example.mealme.service.admin;

import com.example.mealme.dto.ProductFileDto;
import com.example.mealme.mapper.ProductFileMapper;

import lombok.RequiredArgsConstructor;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductFileService {
    private final ProductFileMapper productFileMapper;

    //    application.properties에 저장해둔 file.dir프로퍼티 값을 가져온다.
    @Value("${file.dir}")   // file.dir = /upload 와 같은 이름을 넣어야 된다.
    private String fileDir;


    public void register(ProductFileDto productFileDto){
        if(productFileDto==null){ throw new IllegalArgumentException("파일 정보 누락");}
        productFileMapper.insert(productFileDto);
    }
    public void remove(Long productNumber){
        if (productNumber == null){ throw new IllegalArgumentException("게시물 번호 누락(FIle)");}

        List<ProductFileDto> fileList = findList(productNumber);

        for(ProductFileDto file : fileList){
            File target = new File(fileDir, file.getFileUploadPath() + "/" + file.getFileUuid() + "_" + file.getFileName());
            File thumbnail = new File(fileDir, file.getFileUploadPath() + "/th_" + file.getFileUuid() + "_" + file.getFileName());

            if(target.exists()){
                target.delete();
            }

            if(thumbnail.exists()){
                thumbnail.delete();
            }
        }

        productFileMapper.delete(productNumber);
    }

    public List<ProductFileDto> findList(Long productNumber){

        return productFileMapper.selectList(productNumber);
    }

    //    파일 저장 처리
    public ProductFileDto saveFile(MultipartFile file) throws IOException {
//        사용자가 올린 파일 이름(확장자를 포함)
        String originName = file.getOriginalFilename();
        originName = originName.replaceAll("\\s+", ""); //파일이름에 공백이 들어오면 처리해준다.
//        파일 이름에 붙여줄 uuid 생성(파일 이름 중복이 나오지 않게 처리)
        UUID uuid = UUID.randomUUID();
//        uuid와 파일 이름을 합쳐준다.
        String sysName = uuid.toString() + "_" + originName;

        File uploadPath = new File(fileDir, getUploadPath());  // (상위 경로, 하위 경로)

//        경로가 존재하지 않는다면(폴더가 없다면)
        if(!uploadPath.exists()){
//            경로에 필요한 폴더를 생성한다.
            uploadPath.mkdirs();   // mkdirs : makedirectories의 약자
        }

//        전체 경로와 파일 이름을 연결한다.
        File uploadFile = new File(uploadPath, sysName);

//        매개변수로 받은 파일을 우리가 만든 경로와 이름으로 저장한다.
//        transferTo(경로)
//        MultipartFile객체를 실제로 저장시킨다.
//        저장시킬 경로와 이름을 매개변수로 넘겨주면 된다.
        file.transferTo(uploadFile);

//        썸네일 저장처리
//        이미지 파일인 경우에만 처리하는 조건식
        if(Files.probeContentType(uploadFile.toPath()).startsWith("image")){
            FileOutputStream out = new FileOutputStream(new File(uploadPath, "th_"+sysName));
            Thumbnailator.createThumbnail(file.getInputStream(),out,300,200);
            out.close();
        }

//        productNumber 를 제외한 모든 정보를 가진 ProductFileDto를 반환한다.
        ProductFileDto productFileDto = new ProductFileDto();
        productFileDto.setFileUuid(uuid.toString());
        productFileDto.setFileName(originName);
        productFileDto.setFileUploadPath(getUploadPath());


        return productFileDto;
    }

    /**
     * 파일 리스트를 DB등록 및 저장 처리
     *
     * @param files 여러 파일을 담은 리스트
     * @param productNumber 파일이 속하는 게시글 번호
     * @throws IOException
     */
    public void registerAndSaveFiles(List<MultipartFile> files, Long productNumber) throws IOException{
        for(MultipartFile file : files){
            ProductFileDto productFileDto = saveFile(file);
            productFileDto.setProductNumber(productNumber);
            register(productFileDto);
        }
    }

    //    파일이 저장되는 하위 경로를 현재 날짜로 설정할 것이기 때문에 현재 날짜를 구한다.
    private String getUploadPath(){
        return new SimpleDateFormat("yyyy/MM/dd").format(new Date());

    }

    // 파일 체크
    public boolean productFileCheck(Long productNumber){
        if (productNumber == null) {
            throw new IllegalArgumentException("상품 번호 누락(file)");
        }
        return productFileMapper.productCheck(productNumber) > 0;
    }


}


