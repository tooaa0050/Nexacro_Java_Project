package example.nexacro.uiadapter.pojo;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

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
@Data
@EqualsAndHashCode(callSuper = true)
public class User extends NexacroBase {

    @NotNull(message = "user id is required.")
    @Size(min = 4, max = 20, message = "Please, Enter your id at least 4 Characters.")
    private String userId;

    @NotNull(message = "user namme is required.")
    @Size(max = 20, message = "Please, Enter your name.")
    private String userName;

    @NotNull(message = "password is required.")
    @Size(min = 4, max = 50, message = "Please, Enter your password at least 4 Characters.")
    private String password;

    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "{errors.validation.email}") // message source
    private String email;

    private String enName;
    private String compPhone;
    private String phone;
    private String cellPhone;
    private String company;
    private String jobPosition;
    private String assignment;
    private String officerYn;
    private String fax;
    private String zipCode;
    private String address;
    private String compZipCode;
    private String compAddress;
    private String deptId;
    
}
