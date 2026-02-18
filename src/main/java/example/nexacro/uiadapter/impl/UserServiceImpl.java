package example.nexacro.uiadapter.impl;

import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexacro.uiadapter.jakarta.core.data.DataSetRowTypeAccessor;
import com.nexacro.java.xapi.data.DataSet;

import example.nexacro.uiadapter.mapper.UserMapper;
import example.nexacro.uiadapter.pojo.User;
import example.nexacro.uiadapter.service.UserService;

/**
 * <pre>
 * @title   
 * @desc    아래의 예제는 샘플용으로 작성된 코드로 참고용으로만
 *          사용하시기 바랍니다.
 * -        ServiceImpl Sample Class
 * @package com.nexacro.sample.uiadapter.spring.impl
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
@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
    private SqlSessionTemplate sqlSession;

	/**
	 * VO 데이터 조회
	 */
    @Override
    public User selectUserInfoVO(User searchVO) {
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.select_user_info(searchVO);
    }

    /**
     * MAP 데이터 조회
     */
    @Override
    public Map<String, Object> selectUserInfoMap(Map<String, String> searchMap) {
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
        return mapper.select_user_info_map(searchMap);
    }
    
    /**
     * 데이처 입력,수정,삭제 처리.
     */
    @Override
    public void updateUserInfo(User userInfo) {
    	UserMapper mapper = sqlSession.getMapper(UserMapper.class);
    	
        if (userInfo instanceof DataSetRowTypeAccessor){
            DataSetRowTypeAccessor accessor = (DataSetRowTypeAccessor) userInfo;
            
            if (accessor.getRowType() == DataSet.ROW_TYPE_INSERTED){
            	mapper.insert_user(userInfo);
            }else if (accessor.getRowType() == DataSet.ROW_TYPE_UPDATED){
            	mapper.update_user(userInfo);
            }else if (accessor.getRowType() == DataSet.ROW_TYPE_DELETED){
            	mapper.delete_user(userInfo);
            }
        }
    }
}
