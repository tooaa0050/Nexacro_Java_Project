package example.nexacro.uiadapter.impl;

import com.nexacro.uiadapter.jakarta.core.data.NexacroFirstRowHandler;
import com.nexacro.uiadapter.jakarta.dao.mybatis.MybatisRowHandler;
import example.nexacro.uiadapter.mapper.LargeDataMapper;
import example.nexacro.uiadapter.service.LargeDataService;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service("largeDataService")
@Repository
public class LargeDataServiceImpl implements LargeDataService {
    
    private Logger logger = LoggerFactory.getLogger(getClass());

    private static boolean isInited = false;
    private static int DATA_CNT = 100000;

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Autowired
    private LargeDataJdbc largeDataJdbc;

    @Override
    public void selectLargeData( NexacroFirstRowHandler firstRowHandler,  String sendDataSetName,  int firstRowCount,  int chunkSize) {

        if(!isInited) {
            largeDataJdbc.initData(DATA_CNT);
            isInited = true;
        }
        /**
         * MybatisRowHandler 생성
         * - firstRowHandler: Nexacro response handler
         * - sendDataSetName: 클라이언트로 전송할 데이터셋명
         * - chunkSize: 한번에 전송할 row 수 (메모리 효율을 위해 청크 단위로 전송)
         */
        MybatisRowHandler rowHandler = new MybatisRowHandler( firstRowHandler, sendDataSetName, chunkSize);
        
        logger.info("MybatisRowHandler 생성 완료 - DataSet: {}, ChunkSize: {}", sendDataSetName, chunkSize);
        
        // Mapper를 통해 데이터 조회
        // ResultHandler를 통해 row by row로 처리되며, 
        // chunkSize만큼 모이면 자동으로 response에 write됨
        LargeDataMapper mapper = sqlSession.getMapper(LargeDataMapper.class);
        mapper.selectLargeData(rowHandler);
        rowHandler.sendRemainData();
        logger.info("대량 데이터 조회 처리 완료");
    }
}
