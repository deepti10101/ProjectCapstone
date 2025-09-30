package com.scaler.productservice.common;

import com.scaler.productservice.dto.UserDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;



@Component
public class AuthenticationCommons {
        private RestTemplate restTemplate ;

        public AuthenticationCommons(RestTemplate restTemplate){
            this.restTemplate = restTemplate;
        }

        public UserDto validateToken(String token){
                if (token == null) {
                        return null;
                }
                HttpHeaders headers = new HttpHeaders();
                headers.set("Authorization",  token);
                HttpEntity<String> entity = new HttpEntity<String>(headers);

               ResponseEntity<UserDto> response= restTemplate.exchange(  "http://localhost:9000/users/validate",
                        HttpMethod.POST,
                        entity,
                        UserDto.class);

               return response.getBody();

        }

}
