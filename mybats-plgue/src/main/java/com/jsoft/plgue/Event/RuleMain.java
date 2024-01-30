package com.jsoft.plgue.Event;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RuleMain {

    public static void main(String[] args) {
        String input = "status == 2 and meetingId != null or name = 'John'";
//        String pattern = "(\\b\\w+\\s*(==|!=)\\s*(\\w+|'\\w+')\\b)\\s+(and|or)\\s+";
//        String pattern = "(\\b\\w+\\s*(=|!=)\\s*(\\w+|'\\w+')\\b)\\s+(and|or)\\s*";
        String pattern = "(\\b\\w+\\s*(==|!=)\\s*(\\w+|\\(.*?\\))\\b)\\s+(and|or)\\s*";

        Pattern regexPattern = Pattern.compile(pattern);
        Matcher matcher = regexPattern.matcher(input);

        while (matcher.find()) {
            String condition = matcher.group(1);
            String relation = matcher.group(4);

            System.out.println("条件：" + condition);
            System.out.println("关系词：" + relation);
        }
    }

}
