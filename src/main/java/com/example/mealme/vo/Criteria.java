package com.example.mealme.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor


public class Criteria {
    private int page; //현재페이지
    private int amount; //한 페이지당 게시물 수

    //컨트롤러의 매개변수는 자동으로 기본생성자가 실행된다.
    // 그러므로 page, amount에 대한 데이터가 전달되지 않으면 자동으로 1페이지의 내용이 리스트에 나타나게 된다.
    //만약 page, amount에 대한 데이터가 URL을 통해 들어온다면 criteria객체으 setter 가 실행된다.
    //page 만 들어와도 amount는 기본생정자에 의해 12로 초기화가 된다.


    public Criteria() {
        this(1, 12);
    }

    public int getPage() {
        return page; // 가정: 기존에 page 속성이 존재함
    }

    public int getAmount() {
        return amount; // 가정: 기존에 amount 속성이 존재함
   /* public Criteria(int page, int amount) {
        this.page = page;
        this.amount = amount;
        //위와 아래는 같다.
    }*/
    }
}

