package com.whale.admin.web.convert;

import com.whale.admin.web.repository.model.vo.UserBo;
import com.whale.admin.web.repository.model.vo.UserDetailVo;
import com.whale.admin.web.repository.model.vo.UserVo;

/**
 * 对象转换示例
 *
 * @author trendong
 */
public class ObjectConvertTest {
    
    public static void main(String[] args) {

        UserBo userBo = new UserBo();
        userBo.setId(1);
        userBo.setUsername("test");
        userBo.setAge(10);
        // 对象转换
        UserVo convert1 = UserConvert.INSTANCE.convert(userBo);
        System.out.println(convert1);
        // 对象转换->自定义属性映射
        UserDetailVo convert2 = UserConvert.INSTANCE.convertDetail(userBo);
        System.out.println(convert2);


    }
    
}
