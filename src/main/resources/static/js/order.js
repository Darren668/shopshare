
function sendComment() {

    let order_id = $('#order_id').val();
    //alert(order_id);
    let comment_content = $('#comment_content').val();
    //alert if the content is blank
    if(!comment_content){
        alert("content should not be blank");
        return;
    }
    let commenter_id = $('#commenter_id').val();
    //alert(comment_content);
    $.ajax({
        type: "POST",
        url: "/send/comment",
        contentType:"application/json",
        //前面获取的对象直接写在这里不能解析，借助JSON.stringify来转化成JSON数据
        data: JSON.stringify({
            "parentId":order_id,
            "type": 1,
            "commenterId" : commenter_id,
            "content" : comment_content
        }),
        success: function (data) {
            if(data.code == 200){
                // alert(data.msg);
                //$('#comment_content').hide();
                //refresh the window
                window.location.reload();
            }else{
                //no user information, redirect here(server would not work with ajax)
                if(data.code == 4202){
                    //make use of confirm to send the cancel or confirm button for user
                    var isAccepted = confirm(data.msg);
                    if(isAccepted){
                        var loginPageUrl = $('#rootUrl').val()+"/loginPage";
                        window.close();
                        window.open(loginPageUrl);
                        //window.localStorage.setItem("toClose","true");
                    }
                }else{
                    //error, show the alert message
                    alert(data.msg);
                }

            }
        },
        dataType: "json"
    });

}