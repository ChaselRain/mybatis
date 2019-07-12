package test;

/**
 * 使用redis锁来控制并发抢单
 * @author fuyuwei
 */
public class OrderBiz {
    public int createOrder(){
        // 下单之前的参数、合法性校验这里就不在演示
        OrderLock<Boolean> orderLock = new RedisOrderLock<Boolean>("pro-12345678901");
        boolean isSyn = orderLock.isSyn(new OrderLockBiz<Boolean>(){
            @Override
            public Boolean createOrder() {
                // 省去创建订单逻辑
                return null;
            }
        });
        if(!isSyn){
            System.err.println("创建订单失败");
        }
        return 0;
    }
}
