package example.nexacro.uiadapter.impl;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.nexacro.uiadapter.jakarta.core.data.DataSetRowTypeAccessor;
import com.nexacro.java.xapi.data.DataSet;

import example.nexacro.uiadapter.mapper.ExampleDataTypeMapper;
import example.nexacro.uiadapter.pojo.ExampleDataType;
import example.nexacro.uiadapter.service.ExampleDataTypeServcice;

/**
 * Test를 위한 ServiceImpl Sample Class
 *
 * @author TOBESOFT
 * @since 2025. 08. 05.
 * @version 1.0
 * @see
 */
@Repository
public class ExampleDataTypeServiceImpl implements ExampleDataTypeServcice {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<ExampleDataType> select_datalist(Map<String,String> search) {
     	ExampleDataTypeMapper mapper = sqlSession.getMapper(ExampleDataTypeMapper.class);
        return mapper.select_example_data_type_list(search);
    }

    @Override
    public List<Map<String, Object>> select_datalist_map(Map<String, String> search) {
        ExampleDataTypeMapper mapper = sqlSession.getMapper(ExampleDataTypeMapper.class);
        return mapper.select_example_data_type_map(search);
    }

    @Override
    public void update_datalist(List<ExampleDataType> dataList) {
    	ExampleDataTypeMapper mapper = sqlSession.getMapper(ExampleDataTypeMapper.class);
    	
        int size = dataList.size();
        for (int i=0; i<size; i++) {
            ExampleDataType exampleDataType = dataList.get(i);
            if (exampleDataType instanceof DataSetRowTypeAccessor){
                DataSetRowTypeAccessor accessor = (DataSetRowTypeAccessor) exampleDataType;

                if (accessor.getRowType() == DataSet.ROW_TYPE_INSERTED){
                	mapper.insert_example_data_type(exampleDataType);
                }else if (accessor.getRowType() == DataSet.ROW_TYPE_UPDATED){
                	mapper.update_example_data_type(exampleDataType);
                }else if (accessor.getRowType() == DataSet.ROW_TYPE_DELETED){
                	mapper.delete_example_data_type(exampleDataType);
                }
            }
        }
    }

    @Override
    public void update_datalist_map(List<Map<String, Object>> dataList) {
    	ExampleDataTypeMapper mapper = sqlSession.getMapper(ExampleDataTypeMapper.class);
    	
        int size = dataList.size();
        for (int i=0; i<size; i++) {
            Map<String,Object> exampleData = dataList.get(i);

            int rowType = Integer.parseInt(String.valueOf(exampleData.get(DataSetRowTypeAccessor.NAME)));
            if (rowType == DataSet.ROW_TYPE_INSERTED){
            	mapper.insert_example_data_type_map(exampleData);
            }else if (rowType == DataSet.ROW_TYPE_UPDATED){
            	mapper.update_example_data_type_map(exampleData);
            }else if (rowType == DataSet.ROW_TYPE_DELETED){
            	mapper.delete_example_data_type_map(exampleData);
            }
        }
    }

}
