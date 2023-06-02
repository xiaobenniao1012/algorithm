package org.example.p21;

/**
 * 一个完整的括号字符串定义规则如下:
 * 1、空字符串是完整的。
 * 2、如果s是完整的字符串，那么(s)也是完整的。
 * 3、如果s和t是完整的字符串，将它们连接起来形成的st也是完整的。
 * 例如，"(()())", ““和”(())()“是完整的括号字符串，”())(”, “()(” 和 ")"是不完整的括号字符串。
 * 牛牛有一个括号字符串s,现在需要在其中任意位置尽量少地添加括号,将其转化为一个完整的括号字符串。请问牛牛至少需要添加多少个括号。
 */
public class Example_02 {

    public static void main(String[] args) {
        String s="((009)(e)e((ds))eew))))";

        System.out.println("需要添加的括号数量："+solution(s));
    }

    /**
     * 返回需要插入的个数
     * @param s
     * @return
     */
    public static int solution(String s){

        char[] chars=s.toCharArray();
        int num=0;

        int count=0;
        for(int i=0;i<chars.length;i++){
            if(chars[i]=='('){
                count++;
                continue;
            }

            if(chars[i]==')'){
                count--;
            }
            //如果count小于0，则需要补充左括号
            if(count<0){
                num++;
                count=0;
            }
        }

        return num+count;
    }
}
