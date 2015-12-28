package com.mfq.admin.web.freemarker;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang.StringUtils;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateModelException;

/**
 * 三个参数，源字符串，截取长度(以中文字符为标准，英文字符以及半角标点当做半个字符计算)，截取后追加的字符
 * Author: deanzhu(deanzhu@shijiebang.net)
 * Date: 13-5-14 上午10:44
 */
public class SubstringByWordWidthMethodModel implements TemplateMethodModel {
    private static Set<Character.UnicodeBlock> ubSet = new HashSet<Character.UnicodeBlock>();
    static {
        ubSet.addAll(Arrays.asList(new Character.UnicodeBlock[]{
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS, Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS,
                Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A, Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B,
                Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION, Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS,
                Character.UnicodeBlock.GENERAL_PUNCTUATION
        }));
    }

    @Override
    public Object exec(List arguments) throws TemplateModelException {
        String postfix = "";
        String source = (String) arguments.get(0);
        if (StringUtils.isEmpty(source)) {
            return new SimpleScalar("");
        }
        source = source.trim().replaceAll("<[^>]*>", "");

        int length = Integer.parseInt((String) arguments.get(1));
        if (arguments.size() > 2) {
            postfix = (String) arguments.get(2);
        }

        int chineseWidthUnit = 3;
        int englishWidthUnit = 2;
        if (arguments.size() > 3) {
            chineseWidthUnit = Integer.parseInt((String) arguments.get(3));
        }
        if (arguments.size() > 4) {
            englishWidthUnit = Integer.parseInt((String) arguments.get(4));
        }

        int postfixWidthCapacity = 0;
        for (char c : postfix.toCharArray()) {
            if (isChinese(c)) {
                postfixWidthCapacity += chineseWidthUnit;
            } else {
                postfixWidthCapacity += englishWidthUnit;
            }
        }

        char[] sourceChars = source.toCharArray();
        int widthCapacity = length*chineseWidthUnit;
        int widthCapacityWithoutPostfix = widthCapacity - postfixWidthCapacity;

        int widthCount = 0;
        int charCount = 0;
        int charCountWithoutPostfix = 0;
        for (char c : sourceChars) {
            if (isChinese(c)) {
                widthCount += chineseWidthUnit;
            } else {
                widthCount += englishWidthUnit;
            }
            if (widthCount < widthCapacityWithoutPostfix) {
                charCountWithoutPostfix++;
            }
            if (widthCount > widthCapacity) {
                break;
            }
            charCount++;
        }

        if (charCount == sourceChars.length) {
            return new SimpleScalar(source);
        } else {
            return new SimpleScalar(source.substring(0, charCountWithoutPostfix) + postfix);
        }
    }

    private boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ubSet.contains(ub)) {
            return true;
        }
        return false;
    }
}