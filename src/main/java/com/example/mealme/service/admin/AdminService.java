package com.example.mealme.service.admin;

import com.example.mealme.dto.*;
import com.example.mealme.mapper.AdminMapper;
import com.example.mealme.util.Util;
import com.example.mealme.vo.SearchProductVo;
import com.example.mealme.vo.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
@Transactional
public class AdminService {
    private final AdminMapper adminMapper;
    private final ProductFileService productFileService;



    public void register(UserDto userDto){
        if(userDto == null){
            throw new IllegalArgumentException("회원 정보가 없습니다.");
        }
        adminMapper.insert(userDto);
    }


//    전체 조회
    public List<UserDto> findAll(){
        return adminMapper.selectAll();
    }

//    검색 조회
    public List<UserDto> searchUserList(SearchVo searchVo, Criteria criteria){
        return adminMapper.searchUser(searchVo, criteria);
    }

//    기업 검색 조회
public List<CompanyDto> searchCompanyList(SearchCompanyVo searchCompanyVo, Criteria criteria){
    return adminMapper.searchCompany(searchCompanyVo, criteria);
}
    //    상품 검색 조회
    public List<ProductVo> searchProductList(SearchProductVo searchProductVo, Criteria criteria){
        if(searchProductVo.getSearchType().equals("number")){
            Long productNumber = Long.parseLong(searchProductVo.getKeyword());
            searchProductVo.setKeyword2(productNumber);
            System.out.println("keyword2 설정 !!!!!!!!!!!!!" + productNumber);
        }
        return adminMapper.productListSelect(searchProductVo, criteria);
    }

    //전체 상품 수 조회
    @Transactional(readOnly = true)
    public int getProductTotal(SearchProductVo searchProductVo){
        if(searchProductVo.getSearchType().equals("number")){
            Long productNumber = Long.parseLong(searchProductVo.getKeyword());
            searchProductVo.setKeyword2(productNumber);
            System.out.println("keyword2 설정 !!!!!!!!!!!!!" + productNumber);
        }
        return adminMapper.selectProductTotal(searchProductVo);
    }

    public int productCountTotal(){
        return adminMapper.productCountTotal();
    }

//    유저 삭제
    public void deleteUserList(List<String> checkBoxArr){
        if(checkBoxArr == null){
            throw new IllegalArgumentException("회원을 선택하세요.");
        }

        for(int i=0; i<checkBoxArr.size(); i++){
            adminMapper.delete(checkBoxArr.get(i));
        }


    }

    //조회
//
//    /**
//     *
//     * @param
//     * @throws IllegalArgumentException 게시물 번호가 존재하지 않으면 발생된다.
//     */
////    @Transactional(readOnly = true)
////    public UserDto findBoard(Long boardNumber){
////        if (boardNumber==null){
////            throw new IllegalArgumentException("게시물 번호가 없습니다.");
////        }
////        return Optional.ofNullable(boardMapper.select(boardNumber))
////                .orElseThrow(()->{throw new IllegalArgumentException("존재하지 않는 게시물 번호");});
////
////    }



    //전체조회
    @Transactional(readOnly = true)
    public List<UserDto> findAll(Criteria criteria){
        return adminMapper.selectAll(criteria);
    }

    //전체 게시글 수 조회
    @Transactional(readOnly = true)
    public int getTotal(SearchVo searchVo){
        return adminMapper.selectTotal(searchVo);
    }

    public int userTotal(){return adminMapper.userTotal();}

    //전체 기업 수 조회
    @Transactional(readOnly = true)
    public int getCompanyTotal(SearchCompanyVo searchCompanyVo){
        return adminMapper.selectCompanyTotal(searchCompanyVo);
    }
    public int companyCountTotal(){
        return adminMapper.companyCountTotal();
    }

//  대분류 조회
    @Transactional(readOnly = true)
    public List<ProductCategory1Dto> findCategory(){
        return adminMapper.selectCategory();

    }
//  중분류 조회
    @Transactional(readOnly = true)
    public List<ProductCategory2Dto> findCategory2(Long categoryCode1){
        return adminMapper.selectCategory2(categoryCode1);
    }

//  상품추가
    public void registerProduct(ProductDto productDto){
        System.out.println(productDto);
        if(productDto==null){
            throw new IllegalArgumentException("상품 정보가 없습니다.(null)");
        }
        adminMapper.insertProduct(productDto);
        System.out.println(productDto);
    }
//    상품 수정
    public void changeProduct(ProductDto productDto, ProductVo productVo, List<MultipartFile> files) throws IOException {
        if(productDto == null || files == null){
            throw new IllegalArgumentException("상품 수정 매개변수 null 체크");
        }
        if(productVo == null || files == null){
            throw new IllegalArgumentException("상품 수정 매개변수 null 체크");
        }

        productFileService.remove(productDto.getProductNumber());
        productFileService.registerAndSaveFiles(files, productDto.getProductNumber());
        adminMapper.updateProduct(productDto);
    }

//    상품조회
    public ProductVo modifyProduct(Long productNumber){
       return adminMapper.selectProduct(productNumber);
    }

//  주문 조회
    public List<OrderVo> findOrderList(SearchProductVo searchProductVo, Criteria criteria){
        return adminMapper.selectOrderList(searchProductVo, criteria);
    }
//   주문 수 조회
    public int findOrderTotal(SearchProductVo searchProductVo){
        return adminMapper.selectOrderTotal(searchProductVo);
    }
    public int orderCountTotal(){
        return adminMapper.orderCountTotal();
    }

//   주문 상태 변경
    public void modifyStatus(Long orderConditionCode, List<String> orderNumber){
        if(orderNumber == null){
            throw new IllegalArgumentException("회원을 선택하세요.");
        }

        for(int i=0; i<orderNumber.size(); i++){
            adminMapper.modifyCondition(orderNumber.get(i),orderConditionCode);
        }


    }

    //  기업 상태 변경
    public void modifyCompanyStatus(Long companyStatus, List<String> companyNumber){
        if(companyNumber == null){
            throw new IllegalArgumentException("기업을 선택하세요.");
        }

        for(int i=0; i<companyNumber.size(); i++){
            adminMapper.companyStatus(companyNumber.get(i),companyStatus);
        }


    }

//    상태명 뽑기
    public String getConditionName(Long orderConditionCode){
        return adminMapper.getConditionName(orderConditionCode);
    }

//상태 갯수 조회
    public List<OrderDto> getStatusCount(){
        return adminMapper.statusCount();
    }

//    일반, 기업회원 조회 및 총합
    public UserTotalVo getUserTotal(){
       return adminMapper.userTotalCount();

    }

    public Long findAdminNumber(String id, String password){
        if (id == null || password == null){
            throw new IllegalArgumentException("관리자 id , password 누락 !" );
        }
        String adminPw = Util.pwSha256(password);

        return Optional.ofNullable(adminMapper.selectAdminNumber(id, adminPw))
                .orElseThrow(() -> {
                    throw new IllegalArgumentException("관리자 정보 없음 !");
                });
    }


}
