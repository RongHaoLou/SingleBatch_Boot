package com.indusfo.spc.vo;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName SAXReader
 * @Description //TDD
 * @Author hewb
 * @Date 2019/3/21 17:39
 **/
public class SAXReaderFile {
    /**
     * @return java.lang.String
     * @Author hewb
     * @Description //TODO dom4j解析xml文件
     * 迭代方式解析xml文件 只支持字符串的xml文件解析
     * @Date 2019/3/22 8:40
     * @Param [file]
     */
    public static String saxReaderFile(String file) throws Exception {
        String Intercept = null;
        Document document = DocumentHelper.parseText(file);

        Element rootElement = document.getRootElement();
        Iterator iterator = rootElement.elementIterator();
        while (iterator.hasNext()) {
            Element el = (Element) iterator.next();

            Iterator iterator1 = el.elementIterator();

            while (iterator1.hasNext()) {
                Element el1 = (Element) iterator1.next();
                Iterator iterator2 = el1.elementIterator();
                while (iterator2.hasNext()) {
                    Element el2 = (Element) iterator2.next();
                    String name2 = el2.getName();
                    String text2 = el2.getText();
                    Intercept = el2.getText();
                    Iterator iterator3 = el2.elementIterator();
                    while (iterator3.hasNext()) {
                        Element el3 = (Element) iterator3.next();
                        String name3 = el3.getName();
                        String text3 = el3.getText();
                        Intercept = el2.getText();


                    }

                }
            }
        }
        return Intercept;
    }

    /**
     * @return java.lang.String
     * @Author hewb
     * @Description //TODO 换行
     * @Date 2019/3/22 8:40
     * @Param [level]
     */
    private static String getLevelStr(int level) {
        StringBuffer levelStr = new StringBuffer();
        for (int levelI = 0; levelI < level; levelI++) {
            levelStr.append("\t");
        }
        return levelStr.toString();
    }

    /**
     * @return java.lang.String
     * @Author hewb
     * @Description //TODO 将json数据格式话
     * @Date 2019/3/22 8:39
     * @Param [json]
     */
    public static String TransformJson(String json) {
        //json 字符串
        int level = 0;
        //存放格式化的json字符串
        StringBuffer jsonForMatStr = new StringBuffer();
        for (int index = 0; index < json.length(); index++)//将字符串中的字符逐个按行输出
        {
            //获取s中的每个字符
            char c = json.charAt(index);
//          System.out.println(s.charAt(index));

            //level大于0并且jsonForMatStr中的最后一个字符为\n,jsonForMatStr加入\t
            if (level > 0 && '\n' == jsonForMatStr.charAt(jsonForMatStr.length() - 1)) {
                jsonForMatStr.append(getLevelStr(level));
//                System.out.println("123"+jsonForMatStr);
            }
            //遇到"{"和"["要增加空格和换行，遇到"}"和"]"要减少空格，以对应，遇到","要换行
            switch (c) {
                case '{':
                case '[':
                    jsonForMatStr.append(c + "\n");
                    level++;
                    break;
                case ',':
                    jsonForMatStr.append(c + "\n");
                    break;
                case '}':
                case ']':
                    jsonForMatStr.append("\n");
                    level--;
                    jsonForMatStr.append(getLevelStr(level));
                    jsonForMatStr.append(c);
                    break;
                default:
                    jsonForMatStr.append(c);
                    break;
            }
        }

        return jsonForMatStr.toString();
    }


    /**
     * @return java.lang.String
     * @Author hewb
     * @Description //TODO 截取字符串
     * @Date 2019/3/22 8:39
     * @Param [str, strStart, strEnd]
     */
    public static String subString(String str, String strStart, String strEnd) {

        /* 找出指定的2个字符在 该字符串里面的 位置 */
        int strStartIndex = str.indexOf(strStart);
        int strEndIndex = str.lastIndexOf(strEnd);

        /* index 为负数 即表示该字符串中 没有该字符 */
        if (strStartIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strStart + ", 无法截取目标字符串";
        }
        if (strEndIndex < 0) {
            return "字符串 :---->" + str + "<---- 中不存在 " + strEnd + ", 无法截取目标字符串";
        }
        /* 开始截取 */
        String result = str.substring(strStartIndex, strEndIndex).substring(strStart.length());
        return result;
    }


    public static String castString(String name, String string) throws Exception {
        boolean blean = false;
        Matcher matcher = null;
        String substring = null;
        if (name != null) {
            String regex = "\"" + name + "\":\".*\",";
            Pattern pattern = Pattern.compile(regex);
            matcher = pattern.matcher(string);
            blean = matcher.find();
            if (blean == false) {
                String regex1 = "\"" + name + "\":\".*\",";
                Pattern pattern1 = Pattern.compile(regex1);
                matcher = pattern1.matcher(string);
                blean = matcher.find();
            }

        }

        if (blean) {
            String substr = matcher.group();
            // 找出第一个 : 号的位置
            int i = substr.indexOf(":");
            // 截取 : 后一位开始 ～ 字符串末尾 不包含末尾 "
            substring = substr.substring(i + 2, substr.length() - 2);

        } else {
            throw new Exception("没有找到匹配字符");
        }
        return substring;
    }

}
