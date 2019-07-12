package test;

public interface OrderLock<T> {
    public boolean isSyn(OrderLockBiz<T> orderBiz);

}