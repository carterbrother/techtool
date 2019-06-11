package com.danbay.project.callback;

/**
 * Created by carter on 2017/5/26. Copyright © 2016 －2017 旦倍科技
 */
public class Li {


    public void executeMessage(CallBack callBack,String question)
    {
        System.out.println("小王问的问题........"+ question);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        String result = "答案是 2 ";

        callBack.solve(result);

    }


}
