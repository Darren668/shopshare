### 1. click the logout button on the right corner
![image](https://user-images.githubusercontent.com/50439378/134592556-75738845-f688-4c89-8618-23d94190dce5.png)

### 2. homepage appears without username information
![image](https://user-images.githubusercontent.com/50439378/134592582-e12452c2-419c-429f-a2e6-78d690a67eba.png)


### 3. remove the token by system, you could check by typing in keys * with Redis. The token UserAuth:JWT:Key *** created before is gone.
```Java
@RequestMapping(value = "logout")
    public String logout(HttpServletRequest request, HttpServletResponse response){
        HttpSession session = request.getSession();
        //clear the information in session
        User user = (User)session.getAttribute("user");
        session.removeAttribute("user");
        //clear and reset the cookie of token
        Cookie emptyCookie = new Cookie("accessToken", "");
        emptyCookie.setMaxAge(0);
        emptyCookie.setPath(request.getContextPath());
        response.addCookie(emptyCookie);
        //clear the token in redis
        if(user != null) {
            tokenService.removeTokenByKey(ConstantValue.JWT_TOKEN_REDIS_KEY_PREFIX + user.getId());
        }
        return "redirect:/";
    }
```

```Java
public void removeTokenByKey(String key) {
        redisTemplate.delete(key);
    }
```
