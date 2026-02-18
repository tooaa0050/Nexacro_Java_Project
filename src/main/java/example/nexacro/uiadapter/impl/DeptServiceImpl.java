package example.nexacro.uiadapter.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nexacro.uiadapter.jakarta.core.data.DataSetRowTypeAccessor;
import com.nexacro.java.xapi.data.DataSet;

import example.nexacro.uiadapter.mapper.DeptMapper;
import example.nexacro.uiadapter.pojo.Dept;
import example.nexacro.uiadapter.service.DeptService;

/**
 * Test를 위한 ServiceImpl Sample Class
 *
 * @author Park SeongMin
 * @since 08.12.2015
 * @version 1.0
 * @see
 */
@Repository
public class DeptServiceImpl implements DeptService {

    @Autowired
    private SqlSessionTemplate sqlSession;
    
    @Override
    public List<Dept> select_datalist(Dept searchVO) {
    	DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        return mapper.select_datalist(searchVO);
    }
    
    @Override
	public List<Map<String,Object>> select_datalist_map(Map<String,String> search) {
    	DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
        return mapper.select_datalist_map(search);
	}
    
    @Override
    public void update_datalist(List<Dept> dataList) {
    	DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
    	
        int size = dataList.size();
        for (int i=0; i<size; i++) {
            Dept Dept = dataList.get(i);
            if (Dept instanceof DataSetRowTypeAccessor){
                DataSetRowTypeAccessor accessor = (DataSetRowTypeAccessor) Dept;
                
                if (accessor.getRowType() == DataSet.ROW_TYPE_INSERTED){
                	mapper.insert_Dept(Dept);
                }else if (accessor.getRowType() == DataSet.ROW_TYPE_UPDATED){
                	mapper.update_Dept(Dept);
                }else if (accessor.getRowType() == DataSet.ROW_TYPE_DELETED){
                	mapper.delete_Dept(Dept);
                }
            }
        }
    }
    
    @Override
    public void update_datalist_map(List<Map<String,Object>> dataList) {
    	DeptMapper mapper = sqlSession.getMapper(DeptMapper.class);
    	
        int size = dataList.size();
        for (int i=0; i<size; i++) {
            Map<String,Object> Dept = dataList.get(i);
            
            int rowType = Integer.parseInt(String.valueOf(Dept.get(DataSetRowTypeAccessor.NAME)));
            if (rowType == DataSet.ROW_TYPE_INSERTED){
            	mapper.insert_Dept_map(Dept);
            }else if (rowType == DataSet.ROW_TYPE_UPDATED){
            	mapper.update_Dept_map(Dept);
            }else if (rowType == DataSet.ROW_TYPE_DELETED){
            	mapper.delete_Dept_map(Dept);
            }
        }
    }
}
