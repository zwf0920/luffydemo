package com.luffy.Patterns.AbsractFactory;

public class Test {
    public static void main(String[] args) {
        AppleFactory appleFactory = new AppleFactory();
        IComputerProduct iComputerProduct =appleFactory.icomputerProduct();
        iComputerProduct.start();
        iComputerProduct.work();
        iComputerProduct.shutdown();

        HuaweiFactory huaweiFactory = new HuaweiFactory();
        IComputerProduct huaweicomputer =huaweiFactory.icomputerProduct();
        huaweicomputer.start();
        huaweicomputer.work();
        huaweicomputer.shutdown();
    }
}
