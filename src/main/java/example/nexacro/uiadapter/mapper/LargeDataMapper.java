package example.nexacro.uiadapter.mapper;

import com.nexacro.uiadapter.jakarta.dao.mybatis.MybatisRowHandler;

/**
 * 대량 데이터 조회를 위한 Mapper 인터페이스
 */
public interface LargeDataMapper {
    /**
     * 대량 데이터를 ResultHandler 방식으로 조회
     * 
     * @param resultHandler MyBatis ResultHandler (MybatisRowHandler)
     */
    void selectLargeData(MybatisRowHandler resultHandler);
}
