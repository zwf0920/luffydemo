package com.luffy.Patterns.AbsractFactory;

public class HuaweiFactory implements IProductFactory{
    @Override
    public IPhoneProduct iphoneProduct() {
        return new HuaweiPhone();
    }

    @Override
    public IComputerProduct icomputerProduct() {
        return new HuaweiComputer();
    }
}
