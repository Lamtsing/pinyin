package com.lamtsing.pinyin;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class Pinyin {

    /**
     * 获取姓名拼音的首字母
     *
     * @param str
     * @return
     */
    public static String getTopPinYin(String str) {

        char[] t1 = str.toCharArray();
        String[] t2 = new String[t1.length];
        HanyuPinyinOutputFormat outputFormat = new HanyuPinyinOutputFormat();

        outputFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
        outputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        String pinyin = "";
        try {
            for (int i = 0; i < t1.length; i++) {
                // 判断是否为汉字字符
                if (java.lang.Character.toString(t1[i]).matches(
                        "[\\u4E00-\\u9FA5]+")) {
                    t2 = PinyinHelper.toHanyuPinyinStringArray(t1[i], outputFormat);
                    pinyin += t2[0].charAt(0);
                } else
                    pinyin += java.lang.Character.toString(t1[i]);
            }
            return pinyin.replaceAll("\\W", "").trim();
        } catch (BadHanyuPinyinOutputFormatCombination e1) {
            e1.printStackTrace();
        }
        return pinyin;
    }

    /**
     * 将字符串中的中文转化为拼音,其他字符不变
     *
     * @param str
     * @return
     */
    public static String getPingYin(String str) {
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        format.setVCharType(HanyuPinyinVCharType.WITH_V);

        char[] input = str.trim().toCharArray();
        String output = "";

        try {
            for (int i = 0; i < input.length; i++) {
                if (java.lang.Character.toString(input[i]).matches("[\\u4E00-\\u9FA5]+")) {
                    String[] temp = PinyinHelper.toHanyuPinyinStringArray(input[i], format);
                    output += temp[0];
                } else
                    output += java.lang.Character.toString(input[i]);
            }
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return output;
    }

}
