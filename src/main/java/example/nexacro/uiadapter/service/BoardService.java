package example.nexacro.uiadapter.service;

import java.util.List;
import java.util.Map;

import com.nexacro.uiadapter.jakarta.dao.mybatis.MybatisRowHandler;

import example.nexacro.uiadapter.pojo.Board;

/**
 * <pre>
 * @title   
 * @desc    아래의 예제는 샘플용으로 작성된 코드로 참고용으로만
 *          사용하시기 바랍니다.
 * -        
 * @package example.nexacro.uiadapter.service
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
public interface BoardService {

    List<Board>           select_datalist(Board board);
    List<Map<String,Object>> select_datalist_map(Map<String,String> search);
    Map<String,Object> select_data_single(Map<String,String> search);
    void update_datalist(List<Board> dataList);
    void update_datalist_map(List<Map<String,Object>> dataList);
    void select_datalist_firstrow(Map<String,String> search, MybatisRowHandler rowHandler);
}