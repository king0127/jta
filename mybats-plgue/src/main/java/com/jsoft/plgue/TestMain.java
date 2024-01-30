package com.jsoft.plgue;


import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
    public class TestMain {
        public static void main(String[] args) {

            String email = "wangping123@lixiang.com";
            String username = extractUsername(email);
            System.out.println("邮箱用户名：" + username);

//            String expression = "status >= 2 or meetingId > 1 and topicId != null";
            String expression = "status >= 2";
            List<Boolean> conditions = parseConditions(expression);
            List<String> relations = parseRelations(expression);
            System.out.println("Conditions: " + conditions);
            System.out.println("Relations: " + relations);
            if(CollectionUtils.isEmpty(relations)) {
                System.out.println(conditions.get(0));
            } else {
                String[] strings = relations.toArray(new String[relations.size()]);
                System.out.println(calculateResult(conditions, strings));
            }
        }


        public static String extractUsername(String email) {
            /**
             * 正则表达式  ^(.+)@.*$  的意思是匹配以 "@" 符号为分隔的邮箱地址，并提取 "@" 前面的部分作为第一个捕获组。
             *  具体解释如下：
             * -  ^  表示匹配字符串的开头。
             * -  (.+)  是一个捕获组，用于匹配任意字符（除换行符外）至少一次。括号内的  .  表示匹配任意字符， +  表示至少匹配一次。
             * -  @  表示匹配 "@" 符号。
             * -  .*  表示匹配任意字符（除换行符外）零次或多次。
             * -  $  表示匹配字符串的结尾。
             */
            String regex = "^(.+)@.*$";
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(email);
            if (matcher.find()) {
                return matcher.group(1);
            }
            return "";
        }


        public static List<Boolean> parseConditions(String expression) {
            String[] conditionsArray = expression.split("(?i)\\s+(and|or)\\s+");
            List<Boolean> regex = new ArrayList<>();
            for (String str : conditionsArray) {
                regex(str, regex);
            }
            return regex;
        }
        public static List<String> parseRelations(String expression) {
            List<String> relations = new ArrayList<>();
            Pattern pattern = Pattern.compile("(?i)\\s+(and|or)\\s+");
            Matcher matcher = pattern.matcher(expression);
            while (matcher.find()) {
                relations.add(matcher.group(1));
            }
            return relations;
        }


        public static void regex(String input, List<Boolean> regex) {
            String RULE_PATTERN = "\\b(\\w+)\\s*(=|!=|>=|>|<=|<)\\s*([\\w']+)";
            Pattern regexPattern = Pattern.compile(RULE_PATTERN);
            // 创建Matcher对象并与输入字符串进行匹配
            Matcher matcher = regexPattern.matcher(input);
            // 循环查找匹配的子字符串并进行处理
            while (matcher.find()) {
                String variable = matcher.group(1);
                String operator = matcher.group(2);
                String value = matcher.group(3);
                // 在这里可以对匹配到的变量名、操作符和值进行进一步处理
                System.out.println("变量名：" + variable);
                System.out.println("操作符：" + operator);
                System.out.println("值：" + value);
                regex.add(true);
            }
        }


        public static boolean calculateResult(List<Boolean> values, String[] relations) {
            boolean result = values.get(0);
            for (int i = 1; i < values.size(); i++) {
                if (relations[i - 1].equals("or")) {
                    result = result || values.get(i);
                } else if (relations[i - 1].equals("and")) {
                    result = result && values.get(i);
                }
            }
            return result;
        }

    }


