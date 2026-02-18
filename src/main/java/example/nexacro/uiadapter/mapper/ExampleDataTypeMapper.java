package example.nexacro.uiadapter.mapper;

import java.util.List;
import java.util.Map;
import example.nexacro.uiadapter.pojo.ExampleDataType;

/**
 * <pre>
 * @title   TestDataTypeMapper
 * @desc    TestDataType VO 객체의 DB 매핑을 위한 Mapper 인터페이스 예제입니다.
 * @author  TOBESOFT
 * @since   2025. 08. 04.
 * @version 1.0
 * </pre>
 */
public interface ExampleDataTypeMapper {
    public List<ExampleDataType>    select_example_data_type_list(Map<String, String> params);
    public List<Map<String, Object>> select_example_data_type_map(Map<String, String> params);
    public void insert_example_data_type(ExampleDataType exampleDataType);
    public void update_example_data_type(ExampleDataType exampleDataType);
    public void delete_example_data_type(ExampleDataType exampleDataType);

    public void insert_example_data_type_map(Map<String,Object> exampleData);
    public void update_example_data_type_map(Map<String,Object> exampleData);
    public void delete_example_data_type_map(Map<String,Object> exampleData);
}