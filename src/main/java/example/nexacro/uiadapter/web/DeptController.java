package example.nexacro.uiadapter.web;

import java.util.List;
import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.nexacro.uiadapter.jakarta.core.NexacroException;
import com.nexacro.uiadapter.jakarta.core.annotation.ParamDataSet;
import com.nexacro.uiadapter.jakarta.core.annotation.ParamVariable;
import com.nexacro.uiadapter.jakarta.core.data.NexacroFirstRowHandler;
import com.nexacro.uiadapter.jakarta.core.data.NexacroResult;
import com.nexacro.java.xapi.data.DataSet;
import com.nexacro.java.xapi.data.DataSetList;
import com.nexacro.java.xapi.data.Variable;
import com.nexacro.java.xapi.data.VariableList;
import com.nexacro.java.xapi.tx.HttpPlatformRequest;
import com.nexacro.java.xapi.tx.HttpPlatformResponse;

import example.nexacro.uiadapter.pojo.Dept;
import example.nexacro.uiadapter.service.DeptService;

/**
 * <pre>
 * @title   
 * @desc    아래의 예제는 샘플용으로 작성된 코드로 참고용으로만
 *          사용하시기 바랍니다.
 * -        Controller Sample Class
 * @package com.nexacro.sample.uiadapter.spring.web
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
@Controller
public class DeptController {

	private Logger log = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired(required=true)
    private DeptService DeptService;
    
	/**
	 * 
	 * <pre>
	 * @desc 리스트 데이터 조회 - VO
	 * @param  
	 * @return NexacroResult
	 * @throws 
	 * </pre>
	 */
    @RequestMapping(value = "/select_deptlist.do")
	public NexacroResult select_datalist(@ParamDataSet(name = "dsSearch", required = false) Dept searchVo) throws NexacroException{

		List<Dept> sampleList = DeptService.select_datalist(searchVo);
		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", sampleList);

		return result;
	}
    
    
    /**
	 * 리스트 데이터 조회 - MAP
	 * @param searchVo
	 * @return
	 */
    @RequestMapping(value = "/select_deptlist_map.do")
	public NexacroResult select_datalist_map(@ParamDataSet(name="dsSearch", required = false) Map<String,String> searchMap, HttpServletRequest request) throws NexacroException{

    	log.debug("... >" + searchMap);
		List<Map<String,Object>> sampleList = DeptService.select_datalist_map(searchMap);
		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", sampleList);
		
		return result;
	}
    
    /**
     * 리스트 데이터 입력,수정,삭제
     * @param modifyList
     * @return
     */
    @RequestMapping(value = "/update_deptlist.do")
	public NexacroResult update_datalist(@ParamDataSet(name = "input1") List<Dept> dataList) throws NexacroException{
    	
    	DeptService.update_datalist(dataList);
        
        NexacroResult result = new NexacroResult();
        
        return result;
    }
    
    /**
     * 리스트 데이터 입력,수정,삭제
     * @param modifyList
     * @return
     */
    @RequestMapping(value = "/update_deptlist_map.do")
	public NexacroResult update_datalist_map(@ParamDataSet(name = "input1") List<Map<String,Object>> dataList) throws NexacroException{
    	
    	DeptService.update_datalist_map(dataList);
        
        NexacroResult result = new NexacroResult();
        
        return result;
    }
    
    /**
     * uiadapter 처리 parameter 종류
     * @param unitList
     * @param unitMapList
     * @param dsUnit
     * @param iVar1
     * @param iVar2
     * @param sVar1
     * @param sVar2
     * @param dsList
     * @param varList
     * @param platformRequest
     * @param platformResponse
     * @param firstRowHandler
     * @return
     */
    @RequestMapping(value = "/testUnit.do")
    public NexacroResult test( @ParamDataSet(name="dsUnit") List<Dept> unitList
            				  , @ParamDataSet(name="dsUnit") List<Map<String, Object>>      unitMapList
            				  , @ParamDataSet(name="dsUnit") DataSet        dsUnit
            
            				  , @ParamVariable(name="intValue")    int      intValue
            				  , @ParamVariable(name="stringValue") String   stringValue
            				  , @ParamVariable(name="intValue")    Variable intVariable
            				  , @ParamVariable(name="stringValue") Variable StringValue
            
            				  , DataSetList            dataSetList
            				  , VariableList           variableList
            				  , HttpPlatformRequest    httpPlatformRequest
            				  , HttpPlatformResponse   httpPlatformResponse
            				  , NexacroFirstRowHandler firstRowHandler ){
        
        if (log.isDebugEnabled()) {
            log.debug("debug_message : nexacro supported parameter types..");
        }
        
        // control nexacro result...
        NexacroResult result = new NexacroResult();
        result.addDataSet("dsUnitList"     , unitList);
        result.addVariable("responseInt"   , intVariable);
        result.addVariable("responseString", StringValue);
        return result;
    }
}
