package test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisOrderLock<T> implements OrderLock<T> {

    //  锁等待超时，防止线程饥饿，永远没有入锁执行代码的机会 
    public static final long timeout = 10000;//ms

    // 锁持有超时，防止线程在入锁以后，无限的执行下去，让锁无法释放 
    public static final long expireMsecs = 10000;// ms

    public String lockKey = "orderLockKey";

    public Jedis jedis;

    private static volatile JedisPool jedisPool;

    public RedisOrderLock(String lockKey) {
        this.lockKey = lockKey;
    }
    /**
     * 初始化redis 
     * @return
     */
    public Jedis getInstance() {
        if(jedisPool == null) {
            synchronized(RedisOrderLock.class) {
                if(jedisPool == null) {
                    JedisPoolConfig config = new JedisPoolConfig();
                    config.setMaxIdle(100);
                    jedisPool = new JedisPool(config,"localhost",6379, 3000,"test");
                }
            }
        }
        return jedisPool.getResource();
    }

    /**
     * 线程安全的业务逻辑处理
     */
    @Override
    public boolean isSyn(OrderLockBiz<T> orderBiz) {
        jedis = this.getInstance();
        try {
            // 获取到锁
            if(acquire(jedis)){
                // 执行创建订单逻辑
                orderBiz.createOrder();
            }else{
                System.out.println("waiting other thread creating");
            }
        } catch (Exception e) {
            System.err.println(e + "\n acquire lock failre");
        }finally{
            // 解锁
            this.releaseLock(jedis);
        }
        return false;
    }

    /**
     * accqure lock
     * @param jedis
     * @return
     * @throws InterruptedException
     */
    public synchronized boolean acquire(Jedis jedis){
        boolean locked = false;
        while(timeout > 0){
            long expires = System.currentTimeMillis() + expireMsecs + 1;
            // 10秒之后锁到期
            String expiresStr = String.valueOf(expires);
            // 获取到锁
            if(jedis.setnx(lockKey, expiresStr) == 1){
                locked = true;
                return locked;
            }
            // 没有获取到锁
            String oldValue = jedis.get(lockKey);
            // expireMsecs（10秒）锁的有效期内无法进入if判断，如果锁超时了
            if(oldValue != null 
                    && Long.parseLong(oldValue) < System.currentTimeMillis()){
                // 如果锁超时重新设置
                String oldValue_ = jedis.getSet(lockKey, expiresStr);
                // 值相同说明是同一个线程的操作，获取锁成功
                if(Long.valueOf(oldValue_) == Long.valueOf(oldValue)){
                    locked = true;
                }else{
                    // 被其他线程抢先获取锁
                    locked = false;
                }
            }
            // 锁没有超时，继续等待
            return false;
        }
    }
    /**
     * 释放锁
     * @param jedis
     */
    public synchronized void releaseLock(Jedis jedis){
        try {
            long current = System.currentTimeMillis();  
            // 避免删除非自己获取得到的锁
            if (current < Long.valueOf(jedis.get(lockKey)))
                jedis.del(lockKey);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            // 把用完的连接放到连接池汇中供其他线程调用
            jedisPool.returnResource(jedis);
        }
    }
}
