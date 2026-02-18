package example.nexacro.uiadapter.mapper;

import java.util.Map;

import example.nexacro.uiadapter.pojo.User;

/**
 * <pre>
 * @title   
 * @desc    아래의 예제는 샘플용으로 작성된 코드로 참고용으로만
 *          사용하시기 바랍니다.
 * -        
 * @package com.nexacro.sample.uiadapter.spring.mapper
 * <pre>
 * 
 * @author  TOBESOFT
 * @since   2017. 11. 20.
 * @version 1.0
 * @see
 * =================== 변경 내역 ==================
 * 날짜			변경자		내용
 * ------------------------------------------------
 * 2017. 11. 20.		TOBESOFT	최초작성
 */
public interface UserMapper {
    public User select_user_info(User user);
    public Map<String,Object> select_user_info_map(Map<String,String> user);
    public void insert_user(User user);
    public void update_user(User user);
    public void delete_user(User user);
}