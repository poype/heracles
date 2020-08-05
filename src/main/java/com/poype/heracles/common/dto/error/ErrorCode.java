package com.poype.heracles.common.dto.error;

import java.io.Serializable;

/**
 <p>错误码格式说明：</p>
 <table border="1">
 <tr>
 <td><b>位置</b></td>
 <td>1</td><td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td>7</td><td>8</td><td>9</td><td>10</td>
 </tr>
 <tr>
 <td><b>示例</b></td><td>A</td><td>E</td><td>0</td><td>0</td><td>0</td><td>0</td><td>1</td><td>0</td><td>0</td><td>1</td>
 </tr>
 <tr>
 <td><b>说明</b></td><td colspan=2>固定<br/>标识</td><td>错<br/>误<br/>级<br/>别</td><td colspan=4>错误场景</td><td colspan=3>错误编<br/>码</td>
 </tr>
 </table>
 */

public class ErrorCode implements Serializable {
    private static final long serialVersionUID = 3173429988209471897L;

    public static final int ERROR_CODE_LENGTH = 10;

    /** 开头固定标识2位 */
    private static final String PREFIX = "AE";

    /** 错误级别1位 */
    private String errorLevel;

    /** 错误场景4位 */
    private String errorScene;

    /** 具体错误码3位 */
    private String errorSpecific;

    /** 默认构造函数 */
    public ErrorCode() {}

    /**
     * 根据错误字符串构造ErrorCode对象
     */
    public ErrorCode(String errorCode) {
        checkErrorCode(errorCode);
        char[] chars = errorCode.toCharArray();
        this.errorLevel = "" + chars[2];
        this.errorScene = "" + chars[3] + chars[4] + chars[5] + chars[6];
        this.errorSpecific = "" + chars[7] + chars[8] + chars[9];
    }

    public ErrorCode(String errorLevel, String errorScene, String errorSpecific) {
        this.errorLevel = errorLevel;
        this.errorScene = errorScene;
        this.errorSpecific = errorSpecific;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        checkStringLength(this.errorLevel, 1);
        checkStringLength(this.errorScene, 4);
        checkStringLength(this.errorSpecific, 3);
        sb.append(PREFIX);
        sb.append(errorLevel);
        sb.append(errorScene);
        sb.append(errorSpecific);
        return sb.toString();
    }

    /**
     * errorCode格式校验
     */
    private void checkErrorCode(String errorCode) {
        checkStringLength(errorCode, ERROR_CODE_LENGTH);
        if (!errorCode.startsWith(PREFIX)) {
            throw new IllegalArgumentException("errorCode's prefix is illegal, errorCode: " + errorCode);
        }
    }

    /**
     * 校验字符串str的长度是否为length
     */
    private void checkStringLength(String str, int length) {
        if (str == null || str.length() != length) {
            throw new IllegalArgumentException("string length is illegal, length: " + length);
        }
    }

    public String getErrorLevel() {
        return errorLevel;
    }

    public void setErrorLevel(String errorLevel) {
        this.errorLevel = errorLevel;
    }

    public String getErrorScene() {
        return errorScene;
    }

    public void setErrorScene(String errorScene) {
        this.errorScene = errorScene;
    }

    public String getErrorSpecific() {
        return errorSpecific;
    }

    public void setErrorSpecific(String errorSpecific) {
        this.errorSpecific = errorSpecific;
    }
}
