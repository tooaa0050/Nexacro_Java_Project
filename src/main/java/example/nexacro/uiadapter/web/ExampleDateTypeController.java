package example.nexacro.uiadapter.web;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.util.List;
import java.util.Map;
import java.util.Random;

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

import example.nexacro.uiadapter.pojo.Board;
import example.nexacro.uiadapter.pojo.ExampleDataType;
import example.nexacro.uiadapter.service.ExampleDataTypeServcice;

@Controller
public class ExampleDateTypeController {

	private Logger log = LoggerFactory.getLogger(ExampleDateTypeController.class);
	@Autowired(required=true)
    private ExampleDataTypeServcice exampleDataTypeService;	
	/**
	 * <pre>
	 * @desc 데이터 타입 체크 : dataset - VO
     *      nexacro ui에서 request로 담은 dataset의 컬럼을 VO(ExampleDataType)로 변환하는 샘플입니다.
     *      받은 VO(ExampleDataType)를 다시 Dataset으로 response 합니다.
     *      column 타입별 맵핑은 {@code ExampleDataType} 객체를 참조하세요.
	 * @param  
	 * @return NexacroResult
	 * @throws 
     * @see example.nexacro.uiadapter.pojo.ExampleDataType
	 * </pre>
	 */
    @RequestMapping(value = "/check_testDataTypeList.do")
	public NexacroResult check_testDataTypeList(@ParamDataSet(name = "dsList", required = true) List<ExampleDataType> dataList) throws NexacroException{

        
		log.debug("... >" + dataList.size());
        // 컬럼 타입과 값 로그 출력
        for (ExampleDataType item : dataList) {
            log.debug("stringValue (String): {}", item.getStringValue());
            log.debug("intValue (Integer): {}", item.getIntValue());
            log.debug("booleanValue (Boolean): {}", item.getBooleanValue());
            log.debug("longValue (Long): {}", item.getLongValue());
            log.debug("floatValue (Float): {}", item.getFloatValue());
            log.debug("doubleValue (Double): {}", item.getDoubleValue());
            log.debug("bigDecimalValue (BigDecimal): {}", item.getBigDecimalValue());
            log.debug("dateValue (Date): {}", item.getDateValue());
            log.debug("timeValue (Time): {}", item.getTimeValue());
            log.debug("dateTimeValue (Date): {}", item.getDateTimeValue());
            
            item.setBytesValue(this.getImageBytes());
            log.debug("bytesValue (byte[]): {}", item.getBytesValue());
        }
 
		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", dataList);

		return result;
	}

    /**
	 * <pre>
	 * @desc 리스트 데이터 조회 - VO
     *      nexacro 의 요청에 따라 서비스를 실행하고 db에 저장된 데이터를 List<ExampleDataType>로 받습니다.
     *      받은 List<ExampleDataType>를 다시 Dataset으로 response 합니다.
     *      column 타입별 맵핑은 {@code ExampleDataType} 객체를 참조하세요.
	 * @param  
	 * @return NexacroResult
	 * @throws 
     * @see example.nexacro.uiadapter.pojo.ExampleDataType
	 * </pre>
	 */
    @RequestMapping(value = "/select_testDataTypeList.do")
	public NexacroResult select_testDataTypeList(@ParamDataSet(name = "dsSearch", required = false) Map<String,String> searchMap) throws NexacroException{

		List<ExampleDataType> sampleList = exampleDataTypeService.select_datalist(searchMap);
//        List<Map<String, Object>> sampleList = exampleDataTypeService.select_datalist_map(searchMap);

        // 이미지 데이터(byte[]) 로딩 샘플.
        if(sampleList.size() > 0) {
            sampleList.forEach(item -> {
                if (item != null) {
                    item.setBytesValue(getImageBytes());
                }
            });
        }
        NexacroResult result = new NexacroResult();
        result.addDataSet("output1", sampleList);

		return result;
	}
   
  
    /**
	 * 리스트 데이터 조회 - MAP
	 * @param searchMap
	 * @return
	 */
    @RequestMapping(value = "/select_testDataTypeList_map.do")
	public NexacroResult select_testDataTypeList_map(@ParamDataSet(name="dsSearch", required = false) Map<String,String> searchMap, HttpServletRequest request) throws NexacroException{

    	log.debug("... >" + searchMap);
		List<Map<String,Object>> sampleList = exampleDataTypeService.select_datalist_map(searchMap);
		NexacroResult result = new NexacroResult();
		result.addDataSet("output1", sampleList);
		
		return result;
	}
 
  
    /**
     * 리스트 데이터 입력,수정,삭제
     * @param dataList
     * @return
     */
    @RequestMapping(value = "/update_testDataTypeList.do")
	public NexacroResult update_testDataTypeList(@ParamDataSet(name = "input1") List<ExampleDataType> dataList) throws NexacroException{
    	
    	exampleDataTypeService.update_datalist(dataList);
        
        NexacroResult result = new NexacroResult();
        
        return result;
    }
    
    /**
     * 리스트 데이터 입력,수정,삭제
     * @param dataList
     * @return
     */
    @RequestMapping(value = "/update_testDataTypeList_map.do")
	public NexacroResult update_testDataTypeList_map(@ParamDataSet(name = "input1") List<Map<String,Object>> dataList) throws NexacroException{
    	
    	exampleDataTypeService.update_datalist_map(dataList);
        
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
    @RequestMapping(value = "/checkArgsAnotation.do")
    public NexacroResult checkArgsAnotation( @ParamDataSet(name="dsUnit") List<Board> unitList
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

    
    // 이미지폴더의 파일을 랜덤하게 리턴.
    // 이미지폴더 : /static/images/
    // 이미지 :     example_7k.jpg
    //              nexacro_5k.png
    
    public byte[] getImageBytes() {
        String imagePath = "/static/images/";
        String imageName[] = {"example_7k.jpg", "nexacro_5k.png"};
        Random random = new Random();
        InputStream inputStream;
        byte[] buffer = new byte[4096];
        int bytes_read;

        ByteArrayOutputStream outputBaStream = new ByteArrayOutputStream();
        try {
            String name = imageName[random.nextInt(imageName.length)];
            inputStream = getClass().getResourceAsStream(imagePath + name);
            while ((bytes_read = inputStream.read(buffer)) != -1) {
                outputBaStream.write(buffer, 0, bytes_read);
            }
        } catch (IOException e) {
            log.error("Error reading image file", e);
        }
        return outputBaStream.toByteArray();
    }

}
