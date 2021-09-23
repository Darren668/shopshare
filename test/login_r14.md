## 1. Login or update password or register, system would create the token according to the user_id and return the homepage to users. 
## Codes are as followed:
```Java
public static String createTokenByJwt(String id, String subject) {
        //define the algorithm for generating the signature
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS256;
        //define the key to generate the signature
        SecretKey key = createSecretKey();

        Date now = DateTime.now().toDate();
        //use api of JWT to build the token
        JwtBuilder builder = Jwts.builder()
                //id
                .setId(id)
                //body
                .setSubject(subject)
                //issuer
                .setIssuer(ConstantValue.TOKEN_ISSUER)
                //time
                .setIssuedAt(now)
                //algorithms  and secret key
                .signWith(algorithm, key);
        return builder.compact();
    }
```
the homepage would show like:
![image](https://user-images.githubusercontent.com/50439378/134591875-7ee70d06-8134-446d-995c-a2548e7b1212.png)

## 2. return token to front-end browser and store the token in Redis.
```Java
//set the accessToken into cookie
            String token = tokenService.loginAndCreateToken(user);
            Cookie cookie = new Cookie("accessToken", token);
            cookie.setMaxAge(ConstantValue.COOKIE_EXPIRE_TIME);
            //take the cookie 
            cookie.setPath(request.getContextPath());
            response.addCookie(cookie);
```

#### open the client and connect with redis. Then type in the keys * to check if there is a token starting with UserAuth:JWT:Key
