package example.nexacro.uiadapter.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nexacro.uiadapter.jakarta.core.data.DataSetRowTypeAccessor;
import com.nexacro.uiadapter.jakarta.dao.mybatis.MybatisRowHandler;
import com.nexacro.java.xapi.data.DataSet;

import example.nexacro.uiadapter.mapper.BoardMapper;
import example.nexacro.uiadapter.pojo.Board;
import example.nexacro.uiadapter.service.BoardService;

/**
 * Test를 위한 ServiceImpl Sample Class
 *
 * @author Park SeongMin
 * @since 08.12.2015
 * @version 1.0
 * @see
 */
@Repository
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public List<Board> select_datalist(Board searchVO) {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.select_datalist(searchVO);
    }
    
    @Override
	public List<Map<String,Object>> select_datalist_map(Map<String,String> search) {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.select_datalist_map(search);
	}
    
    /**
     * 
     * <PRE>
     * 설명 : 0건 조회시 리턴 Map 형식 테스트
     * <PRE>
     * @see example.nexacro.uiadapter.service.BoardService#select_data_single(java.util.Map)
     */
    @Override
    public Map<String,Object> select_data_single(Map<String,String> search) {
        BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
        return mapper.select_data_single(search);
    }
    
    @Override
    public void update_datalist(List<Board> dataList) {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
    	
        int size = dataList.size();
        for (int i=0; i<size; i++) {
            Board board = dataList.get(i);
            if (board instanceof DataSetRowTypeAccessor){
                DataSetRowTypeAccessor accessor = (DataSetRowTypeAccessor) board;
                
                if (accessor.getRowType() == DataSet.ROW_TYPE_INSERTED){
                	mapper.insert_board(board);
                }else if (accessor.getRowType() == DataSet.ROW_TYPE_UPDATED){
                	mapper.update_board(board);
                }else if (accessor.getRowType() == DataSet.ROW_TYPE_DELETED){
                	mapper.delete_board(board);
                }
            }
        }
    }
    
    @Override
    public void update_datalist_map(List<Map<String,Object>> dataList) {
    	BoardMapper mapper = sqlSession.getMapper(BoardMapper.class);
    	
        int size = dataList.size();
        for (int i=0; i<size; i++) {
            Map<String,Object> board = dataList.get(i);
            
            int rowType = Integer.parseInt(String.valueOf(board.get(DataSetRowTypeAccessor.NAME)));
            if (rowType == DataSet.ROW_TYPE_INSERTED){
            	mapper.insert_board_map(board);
            }else if (rowType == DataSet.ROW_TYPE_UPDATED){
            	mapper.update_board_map(board);
            }else if (rowType == DataSet.ROW_TYPE_DELETED){
            	mapper.delete_board_map(board);
            }
        }
    }

    /**
     * Todo method for sending chuncked Data using MybatisRowHandler function
     */
    @Override
    public void select_datalist_firstrow(Map<String, String> search, MybatisRowHandler rowHandler) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'select_datalist_firstrow'");
    }
}
