package com.luffy.Patterns.AbsractFactory;

public interface IProductFactory {
    //生产手机
    IPhoneProduct iphoneProduct();

    //生产电脑
    IComputerProduct icomputerProduct();
}
