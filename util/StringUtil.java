package com.suyaoxing.util;

import java.util.UUID;

public class StringUtil {

    private StringUtil() {
        super();
    }

    /**
     * ��ȥnull��""
     * @param src
     * @return
     */
    public static String formatNull(String src) {
        return (src == null || "null".equals(src)) ? "" : src;
    }

    /**
     * �ж��ַ����Ƿ�Ϊ�յ�������ʽ���հ��ַ���Ӧ��unicode����
     */
    private static final String EMPTY_REGEX = "[\\s\\u00a0\\u2007\\u202f\\u0009-\\u000d\\u001c-\\u001f]+";

    /**
     * ��֤�ַ����Ƿ�Ϊ��
     * 
     * @param input
     * @return
     */
    public static boolean isEmpty(String input) {
        return input == null || input.equals("") || input.matches(EMPTY_REGEX);
    }

    public static boolean isNotEmpty(String input){
        return !isEmpty(input);
    }

    public static String getUuid() {
        return UUID.randomUUID().toString();
    }
}