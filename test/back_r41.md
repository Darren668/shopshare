### 1. get access to the administration page by type in the private url http://www.shopshare.top/admin
The codes of query process are as followed:
```Java
@RequestMapping(value = "admin")
public String adminUser(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,  Model model){
        //store the information in map
        HashMap<String, String> map = new HashMap<>();
        map.put("tableName","User Table");
        map.put("key1","username");
        map.put("key2","email");
        model.addAttribute("map",map);
        PageHelper.startPage(pageNum,5);
        List<User> userList = userService.list();
        PageInfo<User> pageInfo = new PageInfo<>(userList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin";
    }
```

```
 @RequestMapping(value = "admin/order")
    public String adminOrder(@RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,  Model model){
        //store the information in map
        HashMap<String, String> map = new HashMap<>();
        map.put("tableName","Order Table");
        map.put("key1","title");
        map.put("key2","deadline");
        model.addAttribute("map",map);
        PageHelper.startPage(pageNum,5);
        List<SharedOrder> orderList = orderService.list(new QueryWrapper<SharedOrder>().orderByDesc("create_time"));
        PageInfo<SharedOrder> pageInfo = new PageInfo<>(orderList);
        model.addAttribute("pageInfo",pageInfo);
        return "admin";
    }
```

### 2. click and check all the users and orders, change to different pages to test the pagination function
![image](https://user-images.githubusercontent.com/50439378/134594761-41830edc-7830-4852-80cc-d593f282fa8e.png)
