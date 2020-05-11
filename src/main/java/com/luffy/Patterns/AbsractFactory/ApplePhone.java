package com.luffy.Patterns.AbsractFactory;

public class ApplePhone implements IPhoneProduct {
    @Override
    public void start() {
        System.out.println("苹果手机启动");
    }

    @Override
    public void shutdown() {
        System.out.println("苹果手机关闭");
    }

    @Override
    public void call() {
        System.out.println("苹果手机打电话");
    }
}
