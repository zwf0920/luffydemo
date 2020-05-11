package com.luffy.Patterns.AbsractFactory;

public class HuaweiComputer implements IComputerProduct {
    @Override
    public void start() {
        System.out.println("华为电脑启动");
    }

    @Override
    public void shutdown() {
        System.out.println("华为电脑关闭");
    }

    @Override
    public void work() {
        System.out.println("华为电脑工作");
    }
}
