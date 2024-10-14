package com.example.Instargram.global.jwt;

import com.example.Instargram.global.exception.CustomException;
import com.example.Instargram.global.exception.ErrorCode;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtUtil {

    private SecretKey secretKey;
    //10분 토큰 만료 시간 고정
    private final long expiration=1000*60*10;

    //어노테이션은 외부 설정값을 클래스 필드에 주입하는 역할-> 스프링 설정파일에서의 값을 가져옴-> 이값은 JWT 서명을 위한 비밀키로 사용됨
    public JwtUtil(@Value("${spring.jwt.secret}") String secret){
        //여기서 @Value 어노테이션은 Lombok이 아니라 import org.springframework.beans.factory.annotation이다
        secretKey= new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8),
                //앞에 인자는 문자열인 비밀키를 바이트 배열로 변환한다& java의 Secret 인터페이스를 구현한 클래스
                Jwts.SIG.HS256.key().build().getAlgorithm());
                //Jwts는 io.jsonwebtoken 패키지에서 제공하는 JWT 라이브러리의 진입점 클래스입니다.
        //Jwts.SIG.HS256는 HMAC-SHA256 (HS256) 알고리즘을 의미합니다. 이는 대칭 키 기반으로 서명하는 방식입니다.
        //key()는 서명에 사용할 키 빌더를 반환하고, build()는 키 객체를 완성합니다.
        //getAlgorithm()은 이 빌더에서 설정된 서명 알고리즘을 반환합니다. 즉, HS256 알고리즘을 SecretKeySpec의 두 번째 파라미터로 전달하는 역할을 합니다
    }

    public  String createToken(Long id){

        long now=System.currentTimeMillis();

        return Jwts.builder()
               .claim("id",id)//id-> JWT의 페이로드에 커스텀 클레임을 추가하는 역할을 합니다.
//        "id"는 클레임의 이름이며, id는 변수로 넘겨받은 사용자 또는 객체의 ID 값입니다-> 여기서 중간에 가로 쳐서 id 값을 넣는거 구나
//        예를 들어, 사용자의 고유 식별자인 id 값을 JWT에 추가하여 토큰을 발급받은 사용자를 식별할 수 있습니다.
//        클레임은 JWT 토큰의 핵심 데이터로서, 일반적으로 사용자 정보, 권한 등을 포함할 수 있습니다.
                .issuedAt(new Date(now))//발급시간
                .expiration(new Date(now+expiration))//만료 시간
                .signWith(secretKey)//비밀키로 사인?
                .compact();

    }
    //토큰 유효성 검사
    public Boolean isValidToken(String token){
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
        } catch (SignatureException e) {
            return  false;
        }
        return true;
    }
    //만료 확인
    public Boolean isExpired(String token){
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                    .getPayload().getExpiration();
            //토큰 불러오고 페이로드를 불러오고 페이로드 안에서 만료시간 확인
            //getExpiration(); 이때 만료 되면 ExpiredJwtException 예외를 발생 시킨다
        }catch (ExpiredJwtException e){
            return true;
        }
        return false;
    }
    //토큰에서 id 얻기
    public Long getId(String token){
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token)
                .getPayload().get("id",Long.class);
        //id라는 key값을 가지고 ,반환 타입 클래스로 바꾼다
    }

    //더 상세한 예외처리가 필요할때 사용
    //코드 중복을 줄이고 싶으면 verify를 사용하면 된다
    public boolean verify(String token){
        try{
            Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
            //토큰을 얻고 이거 뭐지 ??
        }catch (SecurityException | MalformedJwtException e) {
            throw new CustomException(ErrorCode.JWT_ERROR_TOKEN);//서명 또는 구조 문제
        } catch (ExpiredJwtException e) {
            throw new CustomException(ErrorCode.JWT_EXPIRE_TOKEN);//토큰 만료
        } catch (UnsupportedJwtException e) {
            throw new CustomException(ErrorCode.JWT_ERROR_TOKEN);// 지원되지않는 JWT형식
        } catch (IllegalArgumentException e) {
            throw new CustomException(ErrorCode.JWT_ERROR_TOKEN);//잘못된 인수 또는 null
        } catch (Exception e) {
            throw new CustomException(ErrorCode.JWT_ERROR_TOKEN);//기타 예외 처리
        }

        return true;
    }



}
