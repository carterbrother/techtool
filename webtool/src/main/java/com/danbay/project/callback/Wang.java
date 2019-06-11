package com.danbay.project.callback;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 */
public class Wang implements CallBack {


    private Li li = new Li();


    public Wang(){}

    public void askQuestion(String question)
    {
        new Thread(() -> {
            System.out.println("小李你好，问个问题？"+question);
            li.executeMessage(this,question);
        }).start();

        play();
    }

    private void play() {
        System.out.println("逛街去了....");
    }


    @Override
    public void solve(String result) {

        System.out.println("小李的答案是：" + result);


    }


    public static void main(String[] args) {

        Wang wang  =new Wang();
        wang.askQuestion("1+1=多少？");


    }

}
