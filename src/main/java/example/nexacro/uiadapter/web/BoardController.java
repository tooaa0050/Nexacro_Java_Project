package example.nexacro.uiadapter.web;

import java.util.List;
import java.util.Map;

import com.nexacro.uiadapter.jakarta.core.annotation.CheckFree;
import com.nexacro.uiadapter.jakarta.core.annotation.LoggingNexacroRequest;
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
import com.nexacro.uiadapter.jakarta.core.context.NexacroContext;
import com.nexacro.uiadapter.jakarta.core.context.NexacroContextHolder;
import com.nexacro.uiadapter.jakarta.dao.mybatis.MybatisRowHandler;

import example.nexacro.uiadapter.pojo.Board;
import example.nexacro.uiadapter.service.BoardService;

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
public class BoardController {

	private Logger log = LoggerFactory.getLogger(BoardController.class);
	
	@Autowired(required=true)
    private BoardService boardService;
    
	
	@RequestMapping(value = "/select_datalist_firstrow.do")
	public void select_datalist_firstrow(@ParamDataSet(name="dsSearch", required = false) Map<String,String> searchMap, NexacroFirstRowHandler firstRowHandler) throws NexacroException{

    	log.debug("... >" + searchMap);
		NexacroContext nexacroContext = NexacroContextHolder.getNexacroContext();
        NexacroFirstRowHandler contextRowHandler = nexacroContext.getFirstRowHandler();
        if(contextRowHandler == null) {
        	contextRowHandler = firstRowHandler;
        } 
        MybatisRowHandler rowHandler = new MybatisRowHandler(contextRowHandler, "output1", 1000);
        boardService.select_datalist_firstrow(searchMap, rowHandler);
	}
	
	/**
	 * 
	 * <pre>
	 * @desc 리스트 데이터 조회 - VO
	 * @param  
	 * @return NexacroResult
	 * @throws 
	 * </pre>
	 */
    @CheckFree
    @LoggingNexacroRequest
    @RequestMapping(value = "/select_datalist.do")
	public NexacroResult select_datalist(@ParamDataSet(name = "dsSearch", required = false) Board searchVo) throws NexacroException{

		List<Board> sampleList = boardService.select_datalist(searchVo);
		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", sampleList);

		return result;
	}
    
   
    /**
	 * 리스트 데이터 조회 - MAP
	 * @param searchMap
	 * @return
	 */
    @RequestMapping(value = "/select_datalist_map.do")
	public NexacroResult select_datalist_map(@ParamDataSet(name="dsSearch", required = false) Map<String,String> searchMap, HttpServletRequest request) throws NexacroException{

    	log.debug("... >" + searchMap);
		List<Map<String,Object>> sampleList = boardService.select_datalist_map(searchMap);
		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", sampleList);
		
		return result;
	}
    
    /**
     * 단건 데이터 조회 - MAP
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/select_data_single.do")
    public NexacroResult select_data_single(@ParamDataSet(name="dsSearch", required = false) Map<String,String> searchMap, HttpServletRequest request) throws NexacroException{

        log.debug("... >" + searchMap);
        Map<String,Object> sampleList = boardService.select_data_single(searchMap);
        NexacroResult result = new NexacroResult();
        result.addDataSet("output1", sampleList);
        
        return result;
    }
  
    /**
     * 리스트 데이터 입력,수정,삭제
     * @param dataList
     * @return
     */
    @RequestMapping(value = "/update_datalist.do")
	public NexacroResult update_datalist(@ParamDataSet(name = "input1") List<Board> dataList) throws NexacroException{
    	
    	boardService.update_datalist(dataList);
        
        NexacroResult result = new NexacroResult();
        
        return result;
    }
    
    /**
     * 리스트 데이터 입력,수정,삭제
     * @param dataList
     * @return
     */
    @RequestMapping(value = "/update_datalist_map.do")
	public NexacroResult update_datalist_map(@ParamDataSet(name = "input1") List<Map<String,Object>> dataList) throws NexacroException{
    	
    	boardService.update_datalist_map(dataList);
        
        NexacroResult result = new NexacroResult();
        
        return result;
    }
    
    /**
     * uiadapter 처리 parameter 종류
     * @param unitList
     * @param unitMapList
     * @param dsUnit
     * @param intValue
     * @param stringValue
     * @param intVariable
     * @param StringValue
     * @param dataSetList
     * @param variableList
     * @param httpPlatformRequest
     * @param httpPlatformResponse
     * @param firstRowHandler
     * @return
     */
    @RequestMapping(value = "/test.do")
    public NexacroResult test( @ParamDataSet(name="dsUnit") List<Board> unitList
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
