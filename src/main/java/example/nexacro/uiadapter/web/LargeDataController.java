package example.nexacro.uiadapter.web;

import com.nexacro.java.xapi.tx.PlatformType;
import com.nexacro.uiadapter.jakarta.core.annotation.ParamVariable;
import com.nexacro.uiadapter.jakarta.core.data.NexacroFirstRowHandler;
import example.nexacro.uiadapter.service.LargeDataService;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LargeDataController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource(name = "largeDataService")
    private LargeDataService largeDataService;

    /**
     * 대량 데이터를 chunked 방식으로 응답하는 메소드
     * @param firstRowHandler Nexacro FirstRow Handler
     * @param firstRowCount 초기 전송할 row 수 (기본값: 1000)
     */
    @RequestMapping(value = "/sampleLargeData.do")
    public void mybatisLargeData(
            NexacroFirstRowHandler firstRowHandler, 
            @ParamVariable(name="firstRowCount", required=false) Integer firstRowCount,
            @ParamVariable(name="chunkSize", required=false) Integer chunkSize) {
        
        // SSV 포맷으로 설정
        firstRowHandler.setContentType(PlatformType.CONTENT_TYPE_SSV);
        
        String sendDataSetName = "ds_largeData";
        int defaultFirstRowCount = (firstRowCount != null && firstRowCount > 0) ? firstRowCount : 1000;
        int defaultChunkSize = (chunkSize != null && chunkSize > 0) ? chunkSize : 1000;
        
        logger.info("========================================");
        logger.info("대량 데이터 조회 시작");
        logger.info("데이터셋   : {}", sendDataSetName);
        logger.info("초기 Row 수: {}", defaultFirstRowCount);
        logger.info("청크 크기: {}", defaultChunkSize);
        logger.info("========================================");
        
        long startTime = System.currentTimeMillis();
        
        try {
            largeDataService.selectLargeData( firstRowHandler, sendDataSetName, defaultFirstRowCount, defaultChunkSize );
            
            long endTime = System.currentTimeMillis();
            logger.info("대량 데이터 조회 완료 - 소요시간: {}ms", (endTime - startTime));
            
        } catch (Exception e) {
            logger.error("대량 데이터 조회 중 오류 발생", e);
            throw e;
        }
    }
}
