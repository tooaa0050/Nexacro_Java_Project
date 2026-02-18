package example.nexacro.uiadapter.pojo;

import com.nexacro.uiadapter.jakarta.core.annotation.ExcludeField;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * <pre>
 * @title   
 * @desc    아래의 예제는 샘플용으로 작성된 코드로 참고용으로만
 *          사용하시기 바랍니다.
 * -        
 * @package example.nexacro.uiadapter.pojo
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
@Getter
@Setter
public class Board extends NexacroBase {
	
	/** 검색조건 */
    @Getter(AccessLevel.PRIVATE)
    @Setter(AccessLevel.PRIVATE)
    private String searchCondition = "";

    /** 검색Keyword */
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    protected String searchKeyword = "";

    /** 검색사용여부 */
    @Getter(AccessLevel.PROTECTED)
    @Setter(AccessLevel.PROTECTED)
    protected String searchUseYn = "";
    
    // Fields
    public String  title;
    public String  regId;
    public String  postId;
    public String  contents;
    public String  communityId;
    public String  hitCount;
    public Date    regDate;
    public boolean isNotice;
    
    @ExcludeField
    public String hiddenInfo;
}
