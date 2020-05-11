package com.luffy.Patterns.AbsractFactory;

public class AppleComputer implements IComputerProduct {
    @Override
    public void start() {
        System.out.println("苹果电脑启动");
    }

    @Override
    public void shutdown() {
        System.out.println("苹果电脑关闭");
    }

    @Override
    public void work() {
        System.out.println("苹果电脑工作");
    }
}
