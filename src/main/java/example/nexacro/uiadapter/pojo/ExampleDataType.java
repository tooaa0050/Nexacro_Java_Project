package example.nexacro.uiadapter.pojo;

import java.math.BigDecimal;

import java.util.Date;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

/**
 * <pre>
 * @title   TestDataType VO
 * @desc    Nexacro에서 전달하는 데이터 타입을 Java VO 객체 변수 타입으로 변환하는 샘플 클래스입니다.<br>
 *   -      참조URL : https://docs.tobesoft.com/server_setup_guide_nexacro_n_v24_ko/840fb638487c9f67#a62c49ca90b62068
 *          <pre>
 *          Javascript  Nexacro UI    X-API Type            Java Type           Description
 *          ----------- -----------   -------------------- --------------------- ---------------------
 *          String      STRING        DataTypes.STRING      String               문자열
 *          Int         INT           DataTypes.INT         int                  4 byte 정수
 *          Int         INT           DataTypes.BOOLEAN     boolean              참 또는 거짓 (1 또는 0)
 *          BIGDECIMAL  BIGDECIMAL    DataTypes.LONG        long                 8 byte 정수
 *          BIGDECIMAL  FLOAT         DataTypes.FLOAT       float                4 byte 실수
 *          BIGDECIMAL  FLOAT         DataTypes.DOUBLE      double               8 byte 실수
 *          BIGDECIMAL  BIGDECIMAL    DataTypes.BIGDECIMAL  java.math.BigDecimal 임의 정밀도 숫자
 *          Date        DATE          DataTypes.DATE        java.util.Date       일자 (yyyy-MM-dd) "2025-08-04"
 *          Date        DATETIME      ㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡㅡ    java.util.Date       시간 (hh:mm:ss)
 *          Date        DATETIME      DataTypes.DATE_TIME   java.util.Date       일자와 시간 (yyyy-MM-dd hh:mm:ss)
 *          Array       BLOB          DataTypes.BLOB        byte[]               byte 배열
 *          </pre>
 *          Nexacro에서 지원하는 데이터 타입을 Java의 X-API Type의 기본 데이터 타입으로 매핑하는 example입니다.<br>
 *          본 파일은 샘플 코드로 참고용으로 사용하시기 바랍니다.
 * @author  TOBESOFT
 * @since   2025. 08. 04.
 * @version 1.0
 * </pre>
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class ExampleDataType extends NexacroBase {

    public String      stringValue;
    public Integer     intValue;
    public Boolean     booleanValue;
    public Long        longValue;
    public Float       floatValue;
    public Double      doubleValue;
    public BigDecimal  bigDecimalValue;

    public Date        dateValue;
    public Date        timeValue;
    public Date        dateTimeValue;

    public byte[]      bytesValue;
}