package pers.xinhaojie.shopshare.enums;

/**
 * define the comment type code
 * @author xin haojie
 * @create 2021-08-25-22:05
 */
public enum CommentTypeEnum {
    //1 is order type
    ORDER(1),
    //2 us comment type
    COMMENT(2);

    private Integer type;
    CommentTypeEnum(Integer type){
        this.type = type;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public static boolean containsType(Integer type){
        for(CommentTypeEnum validType : CommentTypeEnum.values()){
            if(validType.getType().equals(type) ){
                return true;
            }
        }
        return false;
    }
}
