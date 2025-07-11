package org.example.myblog.util;

import org.example.myblog.domain.RefreshToken;
import org.example.myblog.repository.RefreshTokenRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@RequiredArgsConstructor
@Component
public class TokenFactory {

    final RefreshTokenRepository refreshTokenRepository;

    private int refreshTokenTerm = 14 * 24 * 60;
    private int accessTokenTerm = 60;

    // RefreshToken 발급
    public String generateRefreshToken(Long id){
        return generate(id, "refreshToken");
    }

    // RefreshToken 유효성 확인
    public Long validateRefreshToken(String key){
        RefreshToken refreshToken = refreshTokenRepository.findByToken(key);
        Long userId = validate(key);

        if(userId != null && userId > 0){
            if(userId.equals(refreshToken.getUserId())){
                return userId;
            }
        }

        return null;
    }

    // AccessToken 발급
    public String generateAccessToken(String key){
        Long userId = validateRefreshToken(key);

        if(userId != null && userId > 0){
            return generate(userId, "accessToken");
        }

        return null;
    }

    // AccessToken 유효성 확인
    public Long validateAccessToken(String key){
        return validate(key);
    }

    // Token 생성
    public String generate(Long id, String type){
        String returnData = null;
        String dueDate = null;

        if("refreshToken".equals(type)){
            dueDate = new NowDate().due(refreshTokenTerm);
        }else{
            dueDate = new NowDate().due(accessTokenTerm);
        }

        String data = id + "_" + dueDate;
        try{
            returnData = AES256Cipher.AES_Encode(null, data);
        }catch (Exception e){}

        if("refreshToken".equals(type)){
            if(returnData != null){
                // 중복로그인 제한
                List<RefreshToken> list = refreshTokenRepository.findByUserId(id);
                for(RefreshToken refreshToken : list){
                    refreshTokenRepository.delete(refreshToken);
                }

                refreshTokenRepository.save(RefreshToken.of(id, dueDate, returnData));
            }
        }

        return returnData;
    }

    // Token 유효성 확인
    public Long validate(String key){
        Long data = null;

        try{
            String returnData = null;
            returnData = AES256Cipher.AES_Decode(null, key);
            String[] arrayData = returnData.split("_");

            if(arrayData.length == 2){
                String nowDate = new NowDate().getNow();
                String dueDate = arrayData[1];

                String[] tempArray = {nowDate, dueDate};
                Arrays.sort(tempArray);
                if(nowDate.equals(tempArray[0])){
                    // 유효한 경우
                    data = Long.parseLong(arrayData[0]);
                }else{
                    // 만료한 경우
                    data = (long) -100;
                }

            }
        }catch(Exception e){}

        return data;
    }

}
