package example.nexacro.uiadapter.service;

import com.nexacro.uiadapter.jakarta.core.data.NexacroFirstRowHandler;

public interface LargeDataService {
    /**
     * 대량 데이터를 chunked 방식으로 조회
     * 
     * @param firstRowHandler Nexacro FirstRow Handler
     * @param sendDataSetName 전송할 데이터셋명
     * @param firstRowCount 초기 전송할 row 수
     * @param chunkSize 청크 크기
     */
    void selectLargeData( NexacroFirstRowHandler firstRowHandler, String sendDataSetName, int firstRowCount, int chunkSize );
}
