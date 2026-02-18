package example.nexacro.uiadapter.mapper;

import java.util.List;
import java.util.Map;

import example.nexacro.uiadapter.pojo.Dept;

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
public interface DeptMapper {

	public List<Dept>           select_datalist(Dept Dept);
    public List<Map<String,Object>> select_datalist_map(Map<String,String> Dept);
    public void insert_Dept(Dept Dept);
    public void update_Dept(Dept Dept);
    public void delete_Dept(Dept Dept);
    
    public void insert_Dept_map(Map<String,Object> Dept);
    public void update_Dept_map(Map<String,Object> Dept);
    public void delete_Dept_map(Map<String,Object> Dept);
}
