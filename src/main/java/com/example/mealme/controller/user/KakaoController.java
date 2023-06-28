package com.example.mealme.controller.user;


import com.example.mealme.service.user.KakaoService;
import com.example.mealme.service.user.UserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;


@Controller
@RequiredArgsConstructor
public class KakaoController {
    private KakaoService kakaoService;
    private UserService userService;

    @RequestMapping(value = "/login/getKakaoAuthUrl")
    public @ResponseBody String getKakaoAuthUrl(HttpServletRequest request) throws Exception {
        System.out.println("나 들어아ㅗㅆ어 !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        String reqUrl =
                "https://kauth.kakao.com/oauth/authorize"
                        + "?client_id=2eb24846056aef9a06e47ac117af01b5"
                        + "&redirect_uri=http://localhost:8181/kakao/kakao_callback"
                        + "&response_type=code";

        return reqUrl;
    }

    // 카카오 연동정보 조회
    @RequestMapping(value = "/login/oauth_kakao")
    public String oauthKakao(
            @RequestParam(value = "code", required = false) String code
            , Model model) throws Exception {

        System.out.println("#########" + code);
        String access_Token = getAccessToken(code);
        System.out.println("###access_Token#### : " + access_Token);


        HashMap<String, Object> userInfo = getUserInfo(access_Token);
        System.out.println("###access_Token#### : " + access_Token);
        System.out.println("###userInfo#### : " + userInfo.get("email"));
        System.out.println("###nickname#### : " + userInfo.get("nickname"));

        JSONObject kakaoInfo =  new JSONObject(userInfo);
        model.addAttribute("kakaoInfo", kakaoInfo);

        return "/meal/myPage"; //본인 원하는 경로 설정
    }

    //토큰발급
    public String getAccessToken (String authorize_code) {
        String access_Token = "";
        String refresh_Token = "";
        String reqURL = "https://kauth.kakao.com/oauth/token";

        try {
            URL url = new URL(reqURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            //  URL연결은 입출력에 사용 될 수 있고, POST 혹은 PUT 요청을 하려면 setDoOutput을 true로 설정해야함.
            conn.setRequestMethod("POST");
            conn.setDoOutput(true);

            //	POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
            StringBuilder sb = new StringBuilder();
            sb.append("grant_type=authorization_code");
            sb.append("&client_id=f8071907fae4877f2a0f469e969f639f");  //본인이 발급받은 key
            sb.append("&redirect_uri=http://localhost:8181/login/oauth_kakao");     // 본인이 설정해 놓은 경로
            sb.append("&code=" + authorize_code);
            bw.write(sb.toString());
            bw.flush();

            //    결과 코드가 200이라면 성공
            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            //    요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            //    Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            access_Token = element.getAsJsonObject().get("access_token").getAsString();
            refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();

            System.out.println("access_token : " + access_Token);
            System.out.println("refresh_token : " + refresh_Token);

            br.close();
            bw.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return access_Token;
    }

    //유저정보조회
    public HashMap<String, Object> getUserInfo (String access_Token) {

        //    요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
        HashMap<String, Object> userInfo = new HashMap<String, Object>();
        String reqURL = "https://kapi.kakao.com/v2/user/me";
        try {
            URL url = new URL(reqURL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            //    요청에 필요한 Header에 포함될 내용
            conn.setRequestProperty("Authorization", "Bearer " + access_Token);

            int responseCode = conn.getResponseCode();
            System.out.println("responseCode : " + responseCode);

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String line = "";
            String result = "";

            while ((line = br.readLine()) != null) {
                result += line;
            }
            System.out.println("response body : " + result);

            JsonParser parser = new JsonParser();
            JsonElement element = parser.parse(result);

            JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
            JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

            String nickname = properties.getAsJsonObject().get("nickname").getAsString();
            String email = kakao_account.getAsJsonObject().get("email").getAsString();

            userInfo.put("accessToken", access_Token);
            userInfo.put("nickname", nickname);
            userInfo.put("email", email);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return userInfo;
    }


//
//    @RequestMapping(value = "/kakao_callback", method = RequestMethod.GET)
//    public String redirectkakao(@RequestParam String code, HttpSession session) throws IOException {
//        System.out.println("code:: " + code);
//
//        // 접속토큰 get
//        String kakaoToken = kakaoService.getReturnAccessToken(code);
//
//        // 접속자 정보 get
//        Map<String, Object> result = kakaoService.getUserInfo(kakaoToken);
//        System.out.println("result:: " + result);
//        String snsId = (String) result.get("id");
//        String userName = (String) result.get("nickname");
//        String email = (String) result.get("email");
//        String gender = (String) result.get("gender");
//        String userpw = snsId;
//
//        // 분기
//        UserDto userDto = new UserDto();
//        // 일치하는 snsId 없을 시 회원가입
//        System.out.println(userService.findUserNumber(snsId, userpw));
//        if (userService.findUserNumber(snsId, userpw) == null) {
//            System.out.println("카카오로 회원가입");
//            userDto.setUserId(email);
//            userDto.setUserPassword(userpw);
//            userDto.setUserName(userName);
//            userDto.setUserNickname(userName);
//            String userGender = "";
//            if (gender.equals("male")){
//                userGender = "M";
//            } else if (gender.equals("femail")) {
//                userGender = "F";
//            }
//            userDto.setUserGender(userGender);
//            userDto.setUserEmail(email);
//            userDto.setUserBirth("2000-01-01");
//            userService.registerUser(userDto);
//        }
//
//        // 일치하는 snsId가 있으면 멤버객체에 담음.
////        System.out.println("카카오로 로그인");
////        String userid = memberService.findUserIdBy2(snsId);
////        MemberVO vo = memberService.findByUserId(userid);
////        System.out.println("member:: " + vo);
////        /*Security Authentication에 붙이는 과정*/
////        CustomUser user = new CustomUser(vo);
////        System.out.println("user : " + user);
////        List<GrantedAuthority> roles = CustomUser.getList(vo);
////        Authentication auth = new UsernamePasswordAuthenticationToken(user, null, roles);
////        System.out.println("auth : " + auth);
////        SecurityContextHolder.getContext().setAuthentication(auth);
//
//        /* 로그아웃 처리 시, 사용할 토큰 값 */
//        session.setAttribute("kakaoToken", kakaoToken);
//
//        return "meal/myPage";
//
//    }

}
