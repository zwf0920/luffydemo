package com.luffy.Patterns.AbsractFactory;

public class HuaweiPhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("华为手机启动");
    }

    @Override
    public void shutdown() {
        System.out.println("华为手机关闭");
    }

    @Override
    public void call() {
        System.out.println("华为手机打电话");
    }
}
