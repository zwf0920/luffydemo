package com.luffy.Patterns.AbsractFactory;

public class AppleFactory implements IProductFactory{
    @Override
    public IPhoneProduct iphoneProduct() {
        return new ApplePhone();
    }

    @Override
    public IComputerProduct icomputerProduct() {
        return new AppleComputer();
    }
}
