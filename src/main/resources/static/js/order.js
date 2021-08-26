function sendComment() {
    let order_id = $('#order_id').val();
    //alert(order_id);
    let comment_content = $('#comment_content').val();
    //alert(comment_content);
    $.ajax({
        type: "POST",
        url: "/send/comment",
        contentType:"application/json",
        //前面获取的对象直接写在这里不能解析，借助JSON.stringify来转化成JSON数据
        data: JSON.stringify({
            "parentId":order_id,
            "type": 1,
            "commenterId" : 1,
            "content" : comment_content
        }),
        success: function (data) {
            if(data.code != 200){
                alert(data.message);
            }
            // console.log(data);
        },
        dataType: "json"
    });

}