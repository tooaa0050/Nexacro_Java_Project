package example.nexacro.uiadapter.pojo;


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
public class Dept extends NexacroBase {
	
	/** 검색조건 */
    private String searchCondition = "";

    /** 검색Keyword */
    private String searchKeyword = "";

    /** 검색사용여부 */
    private String searchUseYn = "";
    
    // Fields
    private int  	deptId;
    private String  deptName;
    private int  memberCount;
    
    
    /**
     * @return the searchCondition
     */
    public String getSearchCondition() {
        return searchCondition;
    }

    /**
     * @param searchCondition
     *            the searchCondition to set
     */
    public void setSearchCondition(String searchCondition) {
        this.searchCondition = searchCondition;
    }

    /**
     * @return the searchKeyword
     */
    public String getSearchKeyword() {
        return searchKeyword;
    }

    /**
     * @param searchKeyword
     *            the searchKeyword to set
     */
    public void setSearchKeyword(String searchKeyword) {
        this.searchKeyword = searchKeyword;
    }

    /**
     * @return the searchUseYn
     */
    public String getSearchUseYn() {
        return searchUseYn;
    }

    /**
     * @param searchUseYn
     *            the searchUseYn to set
     */
    public void setSearchUseYn(String searchUseYn) {
        this.searchUseYn = searchUseYn;
    }

    /**
     * @return the deptId
     */
    public int getDeptId() {
        return deptId;
    }

    /**
     * @param deptId
     *            the tdeptIditle to set
     */
    public void setDeptId(int deptId) {
        this.deptId = deptId;
    }

    /**
     * @return the regId
     */
    public String getDeptName() {
        return deptName;
    }

    /**
     * @param deptName
     *            the deptName to set
     */
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    /**
     * @return the memberCount
     */
    public int getMemberCount() {
        return memberCount;
    }

    /**
     * @param memberCount
     *            the memberCount to set
     */
    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

}
